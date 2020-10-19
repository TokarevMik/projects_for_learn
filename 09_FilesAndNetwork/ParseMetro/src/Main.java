import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {
            StationIndex index = new StationIndex();
//            File input = new File("res/Карта метро Москвы со станциями МЦК и МЦД 2020.html");
//            Document doc = Jsoup.parse(input,"UTF-8","https://www.moscowmap.ru/metro.html#lines");
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element tab = doc.select("div[id = metrodata]").first();
            Elements lines = tab.getElementsByClass("s-depend-control-single");//названия линий
            int linesCount = lines.size();//Число линий
            for (int i = 1; i <= linesCount; i++) {   //заполнение линий и станций
                Elements stationLists = tab.getElementsByAttributeValueMatching("data-line", "\\w*\\d+");
                AllStations(stationLists);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void AllStations(Elements stationLists) {
        int i = 0;
        ArrayList<Station> stationsOnLine = new ArrayList<>();
        String nameOfLine = null;
        String numOfLine = null;
        int numOfLine = 0;
        Line line = null;
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-stations")) {
                Elements stations = e.getElementsByTag("p");
                for (Element s : stations) {
                    String nameOfStation = s.text().substring(3);
                    System.out.println("Название станции " + nameOfStation);
                    line.addStation(new Station(nameOfStation, line)); //добавление станции на линию
                }
            } else {
                nameOfLine = e.text();
                System.out.println("Название линии " + nameOfLine);
                String numOfLine = e.attr("data-line"); //номер линии
                System.out.println("Номер линии - " + numOfLine);
                //line = new Line(numOfLine, nameOfLine);//конструктор линии
            }

        }
    }

}
