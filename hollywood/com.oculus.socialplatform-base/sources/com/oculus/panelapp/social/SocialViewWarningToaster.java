package com.oculus.panelapp.social;

import android.content.Context;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.socialplatform.R;

public final class SocialViewWarningToaster {
    public static void showToast(Context context, String str, String str2, String str3) {
        NotificationSender.Builder builder = new NotificationSender.Builder(str, "Error", str2, R.drawable.ic_notif_alert);
        builder.mDisplayDuration = NotificationsDisplayDuration.SHORT;
        builder.mIconImageType = NotificationConstants.LargeImageType.PROFILE;
        builder.send(context);
    }
}
