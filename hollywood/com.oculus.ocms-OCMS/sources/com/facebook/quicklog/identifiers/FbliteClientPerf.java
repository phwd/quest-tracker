package com.facebook.quicklog.identifiers;

public class FbliteClientPerf {
    public static final int FBLITE_APP_UPTIME_EVENT = 21299213;
    public static final int FBLITE_DICTIONARY_COMPRESSION_STATS = 21299207;
    public static final int FBLITE_FEED_START_UP_PERF = 21299214;
    public static final int FBLITE_FIZZ_SOCKET = 21299219;
    public static final int FBLITE_MEMORY_STATS = 21299209;
    public static final int FBLITE_NAVIGATION_TTI = 21299217;
    public static final int FBLITE_QPL_TEST = 21299210;
    public static final int FBLITE_SCREEN_TTI = 21299202;
    public static final int FBLITE_SCROLL_PERF_QPL = 21299208;
    public static final int FBLITE_SCROLL_PERF_QPL_FULL = 21299211;
    public static final int FBLITE_SEQUENCE_TTI = 21299206;
    public static final int FBLITE_SESSION_EVENT = 21299201;
    public static final int IG_CARBON_SINGLE_SESSION_EVENT = 21313612;
    public static final int INITIATING_START_EVENT = 21299220;
    public static final short MODULE_ID = 325;
    public static final int NAVIGATION_TTI = 21299216;
    public static final int SINGLE_SESSION_EVENT = 21299203;
    public static final int TEST_SINGLE_EVENT_FBLITE = 21299204;
    public static final int WEBLITE_SINGLE_SESSION_EVENT = 21303865;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "FBLITE_CLIENT_PERF_FBLITE_SESSION_EVENT";
        }
        if (i == 2) {
            return "FBLITE_CLIENT_PERF_FBLITE_SCREEN_TTI";
        }
        if (i == 3) {
            return "FBLITE_CLIENT_PERF_SINGLE_SESSION_EVENT";
        }
        if (i == 4) {
            return "FBLITE_CLIENT_PERF_TEST_SINGLE_EVENT_FBLITE";
        }
        if (i == 13) {
            return "FBLITE_CLIENT_PERF_FBLITE_APP_UPTIME_EVENT";
        }
        if (i == 14) {
            return "FBLITE_CLIENT_PERF_FBLITE_FEED_START_UP_PERF";
        }
        if (i == 16) {
            return "FBLITE_CLIENT_PERF_NAVIGATION_TTI";
        }
        if (i == 17) {
            return "FBLITE_CLIENT_PERF_FBLITE_NAVIGATION_TTI";
        }
        if (i == 19) {
            return "FBLITE_CLIENT_PERF_FBLITE_FIZZ_SOCKET";
        }
        if (i == 20) {
            return "FBLITE_CLIENT_PERF_INITIATING_START_EVENT";
        }
        if (i == 4665) {
            return "FBLITE_CLIENT_PERF_WEBLITE_SINGLE_SESSION_EVENT";
        }
        if (i == 14412) {
            return "FBLITE_CLIENT_PERF_IG_CARBON_SINGLE_SESSION_EVENT";
        }
        switch (i) {
            case 6:
                return "FBLITE_CLIENT_PERF_FBLITE_SEQUENCE_TTI";
            case 7:
                return "FBLITE_CLIENT_PERF_FBLITE_DICTIONARY_COMPRESSION_STATS";
            case 8:
                return "FBLITE_CLIENT_PERF_FBLITE_SCROLL_PERF_QPL";
            case 9:
                return "FBLITE_CLIENT_PERF_FBLITE_MEMORY_STATS";
            case 10:
                return "FBLITE_CLIENT_PERF_FBLITE_QPL_TEST";
            case 11:
                return "FBLITE_CLIENT_PERF_FBLITE_SCROLL_PERF_QPL_FULL";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
