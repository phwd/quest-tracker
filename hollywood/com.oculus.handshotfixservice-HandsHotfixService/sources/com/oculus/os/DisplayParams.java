package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public class DisplayParams implements Parcelable {
    public static final Parcelable.Creator<DisplayParams> CREATOR = new Parcelable.Creator<DisplayParams>() {
        /* class com.oculus.os.DisplayParams.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public DisplayParams createFromParcel(Parcel source) {
            return new DisplayParams(source);
        }

        @Override // android.os.Parcelable.Creator
        public DisplayParams[] newArray(int size) {
            return new DisplayParams[size];
        }
    };
    private static final int SWAP_TIMING_ARRAY_SIZE = 6;
    private int mScanoutDirection;
    private int mShutterType;
    private float[] mSwapTimingFrontBuffer;
    private float[] mSwapTimingSwappedBuffer;

    public DisplayParams() {
        this.mScanoutDirection = 0;
        this.mShutterType = 0;
        this.mSwapTimingFrontBuffer = new float[6];
        this.mSwapTimingSwappedBuffer = new float[6];
    }

    public DisplayParams(Parcel p) {
        this.mScanoutDirection = p.readInt();
        this.mShutterType = p.readInt();
        this.mSwapTimingFrontBuffer = new float[6];
        p.readFloatArray(this.mSwapTimingFrontBuffer);
        this.mSwapTimingSwappedBuffer = new float[6];
        p.readFloatArray(this.mSwapTimingSwappedBuffer);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mScanoutDirection);
        dest.writeInt(this.mShutterType);
        dest.writeFloatArray(this.mSwapTimingFrontBuffer);
        dest.writeFloatArray(this.mSwapTimingSwappedBuffer);
    }

    public int getScanoutDirection() {
        return this.mScanoutDirection;
    }

    public int getShutterType() {
        return this.mShutterType;
    }

    public float[] getSwapTimingFrontBuffer() {
        return this.mSwapTimingFrontBuffer;
    }

    public float[] getSwapTimingSwappedBuffer() {
        return this.mSwapTimingSwappedBuffer;
    }
}
