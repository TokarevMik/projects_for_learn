import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {
            ArrayList<Line> arrayLines;
//            File input = new File("res/Карта метро Москвы со станциями МЦК и МЦД 2020.html");
//            Document doc = Jsoup.parse(input, "UTF-8", "https://www.moscowmap.ru/metro.html#lines");
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element tab = doc.select("div[id = metrodata]").first();
//            Elements lines = tab.getElementsByClass("s-depend-control-single");//названия линий
            Elements stationLists = tab.getElementsByAttributeValueMatching("data-line", "\\w*\\d+");
            LinkedHashMap<String, ArrayList<String>> stations;
            LinkedList<LinkedHashSet<Station>> connections;
            //stations = AllStations(stationLists);               //нумерованные списки станций
            //arrayLines = AllLines(stationLists); // список линий
            connections = AllConnections(stationLists);
            System.out.println("5555555556666666");
            for (HashSet<Station> s : connections) {
                System.out.println("++++");
                s.forEach(e -> System.out.println("Линия " + e.getLine() + " " + e.getName()));
            }
            System.out.println("5555555556666666");

//            JSONObject object = new JSONObject();//итоговый файл
//            JSONObject stationJO = new JSONObject();
//            JSONArray linesJA = new JSONArray();
//            stations.forEach((k, v) -> stationJO.put(k, v));
//            object.put("stations", stationJO);          //stations в JSON
//            for (Line l : arrayLines) {
//                JSONObject line = new JSONObject();
//                line.put("number", l.getNumber());
//                line.put("name", l.getName());
//                linesJA.add(line);
//            }

            //object.put("lines", linesJA);           //lines в JSON

//            FileWriter file = new FileWriter("res/map2.json");
//            file.write(object.toJSONString());
//            file.flush();
//            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LinkedList<LinkedHashSet<Station>> AllConnections(Elements stationLists) {
        Pattern patternSt = Pattern.compile("\\«.+\\»");
        Pattern patternLn = Pattern.compile("\\w*\\d+");
        String num = null;
        LinkedList<LinkedHashSet<Station>> connection = new LinkedList<>();
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-line")) {
                num = e.attr("data-line");
            }
            Elements stations2 = e.getElementsByTag("p");
            for (Element c : stations2) {
                LinkedHashSet<Station> conn = new LinkedHashSet<>();
                Elements stations3 = c.getElementsByClass("t-icon-metroln");
                if (stations3.hasClass("t-icon-metroln")) {
                    String stFrom1 = c.text();
                    String stFrom = (stFrom1.split("\\s", 2))[1];                           //Станция с которой
                    if (Cheker(connection, stFrom, num)) {
                        conn.add(new Station(stFrom, num));
                    } else {
                        break;
                    }
                    String tAndLine = stations3.toString();
                    Matcher m1 = patternLn.matcher(tAndLine);
                    Matcher m2 = patternSt.matcher(tAndLine);
                    while ((m1.find()) & (m2.find())) {
                        String nameOfStFrom = m2.group();
                        int endIndex = nameOfStFrom.length() - 1;
                        nameOfStFrom = nameOfStFrom.substring(1, endIndex);
                        String numLine = m1.group();
                        boolean chek = Cheker(connection, nameOfStFrom, numLine);
                        if (chek) {
                            conn.add(new Station(nameOfStFrom, numLine));
                        }
                    }
                    connection.add(conn);
                }
            }
        }
        return connection;
    }

    private static boolean Cheker(LinkedList<LinkedHashSet<Station>> connection, String nameOfStFrom, String numLine) {
        for (HashSet<Station> h : connection) {
            for (Station s : h) {
                if (s.getName().equals(nameOfStFrom) & s.getLine().equals(numLine)) {
                    return false;
                }
            }
        }
        return true;
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
