package com.oculus.horizoncontent.horizon;

public class PartyInfo {
    public final int blockedCurrentMembersCount;
    public final int blockedInvitedUsersCount;
    public final int currentMembersCount;
    public final String id;
    public final String inviteActivityAppId;
    public final String inviteActivityDeeplink;
    public final String inviteActivityID;
    public final String inviteActivityImageUri;
    public final String inviteActivityPackageName;
    public final String inviteActivitySubtitle;
    public final String inviteActivityTitle;
    public final int invitedUsersCount;

    public PartyInfo(String str, int i, int i2, int i3, int i4, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.id = str;
        this.blockedInvitedUsersCount = i;
        this.invitedUsersCount = i2;
        this.blockedCurrentMembersCount = i3;
        this.currentMembersCount = i4;
        this.inviteActivityID = str2;
        this.inviteActivityTitle = str3;
        this.inviteActivitySubtitle = str4;
        this.inviteActivityDeeplink = str5 == null ? "" : str5;
        this.inviteActivityImageUri = str6;
        this.inviteActivityPackageName = str7;
        this.inviteActivityAppId = str8;
    }

    public String toString() {
        return "id: " + this.id + " blockedInvitedUsersCount: " + this.blockedInvitedUsersCount + " invitedUsersCount: " + this.invitedUsersCount + " blockedCurrentMembersCount: " + this.blockedCurrentMembersCount + " currentMembersCount: " + this.currentMembersCount + " inviteActivityID: " + this.inviteActivityID + " inviteActivityTitle: " + this.inviteActivityTitle + " inviteActivitySubtitle: " + this.inviteActivitySubtitle + " inviteActivityDeeplink: " + this.inviteActivityDeeplink + " inviteActivityImageUri: " + this.inviteActivityImageUri + " inviteActivityPackageName: " + this.inviteActivityPackageName + " inviteActivityAppId: " + this.inviteActivityAppId;
    }
}
