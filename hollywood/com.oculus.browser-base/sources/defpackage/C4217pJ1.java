package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzy;

/* renamed from: pJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4217pJ1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i5 = 65535 & readInt;
            if (i5 == 2) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i5 == 3) {
                i2 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i5 == 4) {
                i3 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i5 == 5) {
                i4 = AbstractC5588xO0.p(parcel, readInt);
            } else if (i5 != 6) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                f = AbstractC5588xO0.n(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzy(i, i2, i3, i4, f);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzy[i];
    }
}
