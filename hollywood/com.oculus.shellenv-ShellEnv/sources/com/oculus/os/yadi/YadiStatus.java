package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;

public enum YadiStatus implements Parcelable {
    Invalid,
    Queued,
    Downloading,
    Verifying,
    Installing,
    Paused,
    Complete,
    Terminated;
    
    public static final Parcelable.Creator CREATOR = null;

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
