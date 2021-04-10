package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum YadiStatus implements Parcelable {
    Complete,
    Downloading,
    Installing,
    Invalid,
    Paused,
    Queued,
    Terminated,
    Verifying;
    
    public static final Parcelable.Creator CREATOR = null;

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
