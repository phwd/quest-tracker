package com.oculus.vrshell;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.notifications.INotificationDataSetProvider;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.notifications.StatusBarNotificationsList;
import com.oculus.vrshell.notifications.VrNotificationDataSetManager;
import com.oculus.vrshell.notifications.VrNotificationListenerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VrNotificationService extends Service {
    private static final String TAG = LoggingUtil.tag(VrNotificationService.class);
    private final INotificationDataSetProvider mBinder = new INotificationDataSetProvider.Stub() {
        /* class com.oculus.vrshell.VrNotificationService.AnonymousClass1 */

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllNotifications() {
            return Arrays.asList(VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().getAllNotifications());
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllPersistentNotifications() {
            return Arrays.asList(VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().getAllPersistentNotifications());
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) {
            return Arrays.asList(VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().getAllPersistentNotificationsExcept(notificationsType));
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) {
            return Arrays.asList(VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().getFilteredNotifications(notificationsType));
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType[] notificationsTypeArr) {
            StatusBarNotification[] statusBarNotificationArr;
            ArrayList arrayList = new ArrayList(notificationsTypeArr.length);
            VrNotificationDataSetManager vrNotificationDataSetManager = VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager();
            for (NotificationsType notificationsType : notificationsTypeArr) {
                if (notificationsType == NotificationsType.ALL) {
                    statusBarNotificationArr = vrNotificationDataSetManager.getAllPersistentNotificationsExcept(NotificationsType.DOWNLOAD_IN_PROGRESS);
                } else {
                    statusBarNotificationArr = vrNotificationDataSetManager.getFilteredNotifications(notificationsType);
                }
                arrayList.add(new StatusBarNotificationsList(notificationsType, Arrays.asList(statusBarNotificationArr)));
            }
            return arrayList;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void removeNotification(String str) {
            VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().removeNotification(str);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSystemNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isSystemNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSocialNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isSocialNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isDownloadNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isDownloadNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isDownloadInProgressNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isSystemPersistentNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isBlockedNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isBlockedNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isPersistentNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isPersistentNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) {
            return VrNotificationService.this.mVrNotificationListenerService.getVrNotificationDataSetManager().isHighPriorityNotification(statusBarNotification);
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void suppressToasts() {
            VrNotificationService.this.mVrNotificationListenerService.suppressToasts();
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void unsuppressToasts() {
            VrNotificationService.this.mVrNotificationListenerService.unsuppressToasts();
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void enableDNDMode(boolean z) {
            VrNotificationService.this.mVrNotificationListenerService.enableDNDMode(z);
        }
    };
    private VrNotificationListenerService mVrNotificationListenerService;

    @Nullable
    public IBinder onBind(Intent intent) {
        return (IBinder) this.mBinder;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "VrNotificationService onCreate()");
        registerVrNotificationListenerService();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str = TAG;
        Log.d(str, "VrNotificationService onStartCommand() " + intent);
        if (this.mVrNotificationListenerService != null) {
            return 1;
        }
        registerVrNotificationListenerService();
        return 1;
    }

    private void registerVrNotificationListenerService() {
        this.mVrNotificationListenerService = VrNotificationListenerService.getInstance(getApplicationContext());
        this.mVrNotificationListenerService.registerAsSystemServiceUsingReflection();
    }
}
