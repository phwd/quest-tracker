package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzan extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new HD1();
    public final zzai[] F;
    public final zzy G;
    public final zzy H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final float f9674J;
    public final String K;
    public final boolean L;

    public zzan(zzai[] zzaiArr, zzy zzy, zzy zzy2, String str, float f, String str2, boolean z) {
        this.F = zzaiArr;
        this.G = zzy;
        this.H = zzy2;
        this.I = str;
        this.f9674J = f;
        this.K = str2;
        this.L = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.j(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.f(parcel, 4, this.H, i, false);
        AbstractC5758yO0.g(parcel, 5, this.I, false);
        float f = this.f9674J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeFloat(f);
        AbstractC5758yO0.g(parcel, 7, this.K, false);
        boolean z = this.L;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }
}
