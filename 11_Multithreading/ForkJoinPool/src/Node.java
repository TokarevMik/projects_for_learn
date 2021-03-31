import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Node {
    String url;

    public Node(String url) {
        this.url = url;
    }
    public Node(String url, String name) {
        this.url = url;
        this.name = name;
    }
    public String name = "";

    private Collection<Node> nodes = new ArrayList<>();

    public Collection<Node> getChildren() {
        return nodes;
    }

    public void addChildren() {
        try {
            Thread.sleep(200);
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element content = doc.body();
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();
                nodes.add(new Node(linkHref,linkText));
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}

//            Elements content = doc.select("a:not([href$=#])");
//Elements sd = content.select("a[https://lenta]");
//           Elements sd = sk.select("a[[\\s\\/\\w&&[^#]]*$]");