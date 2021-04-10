package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AdBreakStatus extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new UE1();
    public final long F;
    public final long G;
    public final String H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final long f9639J;

    public AdBreakStatus(long j, long j2, String str, String str2, long j3) {
        this.F = j;
        this.G = j2;
        this.H = str;
        this.I = str2;
        this.f9639J = j3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.F == adBreakStatus.F && this.G == adBreakStatus.G && GF1.a(this.H, adBreakStatus.H) && GF1.a(this.I, adBreakStatus.I) && this.f9639J == adBreakStatus.f9639J;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.F), Long.valueOf(this.G), this.H, this.I, Long.valueOf(this.f9639J)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        long j = this.F;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeLong(j);
        long j2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 8);
        parcel.writeLong(j2);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.g(parcel, 5, this.I, false);
        long j3 = this.f9639J;
        AbstractC5758yO0.o(parcel, 6, 8);
        parcel.writeLong(j3);
        AbstractC5758yO0.n(parcel, l);
    }
}
