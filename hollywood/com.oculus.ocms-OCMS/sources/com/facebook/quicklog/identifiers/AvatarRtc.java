package com.facebook.quicklog.identifiers;

public class AvatarRtc {
    public static final int APPLY_AVATAR_EFFECT = 579669752;
    public static final short MODULE_ID = 8845;
    public static final int PREFETCH_EFFECT_METADATA = 579678347;

    public static String getMarkerName(int i) {
        return i != 3832 ? i != 12427 ? "UNDEFINED_QPL_EVENT" : "AVATAR_RTC_PREFETCH_EFFECT_METADATA" : "AVATAR_RTC_APPLY_AVATAR_EFFECT";
    }
}
