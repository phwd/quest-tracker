package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: OB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OB1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8607a;
    public final String b = "com.google.android.gms.signin.internal.ISignInService";

    public OB1(IBinder iBinder) {
        this.f8607a = iBinder;
    }

    public IBinder asBinder() {
        return this.f8607a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final void d(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f8607a.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
