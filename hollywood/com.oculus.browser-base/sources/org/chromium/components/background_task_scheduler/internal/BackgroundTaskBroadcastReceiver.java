package org.chromium.components.background_task_scheduler.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.BatteryManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BackgroundTaskBroadcastReceiver extends BroadcastReceiver {
    public final boolean a(Context context, boolean z) {
        if (!z) {
            return true;
        }
        return ((BatteryManager) context.getSystemService("batterymanager")).isCharging();
    }

    public final boolean b(Context context, Integer num) {
        if (num.intValue() == 0) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (num.intValue() != 1) {
            return !connectivityManager.isActiveNetworkMetered();
        }
        if (activeNetwork != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0269  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r19, android.content.Intent r20) {
        /*
        // Method dump skipped, instructions count: 814
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.background_task_scheduler.internal.BackgroundTaskBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
