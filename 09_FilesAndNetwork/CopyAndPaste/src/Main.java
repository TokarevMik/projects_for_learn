import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (; ; ) {
            try {
                System.out.println("Введите путь копируемой папки ");
                Scanner scn = new Scanner(System.in);
                String firstDir = scn.nextLine();
                Path path1 = Paths.get(firstDir);
                if (!Files.isDirectory(path1) && !Files.exists(path1)) {
                    System.out.println("Не верный адрес");
                } else {
                    for (; ; ) {
                        System.out.println("Введите путь папки назначения ");
                        scn = new Scanner(System.in);
                        Path path2 = Paths.get(scn.nextLine());
                        if (!Files.isDirectory(path2) && !Files.exists(path2)) {
                            System.out.println("Не верный адрес");
                        } else {
                            Files.walkFileTree(path1, new MyFileVisitor(path1, path2));
                            break;
                        }
                    }
                }
            } catch (AccessDeniedException e) {
                System.out.println("Нет доступа 1");
                e.printStackTrace();
            } catch (Exception ex) {
                System.out.println("Нет доступа 2");
                ex.printStackTrace();
            }
        }
    }
}

class MyFileVisitor extends SimpleFileVisitor<Path> {
    Path src;
    Path dest;

    public MyFileVisitor(Path src, Path dest) {
        this.dest = dest;
        this.src = src;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
        Path newPath = dest.resolve(src.relativize(path));
        try {
            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
             e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        Path newPath = dest.resolve(src.relativize(path));
        try {
            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

}



