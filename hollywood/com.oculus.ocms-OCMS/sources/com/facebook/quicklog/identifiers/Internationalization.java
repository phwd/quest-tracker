package com.facebook.quicklog.identifiers;

public class Internationalization {
    public static final int FBT_STRING_RESOURCES_ANDROID = 30474253;
    public static final int FB_RESOURCES_LOADING_STRINGS_ANDROID = 30474250;
    public static final int LANGUAGE_SWITCHER_DURABLE_SYNC = 30474248;
    public static final int LANGUAGE_SWITCHER_PREFETCH_LOCALE = 30474249;
    public static final short MODULE_ID = 465;

    public static String getMarkerName(int i) {
        if (i == 13) {
            return "INTERNATIONALIZATION_FBT_STRING_RESOURCES_ANDROID";
        }
        switch (i) {
            case 8:
                return "INTERNATIONALIZATION_LANGUAGE_SWITCHER_DURABLE_SYNC";
            case 9:
                return "INTERNATIONALIZATION_LANGUAGE_SWITCHER_PREFETCH_LOCALE";
            case 10:
                return "INTERNATIONALIZATION_FB_RESOURCES_LOADING_STRINGS_ANDROID";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
