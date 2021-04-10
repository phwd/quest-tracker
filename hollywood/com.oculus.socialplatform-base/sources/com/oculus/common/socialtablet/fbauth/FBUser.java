package com.oculus.common.socialtablet.fbauth;

public class FBUser {
    public final String mDisplayName;
    public final String mProfilePictureUrl;
    public final String mUserId;

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getProfilePictureUrl() {
        return this.mProfilePictureUrl;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public FBUser(String str, String str2, String str3) {
        this.mUserId = str;
        this.mProfilePictureUrl = str3;
        this.mDisplayName = str2;
    }
}
