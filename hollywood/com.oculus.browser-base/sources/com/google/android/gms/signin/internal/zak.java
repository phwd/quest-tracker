package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new SB1();
    public final int F;
    public final ConnectionResult G;
    public final ResolveAccountResponse H;

    public zak(int i, ConnectionResult connectionResult, ResolveAccountResponse resolveAccountResponse) {
        this.F = i;
        this.G = connectionResult;
        this.H = resolveAccountResponse;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 2, this.G, i, false);
        AbstractC5758yO0.f(parcel, 3, this.H, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public zak() {
        ConnectionResult connectionResult = new ConnectionResult(8, null);
        this.F = 1;
        this.G = connectionResult;
        this.H = null;
    }
}
