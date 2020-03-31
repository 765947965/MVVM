package com.huike.face.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    private static final SimpleDateFormat formatHour = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.SIMPLIFIED_CHINESE);
    private static final SimpleDateFormat formatParse = new SimpleDateFormat("yyyy-MM-ddHHmm", Locale.SIMPLIFIED_CHINESE);

    private static final SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm", Locale.SIMPLIFIED_CHINESE);
    private static final SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd", Locale.SIMPLIFIED_CHINESE);

    public static Date parseDate(String value) {
        try {
            return formatParse.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        return format.format(date);
    }

    public static String getFormatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return formatTime.format(date);
    }

    public static String getFormatDateHourTime(Date date) {
        if (date == null) {
            return "";
        }
        return formatHour.format(date);
    }

    public static String getFormatDateDay(Date date) {
        if (date == null) {
            return "";
        }
        return formatDay.format(date);
    }
}
