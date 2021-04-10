package com.oculus.runtimeipcservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.Surface;

public interface IRuntimeIPCServiceClient extends IInterface {
    void destroy() throws RemoteException;

    ParcelFileDescriptor getFileDescriptor(byte[] bArr) throws RemoteException;

    Surface getSurface(byte[] bArr) throws RemoteException;

    boolean resetClient() throws RemoteException;

    int setFileDescriptor(byte[] bArr, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    int setSurface(byte[] bArr, Surface surface) throws RemoteException;

    public static abstract class Stub extends Binder implements IRuntimeIPCServiceClient {
        private static final String DESCRIPTOR = "com.oculus.runtimeipcservice.IRuntimeIPCServiceClient";
        static final int TRANSACTION_destroy = 1;
        static final int TRANSACTION_getFileDescriptor = 2;
        static final int TRANSACTION_getSurface = 5;
        static final int TRANSACTION_resetClient = 6;
        static final int TRANSACTION_setFileDescriptor = 3;
        static final int TRANSACTION_setSurface = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRuntimeIPCServiceClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRuntimeIPCServiceClient)) {
                return new Proxy(obj);
            }
            return (IRuntimeIPCServiceClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg1;
            Surface _arg12;
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        destroy();
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result = getFileDescriptor(data.createByteArray());
                        reply.writeNoException();
                        if (_result != null) {
                            reply.writeInt(1);
                            _result.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        byte[] _arg0 = data.createByteArray();
                        if (data.readInt() != 0) {
                            _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                        } else {
                            _arg1 = null;
                        }
                        int _result2 = setFileDescriptor(_arg0, _arg1);
                        reply.writeNoException();
                        reply.writeInt(_result2);
                        return true;
                    case TRANSACTION_setSurface /*{ENCODED_INT: 4}*/:
                        data.enforceInterface(DESCRIPTOR);
                        byte[] _arg02 = data.createByteArray();
                        if (data.readInt() != 0) {
                            _arg12 = (Surface) Surface.CREATOR.createFromParcel(data);
                        } else {
                            _arg12 = null;
                        }
                        int _result3 = setSurface(_arg02, _arg12);
                        reply.writeNoException();
                        reply.writeInt(_result3);
                        return true;
                    case TRANSACTION_getSurface /*{ENCODED_INT: 5}*/:
                        data.enforceInterface(DESCRIPTOR);
                        Surface _result4 = getSurface(data.createByteArray());
                        reply.writeNoException();
                        if (_result4 != null) {
                            reply.writeInt(1);
                            _result4.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case TRANSACTION_resetClient /*{ENCODED_INT: 6}*/:
                        data.enforceInterface(DESCRIPTOR);
                        boolean resetClient = resetClient();
                        reply.writeNoException();
                        reply.writeInt(resetClient ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IRuntimeIPCServiceClient {
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

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public void destroy() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public ParcelFileDescriptor getFileDescriptor(byte[] payload) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public int setFileDescriptor(byte[] payload, ParcelFileDescriptor pfd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    if (pfd != null) {
                        _data.writeInt(1);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public int setSurface(byte[] payload, Surface surf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    if (surf != null) {
                        _data.writeInt(1);
                        surf.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_setSurface, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public Surface getSurface(byte[] payload) throws RemoteException {
                Surface _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    this.mRemote.transact(Stub.TRANSACTION_getSurface, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Surface) Surface.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
            public boolean resetClient() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(Stub.TRANSACTION_resetClient, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
