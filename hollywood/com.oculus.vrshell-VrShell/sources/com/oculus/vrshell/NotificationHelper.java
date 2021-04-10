package com.oculus.vrshell;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.notifications.NotificationsDisplayDuration;
import java.util.HashMap;
import java.util.Map;

public class NotificationHelper {
    private static int NOTIFICATION_ID = 0;
    private static final String TAG = LoggingUtil.tag(ShellApplication.class);
    private static final Map<String, NotificationDetail> registeredNotifications = new HashMap<String, NotificationDetail>() {
        /* class com.oculus.vrshell.NotificationHelper.AnonymousClass1 */

        {
            put("vrshell_selected_environment_changed", new NotificationDetail(R.string.toast_selected_environment_changed_title, R.string.toast_selected_environment_changed_body, R.drawable.selected_environment_changed_icon) {
                /* class com.oculus.vrshell.NotificationHelper.AnonymousClass1.AnonymousClass1 */

                {
                    this.LargeIconType = NotificationConstants.LargeImageType.ICON;
                    this.DisplayDuration = NotificationsDisplayDuration.LONG;
                    this.Action = "systemux://settings";
                    this.ActionUri = "/environment";
                }
            });
            put("vrshell_link_available_upsell", new NotificationDetail(R.string.toast_oculus_link_available_title, R.string.toast_oculus_link_available_detail, R.drawable.toast_oculus_link_available_icon) {
                /* class com.oculus.vrshell.NotificationHelper.AnonymousClass1.AnonymousClass2 */

                {
                    this.LargeIconType = NotificationConstants.LargeImageType.ICON;
                }
            });
        }
    };

    /* access modifiers changed from: package-private */
    public static class NotificationDetail {
        public String Action;
        public String ActionUri;
        public NotificationsDisplayDuration DisplayDuration = NotificationsDisplayDuration.DEFAULT;
        public boolean IsFeed = false;
        public NotificationConstants.LargeImageType LargeIconType = NotificationConstants.LargeImageType.INVALID;
        public int Priority = 1;
        public final int ResourceIdBody;
        public final int ResourceIdImage;
        public final int ResourceIdTitle;

        NotificationDetail(int i, int i2, int i3) {
            this.ResourceIdTitle = i;
            this.ResourceIdBody = i2;
            this.ResourceIdImage = i3;
        }
    }

    public static void sendNamedNotification(Context context, String str) {
        NotificationDetail notificationDetail = registeredNotifications.get(str);
        if (notificationDetail != null) {
            Resources resources = context.getResources();
            String string = resources.getString(notificationDetail.ResourceIdTitle);
            String string2 = resources.getString(notificationDetail.ResourceIdBody);
            PendingIntent buildPendingIntent = buildPendingIntent(context, notificationDetail.Action, notificationDetail.ActionUri);
            int i = NOTIFICATION_ID + 1;
            NOTIFICATION_ID = i;
            NotificationSender.build(str, string, string2, str, i, notificationDetail.ResourceIdImage).setPriority(notificationDetail.Priority).setContentIntent(buildPendingIntent).setIsPersistent(notificationDetail.IsFeed).setDisplayDuration(notificationDetail.DisplayDuration).setIconImageType(notificationDetail.LargeIconType).send(context);
            return;
        }
        String str2 = TAG;
        Log.w(str2, "Invalid notification id was passed in - " + str);
    }

    private static PendingIntent buildPendingIntent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("uri", str2);
        }
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
