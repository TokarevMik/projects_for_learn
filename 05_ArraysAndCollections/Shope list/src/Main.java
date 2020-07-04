import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, Long> telephoneBook = new TreeMap<>();
        Scanner scn = new Scanner(System.in);
        while (true) {
            String key;
            Long value;
            System.out.println("Введите контакт: ");
            String wordInt = scn.nextLine();
            if ("LIST".equals(wordInt)) {
                for (Map.Entry<String, Long> entry : telephoneBook.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else if (isNumber(wordInt)) {
                value = Long.parseLong(wordInt);
                if (!telephoneBook.containsValue(value)) {
                    boolean named = false;
                    while (!named) {
                        System.out.println("Введите имя ");
                        key = scn.nextLine();
                        if (isName(key)) {
                            telephoneBook.put(key, value);
                            named = true;
                        } else {
                            System.out.println("He верный запрос");
                        }
                    }
                } else {
                    for (Map.Entry<String, Long> entry : telephoneBook.entrySet()) {
                        if(entry.getValue().equals(value)){
                            System.out.println(entry.getKey() + " - " + entry.getValue());
                        };
                    }
                };
            } else if (isName(wordInt)) {
                key = wordInt;
                if (!telephoneBook.containsKey(key)){
                    boolean numbered=false;
                    while (!numbered){
                        System.out.println("Введите номера телефона ");
                        wordInt = scn.nextLine();
                        if (isNumber(wordInt)){
                            value = Long.parseLong(wordInt);
                            telephoneBook.put(key,value);
                            numbered = true;
                        } else {
                            System.out.println("He верный запрос");
                        }
                    }
                } else {
                    System.out.println(key + " " + telephoneBook.get(key));
                }
            } else {
                System.out.println("Неверный запрос");
            }
        }

    }

    public static boolean isName(String a) {
        boolean wr = false;
        Pattern pWord = Pattern.compile("[A-Za-zА-яа-я\\-\\s]+");
        Matcher mWord = pWord.matcher(a);
        if (mWord.find()) {
            wr = true;
        }
        return wr;
    }

    public static boolean isNumber(String a) {
        boolean num = false;
        Pattern pNum = Pattern.compile("9[\\d{9}]");
        Matcher mWord = pNum.matcher(a);
        if (mWord.find()) {
            num = true;
        }
        return num;
    }
}
