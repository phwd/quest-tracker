package com.facebook.mobileconfigservice.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMobileConfig extends IInterface {
    void subscribe(String str) throws RemoteException;

    void subscribeWithProcessName(String str, String str2, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMobileConfig {
        public static IMobileConfig asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileConfig)) {
                return new Proxy(iBinder);
            }
            return (IMobileConfig) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                subscribe(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                subscribeWithProcessName(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.facebook.mobileconfigservice.service.IMobileConfig");
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public static class Proxy implements IMobileConfig {
            public static IMobileConfig sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public final IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public final void subscribe(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().subscribe(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public final void subscribeWithProcessName(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().subscribeWithProcessName(str, str2, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IMobileConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
