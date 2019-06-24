package cn.edu.nuc.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeForm {

    public static Calendar cal = Calendar.getInstance();
    public static Date dt = new Date();


    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_time = sdf.format(dt);
        return str_time;
    }
    public static String getYear(){
        return String.valueOf(cal.get(Calendar.YEAR));
    }
    public static String getMonth(){
        return String.valueOf(cal.get(Calendar.MONTH)+1);
    }
    public static String getDay(){
        return String.valueOf(cal.get(Calendar.DATE));
    }
    public static String getWeek(){
        String str = null;
        switch(cal.get(Calendar.DAY_OF_WEEK)){
            case 1:
                str = "星期日";
                break;
            case 2:
                str = "星期一";
                break;
            case 3:
                str = "星期二";
                break;
            case 4:
                str = "星期三";
                break;
            case 5:
                str = "星期四";
                break;
            case 6:
                str = "星期五";
                break;
            case 7:
                str = "星期六";
                break;
        }
        return  str;
    }
    public static String getHour(){
        return String.valueOf(cal.get(Calendar.HOUR));
    }

    public static String getMin(){
        return String.valueOf(cal.get(Calendar.MINUTE));
    }

    public static String getSecond(){
        return String.valueOf(cal.get(Calendar.SECOND));
    }

}
