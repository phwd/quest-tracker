package com.facebook.quicklog.identifiers;

public class PathProvider {
    public static final int GET_PATH = 38469633;
    public static final int GET_PATH_WITHOUT_INIT = 38469635;
    public static final int INIT = 38469638;
    public static final int MASTER_SLAVE_SYNC = 38469647;
    public static final short MODULE_ID = 587;
    public static final int PERFORM_EVICTION = 38469640;
    public static final int PLUGIN_ON_PATH_REQUESTED = 38469637;
    public static final int REGISTER_PATH = 38469639;
    public static final int REMOTE_WIPE_PERFORM_REMOVAL_ON_PATH = 38477987;
    public static final int REMOTE_WIPE_TRIGGER_CLEANUP_ACTION = 38484667;
    public static final int SCOPE_PLUGIN_PERFORM_REMOVAL_ON_PATH = 38469645;
    public static final int SCOPE_PLUGIN_TRIGGER_CLEANUP_ACTION = 38469646;
    public static final int SIZE_PLUGIN_PERFORM_REMOVAL_ON_PATH = 38469643;
    public static final int SIZE_PLUGIN_TRIGGER_CLEANUP_ACTION = 38469641;
    public static final int STALE_PLUGIN_PERFORM_REMOVAL_ON_PATH = 38469644;
    public static final int STALE_PLUGIN_TRIGGER_CLEANUP_ACTION = 38469642;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "PATH_PROVIDER_GET_PATH";
        }
        if (i == 3) {
            return "PATH_PROVIDER_GET_PATH_WITHOUT_INIT";
        }
        if (i == 8355) {
            return "PATH_PROVIDER_REMOTE_WIPE_PERFORM_REMOVAL_ON_PATH";
        }
        if (i == 15035) {
            return "PATH_PROVIDER_REMOTE_WIPE_TRIGGER_CLEANUP_ACTION";
        }
        switch (i) {
            case 5:
                return "PATH_PROVIDER_PLUGIN_ON_PATH_REQUESTED";
            case 6:
                return "PATH_PROVIDER_INIT";
            case 7:
                return "PATH_PROVIDER_REGISTER_PATH";
            case 8:
                return "PATH_PROVIDER_PERFORM_EVICTION";
            case 9:
                return "PATH_PROVIDER_SIZE_PLUGIN_TRIGGER_CLEANUP_ACTION";
            case 10:
                return "PATH_PROVIDER_STALE_PLUGIN_TRIGGER_CLEANUP_ACTION";
            case 11:
                return "PATH_PROVIDER_SIZE_PLUGIN_PERFORM_REMOVAL_ON_PATH";
            case 12:
                return "PATH_PROVIDER_STALE_PLUGIN_PERFORM_REMOVAL_ON_PATH";
            case 13:
                return "PATH_PROVIDER_SCOPE_PLUGIN_PERFORM_REMOVAL_ON_PATH";
            case 14:
                return "PATH_PROVIDER_SCOPE_PLUGIN_TRIGGER_CLEANUP_ACTION";
            case 15:
                return "PATH_PROVIDER_MASTER_SLAVE_SYNC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
