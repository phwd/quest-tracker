package com.oculus.appmanager.info.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.common.build.BuildConfig;

public class InstallerUpdateResult implements Parcelable {
    public static final Parcelable.Creator<InstallerUpdateResult> CREATOR = new Parcelable.Creator<InstallerUpdateResult>() {
        /* class com.oculus.appmanager.info.model.InstallerUpdateResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ InstallerUpdateResult[] newArray(int i) {
            return new InstallerUpdateResult[i];
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ InstallerUpdateResult createFromParcel(Parcel parcel) {
            return new InstallerUpdateResult(parcel);
        }
    };
    public final int mAppStatus$6bc80bf2;
    public final long mDownloadedBytes;
    public final String mIstallIdentifier;
    public final long mTimestampMs;
    public final long mTotalBytes;

    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class AppStatus extends Enum<AppStatus> {
        private static final /* synthetic */ int[] $VALUES$4e010e57 = {DOWNLOAD_QUEUED$6bc80bf2, DOWNLOADING$6bc80bf2, INSTALLING$6bc80bf2};
        public static final int DOWNLOADING$6bc80bf2 = 2;
        public static final int DOWNLOAD_QUEUED$6bc80bf2 = 1;
        public static final int INSTALLING$6bc80bf2 = 3;

        public static int[] values$745ac908() {
            return (int[]) $VALUES$4e010e57.clone();
        }
    }

    protected InstallerUpdateResult(Parcel parcel) {
        String readString = parcel.readString();
        this.mIstallIdentifier = readString == null ? BuildConfig.PROVIDER_SUFFIX : readString;
        this.mTimestampMs = parcel.readLong();
        this.mAppStatus$6bc80bf2 = AppStatus.values$745ac908()[parcel.readInt()];
        this.mTotalBytes = parcel.readLong();
        this.mDownloadedBytes = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIstallIdentifier);
        parcel.writeLong(this.mTimestampMs);
        parcel.writeInt(this.mAppStatus$6bc80bf2 - 1);
        parcel.writeLong(this.mTotalBytes);
        parcel.writeLong(this.mDownloadedBytes);
    }
}
