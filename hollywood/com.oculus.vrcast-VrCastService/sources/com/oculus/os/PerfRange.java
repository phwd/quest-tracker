package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public class PerfRange implements Parcelable {
    public static final Parcelable.Creator<PerfRange> CREATOR = new Parcelable.Creator<PerfRange>() {
        /* class com.oculus.os.PerfRange.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public PerfRange createFromParcel(Parcel parcel) {
            return new PerfRange(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PerfRange[] newArray(int i) {
            return new PerfRange[i];
        }
    };
    private int mMaxLevel;
    private int mMinLevel;

    public int describeContents() {
        return 0;
    }

    public PerfRange() {
        this.mMinLevel = -1;
        this.mMaxLevel = -1;
    }

    public PerfRange(Parcel parcel) {
        this.mMinLevel = parcel.readInt();
        this.mMaxLevel = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMinLevel);
        parcel.writeInt(this.mMaxLevel);
    }
}
