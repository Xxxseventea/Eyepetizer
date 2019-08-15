package com.example.eyepetizer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

//    /**
//     * 将时间轴转化为 hh：mm：ss
//     *
//     */
//
//    public static String stampToDate(String s){
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(Long.parseLong(s));
//        res = simpleDateFormat.format(date);
//        return res;
//    }

    public static String time(int timeString){
        int h,m,s;
        h = timeString / 3600;
        m = (timeString - h * 3600)/60;
        s = timeString - h*3600-m*60;
        if(s > 60){
            s =  s % 60;
            m = s/60 + m;
        }
        if(m > 60){
            h = h+1;
            m = m-60;
        }

        return (m+":"+s);
    }
}
