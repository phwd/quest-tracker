package com.oculus.video.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.android.exoplayer2.metadata.Metadata;
import java.util.Arrays;

public class CameraMotionData implements Metadata.Entry {
    public static final Parcelable.Creator<CameraMotionData> CREATOR = new Parcelable.Creator<CameraMotionData>() {
        /* class com.oculus.video.metadata.CameraMotionData.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CameraMotionData createFromParcel(Parcel parcel) {
            return new CameraMotionData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CameraMotionData[] newArray(int i) {
            return new CameraMotionData[i];
        }
    };
    public final float[] data;
    private int hashCode;
    public final long timeUs;

    public int describeContents() {
        return 0;
    }

    public CameraMotionData(long j, float[] fArr) {
        this.timeUs = j;
        this.data = fArr;
    }

    CameraMotionData(Parcel parcel) {
        this.timeUs = parcel.readLong();
        this.data = parcel.createFloatArray();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            long j = this.timeUs;
            this.hashCode = ((527 + ((int) (j ^ (j >>> 32)))) * 31) + Arrays.hashCode(this.data);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.data, ((CameraMotionData) obj).data);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloatArray(this.data);
    }
}
