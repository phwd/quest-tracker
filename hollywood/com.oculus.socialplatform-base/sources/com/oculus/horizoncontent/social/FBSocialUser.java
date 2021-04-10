package com.oculus.horizoncontent.social;

import androidx.annotation.Nullable;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.horizoncontent.social.SocialUser;

public final class FBSocialUser extends SocialUser {
    public boolean mIsMessengerActive;
    public boolean mIsVRUser;
    @Nullable
    public String mLastActiveTime;
    public ProfilePresenceType mPresenceIconType = ProfilePresenceType.NONE;
    public String mVrPersonaID;

    @Nullable
    public String getLastActiveTime() {
        return this.mLastActiveTime;
    }

    @Override // com.oculus.horizoncontent.social.SocialUser
    public ProfilePresenceType getPresenceIconType() {
        return this.mPresenceIconType;
    }

    public String getVrPersonaID() {
        return this.mVrPersonaID;
    }

    public boolean isVRUser() {
        return this.mIsVRUser;
    }

    public FBSocialUser(String str, String str2, String str3, String str4, String str5, boolean z, SocialUser.UserRowType userRowType) {
        super(str, str2, str3, str4, str5, z, userRowType);
    }

    public void setIsVRUser(boolean z) {
        this.mIsVRUser = z;
    }

    public void setLastActiveTime(@Nullable String str) {
        this.mLastActiveTime = str;
    }

    public void setPresenceIconType(ProfilePresenceType profilePresenceType) {
        this.mPresenceIconType = profilePresenceType;
    }

    public void setVrPersonaID(String str) {
        this.mVrPersonaID = str;
    }
}
