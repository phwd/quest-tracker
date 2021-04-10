package com.google.android.gms.common.internal;

import X.C0327Re;
import X.Rt;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new Rt();
    public final int A00;
    public final RootTelemetryConfiguration A01;
    public final boolean A02;
    public final boolean A03;
    public final int[] A04;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A04(parcel, 1, this.A01, i);
        boolean z = this.A02;
        C0327Re.A03(parcel, 2, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.A03;
        C0327Re.A03(parcel, 3, 4);
        parcel.writeInt(z2 ? 1 : 0);
        int[] iArr = this.A04;
        if (iArr != null) {
            int A003 = C0327Re.A00(parcel, 4);
            parcel.writeIntArray(iArr);
            C0327Re.A01(parcel, A003);
        }
        C0327Re.A02(parcel, 5, this.A00);
        C0327Re.A01(parcel, A002);
    }

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration rootTelemetryConfiguration, boolean z, boolean z2, int[] iArr, int i) {
        this.A01 = rootTelemetryConfiguration;
        this.A02 = z;
        this.A03 = z2;
        this.A04 = iArr;
        this.A00 = i;
    }
}
