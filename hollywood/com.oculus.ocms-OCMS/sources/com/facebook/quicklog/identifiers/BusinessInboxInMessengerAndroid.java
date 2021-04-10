package com.facebook.quicklog.identifiers;

public class BusinessInboxInMessengerAndroid {
    public static final short MODULE_ID = 5170;
    public static final int QPL_ACCOUNT_SWITCHER_IMPRESSION = 338823583;

    public static String getMarkerName(int i) {
        return i != 2463 ? "UNDEFINED_QPL_EVENT" : "BUSINESS_INBOX_IN_MESSENGER_ANDROID_QPL_ACCOUNT_SWITCHER_IMPRESSION";
    }
}
