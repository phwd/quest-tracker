package com.facebook.quicklog.identifiers;

public class IgThreadsAppKaraokeCaption {
    public static final int ASR_REQUEST = 57737223;
    public static final int EXTRACT_AUDIO = 57737222;
    public static final int KARAOKE_CAPTION_COMPLETE_PROCESS = 57737221;
    public static final short MODULE_ID = 881;
    public static final int VIEW_MODEL_PREPROCESSING = 57737224;

    public static String getMarkerName(int i) {
        return i != 5 ? i != 6 ? i != 7 ? i != 8 ? "UNDEFINED_QPL_EVENT" : "IG_THREADS_APP_KARAOKE_CAPTION_VIEW_MODEL_PREPROCESSING" : "IG_THREADS_APP_KARAOKE_CAPTION_ASR_REQUEST" : "IG_THREADS_APP_KARAOKE_CAPTION_EXTRACT_AUDIO" : "IG_THREADS_APP_KARAOKE_CAPTION_KARAOKE_CAPTION_COMPLETE_PROCESS";
    }
}
