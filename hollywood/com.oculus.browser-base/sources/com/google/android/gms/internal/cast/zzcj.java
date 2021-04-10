package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzcj extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C2496fF1();
    public String F;

    public zzcj(String str) {
        this.F = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcj)) {
            return false;
        }
        return GF1.a(this.F, ((zzcj) obj).F);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.F});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
