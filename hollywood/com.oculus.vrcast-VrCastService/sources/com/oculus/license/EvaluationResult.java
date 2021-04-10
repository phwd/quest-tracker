package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;

public enum EvaluationResult implements Parcelable {
    USER_ACTION_GRANTED,
    USER_ACTION_DENIED,
    NO_LICENSE_APPLIED;
    
    public static final Parcelable.Creator<EvaluationResult> CREATOR = new Parcelable.Creator<EvaluationResult>() {
        /* class com.oculus.license.EvaluationResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public EvaluationResult createFromParcel(Parcel parcel) {
            return (EvaluationResult) parcel.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public EvaluationResult[] newArray(int i) {
            return new EvaluationResult[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
