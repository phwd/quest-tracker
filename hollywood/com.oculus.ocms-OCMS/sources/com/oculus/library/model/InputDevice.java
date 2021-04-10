package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class InputDevice implements Parcelable {
    public static final Parcelable.Creator<InputDevice> CREATOR = new Parcelable.Creator<InputDevice>() {
        /* class com.oculus.library.model.InputDevice.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InputDevice createFromParcel(Parcel parcel) {
            return new InputDevice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public InputDevice[] newArray(int i) {
            return new InputDevice[i];
        }
    };
    public String name;
    public String tag;

    public int describeContents() {
        return 0;
    }

    public InputDevice(String str, String str2) {
        this.tag = str;
        this.name = str2;
    }

    protected InputDevice(Parcel parcel) {
        this.tag = parcel.readString();
        this.name = parcel.readString();
    }

    public String toString() {
        return String.format(Locale.US, "{InputDevice tag=%s name=%s}", this.tag, this.name);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tag);
        parcel.writeString(this.name);
    }

    public String generateDeviceString() {
        return this.tag + ":" + this.name;
    }

    public static InputDevice generateFromDeviceString(String str) {
        String[] split = str.split(":");
        if (split.length != 2) {
            return null;
        }
        return new InputDevice(split[0], split[1]);
    }
}
