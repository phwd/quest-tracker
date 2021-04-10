package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public class PerfRange implements Parcelable {
    public static final Parcelable.Creator<PerfRange> CREATOR = new Parcelable.Creator<PerfRange>() {
        /* class com.oculus.os.PerfRange.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public PerfRange createFromParcel(Parcel source) {
            return new PerfRange(source);
        }

        @Override // android.os.Parcelable.Creator
        public PerfRange[] newArray(int size) {
            return new PerfRange[size];
        }
    };
    private int mMaxLevel;
    private int mMinLevel;

    public PerfRange() {
        this.mMinLevel = -1;
        this.mMaxLevel = -1;
    }

    public PerfRange(int min, int max) {
        this.mMinLevel = min;
        this.mMaxLevel = max;
    }

    public PerfRange(Parcel p) {
        this.mMinLevel = p.readInt();
        this.mMaxLevel = p.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mMinLevel);
        dest.writeInt(this.mMaxLevel);
    }

    public int getMinLevel() {
        return this.mMinLevel;
    }

    public int getMaxLevel() {
        return this.mMaxLevel;
    }
}
