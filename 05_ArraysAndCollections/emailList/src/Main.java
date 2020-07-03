import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("[A][D][D]\\s[A-Za-z0-9._%+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
        TreeSet<String> eMail = new TreeSet<>();
        Scanner scn = new Scanner(System.in);
        String mailName;
        while (true) {
            System.out.println("Введите email: ");
            mailName = scn.nextLine();
            Matcher m = p.matcher(mailName);
            if (mailName.equals("LIST")) {
                for (String a : eMail) {
                    System.out.println(a);
                }
            } else if (m.find()) {
                String[] mailCommand = mailName.split("\\s");
                if (eMail.contains(mailCommand[1])) {
                    System.out.println("Данный адрес уже в списке");
                } else {
                    eMail.add(mailCommand[1]);
                }
            } else {
                System.out.println("Не верный запрос");
            }

        }
    }
}
