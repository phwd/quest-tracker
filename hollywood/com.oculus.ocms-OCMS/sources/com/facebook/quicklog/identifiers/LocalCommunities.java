package com.facebook.quicklog.identifiers;

public class LocalCommunities {
    public static final int LOCO_HOME_TTRC = 56492033;
    public static final int LOCO_ONBOARDING_NEIGHBORHOOD_TTRC = 56492034;
    public static final short MODULE_ID = 862;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "LOCAL_COMMUNITIES_LOCO_ONBOARDING_NEIGHBORHOOD_TTRC" : "LOCAL_COMMUNITIES_LOCO_HOME_TTRC";
    }
}
