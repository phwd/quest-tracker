package com.oculus.nux.ota;

import android.util.Log;
import java.util.Locale;

public enum NuxOtaState {
    NEW_DEVICE,
    NO_OTA,
    OTA_READY,
    OKAY_TO_REBOOT,
    WAITING_FOR_REBOOT,
    REBOOTING,
    WAITING_FOR_HIGH_PRI_APPS_DOWNLOAD,
    NOTIFY_ENDPOINT,
    COMPLETE;
    
    private static final String TAG = NuxOtaState.class.getSimpleName();

    public static NuxOtaState fromString(String str) {
        try {
            return valueOf(str.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException unused) {
            String str2 = TAG;
            Log.e(str2, "Unable to convert " + str + " to NuxOtaState.");
            return NEW_DEVICE;
        }
    }
}
