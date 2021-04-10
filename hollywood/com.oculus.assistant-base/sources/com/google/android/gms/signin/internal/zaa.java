package com.google.android.gms.signin.internal;

import X.AbstractC0312Qj;
import X.C0327Re;
import X.C0342Sa;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zaa extends AbstractSafeParcelable implements AbstractC0312Qj {
    public static final Parcelable.Creator CREATOR = new C0342Sa();
    public int A00;
    public Intent A01;
    public final int A02;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A02);
        C0327Re.A02(parcel, 2, this.A00);
        C0327Re.A04(parcel, 3, this.A01, i);
        C0327Re.A01(parcel, A002);
    }

    public zaa() {
        this(2, 0, null);
    }

    public zaa(int i, int i2, Intent intent) {
        this.A02 = i;
        this.A00 = i2;
        this.A01 = intent;
    }
}
