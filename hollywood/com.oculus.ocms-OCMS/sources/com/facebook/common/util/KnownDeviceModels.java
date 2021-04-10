package com.facebook.common.util;

import android.os.Build;

public class KnownDeviceModels {
    public static final String AMAZON_KINDLE_FIRE_GEN1 = "Kindle Fire";
    public static final String AMAZON_KINDLE_FIRE_HD7_GEN2 = "KFTT";
    public static final String AMAZON_KINDLE_FIRE_HD7_GEN3 = "KFSOWI";
    public static final String AMAZON_KINDLE_FIRE_HD7_GEN3_WAN = "KFTHWA";
    public static final String AMAZON_KINDLE_FIRE_HD7_GEN3_WIFI = "KFTHWI";
    public static final String AMAZON_KINDLE_FIRE_HD8_9_GEN1_WAN = "KFJWA";
    public static final String AMAZON_KINDLE_FIRE_HD8_9_GEN1_WIFI = "KFJWI";
    public static final String AMAZON_KINDLE_FIRE_HD8_9_GEN2_WAN = "KFAPWA";
    public static final String AMAZON_KINDLE_FIRE_HD8_9_GEN2_WIFI = "KFAPWI";
    public static final String HTC_CHACHA = "HTC Status";

    public static boolean isDeviceModel(String str) {
        return Build.MODEL.contentEquals(str);
    }

    public static boolean isDeviceModelOneOf(String... strArr) {
        for (String str : strArr) {
            if (isDeviceModel(str)) {
                return true;
            }
        }
        return false;
    }
}
