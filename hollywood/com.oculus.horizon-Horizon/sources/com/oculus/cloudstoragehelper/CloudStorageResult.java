package com.oculus.cloudstoragehelper;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class CloudStorageResult implements Parcelable {
    public static final Parcelable.Creator<CloudStorageResult> CREATOR = new Parcelable.Creator<CloudStorageResult>() {
        /* class com.oculus.cloudstoragehelper.CloudStorageResult.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final CloudStorageResult createFromParcel(Parcel parcel) {
            return new CloudStorageResult(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final CloudStorageResult[] newArray(int i) {
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

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(Locale.US, "%s[%s, progress = %f, error = %s, localTimestamp = %d, remoteTimestamp = %d]", "CloudStorageResult", this.resultType.name(), Float.valueOf(this.progress), this.error, Long.valueOf(this.conflictLocalTimestamp), Long.valueOf(this.conflictRemoteTimestamp));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resultType.name());
        parcel.writeString(this.error);
        parcel.writeFloat(this.progress);
        parcel.writeLong(this.conflictLocalTimestamp);
        parcel.writeLong(this.conflictRemoteTimestamp);
    }

    public CloudStorageResult(Parcel parcel) {
        this.resultType = SyncResultType.valueOf(parcel.readString());
        this.error = parcel.readString();
        this.progress = parcel.readFloat();
        this.conflictLocalTimestamp = parcel.readLong();
        this.conflictRemoteTimestamp = parcel.readLong();
    }

    public CloudStorageResult(SyncResultType syncResultType, String str, float f, long j, long j2) {
        this.resultType = syncResultType;
        this.error = str;
        this.progress = f;
        this.conflictLocalTimestamp = j;
        this.conflictRemoteTimestamp = j2;
    }
}
