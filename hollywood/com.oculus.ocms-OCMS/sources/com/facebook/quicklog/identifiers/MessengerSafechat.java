package com.facebook.quicklog.identifiers;

public class MessengerSafechat {
    public static final int MODEL_CACHE_START = 64159747;
    public static final int MODEL_COLD_START = 64159745;
    public static final int MODEL_WARM_START = 64159746;
    public static final short MODULE_ID = 979;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "MESSENGER_SAFECHAT_MODEL_CACHE_START" : "MESSENGER_SAFECHAT_MODEL_WARM_START" : "MESSENGER_SAFECHAT_MODEL_COLD_START";
    }
}
