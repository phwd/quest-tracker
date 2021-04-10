package com.facebook.quicklog.identifiers;

public class GemstoneSettings {
    public static final int GEMSTONE_SETTING_TTRC_ANDROID = 50855940;
    public static final int GEMSTONE_SETTING_TTRC_IOS = 50855941;
    public static final short MODULE_ID = 776;

    public static String getMarkerName(int i) {
        return i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "GEMSTONE_SETTINGS_GEMSTONE_SETTING_TTRC_IOS" : "GEMSTONE_SETTINGS_GEMSTONE_SETTING_TTRC_ANDROID";
    }
}
