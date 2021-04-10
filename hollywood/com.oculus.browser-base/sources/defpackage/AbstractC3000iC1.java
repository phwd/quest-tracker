package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: iC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3000iC1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f10124a;
    public final String b;

    public AbstractC3000iC1(IBinder iBinder, String str) {
        this.f10124a = iBinder;
        this.b = str;
    }

    public IBinder asBinder() {
        return this.f10124a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final Parcel d(int i, Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.f10124a.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    public final void f(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f10124a.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
