package com.lot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 获取当前日期
     *
     * @return date
     */
    public static Date currentDate() {
        return Date.from(Instant.now());
    }

    /**
     * 获取当前日期 （yyyy-MM-dd）
     *
     * @return String
     */
    public static String getCurrentDate() {
        return LocalDate.now().toString();
    }

    /**
     * 获取当前日期 （yyyy-MM-dd HH:mm:ss）
     *
     * @return String
     */
    public static String getCurrentDatetime() {
        return TimeFormat.LONG_DATE_PATTERN_LINE.formatter.format(LocalDateTime.now());
    }

    /**
     * 获取当前日期 （yyyyMMddHHmmss）
     *
     * @return String
     */
    public static String getCurrentDatetimeStr() {
        return TimeFormat.LONG_DATE_PATTERN_NO_SPLIT.formatter.format(LocalDateTime.now());
    }

    /**
     * 获取当前日期 （yyyy-MM-dd HH:mm:ss.SSS）
     *
     * @return String
     */
    public static String getCurrentDateWithMilliSecond() {
        return TimeFormat.LONG_DATE_PATTERN_WITH_MILSEC_LINE.formatter.format(LocalDateTime.now());
    }


    /**
     * String 转 Date （yyyy-mm-dd HH:mm:ss）
     *
     * @param timeStr
     * @return
     */
    public static Date parseDateTime(String timeStr) {
        return localDateTimeToDate(LocalDateTime.parse(timeStr, TimeFormat.LONG_DATE_PATTERN_LINE.formatter));
    }

    /**
     * String 转 Date(yyyy-mm-dd）
     *
     * @param timeStr
     * @return
     */
    public static Date parseDate(String timeStr) {
        return localDateToDate(LocalDate.parse(timeStr, TimeFormat.SHORT_DATE_PATTERN_LINE.formatter));
    }

    /**
     * String 转 Date (不能设置 hh, mm ,ss)
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static Date parseDate(String timeStr, TimeFormat format) {
        return localDateToDate(LocalDate.parse(timeStr, format.formatter));
    }

    /**
     * String 转 LocalDateTime
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String timeStr, TimeFormat format) {
        return LocalDateTime.parse(timeStr, format.formatter);
    }

    /**
     * String 转 Date (设置 hh, mm ,ss)
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static Date parseDateTime(String timeStr, TimeFormat format) {
        return localDateTimeToDate(LocalDateTime.parse(timeStr, format.formatter));
    }

    /**
     * Date 转 String （yyyy-MM-dd）
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        return formatDate(date, TimeFormat.SHORT_DATE_PATTERN_LINE);
    }

    /**
     * Date 转 String （yyyy-MM-dd HH:mm:ss）
     *
     * @param date
     * @return String
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, TimeFormat.LONG_DATE_PATTERN_LINE);
    }

    /**
     * Date 转 String （格式使用format）
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, TimeFormat format) {
        return format.formatter.format(dateToLocalDateTime(date));
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDate 转 Date
     *
     * @param localDate
     * @return
     */
    private static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 时间格式
     */
    public enum TimeFormat {
        /**
         * 短日期格式
         * <p>
         * SHORT_DATE_PATTERN_YEAR_MONTH 只能用于format
         */
        SHORT_DATE_PATTERN_YEAR_MONTH("yyyyMM"),
        SHORT_DATE_PATTERN_YEAR_MONTH_WITH_BAR("yyyy-MM"),
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),
        SHORT_DATE_PATTERN_WORD("yyyy年MM月dd日"),

        SHORT_DATE_PATTERN_LINE_MD("yyyy-M-d"),
        SHORT_DATE_PATTERN_WORD_MD("yyyy年M月d日"),

        /**
         * 短时间
         */
        SHORT_TIME_NO_SECOND("HH:mm"),

        /**
         * 长时间格式
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),
        LONG_DATE_PATTERN_SLASH_NO_SECONDS("yyyy/MM/dd HH:mm"),
        LONG_DATE_PATTERN_LINE_NO_SECONDS("yyyy-MM-dd HH:mm"),
        LONG_DATE_PATTERN_NO_SPLIT("yyyyMMddHHmmss"),
        LONG_DATE_PATTERN_LINE_NO_YEAR_SECOND("MM-dd HH:mm"),
        LONG_DATE_PATTERN_WORD_NO_SECONDS("yyyy年MM月dd日 HH:mm"),

        LONG_DATE_PATTERN_LINE_NO_SECONDS_MDHM("yyyy-M-d H:m"),
        LONG_DATE_PATTERN_WORD_NO_SECONDS_MDHM("yyyy年M月d日 H:m"),

        /**
         * 长时间格式 带毫秒
         */
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NONE2("yyyy/MM/yyyyMMddHHmmssSSS"),
        LONG_DATE_PATTERN_WITH_MILSEC_NO_SPLIT("yyyyMMddHHmmssSSS");

        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }


    public static Timestamp toDateAuto(String arg) {
        if (arg == null) {
            return null;
        }

        try {
            int length = arg.length();
            if (length == 4) {
                return Timestamp.valueOf(arg + "-01-01 " + "00:00:00");
            } else if (length == 7) {
                return Timestamp.valueOf(arg + "-01 " + "00:00:00");
            } else if (length == 10) {
                return Timestamp.valueOf(arg + " 00:00:00");
            } else if (length == 13) {
                return Timestamp.valueOf(arg + ":00:00");
            } else if (length == 16) {
                return Timestamp.valueOf(arg + ":00");
            } else if (length >= 19) {
                return Timestamp.valueOf(arg);
            }
        } catch (Throwable e) {
            String pattern = "Exception while format {0} to Date";
            String msg = MessageFormat.format(pattern, arg);
            logger.error(msg, e);
        }
        return null;
    }


    /**
     * @param datetime
     * @return
     * @Title getDayBegin
     * @Description 获取该日期00:00时间
     */
    public static Timestamp getDayBegin(Timestamp datetime) {
        if (datetime == null) {
            return null;
        }

        Timestamp dayBegin = DateUtils.toDateAuto(DateUtils.yyyyMMdd(datetime) + " 00:00:00.000");

        return dayBegin;
    }

    /**
     * @param datetime
     * @return
     * @Title getDayEnd
     * @Description 获取该日期23:59时间
     */
    public static Timestamp getDayEnd(Timestamp datetime) {
        if (datetime == null) {
            return null;
        }

        Timestamp dayEnd = DateUtils.toDateAuto(DateUtils.yyyyMMdd(datetime) + " 23:59:59.999");

        return dayEnd;
    }

    public static Timestamp yyyyMMddHH(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str + ":00:00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String yyyyMMddHHmmssSSS(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(time);
    }

    public static Timestamp yyyyMMddHHmmssSSS(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String yyyyMMddHHmmss(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static Timestamp yyyyMMddHHmmss(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String yyyyMMddHHmmssSimple(Date time) {
        if (time == null) {
            return null;
        }
        return new SimpleDateFormat("yyyyMMddHHmmss").format(time);
    }

    public static Timestamp yyyyMMddHHmmssSimple(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return new Timestamp(new SimpleDateFormat("yyyyMMddHHmmSSS").parse(str).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String yyyyMMddHHmm(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);
    }

    public static Timestamp yyyyMMddHHmm(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str + ":00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String yyyyMM(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM").format(time);
    }

    public static String yyyyMMInChinese(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy年MM月").format(time);
    }

    public static Timestamp yyyyBegin(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            return Timestamp.valueOf(str + "01-01 00:00:00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static Timestamp yyyyMMBegin(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str + "-01 00:00:00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static String yyyyMMdd(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    public static String yyyyMMddInChinese(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy年MM月dd日").format(time);
    }

    public static String MMdd(Date time) {
        if (time == null) {
            return "";
        }
        return new SimpleDateFormat("MM-dd").format(time);
    }

    public static Timestamp yyyyMMdd(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        try {
            return Timestamp.valueOf(str + " 00:00:00");
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static Timestamp getNow(){
        return new Timestamp(System.currentTimeMillis());
    }

}
