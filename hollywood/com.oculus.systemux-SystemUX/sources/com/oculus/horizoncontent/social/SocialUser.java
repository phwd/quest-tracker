package com.oculus.horizoncontent.social;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.facebook.debug.log.LoggingUtil;
import com.oculus.common.horizoncontent.R;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import java.util.Objects;

public class SocialUser {
    private String mAlias;
    private boolean mCanViewerMessage;
    @Nullable
    private String mCurrentPartyID;
    @Nullable
    private String mCurrentPartyJoinPolicy;
    @Nullable
    private SocialDeeplinkPresence mDeepLink;
    @Nullable
    private SocialUserFriendshipStatus mFriendship;
    private String mID;
    @Nullable
    private String mInvitedToLocalPartyBySenderID;
    private boolean mIsInRoom;
    private boolean mIsPartyFull = false;
    @Nullable
    private String mLastActive;
    @Nullable
    private String mMutualContext;
    private String mName;
    @Nullable
    private Integer mNumMutualFriends;
    @Nullable
    private SocialUserPresenceStatus mPresenceStatus;
    private String mPresenceString;
    private String mProfilePhotoURL;
    private UserRowType mUserType;

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

    public SocialUser(String str, String str2, String str3, String str4, String str5, boolean z, UserRowType userRowType) {
        this.mID = str;
        this.mName = str2;
        this.mAlias = str3;
        this.mPresenceString = str4;
        this.mProfilePhotoURL = str5;
        this.mCanViewerMessage = z;
        this.mUserType = userRowType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SocialUser socialUser = (SocialUser) obj;
        return this.mID.equals(socialUser.mID) && Objects.equals(this.mName, socialUser.mName) && Objects.equals(this.mFriendship, socialUser.mFriendship) && Objects.equals(this.mDeepLink, socialUser.mDeepLink) && Objects.equals(this.mPresenceString, socialUser.mPresenceString) && Objects.equals(this.mPresenceStatus, socialUser.mPresenceStatus) && Objects.equals(this.mProfilePhotoURL, socialUser.mProfilePhotoURL) && Objects.equals(this.mMutualContext, socialUser.mMutualContext) && Objects.equals(this.mCurrentPartyID, socialUser.mCurrentPartyID) && Objects.equals(this.mLastActive, socialUser.mLastActive) && this.mCanViewerMessage == socialUser.mCanViewerMessage && this.mIsPartyFull == socialUser.mIsPartyFull && this.mUserType == socialUser.mUserType;
    }

    public int hashCode() {
        return Objects.hash(this.mID, this.mName, this.mPresenceString, this.mPresenceStatus, this.mProfilePhotoURL, this.mUserType);
    }

    public String toString() {
        String str;
        Object[] objArr = new Object[7];
        boolean z = false;
        objArr[0] = SocialUser.class.getSimpleName();
        objArr[1] = this.mID;
        objArr[2] = this.mName;
        objArr[3] = this.mAlias;
        objArr[4] = this.mPresenceString;
        if (getDeepLink() != null) {
            z = true;
        }
        objArr[5] = Boolean.valueOf(z);
        SocialUserFriendshipStatus socialUserFriendshipStatus = this.mFriendship;
        if (socialUserFriendshipStatus == null) {
            str = LoggingUtil.NO_HASHCODE;
        } else {
            str = socialUserFriendshipStatus.toString();
        }
        objArr[6] = str;
        return String.format("%s: id: %s, name: %s, alias: %s, presence: %s, has deepLink: %b, friendStatus: %s", objArr);
    }

    public String getID() {
        return this.mID;
    }

    public void setID(String str) {
        this.mID = str;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getPresenceString() {
        return this.mPresenceString;
    }

    public void setPresenceString(String str) {
        this.mPresenceString = str;
    }

    @Nullable
    public SocialUserPresenceStatus getPresenceStatus() {
        return this.mPresenceStatus;
    }

    public void setPresenceStatus(String str) {
        this.mPresenceStatus = SocialUserPresenceStatus.valueOf(str);
    }

    public String getProfilePhotoURL() {
        return this.mProfilePhotoURL;
    }

    public void setProfilePhotoURL(String str) {
        this.mProfilePhotoURL = str;
    }

    public void setUserType(UserRowType userRowType) {
        this.mUserType = userRowType;
    }

    @Nullable
    public String getMutualContext() {
        return this.mMutualContext;
    }

    public void setMutualContext(String str) {
        this.mMutualContext = str;
    }

    public boolean getIsActive(Resources resources) {
        SocialUserPresenceStatus socialUserPresenceStatus = this.mPresenceStatus;
        if (socialUserPresenceStatus != null) {
            return socialUserPresenceStatus == SocialUserPresenceStatus.ONLINE;
        }
        String str = this.mPresenceString;
        return str != null && !str.isEmpty() && !this.mPresenceString.contains(resources.getString(R.string.anytime_tablet_social_user_presence_string_offline));
    }

    public UserRowType getUserType() {
        return this.mUserType;
    }

    public void setFriendship(SocialUserFriendshipStatus socialUserFriendshipStatus) {
        this.mFriendship = socialUserFriendshipStatus;
    }

    public SocialUserFriendshipStatus getFriendship() {
        SocialUserFriendshipStatus socialUserFriendshipStatus = this.mFriendship;
        return socialUserFriendshipStatus != null ? socialUserFriendshipStatus : SocialUserFriendshipStatus.UNKNOWN;
    }

    public boolean getIsInRoom() {
        return this.mIsInRoom;
    }

    public void setDeepLink(SocialDeeplinkPresence socialDeeplinkPresence) {
        this.mDeepLink = socialDeeplinkPresence;
    }

    @Nullable
    public SocialDeeplinkPresence getDeepLink() {
        return this.mDeepLink;
    }

    @Nullable
    public String getLastActive() {
        return this.mLastActive;
    }

    public void setLastActive(String str) {
        this.mLastActive = str;
    }

    @Nullable
    public String getCurrentPartyID() {
        return this.mCurrentPartyID;
    }

    public void setCurrentPartyID(String str) {
        this.mCurrentPartyID = str;
    }

    public void setCurrentPartyJoinPolicy(String str) {
        this.mCurrentPartyJoinPolicy = str;
    }

    @Nullable
    public String getCurrentPartyJoinPolicy() {
        return this.mCurrentPartyJoinPolicy;
    }

    public boolean isInJoinableParty() {
        return "DIRECT_JOIN".equals(this.mCurrentPartyJoinPolicy);
    }

    public boolean getIsPartyFull() {
        return this.mIsPartyFull;
    }

    public void setIsPartyFull(boolean z) {
        this.mIsPartyFull = z;
    }

    public boolean getCanViewerMessage() {
        return this.mCanViewerMessage;
    }

    @Nullable
    public String getInvitedToLocalPartyBySenderID() {
        return this.mInvitedToLocalPartyBySenderID;
    }

    public void setInvitedToLocalPartyBySenderID(String str) {
        this.mInvitedToLocalPartyBySenderID = str;
    }

    public void setIsInRoom(boolean z) {
        this.mIsInRoom = z;
    }

    public ProfilePresenceType getPresenceIconType() {
        return ProfilePresenceType.NONE;
    }

    public void setNumMutualFriends(int i) {
        this.mNumMutualFriends = Integer.valueOf(i);
    }

    @Nullable
    public Integer getNumMutualFriends() {
        return this.mNumMutualFriends;
    }
}
