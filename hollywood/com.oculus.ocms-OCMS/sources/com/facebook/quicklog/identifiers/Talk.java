package com.facebook.quicklog.identifiers;

public class Talk {
    public static final short MODULE_ID = 273;
    public static final int T4A_COLD_START = 17891341;
    public static final int T4A_COLD_START_IN_BACKGROUND = 17891344;
    public static final int T4A_LUKEWARM_START = 17891342;
    public static final int T4A_PERF_COLD_START = 17891332;
    public static final int T4A_PERF_COLD_START_FBAPPIMPL_ON_CREATE = 17891331;
    public static final int T4A_PERF_LUKEWARM_START = 17891334;
    public static final int T4A_PERF_MSG_THEAD_LOAD = 17891335;
    public static final int T4A_PERF_PROFILE_LOAD = 17891339;
    public static final int T4A_PERF_RTC_INCOMING_CALL_START = 17891337;
    public static final int T4A_PERF_RTC_OUTGOING_CALL_START = 17891336;
    public static final int T4A_PERF_THREAD_LIST = 17891338;
    public static final int T4A_PERF_WARM_START = 17891333;
    public static final int T4A_WARM_START = 17891343;
    public static final int THREAD_VIEW_INITIALIZATION = 17891340;

    public static String getMarkerName(int i) {
        switch (i) {
            case 3:
                return "TALK_T4A_PERF_COLD_START_FBAPPIMPL_ON_CREATE";
            case 4:
                return "TALK_T4A_PERF_COLD_START";
            case 5:
                return "TALK_T4A_PERF_WARM_START";
            case 6:
                return "TALK_T4A_PERF_LUKEWARM_START";
            case 7:
                return "TALK_T4A_PERF_MSG_THEAD_LOAD";
            case 8:
                return "TALK_T4A_PERF_RTC_OUTGOING_CALL_START";
            case 9:
                return "TALK_T4A_PERF_RTC_INCOMING_CALL_START";
            case 10:
                return "TALK_T4A_PERF_THREAD_LIST";
            case 11:
                return "TALK_T4A_PERF_PROFILE_LOAD";
            case 12:
                return "TALK_THREAD_VIEW_INITIALIZATION";
            case 13:
                return "TALK_T4A_COLD_START";
            case 14:
                return "TALK_T4A_LUKEWARM_START";
            case 15:
                return "TALK_T4A_WARM_START";
            case 16:
                return "TALK_T4A_COLD_START_IN_BACKGROUND";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
