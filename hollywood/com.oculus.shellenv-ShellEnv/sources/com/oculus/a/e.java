package com.oculus.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class e extends Enum implements Parcelable {
    private static e a = new e("USER_ACTION_GRANTED", 0);
    private static e b = new e("USER_ACTION_DENIED", 1);

    static {
        e[] eVarArr = {a, b};
    }

    private e(String str, int i) {
    }

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
