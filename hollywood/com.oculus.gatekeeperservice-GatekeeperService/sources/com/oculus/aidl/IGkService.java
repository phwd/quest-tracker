package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGkService extends IInterface {
    void clearGatekeeperOverride(String str) throws RemoteException;

    boolean deregisterGatekeeper(String str) throws RemoteException;

    boolean fetchGatekeeper(String str) throws RemoteException;

    boolean fetchGatekeepers(long j) throws RemoteException;

    void fetchGatekeepersAsync() throws RemoteException;

    boolean getGatekeeper(String str) throws RemoteException;

    boolean getGatekeeperDef(String str, boolean z) throws RemoteException;

    void overrideGatekeeper(String str, boolean z) throws RemoteException;

    boolean registerGatekeeper(String str) throws RemoteException;

    public static class Default implements IGkService {
        @Override // com.oculus.aidl.IGkService
        public boolean registerGatekeeper(String gatekeeper) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean deregisterGatekeeper(String gatekeeper) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public void overrideGatekeeper(String gatekeeper, boolean override) throws RemoteException {
        }

        @Override // com.oculus.aidl.IGkService
        public void clearGatekeeperOverride(String gatekeeper) throws RemoteException {
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeper(String gatekeeper) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeperDef(String gatekeeper, boolean defValue) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeeper(String gatekeeper) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeepers(long timeout) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public void fetchGatekeepersAsync() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IGkService {
        private static final String DESCRIPTOR = "com.oculus.aidl.IGkService";
        static final int TRANSACTION_clearGatekeeperOverride = 4;
        static final int TRANSACTION_deregisterGatekeeper = 2;
        static final int TRANSACTION_fetchGatekeeper = 7;
        static final int TRANSACTION_fetchGatekeepers = 8;
        static final int TRANSACTION_fetchGatekeepersAsync = 9;
        static final int TRANSACTION_getGatekeeper = 5;
        static final int TRANSACTION_getGatekeeperDef = 6;
        static final int TRANSACTION_overrideGatekeeper = 3;
        static final int TRANSACTION_registerGatekeeper = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGkService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGkService)) {
                return new Proxy(obj);
            }
            return (IGkService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                boolean _arg1 = false;
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        boolean registerGatekeeper = registerGatekeeper(data.readString());
                        reply.writeNoException();
                        reply.writeInt(registerGatekeeper ? 1 : 0);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        boolean deregisterGatekeeper = deregisterGatekeeper(data.readString());
                        reply.writeNoException();
                        reply.writeInt(deregisterGatekeeper ? 1 : 0);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg0 = data.readString();
                        if (data.readInt() != 0) {
                            _arg1 = true;
                        }
                        overrideGatekeeper(_arg0, _arg1);
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        clearGatekeeperOverride(data.readString());
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        boolean gatekeeper = getGatekeeper(data.readString());
                        reply.writeNoException();
                        reply.writeInt(gatekeeper ? 1 : 0);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg02 = data.readString();
                        if (data.readInt() != 0) {
                            _arg1 = true;
                        }
                        boolean gatekeeperDef = getGatekeeperDef(_arg02, _arg1);
                        reply.writeNoException();
                        reply.writeInt(gatekeeperDef ? 1 : 0);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        boolean fetchGatekeeper = fetchGatekeeper(data.readString());
                        reply.writeNoException();
                        reply.writeInt(fetchGatekeeper ? 1 : 0);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        boolean fetchGatekeepers = fetchGatekeepers(data.readLong());
                        reply.writeNoException();
                        reply.writeInt(fetchGatekeepers ? 1 : 0);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        fetchGatekeepersAsync();
                        reply.writeNoException();
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IGkService {
            public static IGkService sDefaultImpl;
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

            @Override // com.oculus.aidl.IGkService
            public boolean registerGatekeeper(String gatekeeper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerGatekeeper(gatekeeper);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean deregisterGatekeeper(String gatekeeper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deregisterGatekeeper(gatekeeper);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public void overrideGatekeeper(String gatekeeper, boolean override) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    _data.writeInt(override ? 1 : 0);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().overrideGatekeeper(gatekeeper, override);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public void clearGatekeeperOverride(String gatekeeper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().clearGatekeeperOverride(gatekeeper);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean getGatekeeper(String gatekeeper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    boolean _result = false;
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatekeeper(gatekeeper);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean getGatekeeperDef(String gatekeeper, boolean defValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    boolean _result = true;
                    _data.writeInt(defValue ? 1 : 0);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatekeeperDef(gatekeeper, defValue);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean fetchGatekeeper(String gatekeeper) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gatekeeper);
                    boolean _result = false;
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchGatekeeper(gatekeeper);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean fetchGatekeepers(long timeout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(timeout);
                    boolean _result = false;
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchGatekeepers(timeout);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public void fetchGatekeepersAsync() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().fetchGatekeepersAsync();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGkService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGkService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
