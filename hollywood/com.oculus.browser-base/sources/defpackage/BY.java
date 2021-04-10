package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: BY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BY implements CY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f7744a;

    public BY(IBinder iBinder) {
        this.f7744a = iBinder;
    }

    public IBinder asBinder() {
        return this.f7744a;
    }

    @Override // defpackage.CY
    public void g(Bundle bundle, FY fy) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.components.browser_ui.photo_picker.IDecoderService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            obtain.writeStrongBinder((EY) fy);
            if (!this.f7744a.transact(1, obtain, null, 1)) {
                int i = AZ.f7676a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
