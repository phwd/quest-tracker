package com.oculus.horizoncontent.social;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.socialplatform.R;
import java.util.Objects;

public class SocialUser {
    public String mAlias;
    public boolean mCanViewerMessage;
    @Nullable
    public String mCurrentPartyID;
    @Nullable
    public String mCurrentPartyJoinPolicy;
    @Nullable
    public SocialDeeplinkPresence mDeepLink;
    @Nullable
    public SocialUserFriendshipStatus mFriendship;
    public String mID;
    @Nullable
    public String mInvitedToLocalPartyBySenderID;
    public boolean mIsInRoom;
    public boolean mIsPartyFull;
    @Nullable
    public String mLastActive;
    public PartyMicrophoneState mMicrophoneChannel = PartyMicrophoneState.UNKNOWN;
    @Nullable
    public String mMutualContext;
    public String mName;
    @Nullable
    public Integer mNumMutualFriends;
    @Nullable
    public SocialUserPresenceStatus mPresenceStatus;
    public String mPresenceString;
    public String mProfilePhotoURL;
    public UserRowType mUserType;

    public enum UserRowType {
        BLOCKED_INVITED,
        BLOCKED_USER,
        FRIEND,
        INVITED,
        INCOMING_FRIEND_REQUEST,
        PARTY_MEMBER,
        PARTY_CONTROLS,
        PEOPLE_NEARBY,
        SUGGESTED_FRIEND
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SocialUser socialUser = (SocialUser) obj;
            if (!this.mID.equals(socialUser.mID) || !Objects.equals(this.mName, socialUser.mName) || !Objects.equals(this.mFriendship, socialUser.mFriendship) || !Objects.equals(this.mDeepLink, socialUser.mDeepLink) || !Objects.equals(this.mPresenceString, socialUser.mPresenceString) || !Objects.equals(this.mPresenceStatus, socialUser.mPresenceStatus) || !Objects.equals(this.mProfilePhotoURL, socialUser.mProfilePhotoURL) || !Objects.equals(this.mMutualContext, socialUser.mMutualContext) || !Objects.equals(this.mCurrentPartyID, socialUser.mCurrentPartyID) || !Objects.equals(this.mLastActive, socialUser.mLastActive) || !Objects.equals(this.mMicrophoneChannel, socialUser.mMicrophoneChannel) || this.mCanViewerMessage != socialUser.mCanViewerMessage || this.mIsPartyFull != socialUser.mIsPartyFull || this.mUserType != socialUser.mUserType) {
                return false;
            }
        }
        return true;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public boolean getCanViewerMessage() {
        return this.mCanViewerMessage;
    }

    @Nullable
    public String getCurrentPartyID() {
        return this.mCurrentPartyID;
    }

    @Nullable
    public String getCurrentPartyJoinPolicy() {
        return this.mCurrentPartyJoinPolicy;
    }

    @Nullable
    public SocialDeeplinkPresence getDeepLink() {
        return this.mDeepLink;
    }

    public SocialUserFriendshipStatus getFriendship() {
        SocialUserFriendshipStatus socialUserFriendshipStatus = this.mFriendship;
        if (socialUserFriendshipStatus == null) {
            return SocialUserFriendshipStatus.UNKNOWN;
        }
        return socialUserFriendshipStatus;
    }

    public String getID() {
        return this.mID;
    }

    @Nullable
    public String getInvitedToLocalPartyBySenderID() {
        return this.mInvitedToLocalPartyBySenderID;
    }

    public boolean getIsActive(Resources resources) {
        SocialUserPresenceStatus socialUserPresenceStatus = this.mPresenceStatus;
        if (socialUserPresenceStatus == null) {
            String str = this.mPresenceString;
            if (str == null || str.isEmpty() || str.contains(resources.getString(R.string.anytime_tablet_social_user_presence_string_offline))) {
                return false;
            }
            return true;
        } else if (socialUserPresenceStatus == SocialUserPresenceStatus.ONLINE) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getIsInRoom() {
        return this.mIsInRoom;
    }

    public boolean getIsPartyFull() {
        return this.mIsPartyFull;
    }

    @Nullable
    public String getLastActive() {
        return this.mLastActive;
    }

    public PartyMicrophoneState getMicrophoneChannel() {
        return this.mMicrophoneChannel;
    }

    @Nullable
    public String getMutualContext() {
        return this.mMutualContext;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public Integer getNumMutualFriends() {
        return this.mNumMutualFriends;
    }

    public ProfilePresenceType getPresenceIconType() {
        return ProfilePresenceType.NONE;
    }

    @Nullable
    public SocialUserPresenceStatus getPresenceStatus() {
        return this.mPresenceStatus;
    }

    public String getPresenceString() {
        return this.mPresenceString;
    }

    public String getProfilePhotoURL() {
        return this.mProfilePhotoURL;
    }

    public UserRowType getUserType() {
        return this.mUserType;
    }

    public int hashCode() {
        return Objects.hash(this.mID, this.mName, this.mPresenceString, this.mPresenceStatus, this.mProfilePhotoURL, this.mUserType);
    }

    public boolean isInJoinableParty() {
        return "DIRECT_JOIN".equals(this.mCurrentPartyJoinPolicy);
    }

    public String toString() {
        String obj;
        boolean z = false;
        String str = this.mID;
        String str2 = this.mName;
        String str3 = this.mAlias;
        String str4 = this.mPresenceString;
        if (this.mDeepLink != null) {
            z = true;
        }
        Boolean valueOf = Boolean.valueOf(z);
        SocialUserFriendshipStatus socialUserFriendshipStatus = this.mFriendship;
        if (socialUserFriendshipStatus == null) {
            obj = "null";
        } else {
            obj = socialUserFriendshipStatus.toString();
        }
        return String.format("%s: id: %s, name: %s, alias: %s, presence: %s, has deepLink: %b, friendStatus: %s", "SocialUser", str, str2, str3, str4, valueOf, obj);
    }

    public SocialUser(String str, String str2, String str3, String str4, String str5, boolean z, UserRowType userRowType) {
        this.mID = str;
        this.mName = str2;
        this.mAlias = str3;
        this.mPresenceString = str4;
        this.mProfilePhotoURL = str5;
        this.mCanViewerMessage = z;
        this.mUserType = userRowType;
        this.mIsPartyFull = false;
    }

    public void setNumMutualFriends(int i) {
        this.mNumMutualFriends = Integer.valueOf(i);
    }

    public void setPresenceStatus(String str) {
        this.mPresenceStatus = SocialUserPresenceStatus.valueOf(str);
    }

    public void setCurrentPartyID(String str) {
        this.mCurrentPartyID = str;
    }

    public void setCurrentPartyJoinPolicy(String str) {
        this.mCurrentPartyJoinPolicy = str;
    }

    public void setDeepLink(SocialDeeplinkPresence socialDeeplinkPresence) {
        this.mDeepLink = socialDeeplinkPresence;
    }

    public void setFriendship(SocialUserFriendshipStatus socialUserFriendshipStatus) {
        this.mFriendship = socialUserFriendshipStatus;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setInvitedToLocalPartyBySenderID(String str) {
        this.mInvitedToLocalPartyBySenderID = str;
    }

    public void setIsInRoom(boolean z) {
        this.mIsInRoom = z;
    }

    public void setIsPartyFull(boolean z) {
        this.mIsPartyFull = z;
    }

    public void setLastActive(String str) {
        this.mLastActive = str;
    }

    public void setMicrophoneChannel(PartyMicrophoneState partyMicrophoneState) {
        this.mMicrophoneChannel = partyMicrophoneState;
    }

    public void setMutualContext(String str) {
        this.mMutualContext = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setPresenceString(String str) {
        this.mPresenceString = str;
    }

    public void setProfilePhotoURL(String str) {
        this.mProfilePhotoURL = str;
    }

    public void setUserType(UserRowType userRowType) {
        this.mUserType = userRowType;
    }
}
