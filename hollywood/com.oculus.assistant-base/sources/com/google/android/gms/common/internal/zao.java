package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0334Ro;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zao extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0334Ro();
    public final int A00;
    public final int A01;
    public final int A02;
    public final long A03;
    public final long A04;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A00);
        C0327Re.A02(parcel, 2, this.A01);
        C0327Re.A02(parcel, 3, this.A02);
        long j = this.A03;
        C0327Re.A03(parcel, 4, 8);
        parcel.writeLong(j);
        long j2 = this.A04;
        C0327Re.A03(parcel, 5, 8);
        parcel.writeLong(j2);
        C0327Re.A01(parcel, A002);
    }

    public zao(int i, int i2, int i3, long j, long j2) {
        this.A00 = i;
        this.A01 = i2;
        this.A02 = i3;
        this.A03 = j;
        this.A04 = j2;
    }
}
