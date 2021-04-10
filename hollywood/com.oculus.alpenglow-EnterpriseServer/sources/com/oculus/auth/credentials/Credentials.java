package com.oculus.auth.credentials;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class Credentials {
    public final String mAccessToken;
    @Nullable
    public final ExpirableToken mDeviceScopedAccessToken;
    public final String mUserId;

    public String getAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    public ExpirableToken getDeviceScopedAccessToken() {
        return this.mDeviceScopedAccessToken;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public Credentials(String str, String str2) {
        this.mUserId = str;
        this.mAccessToken = str2;
        this.mDeviceScopedAccessToken = null;
    }

    public Credentials(String str, String str2, @Nullable ExpirableToken expirableToken) {
        this.mUserId = str;
        this.mAccessToken = str2;
        this.mDeviceScopedAccessToken = expirableToken;
    }
}
