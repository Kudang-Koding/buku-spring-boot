package kudangkoding.gamifikasi.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverterUtil {

    public static String currentTimeMilis() {
        return "" + System.currentTimeMillis();
    }

    public static String thirtyDaysTimeMillis() {

        long millis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        System.out.println(calendar.getTimeInMillis());

        return "" + calendar.getTimeInMillis();
    }

    public static Date toDateFormat(String dateString){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String toStringFormat(Date date){
        if (date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
