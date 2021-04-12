import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    String url;
    String domain = "https://skillbox.ru";
    int levelOfPage = 0;

    public int getLevelOfPage() {
        return levelOfPage;
    }

    public Node(String url) {
        this.url = url;
    }

    public Node(String url, int levelOfPage) {
        this.url = url;
        this.levelOfPage = levelOfPage;
    }

    private Collection<Node> nodes = new HashSet<>();

    public String getUrl() {
        return url;
    }

    public Collection<Node> getChildren() {
        try {
            Thread.sleep(200);
            Document doc = Jsoup.connect(url).timeout(0).userAgent("Mozilla").maxBodySize(0).get();
            Element content = doc.body();
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                Pattern pattern = Pattern.compile("https://skillbox.ru/[\\w,\\D]+/$");
                Pattern pattern2 = Pattern.compile("^/[\\w,-,_]+/$");
                Matcher matcher = pattern.matcher(linkHref);
                Matcher matcher2 = pattern2.matcher(linkHref);
                if (matcher.matches()) {
                        nodes.add(new Node(linkHref, takePageLevel(linkHref)));

                }
                if (matcher2.matches()) {
                    linkHref = domain.concat(linkHref);
                    try {
                        nodes.add(new Node(linkHref, takePageLevel(linkHref)));
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                        System.out.println(linkHref + " ********");
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }

/*    private String getChildAdress(String parent, String child) throws IndexOutOfBoundsException {
        int countCharParent = 0;
        int positionnChild = 0;
        for (int i = parent.length(); i < child.length(); i++) {
            if (child.charAt(i) == '/') {
                positionnChild = i;
                break;
            }
        }
        child = parent.concat(child.substring(parent.length(), positionnChild + 1));
        return child;
    }*/

    private int takePageLevel(String adress) {
        int count = 0;
        for (int i = 0; i < adress.length(); i++) {
            if (adress.charAt(i) == '/') count++;
        }
        int resulte = switch (count) {
            case 4 -> 1;
            case 5 -> 2;
            case 6 -> 3;
            case 7 -> 4;
            default -> 0;
        };
        return resulte;
    }
}
