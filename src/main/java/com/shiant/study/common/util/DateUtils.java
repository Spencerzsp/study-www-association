package com.shiant.study.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @author zhangqk
 * @version 1.0
 * @date 2016年1月11日
 */
public class DateUtils {

    public static final String DATE_FORMAT_STR1 = "yyyyMMdd";

    public static final String DATE_FORMAT_STR2 = "yyyyMMddHHmmssS";

    public static final String DATE_FORMAT_STR3 = "yyyy/MM/dd";

    public static final String DATE_FORMAT_STR4 = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_FORMAT_STR5 = "yyyy/MM/dd HH:mm:ss.S";

    public static final String DATE_FORMAT_STR6 = "yyyy-MM-dd";

    public static final String DATE_FORMAT_STR7 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_STR8 = "yyyy-MM-dd HH:mm:ss.S";

    public static final String DATE_FORMAT_STR9 = "yyyy年MM月dd日HH时mm分ss秒";

    public static final String DATE_FORMAT_STR10 = "yyyyMMddHHmmss";

    public static final String WEEK_MONDAY = "星期一";

    public static final String WEEK_TUESDAY = "星期二";

    public static final String WEEK_WEDNESDAY = "星期三";

    public static final String WEEK_THURSDAY = "星期四";

    public static final String WEEK_FRIDAY = "星期五";

    public static final String WEEK_SATURDAY = "星期六";

    public static final String WEEK_SUNDAY = "星期七";

    private DateUtils() {

    }

    /**
     * 获取当前系统时间(返回日期格式)
     *
     * @return
     */
    public static Date getCurrentTimeToDate() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * 获取当前系统时间(返回字符串格式)
     *
     * @param formatStr 格式化字符串
     * @return
     */
    public static String getCurrentTimeToStr(String formatStr) {
        Calendar c = Calendar.getInstance();
        return getDateToFormat(c.getTime(), formatStr);
    }

    /**
     * 日期格式化
     *
     * @param dateStr
     * @param parttern
     * @return
     */
    public static String getDateStrToFormat(String dateStr, String parttern) {
        Date date = strConvertToDate(dateStr, parttern);
        return getDateToFormat(date, parttern);
    }

    /**
     * 指定日期格式化
     *
     * @param date      日期
     * @param formatStr 格式化字符串
     * @return
     */
    public static String getDateToFormat(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(formatStr)) {
            return date.toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期格式
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static Date strConvertToDate(String dateStr, String parttern) {
        SimpleDateFormat sdf = new SimpleDateFormat(parttern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param firsterDate 第一个时间
     * @param secondDate  第二个时间
     * @return 相差天数
     */
    public static int getDaysBetween(Date firsterDate, Date secondDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            firsterDate = sdf.parse(sdf.format(firsterDate));
            secondDate = sdf.parse(sdf.format(secondDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(firsterDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(secondDate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 获取日期的星期
     *
     * @param date 日期
     * @return
     */
    public static String getWeek(Date date) {
        String week = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = WEEK_SUNDAY;
                break;
            case 1:
                week = WEEK_MONDAY;
                break;
            case 2:
                week = WEEK_TUESDAY;
                break;
            case 3:
                week = WEEK_WEDNESDAY;
                break;
            case 4:
                week = WEEK_THURSDAY;
                break;
            case 5:
                week = WEEK_FRIDAY;
                break;
            case 6:
                week = WEEK_SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 日期增加或减少指定天数计算
     *
     * @param date 日期

     * @return
     */
    public static Date calDayForDate(Date date, int amount, int fieldType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        switch (fieldType) {
            case Calendar.YEAR:
                c.add(Calendar.YEAR, amount + 1);
                break;
            case Calendar.MONTH:
                c.add(Calendar.MONTH, amount);
                break;
            case Calendar.WEEK_OF_YEAR:
                c.add(Calendar.WEEK_OF_YEAR, amount);
                break;
            case Calendar.WEEK_OF_MONTH:
                c.add(Calendar.WEEK_OF_MONTH, amount);
                break;
            case Calendar.DATE:
                c.add(Calendar.DATE, amount);
                break;
            case Calendar.DAY_OF_YEAR:
                c.add(Calendar.DAY_OF_YEAR, amount);
                break;
            case Calendar.HOUR:
                c.add(Calendar.HOUR, amount);
                break;
            case Calendar.MINUTE:
                c.add(Calendar.MINUTE, amount);
                break;
            default:
                break;
        }
        return c.getTime();
    }

    /**
     * 获取当前日期的前一天
     *
     * @return
     */
    public static Date getBeforeDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 获取当前日期的后一天
     *
     * @return
     */
    public static Date getLastDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 获取当前日期的后十五天
     *
     * @return
     */
    public static String getNextFifteenDays(String formatStr) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.DAY_OF_MONTH, 15);
        return getDateToFormat(c.getTime(), formatStr);
    }

    /**
     * 获取当前日期的前三月
     *
     * @return
     */
    public static Date getBeforeMouth3() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -3);
        return c.getTime();
    }

    /**
     * 获取当前日期的前六月
     *
     * @return
     */
    public static Date getBeforeMouth6() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -6);
        return c.getTime();
    }

    /**
     * 把毫秒转换成时分秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strHour = hour < 10 ? "" + hour : "" + hour;//小时  
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟  
        String strSecond = second < 10 ? "0" + second : "" + second;//秒  
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒  
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strHour + "小时" + strMinute + " 分钟 " + strSecond + " 秒";
    }

    /**
     * 获取当年的第一天
     *

     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     *

     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static Date getCurrMonthFirst() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date currentMonthFirst = calendar.getTime();
        return currentMonthFirst;
    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    public static Date getCurrMonthLast() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date currentMonthLast = calendar.getTime();
        return currentMonthLast;
    }

    /**
     * 获取本周的第一天
     *
     * @return
     */
    public static Date getCurrWeekFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 获取本周的最后一天
     *
     * @return
     */
    public static Date getCurrWeekLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    /**
     * 获取本季度第一天
     *
     * @return
     */
    public static Date getCurrSeasonFirst() {
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        calendar.setTime(date);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH) + 1, true);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 获取本季度最后一天
     *
     * @return
     */
    public static Date getCurrSeasonLast() {
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        calendar.setTime(date);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH) + 1, false);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    // 季度一年四季， 春季：3月-5月， 夏季：6月-8月， 秋季：9月-11月， 冬季：12月-2月  
    private static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = {3, 6, 9, 12};
        if (!isQuarterStart) {
            months = new int[]{5, 8, 11, 2};
        }
        if (month >= 3 && month <= 5)
            return months[0];
        else if (month >= 6 && month <= 8)
            return months[1];
        else if (month >= 9 && month <= 11)
            return months[2];
        else
            return months[3];
    }

    /**
     * 获取指定年月的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年/月计算周数
     *
     * @param date
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> returnWeekCollection(String date) throws Exception {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        //返回结果集合
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        //开始日期
        String beginDate = null;
        //结束日期
        String endDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int count = 0;
        for (int i = 1; i <= days; i++) {
            //日期参数封装集合
            Map<String, Object> weekMap = new HashMap<String, Object>();
            //返回参数封装集合
            Map<String, Object> dateMap = new LinkedHashMap<String, Object>();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat1.parse(date + "-" + i);
            calendar.clear();
            calendar.setTime(date2);
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            if (k == 1) {// 若当天是周日
                count++;
                if (i - 6 <= 1) {
                    //本周开始日期
                    beginDate = date + "-" + "0" + 1;
                } else {
                    //本周开始日期
                    if ((i - 6) < 10) {
                        beginDate = date + "-" + "0" + (i - 6);
                    } else {
                        beginDate = date + "-" + (i - 6);
                    }
                }
                if (i < 10) {
                    endDate = date + "-" + "0" + i;
                } else {
                    endDate = date + "-" + i;
                }
            }
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日
                count++;
                //本周开始日期
                if ((i - k + 2) < 10) {
                    beginDate = date + "-" + "0" + (i - k + 2);
                    //本周结束日期
                    endDate = date + "-" + "0" + i;
                } else {
                    beginDate = date + "-" + (i - k + 2);
                    //本周结束日期
                    endDate = date + "-" + i;
                }
            }
            if (beginDate != null && endDate != null) {
                weekMap.put("weekCount", count);
                dateMap.put("endDate", endDate);
                dateMap.put("beginDate", beginDate);
                weekMap.put("dateMap", dateMap);
                resultList.add(weekMap);
                endDate = null;
                beginDate = null;
            }
        }
        return (List<T>) resultList;
    }
}
