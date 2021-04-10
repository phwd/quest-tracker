package com.facebook.quicklog.identifiers;

public class TopicFollowing {
    public static final short MODULE_ID = 214;
    public static final int TOPIC_STORIES_TTI = 14024705;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "TOPIC_FOLLOWING_TOPIC_STORIES_TTI";
    }
}
