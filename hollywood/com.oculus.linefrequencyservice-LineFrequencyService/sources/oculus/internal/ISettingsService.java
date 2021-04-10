package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.ISettingsObserver;

public interface ISettingsService extends IInterface {
    void createUserDatabase(int i) throws RemoteException;

    void deleteUserDatabase(int i) throws RemoteException;

    String getSetting(String str) throws RemoteException;

    String getUserSetting(String str, int i) throws RemoteException;

    void registerSettingsObserver(String str, ISettingsObserver iSettingsObserver) throws RemoteException;

    boolean setSetting(String str, String str2) throws RemoteException;

    boolean setUserSetting(String str, String str2, int i) throws RemoteException;

    void unregisterSettingsObserver(ISettingsObserver iSettingsObserver) throws RemoteException;

    public static class Default implements ISettingsService {
        @Override // oculus.internal.ISettingsService
        public boolean setSetting(String name, String value) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ISettingsService
        public String getSetting(String name) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ISettingsService
        public void registerSettingsObserver(String name, ISettingsObserver observer) throws RemoteException {
        }

        @Override // oculus.internal.ISettingsService
        public void unregisterSettingsObserver(ISettingsObserver observer) throws RemoteException {
        }

        @Override // oculus.internal.ISettingsService
        public boolean setUserSetting(String name, String value, int userId) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ISettingsService
        public String getUserSetting(String name, int userId) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ISettingsService
        public void createUserDatabase(int userId) throws RemoteException {
        }

        @Override // oculus.internal.ISettingsService
        public void deleteUserDatabase(int userId) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISettingsService {
        private static final String DESCRIPTOR = "oculus.internal.ISettingsService";
        static final int TRANSACTION_createUserDatabase = 7;
        static final int TRANSACTION_deleteUserDatabase = 8;
        static final int TRANSACTION_getSetting = 2;
        static final int TRANSACTION_getUserSetting = 6;
        static final int TRANSACTION_registerSettingsObserver = 3;
        static final int TRANSACTION_setSetting = 1;
        static final int TRANSACTION_setUserSetting = 5;
        static final int TRANSACTION_unregisterSettingsObserver = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISettingsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISettingsService)) {
                return new Proxy(obj);
            }
            return (ISettingsService) iin;
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
                        boolean setting = setSetting(data.readString(), data.readString());
                        reply.writeNoException();
                        reply.writeInt(setting ? 1 : 0);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        String _result = getSetting(data.readString());
                        reply.writeNoException();
                        reply.writeString(_result);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        registerSettingsObserver(data.readString(), ISettingsObserver.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterSettingsObserver(ISettingsObserver.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        boolean userSetting = setUserSetting(data.readString(), data.readString(), data.readInt());
                        reply.writeNoException();
                        reply.writeInt(userSetting ? 1 : 0);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        String _result2 = getUserSetting(data.readString(), data.readInt());
                        reply.writeNoException();
                        reply.writeString(_result2);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        createUserDatabase(data.readInt());
                        reply.writeNoException();
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        deleteUserDatabase(data.readInt());
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
        public static class Proxy implements ISettingsService {
            public static ISettingsService sDefaultImpl;
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

            @Override // oculus.internal.ISettingsService
            public boolean setSetting(String name, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(value);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSetting(name, value);
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

            @Override // oculus.internal.ISettingsService
            public String getSetting(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSetting(name);
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

            @Override // oculus.internal.ISettingsService
            public void registerSettingsObserver(String name, ISettingsObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerSettingsObserver(name, observer);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsService
            public void unregisterSettingsObserver(ISettingsObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterSettingsObserver(observer);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsService
            public boolean setUserSetting(String name, String value, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(value);
                    _data.writeInt(userId);
                    boolean _result = false;
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserSetting(name, value, userId);
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

            @Override // oculus.internal.ISettingsService
            public String getUserSetting(String name, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(userId);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSetting(name, userId);
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

            @Override // oculus.internal.ISettingsService
            public void createUserDatabase(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(7, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().createUserDatabase(userId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISettingsService
            public void deleteUserDatabase(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(8, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().deleteUserDatabase(userId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISettingsService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISettingsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
