package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ZC1();
    public final zzan[] F;
    public final zzy G;
    public final zzy H;
    public final zzy I;

    /* renamed from: J  reason: collision with root package name */
    public final String f9673J;
    public final float K;
    public final String L;
    public final int M;
    public final boolean N;
    public final int O;
    public final int P;

    public zzae(zzan[] zzanArr, zzy zzy, zzy zzy2, zzy zzy3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.F = zzanArr;
        this.G = zzy;
        this.H = zzy2;
        this.I = zzy3;
        this.f9673J = str;
        this.K = f;
        this.L = str2;
        this.M = i;
        this.N = z;
        this.O = i2;
        this.P = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.j(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.f(parcel, 4, this.H, i, false);
        AbstractC5758yO0.f(parcel, 5, this.I, i, false);
        AbstractC5758yO0.g(parcel, 6, this.f9673J, false);
        float f = this.K;
        AbstractC5758yO0.o(parcel, 7, 4);
        parcel.writeFloat(f);
        AbstractC5758yO0.g(parcel, 8, this.L, false);
        int i2 = this.M;
        AbstractC5758yO0.o(parcel, 9, 4);
        parcel.writeInt(i2);
        boolean z = this.N;
        AbstractC5758yO0.o(parcel, 10, 4);
        parcel.writeInt(z ? 1 : 0);
        int i3 = this.O;
        AbstractC5758yO0.o(parcel, 11, 4);
        parcel.writeInt(i3);
        int i4 = this.P;
        AbstractC5758yO0.o(parcel, 12, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.n(parcel, l);
    }
}
