public class Main {
    public static void main(String[] args) {
        String[] rainbow = {"Каждый", "охотник", "желает", "знать", "где", "сидит", "фазан"};
        for (int i = 0; i <= rainbow.length / 2; i++) {
            String a = rainbow[i];
            rainbow[i] = rainbow[rainbow.length - 1 - i];
            rainbow[rainbow.length - 1 - i] = a;
        }
        for (String b : rainbow) {
            System.out.println(b);
        }
    }
}
