package com.facebook.quicklog.identifiers;

public class AvatarCoPresence {
    public static final int AVATAR_ANIMATION = 1035671155;
    public static final short MODULE_ID = 15803;

    public static String getMarkerName(int i) {
        return i != 5747 ? "UNDEFINED_QPL_EVENT" : "AVATAR_CO_PRESENCE_AVATAR_ANIMATION";
    }
}
