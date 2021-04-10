package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthService2 extends IInterface {

    public static class Default implements IAuthService2 {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IAuthService2
        public Bundle getCredentials() throws RemoteException {
            return null;
        }

        @Override // com.oculus.aidl.IAuthService2
        public Bundle getDeviceScopedCredentials() throws RemoteException {
            return null;
        }
    }

    Bundle getCredentials() throws RemoteException;

    Bundle getDeviceScopedCredentials() throws RemoteException;

    public static abstract class Stub extends Binder implements IAuthService2 {
        private static final String DESCRIPTOR = "com.oculus.aidl.IAuthService2";
        static final int TRANSACTION_getCredentials = 1;
        static final int TRANSACTION_getDeviceScopedCredentials = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthService2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAuthService2)) {
                return new Proxy(iBinder);
            }
            return (IAuthService2) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Bundle credentials = getCredentials();
                parcel2.writeNoException();
                if (credentials != null) {
                    parcel2.writeInt(1);
                    credentials.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                Bundle deviceScopedCredentials = getDeviceScopedCredentials();
                parcel2.writeNoException();
                if (deviceScopedCredentials != null) {
                    parcel2.writeInt(1);
                    deviceScopedCredentials.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IAuthService2 {
            public static IAuthService2 sDefaultImpl;
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

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getCredentials() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentials();
                    }
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getDeviceScopedCredentials() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceScopedCredentials();
                    }
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAuthService2 iAuthService2) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAuthService2 == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAuthService2;
                return true;
            }
        }

        public static IAuthService2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
