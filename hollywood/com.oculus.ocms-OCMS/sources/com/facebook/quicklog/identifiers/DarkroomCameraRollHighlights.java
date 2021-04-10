package com.facebook.quicklog.identifiers;

public class DarkroomCameraRollHighlights {
    public static final int FOREGROUND_ANALYZER_OCEAN_FRAME_CONVERSION = 34340878;
    public static final int MEDIA_ANALYER_MODEL_LOAD_FB4A = 34340868;
    public static final int MEDIA_ANALYZER_IMAGE_GET_BITMAP_NO_RESIZING = 34340873;
    public static final int MEDIA_ANALYZER_IMAGE_LOAD_FB4A = 34340867;
    public static final int MEDIA_ANALYZER_IMAGE_RESIZING_FB4A = 34340869;
    public static final int MEDIA_ANALYZER_INIT_FB4A = 34340865;
    public static final int MEDIA_ANALYZER_MODEL_LOAD = 34340876;
    public static final int MEDIA_ANALYZER_ON_DEMAND_IMAGE_LOAD = 34340871;
    public static final int MEDIA_INDEXER_EXECUTION_FB4A = 34340866;
    public static final int MEDIA_INDEXER_ON_DEMAND_EXECUTION = 34340872;
    public static final short MODULE_ID = 524;
    public static final int ON_DEMAND_ANALYZER_OCEAN_FRAME_CONVERSION = 34340877;
    public static final int UEG_HIGHLIGHT_APPLICATION = 34340875;
    public static final int UEG_THUMBNAIL_LOAD = 34340870;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_INIT_FB4A";
            case 2:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_INDEXER_EXECUTION_FB4A";
            case 3:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_IMAGE_LOAD_FB4A";
            case 4:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYER_MODEL_LOAD_FB4A";
            case 5:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_IMAGE_RESIZING_FB4A";
            case 6:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_UEG_THUMBNAIL_LOAD";
            case 7:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_ON_DEMAND_IMAGE_LOAD";
            case 8:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_INDEXER_ON_DEMAND_EXECUTION";
            case 9:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_IMAGE_GET_BITMAP_NO_RESIZING";
            case 10:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 11:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_UEG_HIGHLIGHT_APPLICATION";
            case 12:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_MEDIA_ANALYZER_MODEL_LOAD";
            case 13:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_ON_DEMAND_ANALYZER_OCEAN_FRAME_CONVERSION";
            case 14:
                return "DARKROOM_CAMERA_ROLL_HIGHLIGHTS_FOREGROUND_ANALYZER_OCEAN_FRAME_CONVERSION";
        }
    }
}
