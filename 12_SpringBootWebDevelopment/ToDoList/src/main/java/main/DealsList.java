package main;

import main.model.Deal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealsList {
    private static int currentId = 1;
    static private Map<Integer, Deal> toDoList = new HashMap<Integer, Deal>();

    static public synchronized void toDoListUpdater(int id, Deal c) {
        c.setId(id);
        toDoList.put(id, c);
    }

    static boolean containDeal(int id) {
        if (toDoList.containsKey(id)) {
            return true;
        } else return false;
    }

    static public synchronized int adDeal(Deal c) {
        int id = currentId++;
        c.setId(id);
        toDoList.put(id, c);
        return id;
    }

    static public Deal getDeal(int id) {
        Deal c;
        if (toDoList.containsKey(id)) {
            c = toDoList.get(id);
        } else {
            c = null;
        }
        return c;
    }

    static public synchronized List<Deal> getToDoList() {
        List<Deal> allDeals = new ArrayList<>();
        allDeals.addAll(toDoList.values());
        return allDeals;
    }

    static synchronized void removeAllDeals() {
        toDoList.clear();
    }

    static synchronized void removeDeal(int id) {
        toDoList.remove(id);
    }
}
