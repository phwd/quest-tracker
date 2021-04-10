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
    
    public static final Parcelable.Creator<YadiStatus> CREATOR = null;

    public void writeToParcel(Parcel p, int flags) {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }
}
