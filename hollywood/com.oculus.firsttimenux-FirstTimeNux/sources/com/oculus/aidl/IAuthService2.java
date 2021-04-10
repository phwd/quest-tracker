package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthService2 extends IInterface {
    Bundle getCredentials() throws RemoteException;

    Bundle getDeviceScopedCredentials() throws RemoteException;

    public static class Default implements IAuthService2 {
        @Override // com.oculus.aidl.IAuthService2
        public Bundle getCredentials() throws RemoteException {
            return null;
        }

        @Override // com.oculus.aidl.IAuthService2
        public Bundle getDeviceScopedCredentials() throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAuthService2 {
        private static final String DESCRIPTOR = "com.oculus.aidl.IAuthService2";
        static final int TRANSACTION_getCredentials = 1;
        static final int TRANSACTION_getDeviceScopedCredentials = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAuthService2 asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAuthService2)) {
                return new Proxy(obj);
            }
            return (IAuthService2) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle _result = getCredentials();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle _result2 = getDeviceScopedCredentials();
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IAuthService2 {
            public static IAuthService2 sDefaultImpl;
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

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getCredentials() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _result = Stub.getDefaultImpl().getCredentials();
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getDeviceScopedCredentials() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _result = Stub.getDefaultImpl().getDeviceScopedCredentials();
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAuthService2 impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAuthService2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
