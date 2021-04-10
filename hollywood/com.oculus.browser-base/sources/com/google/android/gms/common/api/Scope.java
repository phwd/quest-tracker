package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C4196pC1();
    public final int F;
    public final String G;

    public Scope(int i, String str) {
        SE0.g(str, "scopeUri must not be null or empty");
        this.F = i;
        this.G = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.G.equals(((Scope) obj).G);
    }

    public final int hashCode() {
        return this.G.hashCode();
    }

    public final String toString() {
        return this.G;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public Scope(String str) {
        SE0.g(str, "scopeUri must not be null or empty");
        this.F = 1;
        this.G = str;
    }
}
