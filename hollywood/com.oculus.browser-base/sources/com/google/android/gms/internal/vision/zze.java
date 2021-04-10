package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4720sG1();
    public int F;

    public zze() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.n(parcel, l);
    }

    public zze(int i) {
        this.F = i;
    }
}
