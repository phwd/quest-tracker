package com.facebook.quicklog.identifiers;

public class Facecast {
    public static final int FACECASTFORM_CREATE = 14876674;
    public static final int FACECASTFORM_FIRST_COMMENT = 14889538;
    public static final int FACECASTFORM_LOAD_FORMATS = 14878766;
    public static final int FACECASTFORM_ONACTIVATE = 14876673;
    public static final int FACECASTFORM_RECORDING = 14876675;
    public static final int FACECAST_RECORDING_STATE_TRANSITION = 14876680;
    public static final int FACECAST_STATE_TRANSITION = 14876679;
    public static final int MESSENGER_COWATCH_START = 14876681;
    public static final short MODULE_ID = 227;
    public static final int PLUGIN_ATTACH = 14876676;
    public static final int PLUGIN_DETACH = 14876677;
    public static final int SEND_COMMENT_ANDROID = 14876678;

    public static String getMarkerName(int i) {
        if (i == 2094) {
            return "FACECAST_FACECASTFORM_LOAD_FORMATS";
        }
        if (i == 12866) {
            return "FACECAST_FACECASTFORM_FIRST_COMMENT";
        }
        switch (i) {
            case 1:
                return "FACECAST_FORM_ACTIVATE";
            case 2:
                return "FACECAST_FORM_CREATE";
            case 3:
                return "FACECAST_FORM_START_RECORDING";
            case 4:
                return "FACECAST_PLUGIN_ATTACH";
            case 5:
                return "FACECAST_PLUGIN_DETACH";
            case 6:
                return "FACECAST_SEND_COMMENT_ANDROID";
            case 7:
                return "FACECAST_FACECAST_STATE_TRANSITION";
            case 8:
                return "FACECAST_FACECAST_RECORDING_STATE_TRANSITION";
            case 9:
                return "FACECAST_MESSENGER_COWATCH_START";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
