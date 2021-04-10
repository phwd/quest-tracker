package com.google.android.gms.signin.internal;

import X.C0327Re;
import X.C0343Se;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zat;

public final class zaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0343Se();
    public final int A00;
    public final zat A01;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A00);
        C0327Re.A04(parcel, 2, this.A01, i);
        C0327Re.A01(parcel, A002);
    }

    public zaj(int i, zat zat) {
        this.A00 = i;
        this.A01 = zat;
    }
}
