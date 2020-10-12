import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static final long GIGA_BYTES = 1024 * 1024 * 1024;

    public static void main(String[] args) {
        for (; ; ) {
            try {
                long sizeOfFiles = 0;
                System.out.println("Введите путь ");
                Scanner scn = new Scanner(System.in);
                String path = scn.nextLine();
                File file = new File(path);
                if (file.isDirectory()) {
                    sizeOfFiles = sizeCounter(path);
                } else {
                    System.out.println("Не верный адрес");
                }
                System.out.println("Размер папки " + path + " cоставляет " + String.format("%.3f", (sizeOfFiles)) + " гб");

            } catch (Exception ex) {
                System.out.println(ex.fillInStackTrace());
            }
        }
    }


    public static long sizeCounter(String name) throws IOException {
        long size = Files.walk(Paths.get(name)).map(Path::toFile)
                .filter(file -> file.isFile())
                .mapToLong(File::length)
                .sum();
        size = size / GIGA_BYTES;
        return size;
    }

}
