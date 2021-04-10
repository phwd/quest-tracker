package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new PC1();
    public final zzac F;
    public final zzac G;

    public zzae(zzac zzac, zzac zzac2) {
        this.F = zzac;
        this.G = zzac2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzae = (zzae) obj;
        return GF1.a(this.F, zzae.F) && GF1.a(this.G, zzae.G);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
