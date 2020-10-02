import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    StationIndex testIndex;
    Station from;
    Station to;

    @Override
    public void setUp() throws Exception {
        route = new ArrayList<>();
        Line line1 = new Line(1, "Red");
        Line line2 = new Line(2, "Green");
        route.add(new Station("Горная", line1));
        route.add(new Station("Лесная", line1));
        route.add(new Station("Пушкинская", line2));
        route.add(new Station("Гоголя", line2));

    }

    @Test
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }


    @Test
    public void testGetShortestRoute() {
        testIndex = new StationIndex();
        Line line1 = new Line(1, "Red");
        Line line2 = new Line(2, "Green");
        Line line3 = new Line(3, "Blue");
        Station st_0 = new Station("Горная", line1);
        Station st_1 = new Station("Лесная", line1);
        Station st_2 = new Station("Пушкинская", line2);
        Station st_3 = new Station("Гоголя", line2);
        Station st_4 = new Station("Достоевского", line2);
        Station st_5 = new Station("Яблочная", line3);
        Station st_6 = new Station("Луговая", line3);
        line1.addStation(st_0);
        line1.addStation(st_1);
        line2.addStation(st_2);
        line2.addStation(st_3);
        line2.addStation(st_4);
        line3.addStation(st_5);
        line3.addStation(st_6);
        testIndex.addStation(st_0);//0
        testIndex.addStation(st_1);//1
        testIndex.addStation(st_2);//2
        testIndex.addStation(st_3);//3
        testIndex.addStation(st_4);//4
        testIndex.addStation(st_5);//5
        testIndex.addStation(st_6);//6
        List<Station> connSt1 = new ArrayList<>();
        connSt1.add(st_1);
        connSt1.add(st_2);
        List<Station> connSt2 = new ArrayList<>();
        connSt2.add(st_4);
        connSt2.add(st_5);
        testIndex.addConnection(connSt1);
        testIndex.addConnection(connSt2);
        from = st_0;
        to = st_1;
        RouteCalculator shortDir = new RouteCalculator(testIndex);
        List<Station> actual = shortDir.getShortestRoute(from, to);
        List<Station> oneLineExp = new ArrayList<>(); //маршрут без пересадок
        oneLineExp.add(testIndex.getStation("Горная"));
        oneLineExp.add(testIndex.getStation("Лесная"));
        assertEquals(oneLineExp, actual);//проверка без пересадок
        //_______________________________________________________
        from = st_0;
        to = st_3;
        actual = shortDir.getShortestRoute(from, to);
        List<Station> oneConnExp = new ArrayList<>(); //маршрут с 1 пересадкой
        oneConnExp.add(testIndex.getStation("Горная"));
        oneConnExp.add(testIndex.getStation("Лесная"));
        oneConnExp.add(testIndex.getStation("Пушкинская"));
        oneConnExp.add(testIndex.getStation("Гоголя"));
        assertEquals(oneConnExp, actual); //проверка с 1 пересадкой
        //_______________________________________________________
        List<Station> twoConnExp = new ArrayList<>(); //маршрут с 2 пересадками
        from = st_0;
        to = st_6;
        actual = shortDir.getShortestRoute(from, to);
        twoConnExp.add(testIndex.getStation("Горная"));
        twoConnExp.add(testIndex.getStation("Лесная"));
        twoConnExp.add(testIndex.getStation("Пушкинская"));
        twoConnExp.add(testIndex.getStation("Гоголя"));
        twoConnExp.add(testIndex.getStation("Достоевского"));
        twoConnExp.add(testIndex.getStation("Яблочная"));
        twoConnExp.add(testIndex.getStation("Луговая"));
        assertEquals(twoConnExp, actual);// проверка с 2 пересадками

    }

    @Override
    public void tearDown() throws Exception {
    }


}