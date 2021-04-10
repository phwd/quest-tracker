package com.oculus.vrruntimeservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRuntimeClient extends IInterface {
    void destroy() throws RemoteException;

    boolean enableRendering(IBinder iBinder, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IRuntimeClient {
        private static final String DESCRIPTOR = "com.oculus.vrruntimeservice.IRuntimeClient";
        static final int TRANSACTION_destroy = 1;
        static final int TRANSACTION_enableRendering = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRuntimeClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRuntimeClient)) {
                return new Proxy(obj);
            }
            return (IRuntimeClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                destroy();
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean enableRendering = enableRendering(data.readStrongBinder(), data.readInt() != 0);
                reply.writeNoException();
                reply.writeInt(enableRendering ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IRuntimeClient {
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

            @Override // com.oculus.vrruntimeservice.IRuntimeClient
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

            @Override // com.oculus.vrruntimeservice.IRuntimeClient
            public boolean enableRendering(IBinder clientWindow, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientWindow);
                    boolean _result = true;
                    _data.writeInt(enable ? 1 : 0);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
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
