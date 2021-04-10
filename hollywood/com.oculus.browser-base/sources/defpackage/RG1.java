package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* renamed from: RG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        double d = 0.0d;
        double d2 = 0.0d;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                d = AbstractC5588xO0.l(parcel, readInt);
            } else if (i != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                d2 = AbstractC5588xO0.l(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.GeoPoint(d, d2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.GeoPoint[i];
    }
}
