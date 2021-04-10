package com.google.android.gms.signin.internal;

import X.AbstractC0312Qj;
import X.C0327Re;
import X.Sd;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

public final class zai extends AbstractSafeParcelable implements AbstractC0312Qj {
    public static final Parcelable.Creator CREATOR = new Sd();
    public final String A00;
    public final List A01;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        List<String> list = this.A01;
        if (list != null) {
            int A003 = C0327Re.A00(parcel, 1);
            parcel.writeStringList(list);
            C0327Re.A01(parcel, A003);
        }
        C0327Re.A05(parcel, 2, this.A00);
        C0327Re.A01(parcel, A002);
    }

    public zai(List list, String str) {
        this.A01 = list;
        this.A00 = str;
    }
}
