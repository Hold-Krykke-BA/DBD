package util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static Date getDateFromString(String stringDate){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getStringFromDate(Date date){
        StringBuilder strb = new StringBuilder();
        strb.append(date.)

        return strb.toString();
    }
}
