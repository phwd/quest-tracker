package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0329Rj;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

public final class zaaa extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0329Rj();
    public List A00;
    public final int A01;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A01);
        C0327Re.A06(parcel, 2, this.A00);
        C0327Re.A01(parcel, A002);
    }

    public zaaa(int i, List list) {
        this.A01 = i;
        this.A00 = list;
    }
}
