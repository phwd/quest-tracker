package com.facebook.quicklog.identifiers;

public class VideoChatLinks {
    public static final int CREATE_LINK_MOBILE = 38273025;
    public static final int ENTER_ROOM_MOBILE = 38273028;
    public static final int GET_HOST_MOBILE = 38273029;
    public static final short MODULE_ID = 584;
    public static final int RESOLVE_LINK_MOBILE = 38273027;
    public static final int RESOLVE_LINK_MOBILE_CACHE_USERS = 38288346;
    public static final int REVOKE_LINK_AND_END_CALL_MOBILE = 38278506;
    public static final int REVOKE_LINK_MOBILE = 38273026;
    public static final int UPDATE_ROOM_RINGABILITY_MOBILE = 38273030;

    public static String getMarkerName(int i) {
        if (i == 5482) {
            return "VIDEO_CHAT_LINKS_REVOKE_LINK_AND_END_CALL_MOBILE";
        }
        if (i == 15322) {
            return "VIDEO_CHAT_LINKS_RESOLVE_LINK_MOBILE_CACHE_USERS";
        }
        switch (i) {
            case 1:
                return "VIDEO_CHAT_LINKS_CREATE_LINK_MOBILE";
            case 2:
                return "VIDEO_CHAT_LINKS_REVOKE_LINK_MOBILE";
            case 3:
                return "VIDEO_CHAT_LINKS_RESOLVE_LINK_MOBILE";
            case 4:
                return "VIDEO_CHAT_LINKS_ENTER_ROOM_MOBILE";
            case 5:
                return "VIDEO_CHAT_LINKS_GET_HOST_MOBILE";
            case 6:
                return "VIDEO_CHAT_LINKS_UPDATE_ROOM_RINGABILITY_MOBILE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
