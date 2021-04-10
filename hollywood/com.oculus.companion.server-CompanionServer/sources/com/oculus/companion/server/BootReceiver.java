package com.oculus.companion.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserHandle;
import android.text.TextUtils;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startServiceAsUser(new Intent(context, CompanionServer.class), UserHandle.SYSTEM);
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            char c = 65535;
            if (action.hashCode() == 798292259 && action.equals("android.intent.action.BOOT_COMPLETED")) {
                c = 0;
            }
            if (c == 0) {
                onBootCompleted(context);
            }
        }
    }

    private void onBootCompleted(Context context) {
        recordCrashReportsEnabledEvent(context);
    }

    private void recordCrashReportsEnabledEvent(Context context) {
        SecureStorage secureStorage = new SecureStorage(context);
        if (TextUtils.isEmpty(secureStorage.getStringValue("crash_reports_event_reported_os_version"))) {
            new Telemetry(context).recordCrashReportsEnabledEvent(context);
            secureStorage.storeValue("crash_reports_event_reported_os_version", Build.VERSION.INCREMENTAL);
        }
    }
}
