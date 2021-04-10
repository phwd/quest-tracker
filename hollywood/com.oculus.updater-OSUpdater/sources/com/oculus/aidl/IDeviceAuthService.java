package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDeviceAuthService extends IInterface {
    String getDeviceAuthToken(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceAuthService {
        public Stub() {
            attachInterface(this, "com.oculus.aidl.IDeviceAuthService");
        }

        public static IDeviceAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.IDeviceAuthService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceAuthService)) {
                return new Proxy(iBinder);
            }
            return (IDeviceAuthService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.oculus.aidl.IDeviceAuthService");
                String deviceAuthToken = getDeviceAuthToken(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(deviceAuthToken);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.oculus.aidl.IDeviceAuthService");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IDeviceAuthService {
            public static IDeviceAuthService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.IDeviceAuthService
            public String getDeviceAuthToken(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IDeviceAuthService");
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceAuthToken(str);
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IDeviceAuthService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
