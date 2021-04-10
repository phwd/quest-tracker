package com.oculus.common.socialtablet.notif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.oculus.notifications.NotificationDataSetService;

public class SocialNotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "SocialNotificationBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        intent.getAction();
        NotificationDataSetService.Loader.getInstance().notifyDataSetListeners();
    }
}
