package com.facebook.analytics2.logger;

import X.C0082Ga;
import X.C0392gu;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HighPriUploadRetryReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        if (C0392gu.A01().A00(context, this, intent) && "com.facebook.analytics2.logger.UPLOAD_NOW".equals(intent.getAction())) {
            new C0082Ga(this, intent, context, goAsync()).start();
        }
    }
}
