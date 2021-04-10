package com.oculus.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class b extends Enum implements Parcelable {
    private static b a = new b("USER_ACTION_GRANTED", 0);
    private static b b = new b("USER_ACTION_DENIED", 1);
    private static b c = new b("NO_LICENSE_APPLIED", 2);

    static {
        b[] bVarArr = {a, b, c};
    }

    private b(String str, int i) {
    }

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
