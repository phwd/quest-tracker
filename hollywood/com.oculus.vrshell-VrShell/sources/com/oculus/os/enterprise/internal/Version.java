package com.oculus.os.enterprise.internal;

import android.os.Parcel;
import android.os.Parcelable;

public enum Version implements Parcelable {
    VERSION_0,
    VERSION_1,
    VERSION_2,
    VERSION_3,
    VERSION_4,
    VERSION_5,
    VERSION_6,
    VERSION_7,
    VERSION_8,
    VERSION_9,
    VERSION_10;
    
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator<Version>() {
        /* class com.oculus.os.enterprise.internal.Version.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Version createFromParcel(Parcel parcel) {
            return Version.values()[parcel.readInt()];
        }

        @Override // android.os.Parcelable.Creator
        public Version[] newArray(int i) {
            return new Version[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public boolean isAtLeast(Version version) {
        return ordinal() >= version.ordinal();
    }

    public static Version latest() {
        Version[] values = values();
        return values[values.length - 1];
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
