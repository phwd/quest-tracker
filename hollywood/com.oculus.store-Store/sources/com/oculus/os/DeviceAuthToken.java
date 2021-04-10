package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public final class DeviceAuthToken implements Parcelable {
    public static final Parcelable.Creator<DeviceAuthToken> CREATOR = null;

    DeviceAuthToken(String value, long expirationTime) {
        throw new RuntimeException("Stub!");
    }

    public String value() {
        throw new RuntimeException("Stub!");
    }

    public long expirationTime() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object other) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }
}
