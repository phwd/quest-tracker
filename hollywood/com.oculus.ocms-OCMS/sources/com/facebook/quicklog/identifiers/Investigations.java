package com.facebook.quicklog.identifiers;

public class Investigations {
    public static final int EDIT_PROFILE = 716979633;
    public static final short MODULE_ID = 10940;
    public static final int REQUEST_LEGAL_APPROVAL = 716966485;
    public static final int START_PROFILE = 716966647;

    public static String getMarkerName(int i) {
        return i != 2645 ? i != 2807 ? i != 15793 ? "UNDEFINED_QPL_EVENT" : "INVESTIGATIONS_EDIT_PROFILE" : "INVESTIGATIONS_START_PROFILE" : "INVESTIGATIONS_REQUEST_LEGAL_APPROVAL";
    }
}
