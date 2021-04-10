package android.os;

public interface IMaintenanceActivityListener extends IInterface {

    public static class Default implements IMaintenanceActivityListener {
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IMaintenanceActivityListener
        public void onMaintenanceActivityChanged(boolean z) throws RemoteException {
        }
    }

    void onMaintenanceActivityChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IMaintenanceActivityListener {
        private static final String DESCRIPTOR = "android.os.IMaintenanceActivityListener";
        static final int TRANSACTION_onMaintenanceActivityChanged = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMaintenanceActivityListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMaintenanceActivityListener)) {
                return new Proxy(iBinder);
            }
            return (IMaintenanceActivityListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onMaintenanceActivityChanged(parcel.readInt() != 0);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IMaintenanceActivityListener {
            public static IMaintenanceActivityListener sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.os.IMaintenanceActivityListener
            public void onMaintenanceActivityChanged(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onMaintenanceActivityChanged(z);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMaintenanceActivityListener iMaintenanceActivityListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMaintenanceActivityListener == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMaintenanceActivityListener;
                return true;
            }
        }

        public static IMaintenanceActivityListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
