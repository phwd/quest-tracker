package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: EY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class EY extends Binder implements FY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f7967a = 0;

    public EY() {
        attachInterface(this, "org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
            ((BinderC5899zD) this).p(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
            return true;
        }
    }
}
