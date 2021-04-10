package com.oculus.horizon.testdata;

import androidx.annotation.VisibleForTesting;
import com.google.common.base.Strings;

public class TestData {
    public static final boolean DISABLE_HEARTBEATS = false;
    public static final boolean DISABLE_LIBRARY_FETCHING = false;
    public static final boolean DISABLE_STORE_FETCHING = false;
    @VisibleForTesting
    public static final String EMAIL = "";
    @VisibleForTesting
    public static final String FIRST_NAME = "";
    public static final boolean FORCE_ROADBLOCK_CONTROLLER = false;
    public static final boolean FORCE_ROADBLOCK_HSW = false;
    public static final boolean FORCE_ROADBLOCK_PAYMENT_METHOD = false;
    public static final boolean FORCE_ROADBLOCK_PIN = false;
    public static final boolean FORCE_SAMSUNG_BUNDLE = false;
    @VisibleForTesting
    public static final String LAST_NAME = "";
    @VisibleForTesting
    public static final int NOUNCE = 0;
    public static final String TEST_EMAIL;
    public static final String TEST_FIRST_NAME = "";
    public static final String TEST_LAST_NAME = "";
    public static final String TEST_PASSWORD;
    public static final String TEST_PIN;
    public static final String TEST_USER;
    @VisibleForTesting
    public static final String USER = "";

    static {
        String str;
        String str2;
        String str3;
        String str4 = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(str4);
        if (isNullOrEmpty) {
            str = str4;
        } else {
            str = "+0@oculus.com";
        }
        TEST_EMAIL = str;
        if (isNullOrEmpty) {
            str2 = str4;
        } else {
            str2 = "0";
        }
        TEST_USER = str2;
        if (!isNullOrEmpty) {
            str4 = "n0t3st!!";
        }
        TEST_PASSWORD = str4;
        if (isNullOrEmpty) {
            str3 = null;
        } else {
            str3 = "1234";
        }
        TEST_PIN = str3;
    }
}
