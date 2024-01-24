package com.example.myhealthapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateTransform {

    public static final  SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD.MM.YY");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("DD.MM.YY HH:mm");
    public static final SimpleDateFormat UNIX_DATE_TIME = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

}
