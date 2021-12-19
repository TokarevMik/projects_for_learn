import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandler extends DefaultHandler {
    private static StringBuilder insertQuery = new StringBuilder(7990);

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDate = attributes.getValue("birthDay");
            countVoter(name, birthDate);
        }

    } //

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            if (insertQuery.length() > 80000) {
                try {
                    DBConnection.executeMultiInsert(insertQuery);
                    insertQuery = new StringBuilder(7990);
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

    public static void countVoter(String name, String birthDay) {
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append((isStart ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

    }
}
