import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
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
                System.out.println("Размер папки " + path + " cоставляет " + FileSizeFormat(sizeOfFiles));

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
        return size;
    }
    public static String FileSizeFormat(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[] {"Б", "КБ", "МБ", "ГБ", "ТБ"};
        int digitGroups = (int)(Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

}
