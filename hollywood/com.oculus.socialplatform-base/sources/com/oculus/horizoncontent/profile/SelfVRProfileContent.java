package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;

public final class SelfVRProfileContent implements ProfileContent {
    public String mAlias;
    public String mAvatarImageUrl;
    @Nullable
    public String mBiography;
    public int mFriendCount;
    public Boolean mIsUserActive;
    public ProfilePresence mPresence;
    public String mPresenceString;
    public String mProfilePhotoUrl;
    public String mRealName;

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAlias() {
        return this.mAlias;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAvatarUrl() {
        return this.mAvatarImageUrl;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getBiography() {
        String str = this.mBiography;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public int getFriendCount() {
        return this.mFriendCount;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public SocialUserFriendshipStatus getFriendshipStatusInVr() {
        return SocialUserFriendshipStatus.UNKNOWN;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public Boolean getIsUserActive() {
        return this.mIsUserActive;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getProfilePictureUri() {
        return this.mProfilePhotoUrl;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getRealName() {
        return this.mRealName;
    }

    public SelfVRProfileContent(int i, String str, Boolean bool, String str2, String str3, String str4, String str5, String str6) {
        ProfilePresenceType profilePresenceType;
        this.mFriendCount = i;
        this.mBiography = str;
        this.mIsUserActive = bool;
        this.mPresenceString = str2;
        this.mRealName = str3;
        this.mAlias = str4;
        this.mProfilePhotoUrl = str5;
        this.mAvatarImageUrl = str6;
        if (bool.booleanValue()) {
            profilePresenceType = ProfilePresenceType.VR;
        } else {
            profilePresenceType = ProfilePresenceType.NONE;
        }
        this.mPresence = new ProfilePresence(profilePresenceType, str2);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public ProfilePresence getPresence(Resources resources) {
        return this.mPresence;
    }
}
