package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerResult implements Parcelable {
    public static final Parcelable.Creator<InstallerResult> CREATOR = new Parcelable.Creator<InstallerResult>() {
        /* class com.oculus.appmanager.info.model.InstallerResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerResult createFromParcel(Parcel parcel) {
            return new InstallerResult(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerResult[] newArray(int i) {
            return new InstallerResult[i];
        }
    };
    public final InstallerResultError error;
    public final String installIdentifier;
    public final String packageName;
    public final long timestampMs;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        parcel.writeString(this.installIdentifier);
        parcel.writeString(this.packageName);
        parcel.writeLong(this.timestampMs);
        InstallerResultError installerResultError = this.error;
        if (installerResultError != null) {
            i2 = installerResultError.code;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }

    public InstallerResult(Parcel parcel) {
        InstallerResultError installerResultError;
        this.installIdentifier = parcel.readString();
        this.packageName = parcel.readString();
        this.timestampMs = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt != 0) {
            installerResultError = InstallerResultError.fromInt(readInt);
        } else {
            installerResultError = null;
        }
        this.error = installerResultError;
    }
}
