package com.oculus.horizoncontent.social;

import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.horizoncontent.social.SocialUser;
import javax.annotation.Nullable;

public final class FBSocialUser extends SocialUser {
    private boolean mIsMessengerActive;
    private boolean mIsVRUser;
    @Nullable
    private String mLastActiveTime;
    private ProfilePresenceType mPresenceIconType = ProfilePresenceType.NONE;
    private String mVrPersonaID;

    public FBSocialUser(String str, String str2, String str3, String str4, String str5, boolean z, SocialUser.UserRowType userRowType) {
        super(str, str2, str3, str4, str5, z, userRowType);
    }

    public String getVrPersonaID() {
        return this.mVrPersonaID;
    }

    public void setVrPersonaID(String str) {
        this.mVrPersonaID = str;
    }

    public boolean isVRUser() {
        return this.mIsVRUser;
    }

    public void setIsVRUser(boolean z) {
        this.mIsVRUser = z;
    }

    @Nullable
    public String getLastActiveTime() {
        return this.mLastActiveTime;
    }

    public void setLastActiveTime(@Nullable String str) {
        this.mLastActiveTime = str;
    }

    @Override // com.oculus.horizoncontent.social.SocialUser
    public ProfilePresenceType getPresenceIconType() {
        return this.mPresenceIconType;
    }

    public void setPresenceIconType(ProfilePresenceType profilePresenceType) {
        this.mPresenceIconType = profilePresenceType;
    }
}
