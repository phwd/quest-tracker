package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGatekeeperService extends IInterface {
    boolean getGatekeeperDef(int i, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IGatekeeperService {
        public static IGatekeeperService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("oculus.internal.IGatekeeperService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGatekeeperService)) {
                return new Proxy(iBinder);
            }
            return (IGatekeeperService) queryLocalInterface;
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IGatekeeperService {
            public static IGatekeeperService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // oculus.internal.IGatekeeperService
            public boolean getGatekeeperDef(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("oculus.internal.IGatekeeperService");
                    obtain.writeInt(i);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatekeeperDef(i, z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IGatekeeperService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
