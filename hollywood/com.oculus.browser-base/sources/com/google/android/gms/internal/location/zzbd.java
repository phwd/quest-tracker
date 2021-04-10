package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzbd extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new CE1();
    public static final List F = Collections.emptyList();
    public LocationRequest G;
    public List H;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9671J;
    public boolean K;
    public boolean L;
    public String M;

    public zzbd(LocationRequest locationRequest, List list, String str, boolean z, boolean z2, boolean z3, String str2) {
        this.G = locationRequest;
        this.H = list;
        this.I = str;
        this.f9671J = z;
        this.K = z2;
        this.L = z3;
        this.M = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbd)) {
            return false;
        }
        zzbd zzbd = (zzbd) obj;
        return AbstractC0895Oq0.a(this.G, zzbd.G) && AbstractC0895Oq0.a(this.H, zzbd.H) && AbstractC0895Oq0.a(this.I, zzbd.I) && this.f9671J == zzbd.f9671J && this.K == zzbd.K && this.L == zzbd.L && AbstractC0895Oq0.a(this.M, zzbd.M);
    }

    public final int hashCode() {
        return this.G.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.G);
        if (this.I != null) {
            sb.append(" tag=");
            sb.append(this.I);
        }
        if (this.M != null) {
            sb.append(" moduleId=");
            sb.append(this.M);
        }
        sb.append(" hideAppOps=");
        sb.append(this.f9671J);
        sb.append(" clients=");
        sb.append(this.H);
        sb.append(" forceCoarseLocation=");
        sb.append(this.K);
        if (this.L) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 1, this.G, i, false);
        AbstractC5758yO0.k(parcel, 5, this.H, false);
        AbstractC5758yO0.g(parcel, 6, this.I, false);
        boolean z = this.f9671J;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.K;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = this.L;
        AbstractC5758yO0.o(parcel, 9, 4);
        parcel.writeInt(z3 ? 1 : 0);
        AbstractC5758yO0.g(parcel, 10, this.M, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
