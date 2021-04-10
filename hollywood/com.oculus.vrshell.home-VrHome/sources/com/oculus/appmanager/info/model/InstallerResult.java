package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerResult implements Parcelable {
    public static final Parcelable.Creator<InstallerResult> CREATOR = new Parcelable.Creator<InstallerResult>() {
        /* class com.oculus.appmanager.info.model.InstallerResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InstallerResult createFromParcel(Parcel in) {
            return new InstallerResult(in);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerResult[] newArray(int size) {
            return new InstallerResult[size];
        }
    };
    public final InstallerResultError error;
    public final String installIdentifier;
    public final String packageName;
    public final long timestampMs;

    public static InstallerResult createForError(String installIdentifier2, long timestampMs2, InstallerResultError error2) {
        return new InstallerResult(installIdentifier2, "", timestampMs2, error2);
    }

    public static InstallerResult createForError(String installIdentifier2, String packageName2, long timestampMs2, InstallerResultError error2) {
        return new InstallerResult(installIdentifier2, packageName2, timestampMs2, error2);
    }

    public static InstallerResult createForSuccess(String installIdentifier2, long timestampMs2) {
        return new InstallerResult(installIdentifier2, "", timestampMs2, null);
    }

    public static InstallerResult createForSuccess(String installIdentifier2, String packageName2, long timestampMs2) {
        return new InstallerResult(installIdentifier2, packageName2, timestampMs2, null);
    }

    protected InstallerResult(String installIdentifier2, String packageName2, long timestampMs2, InstallerResultError error2) {
        this.installIdentifier = installIdentifier2;
        this.packageName = packageName2 == null ? "" : packageName2;
        this.timestampMs = timestampMs2;
        this.error = error2;
    }

    protected InstallerResult(Parcel in) {
        this.installIdentifier = in.readString();
        this.packageName = in.readString();
        this.timestampMs = in.readLong();
        int errorCode = in.readInt();
        if (errorCode != 0) {
            this.error = InstallerResultError.fromInt(errorCode);
        } else {
            this.error = null;
        }
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.installIdentifier);
        dest.writeString(this.packageName);
        dest.writeLong(this.timestampMs);
        dest.writeInt(this.error != null ? this.error.code : 0);
    }
}
