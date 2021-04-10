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

    public boolean isSuccess() {
        if (this.error == null) {
            return true;
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i) {
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

    public InstallerResult(String str, String str2, long j, InstallerResultError installerResultError) {
        this.installIdentifier = str;
        this.packageName = str2 == null ? "" : str2;
        this.timestampMs = j;
        this.error = installerResultError;
    }

    public static InstallerResult createForError(String str, long j, InstallerResultError installerResultError) {
        return new InstallerResult(str, "", j, installerResultError);
    }

    public static InstallerResult createForError(String str, String str2, long j, InstallerResultError installerResultError) {
        return new InstallerResult(str, str2, j, installerResultError);
    }

    public static InstallerResult createForSuccess(String str, long j) {
        return new InstallerResult(str, "", j, null);
    }

    public static InstallerResult createForSuccess(String str, String str2, long j) {
        return new InstallerResult(str, str2, j, null);
    }
}
