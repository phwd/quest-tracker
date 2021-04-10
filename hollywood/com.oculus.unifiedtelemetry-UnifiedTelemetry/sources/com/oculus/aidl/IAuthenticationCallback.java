package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthenticationCallback extends IInterface {

    public static class Default implements IAuthenticationCallback {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IAuthenticationCallback
        public void onFailure(int i, Bundle bundle) throws RemoteException {
        }

        @Override // com.oculus.aidl.IAuthenticationCallback
        public void onSuccess(Bundle bundle) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IAuthenticationCallback {
        public static final String DESCRIPTOR = "com.oculus.aidl.IAuthenticationCallback";
        public static final int TRANSACTION_onFailure = 2;
        public static final int TRANSACTION_onSuccess = 1;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                onSuccess(bundle);
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int readInt = parcel.readInt();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                onFailure(readInt, bundle);
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        public static class Proxy implements IAuthenticationCallback {
            public static IAuthenticationCallback sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.aidl.IAuthenticationCallback
            public void onFailure(int i, Bundle bundle) throws RemoteException {
                IAuthenticationCallback iAuthenticationCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iAuthenticationCallback = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iAuthenticationCallback.onFailure(i, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthenticationCallback
            public void onSuccess(Bundle bundle) throws RemoteException {
                IAuthenticationCallback iAuthenticationCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iAuthenticationCallback = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iAuthenticationCallback.onSuccess(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }
        }

        public static IAuthenticationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAuthenticationCallback)) {
                return new Proxy(iBinder);
            }
            return (IAuthenticationCallback) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IAuthenticationCallback iAuthenticationCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAuthenticationCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAuthenticationCallback;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthenticationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void onFailure(int i, Bundle bundle) throws RemoteException;

    void onSuccess(Bundle bundle) throws RemoteException;
}
