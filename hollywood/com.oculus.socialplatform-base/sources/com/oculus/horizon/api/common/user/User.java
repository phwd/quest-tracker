package com.oculus.horizon.api.common.user;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.horizon.api.common.ClientPreference;
import com.oculus.horizon.api.common.ExternalCreditCards;
import com.oculus.horizon.api.common.PaymentMethods;
import com.oculus.horizon.api.common.ReachabilityCheck;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.util.annotations.UsedByGson;
import java.util.List;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class User extends BasicUser implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        /* class com.oculus.horizon.api.common.user.User.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public User[] newArray(int i) {
            return new User[i];
        }
    };
    @Nullable
    public int account_creation_time;
    @Nullable
    public final PaymentMethods all_payment_methods;
    public ProfilePhoto avatar_image;
    @Nullable
    public List<ClientPreference> client_preference_data;
    public CurrentRoom current_room;
    @Nullable
    public String email;
    public FriendRequestsReceived friend_requests_received_2;
    public Friends friends;
    public boolean is_facebook_friend;
    @Nullable
    public Boolean is_pin_set;
    @UsedByGson
    public GameInviteList notifications;
    @Nullable
    public ExternalCreditCards payment_methods;
    public final String presence;
    public ProfilePhoto profile_photo;
    @Nullable
    public List<ReachabilityCheck> reachability_data;

    public static class Friends {
        public int count;
    }

    public static class GameInviteList {
        public List<GameInviteSummary> nodes;

        public static class GameInviteSummary {
            public String id;
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj instanceof User) {
            str = ((BasicUser) obj).id;
        } else if (!(obj instanceof FriendRequestUser)) {
            return false;
        } else {
            str = ((FriendRequestUser) obj).user_id;
        }
        return str.contentEquals(this.id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.oculus.horizon.api.common.user.BasicUser
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.profile_photo, i);
        parcel.writeString(this.email);
        parcel.writeValue(this.is_pin_set);
        parcel.writeParcelable(this.current_room, i);
        parcel.writeParcelable(this.mutual_friends, i);
    }

    @UsedByGson
    public User() {
    }

    public User(Parcel parcel) {
        super(parcel);
        this.profile_photo = (ProfilePhoto) parcel.readParcelable(ProfilePhoto.class.getClassLoader());
        this.email = parcel.readString();
        this.is_pin_set = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.current_room = (CurrentRoom) parcel.readParcelable(CurrentRoom.class.getClassLoader());
        this.mutual_friends = (MutualFriends) parcel.readParcelable(MutualFriends.class.getClassLoader());
    }

    public User(String str, String str2, String str3, String str4, boolean z) {
        super(str, str2, str3);
        this.email = str4;
        this.is_pin_set = Boolean.valueOf(z);
    }
}
