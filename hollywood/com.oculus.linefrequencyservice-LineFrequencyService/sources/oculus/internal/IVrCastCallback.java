package oculus.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVrCastCallback extends IInterface {
    public static final int ERROR_CLIENT_CONNECTION_FAILED = 102;
    public static final int ERROR_DEVICE_LOST_ERROR = 101;
    public static final int ERROR_DISCOVERY_ERROR = 100;
    public static final int ERROR_GOOGLE_CAST_CONNECTION_FAILED = 202;
    public static final int ERROR_GOOGLE_CAST_START_SESSION_AUTH_FAILED = 201;
    public static final int ERROR_GOOGLE_CAST_START_SESSION_FAILED = 200;
    public static final int ERROR_START_CAST_WFD_RECEIVER_CONNECT_ERROR = 132;
    public static final int ERROR_START_CAST_WFD_WIFI_P2P_ERROR = 131;
    public static final int ERROR_UNKNOWN_ERROR = 1;
    public static final int ERROR_WFD_CANCEL_CONNECT_FAILED = 134;
    public static final int ERROR_WFD_DISCONNECT_FAILED = 135;
    public static final int ERROR_WFD_INIT_SERVICE_FAILED = 133;

    void onDeviceChanged(VrCastDevice[] vrCastDeviceArr) throws RemoteException;

    void onDeviceStateChanged(VrCastDevice vrCastDevice) throws RemoteException;

    void onError(int i, Bundle bundle) throws RemoteException;

    public static class Default implements IVrCastCallback {
        @Override // oculus.internal.IVrCastCallback
        public void onDeviceChanged(VrCastDevice[] list) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastCallback
        public void onDeviceStateChanged(VrCastDevice device) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastCallback
        public void onError(int errorCode, Bundle extras) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrCastCallback {
        private static final String DESCRIPTOR = "oculus.internal.IVrCastCallback";
        static final int TRANSACTION_onDeviceChanged = 1;
        static final int TRANSACTION_onDeviceStateChanged = 2;
        static final int TRANSACTION_onError = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrCastCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrCastCallback)) {
                return new Proxy(obj);
            }
            return (IVrCastCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            VrCastDevice _arg0;
            Bundle _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onDeviceChanged((VrCastDevice[]) data.createTypedArray(VrCastDevice.CREATOR));
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = VrCastDevice.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onDeviceStateChanged(_arg0);
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg02 = data.readInt();
                if (data.readInt() != 0) {
                    _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onError(_arg02, _arg1);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IVrCastCallback {
            public static IVrCastCallback sDefaultImpl;
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

            @Override // oculus.internal.IVrCastCallback
            public void onDeviceChanged(VrCastDevice[] list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedArray(list, 0);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceChanged(list);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastCallback
            public void onDeviceStateChanged(VrCastDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceStateChanged(device);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastCallback
            public void onError(int errorCode, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(3, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onError(errorCode, extras);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrCastCallback impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrCastCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
