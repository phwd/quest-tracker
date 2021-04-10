package com.oculus.vrshell.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;

public class DeviceHelper {
    private static final String TAG = "DeviceHelper";

    public static boolean isScreenOn(Context context) {
        boolean z = false;
        for (Display display : ((DisplayManager) context.getSystemService("display")).getDisplays()) {
            if (display.getState() != 1) {
                z = true;
            }
        }
        Log.d(TAG, "isScreenOn = " + z);
        return z;
    }

    public static boolean isDeviceLocked(Context context) {
        boolean isKeyguardLocked = ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardLocked();
        Log.d(TAG, "isDeviceLocked = " + isKeyguardLocked);
        return isKeyguardLocked;
    }
}
