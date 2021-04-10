package com.oculus.horizoncontent.horizon;

import com.oculus.horizoncontent.horizon.HorizonContent;

public final class FriendListRowData {
    public final String displayName;
    public final String id;
    public HorizonContent.FriendList.UserInviteState inviteState;
    public final String presenceStatus;
    public final String profilePhotoURL;

    public FriendListRowData(String str, String str2, String str3, String str4, HorizonContent.FriendList.UserInviteState userInviteState) {
        this.id = str;
        this.displayName = str2;
        this.presenceStatus = str3;
        this.profilePhotoURL = str4;
        this.inviteState = userInviteState;
    }

    public String toString() {
        return "id " + this.id + " displayName: " + this.displayName + " presence: " + this.presenceStatus + " profile photo: " + this.profilePhotoURL + " inviteState: " + this.inviteState;
    }
}
