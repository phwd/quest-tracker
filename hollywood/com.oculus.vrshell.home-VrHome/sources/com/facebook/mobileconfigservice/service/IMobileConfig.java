package com.facebook.mobileconfigservice.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMobileConfig extends IInterface {
    void subscribe(String str) throws RemoteException;

    void subscribeWithProcessName(String str, String str2, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMobileConfig {
        public static IMobileConfig asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
            if (iin == null || !(iin instanceof IMobileConfig)) {
                return new Proxy(obj);
            }
            return (IMobileConfig) iin;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                    subscribe(data.readString());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.facebook.mobileconfigservice.service.IMobileConfig");
                    subscribeWithProcessName(data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.facebook.mobileconfigservice.service.IMobileConfig");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
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

            @Override // com.facebook.mobileconfigservice.service.IMobileConfig
            public void subscribe(String paramsMapContent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
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
                    _data.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
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

        public static IMobileConfig getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
