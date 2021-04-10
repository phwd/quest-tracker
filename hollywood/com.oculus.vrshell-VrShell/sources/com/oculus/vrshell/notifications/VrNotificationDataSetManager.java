package com.oculus.vrshell.notifications;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.service.notification.StatusBarNotification;
import com.oculus.module.notifications.R;
import com.oculus.notifications.NotificationConstants;
import com.oculus.vrshell.panelservice.VerifierConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class VrNotificationDataSetManager {
    private static final String NOTIF_CHANGE_INTENT = "com.oculus.intent.action.NOTIF_CHANGE";
    private static final List<String> NOTIF_CHANGE_INTENT_BROADCAST_PACKAGE_ALLOWLIST = Arrays.asList("com.oculus.systemux", VerifierConstants.OCULUS_SOCIAL_PLATFORM_TABLET_PACKAGE_NAME, "com.oculus.socialplatform");
    private final Context mContext;
    private final Set<String> mHighPriorityNotifications = new HashSet();
    private final Set<String> mOverrideToastSuppressionNotifications = new HashSet();
    private final Set<String> mPackageBlockList = new HashSet();
    private final Set<String> mPackageSystemPersistList = new HashSet();
    private final Set<String> mSystemGeneratedNotifications = new HashSet();
    private final VrNotificationListenerService mVrNotificationListenerService;

    VrNotificationDataSetManager(Context context, VrNotificationListenerService vrNotificationListenerService) {
        this.mVrNotificationListenerService = vrNotificationListenerService;
        this.mContext = context;
        ensureResources();
    }

    private void ensureResources() {
        Resources resources = this.mContext.getResources();
        this.mPackageSystemPersistList.addAll(Arrays.asList(resources.getStringArray(R.array.system_persisted_vr_notifications)));
        this.mPackageBlockList.addAll(Arrays.asList(resources.getStringArray(R.array.blocked_vr_notifications)));
        this.mHighPriorityNotifications.addAll(Arrays.asList(resources.getStringArray(R.array.high_priority_notifications)));
        this.mSystemGeneratedNotifications.addAll(Arrays.asList(resources.getStringArray(R.array.system_generated_notifications)));
        this.mOverrideToastSuppressionNotifications.addAll(Arrays.asList(resources.getStringArray(R.array.override_toast_suppression_notifications)));
    }

    public StatusBarNotification[] getAllNotifications() {
        return this.mVrNotificationListenerService.getSortedNotifications();
    }

    public StatusBarNotification[] getAllPersistentNotifications() {
        return getFilteredNotifications(NotificationsType.ALL);
    }

    public StatusBarNotification[] getFilteredNotifications(NotificationsType notificationsType) {
        StatusBarNotification[] sortedNotifications = this.mVrNotificationListenerService.getSortedNotifications();
        ArrayList arrayList = new ArrayList();
        if (sortedNotifications != null) {
            for (StatusBarNotification statusBarNotification : sortedNotifications) {
                if (isPersistentNotification(statusBarNotification) && matchNotificationsType(statusBarNotification, notificationsType)) {
                    arrayList.add(statusBarNotification);
                }
            }
        }
        return (StatusBarNotification[]) arrayList.toArray(new StatusBarNotification[arrayList.size()]);
    }

    public StatusBarNotification[] getAllPersistentNotificationsExcept(NotificationsType notificationsType) {
        StatusBarNotification[] sortedNotifications = this.mVrNotificationListenerService.getSortedNotifications();
        ArrayList arrayList = new ArrayList();
        if (sortedNotifications != null) {
            for (StatusBarNotification statusBarNotification : sortedNotifications) {
                if (isPersistentNotification(statusBarNotification) && !matchNotificationsType(statusBarNotification, notificationsType)) {
                    arrayList.add(statusBarNotification);
                }
            }
        }
        return (StatusBarNotification[]) arrayList.toArray(new StatusBarNotification[arrayList.size()]);
    }

    public void removeNotification(String str) {
        this.mVrNotificationListenerService.cancelNotification(str);
    }

    public boolean isSystemNotification(StatusBarNotification statusBarNotification) {
        return checkFilter(this.mSystemGeneratedNotifications, statusBarNotification);
    }

    public boolean isSocialNotification(StatusBarNotification statusBarNotification) {
        return "social".equalsIgnoreCase(statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_OCULUS_CATEGORY, ""));
    }

    public boolean isDownloadNotification(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getNotification().extras.getBoolean("is_download");
    }

    public boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) {
        Notification notification = statusBarNotification.getNotification();
        return notification.extras.getBoolean("download_in_progress") || notification.extras.getBoolean("install_in_progress");
    }

    public boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) {
        return checkFilter(this.mPackageSystemPersistList, statusBarNotification);
    }

    public boolean isBlockedNotification(StatusBarNotification statusBarNotification) {
        return checkFilter(this.mPackageBlockList, statusBarNotification);
    }

    public boolean isPersistentNotification(StatusBarNotification statusBarNotification) {
        return getBooleanExtraForKey(NotificationConstants.KEY_AUI_PERSIST, statusBarNotification) && !isBlockedNotification(statusBarNotification);
    }

    public boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) {
        return checkFilter(this.mHighPriorityNotifications, statusBarNotification);
    }

    public boolean isOverrideToastSuppressionNotification(StatusBarNotification statusBarNotification) {
        return checkFilter(this.mOverrideToastSuppressionNotifications, statusBarNotification);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrshell.notifications.VrNotificationDataSetManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$notifications$NotificationsType = new int[NotificationsType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.vrshell.notifications.NotificationsType[] r0 = com.oculus.vrshell.notifications.NotificationsType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType = r0
                int[] r0 = com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrshell.notifications.NotificationsType r1 = com.oculus.vrshell.notifications.NotificationsType.ALL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrshell.notifications.NotificationsType r1 = com.oculus.vrshell.notifications.NotificationsType.SOCIAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrshell.notifications.NotificationsType r1 = com.oculus.vrshell.notifications.NotificationsType.SYSTEM     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrshell.notifications.NotificationsType r1 = com.oculus.vrshell.notifications.NotificationsType.DOWNLOADS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.vrshell.notifications.NotificationsType r1 = com.oculus.vrshell.notifications.NotificationsType.DOWNLOAD_IN_PROGRESS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.notifications.VrNotificationDataSetManager.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean matchNotificationsType(StatusBarNotification statusBarNotification, NotificationsType notificationsType) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrshell$notifications$NotificationsType[notificationsType.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return isSocialNotification(statusBarNotification);
        }
        if (i == 3) {
            return isSystemNotification(statusBarNotification);
        }
        if (i == 4) {
            return isDownloadNotification(statusBarNotification);
        }
        if (i != 5) {
            return false;
        }
        return isDownloadInProgressNotification(statusBarNotification);
    }

    private static boolean getBooleanExtraForKey(String str, StatusBarNotification statusBarNotification) {
        return statusBarNotification.getNotification().extras.getBoolean(str, false);
    }

    private static boolean checkFilter(Set<String> set, StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        String tag = statusBarNotification.getTag();
        int id = statusBarNotification.getId();
        int i = statusBarNotification.getNotification().priority;
        if (!set.contains(packageName)) {
            if (!set.contains(String.format("%s|%s", packageName, tag))) {
                if (!set.contains(String.format("%s|%s|%d", packageName, tag, Integer.valueOf(id)))) {
                    if (!set.contains(String.format("%s|%s|%d|%d", packageName, tag, Integer.valueOf(id), Integer.valueOf(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void updateNotificationListVersion() {
        NOTIF_CHANGE_INTENT_BROADCAST_PACKAGE_ALLOWLIST.forEach(new Consumer() {
            /* class com.oculus.vrshell.notifications.$$Lambda$VrNotificationDataSetManager$oJhKLesSwLQQ60et0gmOH1qzlkk */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                VrNotificationDataSetManager.this.lambda$updateNotificationListVersion$0$VrNotificationDataSetManager((String) obj);
            }
        });
    }

    public /* synthetic */ void lambda$updateNotificationListVersion$0$VrNotificationDataSetManager(String str) {
        Intent intent = new Intent(NOTIF_CHANGE_INTENT);
        intent.setPackage(str);
        this.mContext.sendBroadcast(intent);
    }
}
