package com.facebook.quicklog.identifiers;

public class FbliteCreation {
    public static final int FBLITE_MEDIA_PICKER_SCROLL_PERF = 43909124;
    public static final int FBLITE_MEDIA_PICKER_TTRC = 43909122;
    public static final int FBLITE_MUSIC_STICKER_PLAY_TIME = 43923087;
    public static final int FBLITE_STORIES_BURN_TIME = 43909123;
    public static final int FBLITE_STORIES_EDITOR_CREATIVE_TOOLS_TTRC = 43909121;
    public static final short MODULE_ID = 670;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 13967 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CREATION_FBLITE_MUSIC_STICKER_PLAY_TIME" : "FBLITE_CREATION_FBLITE_MEDIA_PICKER_SCROLL_PERF" : "FBLITE_CREATION_FBLITE_STORIES_BURN_TIME" : "FBLITE_CREATION_FBLITE_MEDIA_PICKER_TTRC" : "FBLITE_CREATION_FBLITE_STORIES_EDITOR_CREATIVE_TOOLS_TTRC";
    }
}
