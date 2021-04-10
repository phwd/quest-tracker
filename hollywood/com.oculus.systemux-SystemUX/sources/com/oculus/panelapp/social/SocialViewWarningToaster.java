package com.oculus.panelapp.social;

import android.content.Context;
import android.util.Log;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;

public final class SocialViewWarningToaster {
    public static void showToast(Context context, String str, String str2, String str3) {
        Log.d(str3, str2);
        NotificationSender.build(str, "Error", str2, R.drawable.ic_notif_alert).setDisplayDuration(NotificationsDisplayDuration.SHORT).setIconImageType(NotificationConstants.LargeImageType.PROFILE).send(context);
    }
}
