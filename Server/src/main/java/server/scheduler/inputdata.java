package server.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class inputdata {

	public static StudentGroup[] studentgroup;
	public static Teacher[] teacher;
	public static double crossoverrate = 1.0, mutationrate = 0.1;
	public static int nostudentgroup, noteacher;
	public static int hoursperday, daysperweek;

	public inputdata() {
		studentgroup = new StudentGroup[100];
		teacher =   new Teacher[100];
	}

	boolean classformat(String l) {
		StringTokenizer st = new StringTokenizer(l, " ");
		if (st.countTokens() == 3)
			return (true);
		else
			return (false);
	}

							// принимает ввод из файла input.txt
	public void takeinput()// takes input from file input.txt
	{
		// этот метод ввода через файл предназначен только для целей разработки, поэтому часы и дни жестко запрограммированы
		//this method of taking input through file is only for development purpose so hours and days are hard coded
		hoursperday = 7;
		daysperweek = 6;
		try {
			File file = new File("C:\\Users\\artyo\\IdeaProjects\\tes\\src\\input.txt");
			// File file = new File(System.getProperty("user.dir") +
			// "/input.txt");
			
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				// вводим студенческие группы
				// input student groups
				if (line.equalsIgnoreCase("studentgroups")) {
					int i = 0, j;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("teachers")) {
						studentgroup[i] = new StudentGroup();
						StringTokenizer st = new StringTokenizer(line, " ");
						studentgroup[i].id = i;
						studentgroup[i].name = st.nextToken();
						studentgroup[i].nosubject = 0;
						j = 0;
						while (st.hasMoreTokens()) {
							studentgroup[i].subject[j] = st.nextToken();
							studentgroup[i].hours[j++] = Integer.parseInt(st.nextToken());
							studentgroup[i].nosubject++;
						}
						i++;
					}
					nostudentgroup = i;
				}

				// вводим учителя
				if (line.equalsIgnoreCase("teachers")) {
					int i = 0;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("end")) {
						teacher[i] = new Teacher();
						StringTokenizer st = new StringTokenizer(line, " ");
						teacher[i].id = i;
						teacher[i].name = st.nextToken();
						teacher[i].subject = st.nextToken();

						i++;
					}
					noteacher = i;
				}

			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//assignTeacher();

	}

	// assigning a teacher for each subject for every studentgroup
	// назначаем учителя по каждому предмету для каждой группы студентов
/*	public void assignTeacher() {

		// looping through every studentgroup
		// перебираем каждую студенческую группу
		for (int i = 0; i < nostudentgroup; i++) {

			// looping through every subject of a student group
			// перебираем каждый предмет студенческой группы
			for (int j = 0; j < studentgroup[i].nosubject; j++) {

				int teacherid = -1;
				int assignedmin = -1;

				String subject = studentgroup[i].subject[j];

				// looping through every teacher to find which teacher teaches the current subject
				// перебираем каждого учителя, чтобы найти, какой учитель преподает текущий предмет
				for (int k = 0; k < noteacher; k++) {

					// если такой учитель найден, проверяем, следует ли ему назначить предмет или другого учителя на основании предыдущих заданий
					// if such teacher found,checking if he should be assigned the subject or some other teacher based on prior assignments
					if (teacher[k].subject.equalsIgnoreCase(subject)) {

						// если первый учитель найден по этому предмету
						// if first teacher found for this subject
						if (assignedmin == -1) {
							assignedmin = teacher[k].assigned;
							teacherid = k;
						}

						// если у найденного учителя нет предварительных заданий меньше, чем у учителя, назначенного для этого предмета
						// if teacher found has less no of pre assignments than the teacher assigned for this subject
						else if (assignedmin > teacher[k].assigned) {
							assignedmin = teacher[k].assigned;
							teacherid = k;
						}
					}
				}


				// 'назначенная' переменная для выбранного учителя увеличена
				// 'assigned' variable for selected teacher incremented
				teacher[teacherid].assigned++;

				studentgroup[i].teacherid[j]= teacherid;
			}
		}
	}*/



	public inputdata(BufferedReader answer) throws IOException {

		hoursperday = 5;
		daysperweek = 3;



		String line = answer.readLine();

		int numberStudents = Integer.parseInt(line);

		studentgroup = new StudentGroup[numberStudents];

		for (int i = 0; i < numberStudents; i++){
			String name = answer.readLine();
			int numberSubjects = Integer.parseInt(answer.readLine());

			studentgroup[i] = new StudentGroup(numberSubjects);
			studentgroup[i].id = i;
			studentgroup[i].name = name;
			studentgroup[i].nosubject = 0;

			for (int j = 0; j < numberSubjects; j++){
				studentgroup[i].subject[j] = answer.readLine();
				System.out.println(studentgroup[i].subject[j]);
				studentgroup[i].teacherid[j]= answer.readLine();
				System.out.println(studentgroup[i].teacherid[j]);
				studentgroup[i].hours[j] = Integer.parseInt(answer.readLine());
				studentgroup[i].nosubject++;
			}
		}

		nostudentgroup = numberStudents;

	}




}