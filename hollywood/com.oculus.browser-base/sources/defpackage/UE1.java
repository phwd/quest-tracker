package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.AdBreakStatus;

/* renamed from: UE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                j = AbstractC5588xO0.r(parcel, readInt);
            } else if (i == 3) {
                j2 = AbstractC5588xO0.r(parcel, readInt);
            } else if (i == 4) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 5) {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i != 6) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                j3 = AbstractC5588xO0.r(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AdBreakStatus(j, j2, str, str2, j3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdBreakStatus[i];
    }
}
