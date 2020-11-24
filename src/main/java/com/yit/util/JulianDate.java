/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author anuphame
 */
public class JulianDate {

    private static final Date d1 = new GregorianCalendar(1840, 11, 31, 0, 0, 0).getTime();

    public static Long getDateToLong(Date input) {
        // Get msec from each, and subtract.
        long diff = input.getTime() - d1.getTime();
        return (diff / (1000 * 60 * 60 * 24));
    }

    public static String getDate(int inputDate) {
        Calendar cal = (Calendar) d1.clone();
        cal.add(Calendar.DAY_OF_YEAR, inputDate);
        Date jdDate = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(jdDate);
    }
}
