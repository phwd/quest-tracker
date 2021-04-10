package com.facebook.quicklog.identifiers;

public class MonzaApp {
    public static final int BT_CONNECTION = 564015126;
    public static final int BT_PAIRING = 564008620;
    public static final int COMPONENT_TTI = 564014947;
    public static final int GALLERY_MEDIA_DELETE_CLOUD_SYNC = 564015597;
    public static final int GALLERY_MEDIA_UPLOAD = 564011287;
    public static final short MODULE_ID = 8606;

    public static String getMarkerName(int i) {
        return i != 5804 ? i != 8471 ? i != 12131 ? i != 12310 ? i != 12781 ? "UNDEFINED_QPL_EVENT" : "MONZA_APP_GALLERY_MEDIA_DELETE_CLOUD_SYNC" : "MONZA_APP_BT_CONNECTION" : "MONZA_APP_COMPONENT_TTI" : "MONZA_APP_GALLERY_MEDIA_UPLOAD" : "MONZA_APP_BT_PAIRING";
    }
}
