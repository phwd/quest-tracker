package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;

public class DisplayParams implements Parcelable {
    public static final Parcelable.Creator<DisplayParams> CREATOR = null;

    public DisplayParams() {
        throw new RuntimeException("Stub!");
    }

    public DisplayParams(Parcel p) {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public int getScanoutDirection() {
        throw new RuntimeException("Stub!");
    }

    public int getShutterType() {
        throw new RuntimeException("Stub!");
    }

    public float[] getSwapTimingFrontBuffer() {
        throw new RuntimeException("Stub!");
    }

    public float[] getSwapTimingSwappedBuffer() {
        throw new RuntimeException("Stub!");
    }
}
