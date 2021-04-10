package com.oculus.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.aidl.IAuthenticationCallback;

public interface IAuthService2 extends IInterface {
    void fetchCredentials(String str, long j, boolean z, IAuthenticationCallback iAuthenticationCallback) throws RemoteException;

    void fetchDeviceScopedCredentials(String str, long j, boolean z, IAuthenticationCallback iAuthenticationCallback) throws RemoteException;

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

        @Override // com.oculus.aidl.IAuthService2
        public void fetchCredentials(String callingPackage, long appId, boolean forceRefresh, IAuthenticationCallback callback) throws RemoteException {
        }

        @Override // com.oculus.aidl.IAuthService2
        public void fetchDeviceScopedCredentials(String callingPackage, long appId, boolean forceRefresh, IAuthenticationCallback callback) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAuthService2 {
        private static final String DESCRIPTOR = "com.oculus.aidl.IAuthService2";
        static final int TRANSACTION_fetchCredentials = 3;
        static final int TRANSACTION_fetchDeviceScopedCredentials = 4;
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
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                Bundle _result = getCredentials();
                reply.writeNoException();
                if (_result != null) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                Bundle _result2 = getDeviceScopedCredentials();
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                fetchCredentials(data.readString(), data.readLong(), data.readInt() != 0, IAuthenticationCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                fetchDeviceScopedCredentials(data.readString(), data.readLong(), data.readInt() != 0, IAuthenticationCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
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
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentials();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            @Override // com.oculus.aidl.IAuthService2
            public Bundle getDeviceScopedCredentials() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceScopedCredentials();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            @Override // com.oculus.aidl.IAuthService2
            public void fetchCredentials(String callingPackage, long appId, boolean forceRefresh, IAuthenticationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeLong(appId);
                    _data.writeInt(forceRefresh ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().fetchCredentials(callingPackage, appId, forceRefresh, callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IAuthService2
            public void fetchDeviceScopedCredentials(String callingPackage, long appId, boolean forceRefresh, IAuthenticationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeLong(appId);
                    _data.writeInt(forceRefresh ? 1 : 0);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().fetchDeviceScopedCredentials(callingPackage, appId, forceRefresh, callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAuthService2 impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAuthService2 getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
