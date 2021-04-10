package com.oculus.notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.notifications.INotificationDataSetProvider;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.notifications.StatusBarNotificationsList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationDataSetService {
    private static final String DATASET_INTENT = "com.oculus.vrshell.notifications.aidl.MAIN";
    private static final String DATASET_SERVICE_PACKAGE = "com.oculus.vrshell";
    private static final String TAG = LoggingUtil.tag(NotificationDataSetService.class);
    private INotificationDataSetProvider mDataSetProvider;
    private Set<NotificationDataSetListener> mNotificationDataSetListeners;

    private NotificationDataSetService() {
        this.mNotificationDataSetListeners = new HashSet();
    }

    public void connectToProvider(final Context context) {
        if (this.mDataSetProvider == null) {
            new Handler().postDelayed(new Runnable() {
                /* class com.oculus.notifications.NotificationDataSetService.AnonymousClass1 */

                public void run() {
                    NotificationDataSetService.this.connectToDataSetProvider(context);
                }
            }, 0);
        }
    }

    public static NotificationDataSetService getInstance() {
        return Loader.getInstance();
    }

    public void registerDataSetListener(NotificationDataSetListener notificationDataSetListener) {
        synchronized (this.mNotificationDataSetListeners) {
            this.mNotificationDataSetListeners.add(notificationDataSetListener);
        }
    }

    public void unregisterDataSetListener(NotificationDataSetListener notificationDataSetListener) {
        synchronized (this.mNotificationDataSetListeners) {
            this.mNotificationDataSetListeners.remove(notificationDataSetListener);
        }
    }

    public void notifyDataSetListeners() {
        synchronized (this.mNotificationDataSetListeners) {
            for (NotificationDataSetListener notificationDataSetListener : this.mNotificationDataSetListeners) {
                notificationDataSetListener.onDataSetChanged();
            }
        }
    }

    public List<StatusBarNotification> getAllNotifications() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllNotifications();
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to fetch notifications.", e);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotification> getAllPersistentNotifications() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllPersistentNotifications();
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to fetch notifications.", e);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllPersistentNotificationsExcept(notificationsType);
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to fetch notifications.", e);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getFilteredNotifications(notificationsType);
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to fetch notifications.", e);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType... notificationsTypeArr) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getNotificationsByType(notificationsTypeArr);
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to fetch notifications.", e);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public void removeNotification(String str) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.removeNotification(str);
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to remove notification.", e);
            }
        }
    }

    public void suppressToasts() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.suppressToasts();
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to suppress toasts.", e);
            }
        }
    }

    public void unsuppressToasts() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.unsuppressToasts();
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to suppress toasts.", e);
            }
        }
    }

    public void enableDNDMode(boolean z) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.enableDNDMode(z);
            } catch (RemoteException e) {
                Log.d(TAG, "Unable to enable DND mode.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public INotificationDataSetProvider getDataSetProvider() {
        return this.mDataSetProvider;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void connectToDataSetProvider(Context context) {
        AnonymousClass2 r0 = new ServiceConnection() {
            /* class com.oculus.notifications.NotificationDataSetService.AnonymousClass2 */

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                NotificationDataSetService.this.mDataSetProvider = INotificationDataSetProvider.Stub.asInterface(iBinder);
                NotificationDataSetService.this.notifyDataSetListeners();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                NotificationDataSetService.this.mDataSetProvider = null;
            }
        };
        Intent intent = new Intent(DATASET_INTENT);
        intent.setPackage("com.oculus.vrshell");
        context.getApplicationContext().bindService(intent, r0, 1);
    }

    private static class Loader {
        static NotificationDataSetService sInstance;

        private Loader() {
        }

        /* access modifiers changed from: private */
        public static final NotificationDataSetService getInstance() {
            if (sInstance == null) {
                sInstance = new NotificationDataSetService();
            }
            return sInstance;
        }
    }
}
