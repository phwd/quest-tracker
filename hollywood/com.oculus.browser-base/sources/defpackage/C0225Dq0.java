package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: Dq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0225Dq0 implements AbstractC0347Fq0 {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f7914a;

    public C0225Dq0(IBinder iBinder) {
        this.f7914a = iBinder;
    }

    public IBinder asBinder() {
        return this.f7914a;
    }

    public Bundle c(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            this.f7914a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
