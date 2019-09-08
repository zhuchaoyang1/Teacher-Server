package cn.usts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/21 2:43
 */

public class ConvertTime {

    /**
     * 将EEE MMM dd HH:mm:ss zzz yyyy类型字符串转java.util.Date类型
     * 由java.sql.util(date.getTime()) --> util.Date 转 sql.Date
     * @param strTime
     * @return
     * @throws ParseException
     */
    public static Date timeStrToDate(String strTime) throws ParseException {
        java.util.Date date = new java.util.Date(strTime);
        return new java.sql.Date(date.getTime());
    }

    public static Date timeStrToDate2(String strTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdf.parse(strTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new java.sql.Date(date.getTime());
    }


    public static String dateToString(java.sql.Date datee)
    {
        return datee.toString();
    }

    public static void main(String[] args) throws ParseException {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        System.out.println(ConvertTime.dateToString(date));
    }

}
