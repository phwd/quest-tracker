package com.facebook.quicklog.identifiers;

public class Inspirations {
    public static final int CAMERAFRAGMENT_ONCREATE = 10551298;
    public static final int CAMERAFRAGMENT_ONCREATEVIEW = 10551299;
    public static final int CAMERAFRAGMENT_ONRESUME = 10551300;
    public static final int CAMERA_ROLL_LOAD_TTI = 10551306;
    public static final short MODULE_ID = 161;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 10 ? "UNDEFINED_QPL_EVENT" : "INSPIRATIONS_CAMERA_ROLL_LOAD_TTI" : "INSPIRATIONS_CAMERAFRAGMENT_ONRESUME" : "INSPIRATIONS_CAMERAFRAGMENT_ONCREATEVIEW" : "INSPIRATIONS_CAMERAFRAGMENT_ONCREATE";
    }
}
