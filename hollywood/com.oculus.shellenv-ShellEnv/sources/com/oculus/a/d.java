package com.oculus.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class d extends Enum implements Parcelable {
    private static d a = new d("INSTALL", 0);
    private static d b = new d("LAUNCH", 1);

    static {
        d[] dVarArr = {a, b};
    }

    private d(String str, int i) {
    }

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
