package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.zzac;

/* renamed from: DC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                f = AbstractC5588xO0.n(parcel, readInt);
            } else if (i == 3) {
                f2 = AbstractC5588xO0.n(parcel, readInt);
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                f3 = AbstractC5588xO0.n(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzac(f, f2, f3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzac[i];
    }
}
