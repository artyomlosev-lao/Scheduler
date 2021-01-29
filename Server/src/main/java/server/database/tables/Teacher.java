package server.database.tables;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    public Teacher(){};
    public Teacher(String name){
        this.name = name;
    }

    @Id
    private String name;

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

//    @OneToMany(cascade=CascadeType.REMOVE)
//    private List<Lesson> lessons;
//
//    public List<Lesson> getLessons() { return lessons;}
//    public void setLessons(List<Lesson> lessons) { this.lessons = lessons;}

    @Override
    public String toString() {
        return getName();
    }
}
