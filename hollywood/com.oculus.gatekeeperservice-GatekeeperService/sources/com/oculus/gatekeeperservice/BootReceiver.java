package com.oculus.gatekeeperservice;

import android.app.ActivityManagerNative;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Slog;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "GkServiceApp";

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || "android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
            rescheduleGkUpdateJob(context);
        }
    }

    private void rescheduleGkUpdateJob(Context context) {
        int currentUser = -1;
        try {
            currentUser = ActivityManagerNative.getDefault().getCurrentUser().id;
        } catch (Exception ex) {
            Slog.e(TAG, "Failed to query current user", ex);
        }
        if (currentUser >= 0) {
            Slog.i(TAG, "Scheduling periodic GK update for userId=" + currentUser);
            CacheUpdaterJobService.scheduleJob(context, currentUser);
        }
    }
}
