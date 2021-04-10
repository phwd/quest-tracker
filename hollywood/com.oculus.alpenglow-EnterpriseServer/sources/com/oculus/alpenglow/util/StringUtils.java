package com.oculus.alpenglow.util;

public class StringUtils {
    public static String A00(String str) {
        int length;
        if (str == null || (length = str.length()) < 2 || !str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        return str.substring(1, length - 1);
    }
}
