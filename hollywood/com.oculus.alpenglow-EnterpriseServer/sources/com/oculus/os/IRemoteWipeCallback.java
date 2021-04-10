package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteWipeCallback extends IInterface {

    public static class Default implements IRemoteWipeCallback {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onFailure(String str) throws RemoteException {
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onSuccess() throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IRemoteWipeCallback {
        public static final String DESCRIPTOR = "com.oculus.os.IRemoteWipeCallback";
        public static final int TRANSACTION_onFailure = 2;
        public static final int TRANSACTION_onSuccess = 1;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSuccess();
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onFailure(parcel.readString());
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        public static class Proxy implements IRemoteWipeCallback {
            public static IRemoteWipeCallback sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.os.IRemoteWipeCallback
            public void onFailure(String str) throws RemoteException {
                IRemoteWipeCallback iRemoteWipeCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iRemoteWipeCallback = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iRemoteWipeCallback.onFailure(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.IRemoteWipeCallback
            public void onSuccess() throws RemoteException {
                IRemoteWipeCallback iRemoteWipeCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iRemoteWipeCallback = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iRemoteWipeCallback.onSuccess();
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

        public static IRemoteWipeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteWipeCallback)) {
                return new Proxy(iBinder);
            }
            return (IRemoteWipeCallback) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IRemoteWipeCallback iRemoteWipeCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iRemoteWipeCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iRemoteWipeCallback;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteWipeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void onFailure(String str) throws RemoteException;

    void onSuccess() throws RemoteException;
}
