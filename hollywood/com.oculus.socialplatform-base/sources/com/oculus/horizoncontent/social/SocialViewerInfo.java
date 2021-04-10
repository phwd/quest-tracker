package com.oculus.horizoncontent.social;

public class SocialViewerInfo {
    public String mAlias;
    public String mAvatarPhotoUrl;
    public boolean mHasSeenVRInviteProfileNux;
    public String mProfilePhotoUrl;

    public String getAlias() {
        return this.mAlias;
    }

    public String getAvatarPhotoUrl() {
        return this.mAvatarPhotoUrl;
    }

    public boolean getHasSeenVRInviteProfileNux() {
        return this.mHasSeenVRInviteProfileNux;
    }

    public String getProfilePhotoUrl() {
        return this.mProfilePhotoUrl;
    }

    public SocialViewerInfo(String str, String str2, String str3, boolean z) {
        this.mAlias = str;
        this.mProfilePhotoUrl = str2;
        this.mAvatarPhotoUrl = str3;
        this.mHasSeenVRInviteProfileNux = z;
    }
}
