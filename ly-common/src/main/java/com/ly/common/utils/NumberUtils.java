package com.ly.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: HuYi.Zhang
 * @create: 2018-04-25 09:13
 **/
public class NumberUtils {

    public static boolean isInt(Double num) {
        return num.intValue() == num;
    }

    /**
     * 判断字符串是否是数值格式
     *
     * @return boolean
     */
    public static boolean isDigit(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return str.matches("^\\d+\\.?\\d+$");
    }

    public static double toDouble(String s) {
        if (s == null) {
            return 0;
        }
        if (!isDigit(s)) {
            return 0;
        }
        return Double.valueOf(s);
    }

    /**
     * 将一个小数精确到指定位数
     *
     * @return 精确后的数字
     */
    public static double scale(double num, int scale) {
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 从字符串中根据正则表达式寻找
     *
     * @return 找到的数字数组
     */
    public static Double[] searchNumber(String value, String regex) {
        List<Double> doubles = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            for (int i = 1; i <= result.groupCount(); i++) {
                doubles.add(Double.valueOf(result.group(i)));
            }
        }
        return doubles.toArray(new Double[doubles.size()]);
    }

    /**
     * 生成指定位数的随机数字
     *
     * @return 随机数
     */
    public static String generateCode(int len) {
        len = Math.min(len, 8);
        int min = Double.valueOf(Math.pow(10, len - 1)).intValue();
        int num = new Random().nextInt(Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0, len);
    }
}
