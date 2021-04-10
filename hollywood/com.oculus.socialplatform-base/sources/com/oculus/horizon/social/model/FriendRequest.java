package com.oculus.horizon.social.model;

import com.oculus.horizon.api.common.user.FriendRequestUser;

public class FriendRequest {
    public final String source;
    public final FriendRequestUser user;

    public boolean equals(Object obj) {
        if (!(obj instanceof FriendRequest) || !((FriendRequest) obj).user.equals(this.user)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.user.hashCode();
    }

    public FriendRequest(FriendRequestUser friendRequestUser, String str) {
        this.user = friendRequestUser;
        this.source = str;
    }
}
