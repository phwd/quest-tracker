package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzad extends AbstractSafeParcelable implements AM0 {
    public static final Parcelable.Creator CREATOR = new TC1();
    public final Status F;

    static {
        new zzad(Status.F);
    }

    public zzad(Status status) {
        this.F = status;
    }

    @Override // defpackage.AM0
    public final Status b() {
        return this.F;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 1, this.F, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
