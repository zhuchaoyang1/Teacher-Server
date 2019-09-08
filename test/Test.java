import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 13:34
 */

public class Test {

    public static Date parse(String str, String pattern, Locale locale) {
        if (str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date date, String pattern, Locale locale) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }



    public static void main(String[] args) throws ParseException {
//        String str = "Mon Dec 31 00:00:00 CST 2012";
////        Date now=new Date();
//        Date date = parse(str, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
////        String str2=format(now, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.CHINA);
//        System.out.printf("%tF %<tT%n", date);
////        System.out.println(str2);




    }

}
