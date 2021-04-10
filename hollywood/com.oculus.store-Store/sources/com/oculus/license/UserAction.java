package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum UserAction implements Parcelable {
    INSTALL,
    LAUNCH;
    
    public static final Parcelable.Creator<UserAction> CREATOR = null;

    public static UserAction from(String action) {
        throw new RuntimeException("Stub!");
    }

    public String getValue() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }
}
