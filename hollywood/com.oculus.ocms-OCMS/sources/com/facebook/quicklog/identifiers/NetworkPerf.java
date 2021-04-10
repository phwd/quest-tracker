package com.facebook.quicklog.identifiers;

public class NetworkPerf {
    public static final int BLACKOUT_DURATION = 4849674;
    public static final int CDN_IMAGE_LOAD = 4849672;
    public static final int CONSECUTIVE_FAILED_REQUESTS = 4849673;
    public static final int HTTP_DNS_RESOLUTION = 4849669;
    public static final int HTTP_FLOW_TOP = 4849666;
    public static final int HTTP_REQUEST_EXCHANGE = 4849668;
    public static final int HTTP_REQUEST_STAGED = 4849667;
    public static final int HTTP_RESPONSE_BODY_READ = 4849665;
    public static final int HTTP_TCP_CONNECT = 4849671;
    public static final int HTTP_TLS_SETUP = 4849670;
    public static final short MODULE_ID = 74;
    public static final int REQUESTS = 4849675;
    public static final int REQUEST_CAP_BANDWIDTH_CALCULATION = 4849677;
    public static final int RESPONSE_LENGTH = 4849676;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "NETWORK_PERF_HTTP_RESPONSE_BODY_READ";
            case 2:
                return "NETWORK_PERF_HTTP_FLOW_TOP";
            case 3:
                return "NETWORK_PERF_HTTP_REQUEST_STAGED";
            case 4:
                return "NETWORK_PERF_HTTP_REQUEST_EXCHANGE";
            case 5:
                return "NETWORK_PERF_HTTP_DNS_RESOLUTION";
            case 6:
                return "NETWORK_PERF_HTTP_TLS_SETUP";
            case 7:
                return "NETWORK_PERF_HTTP_TCP_CONNECT";
            case 8:
                return "NETWORK_PERF_CDN_IMAGE_LOAD";
            case 9:
                return "NETWORK_PERF_CONSECUTIVE_FAILED_REQUESTS";
            case 10:
                return "NETWORK_PERF_BLACKOUT_DURATION";
            case 11:
                return "NETWORK_PERF_REQUESTS";
            case 12:
                return "NETWORK_PERF_RESPONSE_LENGTH";
            case 13:
                return "NETWORK_PERF_REQUEST_CAP_BANDWIDTH_CALCULATION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
