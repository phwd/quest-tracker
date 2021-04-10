package com.oculus.library.model;

import X.C0454Zi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public final class InputDevice implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0454Zi();
    public String A00;
    public String A01;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(Locale.US, "{InputDevice tag=%s name=%s}", this.A01, this.A00);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A01);
        parcel.writeString(this.A00);
    }

    public InputDevice(Parcel parcel) {
        this.A01 = parcel.readString();
        this.A00 = parcel.readString();
    }

    public InputDevice(String str, String str2) {
        this.A01 = str;
        this.A00 = str2;
    }
}
