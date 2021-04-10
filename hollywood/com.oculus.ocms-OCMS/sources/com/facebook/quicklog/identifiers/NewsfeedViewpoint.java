package com.facebook.quicklog.identifiers;

public class NewsfeedViewpoint {
    public static final int LOAD = 34996225;
    public static final int LOGGING_ORGANIC = 34996227;
    public static final int LOGGING_SPONSORED = 34996228;
    public static final short MODULE_ID = 534;
    public static final int STORY_VIEWPOINT_ATTACH = 34996226;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "NEWSFEED_VIEWPOINT_LOGGING_SPONSORED" : "NEWSFEED_VIEWPOINT_LOGGING_ORGANIC" : "NEWSFEED_VIEWPOINT_STORY_VIEWPOINT_ATTACH" : "NEWSFEED_VIEWPOINT_LOAD";
    }
}
