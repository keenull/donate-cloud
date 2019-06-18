package com.xshhope.common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author xshhope
 */
public final class Langs {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public Langs() {
    }

    public static final boolean isNull(Object object) {
        return object == null;
    }

    public static final boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /*public static final boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() == 0;
    }*/


    public static boolean isEmpty(Object aObj) {
        if (aObj instanceof String) {
            return isEmpty((String) aObj);
        } else if (aObj instanceof Long) {
            return isEmpty((Long) aObj);
        } else if (aObj instanceof java.util.Date) {
            return isEmpty((java.util.Date) aObj);
        } else if (aObj instanceof Collection) {
            return isEmpty((Collection) aObj);
        } else if (aObj instanceof Map) {
            return isEmpty((Map) aObj);
        } else if (aObj != null && aObj.getClass().isArray()) {
            return isEmptyArray(aObj);
        } else {
            return isNull(aObj);
        }
    }

    private static boolean isEmptyArray(Object array) {
        int length = 0;
        if (array instanceof int[]) {
            length = ((int[]) array).length;
        } else if (array instanceof byte[]) {
            length = ((byte[]) array).length;
        } else if (array instanceof short[]) {
            length = ((short[]) array).length;
        } else if (array instanceof char[]) {
            length = ((char[]) array).length;
        } else if (array instanceof float[]) {
            length = ((float[]) array).length;
        } else if (array instanceof double[]) {
            length = ((double[]) array).length;
        } else if (array instanceof long[]) {
            length = ((long[]) array).length;
        } else if (array instanceof boolean[]) {
            length = ((boolean[]) array).length;
        } else {
            length = ((Object[]) array).length;
        }
        if (length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(java.util.Date aDate) {
        if (aDate == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Long aLong) {
        if (aLong == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Map m) {
        if (m == null || m.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection c) {
        if (c == null || c.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String aStr) {
        if (aStr == null || aStr.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public static final boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static final boolean isBlank(String stringText) {
        return Strings.isBlank(stringText);
    }

    public static final boolean isNotBlank(String string) {
        return Strings.isNotBlank(string);
    }

    public static final String toString(Object object) {
        if (isNull(object)) {
            return "@null";
        } else if (!object.getClass().isArray()) {
            return object.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            int len = Array.getLength(object);

            for (int k = 0; k < len; ++k) {
                sb.append(toString(Array.get(object, k))).append(',');
            }

            return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
        }
    }
}
