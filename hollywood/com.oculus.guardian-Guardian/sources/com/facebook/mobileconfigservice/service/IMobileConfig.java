package com.facebook.mobileconfigservice.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMobileConfig extends IInterface {
    void subscribe(String str) throws RemoteException;

    void subscribeWithProcessName(String str, String str2, int i) throws RemoteException;

    public static class Default implements IMobileConfig {
        @Override // com.facebook.mobileconfigservice.service.IMobileConfig
        public void subscribe(String paramsMapContent) throws RemoteException {
        }

        @Override // com.facebook.mobileconfigservice.service.IMobileConfig
        public void subscribeWithProcessName(String paramsMapContent, String processName, int pid) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMobileConfig {
        private static final String DESCRIPTOR = "com.facebook.mobileconfigservice.service.IMobileConfig";
        static final int TRANSACTION_subscribe = 1;
        static final int TRANSACTION_subscribeWithProcessName = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileConfig asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMobileConfig)) {
                return new Proxy(obj);
            }
            return (IMobileConfig) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                subscribe(data.readString());
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                subscribeWithProcessName(data.readString(), data.readString(), data.readInt());
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
        public static class Proxy implements IMobileConfig {
            public static IMobileConfig sDefaultImpl;
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

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public void subscribe(String paramsMapContent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(paramsMapContent);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().subscribe(paramsMapContent);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public void subscribeWithProcessName(String paramsMapContent, String processName, int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(paramsMapContent);
                    _data.writeString(processName);
                    _data.writeInt(pid);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().subscribeWithProcessName(paramsMapContent, processName, pid);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMobileConfig impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMobileConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
