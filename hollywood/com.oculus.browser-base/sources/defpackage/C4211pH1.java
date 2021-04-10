package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: pH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4211pH1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f11059a;
    public final String b = "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService";

    public C4211pH1(IBinder iBinder) {
        this.f11059a = iBinder;
    }

    public IBinder asBinder() {
        return this.f11059a;
    }

    public final void c(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f11059a.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
