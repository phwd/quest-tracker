package android.os;

public interface IUpdateEngineCallback extends IInterface {
    void onPayloadApplicationComplete(int i) throws RemoteException;

    void onStatusUpdate(int i, float f) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateEngineCallback {
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.os.IUpdateEngineCallback");
        }

        public static IUpdateEngineCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.os.IUpdateEngineCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUpdateEngineCallback)) {
                return new Proxy(iBinder);
            }
            return (IUpdateEngineCallback) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("android.os.IUpdateEngineCallback");
                onStatusUpdate(parcel.readInt(), parcel.readFloat());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("android.os.IUpdateEngineCallback");
                onPayloadApplicationComplete(parcel.readInt());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.os.IUpdateEngineCallback");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IUpdateEngineCallback {
            public static IUpdateEngineCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.os.IUpdateEngineCallback
            public void onStatusUpdate(int i, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngineCallback");
                    obtain.writeInt(i);
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onStatusUpdate(i, f);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngineCallback
            public void onPayloadApplicationComplete(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngineCallback");
                    obtain.writeInt(i);
                    if (this.mRemote.transact(2, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onPayloadApplicationComplete(i);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static IUpdateEngineCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
