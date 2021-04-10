package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerUpdateResult implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateResult> CREATOR = new Parcelable.Creator<InstallerUpdateResult>() {
        /* class com.oculus.appmanager.info.model.InstallerUpdateResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateResult createFromParcel(Parcel parcel) {
            return new InstallerUpdateResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateResult[] newArray(int i) {
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

    public int describeContents() {
        return 0;
    }

    public static InstallerUpdateResult createForQueuedState(String str) {
        return new InstallerUpdateResult(str, System.currentTimeMillis(), AppStatus.DOWNLOAD_QUEUED, -1, -1);
    }

    public static InstallerUpdateResult createForDownloadingState(String str) {
        return new InstallerUpdateResult(str, System.currentTimeMillis(), AppStatus.DOWNLOADING, -1, 0);
    }

    public static InstallerUpdateResult createForDownloadProgress(String str, long j, long j2) {
        return new InstallerUpdateResult(str, System.currentTimeMillis(), AppStatus.DOWNLOADING, j, j2);
    }

    public static InstallerUpdateResult createForInstallingState(String str) {
        return new InstallerUpdateResult(str, System.currentTimeMillis(), AppStatus.INSTALLING, -1, -1);
    }

    protected InstallerUpdateResult(String str, long j, AppStatus appStatus, long j2, long j3) {
        this.mIstallIdentifier = str;
        this.mTimestampMs = j;
        this.mAppStatus = appStatus;
        this.mTotalBytes = j2;
        this.mDownloadedBytes = j3;
    }

    protected InstallerUpdateResult(Parcel parcel) {
        String readString = parcel.readString();
        this.mIstallIdentifier = readString == null ? "" : readString;
        this.mTimestampMs = parcel.readLong();
        this.mAppStatus = AppStatus.values()[parcel.readInt()];
        this.mTotalBytes = parcel.readLong();
        this.mDownloadedBytes = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIstallIdentifier);
        parcel.writeLong(this.mTimestampMs);
        parcel.writeInt(this.mAppStatus.ordinal());
        parcel.writeLong(this.mTotalBytes);
        parcel.writeLong(this.mDownloadedBytes);
    }
}
