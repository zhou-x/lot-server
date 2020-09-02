package com.lot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author
 */
public abstract class StringUtils extends org.springframework.util.StringUtils {

    /**
     * 定义下划线
     */
    private static final char UNDERLINE = '_';

    /**
     * str 为 null 或 "" 时返回false，否则返回true
     *
     * @param content
     * @return
     */
    public static boolean isNotEmpty(String content) {
        return !isEmpty(content);
    }

    /**
     * 查询符合的手机号码
     *
     * @param content
     */
    public static String getMobilePhoneStr(String content) {
        List<String> ph = getMobilePhone(content);
        if (ph != null) {
            String result = ph.toString();
            return result.substring(1, result.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * 查询符合的手机号码
     *
     * @param content
     */
    public static List<String> getMobilePhone(String content) {
        if (StringUtils.isNotEmpty(content)) {
            Pattern pattern = Pattern.compile("((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}");
            Matcher matcher = pattern.matcher(content);

            List<String> result = new ArrayList<String>();
            while (matcher.find()) {
                result.add(matcher.group());
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * 查询符合的固定电话
     *
     * @param content
     * @return
     */
    public static String getTelPhoneStr(String content) {
        String result = getTelephone(content).toString();
        return result.substring(1, result.length() - 1);
    }

    /**
     * 查询符合的固定电话
     *
     * @param content
     * @return
     */
    public static List<String> getTelephone(String content) {
        Pattern pattern = Pattern.compile("(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)|\\d{7,8}");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 根据键值对填充字符串，如("hello ${name}",{name:"xiaoming"})
     * 输出：
     *
     * @param content
     * @param map
     * @return
     */
    public static String renderString(String content, Map<String, Object> map) {
        Set<Entry<String, Object>> sets = map.entrySet();
        for (Entry<String, Object> entry : sets) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            if (entry.getValue() != null) {
                content = matcher.replaceAll(entry.getValue().toString());
            } else {
                content = matcher.replaceAll("");
            }
        }
        return content;
    }

    /**
     * 获取航班号
     *
     * @param content
     * @return
     */
    public static String getFltNumStr(String content) {
        String result = getFltNum(content).toString();
        return result.substring(1, result.length() - 1);
    }

    /**
     * 获取航班号
     *
     * @param content
     * @return
     */
    public static List<String> getFltNum(String content) {
        Pattern pattern = Pattern.compile("([39]{1}[a-zA-Z]{1}|[a-zA-Z]{1}[2458a-zA-Z]{1})\\d{3,4}");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 匹配时间HH:mm
     *
     * @param content
     * @return
     */
    public static String getTimeStr(String content) {
        String result = getTime(content).toString();
        return result.substring(1, result.length() - 1);
    }

    /**
     * 匹配时间HH:mm
     *
     * @param content
     * @return
     */
    public static List<String> getTime(String content) {
        Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3])[:：;]([0-5][0-9])+");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group().replace("：", ":").replace(";", ":"));
        }
        return result;
    }

    /**
     * 匹配火车车次
     *
     * @param content
     * @return
     */
    public static String getTrainNumStr(String content) {
        String result = getTrainNum(content).toString();
        return result.substring(1, result.length() - 1);
    }

    /**
     * 匹配火车车次
     *
     * @param content
     * @return
     */
    public static List<String> getTrainNum(String content) {
        Pattern pattern = Pattern.compile("[LKTZDGC]{1}[0-9]{1,4}");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 从字符串中找到日期数字
     *
     * @param content
     * @return
     */
    public static List<String> getNumFromString(String content) {
        Pattern pattern = Pattern.compile("[0-9]{1,2}");
        Matcher matcher = pattern.matcher(content);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * 返回默认字符串
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String defaultString(String str1, String str2) {
        if (StringUtils.isNotEmpty(str1)) {
            return str1;
        } else {
            return str2;
        }
    }


    /**
     * String为空判断(不允许空格)
     *
     * @param str
     * @return boolean
     * @author dolyw.com
     * @date 2018/9/4 14:49
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * String不为空判断(不允许空格)
     *
     * @param str
     * @return boolean
     * @author dolyw.com
     * @date 2018/9/4 14:51
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Byte数组为空判断
     *
     * @param bytes
     * @return boolean
     * @author dolyw.com
     * @date 2018/9/4 15:39
     */
    public static boolean isNull(byte[] bytes) {
        // 根据byte数组长度为0判断
        return bytes == null || bytes.length == 0;
    }

    /**
     * Byte数组不为空判断
     *
     * @param bytes
     * @return boolean
     * @author dolyw.com
     * @date 2018/9/4 15:41
     */
    public static boolean isNotNull(byte[] bytes) {
        return !isNull(bytes);
    }

    /**
     * 驼峰转下划线工具
     *
     * @param param
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 14:52
     */
    public static String camelToUnderline(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     *
     * @param param
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 14:52
     */
    public static String underlineToCamel(String param) {
        if (isNotBlank(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = param.charAt(i);
                if (c == 95) {
                    i++;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 在字符串两周添加''
     *
     * @param param
     * @return java.lang.String
     * @author dolyw.com
     * @date 2018/9/4 14:53
     */
    public static String addSingleQuotes(String param) {
        return "\'" + param + "\'";
    }

    public static String substringAfter(String token, String jwtSeparator) {
        return token.substring(jwtSeparator.length(), token.length());
    }

}
