package com.facebook.quicklog.identifiers;

public class AndroidCamera {
    public static final int CAPTURE_IMAGE = 10682375;
    public static final int CLOSE_CAMERA = 10682373;
    public static final int FLIP_CAMERA = 10682372;
    public static final short MODULE_ID = 163;
    public static final int OPEN_CAMERA = 10682369;
    public static final int PREVIEW_FRAME_READY = 10682374;
    public static final int STARTING_TEXT_MODE_FB4A = 10682376;
    public static final int START_RECORDING = 10682370;
    public static final int STOP_RECORDING = 10682371;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "ANDROID_CAMERA_OPEN_CAMERA";
            case 2:
                return "ANDROID_CAMERA_START_RECORDING";
            case 3:
                return "ANDROID_CAMERA_STOP_RECORDING";
            case 4:
                return "ANDROID_CAMERA_FLIP_CAMERA";
            case 5:
                return "ANDROID_CAMERA_CLOSE_CAMERA";
            case 6:
                return "ANDROID_CAMERA_PREVIEW_FRAME_READY";
            case 7:
                return "ANDROID_CAMERA_CAPTURE_IMAGE";
            case 8:
                return "ANDROID_CAMERA_STARTING_TEXT_MODE_FB4A";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
