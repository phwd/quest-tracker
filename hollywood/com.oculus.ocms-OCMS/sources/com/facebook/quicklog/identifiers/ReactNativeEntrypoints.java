package com.facebook.quicklog.identifiers;

public class ReactNativeEntrypoints {
    public static final int LOAD = 53411841;
    public static final short MODULE_ID = 815;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "REACT_NATIVE_ENTRYPOINTS_LOAD";
    }
}
