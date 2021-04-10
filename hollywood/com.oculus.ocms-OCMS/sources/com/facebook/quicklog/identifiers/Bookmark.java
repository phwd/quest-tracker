package com.facebook.quicklog.identifiers;

public class Bookmark {
    public static final int BOOKMARKS_NT_ASYNC_DISMISS_ACTION = 2628810;
    public static final int BOOKMARKS_NT_JS_DISMISS_ACTION = 2643961;
    public static final int BOOKMARKS_TTRC = 2621447;
    public static final short MODULE_ID = 40;

    public static String getMarkerName(int i) {
        return i != 7 ? i != 7370 ? i != 22521 ? "UNDEFINED_QPL_EVENT" : "BOOKMARK_BOOKMARKS_NT_JS_DISMISS_ACTION" : "BOOKMARK_BOOKMARKS_NT_ASYNC_DISMISS_ACTION" : "BOOKMARK_BOOKMARKS_TTRC";
    }
}
