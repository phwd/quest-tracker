package com.oculus.device;

public final class NetworkHeaders {
    public static String sUserAgent;

    public static String getUserAgent() {
        String str = sUserAgent;
        if (str != null) {
            return str;
        }
        throw new RuntimeException("You must call init before calling NetworkHeaders.getUserAgent.");
    }
}
