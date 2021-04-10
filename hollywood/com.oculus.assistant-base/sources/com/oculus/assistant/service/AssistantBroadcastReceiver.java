package com.oculus.assistant.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AssistantBroadcastReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(intent.getAction());
        intent2.putExtras(intent);
        intent2.setClass(context, AssistantIntentService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent2);
        } else {
            context.startService(intent2);
        }
    }
}
