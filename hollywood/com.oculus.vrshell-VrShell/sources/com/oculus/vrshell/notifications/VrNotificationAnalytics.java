package com.oculus.vrshell.notifications;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.UnifiedTelemetryLogger;

/* access modifiers changed from: package-private */
public class VrNotificationAnalytics {
    private static final String EVENT_NOTIFICATION_RECEIVED = "oculus_hmd_notification_received";
    private static final String EVENT_NOTIFICATION_REMOVED = "oculus_hmd_notification_removed";
    private static final String EXTRA_HAS_INTENT_ACTION = "has_intent_action";
    private static final String EXTRA_IS_PERSISTENT = "is_persistent";
    private static final String EXTRA_IS_SYSTEM = "is_system";
    private static final String EXTRA_NOTIFICATION_IS_TOAST = "notification_is_toast";
    private static final String EXTRA_TOASTING_BLOCKED_BY_DND = "toasting_blocked_by_dnd";
    private static final String EXTRA_TOASTING_BLOCKED_BY_LINK = "toasting_blocked_by_link";
    private static final String EXTRA_TOASTING_BLOCKED_BY_MULTI_USER = "toasting_blocked_by_multi_user";
    private static final String EXTRA_TOASTING_BLOCKED_BY_SYSTEM = "toasting_blocked_by_system";
    private static final String EXTRA_VRSHELL_IN_FOREGROUND = "vrshell_in_foreground";
    private static final String TAG = "VrNotificationAnalytics";
    private final ActivityManagerUtils mActivityManagerUtils = new ActivityManagerUtils();
    private final Context mContext;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public VrNotificationAnalytics(Context context) {
        Log.d(TAG, "VrNotificationAnalytics()");
        this.mContext = context;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    public void logNotificationPosted(StatusBarNotification statusBarNotification, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        String notificationAnalyticsType = VrNotificationHelper.getNotificationAnalyticsType(statusBarNotification);
        Log.d(TAG, "logNotificationPosted(" + notificationAnalyticsType + ")");
        VrNotificationAnalyticsEvent vrNotificationAnalyticsEvent = new VrNotificationAnalyticsEvent(EVENT_NOTIFICATION_RECEIVED, statusBarNotification);
        vrNotificationAnalyticsEvent.setExtra(EXTRA_NOTIFICATION_IS_TOAST, Boolean.valueOf(z));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_TOASTING_BLOCKED_BY_DND, Boolean.valueOf(z5));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_TOASTING_BLOCKED_BY_SYSTEM, Boolean.valueOf(z6));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_TOASTING_BLOCKED_BY_LINK, Boolean.valueOf(z7));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_TOASTING_BLOCKED_BY_MULTI_USER, Boolean.valueOf(z8));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_IS_PERSISTENT, Boolean.valueOf(z2));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_IS_SYSTEM, Boolean.valueOf(z3));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_HAS_INTENT_ACTION, Boolean.valueOf(z4));
        vrNotificationAnalyticsEvent.setExtra(EXTRA_VRSHELL_IN_FOREGROUND, Boolean.valueOf(getIsVrShellInForeground()));
        vrNotificationAnalyticsEvent.report(this.mUnifiedTelemetryLogger);
    }

    public void logNotificationRemoved(StatusBarNotification statusBarNotification) {
        String notificationAnalyticsType = VrNotificationHelper.getNotificationAnalyticsType(statusBarNotification);
        Log.d(TAG, "logNotificationRemoved(" + notificationAnalyticsType + ")");
        VrNotificationAnalyticsEvent vrNotificationAnalyticsEvent = new VrNotificationAnalyticsEvent(EVENT_NOTIFICATION_REMOVED, statusBarNotification);
        vrNotificationAnalyticsEvent.setExtra(EXTRA_VRSHELL_IN_FOREGROUND, Boolean.valueOf(getIsVrShellInForeground()));
        vrNotificationAnalyticsEvent.report(this.mUnifiedTelemetryLogger);
    }

    private boolean getIsVrShellInForeground() {
        return PackageHelpers.isShellApp(this.mActivityManagerUtils.getForegroundApp(this.mContext));
    }
}
