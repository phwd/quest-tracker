package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.common.horizoncontent.R;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;

public class FbFriendPrimaryProfile implements ProfileContent {
    private final Long SECONDS_IN_ONE_HOUR = 3600L;
    private final Long SECONDS_IN_TWO_DAYS = 259200L;
    private String mBio;
    private boolean mCanBeInvitedToParty;
    @Nullable
    private SocialUserFriendshipStatus mFriendshipStatusInVr;
    private boolean mIsCurrentlyActiveInMessenger;
    private boolean mIsCurrentlyActiveInVr;
    @Nullable
    private String mJoinablePartyId;
    private Long mLastActiveTimeInMessenger;
    private Long mLastActiveTimeInVr;
    private String mName;
    private String mOculusUserId;
    private String mPresenceInVr;
    private SocialUserPresenceStatus mPresenceStatusInVr;
    private String mProfilePictureUri;
    @Nullable
    private String mViewerCurrentPartyId;
    private String mViewerFbId;

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

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getBiography() {
        return this.mBio;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public SocialUserFriendshipStatus getFriendshipStatusInVr() {
        return this.mFriendshipStatusInVr;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getProfilePictureUri() {
        return this.mProfilePictureUri;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public Boolean getIsUserActive() {
        return Boolean.valueOf(this.mPresenceStatusInVr == SocialUserPresenceStatus.ONLINE || this.mIsCurrentlyActiveInVr || this.mIsCurrentlyActiveInMessenger);
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public ProfilePresence getPresence(Resources resources) {
        if (this.mPresenceStatusInVr != SocialUserPresenceStatus.ONLINE && !this.mIsCurrentlyActiveInVr) {
            String string = resources.getString(R.string.profile_tablet_active_in_messenger);
            if (this.mIsCurrentlyActiveInMessenger) {
                return new ProfilePresence(ProfilePresenceType.MESSENGER, string);
            }
            Long valueOf = Long.valueOf((System.currentTimeMillis() / 1000) - this.mLastActiveTimeInVr.longValue());
            if (this.mLastActiveTimeInVr.longValue() > 0 && valueOf.longValue() < this.SECONDS_IN_ONE_HOUR.longValue() && ProfilePresence.getLastActiveInVrString(resources, this.mLastActiveTimeInVr, this.mPresenceInVr) != null) {
                return new ProfilePresence(ProfilePresenceType.VR, ProfilePresence.getLastActiveInVrString(resources, this.mLastActiveTimeInVr, this.mPresenceInVr));
            }
            Long valueOf2 = Long.valueOf((System.currentTimeMillis() / 1000) - this.mLastActiveTimeInMessenger.longValue());
            if (this.mLastActiveTimeInMessenger.longValue() > 0 && valueOf2.longValue() < this.SECONDS_IN_ONE_HOUR.longValue() && getLastActiveInMessengerString(resources) != null) {
                return new ProfilePresence(ProfilePresenceType.MESSENGER, getLastActiveInMessengerString(resources));
            }
            if (this.mLastActiveTimeInVr.longValue() > 0 && valueOf.longValue() < this.SECONDS_IN_TWO_DAYS.longValue() && ProfilePresence.getLastActiveInVrString(resources, this.mLastActiveTimeInVr, this.mPresenceInVr) != null) {
                return new ProfilePresence(ProfilePresenceType.VR, ProfilePresence.getLastActiveInVrString(resources, this.mLastActiveTimeInVr, this.mPresenceInVr));
            }
            if (this.mLastActiveTimeInMessenger.longValue() <= 0 || valueOf2.longValue() >= this.SECONDS_IN_TWO_DAYS.longValue() || getLastActiveInMessengerString(resources) == null) {
                return new ProfilePresence(ProfilePresenceType.NONE, "");
            }
            return new ProfilePresence(ProfilePresenceType.MESSENGER, getLastActiveInMessengerString(resources));
        } else if (this.mPresenceInVr.length() == 0) {
            return new ProfilePresence(ProfilePresenceType.VR, resources.getString(R.string.profile_tablet_active_in_oculus));
        } else {
            return new ProfilePresence(ProfilePresenceType.VR, this.mPresenceInVr);
        }
    }

    @Nullable
    private String getLastActiveInMessengerString(Resources resources) {
        int timeAgoMinutes = ProfilePresence.getTimeAgoMinutes(this.mLastActiveTimeInMessenger);
        if (timeAgoMinutes < 60) {
            return resources.getString(R.string.profile_tablet_last_active_time_in_messenger_in_minutes, Integer.valueOf(timeAgoMinutes));
        }
        int i = timeAgoMinutes / 60;
        if (i < 24) {
            return resources.getString(R.string.profile_tablet_last_active_time_in_messenger_in_hours, Integer.valueOf(i));
        }
        int i2 = i / 24;
        if (i2 >= 3) {
            return null;
        }
        return resources.getString(R.string.profile_tablet_last_active_time_in_messenger_in_days, Integer.valueOf(i2));
    }

    public String getLinkedOculusUserId() {
        return this.mOculusUserId;
    }

    @Nullable
    public String getViewerCurrentPartyId() {
        return this.mViewerCurrentPartyId;
    }

    public Boolean getCanInviteUserToParty() {
        return Boolean.valueOf(this.mCanBeInvitedToParty);
    }

    @Nullable
    public String getJoinablePartyId() {
        return this.mJoinablePartyId;
    }

    public String getViewerFbId() {
        return this.mViewerFbId;
    }

    @Override // com.oculus.horizoncontent.profile.ProfileContent
    public String getRealName() {
        return this.mName;
    }
}
