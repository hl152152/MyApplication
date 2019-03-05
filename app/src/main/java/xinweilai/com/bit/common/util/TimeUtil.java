package xinweilai.com.bit.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 空 on 2017/11/28 0028.
 */

public class TimeUtil {
    public static String getSeven(String time) {
        try {
            return time.substring(0, 7);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 6B 赞一手 , 兄台名擦秋毫 , 直击重点 6B 了
     *
     * @param time
     * @return
     */
    public static String getTen(String time) {
        try {
            return time.substring(0, 10);
        } catch (Exception e) {
        }
        return "";
    }

    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        return mYear;
    }

    public static String[] get11Year() {
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份

        String[] years = new String[12];
        years[0] = "--全部--";
        for (int i = 0; i < years.length - 1; i++) {
            int year = mYear - 7 + i;
            years[i + 1] = String.valueOf(year);
        }
        return years;
    }


    public static String getCurrentTime() {
        long l = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(l);
        return dateString;
    }

    public static long convertToUnix(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        }
        long unixTimestamp = date.getTime();
        return unixTimestamp;
    }


    public static String getCurrentTimeTen() {
        long l = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(l);
        return getTen(dateString);
    }
}
