import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int day = 14;
        int month = 10;
        int year = 1984;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd - E", Locale.ENGLISH);
        Calendar birthDay = Calendar.getInstance();
        birthDay.set(year,month,day);
        Calendar today = Calendar.getInstance();
        int counter = 0;
        while(today.after(birthDay)) {
           System.out.println(counter + " " + simpleDateFormat.format(birthDay.getTime()));
            birthDay.set(year,month,day);
            year++;
            counter++;
        }

    }
}

