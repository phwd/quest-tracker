package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzn extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C1993cI1();
    public int F;
    public int G;
    public int H;
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public int f9675J;

    public zzn() {
    }

    public static zzn x(GT gt) {
        zzn zzn = new zzn();
        FT ft = gt.f8091a;
        zzn.F = ft.f8017a;
        zzn.G = ft.b;
        zzn.f9675J = 0;
        Objects.requireNonNull(ft);
        zzn.H = 0;
        Objects.requireNonNull(gt.f8091a);
        zzn.I = 0;
        return zzn;
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
        long j = this.I;
        AbstractC5758yO0.o(parcel, 5, 8);
        parcel.writeLong(j);
        int i5 = this.f9675J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(i5);
        AbstractC5758yO0.n(parcel, l);
    }

    public zzn(int i, int i2, int i3, long j, int i4) {
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.I = j;
        this.f9675J = i4;
    }
}
