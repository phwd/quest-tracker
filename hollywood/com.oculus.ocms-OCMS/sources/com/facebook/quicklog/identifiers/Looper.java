package com.facebook.quicklog.identifiers;

public class Looper {
    public static final int FEATURE_GROUP_EXTRACTION = 57081860;
    public static final int FEATURE_REFRESH = 57081859;
    public static final int INDIVIDUAL_FEATURE_EXTRACTION = 57081861;
    public static final short MODULE_ID = 871;
    public static final int PREDICTION = 57081858;
    public static final int PREDICTION_SESSION_INIT = 57081857;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNDEFINED_QPL_EVENT" : "LOOPER_INDIVIDUAL_FEATURE_EXTRACTION" : "LOOPER_FEATURE_GROUP_EXTRACTION" : "LOOPER_FEATURE_REFRESH" : "LOOPER_PREDICTION" : "LOOPER_PREDICTION_SESSION_INIT";
    }
}
