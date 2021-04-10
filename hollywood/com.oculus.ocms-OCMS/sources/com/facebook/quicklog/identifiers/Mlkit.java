package com.facebook.quicklog.identifiers;

public class Mlkit {
    public static final int MLKIT_BATCH_PREDICTION = 22872070;
    public static final int MLKIT_CLIENT_EVALUATOR_INIT = 22872067;
    public static final int MLKIT_FEATURE_EXTRACTION = 22872069;
    public static final int MLKIT_OPERATIONS_QUEUE_RUN = 22872066;
    public static final int MLKIT_PREDICTION = 22872065;
    public static final short MODULE_ID = 349;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 5 ? i != 6 ? "UNDEFINED_QPL_EVENT" : "MLKIT_MLKIT_BATCH_PREDICTION" : "MLKIT_MLKIT_FEATURE_EXTRACTION" : "MLKIT_MLKIT_CLIENT_EVALUATOR_INIT" : "MLKIT_MLKIT_OPERATIONS_QUEUE_RUN" : "MLKIT_MLKIT_PREDICTION";
    }
}
