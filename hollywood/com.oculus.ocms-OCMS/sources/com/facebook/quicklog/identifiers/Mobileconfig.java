package com.facebook.quicklog.identifiers;

public class Mobileconfig {
    public static final int AFTER_LOGIN = 13631504;
    public static final int BUFFER_PATH_MIGRATION = 13631495;
    public static final int FACTORY_IMPL_INIT_MANAGER = 13631496;
    public static final int JAVA_MANAGER_CREATION = 13631490;
    public static final int MOBILECONFIG_SYNC_FETCH = 13631505;
    public static final int MOBILECONFIG_SYNC_FETCH_IOS = 13631499;
    public static final short MODULE_ID = 208;
    public static final int NATIVE_MANAGER_CREATION = 13631489;
    public static final int NULL_BUFFER = 13631494;
    public static final int SESSIONLESS_INIT = 13631492;
    public static final int SESSION_BASED_INIT = 13631491;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 6 ? i != 7 ? i != 8 ? i != 11 ? i != 16 ? i != 17 ? "UNDEFINED_QPL_EVENT" : "MOBILECONFIG_MOBILECONFIG_SYNC_FETCH" : "MOBILECONFIG_AFTER_LOGIN" : "MOBILECONFIG_MOBILECONFIG_SYNC_FETCH_IOS" : "MOBILECONFIG_FACTORY_IMPL_INIT_MANAGER" : "MOBILECONFIG_BUFFER_PATH_MIGRATION" : "MOBILECONFIG_NULL_BUFFER" : "MOBILECONFIG_SESSIONLESS_INIT" : "MOBILECONFIG_SESSION_BASED_INIT" : "MOBILECONFIG_JAVA_MANAGER_CREATION" : "MOBILECONFIG_NATIVE_MANAGER_CREATION";
    }
}
