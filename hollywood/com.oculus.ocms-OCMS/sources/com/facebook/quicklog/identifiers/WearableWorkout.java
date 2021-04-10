package com.facebook.quicklog.identifiers;

public class WearableWorkout {
    public static final int APP_START = 811479903;
    public static final short MODULE_ID = 12382;
    public static final int STORIES_SHARING = 811468012;

    public static String getMarkerName(int i) {
        return i != 1260 ? i != 13151 ? "UNDEFINED_QPL_EVENT" : "WEARABLE_WORKOUT_APP_START" : "WEARABLE_WORKOUT_STORIES_SHARING";
    }
}
