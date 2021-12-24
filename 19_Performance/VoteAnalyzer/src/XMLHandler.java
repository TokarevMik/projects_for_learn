import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private static StringBuilder insertQuery = new StringBuilder(79900);
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    String name;
    Date birthDate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            name = attributes.getValue("name");
            try {
                birthDate = birthDayFormat.parse(attributes.getValue("birthDay"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Voter key = new Voter(name, birthDate);
            if (!voterCounts.containsKey(key)) {
                voterCounts.put(key, 1);
                String date = attributes.getValue("birthDay");
                countVoterBuilder(name, date);
            } else {
                voterCounts.replace(key, voterCounts.get(key) + 1);
                int count = voterCounts.get(key);
                String date = attributes.getValue("birthDay");
                date = date.replace('.', '-');
                try {
                    DBConnection.executeUpdate(name, count, date);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    } //

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            if (insertQuery.length() > 800000) {
                try {
                    DBConnection.executeMultiInsert(insertQuery);
                    insertQuery = new StringBuilder(79900);
                    System.out.println("Сброс");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (qName.equals("voters")) {
            try {
                DBConnection.executeMultiInsert(insertQuery);
                System.out.println("Сброс финальный");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            insertQuery = new StringBuilder();
        }

    }

    public static void countVoterBuilder(String name, String birthDay) {
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append((isStart ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

    }
}
