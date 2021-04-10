package com.oculus.horizon.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.util.annotations.UsedByGson;

public class Room implements Parcelable {
    public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
        /* class com.oculus.horizon.api.common.Room.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Room createFromParcel(Parcel parcel) {
            return new Room(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Room[] newArray(int i) {
            return new Room[i];
        }
    };
    public App app;

    public int describeContents() {
        return 0;
    }

    @UsedByGson
    public Room() {
    }

    public Room(Parcel parcel) {
        this.app = (App) parcel.readParcelable(App.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.app, i);
    }
}
