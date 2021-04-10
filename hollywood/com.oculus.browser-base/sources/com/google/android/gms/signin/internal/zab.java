package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zab extends AbstractSafeParcelable implements AM0 {
    public static final Parcelable.Creator CREATOR = new CA1();
    public final int F;
    public int G;
    public Intent H;

    public zab(int i, int i2, Intent intent) {
        this.F = i;
        this.G = i2;
        this.H = intent;
    }

    @Override // defpackage.AM0
    public final Status b() {
        if (this.G == 0) {
            return Status.F;
        }
        return Status.I;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.f(parcel, 3, this.H, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
