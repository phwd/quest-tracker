package com.google.android.gms.common;

import X.C0327Re;
import X.RX;
import X.SA;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.acra.AppComponentStats;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

public final class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new SA();
    public final String A00;
    public final int A01;
    public final long A02;

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A00, Long.valueOf(A00())});
    }

    public final long A00() {
        long j = this.A02;
        if (j == -1) {
            return (long) this.A01;
        }
        return j;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            String str = this.A00;
            if (str == null ? feature.A00 == null : str.equals(feature.A00)) {
                if (A00() == feature.A00()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        RX rx = new RX(this);
        rx.A00(AppComponentStats.ATTRIBUTE_NAME, this.A00);
        rx.A00("version", Long.valueOf(A00()));
        return rx.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A05(parcel, 1, this.A00);
        C0327Re.A02(parcel, 2, this.A01);
        long A003 = A00();
        C0327Re.A03(parcel, 3, 8);
        parcel.writeLong(A003);
        C0327Re.A01(parcel, A002);
    }

    public Feature(String str, int i, long j) {
        this.A00 = str;
        this.A01 = i;
        this.A02 = j;
    }

    public Feature() {
        this.A00 = "CLIENT_TELEMETRY";
        this.A02 = 1;
        this.A01 = -1;
    }
}
