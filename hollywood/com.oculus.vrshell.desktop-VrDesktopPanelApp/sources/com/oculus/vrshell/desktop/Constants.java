package com.oculus.vrshell.desktop;

public class Constants {
    public static final int DEFAULT_DENSITY = 160;
    public static final int DEFAULT_HEIGHT = 540;
    public static final int DEFAULT_KEYBOARD_POSITION = 0;
    public static final int DEFAULT_SHAPE = 2;
    public static final int DEFAULT_WIDTH = 720;
    public static final int DPAD_CENTER_RADIUS = 48;
    public static final int DPAD_CENTER_RADIUS_SQUARE = 2304;
    public static final String KEY_DENSITY = "panelDensity";
    public static final String KEY_HEIGHT = "panelHeight";
    public static final String KEY_INPUT_MODE = "inputMode";
    public static final String KEY_KEYBOARD_POSITION = "keyboardPosition";
    public static final String KEY_SHAPE = "panelShape";
    public static final String KEY_SHELL_RENDER_SCALE = "_oc_shell_render_scale";
    public static final String KEY_TARGET = "target";
    public static final String KEY_URI = "uri";
    public static final String KEY_WIDTH = "panelWidth";
    public static final int PANEL_SHAPE_LANDSCAPE_CYLINDER = 2;
    public static final long PBUTTON_A = 1;
    public static final long PBUTTON_B = 2;
    public static final long PBUTTON_BACK = 32;
    public static final long PBUTTON_JOYSTICK_MOVE = 68719476736L;
    public static final long PBUTTON_REMOTE_TOUCH = 2147483648L;
    public static final long PBUTTON_RIGHT_TRIGGER = 256;
    public static final long PBUTTON_SWIPE_BACK = 67108864;
    public static final long PBUTTON_SWIPE_DOWN = 16777216;
    public static final long PBUTTON_SWIPE_FORWARD = 33554432;
    public static final long PBUTTON_SWIPE_UP = 8388608;
    public static final long PBUTTON_X = 4;
    public static final long PBUTTON_Y = 8;
    public static final String QUERY_PARAM_ACTION = "action";
    public static final String QUERY_PARAM_DATA = "intent_data";
    public static final float THUMBSTICK_SCROLL_FACTOR = 0.25f;
    public static final int TOUCH_MAX_X = 320;
    public static final int TOUCH_MAX_Y = 320;
    public static final float TOUCH_SCROLL_THRESHOLD = 50.0f;
    public static final String URI_SCHEME = "vrdesktop";

    public static long getAnyBackFlagValue() {
        return 42;
    }

    public static boolean isSurfaceParam(String key) {
        return KEY_WIDTH.equals(key) || KEY_HEIGHT.equals(key) || KEY_DENSITY.equals(key) || KEY_SHAPE.equals(key) || KEY_KEYBOARD_POSITION.equals(key);
    }
}
