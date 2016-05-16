package com.rxdroid.domain.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by rxdroid on 3/24/16.
 */
public final class DateParser {

    private static final String TAG = "DateParser";
    private static final int HOUR_IN_MS = 3600 * 1000;
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String US_DATE_FORMAT = "yyyy-MM-dd";
    private static final String GENERAL_DATE_FORMAT = "dd.MM.yyyy";

    private DateParser() {
    }

    public static Calendar getDateFromIsoString(String dateAsString) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_DATE_FORMAT, Locale.getDefault());
        dateFormat.setTimeZone(calendar.getTimeZone());
        try {
            Date date = dateFormat.parse(dateAsString);
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, getTimeZoneOffsetAsHours());
        } catch (ParseException e) {
            Log.e(TAG, "ParseException: ", e);
            return null;
        }
        return calendar;
    }

    public static String getISO8601StringForDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    private static int getTimeZoneOffsetAsHours() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getOffset(Calendar.getInstance().getTimeInMillis()) / HOUR_IN_MS;
    }

    public static String getDateAsString(Calendar calendar) {
        String day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        SimpleDateFormat simpleDateFormat;
        if (Locale.getDefault() == Locale.US) {
            simpleDateFormat = new SimpleDateFormat(US_DATE_FORMAT, Locale.getDefault());
        } else {
            simpleDateFormat = new SimpleDateFormat(GENERAL_DATE_FORMAT, Locale.getDefault());
        }
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        return day + ", " + simpleDateFormat.format(calendar.getTime());
    }

    public static String getTimeAsString(Calendar calendar) {
        String hours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        int min = calendar.get(Calendar.MINUTE);
        String minutes;
        if (min == 0) {
            minutes = "00";
        } else {
            minutes = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        return hours + ":" + minutes;
    }

}
