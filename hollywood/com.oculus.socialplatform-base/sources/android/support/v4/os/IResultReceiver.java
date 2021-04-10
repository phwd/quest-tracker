package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IResultReceiver extends IInterface {

    public static abstract class Stub extends Binder implements IResultReceiver {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                int readInt = parcel.readInt();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                } else {
                    bundle = null;
                }
                A9Y(readInt, bundle);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.support.v4.os.IResultReceiver");
                return true;
            }
        }

        public static class Proxy implements IResultReceiver {
            public static IResultReceiver A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // android.support.v4.os.IResultReceiver
            public final void A9Y(int i, Bundle bundle) throws RemoteException {
                IResultReceiver iResultReceiver;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.A00.transact(1, obtain, null, 1) && (iResultReceiver = A01) != null) {
                        iResultReceiver.A9Y(i, bundle);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

        public static IResultReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IResultReceiver)) {
                return new Proxy(iBinder);
            }
            return (IResultReceiver) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IResultReceiver iResultReceiver) {
            if (Proxy.A01 != null || iResultReceiver == null) {
                return false;
            }
            Proxy.A01 = iResultReceiver;
            return true;
        }

        public Stub() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public static IResultReceiver getDefaultImpl() {
            return Proxy.A01;
        }
    }

    void A9Y(int i, Bundle bundle) throws RemoteException;
}
