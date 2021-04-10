package com.facebook.quicklog.identifiers;

public class DiskcacheManager {
    public static final int DISKLISTENER_FIRED = 42860553;
    public static final int DISK_UPDATE = 42860545;
    public static final short MODULE_ID = 654;
    public static final int REGISTER_DISKTASK = 42860548;
    public static final int RMANAGER_DISKCHANGED = 42860552;
    public static final int RMANAGER_DISKUPDATE = 42860551;
    public static final int RMONITOR_DISKUPDATE = 42860546;
    public static final int RMONITOR_ONSTART = 42860547;
    public static final int RMONITOR_ONSTOP = 42860549;
    public static final int UNREGISTER_DISKTASK = 42860550;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "DISKCACHE_MANAGER_DISK_UPDATE";
            case 2:
                return "DISKCACHE_MANAGER_RMONITOR_DISKUPDATE";
            case 3:
                return "DISKCACHE_MANAGER_RMONITOR_ONSTART";
            case 4:
                return "DISKCACHE_MANAGER_REGISTER_DISKTASK";
            case 5:
                return "DISKCACHE_MANAGER_RMONITOR_ONSTOP";
            case 6:
                return "DISKCACHE_MANAGER_UNREGISTER_DISKTASK";
            case 7:
                return "DISKCACHE_MANAGER_RMANAGER_DISKUPDATE";
            case 8:
                return "DISKCACHE_MANAGER_RMANAGER_DISKCHANGED";
            case 9:
                return "DISKCACHE_MANAGER_DISKLISTENER_FIRED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
