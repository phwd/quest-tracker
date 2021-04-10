package com.facebook.quicklog.identifiers;

public class Logdb {
    public static final int CAPTURE_COMPARE = 776809821;
    public static final int DB_ERROR = 776811047;
    public static final short MODULE_ID = 11853;
    public static final int ON_ADD_SUBSCRIPTION = 776802110;
    public static final int TRIM = 776798209;
    public static final int TRIM_IF_NEEDED = 776798208;
    public static final int TRIM_WHEN_NEEDED = 776806870;

    public static String getMarkerName(int i) {
        return i != 0 ? i != 1 ? i != 3902 ? i != 8662 ? i != 11613 ? i != 12839 ? "UNDEFINED_QPL_EVENT" : "LOGDB_DB_ERROR" : "LOGDB_CAPTURE_COMPARE" : "LOGDB_TRIM_WHEN_NEEDED" : "LOGDB_ON_ADD_SUBSCRIPTION" : "LOGDB_TRIM" : "LOGDB_TRIM_IF_NEEDED";
    }
}
