public class Main {
    public static void main(String[] args) {
        String alpLat = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alpRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        printAbc(alpLat);
        printAbc(alpRus);

    }
    public static void printAbc (String alph) {
        for (int i = 0; i < alph.length(); i++) {
            char a = alph.charAt(i);
            System.out.println(a + " - " + (int) a);
        }
    }
}

