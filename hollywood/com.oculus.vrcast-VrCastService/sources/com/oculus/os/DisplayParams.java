package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public class DisplayParams implements Parcelable {
    public static final Parcelable.Creator<DisplayParams> CREATOR = new Parcelable.Creator<DisplayParams>() {
        /* class com.oculus.os.DisplayParams.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public DisplayParams createFromParcel(Parcel parcel) {
            return new DisplayParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DisplayParams[] newArray(int i) {
            return new DisplayParams[i];
        }
    };
    private int mScanoutDirection;
    private int mShutterType;
    private float[] mSwapTimingFrontBuffer;
    private float[] mSwapTimingSwappedBuffer;

    public int describeContents() {
        return 0;
    }

    public DisplayParams() {
        this.mScanoutDirection = 0;
        this.mShutterType = 0;
        this.mSwapTimingFrontBuffer = new float[6];
        this.mSwapTimingSwappedBuffer = new float[6];
    }

    public DisplayParams(Parcel parcel) {
        this.mScanoutDirection = parcel.readInt();
        this.mShutterType = parcel.readInt();
        this.mSwapTimingFrontBuffer = new float[6];
        parcel.readFloatArray(this.mSwapTimingFrontBuffer);
        this.mSwapTimingSwappedBuffer = new float[6];
        parcel.readFloatArray(this.mSwapTimingSwappedBuffer);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mScanoutDirection);
        parcel.writeInt(this.mShutterType);
        parcel.writeFloatArray(this.mSwapTimingFrontBuffer);
        parcel.writeFloatArray(this.mSwapTimingSwappedBuffer);
    }
}
