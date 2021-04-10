package com.facebook.quicklog.identifiers;

public class Keyframes {
    public static final int DECODE = 15007747;
    public static final short MODULE_ID = 229;
    public static final int PLAY_DURATION = 15007746;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "KEYFRAMES_DECODE" : "Play Duration";
    }
}
