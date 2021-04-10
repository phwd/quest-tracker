package com.oculus.runtimeipcservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.runtimeipcservice.IRuntimeIPCServiceClient;

public interface IRuntimeIPCService extends IInterface {
    IRuntimeIPCServiceClient createClient(IBinder iBinder, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IRuntimeIPCService {
        private static final String DESCRIPTOR = "com.oculus.runtimeipcservice.IRuntimeIPCService";
        static final int TRANSACTION_createClient = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRuntimeIPCService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRuntimeIPCService)) {
                return new Proxy(obj);
            }
            return (IRuntimeIPCService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IRuntimeIPCServiceClient _result = createClient(data.readStrongBinder(), data.readString());
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

        private static class Proxy implements IRuntimeIPCService {
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

            @Override // com.oculus.runtimeipcservice.IRuntimeIPCService
            public IRuntimeIPCServiceClient createClient(IBinder token, String processName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(processName);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return IRuntimeIPCServiceClient.Stub.asInterface(_reply.readStrongBinder());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
