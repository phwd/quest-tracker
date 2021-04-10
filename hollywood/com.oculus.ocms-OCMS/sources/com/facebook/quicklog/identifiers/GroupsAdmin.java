package com.facebook.quicklog.identifiers;

public class GroupsAdmin {
    public static final int GROUPS_ADMIN_MODULE = 20585629;
    public static final int MEMBER_REQUESTS_TAIL_LOAD = 20578319;
    public static final int MEMBER_SCREENING_INITIAL_TTI = 20578305;
    public static final short MODULE_ID = 314;
    public static final int PRIVACY_CHANGE = 20578321;
    public static final int TOOLS_TTI = 20578306;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 15 ? i != 17 ? i != 7325 ? "UNDEFINED_QPL_EVENT" : "GROUPS_ADMIN_GROUPS_ADMIN_MODULE" : "GROUPS_ADMIN_PRIVACY_CHANGE" : "GROUPS_ADMIN_MEMBER_REQUESTS_TAIL_LOAD" : "GROUPS_ADMIN_TOOLS_TTI" : "GROUPS_ADMIN_MEMBER_SCREENING_INITIAL_TTI";
    }
}
