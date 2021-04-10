package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthService2 extends IInterface {
    Bundle getCredentials() throws RemoteException;

    public static abstract class Stub extends Binder implements IAuthService2 {
        public static IAuthService2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.IAuthService2");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAuthService2)) {
                return new Proxy(iBinder);
            }
            return (IAuthService2) queryLocalInterface;
        }

        private static class Proxy implements IAuthService2 {
            private IBinder mRemote;

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
                    obtain.writeInterfaceToken("com.oculus.aidl.IAuthService2");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
