package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new CC1();
    public int F;
    public long G;
    public long H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public long f9677J;
    public int K;
    public float L;
    public long M;

    public LocationRequest() {
        this.F = 102;
        this.G = 3600000;
        this.H = 600000;
        this.I = false;
        this.f9677J = Long.MAX_VALUE;
        this.K = Integer.MAX_VALUE;
        this.L = 0.0f;
        this.M = 0;
    }

    public LocationRequest(int i, long j, long j2, boolean z, long j3, int i2, float f, long j4) {
        this.F = i;
        this.G = j;
        this.H = j2;
        this.I = z;
        this.f9677J = j3;
        this.K = i2;
        this.L = f;
        this.M = j4;
    }

    public final LocationRequest A(int i) {
        if (i == 100 || i == 102 || i == 104 || i == 105) {
            this.F = i;
            return this;
        }
        throw new IllegalArgumentException(AbstractC2531fV.s(28, "invalid quality: ", i));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        if (this.F == locationRequest.F) {
            long j = this.G;
            long j2 = locationRequest.G;
            if (j == j2 && this.H == locationRequest.H && this.I == locationRequest.I && this.f9677J == locationRequest.f9677J && this.K == locationRequest.K && this.L == locationRequest.L) {
                long j3 = this.M;
                if (j3 >= j) {
                    j = j3;
                }
                long j4 = locationRequest.M;
                if (j4 >= j2) {
                    j2 = j4;
                }
                if (j == j2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.F), Long.valueOf(this.G), Float.valueOf(this.L), Long.valueOf(this.M)});
    }

    public final String toString() {
        StringBuilder i = AbstractC2531fV.i("Request[");
        int i2 = this.F;
        i.append(i2 != 100 ? i2 != 102 ? i2 != 104 ? i2 != 105 ? "???" : "PRIORITY_NO_POWER" : "PRIORITY_LOW_POWER" : "PRIORITY_BALANCED_POWER_ACCURACY" : "PRIORITY_HIGH_ACCURACY");
        if (this.F != 105) {
            i.append(" requested=");
            i.append(this.G);
            i.append("ms");
        }
        i.append(" fastest=");
        i.append(this.H);
        i.append("ms");
        if (this.M > this.G) {
            i.append(" maxWait=");
            i.append(this.M);
            i.append("ms");
        }
        if (this.L > 0.0f) {
            i.append(" smallestDisplacement=");
            i.append(this.L);
            i.append("m");
        }
        long j = this.f9677J;
        if (j != Long.MAX_VALUE) {
            i.append(" expireIn=");
            i.append(j - SystemClock.elapsedRealtime());
            i.append("ms");
        }
        if (this.K != Integer.MAX_VALUE) {
            i.append(" num=");
            i.append(this.K);
        }
        i.append(']');
        return i.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        long j = this.G;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeLong(j);
        long j2 = this.H;
        AbstractC5758yO0.o(parcel, 3, 8);
        parcel.writeLong(j2);
        boolean z = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        long j3 = this.f9677J;
        AbstractC5758yO0.o(parcel, 5, 8);
        parcel.writeLong(j3);
        int i3 = this.K;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i3);
        float f = this.L;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeFloat(f);
        long j4 = this.M;
        AbstractC5758yO0.o(parcel, 8, 8);
        parcel.writeLong(j4);
        AbstractC5758yO0.n(parcel, l);
    }

    public final LocationRequest x(long j) {
        if (j >= 0) {
            this.G = j;
            if (!this.I) {
                this.H = (long) (((double) j) / 6.0d);
            }
            return this;
        }
        throw new IllegalArgumentException(AbstractC2531fV.u(38, "invalid interval: ", j));
    }
}
