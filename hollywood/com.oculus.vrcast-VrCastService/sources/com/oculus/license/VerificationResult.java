package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum VerificationResult implements Parcelable {
    USER_ACTION_GRANTED,
    USER_ACTION_DENIED;
    
    public static final Parcelable.Creator<VerificationResult> CREATOR = new Parcelable.Creator<VerificationResult>() {
        /* class com.oculus.license.VerificationResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public VerificationResult createFromParcel(Parcel parcel) {
            return (VerificationResult) parcel.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public VerificationResult[] newArray(int i) {
            return new VerificationResult[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
