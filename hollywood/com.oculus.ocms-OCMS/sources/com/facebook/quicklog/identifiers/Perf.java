package com.facebook.quicklog.identifiers;

public class Perf {
    public static final int BENCHMARK = 196678;
    public static final int DEFAULT_TTRC_ANDROID = 196685;
    public static final int ELIGIBLE_FOR_INTERSTITIAL_TRIGGER = 196627;
    public static final int FBLITE_CLIENT_EVENT_MANAGER = 196658;
    public static final int FBLITE_CLIENT_FRAME_RENDER = 196662;
    public static final int FBLITE_CLIENT_INIT = 196637;
    public static final int FBLITE_CLIENT_SCROLL_PERF = 196661;
    public static final int FBLITE_CLIENT_TTI = 196640;
    public static final int FBLITE_CLIENT_TTI_SCREEN_DRAWN = 196643;
    public static final int FBLITE_CLIENT_TTI_SCREEN_READY = 196642;
    public static final int FBLITE_CONNECTION_INIT = 196638;
    public static final int FBLITE_SESSION_ESTABLISHED = 196639;
    public static final int FBLITE_SESSION_EVENT = 196651;
    public static final int FBLITE_SESSION_TICKET_AVAILABLE = 196659;
    public static final int GET_FETCH_INTERSTITIAL_RESULT = 196632;
    public static final int MEMORY_PROFILING = 196615;
    public static final int MESSENGER_WARMUP = 196686;
    public static final int MESSENGER_WARMUP_MB = 196687;
    public static final short MODULE_ID = 3;
    public static final int NFC_TEST = 196672;
    public static final int NFC_TEST_2 = 196673;
    public static final int NFC_TEST_3 = 196674;
    public static final int PERFLOG = 196613;
    public static final int QPL_CLIENT_TTI_TRACE = 196665;
    public static final int QPL_CLIENT_TTI_TRACE_2 = 196666;
    public static final int QUICKLOG = 196611;
    public static final int READ_TRIGGER_TOIDS = 196631;
    public static final int RESTORE_INTERSTITIAL_TRIGGER_STATE = 196628;
    public static final int RESTORE_LAZY_TRIGGER_IDS = 196630;
    public static final int SCROLLING_OTHER_SURFACE = 196668;
    public static final int SEQUENCELOG = 196612;
    public static final int TEST1 = 196609;
    public static final int TEST2 = 196610;
    public static final int TOUCH_EVENT_LATENCY = 196636;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "TEST1";
        }
        if (i == 2) {
            return "PERF_TEST2";
        }
        if (i == 3) {
            return "PERF_QUICKLOG";
        }
        if (i == 4) {
            return "PERF_SEQUENCELOG";
        }
        if (i == 5) {
            return "PERF_PERFLOG";
        }
        if (i == 7) {
            return "PERF_MEMORY_PROFILING";
        }
        if (i == 43) {
            return "PERF_FBLITE_SESSION_EVENT";
        }
        if (i == 60) {
            return "PERF_SCROLLING_OTHER_SURFACE";
        }
        if (i == 70) {
            return "PERF_BENCHMARK";
        }
        if (i == 19) {
            return "PERF_ELIGIBLE_FOR_INTERSTITIAL_TRIGGER";
        }
        if (i == 20) {
            return "PERF_RESTORE_INTERSTITIAL_TRIGGER_STATE";
        }
        if (i == 34) {
            return "PERF_FBLITE_CLIENT_TTI_SCREEN_READY";
        }
        if (i == 35) {
            return "PERF_FBLITE_CLIENT_TTI_SCREEN_DRAWN";
        }
        if (i == 50) {
            return "PERF_FBLITE_CLIENT_EVENT_MANAGER";
        }
        if (i == 51) {
            return "PERF_FBLITE_SESSION_TICKET_AVAILABLE";
        }
        if (i == 53) {
            return "PERF_FBLITE_CLIENT_SCROLL_PERF";
        }
        if (i == 54) {
            return "PERF_FBLITE_CLIENT_FRAME_RENDER";
        }
        if (i == 57) {
            return "PERF_QPL_CLIENT_TTI_TRACE";
        }
        if (i == 58) {
            return "PERF_QPL_CLIENT_TTI_TRACE_2";
        }
        switch (i) {
            case 22:
                return "PERF_RESTORE_LAZY_TRIGGER_IDS";
            case 23:
                return "PERF_READ_TRIGGER_TOIDS";
            case 24:
                return "PERF_GET_FETCH_INTERSTITIAL_RESULT";
            default:
                switch (i) {
                    case 28:
                        return "PERF_TOUCH_EVENT_LATENCY";
                    case 29:
                        return "PERF_FBLITE_CLIENT_INIT";
                    case 30:
                        return "PERF_FBLITE_CONNECTION_INIT";
                    case 31:
                        return "PERF_FBLITE_SESSION_ESTABLISHED";
                    case 32:
                        return "PERF_FBLITE_CLIENT_TTI";
                    default:
                        switch (i) {
                            case 64:
                                return "PERF_NFC_TEST";
                            case 65:
                                return "PERF_NFC_TEST_2";
                            case 66:
                                return "PERF_NFC_TEST_3";
                            default:
                                switch (i) {
                                    case 77:
                                        return "PERF_DEFAULT_TTRC_ANDROID";
                                    case 78:
                                        return "PERF_MESSENGER_WARMUP";
                                    case 79:
                                        return "PERF_MESSENGER_WARMUP_MB";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
