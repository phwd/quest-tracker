package com.oculus.notifications;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.notifications.INotificationDataSetProvider;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.notifications.StatusBarNotificationsList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationDataSetService {
    public static final String DATASET_INTENT = "com.oculus.vrshell.notifications.aidl.MAIN";
    public static final String DATASET_SERVICE_PACKAGE = "com.oculus.vrshell";
    public static final String TAG = LoggingUtil.tag(NotificationDataSetService.class);
    public INotificationDataSetProvider mDataSetProvider;
    public Set<NotificationDataSetListener> mNotificationDataSetListeners;

    public static class Loader {
        public static NotificationDataSetService sInstance;

        public static final NotificationDataSetService getInstance() {
            NotificationDataSetService notificationDataSetService = sInstance;
            if (notificationDataSetService != null) {
                return notificationDataSetService;
            }
            NotificationDataSetService notificationDataSetService2 = new NotificationDataSetService();
            sInstance = notificationDataSetService2;
            return notificationDataSetService2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void connectToDataSetProvider(Context context) {
        AnonymousClass2 r3 = new ServiceConnection() {
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
        context.getApplicationContext().bindService(intent, r3, 1);
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

    public void enableDNDMode(boolean z) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.enableDNDMode(z);
            } catch (RemoteException unused) {
            }
        }
    }

    public List<StatusBarNotification> getAllNotifications() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllNotifications();
            } catch (RemoteException unused) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotification> getAllPersistentNotifications() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllPersistentNotifications();
            } catch (RemoteException unused) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getAllPersistentNotificationsExcept(notificationsType);
            } catch (RemoteException unused) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public INotificationDataSetProvider getDataSetProvider() {
        return this.mDataSetProvider;
    }

    public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getFilteredNotifications(notificationsType);
            } catch (RemoteException unused) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType... notificationsTypeArr) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                return iNotificationDataSetProvider.getNotificationsByType(notificationsTypeArr);
            } catch (RemoteException unused) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public void notifyDataSetListeners() {
        synchronized (this.mNotificationDataSetListeners) {
            for (NotificationDataSetListener notificationDataSetListener : this.mNotificationDataSetListeners) {
                notificationDataSetListener.onDataSetChanged();
            }
        }
    }

    public void registerDataSetListener(NotificationDataSetListener notificationDataSetListener) {
        synchronized (this.mNotificationDataSetListeners) {
            this.mNotificationDataSetListeners.add(notificationDataSetListener);
        }
    }

    public void removeNotification(String str) {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.removeNotification(str);
            } catch (RemoteException unused) {
            }
        }
    }

    public void suppressToasts() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.suppressToasts();
            } catch (RemoteException unused) {
            }
        }
    }

    public void unregisterDataSetListener(NotificationDataSetListener notificationDataSetListener) {
        synchronized (this.mNotificationDataSetListeners) {
            this.mNotificationDataSetListeners.remove(notificationDataSetListener);
        }
    }

    public void unsuppressToasts() {
        INotificationDataSetProvider iNotificationDataSetProvider = this.mDataSetProvider;
        if (iNotificationDataSetProvider != null) {
            try {
                iNotificationDataSetProvider.unsuppressToasts();
            } catch (RemoteException unused) {
            }
        }
    }

    public static NotificationDataSetService getInstance() {
        return Loader.getInstance();
    }

    public NotificationDataSetService() {
        this.mNotificationDataSetListeners = new HashSet();
    }
}
