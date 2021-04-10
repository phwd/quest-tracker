package com.facebook.quicklog.identifiers;

public class Cloudseeder {
    public static final short MODULE_ID = 181;
    public static final int NETWORK_HISTOGRAMS = 11862017;
    public static final int SOCKET_TRACE = 11862018;
    public static final int SOCKET_TRACE_LONG_VIDEO = 11862019;
    public static final int TRACE_EXCEPTION = 11862020;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "CLOUDSEEDER_TRACE_EXCEPTION" : "CLOUDSEEDER_SOCKET_TRACE_LONG_VIDEO" : "CLOUDSEEDER_SOCKET_TRACE" : "CLOUDSEEDER_NETWORK_HISTOGRAMS";
    }
}
