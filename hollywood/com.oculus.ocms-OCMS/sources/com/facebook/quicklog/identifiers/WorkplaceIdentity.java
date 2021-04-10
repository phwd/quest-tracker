package com.facebook.quicklog.identifiers;

public class WorkplaceIdentity {
    public static final int CHANGE_PASSWORD = 54263812;
    public static final int INIT_LOGOUT_FROM_ALL_SESSIONS_IN_SESSION_SECTION = 54263820;
    public static final int LOAD_SECURITY_AND_LOGIN_TAB = 54263819;
    public static final int LOGIN_WITH_PASSWORD = 54263810;
    public static final int LOGOUT_FROM_ALL_SESSIONS_IN_SESSION_SECTION = 54263816;
    public static final int LOGOUT_FROM_SESSION_IN_SESSION_SECTION = 54263815;
    public static final int LOGOUT_FROM_WORKPLACE = 54263818;
    public static final short MODULE_ID = 828;
    public static final int SAVE_LOGIN_ALERTS_SETTINGS = 54263817;
    public static final int SEE_LESS_SESSIONS_IN_SESSION_SECTION = 54263814;
    public static final int SEE_MORE_SESSIONS_IN_SESSION_SECTION = 54263813;
    public static final int TEST_SSO_PROVIDER_DATA = 54285047;
    public static final int WORK_AUTH_LOGIN_API = 54266141;

    public static String getMarkerName(int i) {
        if (i == 2) {
            return "WORKPLACE_IDENTITY_LOGIN_WITH_PASSWORD";
        }
        if (i == 2333) {
            return "WORKPLACE_IDENTITY_WORK_AUTH_LOGIN_API";
        }
        if (i == 21239) {
            return "WORKPLACE_IDENTITY_TEST_SSO_PROVIDER_DATA";
        }
        switch (i) {
            case 4:
                return "WORKPLACE_IDENTITY_CHANGE_PASSWORD";
            case 5:
                return "WORKPLACE_IDENTITY_SEE_MORE_SESSIONS_IN_SESSION_SECTION";
            case 6:
                return "WORKPLACE_IDENTITY_SEE_LESS_SESSIONS_IN_SESSION_SECTION";
            case 7:
                return "WORKPLACE_IDENTITY_LOGOUT_FROM_SESSION_IN_SESSION_SECTION";
            case 8:
                return "WORKPLACE_IDENTITY_LOGOUT_FROM_ALL_SESSIONS_IN_SESSION_SECTION";
            case 9:
                return "WORKPLACE_IDENTITY_SAVE_LOGIN_ALERTS_SETTINGS";
            case 10:
                return "WORKPLACE_IDENTITY_LOGOUT_FROM_WORKPLACE";
            case 11:
                return "WORKPLACE_IDENTITY_LOAD_SECURITY_AND_LOGIN_TAB";
            case 12:
                return "WORKPLACE_IDENTITY_INIT_LOGOUT_FROM_ALL_SESSIONS_IN_SESSION_SECTION";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
