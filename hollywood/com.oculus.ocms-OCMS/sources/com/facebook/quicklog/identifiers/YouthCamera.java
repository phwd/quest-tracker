package com.facebook.quicklog.identifiers;

public class YouthCamera {
    public static final int CAMERA_TTI = 27131916;
    public static final int DISPLAY_CAPTURED_VIDEO = 27131915;
    public static final int EFFECT_APPLY = 27131905;
    public static final int EFFECT_CHECK_CACHE = 27131906;
    public static final int EFFECT_DOWNLOAD = 27131907;
    public static final int EFFECT_PICKER_LOAD = 27131908;
    public static final short MODULE_ID = 414;
    public static final int PHOTO_CAPTURE = 27131910;
    public static final int PHOTO_CAPTURE_BITMAP = 27131911;
    public static final int PHOTO_CAPTURE_PROCESS = 27131909;
    public static final int PHOTO_PROCESSING = 27131912;
    public static final int START_VIDEO_CAPTURE = 27131913;
    public static final int STOP_VIDEO_CAPTURE = 27131914;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "YOUTH_CAMERA_EFFECT_APPLY";
            case 2:
                return "YOUTH_CAMERA_EFFECT_CHECK_CACHE";
            case 3:
                return "YOUTH_CAMERA_EFFECT_DOWNLOAD";
            case 4:
                return "YOUTH_CAMERA_EFFECT_PICKER_LOAD";
            case 5:
                return "YOUTH_CAMERA_PHOTO_CAPTURE_PROCESS";
            case 6:
                return "YOUTH_CAMERA_PHOTO_CAPTURE";
            case 7:
                return "YOUTH_CAMERA_PHOTO_CAPTURE_BITMAP";
            case 8:
                return "YOUTH_CAMERA_PHOTO_PROCESSING";
            case 9:
                return "YOUTH_CAMERA_START_VIDEO_CAPTURE";
            case 10:
                return "YOUTH_CAMERA_STOP_VIDEO_CAPTURE";
            case 11:
                return "YOUTH_CAMERA_DISPLAY_CAPTURED_VIDEO";
            case 12:
                return "YOUTH_CAMERA_CAMERA_TTI";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
