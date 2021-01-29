package server.database.tables;

import javax.persistence.*;
import java.util.List;

@Entity
public class SchoolClass {

    public SchoolClass(){};
    public SchoolClass(String name_group){
        this.name_group = name_group;
    }

    @Id
    private String name_group;

    public String getName_group() { return name_group;}
    public void setName_group(String name_group) { this.name_group = name_group;}

//    @OneToMany(cascade=CascadeType.REMOVE)
//    private List<Lesson> lessons;
//
//    public List<Lesson> getLessons() { return lessons;}
//    public void setLessons(List<Lesson> lessons) { this.lessons = lessons;}

    @Override
    public String toString() {
        return getName_group();
    }
}
