package com.oculus.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class InputDevice implements Parcelable {
    public static final Parcelable.Creator<InputDevice> CREATOR = new Parcelable.Creator<InputDevice>() {
        /* class com.oculus.library.model.InputDevice.AnonymousClass1 */

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InputDevice createFromParcel(Parcel parcel) {
            return new InputDevice(parcel);
        }

        /* Return type fixed from 'java.lang.Object[]' to match base method */
        @Override // android.os.Parcelable.Creator
        public final InputDevice[] newArray(int i) {
            return new InputDevice[i];
        }
    };
    public String name;
    public String tag;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(Locale.US, "{InputDevice tag=%s name=%s}", this.tag, this.name);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tag);
        parcel.writeString(this.name);
    }

    public InputDevice(Parcel parcel) {
        this.tag = parcel.readString();
        this.name = parcel.readString();
    }

    public InputDevice(String str, String str2) {
        this.tag = str;
        this.name = str2;
    }
}
