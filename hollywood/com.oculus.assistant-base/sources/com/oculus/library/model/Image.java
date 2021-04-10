package com.oculus.library.model;

import X.EnumC0453Zg;
import X.Zf;
import android.os.Parcel;
import android.os.Parcelable;

public final class Image implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Zf();
    public final int A00;
    public final int A01;
    public final EnumC0453Zg A02;
    public final String A03;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A02.name());
        parcel.writeString(this.A03);
        parcel.writeInt(this.A01);
        parcel.writeInt(this.A00);
    }

    public Image(Parcel parcel) {
        this.A02 = EnumC0453Zg.valueOf(parcel.readString());
        this.A03 = parcel.readString();
        this.A01 = parcel.readInt();
        this.A00 = parcel.readInt();
    }

    public Image(EnumC0453Zg zg, String str, int i, int i2) {
        this.A02 = zg;
        this.A03 = str;
        this.A01 = i;
        this.A00 = i2;
    }
}
