package oculus.internal.enterprise;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IEnterpriseServer extends IInterface {
    Configuration[] getConfigurations() throws RemoteException;

    public static class Default implements IEnterpriseServer {
        @Override // oculus.internal.enterprise.IEnterpriseServer
        public Configuration[] getConfigurations() throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IEnterpriseServer {
        private static final String DESCRIPTOR = "oculus.internal.enterprise.IEnterpriseServer";
        static final int TRANSACTION_getConfigurations = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IEnterpriseServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IEnterpriseServer)) {
                return new Proxy(obj);
            }
            return (IEnterpriseServer) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                Configuration[] _result = getConfigurations();
                reply.writeNoException();
                reply.writeTypedArray(_result, 1);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IEnterpriseServer {
            public static IEnterpriseServer sDefaultImpl;
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

            @Override // oculus.internal.enterprise.IEnterpriseServer
            public Configuration[] getConfigurations() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigurations();
                    }
                    _reply.readException();
                    Configuration[] _result = (Configuration[]) _reply.createTypedArray(Configuration.CREATOR);
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEnterpriseServer impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IEnterpriseServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
