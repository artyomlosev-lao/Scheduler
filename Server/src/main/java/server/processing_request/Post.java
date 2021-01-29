package server.processing_request;

import server.outputdata.Exel;
import server.scheduler.*;

import server.database.DataManager;
import server.database.tables.Login;
import server.database.tables.User;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.URL;
import java.util.Date;

import static server.database.DataManager.*;

/**Класс отвечающий за прием запроса POST, его обработку и отправку клиенту*/
public class Post {

    /**Принимает запрос, обрабатывает и отправляет на сервер
     * @param requested - строка с названием запроса
     * @param out - поток для отправки пакета с запросом
     * @param dataOut - поток для отправки данных
     * @param in - поток для считывания данных запроса
     * @exception IOException если произойдут ошибки в работе с потоками*/
    public static void send(String requested, PrintWriter out, BufferedOutputStream dataOut, BufferedReader in) throws IOException {

        System.out.println("Зашли в пост");

        // Массив для сбора запроса (буфер)
        byte[] fileData = "Запрос был неверным".getBytes();
        // Ответ запроса
        int numberAnswer = 200;

// ----------- ЗАПРОСЫ ------------------------
        switch (requested) {

            case "/count":{
                new inputdata("");
                // вызывает алгоритм
                //invokes algorithm
                new SchedulerMain();

                // захватываем последнюю хромосому, т.е. вывод
                //grabs final chromosome i.e. the output
                Chromosome finalson =SchedulerMain.finalson;

//                finalson.printTimeTable();
                finalson.printTimeTableExel();
                break;
            }
            // Добавления
            case "/addTeacher": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.addTeacher(in.readLine());

                break;
            }
            case "/addSubject": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.addSubject(in.readLine());

                break;
            }
            case "/addClass": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.addGroup(in.readLine());

                break;
            }

            case "/addLesson": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                String name_group = in.readLine();
                String name_teacher = in.readLine();
                String name_subject = in.readLine();
                String hours = in.readLine();

                String id = DataManager.addLesson(name_group, name_subject, name_teacher, hours);
                fileData = id.getBytes();
                break;
            }

            // Удаления
            case "/deleteTeacher": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.deleteTeacher(in.readLine());

                break;
            }
            case "/deleteSubject": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.deleteSubject(in.readLine());

                break;
            }
            case "/deleteClass": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.deleteGroup(in.readLine());

                break;
            }

            case "/deleteLesson": {
                System.out.println("Добавляем учителя");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                DataManager.deleteLesson(in.readLine());

                break;
            }

            // Обновление
            case "/updateLesson": {
                System.out.println("Добавляем урок");

                String line;
                // Пропускаем ненужную информацию
                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}

                String id = in.readLine();
                String name_group = in.readLine();
                String name_teacher = in.readLine();
                String name_subject = in.readLine();
                String hours = in.readLine();

                DataManager.updateLesson(id, name_group, name_subject, name_teacher, hours);

                break;
            }

            case "/load":{
                String group = DataManager.getAllGroup();
                String[] arrGroup = group.split("\n");
                int iter = Integer.parseInt(arrGroup[0]);

                String result = DataManager.getAllGroup()+DataManager.getAllTeacher()+DataManager.getAllSubjectr();

                for(int i = 0; i < iter; i++){
                    result+=DataManager.getLessonsByGroup(arrGroup[i+1]);
                }

                fileData = result.getBytes();

            }


//            case "/register":{
//                System.out.println("Начинаем регистрацию!");
//
//                Login login = new Login();
//                User user = new User();
//                String line;
//
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {System.out.println(line);}
//                login.setLogin(in.readLine());
//                login.setPassword(in.readLine());
//                user.setNickname(in.readLine());
//
//                login.setUser(user);
//
//                System.out.println("Получили следующий логин: "+login.toString());
//
//                if (isLoginCreated(login)) {
//                    System.out.println("Такой логин уже существует");
//                    fileData = "error".getBytes();
//                } else {
//                    addLogin(login);
//
//                    System.out.println("Логин добавлен");
//                    fileData = (""+DataManager.getIdUserByLoginId(login.getLogin())).getBytes();
//                }
//
//                break;}
//
//            case "/login": {
//                System.out.println("Начинаем вход!");
//
//                Login login = new Login();
//                String line;
//
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                login.setLogin(in.readLine());
//                login.setPassword(in.readLine());
//
//                System.out.println("Получили следующий логин: " + login.toString());
//
//                if (isLoginCorrect(login)) {
//                    System.out.println("Вход разрешен");
//                    fileData = ("" + DataManager.getIdUserByLoginId(login.getLogin())).getBytes();
//
//                } else {
//                    System.out.println("Вход запрещен");
//                    fileData = "error".getBytes();
//                }
//
//                break;
//            }
//
//            case "/allUser": {
//                System.out.println("Начинаем поиск всех людей!");
//
//                fileData = DataManager.getAllUser().getBytes();
//
//                break;
//            }
//
//            case "/getUserById": {
//
//                System.out.println("Необходимо получить пользователя по id");
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                line = in.readLine();
//                int id_user = Integer.parseInt(line);
//                fileData = DataManager.getUserById(id_user).toString().getBytes();
//
//                break;
//            }
//
//            case "/receiveDialog": {
//                System.out.println("Получаем номер диалога");
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String num_user1 = in.readLine();
//                String num_user2 = in.readLine();
//
//                fileData = receiveDialog(num_user1, num_user2).getBytes();
//                break;
//            }
//
//            case "/getListDialogs": {
//                System.out.println("Получаем список диалогов");
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_user = in.readLine();
//
//                fileData = getListDialogsForAnswer(id_user).getBytes();
//                break;
//            }
//
//            case "/receiveDialogById": {
//                System.out.println("Получаем диалог по его id");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_dialog = in.readLine();
//
//                fileData = getDialogById(id_dialog).getBytes();
//
//                break;
//            }
//
//            case "/getListMessage": {
//                System.out.println("Список сообщений");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_dialog = in.readLine();
//
//                fileData = getMessages(id_dialog).getBytes();
//                break;
//            }
//            case "/addMessage": {
//                System.out.println("Добавляем сообщение");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_dialog = in.readLine();
//                String id_user = in.readLine();
//                String value = in.readLine();
//
//                addMessage(id_dialog, id_user, value);
//                fileData = "сообщение добавлено, скорее всего".getBytes();
//                break;
//            }
//
//            case "/myFriend": {
//                System.out.println("Получить моих друзей");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_user = in.readLine();
//
//                fileData = getMyFriend(id_user).getBytes();
//                break;
//            }
//
//            case "/addFriend": {
//                System.out.println("Получить моих друзей");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_user = in.readLine();
//                String id_friend = in.readLine();
//                if(!isFriendCreated(id_user, id_friend)) {
//                    addFriend(id_user, id_friend);
//                    fileData="ok".getBytes();
//                }
//                else{
//                    fileData="error".getBytes();
//                }
//                break;
//            }
//            case "/updateUser": {
//                System.out.println("Обновить человека");
//
//                String line = null;
//                // Пропускаем ненужную информацию
//                while ((line = in.readLine()) != null && !(line.isEmpty())) {
//                    System.out.println(line);
//                }
//                String id_user = in.readLine();
//                String name = in.readLine();
//                updateUser(id_user, name);
//                fileData = "ok".getBytes();
//                break;
//            }
        }
        // Шлем HTTP Headers
        out.println("HTTP/1.1 200 OK");
        out.println("Server: Java HTTP Server : 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: text/plain");
        //out.println("Transfer-Encoding: chunked");
        out.println("Connection: keep-alive");
        out.println("Vary: Accept-Encoding");

        // Длина ответа - эхо запроса без первого /
        out.println("Content-length: " + fileData.length);
        out.println();
        //out.flush();

        dataOut.write(fileData, 0, fileData.length);

        System.out.println("Ответ отослан");
        dataOut.flush();
    }
}
