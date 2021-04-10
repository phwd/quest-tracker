package com.oculus.vrshell.notifications;

import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;

public class VrNotificationHelper {
    private static final String TAG = LoggingUtil.tag(VrNotificationHelper.class);
    private static final String THIRD_PARTY_ANALYTICS_TYPE = "third_party";
    private static final String UNSPECIFIED_ANALYTICS_TYPE = "first_party_unspecified";

    public static long getTimeOfPostMs(StatusBarNotification statusBarNotification) {
        long j = statusBarNotification.getNotification().extras.getLong("original_post_time", -1);
        return j == -1 ? statusBarNotification.getPostTime() : j;
    }

    public static String getNotificationAnalyticsType(StatusBarNotification statusBarNotification) {
        String string = statusBarNotification.getNotification().extras.getString(NotificationConstants.EXTRA_TYPE);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        return isFirstPartyNotification(statusBarNotification) ? UNSPECIFIED_ANALYTICS_TYPE : THIRD_PARTY_ANALYTICS_TYPE;
    }

    public static boolean isFirstPartyNotification(StatusBarNotification statusBarNotification) {
        String lowerCase = statusBarNotification.getPackageName().toLowerCase();
        return lowerCase.startsWith("oculus.") || lowerCase.startsWith("com.oculus.") || lowerCase.startsWith("com.facebook.");
    }

    public static boolean isCaptiveWifiNotification(StatusBarNotification statusBarNotification) {
        String tag = statusBarNotification.getTag();
        return tag != null && tag.startsWith("ConnectivityNotification:");
    }
}
