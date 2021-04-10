package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4714sE1();
    public final String F;
    @Deprecated
    public final int G;
    public final long H;

    public Feature(String str, long j) {
        this.F = str;
        this.H = j;
        this.G = -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            String str = this.F;
            if (((str == null || !str.equals(feature.F)) && (this.F != null || feature.F != null)) || x() != feature.x()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, Long.valueOf(x())});
    }

    public String toString() {
        C0834Nq0 nq0 = new C0834Nq0(this, null);
        nq0.a("name", this.F);
        nq0.a("version", Long.valueOf(x()));
        return nq0.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 1, this.F, false);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        long x = x();
        AbstractC5758yO0.o(parcel, 3, 8);
        parcel.writeLong(x);
        AbstractC5758yO0.n(parcel, l);
    }

    public long x() {
        long j = this.H;
        return j == -1 ? (long) this.G : j;
    }

    public Feature(String str, int i, long j) {
        this.F = str;
        this.G = i;
        this.H = j;
    }
}
