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
        public EvaluationResult createFromParcel(Parcel in) {
            return (EvaluationResult) in.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        public EvaluationResult[] newArray(int size) {
            return new EvaluationResult[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(this);
    }
}
