package com.facebook.quicklog.identifiers;

public class IgStories {
    public static final int CLOSE_FRIENDS_SUGGESTIONS_DID_LOAD = 18939908;
    public static final int IG_STORY_STICKER_TRAY = 18946897;
    public static final short MODULE_ID = 289;
    public static final int REMIX_VIDEO_LOAD = 18956285;
    public static final int STORY_CREATION_LONGPRESS_TO_VIDEO = 18948524;
    public static final int STORY_GALLERY_THUMBNAILS = 18949957;

    public static String getMarkerName(int i) {
        return i != 4 ? i != 6993 ? i != 8620 ? i != 10053 ? i != 16381 ? "UNDEFINED_QPL_EVENT" : "IG_STORIES_REMIX_VIDEO_LOAD" : "IG_STORIES_STORY_GALLERY_THUMBNAILS" : "IG_STORIES_STORY_CREATION_LONGPRESS_TO_VIDEO" : "IG_STORIES_IG_STORY_STICKER_TRAY" : "IG_STORIES_CLOSE_FRIENDS_SUGGESTIONS_DID_LOAD";
    }
}
