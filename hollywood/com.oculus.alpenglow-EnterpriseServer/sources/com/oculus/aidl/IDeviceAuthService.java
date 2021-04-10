package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDeviceAuthService extends IInterface {

    public static class Default implements IDeviceAuthService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IDeviceAuthService
        public String getDeviceAuthToken(String str) throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDeviceAuthService {
        public static final String DESCRIPTOR = "com.oculus.aidl.IDeviceAuthService";
        public static final int TRANSACTION_getDeviceAuthToken = 1;

        public static class Proxy implements IDeviceAuthService {
            public static IDeviceAuthService sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.aidl.IDeviceAuthService
            public String getDeviceAuthToken(String str) throws RemoteException {
                String str2;
                IDeviceAuthService iDeviceAuthService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iDeviceAuthService = sDefaultImpl) == null) {
                        obtain2.readException();
                        str2 = obtain2.readString();
                    } else {
                        str2 = iDeviceAuthService.getDeviceAuthToken(str);
                    }
                    return str2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String deviceAuthToken = getDeviceAuthToken(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(deviceAuthToken);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        public static IDeviceAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceAuthService)) {
                return new Proxy(iBinder);
            }
            return (IDeviceAuthService) queryLocalInterface;
        }

        public static IDeviceAuthService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDeviceAuthService iDeviceAuthService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iDeviceAuthService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iDeviceAuthService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }
    }

    String getDeviceAuthToken(String str) throws RemoteException;
}
