package com.facebook.mobileconfigservice.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMobileConfig extends IInterface {

    public static abstract class Stub extends Binder implements IMobileConfig {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                A9X(parcel.readString());
            } else if (i == 2) {
                parcel.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                A9Z(parcel.readString(), parcel.readString(), parcel.readInt());
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.facebook.mobileconfigservice.service.IMobileConfig");
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        public static class Proxy implements IMobileConfig {
            public static IMobileConfig A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public final void A9X(String str) throws RemoteException {
                IMobileConfig iMobileConfig;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
                    obtain.writeString(str);
                    if (this.A00.transact(1, obtain, obtain2, 0) || (iMobileConfig = A01) == null) {
                        obtain2.readException();
                    } else {
                        iMobileConfig.A9X(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public final void A9Z(String str, String str2, int i) throws RemoteException {
                IMobileConfig iMobileConfig;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.A00.transact(2, obtain, obtain2, 0) || (iMobileConfig = A01) == null) {
                        obtain2.readException();
                    } else {
                        iMobileConfig.A9Z(str, str2, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

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

        public static boolean setDefaultImpl(IMobileConfig iMobileConfig) {
            if (Proxy.A01 != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMobileConfig == null) {
                return false;
            } else {
                Proxy.A01 = iMobileConfig;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, "com.facebook.mobileconfigservice.service.IMobileConfig");
        }

        public static IMobileConfig getDefaultImpl() {
            return Proxy.A01;
        }
    }

    void A9X(String str) throws RemoteException;

    void A9Z(String str, String str2, int i) throws RemoteException;
}
