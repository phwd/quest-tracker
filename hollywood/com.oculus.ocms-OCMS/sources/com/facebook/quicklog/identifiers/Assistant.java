package com.facebook.quicklog.identifiers;

public class Assistant {
    public static final int INTERACTION_LATENCY = 50790401;
    public static final short MODULE_ID = 775;
    public static final int OCULUS_ASSISTANT_STARTUP_LATENCY = 50790403;
    public static final int OCULUS_DICTATION_LATENCY = 50790404;
    public static final int OCULUS_VOICE_COMMAND_LATENCY = 50790402;
    public static final int STARTUP_LATENCY = 50801291;
    public static final int WAKE_WORD_VALIDATION_LATENCY = 50806664;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 10891 ? i != 16264 ? "UNDEFINED_QPL_EVENT" : "ASSISTANT_WAKE_WORD_VALIDATION_LATENCY" : "ASSISTANT_STARTUP_LATENCY" : "ASSISTANT_OCULUS_DICTATION_LATENCY" : "ASSISTANT_OCULUS_ASSISTANT_STARTUP_LATENCY" : "ASSISTANT_OCULUS_VOICE_COMMAND_LATENCY" : "ASSISTANT_INTERACTION_LATENCY";
    }
}
