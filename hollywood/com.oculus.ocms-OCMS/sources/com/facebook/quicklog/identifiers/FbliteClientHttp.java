package com.facebook.quicklog.identifiers;

public class FbliteClientHttp {
    public static final int HTTP_REQUEST = 113904119;
    public static final short MODULE_ID = 1738;

    public static String getMarkerName(int i) {
        return i != 2551 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CLIENT_HTTP_HTTP_REQUEST";
    }
}
