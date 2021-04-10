package com.oculus.vrshell.notifications;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.SettingsManager;
import com.oculus.os.Version;
import com.oculus.vrshell.notifications.xrsp.XrspStatusHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class VrNotificationListenerService extends NotificationListenerService {
    private static final boolean DO_NOT_DISTURB_DEFAULT_VALUE = false;
    private static final String TAG = LoggingUtil.tag(VrNotificationListenerService.class);
    private static VrNotificationListenerService sInstance;
    private final Context mContext;
    private boolean mRegistered = false;
    private final SettingsManager mSettingsManager;
    private int mToastsSuppressed = 0;
    private final VrNotificationAnalytics mVrNotificationAnalytics;
    private final VrNotificationCache mVrNotificationCache = new VrNotificationCache();
    private final VrNotificationDataSetManager mVrNotificationDataSetManager;
    private final Comparator<StatusBarNotification> notificationComparator = new Comparator<StatusBarNotification>() {
        /* class com.oculus.vrshell.notifications.VrNotificationListenerService.AnonymousClass1 */

        public int compare(StatusBarNotification statusBarNotification, StatusBarNotification statusBarNotification2) {
            return Long.compare(VrNotificationHelper.getTimeOfPostMs(statusBarNotification2), VrNotificationHelper.getTimeOfPostMs(statusBarNotification));
        }
    };

    private VrNotificationListenerService(Context context) {
        Log.i(TAG, "VrNotificationListenerService created!");
        this.mContext = context;
        System.loadLibrary("vrshell_notifications");
        this.mVrNotificationDataSetManager = new VrNotificationDataSetManager(context, this);
        this.mVrNotificationAnalytics = new VrNotificationAnalytics(context);
        this.mSettingsManager = new SettingsManager();
    }

    public static VrNotificationListenerService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VrNotificationListenerService(context);
        }
        return sInstance;
    }

    public void registerAsSystemServiceUsingReflection() {
        if (!this.mRegistered) {
            this.mRegistered = true;
            Method method = null;
            try {
                method = Class.forName("android.service.notification.NotificationListenerService").getMethod("registerAsSystemService", Context.class, ComponentName.class, Integer.TYPE);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not find NotificationListenerService via reflection", e);
            } catch (NoSuchMethodException e2) {
                Log.e(TAG, "Could not find registerAsSystemService via reflection", e2);
            }
            if (method != null) {
                try {
                    method.invoke(this, this.mContext, new ComponentName(this.mContext.getPackageName(), getClass().getCanonicalName()), -1);
                } catch (IllegalAccessException e3) {
                    Log.e(TAG, "Failed to register as a system service:  ", e3);
                } catch (InvocationTargetException e4) {
                    Log.e(TAG, "Do not have permission to register:  ", e4);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public StatusBarNotification[] getSortedNotifications() {
        StatusBarNotification[] allNotifications = this.mVrNotificationCache.getAllNotifications();
        if (allNotifications != null && allNotifications.length > 0) {
            Arrays.sort(allNotifications, this.notificationComparator);
        }
        return allNotifications;
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (statusBarNotification == null) {
            Log.e(TAG, "onNotificationPosted(): received a null StatusBarNotification");
            return;
        }
        locallyLogNotification("onNotificationPosted()", statusBarNotification);
        this.mVrNotificationCache.add(statusBarNotification);
        updateNotificationListVersion();
        boolean isNotificationAToast = isNotificationAToast(statusBarNotification);
        boolean isPersistentNotification = this.mVrNotificationDataSetManager.isPersistentNotification(statusBarNotification);
        boolean isSystemNotification = this.mVrNotificationDataSetManager.isSystemNotification(statusBarNotification);
        boolean doesNotificationHaveIntent = doesNotificationHaveIntent(statusBarNotification);
        boolean z5 = false;
        if (isNotificationAToast) {
            boolean isNotificationBlockedByLink = isNotificationBlockedByLink(statusBarNotification);
            boolean isNotificationBlockedByMultiUser = isNotificationBlockedByMultiUser(statusBarNotification);
            z4 = isDNDModeEnabled() && !shouldBypassDNDMode(statusBarNotification) && !shouldBypassToastSuppression(statusBarNotification);
            if (areToastsSuppressed() && !shouldBypassToastSuppression(statusBarNotification)) {
                z5 = true;
            }
            if (!z4 && !z5 && !isNotificationBlockedByLink && !isNotificationBlockedByMultiUser) {
                VrNotificationToastGenerator.sendNotificationToast(statusBarNotification, this.mContext, isPersistentNotification, isSystemNotification, doesNotificationHaveIntent);
            }
            z3 = z5;
            z2 = isNotificationBlockedByLink;
            z = isNotificationBlockedByMultiUser;
        } else {
            z4 = false;
            z3 = false;
            z2 = false;
            z = false;
        }
        this.mVrNotificationAnalytics.logNotificationPosted(statusBarNotification, isNotificationAToast, isPersistentNotification, isSystemNotification, doesNotificationHaveIntent, z4, z3, z2, z);
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            Log.e(TAG, "onNotificationRemoved(): received a null StatusBarNotification");
            return;
        }
        locallyLogNotification("onNotificationRemoved()", statusBarNotification);
        this.mVrNotificationCache.remove(statusBarNotification);
        updateNotificationListVersion();
        VrNotificationToastGenerator.dismissNotificationToast(statusBarNotification, this.mContext);
        this.mVrNotificationAnalytics.logNotificationRemoved(statusBarNotification);
    }

    private void locallyLogNotification(String str, StatusBarNotification statusBarNotification) {
        String notificationAnalyticsType = VrNotificationHelper.getNotificationAnalyticsType(statusBarNotification);
        String str2 = TAG;
        Log.i(str2, str + ": key = " + statusBarNotification.getKey() + ", type = " + notificationAnalyticsType);
        String str3 = TAG;
        Log.i(str3, str + ": user|package|tag|id = " + statusBarNotification.getUser() + "|" + statusBarNotification.getPackageName() + "|" + statusBarNotification.getTag() + "|" + statusBarNotification.getId());
    }

    private boolean doesNotificationHaveType(StatusBarNotification statusBarNotification) {
        if (VrNotificationHelper.isFirstPartyNotification(statusBarNotification) && statusBarNotification.getNotification().extras.getString(NotificationConstants.EXTRA_TYPE) == null) {
            return false;
        }
        return true;
    }

    private boolean systemPersistentAndNotDownload(StatusBarNotification statusBarNotification) {
        if (statusBarNotification.getNotification().extras.getBoolean("is_download", false) || !this.mVrNotificationDataSetManager.isSystemPersistentNotification(statusBarNotification)) {
            return false;
        }
        return true;
    }

    private boolean isNotificationBlockedByLink(StatusBarNotification statusBarNotification) {
        if (XrspStatusHelper.shouldToast() || !this.mVrNotificationDataSetManager.isSocialNotification(statusBarNotification)) {
            return false;
        }
        Log.i(TAG, "VrNotifier: Social toasts suppressed by XRSP status");
        return true;
    }

    public boolean isCurrentUserForeground() {
        if (Version.CURRENT_SDK_VERSION < 66) {
            return false;
        }
        try {
            return ActivityManagerUtils.getCurrentUser().equals(Process.myUserHandle());
        } catch (SecurityException unused) {
            return false;
        }
    }

    private boolean isNotificationBlockedByMultiUser(StatusBarNotification statusBarNotification) {
        if (!this.mSettingsManager.getBoolean("multi_user_enabled", false) || ((isForCurrentUser(statusBarNotification) && isCurrentUserForeground()) || !this.mVrNotificationDataSetManager.isSocialNotification(statusBarNotification))) {
            return false;
        }
        String str = TAG;
        Log.i(str, "Multi-user: not toasting social notifications from background user. notification key=" + statusBarNotification.getKey());
        return true;
    }

    private boolean isNotificationAToast(StatusBarNotification statusBarNotification) {
        return (statusBarNotification.getNotification().priority >= 1 || systemPersistentAndNotDownload(statusBarNotification)) && !this.mVrNotificationDataSetManager.isBlockedNotification(statusBarNotification) && (TextUtils.isEmpty(statusBarNotification.getNotification().extras.getString(NotificationCompat.EXTRA_TITLE, "")) ^ true) && !NotificationCompat.CATEGORY_RECOMMENDATION.equals(statusBarNotification.getNotification().category) && !VrNotificationHelper.isCaptiveWifiNotification(statusBarNotification);
    }

    private boolean areToastsSuppressed() {
        return this.mToastsSuppressed > 0;
    }

    public void suppressToasts() {
        this.mToastsSuppressed++;
    }

    public void unsuppressToasts() {
        this.mToastsSuppressed--;
        if (this.mToastsSuppressed < 0) {
            Log.e(TAG, "unsuppressToasts(): unmatched unsuppressToasts call for each suppressToasts");
        }
    }

    private boolean shouldBypassDNDMode(StatusBarNotification statusBarNotification) {
        return this.mVrNotificationDataSetManager.isHighPriorityNotification(statusBarNotification);
    }

    private boolean shouldBypassToastSuppression(StatusBarNotification statusBarNotification) {
        return this.mVrNotificationDataSetManager.isOverrideToastSuppressionNotification(statusBarNotification);
    }

    private static boolean doesNotificationHaveIntent(StatusBarNotification statusBarNotification) {
        Notification notification = statusBarNotification.getNotification();
        return notification.contentIntent != null || (notification.actions != null && notification.actions.length > 0);
    }

    public void enableDNDMode(boolean z) {
        this.mSettingsManager.setBoolean("do_not_disturb", z);
    }

    private boolean isDNDModeEnabled() {
        return this.mSettingsManager.getBoolean("do_not_disturb", false);
    }

    public VrNotificationDataSetManager getVrNotificationDataSetManager() {
        return this.mVrNotificationDataSetManager;
    }

    private void updateNotificationListVersion() {
        this.mVrNotificationDataSetManager.updateNotificationListVersion();
    }

    public void onListenerConnected() {
        super.onListenerConnected();
        Log.i(TAG, "onListenerConnected()");
        try {
            this.mVrNotificationCache.init(getActiveNotifications());
        } catch (SecurityException e) {
            Log.e(TAG, "Failed to get a list of active notifications", e);
        }
    }

    /* access modifiers changed from: private */
    public static boolean isForCurrentUser(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getUser().equals(Process.myUserHandle());
    }

    /* access modifiers changed from: package-private */
    public class VrNotificationCache {
        private Map<NotificationKey, StatusBarNotification> mNotifications = new HashMap();

        VrNotificationCache() {
        }

        public synchronized void init(StatusBarNotification[] statusBarNotificationArr) {
            if (statusBarNotificationArr != null) {
                this.mNotifications.clear();
                for (StatusBarNotification statusBarNotification : statusBarNotificationArr) {
                    add(statusBarNotification);
                }
            }
        }

        public synchronized void add(StatusBarNotification statusBarNotification) {
            if (VrNotificationListenerService.isForCurrentUser(statusBarNotification)) {
                this.mNotifications.put(new NotificationKey(statusBarNotification), statusBarNotification);
            }
        }

        public synchronized void remove(StatusBarNotification statusBarNotification) {
            this.mNotifications.remove(new NotificationKey(statusBarNotification));
        }

        public synchronized StatusBarNotification[] getAllNotifications() {
            return (StatusBarNotification[]) this.mNotifications.values().toArray(new StatusBarNotification[this.mNotifications.size()]);
        }

        /* access modifiers changed from: package-private */
        public class NotificationKey {
            private final int mHash = Objects.hash(this.mPackage, this.mTag, Integer.valueOf(this.mId));
            private final int mId;
            private final String mPackage;
            private final String mTag;

            NotificationKey(StatusBarNotification statusBarNotification) {
                this.mPackage = statusBarNotification.getPackageName();
                this.mTag = statusBarNotification.getTag();
                this.mId = statusBarNotification.getId();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || !(obj instanceof NotificationKey)) {
                    return false;
                }
                NotificationKey notificationKey = (NotificationKey) obj;
                return this.mId == notificationKey.mId && Objects.equals(this.mPackage, notificationKey.mPackage) && Objects.equals(this.mTag, notificationKey.mTag);
            }

            public int hashCode() {
                return this.mHash;
            }
        }
    }
}
