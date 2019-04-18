package com.shiv.purplleassessment.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String getCountry() {
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static String DateToTimeFormat(String oldstringDate) {
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.TIME_FORMAT, new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat(Constants.DATE_FORMAT, new Locale(getCountry())).parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }


    public static String DateFormat(String oldstringDate) {
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.OUTPUT_DATE_FORMAT, new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat(Constants.DATE_FORMAT, new Locale(getCountry())).parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }
}
