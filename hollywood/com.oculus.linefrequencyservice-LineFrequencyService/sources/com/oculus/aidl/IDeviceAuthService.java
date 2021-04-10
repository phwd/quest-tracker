package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.DeviceAuthToken;

public interface IDeviceAuthService extends IInterface {
    public static final int ERROR_CODE_BACKEND = 3;
    public static final int ERROR_CODE_DEVICE_IDENTITY = 1;
    public static final int ERROR_CODE_NETWORK = 2;

    DeviceAuthToken fetchDeviceAuthToken(String str) throws RemoteException;

    String getDeviceAuthToken(String str) throws RemoteException;

    public static class Default implements IDeviceAuthService {
        @Override // com.oculus.aidl.IDeviceAuthService
        public String getDeviceAuthToken(String applicationClientToken) throws RemoteException {
            return null;
        }

        @Override // com.oculus.aidl.IDeviceAuthService
        public DeviceAuthToken fetchDeviceAuthToken(String applicationClientToken) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDeviceAuthService {
        private static final String DESCRIPTOR = "com.oculus.aidl.IDeviceAuthService";
        static final int TRANSACTION_fetchDeviceAuthToken = 2;
        static final int TRANSACTION_getDeviceAuthToken = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceAuthService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceAuthService)) {
                return new Proxy(obj);
            }
            return (IDeviceAuthService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                String _result = getDeviceAuthToken(data.readString());
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                DeviceAuthToken _result2 = fetchDeviceAuthToken(data.readString());
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IDeviceAuthService {
            public static IDeviceAuthService sDefaultImpl;
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

            @Override // com.oculus.aidl.IDeviceAuthService
            public String getDeviceAuthToken(String applicationClientToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(applicationClientToken);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceAuthToken(applicationClientToken);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IDeviceAuthService
            public DeviceAuthToken fetchDeviceAuthToken(String applicationClientToken) throws RemoteException {
                DeviceAuthToken _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(applicationClientToken);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchDeviceAuthToken(applicationClientToken);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DeviceAuthToken.CREATOR.createFromParcel(_reply);
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

        public static boolean setDefaultImpl(IDeviceAuthService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDeviceAuthService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
