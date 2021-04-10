package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWindowLayoutObserver extends IInterface {
    void onFocusedWindowChanged(IBinder iBinder, int i, int i2) throws RemoteException;

    public static class Default implements IWindowLayoutObserver {
        @Override // oculus.internal.IWindowLayoutObserver
        public void onFocusedWindowChanged(IBinder windowToken, int owningUid, int owningPid) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IWindowLayoutObserver {
        private static final String DESCRIPTOR = "oculus.internal.IWindowLayoutObserver";
        static final int TRANSACTION_onFocusedWindowChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowLayoutObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowLayoutObserver)) {
                return new Proxy(obj);
            }
            return (IWindowLayoutObserver) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onFocusedWindowChanged(data.readStrongBinder(), data.readInt(), data.readInt());
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IWindowLayoutObserver {
            public static IWindowLayoutObserver sDefaultImpl;
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

            @Override // oculus.internal.IWindowLayoutObserver
            public void onFocusedWindowChanged(IBinder windowToken, int owningUid, int owningPid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(windowToken);
                    _data.writeInt(owningUid);
                    _data.writeInt(owningPid);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onFocusedWindowChanged(windowToken, owningUid, owningPid);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowLayoutObserver impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IWindowLayoutObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
