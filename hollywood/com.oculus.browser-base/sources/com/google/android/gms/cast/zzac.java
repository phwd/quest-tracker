package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new DC1();
    public final float F;
    public final float G;
    public final float H;

    public zzac(float f, float f2, float f3) {
        this.F = f;
        this.G = f2;
        this.H = f3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzac)) {
            return false;
        }
        zzac zzac = (zzac) obj;
        return this.F == zzac.F && this.G == zzac.G && this.H == zzac.H;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.F), Float.valueOf(this.G), Float.valueOf(this.H)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        float f = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeFloat(f);
        float f2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeFloat(f2);
        float f3 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeFloat(f3);
        AbstractC5758yO0.n(parcel, l);
    }
}
