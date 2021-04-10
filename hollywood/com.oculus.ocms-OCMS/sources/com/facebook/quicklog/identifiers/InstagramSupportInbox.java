package com.facebook.quicklog.identifiers;

public class InstagramSupportInbox {
    public static final short MODULE_ID = 8142;
    public static final int PAGE_LOADED = 533594113;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "INSTAGRAM_SUPPORT_INBOX_PAGE_LOADED";
    }
}
