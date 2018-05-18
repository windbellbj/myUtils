package com.lxl.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 *<p>Description : 日期工具类</p> 
 *<p>Date        : May 3, 2013</p> 
 *<p>Remark      : </p> 
 * @author lixinlong
 */
public class DateUtils {
    /**
     * YYYY-MM-DD格式 
     */
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * YYYY-MM格式 
     */
    private static SimpleDateFormat monthFormat = new SimpleDateFormat(
            "yyyy-MM");


    /**
     * 将String转换成Date 
     */
    public synchronized static Date stringToDate(String pattern, String p_date_s) throws ParseException {
        if (p_date_s == null || "".equals(p_date_s))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(p_date_s);
    }

    /**
     * 将Date转换成String 
     */
    public static String dateToString(String pattern, Date p_string_d){
        if (p_string_d == null)
            return null;
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            return "error";
        }
        return sdf.format(p_string_d);
    }

    /**
     *
     * 根据日期，获得当年第一月 
     *
     * @param date
     *             日期 
     * @return 当年第一月  yyyy-MM 
     */
    public static String GetFirstMonth(String date) {
        Calendar calendar = Calendar.getInstance();
        Date dateFrom = null;
        try {
            dateFrom = format.parse(date);
            calendar.setTime(dateFrom);
            int year = calendar.get(Calendar.YEAR);
            return year + "-" + "01";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * 根据日期，获得当前月
//     *
//     * @param date
//     *             日期
//     * @return 当年第一月  yyyy-MM
//     */
//    public static String GetMonth(String date) {
//
//        Calendar calendar = Calendar.getInstance();
//        Date dateFrom = null;
//
//        try {
//            dateFrom = format.parse(date);
//            calendar.setTime(dateFrom);
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH) + 1;
//            return year + "-" +  StringUtils.Pading(Integer.toString(month), 2, StringUtils.LEFT, '0');
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 获取指定日期的当月第一天日期 
     *
     * @param date
     *            日期 
     * @return 当月第一天日期
     */
    public static String GetFirstDateOfMonth(String date) {

        Calendar calendar = Calendar.getInstance();
        Date dateFrom = null;

        try {
            dateFrom = format.parse(date);
            calendar.setTime(dateFrom);
            int count = calendar.get(Calendar.DAY_OF_MONTH) - 1;
            for (int i = 0; i < count; i++) {
                calendar.add(calendar.DATE, -1);
            }
            return format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 倒退数周的周一日期 
     *
     * @param date
     *            日期 
     * @param week
     *            周数 
     * @return 周第一天
     */
    public static String GetFirstDateOfWeek(String date, int week) {

        Calendar calendar = Calendar.getInstance();
        Date dateFrom = null;
        try {
            dateFrom = format.parse(date);
            calendar.setTime(dateFrom);
            int dow = calendar.get(Calendar.DAY_OF_WEEK) - 2;
            int count = 7 * week;
            for (int i = 0; i < count + dow; i++) {
                calendar.add(calendar.DATE, -1);
            }
            return format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * String类型日期转换Date型 
     *
     * @param bir
     *            出生年月 
     * @param string
     *            格式 
     * @return 日期型
     * @throws ParseException
     *             异常 
     */
    public static Date getDateTime(String bir, String string)
            throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(string);
        Date date = format.parse(bir);
        return date;
    }


    /**
     * 取得当前系统时间 
     * @return
     */
    public static Date getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * 转换当前时间 
     * @param format 指定格式 
     * @return 日期
     */
    public static Date parseCurrentDateTime(String format) {
        Calendar c = Calendar.getInstance();
        Date d = null;
        try {
            d = parseDateTime(c.getTime(), format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 格式化当前时间 
     * @param format 指定格式 
     * @return 日期字符串形式
     */
    public static String formatCurrentDateTime(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(c.getTime());
    }

    /**
     * 取得指定时间固定格式的字符串形式 
     * @param date 时间 
     * @param format 格式 
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 转换指定时间为固定格式 
     * @param dateStr 时间 
     * @param format 格式 
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateStr, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }

    /**
     * 转换指定时间为固定格式 
     * @param date 时间 
     * @param format 格式 
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(Date date, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateFormat.format(date));
    }
    /**
     * 根据日期取得对应周周一日期 
     *
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(Calendar.MONDAY);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 取得上月第一天 
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        //c.setTime(date);  
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    /**
     * 根据日期取得对应月的1号 
     * xuxiaojun 
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonthByDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 得到系统的年,格式："YYYY" 
     *
     *
     * @return int
     */
    public static int getSysYear() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }




    /**
     * 得到当前周是一年第几周 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得指定日期的当前月的最后一天 
     * @param date 指定日期 
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 获得指定日期的下一天 
     * @param date 指定日期 
     * @return
     */
    public static Date getNextDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 获得当前日期当前月的第一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     * @author hll
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);          //本月的第一天  
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期当前月的最后一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);         //本月的最后一天  
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期当前周的第一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     * @author hll
     */
    public static String getFirstDayOfWeek(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 本周的第一天  
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期当前周的最后一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     * @author hll
     */
    public static String getLastDayOfWeek(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY+6); // 本周的最后一天  
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期当前年的第一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     * @author hll
     */
    public static String getFirstDayOfYear(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_YEAR, 1);           //本年的第一天  
        return formatDateTime(cal.getTime(), format);
    }
    /**
     * 获得当前日期当前年的最后一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     * @author hll
     */
    public static String getLastDayOfYear(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_YEAR, 1);           //本年的第一天  
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DATE,-1);                  //本年的最后一天  
        return formatDateTime(cal.getTime(), format);
    }

    /**
     * 获得下一个月的最后一天 
     * @param format 指定格式 
     * @return 指定格式的日期形式
     */
    public static String getLastDayOfNextMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 2);
        cal.add(Calendar.DATE, -1);
        return formatDateTime(cal.getTime(), format);
    }

    public static int[] getDateTimeSub(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int y1 = cal.get(Calendar.YEAR);
        int m1 = cal.get(Calendar.MONTH);
        int d1 = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(date2);
        int y2 = cal.get(Calendar.YEAR);
        int m2 = cal.get(Calendar.MONTH);
        int d2 = cal.get(Calendar.DAY_OF_YEAR);
        int[] array = new int[] {y1 - y2, m1 - m2, d1 - d2};
        return array;
    }


    /**
     * 取得两个日期的差值 
     * @params startTime 开始日期
     * @params endTime 结束日期
     * @params 适用于yyyy-MM-dd，精确到日
     * @return 差值日
     */
    public static int dateDiff(String startTime, String endTime) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        long nd = 1000 * 24 * 60 * 60;
        long diff;
        long day = 0l;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int)day;
    }

}