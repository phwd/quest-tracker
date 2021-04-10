package com.facebook.quicklog.identifiers;

public class MobileStorage {
    public static final short MODULE_ID = 660;
    public static final int MONITOR_TASK = 43253761;
    public static final int MONITOR_TASK_REGISTER = 43253762;
    public static final int TRIMMABLE_ON_UPDATE = 43253764;
    public static final int TRIMMABLE_REGISTER = 43253763;
    public static final int TRIMMABLE_TRIM_TO_MINIMUM = 43253765;
    public static final int TRIMMABLE_TRIM_TO_NOTHING = 43253766;
    public static final int TRIMMABLE_UNREGISTER = 43253768;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MOBILE_STORAGE_MONITOR_TASK";
            case 2:
                return "MOBILE_STORAGE_MONITOR_TASK_REGISTER";
            case 3:
                return "MOBILE_STORAGE_TRIMMABLE_REGISTER";
            case 4:
                return "MOBILE_STORAGE_TRIMMABLE_ON_UPDATE";
            case 5:
                return "MOBILE_STORAGE_TRIMMABLE_TRIM_TO_MINIMUM";
            case 6:
                return "MOBILE_STORAGE_TRIMMABLE_TRIM_TO_NOTHING";
            case 7:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 8:
                return "MOBILE_STORAGE_TRIMMABLE_UNREGISTER";
        }
    }
}
