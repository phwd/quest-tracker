package com.oculus.horizon.fbconnect;

public class FacebookData {
    public final String mAccessToken;
    public final String mId;
    public final String mName;

    public FacebookData(String str, String str2, String str3) {
        this.mId = str;
        this.mName = str2;
        this.mAccessToken = str3;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }
}
