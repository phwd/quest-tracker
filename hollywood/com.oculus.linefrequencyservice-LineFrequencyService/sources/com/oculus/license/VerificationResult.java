package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum VerificationResult implements Parcelable {
    USER_ACTION_GRANTED,
    USER_ACTION_DENIED;
    
    public static final Parcelable.Creator<VerificationResult> CREATOR = new Parcelable.Creator<VerificationResult>() {
        /* class com.oculus.license.VerificationResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public VerificationResult createFromParcel(Parcel in) {
            return (VerificationResult) in.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public VerificationResult[] newArray(int size) {
            return new VerificationResult[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this);
    }
}
