package com.oculus.preloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    public static final boolean DEBUG = false;
    public static final String TAG = "Preloader";
    private static final String propertyOverride = "persist.ovr.preloader.debug";

    public void onReceive(Context context, Intent intent) {
        boolean isRetailDevice = Utils.isRetailDevice();
        boolean isUnlocked = Utils.isUnlocked();
        if (SystemProperties.getBoolean(propertyOverride, false) || (isRetailDevice && !isUnlocked)) {
            Log.i("Preloader", "Starting preloader service...");
            context.startServiceAsUser(new Intent(context, PreloaderService.class), UserHandle.SYSTEM);
            return;
        }
        Log.i("Preloader", "Skip preloader: retail:" + isRetailDevice + " unlocked:" + isUnlocked);
        context.sendBroadcastAsUser(new Intent(Utils.ACTION_ALL_INSTALLS_COMPLETE), UserHandle.SYSTEM);
    }
}
