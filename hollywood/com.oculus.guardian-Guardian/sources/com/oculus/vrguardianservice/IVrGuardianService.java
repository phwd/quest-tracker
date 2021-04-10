package com.oculus.vrguardianservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.vrguardianservice.IVrGuardianUserController;

public interface IVrGuardianService extends IInterface {
    void connectUserController(IVrGuardianUserController iVrGuardianUserController) throws RemoteException;

    void processJsonCmd(String str) throws RemoteException;

    boolean setCurrentUser(int i, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrGuardianService {
        private static final String DESCRIPTOR = "com.oculus.vrguardianservice.IVrGuardianService";
        static final int TRANSACTION_connectUserController = 1;
        static final int TRANSACTION_processJsonCmd = 3;
        static final int TRANSACTION_setCurrentUser = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrGuardianService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrGuardianService)) {
                return new Proxy(obj);
            }
            return (IVrGuardianService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                connectUserController(IVrGuardianUserController.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean currentUser = setCurrentUser(data.readInt(), data.readInt() != 0);
                reply.writeNoException();
                reply.writeInt(currentUser ? 1 : 0);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                processJsonCmd(data.readString());
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IVrGuardianService {
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

            @Override // com.oculus.vrguardianservice.IVrGuardianService
            public void connectUserController(IVrGuardianUserController guardianUserController) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(guardianUserController != null ? guardianUserController.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrguardianservice.IVrGuardianService
            public boolean setCurrentUser(int userId, boolean active) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = true;
                    _data.writeInt(active ? 1 : 0);
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

            @Override // com.oculus.vrguardianservice.IVrGuardianService
            public void processJsonCmd(String cmdString) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cmdString);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
