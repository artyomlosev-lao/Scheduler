package server.database;

import server.database.tables.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

public class DataManager {

    // Добавление записей в БД

   public static void addTeacher(String name){
       BDManager bd = BDManager.getInstance();
       Teacher teacher = new Teacher(name);
       bd.addTeacher(teacher);
   }

   public static void addSubject(String name_subject){
       BDManager bd = BDManager.getInstance();
       Subject subject = new Subject(name_subject);
       bd.addSubject(subject);
   }

   public static void addGroup(String name_group){
       BDManager bd = BDManager.getInstance();
       SchoolClass schoolClass = new SchoolClass(name_group);
       bd.addGroup(schoolClass);
   }

   public static String addLesson(String name_group, String name_subject, String name_teacher, String hours){
       BDManager bd = BDManager.getInstance();
       int hour = Integer.parseInt(hours);
       Lesson lesson = new Lesson(
                                    new SchoolClass(name_group),
                                    new Subject(name_subject),
                                    new Teacher(name_teacher),
                                    hour
       );
       Serializable id = bd.addLesson(lesson);
       return id.toString();
   }

    public static void updateLesson(String id, String name_group, String name_subject, String name_teacher, String hours){
        BDManager bd = BDManager.getInstance();
        int hour = Integer.parseInt(hours);
        int id_les = Integer.parseInt(id);

        Lesson lesson = new Lesson(
                id_les,
                new SchoolClass(name_group),
                new Subject(name_subject),
                new Teacher(name_teacher),
                hour
        );

        bd.updateLesson(lesson);
    }

   // Удаление записей из БД
   public static void deleteTeacher(String name_teacher){
       BDManager bd = BDManager.getInstance();
       Teacher teacher = new Teacher(name_teacher);
       bd.deleteTeacher(teacher);
   }

    public static void deleteSubject(String name_subject){
        BDManager bd = BDManager.getInstance();
        Subject subject = new Subject(name_subject);
        bd.deleteSubject(subject);
    }

    public static void deleteGroup(String name_group){
        BDManager bd = BDManager.getInstance();
        SchoolClass schoolClass = new SchoolClass(name_group);
        bd.deleteGroup(schoolClass);
    }

    public static void deleteLesson(String id){
       BDManager bd = BDManager.getInstance();
       Lesson lesson = new Lesson();
       lesson.setId(Integer.parseInt(id));
       bd.deleteLesson(lesson);
    }


    // Получить какую-то инфу из БД

    public static String getAllGroup(){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<SchoolClass> schoolClasses = bd.getAllGroup();
       result += schoolClasses.size()+"\n";
       for(SchoolClass schoolClass : schoolClasses){
           result += schoolClass.toString()+"\n";
       }
       return result;
    }

    public static String getAllTeacher(){
        String result = "";

        BDManager bd = BDManager.getInstance();
        List<Teacher> teachers = bd.getAllTeacher();
        result += teachers.size()+"\n";
        for(Teacher teacher: teachers){
            result += teacher.toString()+"\n";
        }
        return result;
    }

    public static String getAllSubjectr(){
        String result = "";

        BDManager bd = BDManager.getInstance();
        List<Subject> subjects = bd.getAllSubject();
        result += subjects.size()+"\n";
        for(Subject subject: subjects){
            result += subject.toString()+"\n";
        }
        return result;
    }

    public static String getAllLessons(){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<Lesson> lessons = bd.getAllLesson();
       result += lessons.size()+"\n";
       for(Lesson lesson: lessons){
           result += lesson.toString()+"\n";
       }
       return result;
    }

    public static String getLessonsByGroup(String name_group){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<Lesson> lessons = bd.getLessonsByGroup(name_group);
       result += lessons.size()+"\n";
       for(Lesson lesson: lessons){
           result += lesson.toString()+"\n";
       }
       return result;
    }

    public static String getLessonsByGroupSched(String name_group){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<Lesson> lessons = bd.getLessonsByGroup(name_group);
       result += lessons.size()+"\n";
       for(Lesson lesson: lessons){
           result += lesson.stringForScheduler()+"\n";
       }
       return result;
    }

    public static String getLessonsByTeacher(String name_teacher){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<Lesson> lessons = bd.getLessonsByTeacher(name_teacher);
        result += lessons.size()+"\n";
        for(Lesson lesson: lessons){
            result += lesson.toString()+"\n";
        }
        return result;
    }

    public static String getLessonsBySubject(String name_subject){
       String result = "";

       BDManager bd = BDManager.getInstance();
       List<Lesson> lessons = bd.getLessonsBySubject(name_subject);
       result += lessons.size()+"\n";
       for(Lesson lesson: lessons){
           result += lesson.toString()+"\n";
       }
       return result;
    }
   /*
    public static boolean isLoginCreated(Login login){
        BDManager bd = BDManager.getInstance();
        return bd.isLogin(login) != 0;
    }

    public static boolean isLoginCorrect(Login login){
        BDManager bd = BDManager.getInstance();
        return bd.isLogin(login) == 1;
    }

    public static boolean isDialogCreated(String user1, String user2){
        List<Dialogs> dialogs_user = getListDialogs(user1);
        List<Dialogs> dialogs_user2 = getListDialogs(user2);

        for(Dialogs dialog1 : dialogs_user){
            for(Dialogs dialog2 : dialogs_user2){
                if(dialog1.getDialog()==dialog2.getDialog()){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFriendCreated(String id_user1, String id_user2){
        User user = getUserById(Integer.parseInt(id_user1));
        List<Friends> friends = user.getFriends();

        for(Friends friend : friends){
            if((friend.getId_friend()+"").equals(id_user2)) {
                return true;
            }
        }
        return false;
    }

    // --------- Добавления ---------------------

    public static void addLogin(Login login){
        BDManager bd = BDManager.getInstance();
        bd.addLogin(login);
    }

    public static void addFriend(String my_id_str, String friend_id_str){
        int my_id = Integer.parseInt(my_id_str);
        int friend_id = Integer.parseInt(friend_id_str);

        User user = new User();
        user.setId_user(my_id);
        Friends friend = new Friends(user, friend_id);

        BDManager bd = BDManager.getInstance();
        bd.addFriend(friend);
    }

    public static String receiveDialog(String user1, String user2){

        if(isDialogCreated(user1, user2)){
            return getDialog(user1, user2);
        }
        else{
            int id_user1 = Integer.parseInt(user1);
            int id_user2 = Integer.parseInt(user2);

            BDManager bd = BDManager.getInstance();
            User body_user1 = bd.getUserById(id_user1);
            User body_user2 = bd.getUserById(id_user2);
            List<User> list_user = new ArrayList<>();
            list_user.add(body_user1);
            list_user.add(body_user2);
            Dialogs dialog = new Dialogs(list_user);
            bd.addDialog(dialog);
            return getDialog(user1, user2);
        }
    }

    public static void addMessage(String id_dialog_str, String id_sender_str, String value){
        BDManager bd = BDManager.getInstance();

        int id_dialog = Integer.parseInt(id_dialog_str);
        int sender_id = Integer.parseInt(id_sender_str);

        Dialogs dialog = bd.getDialogById(id_dialog);

        Message message = new Message(dialog, sender_id, value);
        bd.addMessage(message);
    }
    // ---------- Получатели --------------------

    public static int getIdUserByLoginId(String loginId){
        BDManager bd = BDManager.getInstance();
        return bd.getUserByLoginId(loginId).getId_user();
    }

    public static User getUserById(int user_id){
        BDManager bd = BDManager.getInstance();
        return bd.getUserById(user_id);
    }

    public static List<Dialogs> getListDialogs(String id_user_str){
        int id_user = Integer.parseInt(id_user_str);
        User user = getUserById(id_user);

        return user.getDialogs();
    }

    public static String getListDialogsForAnswer(String id_user_str){
        int id_user = Integer.parseInt(id_user_str);
        User user = getUserById(id_user);
        List<Dialogs> dialogs = user.getDialogs();

        String answer = dialogs.size()+"\n";
        for(Dialogs dialog: dialogs){
            answer += dialog.getDialog()+"\n";
            List<User> list_user = dialog.getUsers();
            if(list_user.get(0).getId_user()==id_user){
                answer += list_user.get(1).getId_user()+"\n";
                answer += list_user.get(1).getNickname()+"\n";
            }
            else{
                answer += list_user.get(0).getId_user()+"\n";
                answer += list_user.get(0).getNickname()+"\n";
            }
        }
        return answer;
    }

    public static String getDialog(String id_user_str, String id_user2_str){
        List<Dialogs> dialogs_user = getListDialogs(id_user_str);
        List<Dialogs> dialogs_user2 = getListDialogs(id_user2_str);

        for(Dialogs dialog1 : dialogs_user){
            for(Dialogs dialog2 : dialogs_user2){
                if(dialog1.getDialog()==dialog2.getDialog()){
                    return dialog1.getDialog()+"";
                }
            }
        }
        return "error";
    }

    public static String getDialogById(String id_dialog_str){
        int id_dialog = Integer.parseInt(id_dialog_str);

        BDManager bd = BDManager.getInstance();
        Dialogs dialog = bd.getDialogById(id_dialog);
        return dialog.getDialog()+"";
    }

    public static String getAllUser(){
        BDManager bd = BDManager.getInstance();
        List<User> allUser = bd.getAllUser();

        String answer = allUser.size()+"\n";
        for(User user:allUser){
            answer += user.toString()+"\n";
        }
        return answer;
    }

    public static String getMyFriend(String my_id_str){
        int my_id = Integer.parseInt(my_id_str);
        BDManager bd = BDManager.getInstance();
        User my_user = bd.getUserById(my_id);
        List<Friends> friends = my_user.getFriends();
        String answer = friends.size()+"\n";
        for(Friends friend: friends){
            User my_friend = getUserById(friend.getId_friend());
            answer += my_friend.toString()+"\n";
        }
        return answer;
    }

    public static String getMessages(String id_dialog_str){
        int id_dialog = Integer.parseInt(id_dialog_str);
        BDManager bd = BDManager.getInstance();
        Dialogs dialog = bd.getDialogById(id_dialog);
        List<Message> messages = dialog.getMessages();

        String answer = messages.size()+"\n";
        for(Message message : messages){
            answer += message.toString()+"\n";
        }
        return answer;
    }

    public static void updateUser(String id_user_str, String name){
        int id_user = Integer.parseInt(id_user_str);
        BDManager bd = BDManager.getInstance();
        User user = bd.getUserById(id_user);
        user.setNickname(name);
        bd.updateUser(user);
    }
    */
}
