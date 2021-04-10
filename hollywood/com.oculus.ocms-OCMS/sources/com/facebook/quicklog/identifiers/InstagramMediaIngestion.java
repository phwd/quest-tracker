package com.facebook.quicklog.identifiers;

public class InstagramMediaIngestion {
    public static final int INGEST = 42074113;
    public static final short MODULE_ID = 642;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTAGRAM_MEDIA_INGESTION_INGEST";
    }
}
