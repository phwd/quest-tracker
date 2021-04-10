package com.facebook.quicklog.identifiers;

public class BundleSplitting {
    public static final int FETCH_JS_SEGMENT = 18087937;
    public static final short MODULE_ID = 276;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "BUNDLE_SPLITTING_FETCH_JS_SEGMENT";
    }
}
