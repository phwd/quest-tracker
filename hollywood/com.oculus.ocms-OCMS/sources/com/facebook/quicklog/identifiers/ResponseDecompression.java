package com.facebook.quicklog.identifiers;

public class ResponseDecompression {
    public static final int DECOMPRESS_RESPONSE = 382015128;
    public static final short MODULE_ID = 5829;

    public static String getMarkerName(int i) {
        return i != 5784 ? "UNDEFINED_QPL_EVENT" : "RESPONSE_DECOMPRESSION_DECOMPRESS_RESPONSE";
    }
}
