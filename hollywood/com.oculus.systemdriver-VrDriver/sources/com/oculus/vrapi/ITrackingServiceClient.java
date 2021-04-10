package com.oculus.vrapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITrackingServiceClient extends IInterface {
    public static final int AUDIT_SECURE_TRACKING_MODE_FLAG = 2;
    public static final int SECURE_TRACKING_MODE_FLAG = 1;

    int getTrackingModeFlags() throws RemoteException;

    void onButtonDown(long j, int i) throws RemoteException;

    void onRemoteConnected(long j) throws RemoteException;

    void onRemoteDisconnected(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackingServiceClient {
        private static final String DESCRIPTOR = "com.oculus.vrapi.ITrackingServiceClient";
        static final int TRANSACTION_getTrackingModeFlags = 4;
        static final int TRANSACTION_onButtonDown = 3;
        static final int TRANSACTION_onRemoteConnected = 1;
        static final int TRANSACTION_onRemoteDisconnected = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrackingServiceClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrackingServiceClient)) {
                return new Proxy(obj);
            }
            return (ITrackingServiceClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onRemoteConnected(data.readLong());
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onRemoteDisconnected(data.readLong());
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                onButtonDown(data.readLong(), data.readInt());
                return true;
            } else if (code == TRANSACTION_getTrackingModeFlags) {
                data.enforceInterface(DESCRIPTOR);
                int _result = getTrackingModeFlags();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ITrackingServiceClient {
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

            @Override // com.oculus.vrapi.ITrackingServiceClient
            public void onRemoteConnected(long deviceIdentifier) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(deviceIdentifier);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrapi.ITrackingServiceClient
            public void onRemoteDisconnected(long deviceIdentifier) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(deviceIdentifier);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrapi.ITrackingServiceClient
            public void onButtonDown(long deviceIdentifier, int buttondata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(deviceIdentifier);
                    _data.writeInt(buttondata);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrapi.ITrackingServiceClient
            public int getTrackingModeFlags() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getTrackingModeFlags, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
