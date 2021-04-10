package com.facebook.quicklog.identifiers;

public class Mcom {
    public static final int CHECKOUT = 543627483;
    public static final int INVOICE_CREATION = 543624632;
    public static final short MODULE_ID = 8295;

    public static String getMarkerName(int i) {
        return i != 3512 ? i != 6363 ? "UNDEFINED_QPL_EVENT" : "MCOM_CHECKOUT" : "MCOM_INVOICE_CREATION";
    }
}
