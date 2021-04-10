package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.zzah;

/* renamed from: eD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2319eD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzah(i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzah[i];
    }
}
