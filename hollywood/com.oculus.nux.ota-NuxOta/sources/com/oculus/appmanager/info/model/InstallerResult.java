package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerResult implements Parcelable {
    public static final Parcelable.Creator<InstallerResult> CREATOR = new Parcelable.Creator<InstallerResult>() {
        /* class com.oculus.appmanager.info.model.InstallerResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InstallerResult createFromParcel(Parcel parcel) {
            return new InstallerResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerResult[] newArray(int i) {
            return new InstallerResult[i];
        }
    };
    public final InstallerResultError error;
    public final String installIdentifier;
    public final String packageName;
    public final long timestampMs;

    public int describeContents() {
        return 0;
    }

    protected InstallerResult(Parcel parcel) {
        this.installIdentifier = parcel.readString();
        this.packageName = parcel.readString();
        this.timestampMs = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt != 0) {
            this.error = InstallerResultError.fromInt(readInt);
        } else {
            this.error = null;
        }
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.installIdentifier);
        parcel.writeString(this.packageName);
        parcel.writeLong(this.timestampMs);
        InstallerResultError installerResultError = this.error;
        parcel.writeInt(installerResultError != null ? installerResultError.code : 0);
    }
}
