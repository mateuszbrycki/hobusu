package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Mateusz Brycki on 14/01/2017.
 */
public class DateUtil {

    public static String dateToCommonFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
