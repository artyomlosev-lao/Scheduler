package server.testing;

import server.database.DataManager;

public class TestInput {
    public static void main(String[] args) {
        System.out.println(DataManager.getAllGroup());
        String[] group = DataManager.getAllGroup().split("\n");
        String result = "";
        for(int i=0; i<Integer.parseInt(group[0]); i++) {
            result += group[i+1]+"\n";
            result += DataManager.getLessonsByGroup(group[i+1]);
        }

        System.out.println(result);

    }
}
