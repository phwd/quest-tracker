package com.facebook.quicklog.identifiers;

public class CollaborativeStories {
    public static final int INVITATION_TTRC = 64421889;
    public static final int MANAGEMENT_TTRC = 64421891;
    public static final short MODULE_ID = 983;
    public static final int SHARESHEET_TTRC = 64421890;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "COLLABORATIVE_STORIES_MANAGEMENT_TTRC" : "COLLABORATIVE_STORIES_SHARESHEET_TTRC" : "COLLABORATIVE_STORIES_INVITATION_TTRC";
    }
}
