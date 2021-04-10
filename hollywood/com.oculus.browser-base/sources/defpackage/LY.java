package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: LY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LY implements NY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f8422a;

    public LY(IBinder iBinder) {
        this.f8422a = iBinder;
    }

    public IBinder asBinder() {
        return this.f8422a;
    }

    public String c() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.webapk.lib.common.identity_service.IIdentityService");
            if (!this.f8422a.transact(1, obtain, obtain2, 0)) {
                int i = MY.f8481a;
            }
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
