package com.facebook.quicklog.identifiers;

public class IgAndroidIngestionEncoderSelection {
    public static final int DECODER_SELECTION = 54460418;
    public static final int ENCODER_SELECTION = 54460417;
    public static final short MODULE_ID = 831;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "IG_ANDROID_INGESTION_ENCODER_SELECTION_DECODER_SELECTION" : "IG_ANDROID_INGESTION_ENCODER_SELECTION_ENCODER_SELECTION";
    }
}
