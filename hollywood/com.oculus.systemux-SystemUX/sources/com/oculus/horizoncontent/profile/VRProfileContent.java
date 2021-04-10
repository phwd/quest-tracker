package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;

public class VRProfileContent implements ProfileContent {
    private String mAlias;
    private String mAvatarPhotoUrl;
    private String mBiography;
    private Boolean mCanInviteUserToParty;
    @Nullable
    private SocialUserFriendshipStatus mFriendshipStatus;
    private Boolean mIsUserActive;
    private Boolean mIsUserBlocked;
    @Nullable
    private String mJoinablePartyId;
    private long mLastActive;
    private String mLastPresence;
    private LinkedAccountsInfo mLinkedAccountsInfo;
    private SocialUserPresenceStatus mPresenceStatus;
    private String mPresenceString;
    private String mProfilePhotoUrl;
    private String mRealName;
    @Nullable
    private String mViewerCurrentPartyId;

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public int getFriendCount() {
        return 0;
    }

    public VRProfileContent(String str, String str2, SocialUserFriendshipStatus socialUserFriendshipStatus, Boolean bool, String str3, String str4, Boolean bool2, long j, String str5, String str6, SocialUserPresenceStatus socialUserPresenceStatus, String str7, String str8, Boolean bool3, LinkedAccountsInfo linkedAccountsInfo, String str9) {
        this.mAlias = str;
        this.mBiography = str2;
        this.mFriendshipStatus = socialUserFriendshipStatus;
        this.mIsUserBlocked = bool;
        this.mProfilePhotoUrl = str3;
        this.mAvatarPhotoUrl = str4;
        this.mIsUserActive = bool2;
        this.mLastActive = j;
        this.mLastPresence = str5;
        this.mPresenceString = str6;
        this.mPresenceStatus = socialUserPresenceStatus;
        this.mRealName = str7;
        this.mViewerCurrentPartyId = str8;
        this.mCanInviteUserToParty = bool3;
        this.mLinkedAccountsInfo = linkedAccountsInfo;
        this.mJoinablePartyId = str9;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAlias() {
        return this.mAlias;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAvatarUrl() {
        return this.mAvatarPhotoUrl;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getBiography() {
        return this.mBiography;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public SocialUserFriendshipStatus getFriendshipStatusInVr() {
        return this.mFriendshipStatus;
    }

    public Boolean getIsUserBlocked() {
        return this.mIsUserBlocked;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getProfilePictureUri() {
        return this.mProfilePhotoUrl;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public Boolean getIsUserActive() {
        return Boolean.valueOf(this.mIsUserActive.booleanValue() || this.mPresenceStatus == SocialUserPresenceStatus.ONLINE);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public ProfilePresence getPresence(Resources resources) {
        if (this.mIsUserActive.booleanValue()) {
            return new ProfilePresence(ProfilePresenceType.VR, this.mPresenceString);
        }
        if (this.mLastActive > 0) {
            return new ProfilePresence(ProfilePresenceType.VR, ProfilePresence.getLastActiveInVrString(resources, Long.valueOf(this.mLastActive), this.mLastPresence));
        }
        return new ProfilePresence(ProfilePresenceType.NONE, this.mPresenceString);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getRealName() {
        return this.mRealName;
    }

    @Nullable
    public String getViewerCurrentPartyId() {
        return this.mViewerCurrentPartyId;
    }

    public Boolean getCanInviteUserToParty() {
        return this.mCanInviteUserToParty;
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    @Nullable
    public String getJoinablePartyId() {
        return this.mJoinablePartyId;
    }
}
