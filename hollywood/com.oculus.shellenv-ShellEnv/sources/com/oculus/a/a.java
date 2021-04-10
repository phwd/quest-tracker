package com.oculus.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class a extends Enum implements Parcelable {
    private static a a = new a("PurchasableItem", 0);
    private static a b = new a("Development", 1);
    private static a c = new a("OnDemand", 2);
    private static a d = new a("Provisional", 3);

    static {
        a[] aVarArr = {a, b, c, d};
    }

    private a(String str, int i) {
    }

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final String toString() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
