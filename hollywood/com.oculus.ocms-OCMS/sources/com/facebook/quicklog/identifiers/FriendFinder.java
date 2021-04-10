package com.facebook.quicklog.identifiers;

public class FriendFinder {
    public static final int FRIEND_FINDER_ADD_FRIENDS_TTI_MARKER = 4128769;
    public static final short MODULE_ID = 63;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "FRIEND_FINDER_FRIEND_FINDER_ADD_FRIENDS_TTI_MARKER";
    }
}
