package com.oculus.userserver.api.user;

public final class OculusUser {
    private final long mCreationTime;
    private final String mPictureUri;
    private final int mUserId;
    private final String mUserName;

    public OculusUser(int i, String str, String str2, long j) {
        this.mUserId = i;
        this.mUserName = str;
        this.mPictureUri = str2;
        this.mCreationTime = j;
    }

    public int getUserId() {
        return this.mUserId;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getPictureUri() {
        return this.mPictureUri;
    }

    public long getCreationTime() {
        return this.mCreationTime;
    }

    public String toString() {
        return "OculusUser: {id: " + this.mUserId + ", username: " + this.mUserName + ", creationTime: " + this.mCreationTime + ", pictureUri: " + this.mPictureUri + "}";
    }
}
