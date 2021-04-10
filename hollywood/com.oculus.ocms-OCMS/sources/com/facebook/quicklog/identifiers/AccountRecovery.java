package com.facebook.quicklog.identifiers;

public class AccountRecovery {
    public static final int FLASH_CALL = 240131259;
    public static final int LARA_ENDPOINT = 240135711;
    public static final short MODULE_ID = 3664;

    public static String getMarkerName(int i) {
        return i != 7355 ? i != 11807 ? "UNDEFINED_QPL_EVENT" : "ACCOUNT_RECOVERY_LARA_ENDPOINT" : "ACCOUNT_RECOVERY_FLASH_CALL";
    }
}
