package com.facebook.quicklog.identifiers;

public class AvatarRtcIg {
    public static final int AVATAR_EFFECT_METADATA_DOWNLOAD = 112593162;
    public static final int AVATAR_EFFECT_SELECT = 112601624;
    public static final short MODULE_ID = 1718;

    public static String getMarkerName(int i) {
        return i != 2314 ? i != 10776 ? "UNDEFINED_QPL_EVENT" : "AVATAR_RTC_IG_AVATAR_EFFECT_SELECT" : "AVATAR_RTC_IG_AVATAR_EFFECT_METADATA_DOWNLOAD";
    }
}
