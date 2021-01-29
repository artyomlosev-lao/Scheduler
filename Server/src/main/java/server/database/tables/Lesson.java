package server.database.tables;

import javax.persistence.*;

@Entity
public class Lesson {
    public Lesson(){};
    public Lesson(SchoolClass schoolClass, Subject subject, Teacher teacher, int hours){
        this.schoolClass = schoolClass;
        this.subject = subject;
        this.teacher = teacher;
        this.hours = hours;
    }

    public Lesson(int id, SchoolClass schoolClass, Subject subject, Teacher teacher, int hours){
        this.id = id;
        this.schoolClass = schoolClass;
        this.subject = subject;
        this.teacher = teacher;
        this.hours = hours;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    @Column
    private int hours;

    public int getHours() { return hours;}
    public void setHours(int hours) { this.hours = hours;}

    @ManyToOne
    private SchoolClass schoolClass;

    public SchoolClass getSchoolClass() { return schoolClass;}
    public void setSchoolClass(SchoolClass schoolClass) { this.schoolClass = schoolClass;}

    @ManyToOne
    private Subject subject;

    public Subject getSubject() { return subject;}
    public void setSubject(Subject subject) { this.subject = subject;}

    @ManyToOne
    private Teacher teacher;

    public Teacher getTeacher() { return teacher;}
    public void setTeacher(Teacher teacher) { this.teacher = teacher;}

    @Override
    public String toString() {
        return id+"\n"+ schoolClass.getName_group()+"\n"+subject.getName_subject()+"\n"+teacher.getName()+"\n"+hours;
    }

    public String stringForScheduler(){
        return subject.getName_subject()+"\n"+teacher.getName()+"\n"+hours;
    }
}
