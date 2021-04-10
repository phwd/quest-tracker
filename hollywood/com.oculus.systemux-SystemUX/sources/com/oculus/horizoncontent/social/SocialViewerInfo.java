package com.oculus.horizoncontent.social;

public class SocialViewerInfo {
    private String mAlias;
    private String mAvatarPhotoUrl;
    private boolean mHasSeenVRInviteProfileNux;
    private String mProfilePhotoUrl;

    public SocialViewerInfo(String str, String str2, String str3, boolean z) {
        this.mAlias = str;
        this.mProfilePhotoUrl = str2;
        this.mAvatarPhotoUrl = str3;
        this.mHasSeenVRInviteProfileNux = z;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public String getProfilePhotoUrl() {
        return this.mProfilePhotoUrl;
    }

    public String getAvatarPhotoUrl() {
        return this.mAvatarPhotoUrl;
    }

    public boolean getHasSeenVRInviteProfileNux() {
        return this.mHasSeenVRInviteProfileNux;
    }
}
