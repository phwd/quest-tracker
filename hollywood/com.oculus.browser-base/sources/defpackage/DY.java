package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: DY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DY implements FY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f7895a;

    public DY(IBinder iBinder) {
        this.f7895a = iBinder;
    }

    public IBinder asBinder() {
        return this.f7895a;
    }

    @Override // defpackage.FY
    public void p(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f7895a.transact(1, obtain, null, 1)) {
                int i = EY.f7967a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
