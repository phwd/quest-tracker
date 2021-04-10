package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IPreferencesAppInfoListener;

public interface IPreferencesAppInfoInquirer extends IInterface {
    void getFullPackageInfo() throws RemoteException;

    boolean registerPackageInfoListener(IPreferencesAppInfoListener iPreferencesAppInfoListener) throws RemoteException;

    boolean unregisterPackageInfoListener() throws RemoteException;

    public static class Default implements IPreferencesAppInfoInquirer {
        @Override // oculus.internal.IPreferencesAppInfoInquirer
        public void getFullPackageInfo() throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesAppInfoInquirer
        public boolean registerPackageInfoListener(IPreferencesAppInfoListener listener) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IPreferencesAppInfoInquirer
        public boolean unregisterPackageInfoListener() throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPreferencesAppInfoInquirer {
        private static final String DESCRIPTOR = "oculus.internal.IPreferencesAppInfoInquirer";
        static final int TRANSACTION_getFullPackageInfo = 1;
        static final int TRANSACTION_registerPackageInfoListener = 2;
        static final int TRANSACTION_unregisterPackageInfoListener = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPreferencesAppInfoInquirer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPreferencesAppInfoInquirer)) {
                return new Proxy(obj);
            }
            return (IPreferencesAppInfoInquirer) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                getFullPackageInfo();
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean registerPackageInfoListener = registerPackageInfoListener(IPreferencesAppInfoListener.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                reply.writeInt(registerPackageInfoListener ? 1 : 0);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                boolean unregisterPackageInfoListener = unregisterPackageInfoListener();
                reply.writeNoException();
                reply.writeInt(unregisterPackageInfoListener ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IPreferencesAppInfoInquirer {
            public static IPreferencesAppInfoInquirer sDefaultImpl;
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

            @Override // oculus.internal.IPreferencesAppInfoInquirer
            public void getFullPackageInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getFullPackageInfo();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesAppInfoInquirer
            public boolean registerPackageInfoListener(IPreferencesAppInfoListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerPackageInfoListener(listener);
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

            @Override // oculus.internal.IPreferencesAppInfoInquirer
            public boolean unregisterPackageInfoListener() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterPackageInfoListener();
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

        public static boolean setDefaultImpl(IPreferencesAppInfoInquirer impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPreferencesAppInfoInquirer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
