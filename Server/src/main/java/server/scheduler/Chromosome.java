package server.scheduler;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// Хромосома представляет собой массив генов в виде полного расписания (выглядит как ген [0] ген [1] ген [2] ...)
//Chromosome represents array of genes as complete timetable (looks like gene[0]gene[1]gene[2]...)
public class Chromosome implements Comparable<Chromosome>,Serializable{
	
	static double crossoverrate=inputdata.crossoverrate;
	static double mutationrate=inputdata.mutationrate;
	public static int hours=inputdata.hoursperday,days=inputdata.daysperweek;
	public static int nostgrp=inputdata.nostudentgroup;
	double fitness;
	int point;
	
	public Gene[] gene;
	
	Chromosome(){
		
		gene=new Gene[nostgrp];
		
		for(int i=0;i<nostgrp;i++){
			
			gene[i]=new Gene(i);
			
			//System.out.println("");
		}
		fitness=getFitness();		
		
	}



	public Chromosome deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Chromosome) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public double getFitness(){
		point=0;
		for(int i=0;i<hours*days;i++){
			
			List<String> teacherlist=new ArrayList<String>();
			
			for(int j=0;j<nostgrp;j++){
			
				Slot slot;
				//System.out.println("i="+i+" j="+j);
				if(TimeTable.slot[gene[j].slotno[i]]!=null)
					slot=TimeTable.slot[gene[j].slotno[i]];
				else slot=null;

				if(slot!=null){
				
					if(teacherlist.contains(slot.teacherid)){
						point++;
					}
					else teacherlist.add(slot.teacherid);
				}
			}	
			
			
		}
		//System.out.println(point);
		fitness=1-(point/((nostgrp-1.0)*hours*days));
		point=0;
		return fitness;
	}


	// печать окончательного расписания
	//printing final timetable
	public void printTimeTable(){


		// для каждой группы студентов отдельное расписание
		//for each student group separate time table
		for(int i=0;i<nostgrp;i++){


			// статус, используемый для получения имени группы студентов, потому что, если первый класс свободен, будет выдана ошибка
			//status used to get name of student grp because in case first class is free it will throw error
			boolean status=false;
			int l=0;
			while(!status){

				// выводим название партии
				//printing name of batch
				if(TimeTable.slot[gene[i].slotno[l]]!=null){
					System.out.println("Batch "+TimeTable.slot[gene[i].slotno[l]].studentgroup.name+" Timetable-");
					
					status=true;
				}
				l++;
			
			}


			// цикл на каждый день
			//looping for each day
			for(int j=0;j<days;j++){


				// цикл на каждый час дня
				//looping for each hour of the day
				for(int k=0;k<hours;k++){

					// проверяем, свободен ли этот слот, иначе выводим его
					//checking if this slot is free otherwise printing it
					if(TimeTable.slot[gene[i].slotno[k+j*hours]]!=null) {

						System.out.print(TimeTable.slot[gene[i].slotno[k + j * hours]].subject + " ");
						System.out.print(TimeTable.slot[gene[i].slotno[k + j * hours]].teacherid + " ");
					}
					else System.out.print("*FREE* ");
				
				}
				
				System.out.println("");
			}
			
			System.out.println("\n\n\n");
		
		}

	}

//***************************************************
	public void printTimeTableExel(){
		int indexRow = 0;
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Raspisanie");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);

		Row header = sheet.createRow(indexRow);
		indexRow++;

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Расписание");
		headerCell.setCellStyle(headerStyle);

//		headerCell = header.createCell(1);
//		headerCell.setCellValue("Age");
//		headerCell.setCellStyle(headerStyle);

		// для каждой группы студентов отдельное расписание
		//for each student group separate time table
		for(int i=0;i<nostgrp;i++){


			// статус, используемый для получения имени группы студентов, потому что, если первый класс свободен, будет выдана ошибка
			//status used to get name of student grp because in case first class is free it will throw error
			boolean status=false;
			int l=0;
			while(!status){

				// выводим название партии
				//printing name of batch
				if(TimeTable.slot[gene[i].slotno[l]]!=null){

					Row nameClasses = sheet.createRow(indexRow);
					indexRow++;

					nameClasses.createCell(0).setCellValue("Класс");
					Cell nameClass = nameClasses.createCell(1);
					nameClass.setCellValue(TimeTable.slot[gene[i].slotno[l]].studentgroup.name);
					//System.out.println("Batch "+TimeTable.slot[gene[i].slotno[l]].studentgroup.name+" Timetable-");

					status=true;
				}
				l++;

			}


			// цикл на каждый день
			//looping for each day
			for(int j=0;j<days;j++){
				Row day = sheet.createRow(indexRow);
				indexRow++;
				day.createCell(0).setCellValue("День "+ (j+1));
				// цикл на каждый час дня
				//looping for each hour of the day
				for(int k=0;k<hours;k++){
					Cell lesson = day.createCell(k+1);
					// проверяем, свободен ли этот слот, иначе выводим его
					//checking if this slot is free otherwise printing it
					if(TimeTable.slot[gene[i].slotno[k+j*hours]]!=null) {
						lesson.setCellValue(TimeTable.slot[gene[i].slotno[k + j * hours]].subject + " ("+TimeTable.slot[gene[i].slotno[k + j * hours]].teacherid + ") ");
						//System.out.print(TimeTable.slot[gene[i].slotno[k + j * hours]].subject + " ");
						//System.out.print(TimeTable.slot[gene[i].slotno[k + j * hours]].teacherid + " ");
					}
					else {
						lesson.setCellValue("*FREE*");
						//System.out.print("*FREE* ");
					}

				}

				//System.out.println("");
			}
			indexRow++;
			//System.out.println("\n\n\n");

		}

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "Server\\src\\main\\resources\\temp.xlsx";

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	public void printChromosome(){
		
		for(int i=0;i<nostgrp;i++){
			for(int j=0;j<hours*days;j++){
				System.out.print(gene[i].slotno[j]+" ");
			}
			System.out.println("");
		}
		
	}



	public int compareTo(Chromosome c) {
		
		if(fitness==c.fitness) return 0;
		else if(fitness>c.fitness) return -1;
		else return 1;
	
	}
	
	
	
}