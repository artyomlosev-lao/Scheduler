package server.outputdata;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import server.scheduler.Chromosome;
import server.scheduler.SchedulerMain;
import server.scheduler.TimeTable;
import server.scheduler.inputdata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Exel {


    public static void writeIntoExcel(String file) throws FileNotFoundException, IOException {

        // захватываем последнюю хромосому, т.е. вывод
        //grabs final chromosome i.e. the output
        Chromosome finalson = SchedulerMain.finalson;


        int days = inputdata.daysperweek;
        int hours = inputdata.hoursperday;
        int nostgrp = inputdata.nostudentgroup;

        if (file.charAt(file.length()-1) == 'x') {
            Workbook book = new XSSFWorkbook();
            Sheet sheet = book.createSheet("Расписание");


/*

            Row row = sheet.createRow(0);

            // для каждой группы студентов отдельное расписание
            //for each student group separate time table
            for(int i=0;i<finalson.nostgrp;i++){


               //
                //
                // Row row = sheet.createRow(i);



                // статус, используемый для получения имени группы студентов, потому что, если первый класс свободен, будет выдана ошибка
                //status used to get name of student grp because in case first class is free it will throw error
                boolean status=false;
                int l=0;
                while(!status){

                    // выводим название партии
                    //printing name of batch
                    if(TimeTable.slot[finalson.gene[i].slotno[l]]!=null){
                        System.out.println("Batch "+TimeTable.slot[finalson.gene[i].slotno[l]].studentgroup.name+" Timetable-");

                        Cell nameGroup = row.createCell(l);
                        nameGroup.setCellValue(TimeTable.slot[finalson.gene[i].slotno[l]].studentgroup.name);

                        status=true;
                    }
                    l++;

                }

                 // цикл на каждый день
                //looping for each day
                for(int j=0;j<finalson.days;j++){


                    // цикл на каждый час дня
                    //looping for each hour of the day
                    for(int k=0;k<finalson.hours;k++) {

                        Cell subject = row.createCell(k + j * finalson.hours);
                        Cell teacher = row.createCell(k + j * finalson.hours);
                        // проверяем, свободен ли этот слот, иначе выводим его
                        //checking if this slot is free otherwise printing it
                        if (TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]] != null){



                            System.out.print(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].subject + " ");
                            System.out.print(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].teacherid + " ");


                            subject.setCellValue(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].subject);
                            teacher.setCellValue(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].teacherid);


                        }else System.out.print("*FREE* ");

                    }

                    System.out.println("");
                }

                System.out.println("\n\n\n");

            }

*/
            // Меняем размер столбца
            sheet.autoSizeColumn(1);

            // Записываем всё в файл
            book.write(new FileOutputStream(file));
            //  book.close();


        }
        else {

            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("Расписание");












            Row row = sheet.createRow(0);

            // для каждой группы студентов отдельное расписание
            //for each student group separate time table
            for(int i=0;i<finalson.nostgrp;i++){


                //
                //
                // Row row = sheet.createRow(i);



                // статус, используемый для получения имени группы студентов, потому что, если первый класс свободен, будет выдана ошибка
                //status used to get name of student grp because in case first class is free it will throw error
                boolean status=false;
                int l=0;
                while(!status){

                    // выводим название партии
                    //printing name of batch
                    if(TimeTable.slot[finalson.gene[i].slotno[l]]!=null){
                        System.out.println("Batch "+TimeTable.slot[finalson.gene[i].slotno[l]].studentgroup.name+" Timetable-");

                        Cell nameGroup = row.createCell(l);
                        nameGroup.setCellValue(TimeTable.slot[finalson.gene[i].slotno[l]].studentgroup.name);

                        status=true;
                    }
                    l++;

                }

                // цикл на каждый день
                //looping for each day
                for(int j=0;j<finalson.days;j++){


                    // цикл на каждый час дня
                    //looping for each hour of the day
                    for(int k=0;k<finalson.hours;k++) {

                        Cell subject = row.createCell(k + j * finalson.hours);
                        Cell teacher = row.createCell(k + j * finalson.hours);
                        // проверяем, свободен ли этот слот, иначе выводим его
                        //checking if this slot is free otherwise printing it
                        if (TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]] != null){



                            System.out.print(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].subject + " ");
                            System.out.print(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].teacherid + " ");


                            subject.setCellValue(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].subject);
                            teacher.setCellValue(TimeTable.slot[finalson.gene[i].slotno[k + j * finalson.hours]].teacherid);


                        }else System.out.print("*FREE* ");

                    }

                    System.out.println("");
                }

                System.out.println("\n\n\n");

            }







            // Меняем размер столбца
            sheet.autoSizeColumn(1);

            // Записываем всё в файл
            book.write(new FileOutputStream(file));
            //  book.close();

        }
    }



}
