package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class InputDevice implements Parcelable {
    public static final Parcelable.Creator<InputDevice> CREATOR = new Parcelable.Creator<InputDevice>() {
        /* class com.oculus.library.model.InputDevice.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public InputDevice createFromParcel(Parcel in) {
            return new InputDevice(in);
        }

        @Override // android.os.Parcelable.Creator
        public InputDevice[] newArray(int size) {
            return new InputDevice[size];
        }
    };
    public String name;
    public String tag;

    public InputDevice(String tag2, String name2) {
        this.tag = tag2;
        this.name = name2;
    }

    protected InputDevice(Parcel in) {
        this.tag = in.readString();
        this.name = in.readString();
    }

    public String toString() {
        return String.format(Locale.US, "{InputDevice tag=%s name=%s}", this.tag, this.name);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tag);
        parcel.writeString(this.name);
    }

    public String generateDeviceString() {
        return this.tag + ":" + this.name;
    }

    public static InputDevice generateFromDeviceString(String deviceString) {
        String[] parts = deviceString.split(":");
        if (parts.length != 2) {
            return null;
        }
        return new InputDevice(parts[0], parts[1]);
    }
}
