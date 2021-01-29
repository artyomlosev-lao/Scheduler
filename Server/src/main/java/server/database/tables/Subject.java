package server.database.tables;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject {

    public Subject(){};
    public Subject(String name_subject){
        this.name_subject = name_subject;
    }

    @Id
    private String name_subject;

    public String getName_subject() { return name_subject;}
    public void setName_subject(String name_lesson) { this.name_subject = name_lesson;}

//    @OneToMany(cascade=CascadeType.REMOVE)
//    private List<Lesson> lessons;
//
//    public List<Lesson> getLessons() { return lessons;}
//    public void setLessons(List<Lesson> lessons) { this.lessons = lessons;}

    @Override
    public String toString() {
        return getName_subject();
    }
}

