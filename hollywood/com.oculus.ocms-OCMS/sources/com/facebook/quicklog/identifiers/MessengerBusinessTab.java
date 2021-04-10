package com.facebook.quicklog.identifiers;

public class MessengerBusinessTab {
    public static final short MODULE_ID = 547;
    public static final int RECOMMENDATION_RENDER = 35848195;
    public static final int THREAD_LIST_RENDER = 35848194;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_BUSINESS_TAB_RECOMMENDATION_RENDER" : "MESSENGER_BUSINESS_TAB_THREAD_LIST_RENDER";
    }
}
