package com.viettel.vtskit.logs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    public static String formatDate(Date date){
        if(date == null){
            return null;
        }
        String pattern = "yyyy-MM-dd@HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
