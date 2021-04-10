package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;

public class FriendRequestUser extends BasicUser implements Parcelable {
    public static final Parcelable.Creator<FriendRequestUser> CREATOR = new Parcelable.Creator<FriendRequestUser>() {
        /* class com.oculus.horizon.api.common.user.FriendRequestUser.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FriendRequestUser createFromParcel(Parcel parcel) {
            return new FriendRequestUser(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FriendRequestUser[] newArray(int i) {
            return new FriendRequestUser[i];
        }
    };
    public String user_id;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        if (obj instanceof FriendRequestUser) {
            str = ((BasicUser) obj).id;
            str2 = this.id;
        } else if (!(obj instanceof User)) {
            return false;
        } else {
            str = ((BasicUser) obj).id;
            str2 = this.user_id;
        }
        return str.contentEquals(str2);
    }

    public int hashCode() {
        return this.user_id.hashCode();
    }

    public FriendRequestUser(Parcel parcel) {
        super(parcel);
        this.user_id = parcel.readString();
    }

    @Override // com.oculus.horizon.api.common.user.BasicUser
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.user_id);
    }
}
