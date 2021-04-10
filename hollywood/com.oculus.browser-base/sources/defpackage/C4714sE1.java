package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;

/* renamed from: sE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4714sE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        int i = 0;
        long j = -1;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i2 == 2) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                j = AbstractC5588xO0.r(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Feature(str, i, j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Feature[i];
    }
}
