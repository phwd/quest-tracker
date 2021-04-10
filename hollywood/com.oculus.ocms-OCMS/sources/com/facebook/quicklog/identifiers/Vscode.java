package com.facebook.quicklog.identifiers;

public class Vscode {
    public static final int CORE_EXTENSION_ACTIVATION = 58471621;
    public static final int EXTENSIONS_START = 58458114;
    public static final int EXTENSIONS_START_V2 = 58464469;
    public static final int ISL_SMARTLOG_LOAD = 58461407;
    public static final short MODULE_ID = 892;
    public static final int WEBVIEW_LOAD = 58458115;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 3 ? i != 3295 ? i != 6357 ? i != 13509 ? "UNDEFINED_QPL_EVENT" : "VSCODE_CORE_EXTENSION_ACTIVATION" : "VSCODE_EXTENSIONS_START_V2" : "VSCODE_ISL_SMARTLOG_LOAD" : "VSCODE_WEBVIEW_LOAD" : "VSCODE_EXTENSIONS_START";
    }
}
