package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;
import com.oculus.socialplatform.R;

public class FbFriendPrimaryProfile implements ProfileContent {
    public final Long SECONDS_IN_ONE_HOUR = 3600L;
    public final Long SECONDS_IN_TWO_DAYS = 259200L;
    public String mBio;
    public boolean mCanBeInvitedToParty;
    @Nullable
    public SocialUserFriendshipStatus mFriendshipStatusInVr;
    public boolean mIsCurrentlyActiveInMessenger;
    public boolean mIsCurrentlyActiveInVr;
    @Nullable
    public String mJoinablePartyId;
    public Long mLastActiveTimeInMessenger;
    public Long mLastActiveTimeInVr;
    public String mName;
    public String mOculusUserId;
    public String mPresenceInVr;
    public SocialUserPresenceStatus mPresenceStatusInVr;
    public String mProfilePictureUri;
    @Nullable
    public String mViewerCurrentPartyId;
    public String mViewerFbId;

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAlias() {
        return "";
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getAvatarUrl() {
        return "";
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public int getFriendCount() {
        return 0;
    }

    @Nullable
    private String getLastActiveInMessengerString(Resources resources) {
        int i;
        int timeAgoMinutes = ProfilePresence.getTimeAgoMinutes(this.mLastActiveTimeInMessenger);
        if (timeAgoMinutes < 60) {
            i = R.string.profile_tablet_last_active_time_in_messenger_in_minutes;
        } else {
            timeAgoMinutes /= 60;
            if (timeAgoMinutes < 24) {
                i = R.string.profile_tablet_last_active_time_in_messenger_in_hours;
            } else {
                timeAgoMinutes /= 24;
                if (timeAgoMinutes >= 3) {
                    return null;
                }
                i = R.string.profile_tablet_last_active_time_in_messenger_in_days;
            }
        }
        return resources.getString(i, Integer.valueOf(timeAgoMinutes));
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getBiography() {
        return this.mBio;
    }

    public Boolean getCanInviteUserToParty() {
        return Boolean.valueOf(this.mCanBeInvitedToParty);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public SocialUserFriendshipStatus getFriendshipStatusInVr() {
        return this.mFriendshipStatusInVr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r2.mIsCurrentlyActiveInMessenger != false) goto L_0x000f;
     */
    @Override // com.oculus.horizoncontent.profile.ProfileContent
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean getIsUserActive() {
        /*
            r2 = this;
            com.oculus.horizoncontent.social.SocialUserPresenceStatus r1 = r2.mPresenceStatusInVr
            com.oculus.horizoncontent.social.SocialUserPresenceStatus r0 = com.oculus.horizoncontent.social.SocialUserPresenceStatus.ONLINE
            if (r1 == r0) goto L_0x000f
            boolean r0 = r2.mIsCurrentlyActiveInVr
            if (r0 != 0) goto L_0x000f
            boolean r1 = r2.mIsCurrentlyActiveInMessenger
            r0 = 0
            if (r1 == 0) goto L_0x0010
        L_0x000f:
            r0 = 1
        L_0x0010:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.profile.FbFriendPrimaryProfile.getIsUserActive():java.lang.Boolean");
    }

    @Nullable
    public String getJoinablePartyId() {
        return this.mJoinablePartyId;
    }

    public String getLinkedOculusUserId() {
        return this.mOculusUserId;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public ProfilePresence getPresence(Resources resources) {
        ProfilePresenceType profilePresenceType;
        String str;
        String str2;
        ProfilePresenceType profilePresenceType2;
        if (this.mPresenceStatusInVr == SocialUserPresenceStatus.ONLINE || this.mIsCurrentlyActiveInVr) {
            str2 = this.mPresenceInVr;
            if (str2.length() == 0) {
                profilePresenceType = ProfilePresenceType.VR;
                str = resources.getString(R.string.profile_tablet_active_in_oculus);
                return new ProfilePresence(profilePresenceType, str);
            }
            profilePresenceType2 = ProfilePresenceType.VR;
        } else {
            str2 = resources.getString(R.string.profile_tablet_active_in_messenger);
            if (this.mIsCurrentlyActiveInMessenger) {
                profilePresenceType2 = ProfilePresenceType.MESSENGER;
            } else {
                Long l = this.mLastActiveTimeInVr;
                long longValue = l.longValue();
                Long valueOf = Long.valueOf((System.currentTimeMillis() / 1000) - longValue);
                if (longValue <= 0 || valueOf.longValue() >= this.SECONDS_IN_ONE_HOUR.longValue() || ProfilePresence.getLastActiveInVrString(resources, l, this.mPresenceInVr) == null) {
                    long longValue2 = this.mLastActiveTimeInMessenger.longValue();
                    Long valueOf2 = Long.valueOf((System.currentTimeMillis() / 1000) - longValue2);
                    if (longValue2 <= 0 || valueOf2.longValue() >= this.SECONDS_IN_ONE_HOUR.longValue() || getLastActiveInMessengerString(resources) == null) {
                        Long l2 = this.mLastActiveTimeInVr;
                        if (l2.longValue() <= 0 || valueOf.longValue() >= this.SECONDS_IN_TWO_DAYS.longValue() || ProfilePresence.getLastActiveInVrString(resources, l2, this.mPresenceInVr) == null) {
                            if (this.mLastActiveTimeInMessenger.longValue() <= 0 || valueOf2.longValue() >= this.SECONDS_IN_TWO_DAYS.longValue() || getLastActiveInMessengerString(resources) == null) {
                                return new ProfilePresence(ProfilePresenceType.NONE, "");
                            }
                        }
                    }
                    profilePresenceType = ProfilePresenceType.MESSENGER;
                    str = getLastActiveInMessengerString(resources);
                    return new ProfilePresence(profilePresenceType, str);
                }
                profilePresenceType = ProfilePresenceType.VR;
                str = ProfilePresence.getLastActiveInVrString(resources, this.mLastActiveTimeInVr, this.mPresenceInVr);
                return new ProfilePresence(profilePresenceType, str);
            }
        }
        return new ProfilePresence(profilePresenceType2, str2);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getProfilePictureUri() {
        return this.mProfilePictureUri;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getRealName() {
        return this.mName;
    }

    @Nullable
    public String getViewerCurrentPartyId() {
        return this.mViewerCurrentPartyId;
    }

    public String getViewerFbId() {
        return this.mViewerFbId;
    }

    public FbFriendPrimaryProfile(String str, String str2, String str3, boolean z, Long l, String str4, SocialUserFriendshipStatus socialUserFriendshipStatus, SocialUserPresenceStatus socialUserPresenceStatus, boolean z2, String str5, Long l2, boolean z3, String str6, String str7, String str8) {
        this.mName = str;
        this.mProfilePictureUri = str2;
        this.mBio = str3;
        this.mIsCurrentlyActiveInMessenger = z;
        this.mLastActiveTimeInMessenger = l;
        this.mOculusUserId = str4;
        this.mFriendshipStatusInVr = socialUserFriendshipStatus;
        this.mPresenceStatusInVr = socialUserPresenceStatus;
        this.mIsCurrentlyActiveInVr = z2;
        this.mPresenceInVr = str5;
        this.mLastActiveTimeInVr = l2;
        this.mCanBeInvitedToParty = z3;
        this.mJoinablePartyId = str6;
        this.mViewerFbId = str7;
        this.mViewerCurrentPartyId = str8;
    }
}
