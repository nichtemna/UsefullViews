package com.nichtemna.views.utils;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class DateUtils {
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DAY_TIME = "dd MMMM HH:mm";
    public static final String DATE_FORMAT_DAY_MONTH_YEAR = "dd MMMM yyyy";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TIME = "HH:mm";


    public static String convertDateBeginEnd(String stringBeginDate, String stringEndDate) {
        String result;

        Date beginDate = getDateFromString(stringBeginDate, DATE_FORMAT_FULL);
        Date endDate = getDateFromString(stringEndDate, DATE_FORMAT_FULL);

        DateFormat dateFormatDayTime = new SimpleDateFormat(DATE_FORMAT_DAY_TIME, new Locale("ru", "RU"));
        DateFormat dateFormatTime = new SimpleDateFormat(DATE_FORMAT_TIME, new Locale("ru", "RU"));

        result = dateFormatDayTime.format(beginDate);

        if (beginDate.getDate() == endDate.getDate() && beginDate.getMonth() == endDate.getMonth()) {
            result += " - " + dateFormatTime.format(endDate);
        } else {
            result += " - " + dateFormatDayTime.format(endDate);
        }

        return result;
    }

    public static String convertDate(String date) {
        String result;

        Date beginDate = getDateFromString(date, DATE_FORMAT_FULL);

        DateFormat dateFormatDayTime = new SimpleDateFormat(DATE_FORMAT_DAY_TIME, new Locale("ru", "RU"));

        result = dateFormatDayTime.format(beginDate);

        return result;
    }

    /**
     * Converts string with date to readable state
     *
     * @param stringDate with date and time in it
     * @param formatFrom of date and time in this string
     */
    public static String convertDate(String stringDate, String formatFrom, String formatTo) {
        String convertedDate = "";
        if (stringDate != null) {
            Date date = getDateFromString(stringDate, formatFrom);
            DateFormat dateFormatDayTime = new SimpleDateFormat(formatTo, new Locale("ru", "RU"));
            convertedDate = dateFormatDayTime.format(date);
        }
        return convertedDate;
    }

    /**
     * Converts calendar  to readable state
     */
    public static String convertCalendarToString(Calendar calendar, String formatTo) {
        Date date = calendar.getTime();
        DateFormat dateFormatDayTime = new SimpleDateFormat(formatTo, new Locale("ru", "RU"));
        return dateFormatDayTime.format(date);
    }

    /**
     * Gets instance of calendar with date and time from the string  using format
     */
    public static Calendar getCalendarFromString(String string, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(string);
        } catch (ParseException e) {
            Log.e("tag", e.getLocalizedMessage());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Converts string to Date class using date format
     */
    public static Date getDateFromString(String string, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(string);
        } catch (ParseException e) {
            Log.e("tag", e.getLocalizedMessage());
        }
        return date;
    }


}
