package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum EvaluationResult implements Parcelable {
    USER_ACTION_GRANTED,
    USER_ACTION_DENIED,
    NO_LICENSE_APPLIED;
    
    public static final Parcelable.Creator<EvaluationResult> CREATOR = null;

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
