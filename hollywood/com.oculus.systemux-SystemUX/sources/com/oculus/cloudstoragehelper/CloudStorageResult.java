package com.oculus.cloudstoragehelper;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class CloudStorageResult implements Parcelable {
    public static final Parcelable.Creator<CloudStorageResult> CREATOR = new Parcelable.Creator<CloudStorageResult>() {
        /* class com.oculus.cloudstoragehelper.CloudStorageResult.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CloudStorageResult createFromParcel(Parcel parcel) {
            return new CloudStorageResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CloudStorageResult[] newArray(int i) {
            return new CloudStorageResult[i];
        }
    };
    public final long conflictLocalTimestamp;
    public final long conflictRemoteTimestamp;
    public final String error;
    public final float progress;
    public final SyncResultType resultType;

    public enum SyncResultType {
        PROGRESS,
        SUCCESS,
        FAILURE,
        CONFLICT
    }

    public int describeContents() {
        return 0;
    }

    public static CloudStorageResult forProgress(float f) {
        return new CloudStorageResult(SyncResultType.PROGRESS, null, f, -1, -1);
    }

    public static CloudStorageResult forSuccess() {
        return new CloudStorageResult(SyncResultType.SUCCESS, null, 1.0f, -1, -1);
    }

    public static CloudStorageResult forFailure(String str) {
        return new CloudStorageResult(SyncResultType.FAILURE, str, 1.0f, -1, -1);
    }

    public static CloudStorageResult forConflict(long j, long j2) {
        return new CloudStorageResult(SyncResultType.CONFLICT, null, 1.0f, j, j2);
    }

    private CloudStorageResult(SyncResultType syncResultType, String str, float f, long j, long j2) {
        this.resultType = syncResultType;
        this.error = str;
        this.progress = f;
        this.conflictLocalTimestamp = j;
        this.conflictRemoteTimestamp = j2;
    }

    public CloudStorageResult(Parcel parcel) {
        this.resultType = SyncResultType.valueOf(parcel.readString());
        this.error = parcel.readString();
        this.progress = parcel.readFloat();
        this.conflictLocalTimestamp = parcel.readLong();
        this.conflictRemoteTimestamp = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resultType.name());
        parcel.writeString(this.error);
        parcel.writeFloat(this.progress);
        parcel.writeLong(this.conflictLocalTimestamp);
        parcel.writeLong(this.conflictRemoteTimestamp);
    }

    public String toString() {
        return String.format(Locale.US, "%s[%s, progress = %f, error = %s, localTimestamp = %d, remoteTimestamp = %d]", CloudStorageResult.class.getSimpleName(), this.resultType.name(), Float.valueOf(this.progress), this.error, Long.valueOf(this.conflictLocalTimestamp), Long.valueOf(this.conflictRemoteTimestamp));
    }
}
