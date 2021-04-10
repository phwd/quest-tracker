package com.oculus.vrshell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public final class VrBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(VrBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.d(str, "action: " + intent.getAction());
        if (intent.getAction() == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == 115274560 && action.equals("com.oculus.vrshell.intent.action.NOTIFICATION_WAKE")) {
            c = 0;
        }
        if (c != 0) {
            String str2 = TAG;
            Log.w(str2, "Received unknown action: " + intent.getAction());
            return;
        }
        context.startService(new Intent(context, VrNotificationService.class));
    }
}
