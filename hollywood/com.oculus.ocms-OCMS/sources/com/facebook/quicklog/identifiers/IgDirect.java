package com.facebook.quicklog.identifiers;

public class IgDirect {
    public static final int DIRECT_LOCAL_SEND_SPEED = 20122678;
    public static final int IG_DIRECT_PARSE_MESSAGES = 20119560;
    public static final int IG_DIRECT_PARSE_THREAD_SUMMARIES = 20119561;
    public static final int IRIS_TIME_TO_NEW_CONTENT = 20119557;
    public static final short MODULE_ID = 307;
    public static final int RECEIVE_LATENCY = 20119564;
    public static final int THREAD_SPINNER_VISIBLE = 20119565;

    public static String getMarkerName(int i) {
        return i != 5 ? i != 3126 ? i != 8 ? i != 9 ? i != 12 ? i != 13 ? "UNDEFINED_QPL_EVENT" : "IG_DIRECT_THREAD_SPINNER_VISIBLE" : "IG_DIRECT_RECEIVE_LATENCY" : "IG_DIRECT_IG_DIRECT_PARSE_THREAD_SUMMARIES" : "IG_DIRECT_IG_DIRECT_PARSE_MESSAGES" : "IG_DIRECT_DIRECT_LOCAL_SEND_SPEED" : "IG_DIRECT_IRIS_TIME_TO_NEW_CONTENT";
    }
}
