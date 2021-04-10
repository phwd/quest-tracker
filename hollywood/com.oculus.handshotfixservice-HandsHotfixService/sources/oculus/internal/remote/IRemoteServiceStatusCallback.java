package oculus.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteServiceStatusCallback extends IInterface {
    void onStatus(int i, RemoteStatus remoteStatus) throws RemoteException;

    public static class Default implements IRemoteServiceStatusCallback {
        @Override // oculus.internal.remote.IRemoteServiceStatusCallback
        public void onStatus(int deviceType, RemoteStatus status) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IRemoteServiceStatusCallback {
        private static final String DESCRIPTOR = "oculus.internal.remote.IRemoteServiceStatusCallback";
        static final int TRANSACTION_onStatus = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteServiceStatusCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteServiceStatusCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteServiceStatusCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RemoteStatus _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0 = data.readInt();
                if (data.readInt() != 0) {
                    _arg1 = RemoteStatus.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onStatus(_arg0, _arg1);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IRemoteServiceStatusCallback {
            public static IRemoteServiceStatusCallback sDefaultImpl;
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

            @Override // oculus.internal.remote.IRemoteServiceStatusCallback
            public void onStatus(int deviceType, RemoteStatus status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onStatus(deviceType, status);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteServiceStatusCallback impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRemoteServiceStatusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
