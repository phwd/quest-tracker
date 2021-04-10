package com.facebook.quicklog.identifiers;

public class Fury {
    public static final int FBLITE_FURY_INSTRUMENT_REJECT_RUNNABLE = 28246017;
    public static final int FBLITE_FURY_INSTRUMENT_RUNNABLE = 28246018;
    public static final int FBLITE_FURY_INSTRUMENT_THREAD_STATS = 28246019;
    public static final short MODULE_ID = 431;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "FURY_FBLITE_FURY_INSTRUMENT_THREAD_STATS" : "FURY_FBLITE_FURY_INSTRUMENT_RUNNABLE" : "FURY_FBLITE_FURY_INSTRUMENT_REJECT_RUNNABLE";
    }
}
