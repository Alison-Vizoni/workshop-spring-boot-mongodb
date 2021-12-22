package com.workshop.mongodb.resources.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

    public static  String decodeParam(String text){
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    public static Date convertDate(String textDate, Date defaultfvalue){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return dateFormat.parse(textDate);
        } catch (ParseException e) {
            return defaultfvalue;
        }
    }
}
