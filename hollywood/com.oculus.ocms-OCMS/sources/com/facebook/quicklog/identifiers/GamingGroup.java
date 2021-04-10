package com.facebook.quicklog.identifiers;

public class GamingGroup {
    public static final short MODULE_ID = 16232;
    public static final int STREAMER_FAN_GROUP_MALL_JOIN_NOTIF = 1063796217;
    public static final int STREAMER_FAN_PAGE_GROUP_TAB_JOIN_NOTIF = 1063783100;

    public static String getMarkerName(int i) {
        return i != 2748 ? i != 15865 ? "UNDEFINED_QPL_EVENT" : "GAMING_GROUP_STREAMER_FAN_GROUP_MALL_JOIN_NOTIF" : "GAMING_GROUP_STREAMER_FAN_PAGE_GROUP_TAB_JOIN_NOTIF";
    }
}
