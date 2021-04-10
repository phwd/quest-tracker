package com.facebook.analytics2.logger;

import X.C00850Gt;
import X.C02670bA;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HighPriUploadRetryReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        if (C02670bA.A01().A00(context, this, intent) && "com.facebook.analytics2.logger.UPLOAD_NOW".equals(intent.getAction())) {
            new C00850Gt(this, intent, context, goAsync()).start();
        }
    }
}
