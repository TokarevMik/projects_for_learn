import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    // Распечатайте с помощью библиотеки airport.jar время вылета и модели самолётов, вылетающих в ближайшие два часа.
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        Date dateNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date dateFlight = calendar.getTime();
        airport.getTerminals().stream()
                .flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getDate().before(dateFlight))
                .filter(f -> f.getDate().after(dateNow))
                .filter(f -> f.getType().toString().equals("DEPARTURE"))
                .forEach(f -> System.out.println("время вылета " + f.getDate() + " модель " + f.getAircraft()));
    }
}
