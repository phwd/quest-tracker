package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum VerificationResult implements Parcelable {
    USER_ACTION_GRANTED,
    USER_ACTION_DENIED;
    
    public static final Parcelable.Creator<VerificationResult> CREATOR = null;

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
