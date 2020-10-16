import java.io.File;
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
                String sourceFile = scn.nextLine();
                Path path1 = Paths.get(sourceFile);
                if (!Files.isDirectory(path1) && !Files.exists(path1)) {
                    System.out.println("Не верный адрес");
                } else {
                    for (; ; ) {
                        System.out.println("Введите путь папки назначения ");
                        String destFile = scn.nextLine();
                        Path path2 = Paths.get(destFile);
                        if (!Files.isDirectory(path2) && !Files.exists(path2)) {
                            System.out.println("Не верный адрес");
                        } else {
                            //Files.walkFileTree(path1, new MyFileVisitor(path1, path2));
                            copyFolder(sourceFile, destFile);
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Нет доступа 2");
                ex.printStackTrace();
            }
        }
         }
    private static void copyFolder (String sourceFile, String destFile) {
        File src = new File(sourceFile);
        for (File file : src.listFiles()) {
            String sourceFilePath = sourceFile + "/" + file.getName();
            String destFilePath = destFile + "/" + file.getName();
            if (file.isDirectory()) {
                new File(destFilePath).mkdir();
                copyFolder(sourceFilePath, destFilePath);
            } else {
                try {
                    Files.copy(Paths.get(sourceFilePath), Paths.get(destFilePath), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//class MyFileVisitor extends SimpleFileVisitor<Path> {
//    Path src;
//    Path dest;
//
//    public MyFileVisitor(Path src, Path dest) {
//        this.dest = dest;
//        this.src = src;
//    }

//    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
//        Path newPath = dest.resolve(src.relativize(path));
//        try {
//            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return FileVisitResult.CONTINUE;
//    }
//
//    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
//        Path newPath = dest.resolve(src.relativize(path));
//        try {
//            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return FileVisitResult.CONTINUE;
//    }
//
//}



