package com.oculus.vrruntimeservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.vrruntimeservice.IRuntimeClient;

public interface IVrRuntimeService extends IInterface {
    public static final int CLIENT_FLAG_OVERLAY_ALWAYS_UPDATE_INPUT = 1;
    public static final int CLIENT_TYPE_APPLICATION = 0;
    public static final int CLIENT_TYPE_GUARDIAN = 2;
    public static final int CLIENT_TYPE_MRSERVICE = 3;
    public static final int CLIENT_TYPE_OVERLAY = 1;

    IRuntimeClient createClient(IBinder iBinder, int i, int i2, int i3, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrRuntimeService {
        private static final String DESCRIPTOR = "com.oculus.vrruntimeservice.IVrRuntimeService";
        static final int TRANSACTION_createClient = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrRuntimeService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrRuntimeService)) {
                return new Proxy(obj);
            }
            return (IVrRuntimeService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IRuntimeClient _result = createClient(data.readStrongBinder(), data.readInt(), data.readInt(), data.readInt(), data.readString());
                reply.writeNoException();
                reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IVrRuntimeService {
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

            @Override // com.oculus.vrruntimeservice.IVrRuntimeService
            public IRuntimeClient createClient(IBinder token, int clientType, int clientFlags, int clientSessionPlacement, String processName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(clientType);
                    _data.writeInt(clientFlags);
                    _data.writeInt(clientSessionPlacement);
                    _data.writeString(processName);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return IRuntimeClient.Stub.asInterface(_reply.readStrongBinder());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
