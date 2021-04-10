package com.facebook.quicklog.identifiers;

public class OculusSocialPlatform {
    public static final int FB_MESSENGER_TABLET_START_UP = 911217013;
    public static final short MODULE_ID = 13904;

    public static String getMarkerName(int i) {
        return i != 4469 ? "UNDEFINED_QPL_EVENT" : "OCULUS_SOCIAL_PLATFORM_FB_MESSENGER_TABLET_START_UP";
    }
}
