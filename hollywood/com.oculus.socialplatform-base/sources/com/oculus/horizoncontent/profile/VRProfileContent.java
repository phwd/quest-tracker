package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;

public class VRProfileContent implements ProfileContent {
    public String mAlias;
    public String mAvatarPhotoUrl;
    public String mBiography;
    public Boolean mCanInviteUserToParty;
    @Nullable
    public SocialUserFriendshipStatus mFriendshipStatus;
    public Boolean mIsUserActive;
    public Boolean mIsUserBlocked;
    @Nullable
    public String mJoinablePartyId;
    public long mLastActive;
    public String mLastPresence;
    public LinkedAccountsInfo mLinkedAccountsInfo;
    public SocialUserPresenceStatus mPresenceStatus;
    public String mPresenceString;
    public String mProfilePhotoUrl;
    public String mRealName;
    @Nullable
    public String mViewerCurrentPartyId;

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public int getFriendCount() {
        return 0;
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

    public Boolean getCanInviteUserToParty() {
        return this.mCanInviteUserToParty;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public SocialUserFriendshipStatus getFriendshipStatusInVr() {
        return this.mFriendshipStatus;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r3.mPresenceStatus == com.oculus.horizoncontent.social.SocialUserPresenceStatus.ONLINE) goto L_0x000f;
     */
    @Override // com.oculus.horizoncontent.profile.ProfileContent
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean getIsUserActive() {
        /*
            r3 = this;
            java.lang.Boolean r0 = r3.mIsUserActive
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x000f
            com.oculus.horizoncontent.social.SocialUserPresenceStatus r2 = r3.mPresenceStatus
            com.oculus.horizoncontent.social.SocialUserPresenceStatus r1 = com.oculus.horizoncontent.social.SocialUserPresenceStatus.ONLINE
            r0 = 0
            if (r2 != r1) goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.profile.VRProfileContent.getIsUserActive():java.lang.Boolean");
    }

    public Boolean getIsUserBlocked() {
        return this.mIsUserBlocked;
    }

    @Nullable
    public String getJoinablePartyId() {
        return this.mJoinablePartyId;
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public ProfilePresence getPresence(Resources resources) {
        ProfilePresenceType profilePresenceType;
        String lastActiveInVrString;
        if (this.mIsUserActive.booleanValue()) {
            profilePresenceType = ProfilePresenceType.VR;
        } else {
            long j = this.mLastActive;
            if (j > 0) {
                profilePresenceType = ProfilePresenceType.VR;
                lastActiveInVrString = ProfilePresence.getLastActiveInVrString(resources, Long.valueOf(j), this.mLastPresence);
                return new ProfilePresence(profilePresenceType, lastActiveInVrString);
            }
            profilePresenceType = ProfilePresenceType.NONE;
        }
        lastActiveInVrString = this.mPresenceString;
        return new ProfilePresence(profilePresenceType, lastActiveInVrString);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getProfilePictureUri() {
        return this.mProfilePhotoUrl;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getRealName() {
        return this.mRealName;
    }

    @Nullable
    public String getViewerCurrentPartyId() {
        return this.mViewerCurrentPartyId;
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
}
