package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InstallerUpdateResult implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateResult> CREATOR = new Parcelable.Creator<InstallerUpdateResult>() {
        /* class com.oculus.appmanager.info.model.InstallerUpdateResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateResult createFromParcel(Parcel in) {
            return new InstallerUpdateResult(in);
        }

        @Override // android.os.Parcelable.Creator
        public InstallerUpdateResult[] newArray(int size) {
            return new InstallerUpdateResult[size];
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

    public static InstallerUpdateResult createForQueuedState(String installIdentifier) {
        return new InstallerUpdateResult(installIdentifier, System.currentTimeMillis(), AppStatus.DOWNLOAD_QUEUED, -1, -1);
    }

    public static InstallerUpdateResult createForDownloadingState(String installIdentifier) {
        return new InstallerUpdateResult(installIdentifier, System.currentTimeMillis(), AppStatus.DOWNLOADING, -1, 0);
    }

    public static InstallerUpdateResult createForDownloadProgress(String installIdentifier, long totalBytes, long downloadedBytes) {
        return new InstallerUpdateResult(installIdentifier, System.currentTimeMillis(), AppStatus.DOWNLOADING, totalBytes, downloadedBytes);
    }

    public static InstallerUpdateResult createForInstallingState(String installIdentifier) {
        return new InstallerUpdateResult(installIdentifier, System.currentTimeMillis(), AppStatus.INSTALLING, -1, -1);
    }

    protected InstallerUpdateResult(String installIdentifier, long timestampMs, AppStatus appStatus, long totalBytes, long downloadedBytes) {
        this.mIstallIdentifier = installIdentifier;
        this.mTimestampMs = timestampMs;
        this.mAppStatus = appStatus;
        this.mTotalBytes = totalBytes;
        this.mDownloadedBytes = downloadedBytes;
    }

    protected InstallerUpdateResult(Parcel in) {
        String identifier = in.readString();
        this.mIstallIdentifier = identifier == null ? "" : identifier;
        this.mTimestampMs = in.readLong();
        this.mAppStatus = AppStatus.values()[in.readInt()];
        this.mTotalBytes = in.readLong();
        this.mDownloadedBytes = in.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mIstallIdentifier);
        dest.writeLong(this.mTimestampMs);
        dest.writeInt(this.mAppStatus.ordinal());
        dest.writeLong(this.mTotalBytes);
        dest.writeLong(this.mDownloadedBytes);
    }
}
