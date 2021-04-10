package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: xE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5564xE1 implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f11600a;
    public final String b;

    public AbstractC5564xE1(IBinder iBinder, String str) {
        this.f11600a = iBinder;
        this.b = str;
    }

    public IBinder asBinder() {
        return this.f11600a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final Parcel d(int i, Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.f11600a.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }
}
