package com.facebook.quicklog.identifiers;

public class EgoListView {
    public static final short MODULE_ID = 100;
    public static final int PAGINATED_GYSJ_TTI = 6553601;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "EGO_LIST_VIEW_PAGINATED_GYSJ_TTI";
    }
}
