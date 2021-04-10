package com.facebook.quicklog.identifiers;

public class WearableCamera {
    public static final int APP_START = 652223273;
    public static final int AUTO_ENHANCEMENT = 652219764;
    public static final int EDITING_IMAGE_PREVIEW = 652222976;
    public static final int EDITING_IMAGE_SAVE = 652228959;
    public static final int EDITOR_IMAGE_SAVE = 652221767;
    public static final int EDITOR_VIDEO_SAVE = 652222384;
    public static final int ENTER_GALLERY_GRID = 652226419;
    public static final int ENTER_GALLERY_PAGER_PHOTO = 652222469;
    public static final int ENTER_GALLERY_PAGER_VIDEO = 652221530;
    public static final int IMAGE_CAPTURE = 652229552;
    public static final int IMAGE_TRANSCODE = 652229033;
    public static final short MODULE_ID = 9952;
    public static final int SCROLL_PERF = 652225116;
    public static final int STORY_PUBLISH = 652217467;
    public static final int SWITCH_CAMERA = 652215982;
    public static final int VIDEO_CAPTURE = 652220699;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1710:
                return "WEARABLE_CAMERA_SWITCH_CAMERA";
            case 3195:
                return "WEARABLE_CAMERA_STORY_PUBLISH";
            case 5492:
                return "WEARABLE_CAMERA_AUTO_ENHANCEMENT";
            case 6427:
                return "WEARABLE_CAMERA_VIDEO_CAPTURE";
            case 7258:
                return "WEARABLE_CAMERA_ENTER_GALLERY_PAGER_VIDEO";
            case 7495:
                return "WEARABLE_CAMERA_EDITOR_IMAGE_SAVE";
            case 8112:
                return "WEARABLE_CAMERA_EDITOR_VIDEO_SAVE";
            case 8197:
                return "WEARABLE_CAMERA_ENTER_GALLERY_PAGER_PHOTO";
            case 8704:
                return "WEARABLE_CAMERA_EDITING_IMAGE_PREVIEW";
            case 9001:
                return "WEARABLE_CAMERA_APP_START";
            case 10844:
                return "WEARABLE_CAMERA_SCROLL_PERF";
            case 12147:
                return "WEARABLE_CAMERA_ENTER_GALLERY_GRID";
            case 14687:
                return "WEARABLE_CAMERA_EDITING_IMAGE_SAVE";
            case 14761:
                return "WEARABLE_CAMERA_IMAGE_TRANSCODE";
            case 15280:
                return "WEARABLE_CAMERA_IMAGE_CAPTURE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
