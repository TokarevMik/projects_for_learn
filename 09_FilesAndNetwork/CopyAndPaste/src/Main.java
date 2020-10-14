import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //for (; ; ) {
            try {
                System.out.println("Введите путь копируемой папки ");
                Scanner scn = new Scanner(System.in);
                String firstDir = scn.nextLine();
                Path path1 = Paths.get(firstDir);
//                if (!Files.isDirectory(path1) && !Files.exists(path1)) {
//                    System.out.println("Не верный адрес");
//                } else {
//                    for (; ; ) {
                        System.out.println("Введите путь папки назначения ");
                        scn = new Scanner(System.in);
                        Path path2 = Paths.get(scn.nextLine());
//                        if (!Files.isDirectory(path2) && !Files.exists(path2)) {
//                            System.out.println("Не верный адрес");
//                        } else {
                            Files.walkFileTree(path1, new MyFileVisitor(path1, path2));
//                        }
//                    }
//                }
            }
            catch (AccessDeniedException e){
                System.out.println("Нет доступа 1"  );
                e.printStackTrace();
            }
            catch (Exception ex) {
                System.out.println("Нет доступа 2");
                ex.printStackTrace();
            }
       // }
    }
}

class MyFileVisitor implements FileVisitor<Path> {
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
        } catch (Exception e) {
            System.out.println("113");
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        Path newPath = dest.resolve(src.relativize(path));
        try {
            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("112");
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) /*throws IOException */{
        try {
            System.out.println("Нет доступа к файлу");
        } catch (Exception e) {
            System.out.println("111");
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return null;
    }


}