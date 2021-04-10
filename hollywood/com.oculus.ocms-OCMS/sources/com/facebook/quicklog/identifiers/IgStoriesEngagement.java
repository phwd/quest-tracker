package com.facebook.quicklog.identifiers;

public class IgStoriesEngagement {
    public static final short MODULE_ID = 264;
    public static final int PRESENT_STORY_VIEWER = 17301505;
    public static final int STORIES_IFU_ITEM_LOAD_LATENCY = 17301507;
    public static final int STORIES_TRAY_ITEM_WITH_PREVIEW_RENDER_LATENCY = 17323904;
    public static final int VIEWER_HIDE_ANIMATION = 17301508;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 22400 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "IG_STORIES_ENGAGEMENT_VIEWER_HIDE_ANIMATION" : "IG_STORIES_ENGAGEMENT_STORIES_IFU_ITEM_LOAD_LATENCY" : "IG_STORIES_ENGAGEMENT_STORIES_TRAY_ITEM_WITH_PREVIEW_RENDER_LATENCY" : "IG_STORIES_ENGAGEMENT_PRESENT_STORY_VIEWER";
    }
}
