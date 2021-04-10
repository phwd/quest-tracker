package com.facebook.quicklog.identifiers;

public class AndroidWebview {
    public static final short MODULE_ID = 295;
    public static final int WEBVIEW_URI_REDIRECTOR_CONSTRUCT = 19333121;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "ANDROID_WEBVIEW_WEBVIEW_URI_REDIRECTOR_CONSTRUCT";
    }
}
