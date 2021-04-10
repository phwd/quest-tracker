package com.facebook.quicklog.identifiers;

public class LocalAggregator {
    public static final int END_AGGREGATION = 46333953;
    public static final short MODULE_ID = 707;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "LOCAL_AGGREGATOR_END_AGGREGATION";
    }
}
