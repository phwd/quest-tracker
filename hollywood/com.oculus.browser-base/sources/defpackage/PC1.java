package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.zzac;
import com.google.android.gms.cast.zzae;

/* renamed from: PC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        zzac zzac = null;
        zzac zzac2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                zzac = (zzac) AbstractC5588xO0.d(parcel, readInt, zzac.CREATOR);
            } else if (i != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                zzac2 = (zzac) AbstractC5588xO0.d(parcel, readInt, zzac.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzae(zzac, zzac2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzae[i];
    }
}
