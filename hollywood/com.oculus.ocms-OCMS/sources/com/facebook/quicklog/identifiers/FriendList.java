package com.facebook.quicklog.identifiers;

public class FriendList {
    public static final int ALL_TAB_TAIL_LOAD = 4063244;
    public static final int INITIAL_LOAD_ALL_TAB = 4063234;
    public static final int INITIAL_LOAD_ALL_TAB_TTRC_ANDROID = 4063240;
    public static final int INITIAL_LOAD_MUTUAL_TAB = 4063235;
    public static final int INITIAL_LOAD_MUTUAL_TAB_TTRC_ANDROID = 4063243;
    public static final int INITIAL_LOAD_RECENT_TAB = 4063236;
    public static final int INITIAL_LOAD_RECENT_TAB_TTRC_ANDROID = 4063242;
    public static final int INITIAL_LOAD_SUGGESTIONS_TAB = 4063237;
    public static final int INITIAL_LOAD_SUGGESTIONS_TAB_TTRC_ANDROID = 4063241;
    public static final int INITIAL_LOAD_WITH_NEW_POSTS_TAB = 4063238;
    public static final short MODULE_ID = 62;

    public static String getMarkerName(int i) {
        switch (i) {
            case 2:
                return "FRIEND_LIST_INITIAL_LOAD_ALL_TAB";
            case 3:
                return "FRIEND_LIST_INITIAL_LOAD_MUTUAL_TAB";
            case 4:
                return "FRIEND_LIST_INITIAL_LOAD_RECENT_TAB";
            case 5:
                return "FRIEND_LIST_INITIAL_LOAD_SUGGESTIONS_TAB";
            case 6:
                return "FRIEND_LIST_INITIAL_LOAD_WITH_NEW_POSTS_TAB";
            case 7:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "FRIEND_LIST_INITIAL_LOAD_ALL_TAB_TTRC_ANDROID";
            case 9:
                return "FRIEND_LIST_INITIAL_LOAD_SUGGESTIONS_TAB_TTRC_ANDROID";
            case 10:
                return "FRIEND_LIST_INITIAL_LOAD_RECENT_TAB_TTRC_ANDROID";
            case 11:
                return "FRIEND_LIST_INITIAL_LOAD_MUTUAL_TAB_TTRC_ANDROID";
            case 12:
                return "FRIEND_LIST_ALL_TAB_TAIL_LOAD";
        }
    }
}
