package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: V30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V30 implements X30 {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f9063a;

    public V30(IBinder iBinder) {
        this.f9063a = iBinder;
    }

    public IBinder asBinder() {
        return this.f9063a;
    }

    public void c(Z30 z30) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.IsReadyToPayService");
            obtain.writeStrongBinder((Y30) z30);
            if (!this.f9063a.transact(1, obtain, null, 1)) {
                int i = W30.f9125a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
