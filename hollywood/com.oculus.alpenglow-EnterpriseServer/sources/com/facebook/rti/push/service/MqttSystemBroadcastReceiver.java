package com.facebook.rti.push.service;

import X.AnonymousClass10W;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MqttSystemBroadcastReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || "android.intent.action.MY_PACKAGE_REPLACED".equals(intent.getAction()) || "android.intent.action.PACKAGE_REMOVED".equals(intent.getAction()) || "android.intent.action.USER_PRESENT".equals(intent.getAction()) || "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            String action = intent.getAction();
            String packageName = context.getPackageName();
            if (packageName == null) {
                packageName = context.getPackageName();
            }
            AnonymousClass10W.A00(context, FbnsService.A01(packageName), action, false, packageName);
        }
    }
}
