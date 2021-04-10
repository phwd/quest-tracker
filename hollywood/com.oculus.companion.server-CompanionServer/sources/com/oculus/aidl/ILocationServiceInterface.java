package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILocationServiceInterface extends IInterface {
    void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationServiceInterface {
        public static ILocationServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.ILocationServiceInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationServiceInterface)) {
                return new Proxy(iBinder);
            }
            return (ILocationServiceInterface) queryLocalInterface;
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ILocationServiceInterface {
            public static ILocationServiceInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.ILocationServiceInterface");
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationWithTimezoneUsingIPOnly(iLocationCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ILocationServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
