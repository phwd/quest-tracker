package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3182jG1();
    public int F;
    public int G;
    public int H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9685J;
    public float K;

    public zze() {
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
        boolean z = this.I;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.f9685J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(z2 ? 1 : 0);
        float f = this.K;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeFloat(f);
        AbstractC5758yO0.n(parcel, l);
    }

    public zze(int i, int i2, int i3, boolean z, boolean z2, float f) {
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.I = z;
        this.f9685J = z2;
        this.K = f;
    }
}
