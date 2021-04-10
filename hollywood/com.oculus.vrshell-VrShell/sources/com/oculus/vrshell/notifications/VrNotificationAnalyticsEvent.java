package com.oculus.vrshell.notifications;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class VrNotificationAnalyticsEvent extends AnalyticsEvent {
    private static final String EXTRA_FBID = "notification_fbid";
    private static final String EXTRA_FROM_PACKAGE_NAME = "from_package_name";
    private static final String EXTRA_ID = "notification_id";
    private static final String EXTRA_IS_FIRST_PARTY = "is_first_party";
    private static final String EXTRA_NDID = "notification_ndid";
    private static final String EXTRA_TAG = "notification_tag";
    private static final String EXTRA_TYPE = "notification_type";

    public VrNotificationAnalyticsEvent(String str, StatusBarNotification statusBarNotification) {
        super(str);
        setExtra(EXTRA_ID, Integer.valueOf(statusBarNotification.getId()));
        setExtra(EXTRA_TAG, statusBarNotification.getTag());
        setExtra(EXTRA_FROM_PACKAGE_NAME, statusBarNotification.getPackageName());
        setExtra(EXTRA_IS_FIRST_PARTY, Boolean.valueOf(VrNotificationHelper.isFirstPartyNotification(statusBarNotification)));
        setExtra(EXTRA_TYPE, VrNotificationHelper.getNotificationAnalyticsType(statusBarNotification));
        Bundle bundle = statusBarNotification.getNotification().extras;
        setExtraStringFromBundle(bundle, NotificationConstants.EXTRA_FBID, EXTRA_FBID);
        setExtraStringFromBundle(bundle, NotificationConstants.EXTRA_NDID, EXTRA_NDID);
    }

    private void setExtraStringFromBundle(Bundle bundle, String str, String str2) {
        String string = bundle.getString(str);
        if (string != null) {
            setExtra(str2, string);
        }
    }

    public void report(UnifiedTelemetryLogger unifiedTelemetryLogger) {
        unifiedTelemetryLogger.reportEvent(this, false);
    }
}
