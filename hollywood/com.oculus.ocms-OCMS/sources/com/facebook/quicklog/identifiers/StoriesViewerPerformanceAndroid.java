package com.facebook.quicklog.identifiers;

public class StoriesViewerPerformanceAndroid {
    public static final int BUCKET_TRANSITION_TTRC = 39845890;
    public static final int INITIAL_LOAD_TTRC = 39845889;
    public static final short MODULE_ID = 608;
    public static final int THREAD_TRANSITION_TTRC = 39845891;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "STORIES_VIEWER_PERFORMANCE_ANDROID_THREAD_TRANSITION_TTRC" : "STORIES_VIEWER_PERFORMANCE_ANDROID_BUCKET_TRANSITION_TTRC" : "STORIES_VIEWER_PERFORMANCE_ANDROID_INITIAL_LOAD_TTRC";
    }
}
