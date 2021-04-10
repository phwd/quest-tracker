package com.oculus.vrguardianservice;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVrGuardianUserController extends IInterface {
    boolean createSubdirStructure() throws RemoteException;

    String loadUserFile(String str) throws RemoteException;

    boolean saveUserFile(String str, String str2) throws RemoteException;

    void sendUserBroadcast(Intent intent) throws RemoteException;

    void startUserActivity(Intent intent) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrGuardianUserController {
        private static final String DESCRIPTOR = "com.oculus.vrguardianservice.IVrGuardianUserController";
        static final int TRANSACTION_createSubdirStructure = 1;
        static final int TRANSACTION_loadUserFile = 3;
        static final int TRANSACTION_saveUserFile = 2;
        static final int TRANSACTION_sendUserBroadcast = 4;
        static final int TRANSACTION_startUserActivity = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrGuardianUserController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrGuardianUserController)) {
                return new Proxy(obj);
            }
            return (IVrGuardianUserController) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Intent _arg0;
            Intent _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                boolean createSubdirStructure = createSubdirStructure();
                reply.writeNoException();
                reply.writeInt(createSubdirStructure ? 1 : 0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean saveUserFile = saveUserFile(data.readString(), data.readString());
                reply.writeNoException();
                reply.writeInt(saveUserFile ? 1 : 0);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String _result = loadUserFile(data.readString());
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (Intent) Intent.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                sendUserBroadcast(_arg0);
                reply.writeNoException();
                return true;
            } else if (code == 5) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = (Intent) Intent.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                startUserActivity(_arg02);
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IVrGuardianUserController {
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

            @Override // com.oculus.vrguardianservice.IVrGuardianUserController
            public boolean createSubdirStructure() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrguardianservice.IVrGuardianUserController
            public boolean saveUserFile(String fileName, String content) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fileName);
                    _data.writeString(content);
                    boolean _result = false;
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrguardianservice.IVrGuardianUserController
            public String loadUserFile(String fileName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fileName);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrguardianservice.IVrGuardianUserController
            public void sendUserBroadcast(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.vrguardianservice.IVrGuardianUserController
            public void startUserActivity(Intent intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
