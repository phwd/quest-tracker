package com.oculus.vrshell.notifications;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import java.util.List;

public interface INotificationDataSetProvider extends IInterface {

    public static class Default implements INotificationDataSetProvider {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void enableDNDMode(boolean z) throws RemoteException {
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllNotifications() throws RemoteException {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllPersistentNotifications() throws RemoteException {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) throws RemoteException {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) throws RemoteException {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType[] notificationsTypeArr) throws RemoteException {
            return null;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isBlockedNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isDownloadNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSocialNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSystemNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
            return false;
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void removeNotification(String str) throws RemoteException {
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void suppressToasts() throws RemoteException {
        }

        @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
        public void unsuppressToasts() throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements INotificationDataSetProvider {
        public static final String DESCRIPTOR = "com.oculus.vrshell.notifications.INotificationDataSetProvider";
        public static final int TRANSACTION_enableDNDMode = 17;
        public static final int TRANSACTION_getAllNotifications = 1;
        public static final int TRANSACTION_getAllPersistentNotifications = 2;
        public static final int TRANSACTION_getAllPersistentNotificationsExcept = 3;
        public static final int TRANSACTION_getFilteredNotifications = 4;
        public static final int TRANSACTION_getNotificationsByType = 5;
        public static final int TRANSACTION_isBlockedNotification = 12;
        public static final int TRANSACTION_isDownloadInProgressNotification = 10;
        public static final int TRANSACTION_isDownloadNotification = 9;
        public static final int TRANSACTION_isHighPriorityNotification = 14;
        public static final int TRANSACTION_isPersistentNotification = 13;
        public static final int TRANSACTION_isSocialNotification = 8;
        public static final int TRANSACTION_isSystemNotification = 7;
        public static final int TRANSACTION_isSystemPersistentNotification = 11;
        public static final int TRANSACTION_removeNotification = 6;
        public static final int TRANSACTION_suppressToasts = 15;
        public static final int TRANSACTION_unsuppressToasts = 16;

        public static class Proxy implements INotificationDataSetProvider {
            public static INotificationDataSetProvider sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void enableDNDMode(boolean z) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(17, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iNotificationDataSetProvider.enableDNDMode(z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllNotifications() throws RemoteException {
                List<StatusBarNotification> list;
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                    } else {
                        list = iNotificationDataSetProvider.getAllNotifications();
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllPersistentNotifications() throws RemoteException {
                List<StatusBarNotification> list;
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                    } else {
                        list = iNotificationDataSetProvider.getAllPersistentNotifications();
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getAllPersistentNotificationsExcept(NotificationsType notificationsType) throws RemoteException {
                List<StatusBarNotification> list;
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                    } else {
                        list = iNotificationDataSetProvider.getAllPersistentNotificationsExcept(notificationsType);
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotification> getFilteredNotifications(NotificationsType notificationsType) throws RemoteException {
                List<StatusBarNotification> list;
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(StatusBarNotification.CREATOR);
                    } else {
                        list = iNotificationDataSetProvider.getFilteredNotifications(notificationsType);
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public List<StatusBarNotificationsList> getNotificationsByType(NotificationsType[] notificationsTypeArr) throws RemoteException {
                List<StatusBarNotificationsList> list;
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(notificationsTypeArr, 0);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(StatusBarNotificationsList.CREATOR);
                    } else {
                        list = iNotificationDataSetProvider.getNotificationsByType(notificationsTypeArr);
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isBlockedNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isBlockedNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isDownloadInProgressNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isDownloadNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isDownloadNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isHighPriorityNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isHighPriorityNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isPersistentNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSocialNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isSocialNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSystemNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isSystemNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public boolean isSystemPersistentNotification(StatusBarNotification statusBarNotification) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
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
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && (iNotificationDataSetProvider = sDefaultImpl) != null) {
                        return iNotificationDataSetProvider.isSystemPersistentNotification(statusBarNotification);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void removeNotification(String str) throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iNotificationDataSetProvider.removeNotification(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void suppressToasts() throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(15, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iNotificationDataSetProvider.suppressToasts();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.vrshell.notifications.INotificationDataSetProvider
            public void unsuppressToasts() throws RemoteException {
                INotificationDataSetProvider iNotificationDataSetProvider;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, obtain, obtain2, 0) || (iNotificationDataSetProvider = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iNotificationDataSetProvider.unsuppressToasts();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
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

        public static INotificationDataSetProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(INotificationDataSetProvider iNotificationDataSetProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iNotificationDataSetProvider == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iNotificationDataSetProvider;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z;
            List<StatusBarNotificationsList> list;
            if (i != 1598968902) {
                StatusBarNotification statusBarNotification = null;
                NotificationsType notificationsType = null;
                NotificationsType notificationsType2 = null;
                StatusBarNotification statusBarNotification2 = null;
                StatusBarNotification statusBarNotification3 = null;
                StatusBarNotification statusBarNotification4 = null;
                StatusBarNotification statusBarNotification5 = null;
                StatusBarNotification statusBarNotification6 = null;
                StatusBarNotification statusBarNotification7 = null;
                StatusBarNotification statusBarNotification8 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        list = getAllNotifications();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(list == 1 ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        list = getAllPersistentNotifications();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(list == 1 ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            notificationsType = NotificationsType.CREATOR.createFromParcel(parcel);
                        }
                        list = getAllPersistentNotificationsExcept(notificationsType);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(list == 1 ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            notificationsType2 = NotificationsType.CREATOR.createFromParcel(parcel);
                        }
                        list = getFilteredNotifications(notificationsType2);
                        parcel2.writeNoException();
                        parcel2.writeTypedList(list == 1 ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        list = getNotificationsByType((NotificationsType[]) parcel.createTypedArray(NotificationsType.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeTypedList(list == 1 ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeNotification(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification2 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isSystemNotification(statusBarNotification2);
                        parcel2.writeNoException();
                        int i3 = z ? 1 : 0;
                        int i4 = z ? 1 : 0;
                        int i5 = z ? 1 : 0;
                        int i6 = z ? 1 : 0;
                        int i7 = z ? 1 : 0;
                        int i8 = z ? 1 : 0;
                        int i9 = z ? 1 : 0;
                        int i10 = z ? 1 : 0;
                        int i11 = z ? 1 : 0;
                        parcel2.writeInt(i3);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification3 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isSocialNotification(statusBarNotification3);
                        parcel2.writeNoException();
                        int i32 = z ? 1 : 0;
                        int i42 = z ? 1 : 0;
                        int i52 = z ? 1 : 0;
                        int i62 = z ? 1 : 0;
                        int i72 = z ? 1 : 0;
                        int i82 = z ? 1 : 0;
                        int i92 = z ? 1 : 0;
                        int i102 = z ? 1 : 0;
                        int i112 = z ? 1 : 0;
                        parcel2.writeInt(i32);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification4 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isDownloadNotification(statusBarNotification4);
                        parcel2.writeNoException();
                        int i322 = z ? 1 : 0;
                        int i422 = z ? 1 : 0;
                        int i522 = z ? 1 : 0;
                        int i622 = z ? 1 : 0;
                        int i722 = z ? 1 : 0;
                        int i822 = z ? 1 : 0;
                        int i922 = z ? 1 : 0;
                        int i1022 = z ? 1 : 0;
                        int i1122 = z ? 1 : 0;
                        parcel2.writeInt(i322);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification5 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isDownloadInProgressNotification(statusBarNotification5);
                        parcel2.writeNoException();
                        int i3222 = z ? 1 : 0;
                        int i4222 = z ? 1 : 0;
                        int i5222 = z ? 1 : 0;
                        int i6222 = z ? 1 : 0;
                        int i7222 = z ? 1 : 0;
                        int i8222 = z ? 1 : 0;
                        int i9222 = z ? 1 : 0;
                        int i10222 = z ? 1 : 0;
                        int i11222 = z ? 1 : 0;
                        parcel2.writeInt(i3222);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification6 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isSystemPersistentNotification(statusBarNotification6);
                        parcel2.writeNoException();
                        int i32222 = z ? 1 : 0;
                        int i42222 = z ? 1 : 0;
                        int i52222 = z ? 1 : 0;
                        int i62222 = z ? 1 : 0;
                        int i72222 = z ? 1 : 0;
                        int i82222 = z ? 1 : 0;
                        int i92222 = z ? 1 : 0;
                        int i102222 = z ? 1 : 0;
                        int i112222 = z ? 1 : 0;
                        parcel2.writeInt(i32222);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification7 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isBlockedNotification(statusBarNotification7);
                        parcel2.writeNoException();
                        int i322222 = z ? 1 : 0;
                        int i422222 = z ? 1 : 0;
                        int i522222 = z ? 1 : 0;
                        int i622222 = z ? 1 : 0;
                        int i722222 = z ? 1 : 0;
                        int i822222 = z ? 1 : 0;
                        int i922222 = z ? 1 : 0;
                        int i1022222 = z ? 1 : 0;
                        int i1122222 = z ? 1 : 0;
                        parcel2.writeInt(i322222);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification8 = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isPersistentNotification(statusBarNotification8);
                        parcel2.writeNoException();
                        int i3222222 = z ? 1 : 0;
                        int i4222222 = z ? 1 : 0;
                        int i5222222 = z ? 1 : 0;
                        int i6222222 = z ? 1 : 0;
                        int i7222222 = z ? 1 : 0;
                        int i8222222 = z ? 1 : 0;
                        int i9222222 = z ? 1 : 0;
                        int i10222222 = z ? 1 : 0;
                        int i11222222 = z ? 1 : 0;
                        parcel2.writeInt(i3222222);
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            statusBarNotification = (StatusBarNotification) StatusBarNotification.CREATOR.createFromParcel(parcel);
                        }
                        z = isHighPriorityNotification(statusBarNotification);
                        parcel2.writeNoException();
                        int i32222222 = z ? 1 : 0;
                        int i42222222 = z ? 1 : 0;
                        int i52222222 = z ? 1 : 0;
                        int i62222222 = z ? 1 : 0;
                        int i72222222 = z ? 1 : 0;
                        int i82222222 = z ? 1 : 0;
                        int i92222222 = z ? 1 : 0;
                        int i102222222 = z ? 1 : 0;
                        int i112222222 = z ? 1 : 0;
                        parcel2.writeInt(i32222222);
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
                        boolean z2 = false;
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        enableDNDMode(z2);
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
    }

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
}
