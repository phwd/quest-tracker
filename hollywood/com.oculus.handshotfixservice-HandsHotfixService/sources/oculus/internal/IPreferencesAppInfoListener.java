package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPreferencesAppInfoListener extends IInterface {
    void deleteUserData(int i) throws RemoteException;

    void onSchema(byte[] bArr) throws RemoteException;

    public static class Default implements IPreferencesAppInfoListener {
        @Override // oculus.internal.IPreferencesAppInfoListener
        public void onSchema(byte[] schemaList) throws RemoteException {
        }

        @Override // oculus.internal.IPreferencesAppInfoListener
        public void deleteUserData(int userId) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPreferencesAppInfoListener {
        private static final String DESCRIPTOR = "oculus.internal.IPreferencesAppInfoListener";
        static final int TRANSACTION_deleteUserData = 2;
        static final int TRANSACTION_onSchema = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPreferencesAppInfoListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPreferencesAppInfoListener)) {
                return new Proxy(obj);
            }
            return (IPreferencesAppInfoListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onSchema(data.createByteArray());
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                deleteUserData(data.readInt());
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IPreferencesAppInfoListener {
            public static IPreferencesAppInfoListener sDefaultImpl;
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

            @Override // oculus.internal.IPreferencesAppInfoListener
            public void onSchema(byte[] schemaList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(schemaList);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onSchema(schemaList);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IPreferencesAppInfoListener
            public void deleteUserData(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(2, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().deleteUserData(userId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPreferencesAppInfoListener impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPreferencesAppInfoListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
