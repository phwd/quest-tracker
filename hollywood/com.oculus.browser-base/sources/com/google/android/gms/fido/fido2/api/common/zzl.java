package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new VH1();
    public final long F;
    public final byte[] G;
    public final byte[] H;
    public final byte[] I;

    public zzl(long j, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.F = j;
        Objects.requireNonNull(bArr, "null reference");
        this.G = bArr;
        Objects.requireNonNull(bArr2, "null reference");
        this.H = bArr2;
        Objects.requireNonNull(bArr3, "null reference");
        this.I = bArr3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzl)) {
            return false;
        }
        zzl zzl = (zzl) obj;
        if (this.F != zzl.F || !Arrays.equals(this.G, zzl.G) || !Arrays.equals(this.H, zzl.H) || !Arrays.equals(this.I, zzl.I)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.F), this.G, this.H, this.I});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        long j = this.F;
        AbstractC5758yO0.o(parcel, 1, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.b(parcel, 2, this.G, false);
        AbstractC5758yO0.b(parcel, 3, this.H, false);
        AbstractC5758yO0.b(parcel, 4, this.I, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
