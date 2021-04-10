package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.Context;
import android.util.Log;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;

public final class ProfileViewToaster {
    public static void showToast(Context context, String str, String str2, String str3, int i, String str4) {
        Log.d(str4, str2 + "-" + str3);
        NotificationSender.build(str, str2, str3, i).setDisplayDuration(NotificationsDisplayDuration.SHORT).send(context);
    }
}
