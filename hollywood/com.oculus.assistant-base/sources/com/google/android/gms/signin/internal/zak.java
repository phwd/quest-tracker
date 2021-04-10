package com.google.android.gms.signin.internal;

import X.C0327Re;
import X.C0344Sf;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zau;

public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0344Sf();
    public final ConnectionResult A00;
    public final zau A01;
    public final int A02;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A02);
        C0327Re.A04(parcel, 2, this.A00, i);
        C0327Re.A04(parcel, 3, this.A01, i);
        C0327Re.A01(parcel, A002);
    }

    public zak(int i, ConnectionResult connectionResult, zau zau) {
        this.A02 = i;
        this.A00 = connectionResult;
        this.A01 = zau;
    }
}
