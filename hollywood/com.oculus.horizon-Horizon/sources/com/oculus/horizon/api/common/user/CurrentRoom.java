package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.horizon.api.common.Room;
import com.oculus.util.annotations.UsedByGson;
import java.util.ArrayList;
import java.util.List;

public class CurrentRoom implements Parcelable {
    public static final Parcelable.Creator<CurrentRoom> CREATOR = new Parcelable.Creator<CurrentRoom>() {
        /* class com.oculus.horizon.api.common.user.CurrentRoom.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CurrentRoom createFromParcel(Parcel parcel) {
            return new CurrentRoom(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CurrentRoom[] newArray(int i) {
            return new CurrentRoom[i];
        }
    };
    public List<Room> nodes;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.nodes);
    }

    @UsedByGson
    public CurrentRoom() {
    }

    public CurrentRoom(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.nodes = arrayList;
        parcel.readTypedList(arrayList, Room.CREATOR);
    }
}
