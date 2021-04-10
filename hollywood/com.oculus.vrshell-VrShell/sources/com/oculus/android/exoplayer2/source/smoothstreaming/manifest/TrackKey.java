package com.oculus.android.exoplayer2.source.smoothstreaming.manifest;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public final class TrackKey implements Parcelable, Comparable<TrackKey> {
    public static final Parcelable.Creator<TrackKey> CREATOR = new Parcelable.Creator<TrackKey>() {
        /* class com.oculus.android.exoplayer2.source.smoothstreaming.manifest.TrackKey.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public TrackKey createFromParcel(Parcel parcel) {
            return new TrackKey(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public TrackKey[] newArray(int i) {
            return new TrackKey[i];
        }
    };
    public final int streamElementIndex;
    public final int trackIndex;

    public int describeContents() {
        return 0;
    }

    public TrackKey(int i, int i2) {
        this.streamElementIndex = i;
        this.trackIndex = i2;
    }

    public String toString() {
        return this.streamElementIndex + "." + this.trackIndex;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.streamElementIndex);
        parcel.writeInt(this.trackIndex);
    }

    public int compareTo(@NonNull TrackKey trackKey) {
        int i = this.streamElementIndex - trackKey.streamElementIndex;
        return i == 0 ? this.trackIndex - trackKey.trackIndex : i;
    }
}
