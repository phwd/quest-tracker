package oculus.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVrCastCallback extends IInterface {
    void onDeviceChanged(VrCastDevice[] vrCastDeviceArr) throws RemoteException;

    void onDeviceStateChanged(VrCastDevice vrCastDevice) throws RemoteException;

    void onError(int i, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrCastCallback {
        private static final String DESCRIPTOR = "oculus.internal.IVrCastCallback";
        static final int TRANSACTION_onDeviceChanged = 1;
        static final int TRANSACTION_onDeviceStateChanged = 2;
        static final int TRANSACTION_onError = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrCastCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IVrCastCallback)) {
                return new Proxy(iBinder);
            }
            return (IVrCastCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                VrCastDevice vrCastDevice = null;
                Bundle bundle = null;
                if (i == 2) {
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        vrCastDevice = VrCastDevice.CREATOR.createFromParcel(parcel);
                    }
                    onDeviceStateChanged(vrCastDevice);
                    parcel2.writeNoException();
                    return true;
                } else if (i == 3) {
                    parcel.enforceInterface(DESCRIPTOR);
                    int readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    onError(readInt, bundle);
                    return true;
                } else if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                onDeviceChanged((VrCastDevice[]) parcel.createTypedArray(VrCastDevice.CREATOR));
                parcel2.writeNoException();
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IVrCastCallback {
            public static IVrCastCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // oculus.internal.IVrCastCallback
            public void onDeviceChanged(VrCastDevice[] vrCastDeviceArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(vrCastDeviceArr, 0);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceChanged(vrCastDeviceArr);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // oculus.internal.IVrCastCallback
            public void onDeviceStateChanged(VrCastDevice vrCastDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vrCastDevice != null) {
                        obtain.writeInt(1);
                        vrCastDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceStateChanged(vrCastDevice);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // oculus.internal.IVrCastCallback
            public void onError(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onError(i, bundle);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrCastCallback iVrCastCallback) {
            if (Proxy.sDefaultImpl != null || iVrCastCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iVrCastCallback;
            return true;
        }

        public static IVrCastCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
