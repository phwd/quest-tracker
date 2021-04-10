package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IAui4AManager extends IInterface {
    void setAui4ATcpConnectionInfo(boolean z, String str, int i, List<String> list) throws RemoteException;

    public static class Default implements IAui4AManager {
        @Override // oculus.internal.IAui4AManager
        public void setAui4ATcpConnectionInfo(boolean connectionAvailable, String nonce, int port, List<String> list) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAui4AManager {
        private static final String DESCRIPTOR = "oculus.internal.IAui4AManager";
        static final int TRANSACTION_setAui4ATcpConnectionInfo = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAui4AManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAui4AManager)) {
                return new Proxy(obj);
            }
            return (IAui4AManager) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                setAui4ATcpConnectionInfo(data.readInt() != 0, data.readString(), data.readInt(), data.createStringArrayList());
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
        public static class Proxy implements IAui4AManager {
            public static IAui4AManager sDefaultImpl;
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

            @Override // oculus.internal.IAui4AManager
            public void setAui4ATcpConnectionInfo(boolean connectionAvailable, String nonce, int port, List<String> addresses) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(connectionAvailable ? 1 : 0);
                    _data.writeString(nonce);
                    _data.writeInt(port);
                    _data.writeStringList(addresses);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setAui4ATcpConnectionInfo(connectionAvailable, nonce, port, addresses);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAui4AManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAui4AManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
