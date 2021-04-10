package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: FG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FG1 implements AbstractC2328eG1, IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8006a;
    public final String b = "com.google.android.auth.IAuthManagerService";

    public FG1(IBinder iBinder) {
        this.f8006a = iBinder;
    }

    public IBinder asBinder() {
        return this.f8006a;
    }

    public final Parcel c() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final Parcel d(int i, Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.f8006a.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }
}
