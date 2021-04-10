package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.zza;

/* renamed from: XE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        Bundle bundle = null;
        int i = 0;
        Feature[] featureArr = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                bundle = AbstractC5588xO0.a(parcel, readInt);
            } else if (i2 == 2) {
                featureArr = (Feature[]) AbstractC5588xO0.h(parcel, readInt, Feature.CREATOR);
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zza(bundle, featureArr, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }
}
