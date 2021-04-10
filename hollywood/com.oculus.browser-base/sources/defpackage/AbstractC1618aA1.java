package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: aA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1618aA1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f9415a;
    public final String b;

    public AbstractC1618aA1(IBinder iBinder, String str) {
        this.f9415a = iBinder;
        this.b = str;
    }

    public IBinder asBinder() {
        return this.f9415a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final void d(int i, Parcel parcel) {
        try {
            this.f9415a.transact(i, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
