package com.facebook.quicklog.identifiers;

public class LigerNetworkStats {
    public static final int DNS_LATENCY = 10289158;
    public static final int IS_NEW_CONNECTION = 10289153;
    public static final short MODULE_ID = 157;
    public static final int REQUEST_BODY_BYTES = 10289155;
    public static final int REQUEST_HEADER_BYTES = 10289154;
    public static final int RESPONSE_BODY_BYTES = 10289157;
    public static final int RESPONSE_HEADER_BYTES = 10289156;
    public static final int RESPOSNE_BODY_BYTES_TIME = 10289161;
    public static final int TCP_LATENCY = 10289159;
    public static final int TLS_LATENCY = 10289160;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "ISNEWCONNECTION";
            case 2:
                return "LIGER_NETWORK_STATS_REQUEST_HEADER_BYTES";
            case 3:
                return "LIGER_NETWORK_STATS_REQUEST_BODY_BYTES";
            case 4:
                return "LIGER_NETWORK_STATS_RESPONSE_HEADER_BYTES";
            case 5:
                return "LIGER_NETWORK_STATS_RESPONSE_BODY_BYTES";
            case 6:
                return "LIGER_NETWORK_STATS_DNS_LATENCY";
            case 7:
                return "LIGER_NETWORK_STATS_TCP_LATENCY";
            case 8:
                return "LIGER_NETWORK_STATS_TLS_LATENCY";
            case 9:
                return "LIGER_NETWORK_STATS_RESPOSNE_BODY_BYTES_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
