package com.zhangyuwei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class d {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH时mm分ss秒");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String s=df.format(new Date());
        System.out.println(s);
    }
}
