package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISystemKeyEventHandler extends IInterface {
    boolean handleSystemKeyEvent(int i, int i2) throws RemoteException;

    public static class Default implements ISystemKeyEventHandler {
        @Override // oculus.internal.ISystemKeyEventHandler
        public boolean handleSystemKeyEvent(int keyCode, int action) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISystemKeyEventHandler {
        private static final String DESCRIPTOR = "oculus.internal.ISystemKeyEventHandler";
        static final int TRANSACTION_handleSystemKeyEvent = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemKeyEventHandler asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISystemKeyEventHandler)) {
                return new Proxy(obj);
            }
            return (ISystemKeyEventHandler) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                boolean handleSystemKeyEvent = handleSystemKeyEvent(data.readInt(), data.readInt());
                reply.writeNoException();
                reply.writeInt(handleSystemKeyEvent ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ISystemKeyEventHandler {
            public static ISystemKeyEventHandler sDefaultImpl;
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

            @Override // oculus.internal.ISystemKeyEventHandler
            public boolean handleSystemKeyEvent(int keyCode, int action) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyCode);
                    _data.writeInt(action);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().handleSystemKeyEvent(keyCode, action);
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

        public static boolean setDefaultImpl(ISystemKeyEventHandler impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISystemKeyEventHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
