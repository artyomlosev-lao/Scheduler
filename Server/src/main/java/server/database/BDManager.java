package server.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import server.database.tables.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import java.util.stream.*;

public class BDManager {
    private static BDManager instance;

    public static BDManager getInstance() {
        if (instance == null) {
            instance = new BDManager();
        }
        return instance;
    }

    /* Учителя */
    public void addTeacher(Teacher teacher){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.saveOrUpdate(teacher);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(Teacher teacher){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            deleteLessonsByTeacher(teacher);
            Teacher t = session.load(Teacher.class, teacher.getName());
            session.delete(t);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeacher(){
        CriteriaBuilder builder = BDConfiguration.getInstance().getSession().getCriteriaBuilder();
        CriteriaQuery<Teacher> cq = builder.createQuery(Teacher.class);
        Root<Teacher> rootEntry = cq.from(Teacher.class);
        CriteriaQuery<Teacher> all = cq.select(rootEntry);

        TypedQuery<Teacher> allQuery = BDConfiguration.getInstance().getSession().createQuery(all);
        return allQuery.getResultList();
    }

    /* Уроки */
    public Serializable addLesson(Lesson lesson){
        Serializable result = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            result = session.save(lesson);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    public  void deleteLesson(Lesson lesson){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            Lesson les = session.load(Lesson.class, lesson.getId());
            session.delete(les);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public void updateLesson(Lesson lesson){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.update(lesson);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLesson(){
        CriteriaBuilder builder = BDConfiguration.getInstance().getSession().getCriteriaBuilder();
        CriteriaQuery<Lesson> cq = builder.createQuery(Lesson.class);
        Root<Lesson> rootEntry = cq.from(Lesson.class);
        CriteriaQuery<Lesson> all = cq.select(rootEntry);

        TypedQuery<Lesson> allQuery = BDConfiguration.getInstance().getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public List<Lesson> getLessonsByGroup(final String name_group){
        List<Lesson> result = getAllLesson();
        result = result.stream().filter((Lesson les)->les.getSchoolClass().getName_group().equals(name_group)).collect(Collectors.toList());
        return result;
    }

    private void deleteLessonsByGroup(SchoolClass schoolClass){
        Session session = BDConfiguration.getInstance().getSession();
        List<Lesson> lessons = getLessonsByGroup(schoolClass.getName_group());
        for(Lesson lesson: lessons) {
            session.delete(lesson);
        }
    }

    public List<Lesson> getLessonsBySubject(final String name_subject){
        List<Lesson> result = getAllLesson();
        result = result.stream().filter((Lesson les)->les.getSubject().getName_subject().equals(name_subject)).collect(Collectors.toList());
        return result;
    }

    private void deleteLessonsBySubject(Subject subject){
        Session session = BDConfiguration.getInstance().getSession();
        List<Lesson> lessons = getLessonsBySubject(subject.getName_subject());
        for(Lesson lesson: lessons) {
            deleteLesson(lesson);
        }
    }


    public List<Lesson> getLessonsByTeacher(final String name_teacher){
        List<Lesson> result = getAllLesson();
        result = result.stream().filter((Lesson les)->les.getTeacher().getName().equals(name_teacher)).collect(Collectors.toList());
        return result;
    }

    private void deleteLessonsByTeacher(Teacher teacher){
        Session session = BDConfiguration.getInstance().getSession();
        List<Lesson> lessons = getLessonsByTeacher(teacher.getName());
        for(Lesson lesson: lessons) {
            deleteLesson(lesson);
        }
    }

    /* Предметы */
    public void addSubject(Subject subject){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.saveOrUpdate(subject);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubject(Subject subject){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            deleteLessonsBySubject(subject);
            Subject sub = session.load(Subject.class, subject.getName_subject());
            session.delete(sub);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public List<Subject> getAllSubject(){
        CriteriaBuilder builder = BDConfiguration.getInstance().getSession().getCriteriaBuilder();
        CriteriaQuery<Subject> cq = builder.createQuery(Subject.class);
        Root<Subject> rootEntry = cq.from(Subject.class);
        CriteriaQuery<Subject> all = cq.select(rootEntry);

        TypedQuery<Subject> allQuery = BDConfiguration.getInstance().getSession().createQuery(all);
        return allQuery.getResultList();
    }

    /* Классы */
    public void addGroup(SchoolClass schoolClass){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.saveOrUpdate(schoolClass);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(SchoolClass schoolClass){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            deleteLessonsByGroup(schoolClass);
            SchoolClass sc = session.load(SchoolClass.class, schoolClass.getName_group());
            session.remove(sc);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }


    }

    public List<SchoolClass> getAllGroup(){
        CriteriaBuilder builder = BDConfiguration.getInstance().getSession().getCriteriaBuilder();
        CriteriaQuery<SchoolClass> cq = builder.createQuery(SchoolClass.class);
        Root<SchoolClass> rootEntry = cq.from(SchoolClass.class);
        CriteriaQuery<SchoolClass> all = cq.select(rootEntry);

        TypedQuery<SchoolClass> allQuery = BDConfiguration.getInstance().getSession().createQuery(all);
        return allQuery.getResultList();
    }
    /*
    public void addLogin(Login login){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.save(login);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(Message message){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    /**0-несуществует, 1-вход разрешен, -1-вход запрещен*/
    /*
    public int isLogin(Login login){
        Login log = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            log = session.get(Login.class, login.getLogin());
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        if(log == null){
            return 0;
        }
        else if(log.getPassword().equals(login.getPassword())){
            return 1;
        }
        else return -1;
    }


    public Login getLogin(String loginId){
        Login login = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            login = session.load(Login.class, loginId);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return login;
    }

    public User getUserById(int id_user){
        User user = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            user = session.load(User.class, id_user);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByLoginId(String id_login){
        User user = null;
        Login login = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            login = session.load(Login.class, id_login);
            user = login.getUser();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addFriend(Friends friend){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.save(friend);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void addDialog(Dialogs dialog){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.save(dialog);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser(){
        CriteriaBuilder builder = BDConfiguration.getInstance().getSession().getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);

        TypedQuery<User> allQuery = BDConfiguration.getInstance().getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public Dialogs getDialogById(int id){
        Dialogs dialog = null;
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            dialog = session.load(Dialogs.class, id);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return dialog;

    }

    public void updateUser(User user){
        try {
            Session session = BDConfiguration.getInstance().getSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
*/

}
