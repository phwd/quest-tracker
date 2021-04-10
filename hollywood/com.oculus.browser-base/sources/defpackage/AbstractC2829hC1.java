package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: hC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2829hC1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f10053a;
    public final String b;

    public AbstractC2829hC1(IBinder iBinder, String str) {
        this.f10053a = iBinder;
        this.b = str;
    }

    public IBinder asBinder() {
        return this.f10053a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final void d(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f10053a.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
