package com.facebook.quicklog.identifiers;

public class HelpdeskOnWorkplace {
    public static final int ENTER_HELPDESK_MOBILE = 868232391;
    public static final short MODULE_ID = 13248;

    public static String getMarkerName(int i) {
        return i != 11463 ? "UNDEFINED_QPL_EVENT" : "HELPDESK_ON_WORKPLACE_ENTER_HELPDESK_MOBILE";
    }
}
