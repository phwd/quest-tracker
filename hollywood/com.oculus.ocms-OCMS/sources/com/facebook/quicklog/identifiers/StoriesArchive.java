package com.facebook.quicklog.identifiers;

public class StoriesArchive {
    public static final int ARCHIVE_GRID_TTI = 22347781;
    public static final short MODULE_ID = 341;
    public static final int PAGINATION_TTI = 22347779;
    public static final int SCROLL_PERF = 22347783;
    public static final int STORIES_VIEWER_TTI = 22347777;
    public static final int THUMBNAIL_TTI = 22347780;
    public static final int VIEWERS_LIST_TTI = 22347778;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 7 ? "UNDEFINED_QPL_EVENT" : "STORIES_ARCHIVE_SCROLL_PERF" : "STORIES_ARCHIVE_ARCHIVE_GRID_TTI" : "STORIES_ARCHIVE_THUMBNAIL_TTI" : "STORIES_ARCHIVE_PAGINATION_TTI" : "STORIES_ARCHIVE_VIEWERS_LIST_TTI" : "STORIES_ARCHIVE_STORIES_VIEWER_TTI";
    }
}
