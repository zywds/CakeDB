package com.zhangyuwei.cake.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class calcualtorTime {
    /** 获取两个时间的时间查 如1天2小时30分钟 */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        String str ="2018-12-23 17:00";
        //String str1="2016-06-01 12:30";
        Date str1=new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        long from = simpleFormat.parse(str).getTime();
        //long to = simpleFormat.parse(str1).getTime();
        long to=str1.getTime();
        int minutes = (int) ((to - from)/(1000 * 60));
        System.out.println(minutes);
    }
}
