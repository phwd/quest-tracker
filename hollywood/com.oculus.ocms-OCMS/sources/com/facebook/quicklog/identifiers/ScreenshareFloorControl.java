package com.facebook.quicklog.identifiers;

public class ScreenshareFloorControl {
    public static final short MODULE_ID = 3204;
    public static final int PRESENTER = 209979636;
    public static final int VIEWER = 209989735;

    public static String getMarkerName(int i) {
        return i != 2292 ? i != 12391 ? "UNDEFINED_QPL_EVENT" : "SCREENSHARE_FLOOR_CONTROL_VIEWER" : "SCREENSHARE_FLOOR_CONTROL_PRESENTER";
    }
}
