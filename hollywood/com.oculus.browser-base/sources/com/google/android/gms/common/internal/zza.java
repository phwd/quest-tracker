package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new XE1();
    public Bundle F;
    public Feature[] G;
    public int H;

    public zza(Bundle bundle, Feature[] featureArr, int i) {
        this.F = bundle;
        this.G = featureArr;
        this.H = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.a(parcel, 1, this.F, false);
        AbstractC5758yO0.j(parcel, 2, this.G, i, false);
        int i2 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.n(parcel, l);
    }
}
