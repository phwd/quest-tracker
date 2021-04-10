package com.facebook.quicklog.identifiers;

public class WaPerf {
    public static final int CAMERA_OPEN = 24772612;
    public static final int CHAT_KEYBOARD_OPEN = 24785848;
    public static final int CHAT_LIST_SCROLL = 24781737;
    public static final int CHAT_OPEN = 24772613;
    public static final int COLD_START_ANDROID = 24772609;
    public static final int CONTACT_OPEN = 24779646;
    public static final int FIRST_FTS_RESULT = 24774993;
    public static final int HOT_START_ANDROID = 24772611;
    public static final int IMAGE_OPEN = 24774469;
    public static final short MODULE_ID = 378;
    public static final int SCROLL_PERF = 24772614;
    public static final int WARM_START_ANDROID = 24772610;

    public static String getMarkerName(int i) {
        if (i == 1861) {
            return "WA_PERF_IMAGE_OPEN";
        }
        if (i == 2385) {
            return "WA_PERF_FIRST_FTS_RESULT";
        }
        if (i == 7038) {
            return "WA_PERF_CONTACT_OPEN";
        }
        if (i == 9129) {
            return "WA_PERF_CHAT_LIST_SCROLL";
        }
        if (i == 13240) {
            return "WA_PERF_CHAT_KEYBOARD_OPEN";
        }
        switch (i) {
            case 1:
                return "WA_PERF_COLD_START_ANDROID";
            case 2:
                return "WA_PERF_WARM_START_ANDROID";
            case 3:
                return "WA_PERF_HOT_START_ANDROID";
            case 4:
                return "WA_PERF_CAMERA_OPEN";
            case 5:
                return "WA_PERF_CHAT_OPEN";
            case 6:
                return "WA_PERF_SCROLL_PERF";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
