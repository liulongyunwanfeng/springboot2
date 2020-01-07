package com.django.springboot2.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    private static final Random RANDOM = new SecureRandom();
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 截取数字 【读取字符串中第一个连续的数字字符串，不包含后面不连续的数字】
     *
     * @param content
     * @return
     */
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static String getNumbersDe(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }

    /**
     * 截取非数字
     *
     * @param content
     * @return
     */
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 获取随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String generateNonceStr(int length) {
        char[] nonceChars = new char[length];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    public static String fomatScale(String value, int scale) {
        if (!isNullOrEmpty(value)) {
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(scale, RoundingMode.HALF_UP);
            return bd.toString();
        }
        return "";
    }

    public static boolean isNullOrEmpty(String val) {
        if (null == val || "" == val || " " == val || val.length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 将字符串第一个字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.get(field.getName()) == null) {
                continue;
            }
            if (Long.class.isAssignableFrom(field.getType())) {
                field.set(obj, Long.parseLong(map.get(field.getName()).toString()));
            } else if (Integer.class.isAssignableFrom(field.getType()))
                field.set(obj, Integer.parseInt(map.get(field.getName()).toString()));
            else if (Date.class.isAssignableFrom(field.getType()))
                field.set(obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get(field.getName()).toString()));
            else if (BigDecimal.class.isAssignableFrom(field.getType()))
                field.set(obj, new BigDecimal(map.get(field.getName()).toString()));
            else
                field.set(obj, map.get(field.getName()).toString());
        }

        return obj;
    }

    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static String getRqstUrl(Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();

        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (!isNullOrEmpty(builder.toString())) {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }


    public static int parseInt(String s, int defaultValue) {
        if (s == null)
            return defaultValue;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException x) {
            return defaultValue;
        }
    }

    public static String stringToGbk(String string) throws UnsupportedEncodingException {
        byte[] bytes = new byte[string.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            byte high = Byte.parseByte(string.substring(i * 2, i * 2 + 1), 16);
            byte low = Byte.parseByte(string.substring(i * 2 + 1, i * 2 + 2), 16);
            bytes[i] = (byte) (high << 4 | low);
        }
        String result = new String(bytes, "gbk");
        return result;

    }

    public static String getStarString(String content, int begin, int end) {

        if (begin >= content.length() || begin < 0) {
            return content;
        }
        if (end >= content.length() || end < 0) {
            return content;
        }
        if (begin >= end) {
            return content;
        }
        String starStr = "";
        for (int i = begin; i < end; i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, begin) + starStr + content.substring(end, content.length());

    }


    /**
     * 从json对象集合表达式中得到一个java对象列表
     *

     * @return
     */
   /* @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        T bean;
        int size = jsonArray.size();
        List<T> list = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            jsonObject = jsonArray.getJSONObject(i);
            bean = (T) JSONObject.toBean(jsonObject, beanClass);
            list.add(bean);
        }
        return list;
    }
    public static void main(String[] args) {
        String id = "oSVbE5D4GV_o5JbMSa_diiiers_E";
        System.out.print(getStarString(id, 5, id.length() - 5));
    }
*/

}
