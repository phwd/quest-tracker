package com.facebook.quicklog.identifiers;

public class MsysBootstrapAndroid {
    public static final short MODULE_ID = 810;
    public static final int MSYS_BOOTSTRAP = 53084161;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "MSYS_BOOTSTRAP_ANDROID_MSYS_BOOTSTRAP";
    }
}
