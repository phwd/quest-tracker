package com.oculus.notification_proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.TimeUnit;

public class ConnectivityEventReceiver extends BroadcastReceiver {
    private static final long NOTIFY_INTERVAL_NS = TimeUnit.SECONDS.toNanos(60);
    private Long mLastDisplayTime;

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() == "android.net.conn.CONNECTIVITY_CHANGE" && intent.getBooleanExtra("noConnectivity", false)) {
            long nanoTime = System.nanoTime();
            Long l = this.mLastDisplayTime;
            if (l == null || nanoTime > l.longValue() + NOTIFY_INTERVAL_NS) {
                NotificationBuildingHelper.notify(context, R.string.wifi_disconnected_title, R.string.wifi_disconnected_text, NotificationIds.WIFI_DISCONNECTED);
                this.mLastDisplayTime = Long.valueOf(nanoTime);
            }
        }
    }

    public void register(Context context) {
        context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
