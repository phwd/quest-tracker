package com.oculus.assistant.service.api.panel;

public final class AssistantComponentContract {

    public static final class Components {
        public static final String COMPONENTS = "components";
        public static final String DATA = "data";
        public static final String TYPE = "type";

        public static final class BaseComponent {
            public static final String BACKGROUND_COLOR = "background-color";
            public static final String HEIGHT = "height";
            public static final String ID = "id";
            public static final String LAYOUT_GRAVITY = "layout-gravity";
            public static final String WIDTH = "width";
        }

        public static final class ButtonComponent {
            public static final String BUTTON_STYLE = "button-style";
            public static final String ID = "id";
            public static final String TEXT = "text";
            public static final String TYPE = "button";

            public static final class ButtonStyles {
                public static final String BORDERED = "bordered";
                public static final String BORDERLESS = "borderless";
                public static final String PRIMARY = "primary";
            }
        }

        public static final class CheckboxComponent {
            public static final String CHECKED = "checked";
            public static final String TYPE = "checkbox";
        }

        public static final class FeedbackComponent {
            public static final String LEFT_ICON_1 = "left-icon-1";
            public static final String LEFT_ICON_2 = "left-icon-2";
            public static final String RIGHT_ICON_1 = "right-icon-1";
            public static final String RIGHT_ICON_2 = "right-icon-2";
            public static final String TEXT = "text";
            public static final String TYPE = "feedback";
        }

        public static final class FrameLayoutComponent {
            public static final String LAYOUT_GRAVITY = "layout-gravity";
            public static final String TYPE = "frame-layout";
        }

        public static final class InformationBoxComponent {
            public static final String TEXT = "text";
            public static final String TYPE = "information_box";
        }

        public static final class LinearLayoutComponent {
            public static final String LAYOUT_GRAVITY = "layout-gravity";
            public static final String ORIENTATION = "orientation";
            public static final String TYPE = "linear-layout";
            public static final String WEIGHT = "weight";
        }

        public static final class ListItemComponent {
            public static final String CHEVRON = "chevron";
            public static final String ICON = "icon";
            public static final String ID = "id";
            public static final String PRIMARY_TEXT = "primary";
            public static final String SECONDARY_TEXT = "secondary";
            public static final String TYPE = "list-item";

            public static final class Icon {
                public static final String NAME = "icon-name";
                public static final String PACKAGE = "package";
                public static final String RESOURCE = "res";
            }
        }

        public static final class RemoteImageViewComponent {
            public static final String ANIMATIONS = "animations";
            public static final String ASSET = "asset";
            public static final String BACKGROUND_ASSET = "background-asset";
            public static final String BACKGROUND_NONE = "background-none";
            public static final String BACKGROUND_RES = "background-res";
            public static final String ID = "id";
            public static final String IMAGE_THUMB = "image-thumb";
            public static final String INTERACTIVE_IMAGE_STYLE = "interactive-image-style";
            public static final String LOOP = "loop";
            public static final String NONE = "none";
            public static final String PACKAGE = "package";
            public static final String PATH = "path";
            public static final String RES = "res";
            public static final String RES_PROVIDER = "res-provider";
            public static final String THUMB_KIND = "thumb-kind";
            public static final String TRANSITION = "transition";
            public static final String TYPE = "image";
            public static final String URI = "uri";
            public static final String VIDEO_THUMB = "video-thumb";

            public static final class ImageStyle {
                public static final String BORDERLESS = "borderless";
                public static final String NONE = "none";
                public static final String PRIMARY = "primary";
                public static final String ROUND = "round";
                public static final String SECONDARY = "secondary";
            }

            public static final class Transitions {
                public static final String CROSSFADE = "crossfade";
                public static final String FADE_OUT = "fade-out";
            }
        }

        public static final class TextComponent {
            public static final String GRAVITY = "gravity";
            public static final String STYLE = "style";
            public static final String TEXT = "text";
            public static final String TYPE = "text";
            public static final String VALUE = "value";

            public static final class Styles {
                public static final String BODY1 = "body1";
                public static final String BODY2 = "body2";
                public static final String H1 = "h1";
                public static final String H2 = "h2";
                public static final String H3 = "h3";
                public static final String TITLE = "title";
            }
        }

        public static final class ToggleComponent {
            public static final String CHECKED = "checked";
            public static final String TYPE = "toggle";
        }
    }

    public static final class Gravity {
        public static final String BOTTOM = "bottom";
        public static final String CENTER = "center";
        public static final String CENTER_HORIZONTAL = "center-horizontal";
        public static final String CENTER_VERTICAL = "center-vertical";
        public static final String GRAVITY = "gravity";
        public static final String LEFT = "left";
        public static final String RIGHT = "right";
        public static final String TOP = "top";
    }

    public static final class Margins {
        public static final String BOTTOM = "bottom";
        public static final String LEFT = "left";
        public static final String MARGINS = "margins";
        public static final String RIGHT = "right";
        public static final String TOP = "top";
    }

    public static final class Padding {
        public static final String BOTTOM = "bottom";
        public static final String LEFT = "left";
        public static final String PADDING = "padding";
        public static final String RIGHT = "right";
        public static final String TOP = "top";
    }
}
