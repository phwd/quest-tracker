package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationProxy extends IInterface {
    public static final int FIRMWARE_UPDATE_FAILED = 2;
    public static final int FIRMWARE_UPDATE_IN_PROGRESS = 0;
    public static final int FIRMWARE_UPDATE_SUCCEEDED = 1;

    void showControllerFirmwareUpdateProgress(int i, int i2) throws RemoteException;

    void showControllerFirmwareUpdateProgressWithDeviceType(int i, int i2, int i3) throws RemoteException;

    void showDebugToast(String str, String str2) throws RemoteException;

    void showVrUiGazeTutorialNotification() throws RemoteException;

    public static class Default implements INotificationProxy {
        @Override // oculus.internal.INotificationProxy
        public void showControllerFirmwareUpdateProgress(int status, int percentComplete) throws RemoteException {
        }

        @Override // oculus.internal.INotificationProxy
        public void showControllerFirmwareUpdateProgressWithDeviceType(int deviceType, int status, int percentComplete) throws RemoteException {
        }

        @Override // oculus.internal.INotificationProxy
        public void showVrUiGazeTutorialNotification() throws RemoteException {
        }

        @Override // oculus.internal.INotificationProxy
        public void showDebugToast(String title, String msg) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements INotificationProxy {
        private static final String DESCRIPTOR = "oculus.internal.INotificationProxy";
        static final int TRANSACTION_showControllerFirmwareUpdateProgress = 1;
        static final int TRANSACTION_showControllerFirmwareUpdateProgressWithDeviceType = 2;
        static final int TRANSACTION_showDebugToast = 4;
        static final int TRANSACTION_showVrUiGazeTutorialNotification = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationProxy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INotificationProxy)) {
                return new Proxy(obj);
            }
            return (INotificationProxy) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                showControllerFirmwareUpdateProgress(data.readInt(), data.readInt());
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                showControllerFirmwareUpdateProgressWithDeviceType(data.readInt(), data.readInt(), data.readInt());
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                showVrUiGazeTutorialNotification();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                showDebugToast(data.readString(), data.readString());
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements INotificationProxy {
            public static INotificationProxy sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // oculus.internal.INotificationProxy
            public void showControllerFirmwareUpdateProgress(int status, int percentComplete) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeInt(percentComplete);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().showControllerFirmwareUpdateProgress(status, percentComplete);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.INotificationProxy
            public void showControllerFirmwareUpdateProgressWithDeviceType(int deviceType, int status, int percentComplete) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    _data.writeInt(status);
                    _data.writeInt(percentComplete);
                    if (this.mRemote.transact(2, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().showControllerFirmwareUpdateProgressWithDeviceType(deviceType, status, percentComplete);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.INotificationProxy
            public void showVrUiGazeTutorialNotification() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().showVrUiGazeTutorialNotification();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.INotificationProxy
            public void showDebugToast(String title, String msg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(title);
                    _data.writeString(msg);
                    if (this.mRemote.transact(4, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().showDebugToast(title, msg);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationProxy impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INotificationProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
