package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zai extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new QB1();
    public final int F;
    public final ResolveAccountRequest G;

    public zai(int i, ResolveAccountRequest resolveAccountRequest) {
        this.F = i;
        this.G = resolveAccountRequest;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 2, this.G, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public zai(ResolveAccountRequest resolveAccountRequest) {
        this.F = 1;
        this.G = resolveAccountRequest;
    }
}
