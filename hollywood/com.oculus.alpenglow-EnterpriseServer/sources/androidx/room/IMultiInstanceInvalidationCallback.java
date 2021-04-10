package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMultiInstanceInvalidationCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationCallback {

        public static class Proxy implements IMultiInstanceInvalidationCallback {
            public IBinder A00;

            public final IBinder asBinder() {
                return this.A00;
            }

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // androidx.room.IMultiInstanceInvalidationCallback
            public final void A6A(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.room.IMultiInstanceInvalidationCallback");
                    obtain.writeStringArray(strArr);
                    this.A00.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("androidx.room.IMultiInstanceInvalidationCallback");
                A6A(parcel.createStringArray());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("androidx.room.IMultiInstanceInvalidationCallback");
                return true;
            }
        }

        public static IMultiInstanceInvalidationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.room.IMultiInstanceInvalidationCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMultiInstanceInvalidationCallback)) {
                return new Proxy(iBinder);
            }
            return (IMultiInstanceInvalidationCallback) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, "androidx.room.IMultiInstanceInvalidationCallback");
        }
    }

    void A6A(String[] strArr) throws RemoteException;
}
