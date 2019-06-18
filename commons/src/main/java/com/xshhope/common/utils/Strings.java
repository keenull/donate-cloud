package com.xshhope.common.utils;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @author xshhope
 */
public final class Strings {
    private static final String DELIM_STR = "{}";
    public static final String EMPTY = "";

    public Strings() {
    }

    public static final boolean isEmpty(String string) {
        return Langs.isNull(string) || string.length() == 0;
    }

    public static final boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static final boolean isBlank(String string) {
        return !isNotBlank(string);
    }

    public static final boolean isNotBlank(String string) {
        return Langs.isNotNull(string) && string.trim().length() > 0;
    }

    public static final String repeat(char ch, int repeat) {
        Args.notNull(ch, "repeat char must not be null");
        if (repeat <= 0) {
            return "";
        } else {
            char[] buf = new char[repeat];

            for (int k = 0; k < repeat; ++k) {
                buf[k] = ch;
            }

            return new String(buf);
        }
    }

    public static final String center(String string, char ch, int length) {
        if (string.length() < length) {
            int pad = (length - string.length()) / 2;
            return repeat(ch, pad) + string + repeat(ch, pad);
        } else {
            return string;
        }
    }

    public static final String leftPad(String string, int size) {
        Objects.requireNonNull(string);
        return string.length() < size ? repeat(' ', size - string.length()) + string : string;
    }

    public static final String rightPad(String string, int size) {
        Objects.requireNonNull(string);
        return string.length() < size ? string + repeat(' ', size - string.length()) : string;
    }

    public static final boolean contains(String plainText, String sk) {
        return !Objects.isNull(plainText) && !Objects.isNull(sk) ? plainText.toUpperCase().contains(sk.toUpperCase()) : false;
    }

    /**
     * 随机4位数生成
     */
    public static String getRandomNum() {

        Random random = new Random();
        int end2 = random.nextInt(9999);
        //如果不足两位前面补0
        String str = String.format("%04d", end2);
        return str;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将utf-8编码的汉字转为中文
     *
     * @param str
     * @return
     * @author zhaoqiang
     */
    public static String utf8Decoding(String str) {
        String result = str;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
