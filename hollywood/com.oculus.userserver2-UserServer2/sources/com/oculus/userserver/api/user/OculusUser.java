package com.oculus.userserver.api.user;

public final class OculusUser {
    public final long mCreationTime;
    public final boolean mIsSuspended;
    public final String mPictureUri;
    public final int mUserId;
    public final String mUserName;

    public final String toString() {
        StringBuilder sb = new StringBuilder("OculusUser: {id: ");
        sb.append(this.mUserId);
        sb.append(", username: ");
        sb.append(this.mUserName);
        sb.append(", creationTime: ");
        sb.append(this.mCreationTime);
        sb.append(", pictureUri: ");
        sb.append(this.mPictureUri);
        sb.append(", isSuspended: ");
        sb.append(this.mIsSuspended);
        sb.append("}");
        return sb.toString();
    }

    public OculusUser(int i, String str, String str2, long j, boolean z) {
        this.mUserId = i;
        this.mUserName = str;
        this.mPictureUri = str2;
        this.mCreationTime = j;
        this.mIsSuspended = z;
    }
}
