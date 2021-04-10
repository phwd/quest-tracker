package com.facebook.quicklog.identifiers;

public class Oculus {
    public static final int AUTH_LOGIN = 15400964;
    public static final int AUTH_LOGIN_HANDLERS = 15400965;
    public static final int AUTH_LOGIN_REQUEST = 15400966;
    public static final int HORIZON_PRIVACY_TTI = 15400962;
    public static final int INIT = 15400961;
    public static final int LIBRARY_LOAD = 15400963;
    public static final short MODULE_ID = 235;
    public static final int TTI = 15400967;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "OCULUS_INIT";
            case 2:
                return "OCULUS_HORIZON_PRIVACY_TTI";
            case 3:
                return "OCULUS_LIBRARY_LOAD";
            case 4:
                return "OCULUS_AUTH_LOGIN";
            case 5:
                return "OCULUS_AUTH_LOGIN_HANDLERS";
            case 6:
                return "OCULUS_AUTH_LOGIN_REQUEST";
            case 7:
                return "OCULUS_TTI";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
