import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<String> autoNumbers1 = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            autoNumbers1.add(numberMaker());
        }
        TreeSet<String> autoNumbers2 = new TreeSet<>();
        autoNumbers2.addAll(autoNumbers1);
        HashSet<String> autoNumbers3 = new HashSet<>();
        autoNumbers3.addAll(autoNumbers1);
        Collections.sort(autoNumbers1);
        for (; ; ) {
            String num = scn.nextLine();
            //0
            long start = System.nanoTime();
            boolean pos = autoNumbers1.contains(num);
            long duration = System.nanoTime() - start;
            //1
            long start1 = System.nanoTime();
            int pos1 = Collections.binarySearch(autoNumbers1, num);
            long duration1 = System.nanoTime() - start1;
            //2
            long start2 = System.nanoTime();
            boolean pos2 = autoNumbers2.contains(num);
            long duration2 = System.nanoTime() - start2;
            //3
            long start3 = System.nanoTime();
            boolean pos3 = autoNumbers3.contains(num);
            long duration3 = System.nanoTime() - start3;
            System.out.println("Поиск перебором: " + (pos ? "номер найден" : "не найден") + ", поиск занял " + duration + "нс");
            System.out.println("Бинарный поиск: " + ((pos1 >= 0) ? "номер найден" : "не найден") + ", поиск занял " + duration1 + "нс");
            System.out.println("Поиск в TreeSet:" + (pos2 ? "номер найден" : "не найден") + ", поиск занял " + duration2 + "нс");
            System.out.println("Поиск в HashSet: " + (pos3 ? "номер найден" : "не найден") + ", поиск занял " + duration3 + "нс");
            //System.out.println("пример - " + autoNumbers1.get((int)(Math.random()*2000000)));

        }

    }
    public static String numberMaker() {
        StringBuilder b = new StringBuilder();
        b.append(randomChar());
        int a = (int) (Math.random() * 9);
        for (int i = 0; i < 3; i++) {
            b.append(a);
        }
        b.append(randomChar());
        b.append(randomChar());
        b.append(1 + (int) (Math.random() * 197));
        return b.toString();
    }
    public static char randomChar() {
        char[] ch = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};
        char c = ch[((int) (Math.random() * 12))];
        return c;
    }
}
