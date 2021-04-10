package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.util.annotations.UsedByGson;

public class MutualFriends implements Parcelable {
    public static final Parcelable.Creator<MutualFriends> CREATOR = new Parcelable.Creator<MutualFriends>() {
        /* class com.oculus.horizon.api.common.user.MutualFriends.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MutualFriends createFromParcel(Parcel parcel) {
            return new MutualFriends(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MutualFriends[] newArray(int i) {
            return new MutualFriends[i];
        }
    };
    public int count;

    public int describeContents() {
        return 0;
    }

    @UsedByGson
    public MutualFriends() {
    }

    public MutualFriends(Parcel parcel) {
        this.count = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.count);
    }
}
