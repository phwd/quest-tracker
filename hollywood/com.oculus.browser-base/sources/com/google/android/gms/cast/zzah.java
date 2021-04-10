package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C2319eD1();
    public int F;

    public zzah() {
        this.F = 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzah) && this.F == ((zzah) obj).F;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.F)});
    }

    public final String toString() {
        int i = this.F;
        return String.format("joinOptions(connectionType=%s)", i != 0 ? i != 2 ? "UNKNOWN" : "INVISIBLE" : "STRONG");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.n(parcel, l);
    }

    public zzah(int i) {
        this.F = i;
    }
}
