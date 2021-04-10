package com.facebook.quicklog.identifiers;

public class Pymk {
    public static final short MODULE_ID = 46;
    public static final int PAGINATED_PYMK_TTI = 3014657;
    public static final int PYMK_FEED_UNIT_TTI = 3014658;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "PYMK_PYMK_FEED_UNIT_TTI" : "PYMK_PAGINATED_PYMK_TTI";
    }
}
