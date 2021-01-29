package server.testing;

import server.database.BDManager;
import server.database.DataManager;
import server.database.tables.Lesson;
import server.database.tables.SchoolClass;
import server.database.tables.Subject;
import server.database.tables.Teacher;

public class TestBD {
    public static void main(String[] args) {
        Teacher[] teachers = {  new Teacher("Василий Петрович"),
                                new Teacher("Марина Генадьевна"),
                                new Teacher("Петр Иванович")
        };

        SchoolClass[] schoolClasses = {  new SchoolClass("5А"),
                            new SchoolClass("7Б"),
                            new SchoolClass("10В")
        };

        Subject[] subjects = {  new Subject("Биология"),
                                new Subject("Математика"),
                                new Subject("Физика")
        };

        for(int i=0; i<3; i++){
            DataManager.addGroup(schoolClasses[i].getName_group());
            DataManager.addSubject(subjects[i].getName_subject());
            DataManager.addTeacher(teachers[i].getName());
        }

        System.out.println("Выводим группы:\n"+DataManager.getAllGroup());
        System.out.println("Выводим учителей:\n"+DataManager.getAllTeacher());
        System.out.println("Выводим предметы:\n"+DataManager.getAllSubjectr());


        DataManager.deleteGroup(schoolClasses[2].getName_group());
        DataManager.deleteSubject(subjects[2].getName_subject());
        DataManager.deleteTeacher(teachers[2].getName());

        System.out.println("Выводим группы:\n"+DataManager.getAllGroup());
        System.out.println("Выводим учителей:\n"+DataManager.getAllTeacher());
        System.out.println("Выводим предметы:\n"+DataManager.getAllSubjectr());

        for(int i=0; i<8; i++){
            DataManager.addLesson(schoolClasses[i/4].getName_group(),subjects[i%2].getName_subject(), teachers[i&1].getName(), 3+"");
        }

        System.out.println("Выводим все уроки\n"+DataManager.getAllLessons());
        for(int i=0; i<2; i++){
            System.out.println("******************************");
            System.out.println("Выводим по уроки по группе\n"+DataManager.getLessonsByGroup(schoolClasses[i].getName_group()));
            System.out.println("Выводим по уроки по Учителю\n"+DataManager.getLessonsByTeacher(teachers[i].getName()));
            System.out.println("Выводим по уроки по предмету\n"+DataManager.getLessonsBySubject(subjects[i].getName_subject()));
        }

        System.out.println("--------- Удаляем учителя --------------");
        DataManager.deleteTeacher(teachers[1].getName());
        DataManager.updateLesson("65", "7Б", "Математика", "Василий Петрович", "6");
        System.out.println("Выводим все уроки\n"+DataManager.getAllLessons());
    }

}
