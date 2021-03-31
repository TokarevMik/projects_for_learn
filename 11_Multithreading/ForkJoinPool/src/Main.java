import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox.ru";

        Node root = new Node(url);
        List<String> sum = new ForkJoinPool().invoke(new ParseNode(root));
        for (String a:sum){
            System.out.println(a);
        }
    }
}