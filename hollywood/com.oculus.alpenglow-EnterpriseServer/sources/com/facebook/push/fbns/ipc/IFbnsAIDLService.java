package com.facebook.push.fbns.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IFbnsAIDLService extends IInterface {

    public static abstract class Stub extends Binder implements IFbnsAIDLService {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            FbnsAIDLRequest fbnsAIDLRequest = null;
            if (i == 1) {
                parcel.enforceInterface("com.facebook.push.fbns.ipc.IFbnsAIDLService");
                if (parcel.readInt() != 0) {
                    fbnsAIDLRequest = FbnsAIDLRequest.CREATOR.createFromParcel(parcel);
                }
                FbnsAIDLResult A75 = A75(fbnsAIDLRequest);
                parcel2.writeNoException();
                if (A75 != null) {
                    parcel2.writeInt(1);
                    A75.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.facebook.push.fbns.ipc.IFbnsAIDLService");
                if (parcel.readInt() != 0) {
                    fbnsAIDLRequest = FbnsAIDLRequest.CREATOR.createFromParcel(parcel);
                }
                A8h(fbnsAIDLRequest);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.facebook.push.fbns.ipc.IFbnsAIDLService");
                return true;
            }
        }

        public static class Proxy implements IFbnsAIDLService {
            public static IFbnsAIDLService A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
            public final FbnsAIDLResult A75(FbnsAIDLRequest fbnsAIDLRequest) throws RemoteException {
                FbnsAIDLResult fbnsAIDLResult;
                IFbnsAIDLService iFbnsAIDLService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.fbns.ipc.IFbnsAIDLService");
                    if (fbnsAIDLRequest != null) {
                        obtain.writeInt(1);
                        fbnsAIDLRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.A00.transact(1, obtain, obtain2, 0) || (iFbnsAIDLService = A01) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            fbnsAIDLResult = FbnsAIDLResult.CREATOR.createFromParcel(obtain2);
                        } else {
                            fbnsAIDLResult = null;
                        }
                    } else {
                        fbnsAIDLResult = iFbnsAIDLService.A75(fbnsAIDLRequest);
                    }
                    return fbnsAIDLResult;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
            public final void A8h(FbnsAIDLRequest fbnsAIDLRequest) throws RemoteException {
                IFbnsAIDLService iFbnsAIDLService;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.fbns.ipc.IFbnsAIDLService");
                    if (fbnsAIDLRequest != null) {
                        obtain.writeInt(1);
                        fbnsAIDLRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.A00.transact(2, obtain, null, 1) && (iFbnsAIDLService = A01) != null) {
                        iFbnsAIDLService.A8h(fbnsAIDLRequest);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

        public static IFbnsAIDLService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.push.fbns.ipc.IFbnsAIDLService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFbnsAIDLService)) {
                return new Proxy(iBinder);
            }
            return (IFbnsAIDLService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IFbnsAIDLService iFbnsAIDLService) {
            if (Proxy.A01 != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iFbnsAIDLService == null) {
                return false;
            } else {
                Proxy.A01 = iFbnsAIDLService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, "com.facebook.push.fbns.ipc.IFbnsAIDLService");
        }

        public static IFbnsAIDLService getDefaultImpl() {
            return Proxy.A01;
        }
    }

    FbnsAIDLResult A75(FbnsAIDLRequest fbnsAIDLRequest) throws RemoteException;

    void A8h(FbnsAIDLRequest fbnsAIDLRequest) throws RemoteException;
}
