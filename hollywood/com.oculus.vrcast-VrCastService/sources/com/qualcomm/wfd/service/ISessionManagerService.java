package com.qualcomm.wfd.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.qualcomm.wfd.WfdDevice;
import com.qualcomm.wfd.WfdStatus;

public interface ISessionManagerService extends IInterface {
    int deinit() throws RemoteException;

    WfdStatus getStatus() throws RemoteException;

    int init(IWfdActionListener iWfdActionListener, WfdDevice wfdDevice) throws RemoteException;

    int setAvPlaybackMode(int i) throws RemoteException;

    int setDeviceType(int i) throws RemoteException;

    int startWfdSession(WfdDevice wfdDevice) throws RemoteException;

    int teardown() throws RemoteException;

    public static abstract class Stub extends Binder implements ISessionManagerService {
        public static ISessionManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.qualcomm.wfd.service.ISessionManagerService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISessionManagerService)) {
                return new Proxy(iBinder);
            }
            return (ISessionManagerService) queryLocalInterface;
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ISessionManagerService {
            public static ISessionManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int setDeviceType(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDeviceType(i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public WfdStatus getStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatus();
                    }
                    obtain2.readException();
                    WfdStatus createFromParcel = obtain2.readInt() != 0 ? WfdStatus.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int init(IWfdActionListener iWfdActionListener, WfdDevice wfdDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    obtain.writeStrongBinder(iWfdActionListener != null ? iWfdActionListener.asBinder() : null);
                    if (wfdDevice != null) {
                        obtain.writeInt(1);
                        wfdDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().init(iWfdActionListener, wfdDevice);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int deinit() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deinit();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int startWfdSession(WfdDevice wfdDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    if (wfdDevice != null) {
                        obtain.writeInt(1);
                        wfdDevice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startWfdSession(wfdDevice);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int setAvPlaybackMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAvPlaybackMode(i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.qualcomm.wfd.service.ISessionManagerService
            public int teardown() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.qualcomm.wfd.service.ISessionManagerService");
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().teardown();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISessionManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
