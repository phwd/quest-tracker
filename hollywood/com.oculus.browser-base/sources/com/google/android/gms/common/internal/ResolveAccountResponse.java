package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new VB1();
    public final int F;
    public IBinder G;
    public ConnectionResult H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9659J;

    public ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.F = i;
        this.G = iBinder;
        this.H = connectionResult;
        this.I = z;
        this.f9659J = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.H.equals(resolveAccountResponse.H) && x().equals(resolveAccountResponse.x());
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.d(parcel, 2, this.G, false);
        AbstractC5758yO0.f(parcel, 3, this.H, i, false);
        boolean z = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.f9659J;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(z2 ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }

    public AbstractC4757sY x() {
        return D0.d(this.G);
    }
}
