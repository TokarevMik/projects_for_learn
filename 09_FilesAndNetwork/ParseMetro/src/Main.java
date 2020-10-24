import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String dataFile = "res/map2.json";

    public static void main(String[] args) {
        String url = "https://www.moscowmap.ru/metro.html#lines";
        try {
            List<Line> arrayLines;
//            File input = new File("res/Карта метро Москвы со станциями МЦК и МЦД 2020.html");
//            Document doc = Jsoup.parse(input, "UTF-8", "https://www.moscowmap.ru/metro.html#lines");
            Document doc = Jsoup.connect(url).timeout(0).maxBodySize(0).get();
            Element tab = doc.select("div[id = metrodata]").first();
            Elements stationLists = tab.getElementsByAttributeValueMatching("data-line", "\\w*\\d+");
            Map<String, List<String>> stations;
            List<Set<Station>> connections;
            stations = allStations(stationLists);               //нумерованные списки станций
            arrayLines = allLines(stationLists);                // список линий
            connections = allConnections(stationLists);

            JSONObject object = new JSONObject();//итоговый файл
            JSONObject stationJO = new JSONObject();
            JSONArray linesJA = new JSONArray();
            stations.forEach(stationJO::put);
            object.put("stations", stationJO);          //stations в JSON
            for (Line l : arrayLines) {
                JSONObject line = new JSONObject();
                line.put("number", l.getNumber());
                line.put("name", l.getName());
                linesJA.add(line);
            }
            object.put("lines", linesJA);           //lines в JSON
            JSONArray allConnectionsJ = new JSONArray();
            for (Set<Station> sta : connections) {
                JSONArray connectionJ = new JSONArray();
                for (Station s : sta) {
                    JSONObject conJo = new JSONObject();
                    conJo.put("line", s.getLine());
                    conJo.put("station", s.getName());
                    connectionJ.add(conJo);
                }
                allConnectionsJ.add(connectionJ);
            }
            object.put("connections", allConnectionsJ);  //connections to JSON
            FileWriter file = new FileWriter(dataFile);
            file.write(object.toJSONString());
            file.flush();
            file.close();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(getJsonFile());
            JSONObject jsonStations = (JSONObject) jsonObject.get("stations");
            jsonStations.keySet().forEach(lineNumberObject ->
            {
                String lineNumber = ((String) lineNumberObject);
                JSONArray stationsArray = (JSONArray) jsonStations.get(lineNumberObject);
                int stationCount = stationsArray.size();
                System.out.println("На линии " + lineNumber + "  - " + stationCount + " станций");
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Set<Station>> allConnections(Elements stationLists) {
        Pattern patternSt = Pattern.compile("\\«.+\\»");
        Pattern patternLn = Pattern.compile("\\w*\\d+");
        String num = null;
        List<Set<Station>> connection = new LinkedList<>();
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-line")) {
                num = e.attr("data-line");
            }
            Elements stations2 = e.getElementsByTag("p");
            for (Element c : stations2) {
                Set<Station> conn = new LinkedHashSet<>();
                Elements stations3 = c.getElementsByClass("t-icon-metroln");
                if (stations3.hasClass("t-icon-metroln")) {
                    String stFrom1 = c.text();
                    String stFrom = (stFrom1.split("\\s", 2))[1];
                    if (cheker(connection, stFrom, num)) {
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
                        boolean chek = cheker(connection, nameOfStFrom, numLine);
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

    private static boolean cheker(List<Set<Station>> connection, String nameOfStFrom, String numLine) {
        for (Set<Station> h : connection) {
            for (Station s : h) {
                if (s.getName().equals(nameOfStFrom) & s.getLine().equals(numLine)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Line> allLines(Elements stationLists) {
        List<Line> allLines = new ArrayList<>();
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-line")) {
                String num = e.attr("data-line");
                String name = e.text();
                allLines.add(new Line(num, name));
            }
        }
        return allLines;
    }

    private static Map<String, List<String>> allStations(Elements stationLists) {
        Map<String, List<String>> allStations = new LinkedHashMap<>();
        String numOfLine = null;
        List<String> st = null;
        for (Element e : stationLists) {
            if (e.hasClass("js-metro-stations")) {
                Elements stations = e.getElementsByTag("p");
                for (Element s : stations) {
                    String nameOfStation = s.text().substring(3);
                    st.add(nameOfStation);
                    allStations.put(numOfLine, st);
                }
            } else {
                numOfLine = e.attr("data-line"); //номер линии
                st = new ArrayList<>();
            }
        }
        return allStations;
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
