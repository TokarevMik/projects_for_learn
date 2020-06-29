import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int day = 14;
        int month = 02;
        int year = 1984;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd - E", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i <36 ; i++) {
            year++;
            calendar.set(year,month,day);
            System.out.println(i + " " + simpleDateFormat.format(calendar.getTime()));
        }


    }
}

