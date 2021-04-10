package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.common.build.BuildConfig;

public class InstallerResult implements Parcelable {
    public static final Parcelable.Creator<InstallerResult> CREATOR = new Parcelable.Creator<InstallerResult>() {
        /* class com.oculus.appmanager.info.model.InstallerResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ InstallerResult[] newArray(int i) {
            return new InstallerResult[i];
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ InstallerResult createFromParcel(Parcel parcel) {
            return new InstallerResult(parcel);
        }
    };
    public final InstallerResultError error;
    public final String installIdentifier;
    public final String packageName;
    public final long timestampMs;

    public int describeContents() {
        return 0;
    }

    public static InstallerResult createForError(String str, long j, InstallerResultError installerResultError) {
        return new InstallerResult(str, BuildConfig.PROVIDER_SUFFIX, j, installerResultError);
    }

    private InstallerResult(String str, String str2, long j, InstallerResultError installerResultError) {
        this.installIdentifier = str;
        this.packageName = str2;
        this.timestampMs = j;
        this.error = installerResultError;
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

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.installIdentifier);
        parcel.writeString(this.packageName);
        parcel.writeLong(this.timestampMs);
        InstallerResultError installerResultError = this.error;
        parcel.writeInt(installerResultError != null ? installerResultError.code : 0);
    }
}
