import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String num1 = "+7 909 123-45-67";
        String num2 = "+7 (909) 1234567";
        String num3 = "8-905-1234567";
        String num4 = "9-453-1234567";
        String num5 = "8-905-123";
        String num6 = "905-1234567";
        String num7 = "8-905-12345672342";
        System.out.println("num 1 " + clearNumber(num1));
        System.out.println("num 2 " + clearNumber(num2));
        System.out.println("num 3 " + clearNumber(num3));
        System.out.println("num 4 " + clearNumber(num4));
        System.out.println("num 5 " + clearNumber(num5));
        System.out.println("num 6 " + clearNumber(num6));
        System.out.println("num 7 " + clearNumber(num7));

    }

    static String clearNumber(String num) {
        String a = num.replaceAll("[^0-9]", "");
        Pattern p1 = Pattern.compile("[7,8]9[\\d{9}]");
        Pattern p2 = Pattern.compile("9[\\d{9}]");
        Matcher m1 = p1.matcher(a);
        Matcher m2 = p2.matcher(a);
        if (a.length() < 10 || a.length() > 11) {
            return "Не верный формат номера";
        } else if (a.length() == 11)
        { // номер с 7,8
            if (m1.find())
            { // вторая цифра 9
                a = a.substring(1);
            } else
                {
                return "Не верный формат номера"; //вторая цифра не 9
                }

        } else
            {
                if(!m2.find()){
                    return "Не верный формат номера";// 10 цифр, но начало не с 9-и
                }
            }

        return "7"+a;
    }
}
