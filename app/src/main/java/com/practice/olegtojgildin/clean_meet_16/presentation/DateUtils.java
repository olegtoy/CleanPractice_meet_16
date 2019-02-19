package com.practice.olegtojgildin.clean_meet_16.presentation;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by olegtojgildin on 16/02/2019.
 */

public class DateUtils {

    public static String covertDate(long dates) {
        Date time = new java.util.Date((dates * 1000));
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(time);
    }
}
