package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* renamed from: jC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3170jC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        String[] strArr = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                strArr = AbstractC5588xO0.f(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.Address(i, strArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.Address[i];
    }
}
