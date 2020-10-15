import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException {
        Pattern p = Pattern.compile("\\\"[\\d]+,[\\d]+\\\"");
        String path = "src/movementList.csv";
        Map<String, Double> moneyCount = new HashMap<>();
        double allRefill = 0;
        double allWritOff = 0;
        try {
            List<String> money = Files.readAllLines(Paths.get(path));
            money.remove(0);
            for (String s : money) {
                String[] data = s.split("\\,", 8);
                double ref = 0;
                Matcher m1 = p.matcher(data[6]);

                if (m1.find()) {
                    ref = Double.parseDouble(writeOffCleaner(data[6]));
                } else {
                    ref = Double.parseDouble(data[6]);
                }

                double writeOff = 0;
                Matcher m2 = p.matcher(data[7]);

                if (m2.find()) {
                    writeOff = Double.parseDouble(writeOffCleaner(data[7]));
                } else {
                    writeOff = Double.parseDouble(data[7]);
                }
                if (writeOff != 0.0) {
                    allWritOff = allWritOff + writeOff;
                    String accOper = stringCleaner(data[5]);
                    if (moneyCount.get(accOper) == null) {
                        double sum = writeOff;
                        moneyCount.put(accOper, writeOff);
                    } else {
                        double sum = moneyCount.get(accOper) + writeOff;
                        moneyCount.put(accOper, sum);
                    }
                } else {
                    allRefill = allRefill + ref;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма расходов: " + allWritOff);
        System.out.println("Сумма доходов: " + allRefill);
        System.out.println("Расходы по пунктам - ");
        moneyCount.forEach((k, v) -> System.out.println("За: " + k + " списано: " + v));
    }

    public static String stringCleaner(String a) {//название операции
        String write = null;
        try {
            String[] arr = a.split("[\\d\\D]+28[\\s]*[\\d\\D]+[\\\\\\/]");
            String[] arr2 = arr[1].split("\\s\\s");
            write = arr2[0];

        } catch (Exception e) {
            System.out.println(a);
            e.printStackTrace();
        }
        return write;
    }

    public static String writeOffCleaner(String a) { //убираем кавычки и запятые
        String res;
        res = a.substring(1, (a.length() - 1));
        res = res.replace(',', '.');
        return res;
    }

}

