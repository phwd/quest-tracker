package com.facebook.quicklog.identifiers;

public class Qrcode {
    public static final short MODULE_ID = 257;
    public static final int QRCODE_SCANNER_SCAN = 16842754;
    public static final int QRCODE_SCANNER_TTI = 16842753;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "QRCODE_QRCODE_SCANNER_SCAN" : "QRCODE_QRCODE_SCANNER_TTI";
    }
}
