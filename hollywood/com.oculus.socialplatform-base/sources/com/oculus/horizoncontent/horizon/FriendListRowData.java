package com.oculus.horizoncontent.horizon;

import com.oculus.horizoncontent.horizon.HorizonContent;

public final class FriendListRowData {
    public final String displayName;
    public final String id;
    public HorizonContent.FriendList.UserInviteState inviteState;
    public final String presenceStatus;
    public final String profilePhotoURL;

    public String toString() {
        StringBuilder sb = new StringBuilder("id ");
        sb.append(this.id);
        sb.append(" displayName: ");
        sb.append(this.displayName);
        sb.append(" presence: ");
        sb.append(this.presenceStatus);
        sb.append(" profile photo: ");
        sb.append(this.profilePhotoURL);
        sb.append(" inviteState: ");
        sb.append(this.inviteState);
        return sb.toString();
    }

    public FriendListRowData(String str, String str2, String str3, String str4, HorizonContent.FriendList.UserInviteState userInviteState) {
        this.id = str;
        this.displayName = str2;
        this.presenceStatus = str3;
        this.profilePhotoURL = str4;
        this.inviteState = userInviteState;
    }
}
