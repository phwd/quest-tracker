package com.facebook.quicklog.identifiers;

public class Logout {
    public static final int AFTER_LOGOUT = 9699336;
    public static final int AFTER_LOGOUT_COMPONENT = 9699337;
    public static final int APP_SESSION_CLEAR_USER_DATA = 9699357;
    public static final int BEFORE_LOGOUT = 9699330;
    public static final int BEFORE_LOGOUT_COMPONENT = 9699331;
    public static final int CLEAR_FB_APP_USER_DATA_COMPONENT = 9699354;
    public static final int CLEAR_PRIVACY_CRITICAL_KEYS = 9699340;
    public static final int CLEAR_PRIVACY_CRITICAL_KEYS_COMPONENT = 9699341;
    public static final int CLEAR_USER_AUTH_DATA = 9699344;
    public static final int CLEAR_USER_DATA = 9699342;
    public static final int CLEAR_USER_DATA_COMPONENT = 9699343;
    public static final int EXPIRE_SESSION = 9699334;
    public static final int EXPIRE_SESSION_ASYNC = 9699353;
    public static final int HANDLE_DITTO_LOGOUT = 9699356;
    public static final int HANDLE_LOGOUT = 9699329;
    public static final int LOGOUT_COMPLETE = 9699338;
    public static final int LOGOUT_COMPLETE_COMPONENT = 9699339;
    public static final int LOGOUT_E2E = 9699359;
    public static final int LOGOUT_HELPER = 9699335;
    public static final short MODULE_ID = 148;
    public static final int PRELOAD_COMPONENT = 9699358;
    public static final int UNREGISTER_PUSH_TOKEN = 9699332;
    public static final int UNREGISTER_PUSH_TOKEN_ASYNC = 9699351;
    public static final int UNREGISTER_PUSH_TOKEN_ASYNC_TAG = 9699355;
    public static final int UNREGISTER_PUSH_TOKEN_COMPONENT = 9699333;
    public static final int UNREGISTER_PUSH_TOKEN_COMPONENT_ASYNC = 9699352;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "LOGOUT_HANDLE_LOGOUT";
            case 2:
                return "LOGOUT_BEFORE_LOGOUT";
            case 3:
                return "LOGOUT_BEFORE_LOGOUT_COMPONENT";
            case 4:
                return "LOGOUT_UNREGISTER_PUSH_TOKEN";
            case 5:
                return "LOGOUT_UNREGISTER_PUSH_TOKEN_COMPONENT";
            case 6:
                return "LOGOUT_EXPIRE_SESSION";
            case 7:
                return "LOGOUT_LOGOUT_HELPER";
            case 8:
                return "LOGOUT_AFTER_LOGOUT";
            case 9:
                return "LOGOUT_AFTER_LOGOUT_COMPONENT";
            case 10:
                return "LOGOUT_LOGOUT_COMPLETE";
            case 11:
                return "LOGOUT_LOGOUT_COMPLETE_COMPONENT";
            case 12:
                return "LOGOUT_CLEAR_PRIVACY_CRITICAL_KEYS";
            case 13:
                return "LOGOUT_CLEAR_PRIVACY_CRITICAL_KEYS_COMPONENT";
            case 14:
                return "LOGOUT_CLEAR_USER_DATA";
            case 15:
                return "LOGOUT_CLEAR_USER_DATA_COMPONENT";
            case 16:
                return "LOGOUT_CLEAR_USER_AUTH_DATA";
            default:
                switch (i) {
                    case 23:
                        return "LOGOUT_UNREGISTER_PUSH_TOKEN_ASYNC";
                    case 24:
                        return "LOGOUT_UNREGISTER_PUSH_TOKEN_COMPONENT_ASYNC";
                    case 25:
                        return "LOGOUT_EXPIRE_SESSION_ASYNC";
                    case 26:
                        return "LOGOUT_CLEAR_FB_APP_USER_DATA_COMPONENT";
                    case 27:
                        return "LOGOUT_UNREGISTER_PUSH_TOKEN_ASYNC_TAG";
                    case 28:
                        return "LOGOUT_HANDLE_DITTO_LOGOUT";
                    case 29:
                        return "LOGOUT_APP_SESSION_CLEAR_USER_DATA";
                    case 30:
                        return "LOGOUT_PRELOAD_COMPONENT";
                    case 31:
                        return "LOGOUT_LOGOUT_E2E";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
