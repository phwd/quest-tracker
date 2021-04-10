package com.facebook.quicklog.identifiers;

public class Reliability {
    public static final int ALEX_N_TEST = 21364740;
    public static final int APPSTATELOGGER_WRITE = 21364737;
    public static final int EARLY_ACTIVITY_TRANSITION_LAUNCH = 21364753;
    public static final int EARLY_ACTIVITY_TRANSITION_STOP = 21364754;
    public static final int HOME_TASK_SWITCHER_PRESSED = 21364755;
    public static final int LONG_STALL_TRACE_ANDROID = 21364741;
    public static final int MEMORY_TRIM = 21364746;
    public static final short MODULE_ID = 326;
    public static final int NAVIGATION_ANDROID = 21364744;
    public static final int NAVIGATION_SESSION_WITH_MEMORY = 21371299;
    public static final int NAVIGATION_WITH_LIFECYCLE = 21379434;
    public static final int PERIODIC_MEMORY_INFO = 21364745;
    public static final int SOFT_ERROR = 21364743;
    public static final int SOFT_ERROR_AGGREGATED = 21364751;
    public static final int TTRC_ABSTRACT = 21385285;
    public static final int TTRC_FAILURE = 21364749;
    public static final int TTRC_FAILURE_ANDROID = 21364738;
    public static final int TTRC_LONG_CANCEL = 21364750;
    public static final int TTRC_LONG_CANCEL_ANDROID = 21364739;
    public static final int UI_THREAD_LAG_ANDROID = 21364742;

    public static String getMarkerName(int i) {
        if (i == 6563) {
            return "RELIABILITY_NAVIGATION_SESSION_WITH_MEMORY";
        }
        if (i == 14698) {
            return "RELIABILITY_NAVIGATION_WITH_LIFECYCLE";
        }
        if (i == 20549) {
            return "RELIABILITY_TTRC_ABSTRACT";
        }
        switch (i) {
            case 1:
                return "RELIABILITY_APPSTATELOGGER_WRITE";
            case 2:
                return "RELIABILITY_TTRC_FAILURE_ANDROID";
            case 3:
                return "RELIABILITY_TTRC_LONG_CANCEL_ANDROID";
            case 4:
                return "RELIABILITY_ALEX_N_TEST";
            case 5:
                return "RELIABILITY_LONG_STALL_TRACE_ANDROID";
            case 6:
                return "RELIABILITY_UI_THREAD_LAG_ANDROID";
            case 7:
                return "RELIABILITY_SOFT_ERROR";
            case 8:
                return "RELIABILITY_NAVIGATION_ANDROID";
            case 9:
                return "RELIABILITY_PERIODIC_MEMORY_INFO";
            case 10:
                return "RELIABILITY_MEMORY_TRIM";
            default:
                switch (i) {
                    case 13:
                        return "RELIABILITY_TTRC_FAILURE";
                    case 14:
                        return "RELIABILITY_TTRC_LONG_CANCEL";
                    case 15:
                        return "RELIABILITY_SOFT_ERROR_AGGREGATED";
                    default:
                        switch (i) {
                            case 17:
                                return "RELIABILITY_EARLY_ACTIVITY_TRANSITION_LAUNCH";
                            case 18:
                                return "RELIABILITY_EARLY_ACTIVITY_TRANSITION_STOP";
                            case 19:
                                return "RELIABILITY_HOME_TASK_SWITCHER_PRESSED";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
