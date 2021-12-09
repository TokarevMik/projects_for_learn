import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    WorkTime workTime;
    HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    //    WorkTime[] voteStationWorkTimes = new WorkTime[180];
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();
    Voter voter;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                Date birthDate = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(name, birthDate);
                Integer count = voterCounts.get(voter);
                voterCounts.put(voter, count == null ? 1 : count + 1);
            }
            if (qName.equals("visit")) {
                int station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                workTime = voteStationWorkTimes.get(station);     //проверка HashMap
//                workTime = voteStationWorkTimes[station];   //проверка цикла
                if (workTime == null) {
                    workTime = new WorkTime();
//                    voteStationWorkTimes[station] = workTime; //проверка цикла
                    voteStationWorkTimes.put(station, workTime);  //проверка HashMap
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }//end of startElement

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) voter = null;
        if (qName.equals("visit")) workTime = null;
    }

    public HashMap getWorkTime() {
        return voteStationWorkTimes;
    }  //

    /*public WorkTime[] getWorkTime() {
        return voteStationWorkTimes;
    }*/   //

    public HashMap getVoterCounter() {
        return voterCounts;
    }

}
