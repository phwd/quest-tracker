package com.facebook.quicklog.identifiers;

public class WorkReporting {
    public static final short MODULE_ID = 3551;
    public static final int SUBMIT_REPORT = 232726078;

    public static String getMarkerName(int i) {
        return i != 7742 ? "UNDEFINED_QPL_EVENT" : "WORK_REPORTING_SUBMIT_REPORT";
    }
}
