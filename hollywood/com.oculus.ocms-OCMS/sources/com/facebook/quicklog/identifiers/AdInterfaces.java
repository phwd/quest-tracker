package com.facebook.quicklog.identifiers;

public class AdInterfaces {
    public static final int ADD_BUDGET = 5898246;
    public static final int CREATE = 5898242;
    public static final int DELETE = 5898244;
    public static final int LOAD_AD_INTERFACE = 5898241;
    public static final short MODULE_ID = 90;
    public static final int PAUSE = 5898243;
    public static final int RESUME = 5898245;
    public static final int UPDATE_AD_ACCOUNT = 5898247;
    public static final int UPDATE_BUDGET = 5898248;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "AD_INTERFACES_LOAD_AD_INTERFACE";
            case 2:
                return "AD_INTERFACES_CREATE";
            case 3:
                return "AD_INTERFACES_PAUSE";
            case 4:
                return "AD_INTERFACES_DELETE";
            case 5:
                return "AD_INTERFACES_RESUME";
            case 6:
                return "AD_INTERFACES_ADD_BUDGET";
            case 7:
                return "AD_INTERFACES_UPDATE_AD_ACCOUNT";
            case 8:
                return "AD_INTERFACES_UPDATE_BUDGET";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
