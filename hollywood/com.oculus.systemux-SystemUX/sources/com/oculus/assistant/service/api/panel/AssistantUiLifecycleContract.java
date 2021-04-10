package com.oculus.assistant.service.api.panel;

public final class AssistantUiLifecycleContract {
    public static final String ACTION_UI_CONTROL_EVENT = "com.oculus.assistant.ACTION_UI_CONTROL_SERVICE";
    public static final String ACTION_UI_EVENT_BROADCAST = "com.oculus.assistant.UI_LIFECYCLE_EVENT";
    public static final String EXTRA_EVENT_DATA = "EXTRA_EVENT_DATA_SOURCE";
    public static final String EXTRA_EVENT_ID = "EXTRA_EVENT_ID";
    public static final String EXTRA_SURFACE_ID = "EXTRA_SURFACE_ID";
    public static final String EXTRA_SURFACE_TYPE = "EXTRA_SURFACE_TYPE";

    public static final class CloseSources {
        public static final String BACK = "back";
        public static final String HOME = "home-button";
        public static final String TASK_BAR_NAVIGATION = "task-bar-navigation";
    }

    public static final class EventData {
        public static final String CLOSE_SOURCE = "source";
    }

    public static final class LayerEvents {
        public static final String ATTENTION_SYSTEM_DESTROYED = "ATTENTION_SYSTEM_DESTROYED";
        public static final String ATTENTION_SYSTEM_ON_CLOSE = "ATTENTION_SYSTEM_ON_CLOSE";
        public static final String MAIN_LAYER_DESTROYED = "MAIN_LAYER_DESTROYED";
        public static final String MAIN_LAYER_ON_CLOSE = "MAIN_LAYER_ON_CLOSE";
    }

    public static final class SurfaceEvents {
        public static final String SURFACE_CREATED = "SURFACE_CREATED";
        public static final String SURFACE_HIDDEN = "SURFACE_HIDDEN";
        public static final String SURFACE_HIDING = "SURFACE_HIDING";
        public static final String SURFACE_SHOWN = "SURFACE_SHOWN";
    }

    public static final class SystemEvents {
        public static final String SYSTEM_BACK_PRESSED = "SYSTEM_BACK_PRESSED";
    }
}
