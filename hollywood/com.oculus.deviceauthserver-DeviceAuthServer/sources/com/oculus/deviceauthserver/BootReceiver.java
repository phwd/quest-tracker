package com.oculus.deviceauthserver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
            Intent refresh = new Intent(context, DeviceAuthService.class);
            refresh.setAction(DeviceAuthService.ACTION_REFRESH_TOKEN);
            ((AlarmManager) context.getSystemService("alarm")).setInexactRepeating(2, SystemClock.elapsedRealtime(), 86400000, PendingIntent.getService(context, 0, refresh, 0));
        }
    }
}
