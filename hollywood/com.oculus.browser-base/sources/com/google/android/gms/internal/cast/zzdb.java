package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzdb extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new HF1();
    public double F;
    public boolean G;
    public int H;
    public ApplicationMetadata I;

    /* renamed from: J  reason: collision with root package name */
    public int f9670J;
    public zzae K;
    public double L;

    public zzdb(double d, boolean z, int i, ApplicationMetadata applicationMetadata, int i2, zzae zzae, double d2) {
        this.F = d;
        this.G = z;
        this.H = i;
        this.I = applicationMetadata;
        this.f9670J = i2;
        this.K = zzae;
        this.L = d2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return false;
        }
        zzdb zzdb = (zzdb) obj;
        if (this.F == zzdb.F && this.G == zzdb.G && this.H == zzdb.H && GF1.a(this.I, zzdb.I) && this.f9670J == zzdb.f9670J) {
            zzae zzae = this.K;
            return GF1.a(zzae, zzae) && this.L == zzdb.L;
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Double.valueOf(this.F), Boolean.valueOf(this.G), Integer.valueOf(this.H), this.I, Integer.valueOf(this.f9670J), this.K, Double.valueOf(this.L)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        double d = this.F;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeDouble(d);
        boolean z = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(z ? 1 : 0);
        int i2 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 5, this.I, i, false);
        int i3 = this.f9670J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.f(parcel, 7, this.K, i, false);
        double d2 = this.L;
        AbstractC5758yO0.o(parcel, 8, 8);
        parcel.writeDouble(d2);
        AbstractC5758yO0.n(parcel, l);
    }
}
