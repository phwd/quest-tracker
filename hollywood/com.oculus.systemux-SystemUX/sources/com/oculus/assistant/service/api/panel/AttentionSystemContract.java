package com.oculus.assistant.service.api.panel;

public class AttentionSystemContract {
    public static final int DEFAULT_DURATION = 7000;
    public static final String DEFAULT_ICON_NAME = "ic_attn_default";
    public static final String EXTRA_DATA = "data";
    public static final String KEY_ANIMATE = "animate";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_HIT_TESTABLE = "hit-testable";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_STATE = "state";
    public static final String KEY_STATE_ICON = "state-icon";
    public static final String KEY_STATE_ICON_PADDING = "state-icon-padding";
    public static final String KEY_STATE_IMAGE = "state-image";
    public static final String KEY_SUBMESSAGE = "submessage";
    public static final String KEY_TYPEFACE = "typeface";

    public static final class States {
        public static final String IDLE = "IDLE";
        public static final String LISTENING = "LISTENING";
        public static final String RESPONDING = "RESPONDING";
        public static final String THINKING = "THINKING";
        public static final String TRANSCRIPTION = "TRANSCRIPTION";
    }
}
