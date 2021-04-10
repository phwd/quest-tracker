package com.facebook.quicklog.identifiers;

public class QuickerExperiment {
    public static final short MODULE_ID = 129;
    public static final int SESSIONED_STORE_INITIALIZE = 8454145;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "QUICKER_EXPERIMENT_SESSIONED_STORE_INITIALIZE";
    }
}
