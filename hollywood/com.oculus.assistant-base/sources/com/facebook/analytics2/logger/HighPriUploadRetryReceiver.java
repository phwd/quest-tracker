package com.facebook.analytics2.logger;

import X.AnonymousClass7B;
import X.JP;
import X.JU;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HighPriUploadRetryReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        JU ju;
        synchronized (JP.class) {
            ju = JP.A01;
        }
        if (ju.A00(context, this, intent) && "com.facebook.analytics2.logger.UPLOAD_NOW".equals(intent.getAction())) {
            new AnonymousClass7B(this, intent, context, goAsync()).start();
        }
    }
}
