package com.facebook.quicklog.identifiers;

public class FbliteClientLogsMetadata {
    public static final int CLIENT_LOG_RECEIVED = 58785793;
    public static final short MODULE_ID = 897;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CLIENT_LOGS_METADATA_CLIENT_LOG_RECEIVED";
    }
}
