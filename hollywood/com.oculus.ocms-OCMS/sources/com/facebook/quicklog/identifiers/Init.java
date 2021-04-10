package com.facebook.quicklog.identifiers;

public class Init {
    public static final int APP_INIT = 4003988;
    public static final int BACKGROUND_START = 3997719;
    public static final int COLD_START = 3997703;
    public static final int COLD_START_EXPERIMENTAL = 3997709;
    public static final int HIGH_PRI_BG_THREAD_MARKER = 3997697;
    public static final int HOT_START = 3997705;
    public static final int HOT_START_EXPERIMENTAL = 3997711;
    public static final int LOW_PRI_BG_THREAD_MARKER = 3997700;
    public static final short MODULE_ID = 61;
    public static final int NON_CRITICAL_AFTER_COLD_START = 3997702;
    public static final int NON_CRITICAL_AFTER_UI_LOADED = 3997701;
    public static final int POST_CHROME_ANDROID = 3997707;
    public static final int POST_CHROME_ANDROID_SHORT = 3997715;
    public static final int POST_CHROME_ANDROID_SHORT_STALL = 3997716;
    public static final int POST_CHROME_ANDROID_STALL = 3997714;
    public static final int POST_CHROME_ANDROID_STALL_FOREGROUND_SESSION = 3997722;
    public static final int POST_CHROME_ANDROID_WATCHDOG = 3997718;
    public static final int TIME_SPENT_BACKGROUND_BR_WAIT_TIME = 3997725;
    public static final int TIME_SPENT_FOREGROUND_BR_WAIT_TIME = 3997721;
    public static final int WARM_START = 3997704;
    public static final int WARM_START_EXPERIMENTAL = 3997710;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "INIT_HIGH_PRI_BG_THREAD_MARKER";
        }
        if (i == 11) {
            return "INIT_POST_CHROME_ANDROID";
        }
        if (i == 29) {
            return "INIT_TIME_SPENT_BACKGROUND_BR_WAIT_TIME";
        }
        if (i == 6292) {
            return "INIT_APP_INIT";
        }
        if (i == 22) {
            return "INIT_POST_CHROME_ANDROID_WATCHDOG";
        }
        if (i == 23) {
            return "INIT_BACKGROUND_START";
        }
        if (i == 25) {
            return "INIT_TIME_SPENT_FOREGROUND_BR_WAIT_TIME";
        }
        if (i == 26) {
            return "INIT_POST_CHROME_ANDROID_STALL_FOREGROUND_SESSION";
        }
        switch (i) {
            case 4:
                return "INIT_LOW_PRI_BG_THREAD_MARKER";
            case 5:
                return "INIT_NON_CRITICAL_AFTER_UI_LOADED";
            case 6:
                return "INIT_NON_CRITICAL_AFTER_COLD_START";
            case 7:
                return "INIT_COLD_START";
            case 8:
                return "INIT_WARM_START";
            case 9:
                return "INIT_HOT_START";
            default:
                switch (i) {
                    case 13:
                        return "INIT_COLD_START_EXPERIMENTAL";
                    case 14:
                        return "INIT_WARM_START_EXPERIMENTAL";
                    case 15:
                        return "INIT_HOT_START_EXPERIMENTAL";
                    default:
                        switch (i) {
                            case 18:
                                return "INIT_POST_CHROME_ANDROID_STALL";
                            case 19:
                                return "INIT_POST_CHROME_ANDROID_SHORT";
                            case 20:
                                return "INIT_POST_CHROME_ANDROID_SHORT_STALL";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
