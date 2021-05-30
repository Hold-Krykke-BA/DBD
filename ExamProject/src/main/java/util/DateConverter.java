package util;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// 2021-05-19 21:14:35
// yyyy-MM-dd hh:mm:ss
public class DateConverter {

    public static LocalDateTime getDateFromString(String stringDate){
        return LocalDateTime.parse(stringDate, getDateFormatter());
    }

    public static DateTimeFormatter getDateFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static String getStringFromDate(LocalDateTime date){
        return date.format(getDateFormatter());
    }

    public static LocalDateTime javaSQLTimestampToLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }

    public static Timestamp LocalDateTimeToJavaTimestamp(LocalDateTime localdatetime){
        return Timestamp.valueOf(localdatetime);
    }

    public static LocalDateTime EpochToLocalDateTime(Long epoch) {
        return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
