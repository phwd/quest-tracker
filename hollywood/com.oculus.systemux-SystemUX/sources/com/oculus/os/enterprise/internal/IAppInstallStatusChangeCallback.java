package com.oculus.os.enterprise.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface IAppInstallStatusChangeCallback extends IInterface {

    public static class Default implements IAppInstallStatusChangeCallback {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback
        public void onStatusChange(Map map) throws RemoteException {
        }
    }

    void onStatusChange(Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppInstallStatusChangeCallback {
        private static final String DESCRIPTOR = "com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback";
        static final int TRANSACTION_onStatusChange = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppInstallStatusChangeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAppInstallStatusChangeCallback)) {
                return new Proxy(iBinder);
            }
            return (IAppInstallStatusChangeCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onStatusChange(parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IAppInstallStatusChangeCallback {
            public static IAppInstallStatusChangeCallback sDefaultImpl;
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

            @Override // com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback
            public void onStatusChange(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onStatusChange(map);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAppInstallStatusChangeCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAppInstallStatusChangeCallback;
                return true;
            }
        }

        public static IAppInstallStatusChangeCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
