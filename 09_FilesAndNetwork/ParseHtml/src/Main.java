import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://lenta.ru";
        Document doc = Jsoup.connect(url).get();
        Elements imgs = doc.select("img[class = g-picture]");
        List<URL> urls = new ArrayList<>();
        for (Element e : imgs) {
            urls.add(new URL(e.attr("abs:src")));
        }
        InputStream input = null;
        for (URL u : urls) {
            String name = nameOfImg(u.getFile());
            input = u.openStream();
            Path p = Path.of("images\\" + name);
            Files.copy(input,p);
            System.out.println(name);
        }
        input.close();
    }

    private static String nameOfImg(String file) {
        String[] s = file.split(".*\\/");
        String name = s[1];
        return name;
    }
}

