package com.facebook.quicklog.identifiers;

public class IgMediaIngestion {
    public static final int CONFIGURE = 42139652;
    public static final int COVER_PHOTO_UPLOAD = 42139654;
    public static final int FINISH = 42139653;
    public static final int INGEST = 42139649;
    public static final short MODULE_ID = 643;
    public static final int PUBLISH = 42153283;
    public static final int RENDER = 42139650;
    public static final int UPLOAD = 42139651;

    public static String getMarkerName(int i) {
        if (i == 13635) {
            return "IG_MEDIA_INGESTION_PUBLISH";
        }
        switch (i) {
            case 1:
                return "IG_MEDIA_INGESTION_INGEST";
            case 2:
                return "IG_MEDIA_INGESTION_RENDER";
            case 3:
                return "IG_MEDIA_INGESTION_UPLOAD";
            case 4:
                return "IG_MEDIA_INGESTION_CONFIGURE";
            case 5:
                return "IG_MEDIA_INGESTION_FINISH";
            case 6:
                return "IG_MEDIA_INGESTION_COVER_PHOTO_UPLOAD";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
