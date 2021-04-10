package com.facebook.quicklog.identifiers;

public class GroupMallReliability {
    public static final int GROUPS_MALL_RELIABILITY_RESTRICTED_ISSUES = 59776465;
    public static final int GROUP_MALL_TAIL_LOAD_RELIABILITY = 59768833;
    public static final short MODULE_ID = 912;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 7633 ? "UNDEFINED_QPL_EVENT" : "GROUP_MALL_RELIABILITY_GROUPS_MALL_RELIABILITY_RESTRICTED_ISSUES" : "GROUP_MALL_RELIABILITY_GROUP_MALL_TAIL_LOAD_RELIABILITY";
    }
}
