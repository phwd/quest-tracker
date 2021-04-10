package com.facebook.quicklog.identifiers;

public class Blueservice {
    public static final int BLUESERVICETOBINDDONE = 4718594;
    public static final int BLUESERVICETOTALOVERHEAD = 4718593;
    public static final int BLUESERVICEWAITINQUEUE = 4718595;
    public static final short MODULE_ID = 72;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "BLUESERVICE_BLUESERVICEWAITINQUEUE" : "BLUESERVICE_BLUESERVICETOBINDDONE" : "BLUESERVICE_BLUESERVICETOTALOVERHEAD";
    }
}
