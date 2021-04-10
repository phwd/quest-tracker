package X;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: X.j1  reason: case insensitive filesystem */
public final class C0825j1 implements C2 {
    public final IBinder A00;

    public C0825j1(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // X.C2
    public final void A3d(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
            obtain.writeInt(i);
            this.A00.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
