package com.facebook.internal;

public class InternalSettings {
    public static volatile String mCustomUserAgent;

    public static String getCustomUserAgent() {
        return mCustomUserAgent;
    }

    public static void setCustomUserAgent(String str) {
        mCustomUserAgent = str;
    }
}
