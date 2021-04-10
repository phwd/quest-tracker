package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public abstract class BasicUser implements Parcelable {
    public String alias;
    public ProfilePhoto avatar;
    public boolean can_viewer_message;
    public String friend_status;
    @Nullable
    public String id;
    public MutualFriends mutual_friends;
    @Nullable
    public String name;
    @Nullable
    public List<String> online_platforms;
    public String presence_status;

    public BasicUser() {
    }

    public BasicUser(String str, String str2, String str3) {
        this.id = str;
        this.name = str2;
        this.alias = str3;
    }

    protected BasicUser(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.alias = parcel.readString();
        this.friend_status = parcel.readString();
        this.avatar = (ProfilePhoto) parcel.readParcelable(ProfilePhoto.class.getClassLoader());
        this.mutual_friends = (MutualFriends) parcel.readParcelable(MutualFriends.class.getClassLoader());
        this.online_platforms = new ArrayList();
        parcel.readStringList(this.online_platforms);
        this.presence_status = parcel.readString();
        this.can_viewer_message = parcel.readBoolean();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.alias);
        parcel.writeString(this.friend_status);
        parcel.writeParcelable(this.avatar, i);
        parcel.writeParcelable(this.mutual_friends, i);
        parcel.writeStringList(this.online_platforms);
        parcel.writeString(this.presence_status);
        parcel.writeBoolean(this.can_viewer_message);
    }
}
