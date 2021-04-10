package com.oculus.notification_proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SendNotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TITLE = "title";
    private static final String TAG = "SendNotificationBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            Log.e(TAG, "Missing or invalid intent");
            return;
        }
        Bundle extras = intent.getExtras();
        String string = extras.getString(KEY_TITLE);
        String string2 = extras.getString(KEY_TEXT);
        if (string == null || string2 == null) {
            Log.e(TAG, "Missing title and/or text fields");
        } else {
            NotificationBuildingHelper.notify(context, null, 0, string, string2, extras.getInt(KEY_PRIORITY, 0));
        }
    }
}
