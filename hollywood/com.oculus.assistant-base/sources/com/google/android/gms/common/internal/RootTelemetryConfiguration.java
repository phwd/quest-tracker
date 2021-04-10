package com.google.android.gms.common.internal;

import X.C0327Re;
import X.S1;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class RootTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new S1();
    public final int A00;
    public final int A01;
    public final int A02;
    public final boolean A03;
    public final boolean A04;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A00);
        boolean z = this.A03;
        C0327Re.A03(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.A04;
        C0327Re.A03(parcel, 3, 4);
        parcel.writeInt(z2 ? 1 : 0);
        C0327Re.A02(parcel, 4, this.A01);
        C0327Re.A02(parcel, 5, this.A02);
        C0327Re.A01(parcel, A002);
    }

    public RootTelemetryConfiguration(int i, boolean z, boolean z2, int i2, int i3) {
        this.A00 = i;
        this.A03 = z;
        this.A04 = z2;
        this.A01 = i2;
        this.A02 = i3;
    }
}
