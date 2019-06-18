package com.xshhope.common.utils;

/**
 * @author xshhope
 */
public final class Args {
    public Args() {
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assert Fail] - this expression must be true.");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assert Fail] - this expression must be false.");
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assert Fail] - this object must not be null.");
    }

    public static void notNull(Object object, String message) {
        if (Langs.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String text) {
        notBlank(text, "[Assert Fail] - this string text must not be blank.");
    }

    public static void notBlank(String text, String message) {
        if (Strings.isBlank(text)) {
            throw new IllegalArgumentException(message);
        }
    }
}
