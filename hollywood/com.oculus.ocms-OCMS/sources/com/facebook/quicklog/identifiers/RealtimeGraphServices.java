package com.facebook.quicklog.identifiers;

public class RealtimeGraphServices {
    public static final short MODULE_ID = 14267;
    public static final int REALTIME_GRAPH_SERVICE_QUERY_EXECUTION = 935014793;
    public static final int REALTIME_GRAPH_SERVICE_QUERY_RESPONSE = 935013523;
    public static final int REALTIME_GRAPH_SERVICE_QUERY_SUBSCRIBE = 935006090;

    public static String getMarkerName(int i) {
        return i != 3978 ? i != 11411 ? i != 12681 ? "UNDEFINED_QPL_EVENT" : "REALTIME_GRAPH_SERVICES_REALTIME_GRAPH_SERVICE_QUERY_EXECUTION" : "REALTIME_GRAPH_SERVICES_REALTIME_GRAPH_SERVICE_QUERY_RESPONSE" : "REALTIME_GRAPH_SERVICES_REALTIME_GRAPH_SERVICE_QUERY_SUBSCRIBE";
    }
}
