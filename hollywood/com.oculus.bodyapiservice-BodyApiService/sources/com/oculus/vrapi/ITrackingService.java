package com.oculus.vrapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.oculus.vrapi.ITrackingServiceClient;

public interface ITrackingService extends IInterface {
    ParcelFileDescriptor getSharedMemoryFileDescriptor(ITrackingServiceClient iTrackingServiceClient) throws RemoteException;

    ParcelFileDescriptor getTrackingSocketFileDescriptor() throws RemoteException;

    void registerClient(ITrackingServiceClient iTrackingServiceClient) throws RemoteException;

    void sendSignal(int i) throws RemoteException;

    void unregisterClient(ITrackingServiceClient iTrackingServiceClient) throws RemoteException;

    public static abstract class Stub extends Binder implements ITrackingService {
        private static final String DESCRIPTOR = "com.oculus.vrapi.ITrackingService";
        static final int TRANSACTION_getSharedMemoryFileDescriptor = 1;
        static final int TRANSACTION_getTrackingSocketFileDescriptor = 2;
        static final int TRANSACTION_registerClient = 4;
        static final int TRANSACTION_sendSignal = 3;
        static final int TRANSACTION_unregisterClient = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrackingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrackingService)) {
                return new Proxy(obj);
            }
            return (ITrackingService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor _result = getSharedMemoryFileDescriptor(ITrackingServiceClient.Stub.asInterface(data.readStrongBinder()));
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
                ParcelFileDescriptor _result2 = getTrackingSocketFileDescriptor();
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == TRANSACTION_sendSignal) {
                data.enforceInterface(DESCRIPTOR);
                sendSignal(data.readInt());
                return true;
            } else if (code == TRANSACTION_registerClient) {
                data.enforceInterface(DESCRIPTOR);
                registerClient(ITrackingServiceClient.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == TRANSACTION_unregisterClient) {
                data.enforceInterface(DESCRIPTOR);
                unregisterClient(ITrackingServiceClient.Stub.asInterface(data.readStrongBinder()));
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
        public static class Proxy implements ITrackingService {
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

            @Override // com.oculus.vrapi.ITrackingService
            public ParcelFileDescriptor getSharedMemoryFileDescriptor(ITrackingServiceClient cb) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
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

            @Override // com.oculus.vrapi.ITrackingService
            public ParcelFileDescriptor getTrackingSocketFileDescriptor() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
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

            @Override // com.oculus.vrapi.ITrackingService
            public void sendSignal(int signal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(signal);
                    this.mRemote.transact(Stub.TRANSACTION_sendSignal, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrapi.ITrackingService
            public void registerClient(ITrackingServiceClient cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_registerClient, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrapi.ITrackingService
            public void unregisterClient(ITrackingServiceClient cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_unregisterClient, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
