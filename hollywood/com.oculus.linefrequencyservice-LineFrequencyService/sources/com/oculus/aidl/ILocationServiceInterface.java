package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.aidl.ILocationCallback;

public interface ILocationServiceInterface extends IInterface {
    void getLocation(ILocationCallback iLocationCallback) throws RemoteException;

    void getLocationByType(ILocationCallback iLocationCallback, int i) throws RemoteException;

    void getLocationUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException;

    void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException;

    public static class Default implements ILocationServiceInterface {
        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocation(ILocationCallback callback) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationUsingIPOnly(ILocationCallback callback) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationWithTimezoneUsingIPOnly(ILocationCallback callback) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationByType(ILocationCallback callback, int requestType) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILocationServiceInterface {
        private static final String DESCRIPTOR = "com.oculus.aidl.ILocationServiceInterface";
        static final int TRANSACTION_getLocation = 1;
        static final int TRANSACTION_getLocationByType = 4;
        static final int TRANSACTION_getLocationUsingIPOnly = 2;
        static final int TRANSACTION_getLocationWithTimezoneUsingIPOnly = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationServiceInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILocationServiceInterface)) {
                return new Proxy(obj);
            }
            return (ILocationServiceInterface) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                getLocation(ILocationCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                getLocationUsingIPOnly(ILocationCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                getLocationWithTimezoneUsingIPOnly(ILocationCallback.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                getLocationByType(ILocationCallback.Stub.asInterface(data.readStrongBinder()), data.readInt());
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
        public static class Proxy implements ILocationServiceInterface {
            public static ILocationServiceInterface sDefaultImpl;
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

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocation(ILocationCallback callback) throws RemoteException {
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
                    Stub.getDefaultImpl().getLocation(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationUsingIPOnly(ILocationCallback callback) throws RemoteException {
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
                    Stub.getDefaultImpl().getLocationUsingIPOnly(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationWithTimezoneUsingIPOnly(ILocationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationWithTimezoneUsingIPOnly(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationByType(ILocationCallback callback, int requestType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(requestType);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationByType(callback, requestType);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationServiceInterface impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILocationServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
