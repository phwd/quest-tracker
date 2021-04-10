package com.facebook.quicklog.identifiers;

public class Crash {
    public static final int ACRA_SETUP = 8781825;
    public static final int BREAKPAD_MANAGER_SETUP = 8781826;
    public static final int CATCH_ME_IF_YOU_CAN_SETUP = 8781827;
    public static final short MODULE_ID = 134;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "CRASH_CATCH_ME_IF_YOU_CAN_SETUP" : "CRASH_BREAKPAD_MANAGER_SETUP" : "CRASH_ACRA_SETUP";
    }
}
