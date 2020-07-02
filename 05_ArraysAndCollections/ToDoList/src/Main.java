import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern pAdd1 = Pattern.compile("[A][D][D]\\s[\\d]+\\s[А-Яа-яA-Za-z\\s]+");
        Pattern pAdd2 = Pattern.compile("[A][D][D]\\s[А-Яа-яA-Za-z\\s]+");
        Pattern pEdit = Pattern.compile("EDIT\\s[\\d]+\\s[А-Яа-яA-Za-z\\s]+");
        Pattern pDelite = Pattern.compile("DELITE\\s[\\d]+");
        //Matcher mAdd1 = pAdd1.matcher();
        ArrayList<String> toDoList = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        String todo;
        while (true) {
            System.out.println("Введите команду");
            todo = scn.nextLine();
            Matcher mAdd1 = pAdd1.matcher(todo);
            Matcher mAdd2 = pAdd2.matcher(todo);
            Matcher mEdit = pEdit.matcher(todo);
            Matcher mDelite = pDelite.matcher(todo);
            if (todo.equals("LIST")) {
                for (String list : toDoList) {
                    System.out.println(list);
                }
            } else if (mAdd1.find()) {
                String[] toDoArray = todo.split("\\s", 3);
                int num = Integer.parseInt(toDoArray[1]);
                if (num >= toDoList.size() + 1) {
                    System.out.println("Неверный запрос");
                    continue;
                }
                String doIt = toDoArray[2];
                toDoList.add(num, doIt);
            } else if (mAdd2.find()) {
                String[] toDoArray = todo.split("\\s", 2);
                String doIt = toDoArray[1];
                toDoList.add(doIt);
            } else if (mEdit.find()) {
                String[] toDoArray = todo.split("\\s", 3);
                int num = Integer.parseInt(toDoArray[1]);
                if (num >= toDoList.size() + 1) {
                    System.out.println("Неверный запрос");
                    continue;
                }
                String doIt = toDoArray[2];
                toDoList.set(num, doIt);
            } else if (mDelite.find()) {
                String[] toDoArray = todo.split("\\s", 2);
                int num = Integer.parseInt(toDoArray[1]);
                if (num >= toDoList.size() + 1) {
                    System.out.println("Неверный запрос");
                    continue;
                }
                toDoList.remove(num);
            } else {
                System.out.println("Не верный формат запроса.");
            }
        }
    }
}

