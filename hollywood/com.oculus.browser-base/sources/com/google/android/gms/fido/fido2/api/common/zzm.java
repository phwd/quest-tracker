package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C2164dI1();
    public final List F;

    public zzm(List list) {
        Objects.requireNonNull(list, "null reference");
        this.F = list;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        if (!this.F.containsAll(zzm.F) || !zzm.F.containsAll(this.F)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{new HashSet(this.F)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.k(parcel, 1, this.F, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
