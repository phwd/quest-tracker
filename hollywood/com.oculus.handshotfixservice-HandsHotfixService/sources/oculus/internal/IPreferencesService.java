package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IPreferencesListener;

public interface IPreferencesService extends IInterface {
    public static final int STATUS_BINDER_FAILED = -10;
    public static final int STATUS_DATA_DUPLICATE = -12;
    public static final int STATUS_DATA_ERROR = -11;
    public static final int STATUS_DELETE_USER_DATA_FAILED = -15;
    public static final int STATUS_KEY_NOT_FOUND = -2;
    public static final int STATUS_LISTENER_REGISTRATION_FAILED = -13;
    public static final int STATUS_NO_ACCESS = -3;
    public static final int STATUS_NO_LISTENER_FOUND = -6;
    public static final int STATUS_NO_READ_ACCESS = -5;
    public static final int STATUS_NO_WRITE_ACCESS = -4;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OTHERS_FAILED = -14;
    public static final int STATUS_PERMISSION_DENIED = -7;
    public static final int STATUS_READ_ERROR = -9;
    public static final int STATUS_SERVER_NOT_FOUND = -1;
    public static final int STATUS_WRITE_ERROR = -8;
    public static final int TYPE_BOOLEAN = 4;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_INTEGER = 0;
    public static final int TYPE_LONG = 3;
    public static final int TYPE_STRING = 2;
    public static final int TYPE_UNKNOWN = 6;

    boolean getBoolean(String str) throws RemoteException;

    double getDouble(String str) throws RemoteException;

    float getFloat(String str) throws RemoteException;

    int getInteger(String str) throws RemoteException;

    long getLong(String str) throws RemoteException;

    String getString(String str) throws RemoteException;

    int getType(String str) throws RemoteException;

    void registerListener(IPreferencesListener iPreferencesListener, String[] strArr) throws RemoteException;

    void setBoolean(String str, boolean z) throws RemoteException;

    void setDouble(String str, double d) throws RemoteException;

    void setFloat(String str, float f) throws RemoteException;

    void setInteger(String str, int i) throws RemoteException;

    void setLong(String str, long j) throws RemoteException;

    void setString(String str, String str2) throws RemoteException;

    void unregisterListener(IPreferencesListener iPreferencesListener) throws RemoteException;

    public static class Default implements IPreferencesService {
        @Override // oculus.internal.IPreferencesService
        public void setInteger(String key, int value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void setDouble(String key, double value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void setString(String key, String value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void setLong(String key, long value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void setBoolean(String key, boolean value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void setFloat(String key, float value) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public int getInteger(String key) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IPreferencesService
        public double getDouble(String key) throws RemoteException {
            return 0.0d;
        }

        @Override // oculus.internal.IPreferencesService
        public String getString(String key) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IPreferencesService
        public long getLong(String key) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IPreferencesService
        public boolean getBoolean(String key) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IPreferencesService
        public float getFloat(String key) throws RemoteException {
            return 0.0f;
        }

        @Override // oculus.internal.IPreferencesService
        public int getType(String key) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IPreferencesService
        public void registerListener(IPreferencesListener listener, String[] keys) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesService
        public void unregisterListener(IPreferencesListener listener) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPreferencesService {
        private static final String DESCRIPTOR = "oculus.internal.IPreferencesService";
        static final int TRANSACTION_getBoolean = 11;
        static final int TRANSACTION_getDouble = 8;
        static final int TRANSACTION_getFloat = 12;
        static final int TRANSACTION_getInteger = 7;
        static final int TRANSACTION_getLong = 10;
        static final int TRANSACTION_getString = 9;
        static final int TRANSACTION_getType = 13;
        static final int TRANSACTION_registerListener = 14;
        static final int TRANSACTION_setBoolean = 5;
        static final int TRANSACTION_setDouble = 2;
        static final int TRANSACTION_setFloat = 6;
        static final int TRANSACTION_setInteger = 1;
        static final int TRANSACTION_setLong = 4;
        static final int TRANSACTION_setString = 3;
        static final int TRANSACTION_unregisterListener = 15;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPreferencesService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPreferencesService)) {
                return new Proxy(obj);
            }
            return (IPreferencesService) iin;
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
                        setInteger(data.readString(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        setDouble(data.readString(), data.readDouble());
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        setString(data.readString(), data.readString());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        setLong(data.readString(), data.readLong());
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        setBoolean(data.readString(), data.readInt() != 0);
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        setFloat(data.readString(), data.readFloat());
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        int _result = getInteger(data.readString());
                        reply.writeNoException();
                        reply.writeInt(_result);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        double _result2 = getDouble(data.readString());
                        reply.writeNoException();
                        reply.writeDouble(_result2);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        String _result3 = getString(data.readString());
                        reply.writeNoException();
                        reply.writeString(_result3);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        long _result4 = getLong(data.readString());
                        reply.writeNoException();
                        reply.writeLong(_result4);
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        boolean z = getBoolean(data.readString());
                        reply.writeNoException();
                        reply.writeInt(z ? 1 : 0);
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        float _result5 = getFloat(data.readString());
                        reply.writeNoException();
                        reply.writeFloat(_result5);
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        int _result6 = getType(data.readString());
                        reply.writeNoException();
                        reply.writeInt(_result6);
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        registerListener(IPreferencesListener.Stub.asInterface(data.readStrongBinder()), data.createStringArray());
                        reply.writeNoException();
                        return true;
                    case 15:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterListener(IPreferencesListener.Stub.asInterface(data.readStrongBinder()));
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
        public static class Proxy implements IPreferencesService {
            public static IPreferencesService sDefaultImpl;
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

            @Override // oculus.internal.IPreferencesService
            public void setInteger(String key, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(value);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setInteger(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void setDouble(String key, double value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeDouble(value);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setDouble(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void setString(String key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeString(value);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setString(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void setLong(String key, long value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeLong(value);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setLong(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void setBoolean(String key, boolean value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeInt(value ? 1 : 0);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setBoolean(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void setFloat(String key, float value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    _data.writeFloat(value);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setFloat(key, value);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public int getInteger(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInteger(key);
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

            @Override // oculus.internal.IPreferencesService
            public double getDouble(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDouble(key);
                    }
                    _reply.readException();
                    double _result = _reply.readDouble();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public String getString(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getString(key);
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

            @Override // oculus.internal.IPreferencesService
            public long getLong(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(10, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLong(key);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public boolean getBoolean(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    boolean _result = false;
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBoolean(key);
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

            @Override // oculus.internal.IPreferencesService
            public float getFloat(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFloat(key);
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public int getType(String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(key);
                    if (!this.mRemote.transact(13, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getType(key);
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

            @Override // oculus.internal.IPreferencesService
            public void registerListener(IPreferencesListener listener, String[] keys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeStringArray(keys);
                    if (this.mRemote.transact(14, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerListener(listener, keys);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesService
            public void unregisterListener(IPreferencesListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(15, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterListener(listener);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPreferencesService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPreferencesService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
