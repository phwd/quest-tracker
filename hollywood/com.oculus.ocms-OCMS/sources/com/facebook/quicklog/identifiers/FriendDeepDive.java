package com.facebook.quicklog.identifiers;

public class FriendDeepDive {
    public static final int FRIEND_DEEP_DIVE_TTRC = 46137345;
    public static final short MODULE_ID = 704;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FRIEND_DEEP_DIVE_FRIEND_DEEP_DIVE_TTRC";
    }
}
