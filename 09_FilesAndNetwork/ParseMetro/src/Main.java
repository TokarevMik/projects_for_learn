import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {


            ArrayList<Line> arrayLines;
//            File input = new File("res/Карта метро Москвы со станциями МЦК и МЦД 2020.html");
//            Document doc = Jsoup.parse(input,"UTF-8","https://www.moscowmap.ru/metro.html#lines");
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element tab = doc.select("div[id = metrodata]").first();
            Elements lines = tab.getElementsByClass("s-depend-control-single");//названия линий
            Elements stationLists = tab.getElementsByAttributeValueMatching("data-line", "\\w*\\d+");
            LinkedHashMap<String, ArrayList<String>> stations = null;
            stations = AllStations(stationLists);               //нумерованные списки станций
            //linesM.forEach((k, v) -> System.out.println("За: " + k + " списано: " + v));
            arrayLines = AllLines(stationLists); // список линий

            JSONObject object = new JSONObject();
            JSONObject stationJO = new JSONObject();
            stations.forEach((k, v) -> stationJO.put(k, v));
//            for (Map.Entry<String, ArrayList<String>> entry : stations.entrySet()) {
//                JSONArray arrStns = new JSONArray();
//                arrStns.addAll(entry.getValue());
//                stationJO.put(entry.getKey(), arrStns);
//            }
            object.put("stations", stationJO);

            FileWriter file = new FileWriter("res/map2.json");
            file.write(object.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Line> AllLines(Elements stationLists) {
        ArrayList<Line> allLines = new ArrayList<>();
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-line")) {
                String num = e.attr("data-line");
                String name = e.text();
                allLines.add(new Line(num, name));
            }
        }
        return allLines;
    }

    private static LinkedHashMap<String, ArrayList<String>> AllStations(Elements stationLists) {
        LinkedHashMap<String, ArrayList<String>> f = new LinkedHashMap<>();
        String numOfLine = null;
        ArrayList<String> st = null;
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-stations")) {
                Elements stations = e.getElementsByTag("p");
                for (Element s : stations) {
                    String nameOfStation = s.text().substring(3);
                    st.add(nameOfStation);
                    f.put(numOfLine, st);
                }
            } else {
                numOfLine = e.attr("data-line"); //номер линии
                st = new ArrayList<>();
            }
        }
        return f;
    }
}
