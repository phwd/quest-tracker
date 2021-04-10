package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0338Rs;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0338Rs();
    public Feature[] A00;
    public Bundle A01;
    public ConnectionTelemetryConfiguration A02;
    public int A03;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        Bundle bundle = this.A01;
        if (bundle != null) {
            int A003 = C0327Re.A00(parcel, 1);
            parcel.writeBundle(bundle);
            C0327Re.A01(parcel, A003);
        }
        C0327Re.A07(parcel, 2, this.A00, i);
        C0327Re.A02(parcel, 3, this.A03);
        C0327Re.A04(parcel, 4, this.A02, i);
        C0327Re.A01(parcel, A002);
    }

    public zzc() {
    }

    public zzc(Bundle bundle, Feature[] featureArr, int i, ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.A01 = bundle;
        this.A00 = featureArr;
        this.A03 = i;
        this.A02 = connectionTelemetryConfiguration;
    }
}
