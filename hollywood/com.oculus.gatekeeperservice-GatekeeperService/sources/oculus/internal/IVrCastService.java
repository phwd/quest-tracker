package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IVrCastCallback;

public interface IVrCastService extends IInterface {
    VrCastDevice getCurrentDevice() throws RemoteException;

    void registerCallback(IVrCastCallback iVrCastCallback) throws RemoteException;

    void startCast(String str, String str2, boolean z) throws RemoteException;

    void startDiscovery(String str) throws RemoteException;

    void stopCast(String str, String str2) throws RemoteException;

    void stopCastWithError(String str, String str2, String str3) throws RemoteException;

    void stopDiscovery(String str) throws RemoteException;

    void unregisterCallback(IVrCastCallback iVrCastCallback) throws RemoteException;

    public static class Default implements IVrCastService {
        @Override // oculus.internal.IVrCastService
        public void registerCallback(IVrCastCallback callback) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void unregisterCallback(IVrCastCallback callback) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void startDiscovery(String callerContext) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void stopDiscovery(String callerContext) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void startCast(String deviceId, String callerContext, boolean isDeviceAudioDest) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void stopCast(String deviceId, String callerContext) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public void stopCastWithError(String deviceId, String callerContext, String errorMessage) throws RemoteException {
        }

        @Override // oculus.internal.IVrCastService
        public VrCastDevice getCurrentDevice() throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrCastService {
        private static final String DESCRIPTOR = "oculus.internal.IVrCastService";
        static final int TRANSACTION_getCurrentDevice = 8;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_startCast = 5;
        static final int TRANSACTION_startDiscovery = 3;
        static final int TRANSACTION_stopCast = 6;
        static final int TRANSACTION_stopCastWithError = 7;
        static final int TRANSACTION_stopDiscovery = 4;
        static final int TRANSACTION_unregisterCallback = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrCastService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrCastService)) {
                return new Proxy(obj);
            }
            return (IVrCastService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                boolean _arg2 = false;
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        registerCallback(IVrCastCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterCallback(IVrCastCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        startDiscovery(data.readString());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        stopDiscovery(data.readString());
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg0 = data.readString();
                        String _arg1 = data.readString();
                        if (data.readInt() != 0) {
                            _arg2 = true;
                        }
                        startCast(_arg0, _arg1, _arg2);
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        stopCast(data.readString(), data.readString());
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        stopCastWithError(data.readString(), data.readString(), data.readString());
                        reply.writeNoException();
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        VrCastDevice _result = getCurrentDevice();
                        reply.writeNoException();
                        if (_result != null) {
                            reply.writeInt(1);
                            _result.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IVrCastService {
            public static IVrCastService sDefaultImpl;
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

            @Override // oculus.internal.IVrCastService
            public void registerCallback(IVrCastCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void unregisterCallback(IVrCastCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void startDiscovery(String callerContext) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callerContext);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startDiscovery(callerContext);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void stopDiscovery(String callerContext) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callerContext);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopDiscovery(callerContext);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void startCast(String deviceId, String callerContext, boolean isDeviceAudioDest) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeString(callerContext);
                    _data.writeInt(isDeviceAudioDest ? 1 : 0);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startCast(deviceId, callerContext, isDeviceAudioDest);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void stopCast(String deviceId, String callerContext) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeString(callerContext);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopCast(deviceId, callerContext);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public void stopCastWithError(String deviceId, String callerContext, String errorMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeString(callerContext);
                    _data.writeString(errorMessage);
                    if (this.mRemote.transact(7, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopCastWithError(deviceId, callerContext, errorMessage);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrCastService
            public VrCastDevice getCurrentDevice() throws RemoteException {
                VrCastDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentDevice();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VrCastDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrCastService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrCastService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
