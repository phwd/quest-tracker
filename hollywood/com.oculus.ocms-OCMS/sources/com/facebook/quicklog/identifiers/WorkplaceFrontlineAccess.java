package com.facebook.quicklog.identifiers;

public class WorkplaceFrontlineAccess {
    public static final int ACCESS_CODE_DIALOG_DISPLAYED = 53346306;
    public static final int CREATE_PERSON_IN_AREA = 53359965;
    public static final int FORCE_PASSWORD_RESET = 53346314;
    public static final int LOGOUT_OTHER_USER = 53346315;
    public static final short MODULE_ID = 814;
    public static final int SEARCH_PERSON_IN_AREA = 53352841;
    public static final int SEND_CLAIM_LINK_VIA_EMAIL = 53350894;

    public static String getMarkerName(int i) {
        return i != 2 ? i != 4590 ? i != 6537 ? i != 13661 ? i != 10 ? i != 11 ? "UNDEFINED_QPL_EVENT" : "WORKPLACE_FRONTLINE_ACCESS_LOGOUT_OTHER_USER" : "WORKPLACE_FRONTLINE_ACCESS_FORCE_PASSWORD_RESET" : "WORKPLACE_FRONTLINE_ACCESS_CREATE_PERSON_IN_AREA" : "WORKPLACE_FRONTLINE_ACCESS_SEARCH_PERSON_IN_AREA" : "WORKPLACE_FRONTLINE_ACCESS_SEND_CLAIM_LINK_VIA_EMAIL" : "WORKPLACE_FRONTLINE_ACCESS_ACCESS_CODE_DIALOG_DISPLAYED";
    }
}
