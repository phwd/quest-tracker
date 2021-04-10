package com.facebook.quicklog.identifiers;

public class AssistantInteractionChanged {
    public static final short MODULE_ID = 632;
    public static final int SPEECH_STARTING = 41418759;

    public static String getMarkerName(int i) {
        return i != 7 ? "UNDEFINED_QPL_EVENT" : "ASSISTANT_INTERACTION_CHANGED_SPEECH_STARTING";
    }
}
