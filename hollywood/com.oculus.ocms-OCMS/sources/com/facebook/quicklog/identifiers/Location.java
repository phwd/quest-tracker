package com.facebook.quicklog.identifiers;

public class Location {
    public static final int FETCH_DATA = 3342344;
    public static final int INIT = 3342343;
    public static final short MODULE_ID = 51;
    public static final int OVERALL_TTI = 3342342;
    public static final int RENDER = 3342345;

    public static String getMarkerName(int i) {
        switch (i) {
            case 6:
                return "LOCATION_OVERALL_TTI";
            case 7:
                return "LOCATION_INIT";
            case 8:
                return "LOCATION_FETCH_DATA";
            case 9:
                return "LOCATION_RENDER";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
