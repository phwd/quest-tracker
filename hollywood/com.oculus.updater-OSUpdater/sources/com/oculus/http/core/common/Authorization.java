package com.oculus.http.core.common;

public final class Authorization {
    public static String generate(String str) {
        return "Bearer " + str;
    }
}
