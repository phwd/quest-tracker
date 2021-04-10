package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.IRemoteWipeCallback;

public interface ICompanionServer extends IInterface {
    void claimDevice(String str) throws RemoteException;

    String getLegacyNuxOtaState() throws RemoteException;

    boolean getLegacyPreOtaComplete() throws RemoteException;

    String getUserId() throws RemoteException;

    void performAntiPiracyKillSwitchAction() throws RemoteException;

    void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException;

    public static class Default implements ICompanionServer {
        @Override // com.oculus.os.ICompanionServer
        public String getUserId() throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.ICompanionServer
        public void performRemoteWipe(IRemoteWipeCallback callback) throws RemoteException {
        }

        @Override // com.oculus.os.ICompanionServer
        public void claimDevice(String userSecret) throws RemoteException {
        }

        @Override // com.oculus.os.ICompanionServer
        public void performAntiPiracyKillSwitchAction() throws RemoteException {
        }

        @Override // com.oculus.os.ICompanionServer
        public boolean getLegacyPreOtaComplete() throws RemoteException {
            return false;
        }

        @Override // com.oculus.os.ICompanionServer
        public String getLegacyNuxOtaState() throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICompanionServer {
        private static final String DESCRIPTOR = "com.oculus.os.ICompanionServer";
        static final int TRANSACTION_claimDevice = 3;
        static final int TRANSACTION_getLegacyNuxOtaState = 6;
        static final int TRANSACTION_getLegacyPreOtaComplete = 5;
        static final int TRANSACTION_getUserId = 1;
        static final int TRANSACTION_performAntiPiracyKillSwitchAction = 4;
        static final int TRANSACTION_performRemoteWipe = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICompanionServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICompanionServer)) {
                return new Proxy(obj);
            }
            return (ICompanionServer) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        String _result = getUserId();
                        reply.writeNoException();
                        reply.writeString(_result);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        performRemoteWipe(IRemoteWipeCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        claimDevice(data.readString());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        performAntiPiracyKillSwitchAction();
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        boolean legacyPreOtaComplete = getLegacyPreOtaComplete();
                        reply.writeNoException();
                        reply.writeInt(legacyPreOtaComplete ? 1 : 0);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        String _result2 = getLegacyNuxOtaState();
                        reply.writeNoException();
                        reply.writeString(_result2);
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
        public static class Proxy implements ICompanionServer {
            public static ICompanionServer sDefaultImpl;
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

            @Override // com.oculus.os.ICompanionServer
            public String getUserId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserId();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public void performRemoteWipe(IRemoteWipeCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().performRemoteWipe(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public void claimDevice(String userSecret) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(userSecret);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().claimDevice(userSecret);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public void performAntiPiracyKillSwitchAction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().performAntiPiracyKillSwitchAction();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public boolean getLegacyPreOtaComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyPreOtaComplete();
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

            @Override // com.oculus.os.ICompanionServer
            public String getLegacyNuxOtaState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyNuxOtaState();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICompanionServer impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICompanionServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
