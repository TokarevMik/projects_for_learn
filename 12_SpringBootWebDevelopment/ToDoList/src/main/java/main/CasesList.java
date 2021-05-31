package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasesList {
    private static int currentId = 1;
    static private Map<Integer, Case> toDoList = new HashMap<Integer, Case>();

    static public synchronized void toDoListUpdater(int id, Case c) {
        c.setId(id);
        toDoList.put(id, c);
    }

    static boolean containCase(int id) {
        if (toDoList.containsKey(id)) {
            return true;
        } else return false;
    }

    static public synchronized int addCase(Case c) {
        int id = currentId++;
        c.setId(id);
        toDoList.put(id, c);
        return id;
    }

    static public synchronized List<Case> getToDoList() {
        List<Case> allCases = new ArrayList<>();
        allCases.addAll(toDoList.values());
        return allCases;
    }

    static synchronized void removeAllCases() {
        toDoList.clear();
    }

    static synchronized void removeCase(int id) {
        toDoList.remove(id);
    }
}
