package com.facebook.quicklog.identifiers;

public class ObjSel {
    public static final short MODULE_ID = 226;
    public static final int OBJSEL_FETCH = 14811137;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "OBJ_SEL_OBJSEL_FETCH";
    }
}
