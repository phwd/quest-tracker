package com.oculus.horizoncontent.social;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public final class SocialGroupLaunchAppDestination {
    public String mAppDisplayName;
    public String mAppID;
    public boolean mAppIsViewerEntitled;
    public String mCursor;
    public String mDeeplinkMessage;
    public String mDestinationDisplayName;
    public String mGroupLaunchID;
    public SocialGroupLaunchState mGroupLaunchState;
    public boolean mHasNext;
    public String mId;
    public String mImageUrl;
    public String mIsExternalDeeplinkable;
    public boolean mIsOnlyQuestUsersAccepted;
    public int mMaxCapacity;
    public Map<String, SocialGroupLaunchResponse> mUserResponses;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = (SocialGroupLaunchAppDestination) obj;
            if (!this.mGroupLaunchID.equals(socialGroupLaunchAppDestination.mGroupLaunchID) || !this.mGroupLaunchState.equals(socialGroupLaunchAppDestination.mGroupLaunchState) || !this.mAppDisplayName.equals(socialGroupLaunchAppDestination.mAppDisplayName) || !this.mDeeplinkMessage.equals(socialGroupLaunchAppDestination.mDeeplinkMessage) || !Objects.equals(this.mDestinationDisplayName, socialGroupLaunchAppDestination.mDestinationDisplayName) || !Objects.equals(this.mId, socialGroupLaunchAppDestination.mId) || !Objects.equals(this.mImageUrl, socialGroupLaunchAppDestination.mImageUrl) || !Objects.equals(Integer.valueOf(this.mMaxCapacity), Integer.valueOf(socialGroupLaunchAppDestination.mMaxCapacity)) || !Objects.equals(Boolean.valueOf(this.mIsOnlyQuestUsersAccepted), Boolean.valueOf(socialGroupLaunchAppDestination.mIsOnlyQuestUsersAccepted)) || !this.mIsExternalDeeplinkable.equals(socialGroupLaunchAppDestination.mIsExternalDeeplinkable)) {
                return false;
            }
        }
        return true;
    }

    public Map<String, SocialGroupLaunchResponse> getAllUserResponses() {
        return this.mUserResponses;
    }

    public String getAppDisplayName() {
        return this.mAppDisplayName;
    }

    public String getAppID() {
        return this.mAppID;
    }

    public boolean getAppIsViewerEntitled() {
        return this.mAppIsViewerEntitled;
    }

    public String getCursor() {
        return this.mCursor;
    }

    public String getDestinationDisplayName() {
        return this.mDestinationDisplayName;
    }

    public String getGroupLaunchID() {
        return this.mGroupLaunchID;
    }

    public SocialGroupLaunchState getGroupLaunchState() {
        return this.mGroupLaunchState;
    }

    public String getID() {
        return this.mId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getIsExternalDeeplinkable() {
        return this.mIsExternalDeeplinkable;
    }

    public boolean getIsOnlyQuestUsersAccepted() {
        return this.mIsOnlyQuestUsersAccepted;
    }

    public int getMaxCapacity() {
        return this.mMaxCapacity;
    }

    public int getNumAcceptedUsers() {
        int i = 0;
        for (SocialGroupLaunchResponse socialGroupLaunchResponse : this.mUserResponses.values()) {
            if (socialGroupLaunchResponse == SocialGroupLaunchResponse.ACCEPT) {
                i++;
            }
        }
        return i;
    }

    public SocialGroupLaunchResponse getUserResponse(String str) {
        return this.mUserResponses.get(str);
    }

    public boolean hasNext() {
        return this.mHasNext;
    }

    public int hashCode() {
        return Objects.hash(this.mDestinationDisplayName, this.mId, this.mImageUrl, Integer.valueOf(this.mMaxCapacity), this.mGroupLaunchID);
    }

    public String toString() {
        return String.format(Locale.ROOT, "%s: id: %s, appDisplayName: $s, destinationDisplayName: %s, destinationName: %s, maxCapacity: %d, iconURL: %s, isExternalDeeplinkable: %s", "SocialGroupLaunchAppDestination", this.mId, this.mAppDisplayName, this.mDestinationDisplayName, Integer.valueOf(this.mMaxCapacity), this.mImageUrl, this.mIsExternalDeeplinkable);
    }

    public SocialGroupLaunchAppDestination(String str, SocialGroupLaunchState socialGroupLaunchState, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, int i, Map<String, SocialGroupLaunchResponse> map, boolean z2, String str8, String str9, boolean z3) {
        this.mGroupLaunchID = str;
        this.mGroupLaunchState = socialGroupLaunchState;
        this.mAppID = str2;
        this.mAppDisplayName = str3;
        this.mAppIsViewerEntitled = z;
        this.mDeeplinkMessage = str4;
        this.mDestinationDisplayName = str5;
        this.mId = str6;
        this.mImageUrl = str7;
        this.mMaxCapacity = i;
        this.mUserResponses = map;
        this.mIsOnlyQuestUsersAccepted = z2;
        this.mIsExternalDeeplinkable = str8;
        this.mCursor = str9;
        this.mHasNext = z3;
    }

    public void setID(String str) {
        this.mId = str;
    }

    public void setUserResponse(Map<String, SocialGroupLaunchResponse> map) {
        this.mUserResponses = map;
    }
}
