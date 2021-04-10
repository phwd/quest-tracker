package com.oculus.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String NOTIF_CHANGE_ACTION = "com.oculus.intent.action.NOTIF_CHANGE";
    private static final String TAG = LoggingUtil.tag(NotificationBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.d(str, "action: " + intent.getAction());
        if (intent.getAction() == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == 2033904317 && action.equals(NOTIF_CHANGE_ACTION)) {
            c = 0;
        }
        if (c != 0) {
            String str2 = TAG;
            Log.w(str2, "Received unknown action: " + intent.getAction());
            return;
        }
        NotificationDataSetService.getInstance().notifyDataSetListeners();
    }
}
