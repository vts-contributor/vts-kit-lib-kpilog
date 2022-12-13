package com.viettel.vtskit.logs.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }

    public static String safeString(String input) {
        if (isNullOrEmpty(input)) {
            return "";
        }
        return input;
    }
}
