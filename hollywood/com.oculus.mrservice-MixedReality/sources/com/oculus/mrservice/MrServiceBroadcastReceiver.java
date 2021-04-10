package com.oculus.mrservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MrServiceBroadcastReceiver extends BroadcastReceiver {
    private static Class<?> SystemPropertiesCLASS = null;
    private static final String TAG = "MrServiceBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive " + intent);
        intent.getAction();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Log.e(TAG, "onReceive - ex: " + key + " : " + bundle.get(key));
            }
        }
        String packageName = context.getPackageName();
        Intent serviceIntent = new Intent(MrService.BOOT);
        serviceIntent.setComponent(new ComponentName(packageName, "com.oculus.mrservice.MrService"));
        context.startService(serviceIntent);
    }
}
