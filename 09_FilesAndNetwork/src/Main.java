import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final long GIGA_BYTES = 1024 * 1024 * 1024;

    public static void main(String[] args) {
        ArrayList<File> allFiles = new ArrayList<>();

        for (; ; ) {
            try {
                System.out.println("Введите путь ");
                Scanner scn = new Scanner(System.in);
                String path = scn.nextLine();
                File file = new File(path);
                if (file.isDirectory()) {
                    allFiles = ListBuilder(file, allFiles);
                } else {
                    System.out.println("Не верный адрес");
                }
                double sizeOfFiles = 0;
                for (File f : allFiles) {
                    sizeOfFiles = sizeOfFiles + f.length();
                }

                sizeOfFiles = sizeOfFiles / GIGA_BYTES;
                System.out.println("Размер папки " + path + " cоставляет " + String.format("%.2f", (sizeOfFiles)) + " гб");

            } catch (Exception ex) {
                System.out.println(ex.fillInStackTrace());
            }
        }
    }


    public static ArrayList<File> ListBuilder(File a, ArrayList<File> files) {
        for (File b : a.listFiles()) {
            if (b.isFile()) {
                files.add(b);
            } else if (b.isDirectory()) {
                ListBuilder(b, files);
            }
        }
        return files;
    }
}
