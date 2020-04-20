package br.com.help;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatePicker {

    public static String getActualDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
