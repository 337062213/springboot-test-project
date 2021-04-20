 package com.springboot.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
     
     public static String  getCurrentTime1() {
         Date date = new Date();
         SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         return dateFormat.format(date);
     }
     
     public static String  getCurrentTime2() {
         Calendar calendar= Calendar.getInstance();
         SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         System.out.println(dateFormat.format(calendar.getTime()));
         return dateFormat.format(calendar.getTime());
     }
     
     public static String  getCurrentTime3() {
         Calendar cal=Calendar.getInstance();      
         int y=cal.get(Calendar.YEAR);      
         int m=cal.get(Calendar.MONTH);      
         int d=cal.get(Calendar.DATE);      
         int h=cal.get(Calendar.HOUR_OF_DAY);      
         int mi=cal.get(Calendar.MINUTE);      
         int s=cal.get(Calendar.SECOND);      
         System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");
         return "现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";
     }

}
