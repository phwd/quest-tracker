package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: eZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2368eZ implements AbstractC2710gZ {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f9858a;

    public C2368eZ(IBinder iBinder) {
        this.f9858a = iBinder;
    }

    public IBinder asBinder() {
        return this.f9858a;
    }

    public void c() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
            if (!this.f9858a.transact(2, obtain, null, 1)) {
                int i = AbstractBinderC2539fZ.f9928a;
            }
        } finally {
            obtain.recycle();
        }
    }

    public void d(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            if (!this.f9858a.transact(1, obtain, null, 1)) {
                int i = AbstractBinderC2539fZ.f9928a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
