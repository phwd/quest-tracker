package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IUserSwitchListener;

public interface ISettingsProxy extends IInterface {
    int getCurrentUserId() throws RemoteException;

    int getScreenBrightness() throws RemoteException;

    void registerUserSwitchListener(IUserSwitchListener iUserSwitchListener) throws RemoteException;

    void setScreenBrightness(int i) throws RemoteException;

    void unregisterUserSwitchListener(IUserSwitchListener iUserSwitchListener) throws RemoteException;

    public static class Default implements ISettingsProxy {
        @Override // oculus.internal.ISettingsProxy
        public int getCurrentUserId() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.ISettingsProxy
        public void registerUserSwitchListener(IUserSwitchListener listener) throws RemoteException {
        }

        @Override // oculus.internal.ISettingsProxy
        public void unregisterUserSwitchListener(IUserSwitchListener listener) throws RemoteException {
        }

        @Override // oculus.internal.ISettingsProxy
        public int getScreenBrightness() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.ISettingsProxy
        public void setScreenBrightness(int brightness) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISettingsProxy {
        private static final String DESCRIPTOR = "oculus.internal.ISettingsProxy";
        static final int TRANSACTION_getCurrentUserId = 1;
        static final int TRANSACTION_getScreenBrightness = 4;
        static final int TRANSACTION_registerUserSwitchListener = 2;
        static final int TRANSACTION_setScreenBrightness = 5;
        static final int TRANSACTION_unregisterUserSwitchListener = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISettingsProxy asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISettingsProxy)) {
                return new Proxy(obj);
            }
            return (ISettingsProxy) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _result = getCurrentUserId();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                registerUserSwitchListener(IUserSwitchListener.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                unregisterUserSwitchListener(IUserSwitchListener.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                int _result2 = getScreenBrightness();
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            } else if (code == 5) {
                data.enforceInterface(DESCRIPTOR);
                setScreenBrightness(data.readInt());
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
        public static class Proxy implements ISettingsProxy {
            public static ISettingsProxy sDefaultImpl;
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

            @Override // oculus.internal.ISettingsProxy
            public int getCurrentUserId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentUserId();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsProxy
            public void registerUserSwitchListener(IUserSwitchListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerUserSwitchListener(listener);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsProxy
            public void unregisterUserSwitchListener(IUserSwitchListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterUserSwitchListener(listener);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsProxy
            public int getScreenBrightness() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScreenBrightness();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsProxy
            public void setScreenBrightness(int brightness) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(brightness);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setScreenBrightness(brightness);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISettingsProxy impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISettingsProxy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
