package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* renamed from: PG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        String str3 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 == 3) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i2 == 4) {
                str3 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i2 != 5) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.Email(i, str, str3, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.Email[i];
    }
}
