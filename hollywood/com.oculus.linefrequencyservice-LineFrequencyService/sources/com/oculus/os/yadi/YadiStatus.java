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

    private static class Creator implements Parcelable.Creator<YadiStatus> {
        private Creator() {
        }

        @Override // android.os.Parcelable.Creator
        public YadiStatus[] newArray(int size) {
            return new YadiStatus[size];
        }

        @Override // android.os.Parcelable.Creator
        public YadiStatus createFromParcel(Parcel p) {
            return YadiStatus.values()[p.readInt()];
        }
    }

    public void writeToParcel(Parcel p, int flags) {
        p.writeInt(ordinal());
    }

    public int describeContents() {
        return 0;
    }
}
