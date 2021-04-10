package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzn;

/* renamed from: cI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1993cI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        long j = 0;
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
                j = AbstractC5588xO0.r(parcel, readInt);
            } else if (i5 != 6) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i4 = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzn(i, i2, i3, j, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }
}
