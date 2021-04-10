package com.facebook.quicklog.identifiers;

public class RtcPlatformClientPerf {
    public static final int ACCEPT_INCOMING_CONNECTION_FLOW = 41156610;
    public static final short MODULE_ID = 628;
    public static final int SIGNALING_RELIABILITY_RECEIVE_MESSAGE = 41167730;
    public static final int SIGNALING_RELIABILITY_SEND_MESSAGE = 41164813;
    public static final int START_GROUP_ESCALATION = 41156611;
    public static final int START_OUTGOING_CONNECTION = 41156609;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 8205 ? i != 11122 ? "UNDEFINED_QPL_EVENT" : "RTC_PLATFORM_CLIENT_PERF_SIGNALING_RELIABILITY_RECEIVE_MESSAGE" : "RTC_PLATFORM_CLIENT_PERF_SIGNALING_RELIABILITY_SEND_MESSAGE" : "RTC_PLATFORM_CLIENT_PERF_START_GROUP_ESCALATION" : "RTC_PLATFORM_CLIENT_PERF_ACCEPT_INCOMING_CONNECTION_FLOW" : "RTC_PLATFORM_CLIENT_PERF_START_OUTGOING_CONNECTION";
    }
}
