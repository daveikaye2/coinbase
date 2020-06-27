package com.kayeconsulting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private final static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String toString(Date date) {

        return DEFAULT_DATE_FORMAT.format(date);
    }

}
