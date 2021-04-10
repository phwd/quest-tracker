package com.android.okhttp.internal;

public final class Version {
    public static String userAgent() {
        String property = System.getProperty("http.agent");
        if (property != null) {
            return property;
        }
        return "Java" + System.getProperty("java.version");
    }
}
