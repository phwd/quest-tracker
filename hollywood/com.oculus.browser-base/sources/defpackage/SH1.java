package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;

/* renamed from: SH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.UrlBookmark(str, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.UrlBookmark[i];
    }
}
