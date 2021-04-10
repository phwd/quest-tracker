package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.ISystemKeyEventHandler;
import oculus.internal.IWindowLayoutObserver;

public interface IOculusWindowManager extends IInterface {
    boolean registerSystemKeyEventHandler(ISystemKeyEventHandler iSystemKeyEventHandler) throws RemoteException;

    boolean registerWindowLayoutObserver(IWindowLayoutObserver iWindowLayoutObserver) throws RemoteException;

    boolean unregisterSystemKeyEventHandler(ISystemKeyEventHandler iSystemKeyEventHandler) throws RemoteException;

    void unregisterWindowLayoutObserver(IWindowLayoutObserver iWindowLayoutObserver) throws RemoteException;

    public static class Default implements IOculusWindowManager {
        @Override // oculus.internal.IOculusWindowManager
        public boolean registerWindowLayoutObserver(IWindowLayoutObserver observer) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IOculusWindowManager
        public void unregisterWindowLayoutObserver(IWindowLayoutObserver observer) throws RemoteException {
        }

        @Override // oculus.internal.IOculusWindowManager
        public boolean registerSystemKeyEventHandler(ISystemKeyEventHandler handler) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IOculusWindowManager
        public boolean unregisterSystemKeyEventHandler(ISystemKeyEventHandler handler) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOculusWindowManager {
        private static final String DESCRIPTOR = "oculus.internal.IOculusWindowManager";
        static final int TRANSACTION_registerSystemKeyEventHandler = 3;
        static final int TRANSACTION_registerWindowLayoutObserver = 1;
        static final int TRANSACTION_unregisterSystemKeyEventHandler = 4;
        static final int TRANSACTION_unregisterWindowLayoutObserver = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusWindowManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IOculusWindowManager)) {
                return new Proxy(obj);
            }
            return (IOculusWindowManager) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                boolean registerWindowLayoutObserver = registerWindowLayoutObserver(IWindowLayoutObserver.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                reply.writeInt(registerWindowLayoutObserver ? 1 : 0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                unregisterWindowLayoutObserver(IWindowLayoutObserver.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                boolean registerSystemKeyEventHandler = registerSystemKeyEventHandler(ISystemKeyEventHandler.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                reply.writeInt(registerSystemKeyEventHandler ? 1 : 0);
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                boolean unregisterSystemKeyEventHandler = unregisterSystemKeyEventHandler(ISystemKeyEventHandler.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                reply.writeInt(unregisterSystemKeyEventHandler ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IOculusWindowManager {
            public static IOculusWindowManager sDefaultImpl;
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

            @Override // oculus.internal.IOculusWindowManager
            public boolean registerWindowLayoutObserver(IWindowLayoutObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerWindowLayoutObserver(observer);
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

            @Override // oculus.internal.IOculusWindowManager
            public void unregisterWindowLayoutObserver(IWindowLayoutObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterWindowLayoutObserver(observer);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IOculusWindowManager
            public boolean registerSystemKeyEventHandler(ISystemKeyEventHandler handler) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(handler != null ? handler.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerSystemKeyEventHandler(handler);
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

            @Override // oculus.internal.IOculusWindowManager
            public boolean unregisterSystemKeyEventHandler(ISystemKeyEventHandler handler) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(handler != null ? handler.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterSystemKeyEventHandler(handler);
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
        }

        public static boolean setDefaultImpl(IOculusWindowManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IOculusWindowManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
