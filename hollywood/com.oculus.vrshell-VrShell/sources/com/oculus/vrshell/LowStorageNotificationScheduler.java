package com.oculus.vrshell;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationSender;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class LowStorageNotificationScheduler {
    private static final String NOTIFICATION_TAG_STORAGE = "storage";
    public static final long STORAGE_CHECK_INITIAL_TIMEOUT = 5000;
    private static final long STORAGE_CHECK_REPEAT_TIMEOUT = 300000;
    private static final long STORAGE_CRITICAL_THRESHOLD = 524288000;
    private static final long STORAGE_LOW_THRESHOLD = 2147483648L;
    private static final String STORAGE_NOTIF_ID_CRITICAL = "oculus_mobile_storage_critical";
    private static final String STORAGE_NOTIF_ID_LOW = "oculus_mobile_storage_low";
    private static final String TAG = LoggingUtil.tag(LowStorageNotificationScheduler.class);
    private static boolean sHasShownCriticalStorageNotificationInSession = false;
    private static boolean sHasShownLowStorageNotificationInSession = false;
    private final Handler mLowStorageHandler = new Handler(Looper.getMainLooper());

    public void scheduleStorageCheck(long j, final Context context, final UnifiedTelemetryLogger unifiedTelemetryLogger) {
        Handler handler = this.mLowStorageHandler;
        if (handler != null) {
            handler.postDelayed(new Runnable() {
                /* class com.oculus.vrshell.LowStorageNotificationScheduler.AnonymousClass1 */

                public void run() {
                    LowStorageNotificationScheduler.this.checkStorageAndSendNotifications(context, unifiedTelemetryLogger);
                }
            }, j);
        }
    }

    public void cancelStorageChecks() {
        Handler handler = this.mLowStorageHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkStorageAndSendNotifications(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger) {
        if (!sHasShownCriticalStorageNotificationInSession) {
            try {
                long availableBytes = new StatFs(Environment.getExternalStorageDirectory().getPath()).getAvailableBytes();
                String str = TAG;
                Log.d(str, "Storage Checks: Internal Free (" + availableBytes + ")");
                if (availableBytes <= STORAGE_CRITICAL_THRESHOLD) {
                    sHasShownCriticalStorageNotificationInSession = true;
                    sendStorageNotification(context, unifiedTelemetryLogger, STORAGE_NOTIF_ID_CRITICAL, R.string.notification_storage_critical_title, R.string.notification_storage_critical_description, availableBytes);
                } else if (availableBytes <= STORAGE_LOW_THRESHOLD && !sHasShownLowStorageNotificationInSession) {
                    sHasShownLowStorageNotificationInSession = true;
                    sendStorageNotification(context, unifiedTelemetryLogger, STORAGE_NOTIF_ID_LOW, R.string.notification_storage_low_title, R.string.notification_storage_low_description, availableBytes);
                }
                if (!sHasShownCriticalStorageNotificationInSession) {
                    scheduleStorageCheck(STORAGE_CHECK_REPEAT_TIMEOUT, context, unifiedTelemetryLogger);
                }
            } catch (IllegalArgumentException e) {
                Throwable cause = e.getCause();
                if (!(cause instanceof ErrnoException) || ((ErrnoException) cause).errno != OsConstants.EACCES) {
                    throw e;
                }
            }
        }
    }

    private static void sendStorageNotification(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, String str, int i, int i2, long j) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_storage_notification");
        analyticsEvent.setExtra("storage_available", Integer.valueOf((int) j));
        unifiedTelemetryLogger.reportEvent(analyticsEvent, false);
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("systemux://storage_manager"));
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 1, intent, 0);
        NotificationSender.build(str, context.getString(i), context.getString(i2), NOTIFICATION_TAG_STORAGE, 1, R.drawable.ic_notif_storage).setContentIntent(broadcast).setAcceptIntent(broadcast).setIsPersistent(true).send(context);
    }
}
