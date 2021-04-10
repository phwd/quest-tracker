package com.facebook.quicklog.identifiers;

public class SharedPhoneAccountRecovery {
    public static final int CODE_VERIFICATION = 60555265;
    public static final short MODULE_ID = 924;

    public static String getMarkerName(int i) {
        return i != 1 ? "UNDEFINED_QPL_EVENT" : "SHARED_PHONE_ACCOUNT_RECOVERY_CODE_VERIFICATION";
    }
}
