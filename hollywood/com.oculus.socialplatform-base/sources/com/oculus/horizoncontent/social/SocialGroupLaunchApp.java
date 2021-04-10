package com.oculus.horizoncontent.social;

public class SocialGroupLaunchApp {
    public int mCurrentPartyEntitlementsCount;
    public int mCurrentPartyUsersCount;
    public String mDisplayName;
    public String mDisplayShortDescription;
    public String mId;
    public String mImageLandscapeUrl;
    public String mImageSquareUrl;
    public int mMaxGroupLaunchCapacity;

    public int getCurrentPartyEntitlementsCount() {
        return this.mCurrentPartyEntitlementsCount;
    }

    public int getCurrentPartyUsersCount() {
        return this.mCurrentPartyUsersCount;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getDisplayShortDescription() {
        return this.mDisplayShortDescription;
    }

    public String getID() {
        return this.mId;
    }

    public String getImageSquareUrl() {
        return this.mImageSquareUrl;
    }

    public String getLandscapeImageUrl() {
        return this.mImageLandscapeUrl;
    }

    public int getMaxGroupLaunchCapacity() {
        return this.mMaxGroupLaunchCapacity;
    }

    public SocialGroupLaunchApp(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3) {
        this.mId = str;
        this.mDisplayName = str2;
        this.mDisplayShortDescription = str3;
        this.mImageLandscapeUrl = str4;
        this.mImageSquareUrl = str5;
        this.mMaxGroupLaunchCapacity = i;
        this.mCurrentPartyEntitlementsCount = i2;
        this.mCurrentPartyUsersCount = i3;
    }
}
