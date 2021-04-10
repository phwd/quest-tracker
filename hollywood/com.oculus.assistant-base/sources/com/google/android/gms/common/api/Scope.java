package com.google.android.gms.common.api;

import X.C0327Re;
import X.RG;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new RG();
    public final String A00;
    public final int A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.A00.equals(((Scope) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A01);
        C0327Re.A05(parcel, 2, this.A00);
        C0327Re.A01(parcel, A002);
    }

    public Scope(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.A01 = i;
            this.A00 = str;
            return;
        }
        throw new IllegalArgumentException(String.valueOf("scopeUri must not be null or empty"));
    }

    public final String toString() {
        return this.A00;
    }
}
