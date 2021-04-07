import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    String url;

    public Node(String url) {
        this.url = url;
    }

    private Collection<Node> nodes = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public Collection<Node> getChildren() {
        try {
            Thread.sleep(200);
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element content = doc.body();
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                Pattern pattern = Pattern.compile("^/[\\w,-,_]+/$");
                Pattern pattern2 = Pattern.compile("https://skillbox.ru/[\\w,\\D]+/$");
                Matcher matcher = pattern.matcher(linkHref);
                Matcher matcher2 = pattern2.matcher(linkHref);
                if (matcher2.matches()) {
                    nodes.add(new Node(linkHref));
                }
                if (matcher.matches()) {
                    if (url.endsWith("/")) {
                        linkHref = linkHref.substring(1);
                    }
                    linkHref = url.concat(linkHref);
                    nodes.add(new Node(linkHref));
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }
}
