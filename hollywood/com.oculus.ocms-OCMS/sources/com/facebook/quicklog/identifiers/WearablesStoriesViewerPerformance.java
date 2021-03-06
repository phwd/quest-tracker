package com.facebook.quicklog.identifiers;

public class WearablesStoriesViewerPerformance {
    public static final int INITIAL_LOAD_TTRC = 167514875;
    public static final int MEDIA_ZOOM_PERF = 167521484;
    public static final short MODULE_ID = 2556;
    public static final int REACTION_SHEET_SCROLL_PERF = 167525043;
    public static final int SELF_VIEWER_SCROLL_PERF = 167521005;
    public static final int THREAD_TRANSITION_TTRC = 167525674;
    public static final int VIEWER_LIST_SCROLL_PERF = 167512032;

    public static String getMarkerName(int i) {
        return i != 2016 ? i != 4859 ? i != 10989 ? i != 11468 ? i != 15027 ? i != 15658 ? "UNDEFINED_QPL_EVENT" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_THREAD_TRANSITION_TTRC" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_REACTION_SHEET_SCROLL_PERF" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_MEDIA_ZOOM_PERF" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_SELF_VIEWER_SCROLL_PERF" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_INITIAL_LOAD_TTRC" : "WEARABLES_STORIES_VIEWER_PERFORMANCE_VIEWER_LIST_SCROLL_PERF";
    }
}
