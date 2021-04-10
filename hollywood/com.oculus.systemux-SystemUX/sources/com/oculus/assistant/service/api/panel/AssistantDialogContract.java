package com.oculus.assistant.service.api.panel;

public final class AssistantDialogContract {
    public static final String CLEAR_DIALOG_STACK = "clear-dialog-stack";
    public static final String CLEAR_QUEUE_ON_CLOSE = "clear-on-close";
    public static final String COMPONENTS = "components";
    public static final String DATA = "data";
    public static final String MESSAGE_TYPE = "message-type";
    public static final String QUEUE = "queue";
    public static final String RESPONSE_DATA = "response-data";
    public static final String TARGET = "target";
    public static final String TYPE = "type";
    public static final String USE_PANEL_DIALOGS = "use-panel-dialogs";

    public static final class AttentionSystem {
        public static final String STATE_ICON = "state-icon";
        public static final String STATE_ICON_PADDING = "state-icon-padding";
    }

    public static final class Container {
        public static final String CLOSE_ON_CLICK_OUTSIDE = "close-on-click-outside";
        public static final String CONTAINER_SETTINGS = "container-settings";
        public static final String HEIGHT = "height";
        public static final String USE_CONTENT_MARGINS = "use-content-margins";
        public static final String WIDTH = "width";
    }

    public static final class Dialog {
        public static final String BACK = "back";
        public static final String CLOSE_BUTTON_VISIBLE = "close-visible";
        public static final String DESCRIPTION = "description";
        public static final String FORWARD = "forward";
        public static final String HEIGHT = "height";
        public static final String HERO = "hero";
        public static final String HIT_TESTABLE = "hit-testable";
        public static final String ID = "id";
        public static final String INFORMATION_BOX = "information-box";
        public static final String REMOVE_ON_HIDE = "remove-on-hide";
        public static final String TITLE = "title";
        public static final String VIDEO = "video";
        public static final String WIDTH = "width";

        public static final class Buttons {
            public static final String ACTION_BACK = "back";
            public static final String ACTION_CLOSE = "close";
            public static final String ACTION_FORWARD = "forward";
            public static final String ACTION_PRIMARY = "primary";
            public static final String ACTION_SECONDARY = "secondary";
            public static final String ACTION_TERTIARY = "tertiary";
            public static final String AUTO_CLOSE = "auto-close";
            public static final String AUTO_CLOSE_PRIMARY = "auto-close-primary";
            public static final String AUTO_CLOSE_SECONDARY = "auto-close-secondary";
            public static final String AUTO_CLOSE_TERTIARY = "auto-close-tertiary";
        }

        public static final class LifeCycle {
            public static final String ACTION_DIALOG_HIDE = "hide";
            public static final String ACTION_DIALOG_VISIBLE = "visible";
            public static final String ACTION_MAIN_LAYER_HIDE = "main-layer-hide";
        }
    }

    public static final class DialogResponse {
        public static final String ACTION = "action";
        public static final String EXTRAS = "extras";
        public static final String ID = "id";
    }

    public static final class DialogTypes {
        public static final String ASSISTANT_SYSTEM_DIALOG = "dialog";
        public static final String ATTENTION_SYSTEM_SURFACE = "attention-system-surface";
        public static final String EXPANDED_ATTENTION_SYSTEM = "exp-attn-system";
        public static final String MULTISUGGESTION_DIALOG = "multisuggestion-dialog";
        public static final String SYSTEM_DIALOG = "system-dialog";
    }

    public static final class MultiselectionDialog {
        public static final String SECTIONS = "sections";

        public static final class Items {
            public static final String ID = "id";
            public static final String LABEL = "label";
        }

        public static final class Section {
            public static final String HEADER = "header";
            public static final String ITEMS = "items";
        }
    }
}
