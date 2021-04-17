
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox.ru";
        Node root = new Node(url);
        File file = new File("file.txt");
        Set<String> sum = new ForkJoinPool().invoke(new ParseNode(root));
        sum.forEach(System.out::println);
        try (FileWriter fl = new FileWriter(file, true)) {
            sum.forEach(s -> {
                try {
                    fl.write(s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

