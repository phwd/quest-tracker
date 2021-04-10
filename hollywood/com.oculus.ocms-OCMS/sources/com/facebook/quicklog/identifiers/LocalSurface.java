package com.facebook.quicklog.identifiers;

public class LocalSurface {
    public static final int LOAD_CATEGORY_TYPEAHEAD_NULL_STATE_TTRC = 13041670;
    public static final int LOAD_CATEGORY_TYPEAHEAD_RESULTS_TTRC = 13041672;
    public static final int LOAD_LOCATION_TYPEAHEAD_NULL_STATE_TTRC = 13041674;
    public static final int LOAD_LOCATION_TYPEAHEAD_RESULTS_TTRC = 13041673;
    public static final short MODULE_ID = 199;

    public static String getMarkerName(int i) {
        switch (i) {
            case 6:
                return "LOCAL_SURFACE_LOAD_CATEGORY_TYPEAHEAD_NULL_STATE_TTRC";
            case 7:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "LOCAL_SURFACE_LOAD_CATEGORY_TYPEAHEAD_RESULTS_TTRC";
            case 9:
                return "LOCAL_SURFACE_LOAD_LOCATION_TYPEAHEAD_RESULTS_TTRC";
            case 10:
                return "LOCAL_SURFACE_LOAD_LOCATION_TYPEAHEAD_NULL_STATE_TTRC";
        }
    }
}
