package com.oculus.vrshell.notifications;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import java.util.List;

public interface INotificationDataSetProvider extends IInterface {
    void enableDNDMode(boolean z) throws RemoteException;

    List<StatusBarNotification> getAllNotifications() throws RemoteException;

    List<StatusBarNotification> getAllPersistentNotifications() throws RemoteException;

    List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) throws RemoteException;

    List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) throws RemoteException;

    List<StatusBarNotificationsList> getNotificationsByType(NotificationsType[] notificationsTypeArr) throws RemoteException;

    boolean isBlockedNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isDownloadNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isSocialNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isSystemNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException;

    void removeNotification(String str) throws RemoteException;

    void suppressToasts() throws RemoteException;

    void unsuppressToasts() throws RemoteException;

    public static abstract class Stub extends Binder implements INotificationDataSetProvider {
        private static final String DESCRIPTOR = "com.oculus.vrshell.notifications.INotificationDataSetProvider";
        static final int TRANSACTION_enableDNDMode = 17;
        static final int TRANSACTION_getAllNotifications = 1;
        static final int TRANSACTION_getAllPersistentNotifications = 2;
        static final int TRANSACTION_getAllPersistentNotificationsExcept = 3;
        static final int TRANSACTION_getFilteredNotifications = 4;
        static final int TRANSACTION_getNotificationsByType = 5;
        static final int TRANSACTION_isBlockedNotification = 12;
        static final int TRANSACTION_isDownloadInProgressNotification = 10;
        static final int TRANSACTION_isDownloadNotification = 9;
        static final int TRANSACTION_isHighPriorityNotification = 14;
        static final int TRANSACTION_isPersistentNotification = 13;
        static final int TRANSACTION_isSocialNotification = 8;
        static final int TRANSACTION_isSystemNotification = 7;
        static final int TRANSACTION_isSystemPersistentNotification = 11;
        static final int TRANSACTION_removeNotification = 6;
        static final int TRANSACTION_suppressToasts = 15;
        static final int TRANSACTION_unsuppressToasts = 16;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationDataSetProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof INotificationDataSetProvider)) {
                return new Proxy(iBinder);
            }
            return (INotificationDataSetProvider) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                NotificationsType notificationsType = null;
                StatusBarNotification statusBarNotification = null;
                StatusBarNotification statusBarNotification2 = null;
                StatusBarNotification statusBarNotification3 = null;
                StatusBarNotification statusBarNotification4 = null;
                StatusBarNotification statusBarNotification5 = null;
                StatusBarNotification statusBarNotification6 = null;
                StatusBarNotification statusBarNotification7 = null;
                StatusBarNotification statusBarNotification8 = null;
                NotificationsType notificationsType2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<StatusBarNotification> allNotifications = getAllNotifications();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allNotifications);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<StatusBarNotification> allPersistentNotifications = getAllPersistentNotifications();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allPersistentNotifications);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            notificationsType = NotificationsType.CREATOR.createFromParcel(parcel);
                        }
                        List<StatusBarNotification> allPersistentNotificationsExcept = getAllPersistentNotificationsExcept(notificationsType);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(allPersistentNotificationsExcept);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            notificationsType2 = NotificationsType.CREATOR.createFromParcel(parcel);
                        }
                        List<StatusBarNotification> filteredNotifications = getFilteredNotifications(notificationsType2);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(filteredNotifications);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<StatusBarNotificationsList> notificationsByType = getNotificationsByType((NotificationsType[]) parcel.createTypedArray(NotificationsType.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeTypedList(notificationsByType);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeNotification(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification8 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isSystemNotification = isSystemNotification(statusBarNotification8);
                        parcel2.writeNoException();
                        parcel2.writeInt(isSystemNotification ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification7 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isSocialNotification = isSocialNotification(statusBarNotification7);
                        parcel2.writeNoException();
                        parcel2.writeInt(isSocialNotification ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification6 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isDownloadNotification = isDownloadNotification(statusBarNotification6);
                        parcel2.writeNoException();
                        parcel2.writeInt(isDownloadNotification ? 1 : 0);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification5 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isDownloadInProgressNotification = isDownloadInProgressNotification(statusBarNotification5);
                        parcel2.writeNoException();
                        parcel2.writeInt(isDownloadInProgressNotification ? 1 : 0);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification4 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isSystemPersistentNotification = isSystemPersistentNotification(statusBarNotification4);
                        parcel2.writeNoException();
                        parcel2.writeInt(isSystemPersistentNotification ? 1 : 0);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification3 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isBlockedNotification = isBlockedNotification(statusBarNotification3);
                        parcel2.writeNoException();
                        parcel2.writeInt(isBlockedNotification ? 1 : 0);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification2 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isPersistentNotification = isPersistentNotification(statusBarNotification2);
                        parcel2.writeNoException();
                        parcel2.writeInt(isPersistentNotification ? 1 : 0);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        boolean isHighPriorityNotification = isHighPriorityNotification(statusBarNotification);
                        parcel2.writeNoException();
                        parcel2.writeInt(isHighPriorityNotification ? 1 : 0);
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        suppressToasts();
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        unsuppressToasts();
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        enableDNDMode(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements INotificationDataSetProvider {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllNotifications() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllPersistentNotifications() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationsType != null) {
                        obtain.writeInt(1);
                        notificationsType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationsType != null) {
                        obtain.writeInt(1);
                        notificationsType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType[] notificationsTypeArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(notificationsTypeArr, 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatusBarNotificationsList.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void removeNotification(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSystemNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSocialNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isDownloadNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isBlockedNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (statusBarNotification != null) {
                        obtain.writeInt(1);
                        statusBarNotification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void suppressToasts() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void unsuppressToasts() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void enableDNDMode(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
