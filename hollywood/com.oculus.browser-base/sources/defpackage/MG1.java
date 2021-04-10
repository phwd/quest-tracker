package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: MG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MG1 extends AbstractC4717sF1 {
    public MG1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }

    public final void c(int i) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        obtain.writeInt(i);
        Parcel obtain2 = Parcel.obtain();
        try {
            this.f11261a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
