package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerUpdateResult implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateResult> CREATOR = new Parcelable.Creator<InstallerUpdateResult>() {
        /* class com.oculus.appmanager.info.model.InstallerUpdateResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerUpdateResult createFromParcel(Parcel parcel) {
            return new InstallerUpdateResult(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InstallerUpdateResult[] newArray(int i) {
            return new InstallerUpdateResult[i];
        }
    };
    public final AppStatus mAppStatus;
    public final long mDownloadedBytes;
    public final String mIstallIdentifier;
    public final long mTimestampMs;
    public final long mTotalBytes;

    public enum AppStatus {
        DOWNLOAD_QUEUED,
        DOWNLOADING,
        INSTALLING
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIstallIdentifier);
        parcel.writeLong(this.mTimestampMs);
        parcel.writeInt(this.mAppStatus.ordinal());
        parcel.writeLong(this.mTotalBytes);
        parcel.writeLong(this.mDownloadedBytes);
    }

    public InstallerUpdateResult(Parcel parcel) {
        String readString = parcel.readString();
        this.mIstallIdentifier = readString == null ? "" : readString;
        this.mTimestampMs = parcel.readLong();
        this.mAppStatus = AppStatus.values()[parcel.readInt()];
        this.mTotalBytes = parcel.readLong();
        this.mDownloadedBytes = parcel.readLong();
    }
}
