package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* renamed from: ZH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i2 == 3) {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i2 != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.WiFi(str, str2, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.WiFi[i];
    }
}
