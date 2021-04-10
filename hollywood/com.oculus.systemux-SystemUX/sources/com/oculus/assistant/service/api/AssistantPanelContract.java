package com.oculus.assistant.service.api;

public final class AssistantPanelContract {
    public static final String HIDE = "hide";
    public static final String HIDE_ATTENTION = "hide-attention";
    public static final String HIDE_ATTENTION_INSTANT = "hide-attention-instant";
    public static final String REQUEST_PANEL_STATUS = "request-panel-status";
    public static final String SHOW = "show";
    public static final String SHOW_ATTENTION = "show-attention";
    public static final String TAKE_SCREENSHOT = "take-screenshot";

    public static final class Controls {
        public static final String ATTENTION_SYSTEM_CONTROL = "ATTENTION_SYSTEM_CONTROL";

        public static final class AttentionSystemControl {
            public static final String ACTION_CLOSE = "hide-attention";
        }
    }
}
