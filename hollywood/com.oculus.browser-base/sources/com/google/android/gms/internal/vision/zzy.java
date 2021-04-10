package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzy extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4217pJ1();
    public final int F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final float f9676J;

    public zzy(int i, int i2, int i3, int i4, float f) {
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.I = i4;
        this.f9676J = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i3);
        int i4 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i4);
        int i5 = this.I;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(i5);
        float f = this.f9676J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeFloat(f);
        AbstractC5758yO0.n(parcel, l);
    }
}
