package com.facebook.quicklog.identifiers;

public class Launchables {
    public static final int LOAD_APPS_FROM_PACKAGE_MANAGER = 3276801;
    public static final int LOAD_SHORTCUTS_FROM_DATABASE = 3276802;
    public static final short MODULE_ID = 50;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "LAUNCHABLES_LOAD_SHORTCUTS_FROM_DATABASE" : "LAUNCHABLES_LOAD_APPS_FROM_PACKAGE_MANAGER";
    }
}
