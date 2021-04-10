package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;

public enum YadiStatus implements Parcelable {
    Invalid,
    Queued,
    Downloading,
    Verifying,
    Installing,
    Paused,
    Complete,
    Terminated;
    
    public static final Parcelable.Creator<YadiStatus> CREATOR = new Creator();

    public int describeContents() {
        return 0;
    }

    private static class Creator implements Parcelable.Creator<YadiStatus> {
        private Creator() {
        }

        @Override // android.os.Parcelable.Creator
        public YadiStatus[] newArray(int i) {
            return new YadiStatus[i];
        }

        @Override // android.os.Parcelable.Creator
        public YadiStatus createFromParcel(Parcel parcel) {
            return YadiStatus.values()[parcel.readInt()];
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
