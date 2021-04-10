package com.facebook.quicklog.identifiers;

public class SiLearning {
    public static final short MODULE_ID = 2907;
    public static final int UNITS_LIST_PAGING_TTRC_NT = 190515375;
    public static final int UNIT_DETAILS_PAGING_TTRC_NT = 190521758;

    public static String getMarkerName(int i) {
        return i != 2223 ? i != 8606 ? "UNDEFINED_QPL_EVENT" : "SI_LEARNING_UNIT_DETAILS_PAGING_TTRC_NT" : "SI_LEARNING_UNITS_LIST_PAGING_TTRC_NT";
    }
}
