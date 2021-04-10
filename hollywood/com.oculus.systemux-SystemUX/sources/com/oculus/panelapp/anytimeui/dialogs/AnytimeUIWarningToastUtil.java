package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.panelapp.anytimeui.R;

public final class AnytimeUIWarningToastUtil {
    public static void showWarningToast(Context context, String str, String str2) {
        NotificationSender.build(str, "Error", str2, R.drawable.ic_notif_alert).setDisplayDuration(NotificationsDisplayDuration.SHORT).setIconImageType(NotificationConstants.LargeImageType.INVALID).send(context);
    }
}
