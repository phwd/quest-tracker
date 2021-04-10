package oculus.internal.audio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVrAudioDataDumpManagerClient extends IInterface {
    void onZippingComplete() throws RemoteException;

    public static class Default implements IVrAudioDataDumpManagerClient {
        @Override // oculus.internal.audio.IVrAudioDataDumpManagerClient
        public void onZippingComplete() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrAudioDataDumpManagerClient {
        private static final String DESCRIPTOR = "oculus.internal.audio.IVrAudioDataDumpManagerClient";
        static final int TRANSACTION_onZippingComplete = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrAudioDataDumpManagerClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrAudioDataDumpManagerClient)) {
                return new Proxy(obj);
            }
            return (IVrAudioDataDumpManagerClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onZippingComplete();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IVrAudioDataDumpManagerClient {
            public static IVrAudioDataDumpManagerClient sDefaultImpl;
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

            @Override // oculus.internal.audio.IVrAudioDataDumpManagerClient
            public void onZippingComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onZippingComplete();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrAudioDataDumpManagerClient impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrAudioDataDumpManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
