package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {

    public static abstract class Stub extends Binder implements INotificationSideChannel {

        public static class Proxy implements INotificationSideChannel {
            public static INotificationSideChannel A01;
            public IBinder A00;

            public final IBinder asBinder() {
                return this.A00;
            }

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public final void A1e(String str, int i, String str2) throws RemoteException {
                INotificationSideChannel iNotificationSideChannel;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (!this.A00.transact(2, obtain, null, 1) && (iNotificationSideChannel = A01) != null) {
                        iNotificationSideChannel.A1e(str, i, str2);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public final void A1f(String str) throws RemoteException {
                INotificationSideChannel iNotificationSideChannel;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    obtain.writeString(str);
                    if (!this.A00.transact(3, obtain, null, 1) && (iNotificationSideChannel = A01) != null) {
                        iNotificationSideChannel.A1f(str);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public final void A5n(String str, int i, String str2, Notification notification) throws RemoteException {
                INotificationSideChannel iNotificationSideChannel;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.A00.transact(1, obtain, null, 1) && (iNotificationSideChannel = A01) != null) {
                        iNotificationSideChannel.A5n(str, i, str2, notification);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Notification notification;
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                String readString = parcel.readString();
                int readInt = parcel.readInt();
                String readString2 = parcel.readString();
                if (parcel.readInt() != 0) {
                    notification = (Notification) Notification.CREATOR.createFromParcel(parcel);
                } else {
                    notification = null;
                }
                A5n(readString, readInt, readString2, notification);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                A1e(parcel.readString(), parcel.readInt(), parcel.readString());
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("android.support.v4.app.INotificationSideChannel");
                A1f(parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.support.v4.app.INotificationSideChannel");
                return true;
            }
        }

        public static INotificationSideChannel asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof INotificationSideChannel)) {
                return new Proxy(iBinder);
            }
            return (INotificationSideChannel) queryLocalInterface;
        }

        public static INotificationSideChannel getDefaultImpl() {
            return Proxy.A01;
        }

        public static boolean setDefaultImpl(INotificationSideChannel iNotificationSideChannel) {
            if (Proxy.A01 != null || iNotificationSideChannel == null) {
                return false;
            }
            Proxy.A01 = iNotificationSideChannel;
            return true;
        }

        public Stub() {
            attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }
    }

    void A1e(String str, int i, String str2) throws RemoteException;

    void A1f(String str) throws RemoteException;

    void A5n(String str, int i, String str2, Notification notification) throws RemoteException;
}
