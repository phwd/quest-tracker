package com.oculus.nux.ota;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import oculus.internal.ContextCompat;

public class NuxOtaBootReceiver extends BroadcastReceiver {
    private static final String TAG = "NuxOtaBootReceiver";

    public void onReceive(Context context, Intent intent) {
        if (NuxOta.DEBUG) {
            Log.d(TAG, "Starting NuxOtaService.");
        }
        new ContextCompat().startForegroundService(context, new Intent(context, NuxOtaService.class));
    }
}
