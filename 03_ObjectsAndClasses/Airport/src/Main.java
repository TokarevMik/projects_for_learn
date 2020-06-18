import com.skillbox.airport.Airport;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println(airport.getAllAircrafts());
        System.out.println("*************************");
        List aircraft = airport.getAllAircrafts();
        System.out.println(aircraft.size());
        
    }
}
