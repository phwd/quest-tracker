package oculus.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteServiceFirmwareUpdateProgressCallback extends IInterface {
    void onFirmwareUpdateProgress(int i, float f) throws RemoteException;

    public static class Default implements IRemoteServiceFirmwareUpdateProgressCallback {
        @Override // oculus.internal.remote.IRemoteServiceFirmwareUpdateProgressCallback
        public void onFirmwareUpdateProgress(int deviceType, float progressPercentage) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IRemoteServiceFirmwareUpdateProgressCallback {
        private static final String DESCRIPTOR = "oculus.internal.remote.IRemoteServiceFirmwareUpdateProgressCallback";
        static final int TRANSACTION_onFirmwareUpdateProgress = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteServiceFirmwareUpdateProgressCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteServiceFirmwareUpdateProgressCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteServiceFirmwareUpdateProgressCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onFirmwareUpdateProgress(data.readInt(), data.readFloat());
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IRemoteServiceFirmwareUpdateProgressCallback {
            public static IRemoteServiceFirmwareUpdateProgressCallback sDefaultImpl;
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

            @Override // oculus.internal.remote.IRemoteServiceFirmwareUpdateProgressCallback
            public void onFirmwareUpdateProgress(int deviceType, float progressPercentage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    _data.writeFloat(progressPercentage);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onFirmwareUpdateProgress(deviceType, progressPercentage);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteServiceFirmwareUpdateProgressCallback impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRemoteServiceFirmwareUpdateProgressCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
