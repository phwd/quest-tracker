package com.oculus.panelapp.people.views;

import android.content.Context;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.socialplatform.R;

public final class PeopleViewToaster {
    public static void showErrorToast(Context context, String str, String str2, String str3) {
        showToast(context, str, context.getResources().getString(R.string.people_tablet_error_toast_title), str2, R.drawable.ic_notif_alert, str3);
    }

    public static void showToast(Context context, String str, String str2, String str3, int i, String str4) {
        NotificationSender.Builder builder = new NotificationSender.Builder(str, str2, str3, i);
        builder.mDisplayDuration = NotificationsDisplayDuration.SHORT;
        builder.send(context);
    }
}
