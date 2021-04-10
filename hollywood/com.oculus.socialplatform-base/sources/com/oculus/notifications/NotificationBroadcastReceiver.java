package com.oculus.notifications;

import X.AnonymousClass006;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationDataSetService;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String NOTIF_CHANGE_ACTION = "com.oculus.intent.action.NOTIF_CHANGE";
    public static final String TAG = LoggingUtil.tag(NotificationBroadcastReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str;
        String A07;
        intent.getAction();
        if (intent.getAction() == null) {
            str = TAG;
            A07 = "Received an intent with no action.";
        } else {
            String action = intent.getAction();
            if (action.hashCode() != 2033904317 || !action.equals("com.oculus.intent.action.NOTIF_CHANGE")) {
                str = TAG;
                A07 = AnonymousClass006.A07("Received unknown action: ", intent.getAction());
            } else {
                NotificationDataSetService.Loader.getInstance().notifyDataSetListeners();
                return;
            }
        }
        Log.w(str, A07);
    }
}
