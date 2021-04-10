package com.google.common.base;

public final class Ascii {
    public static String toLowerCase(String string) {
        int length = string.length();
        int i = 0;
        while (i < length) {
            if (isUpperCase(string.charAt(i))) {
                char[] chars = string.toCharArray();
                while (i < length) {
                    char c = chars[i];
                    if (isUpperCase(c)) {
                        chars[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(chars);
            }
            i++;
        }
        return string;
    }

    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }
}
