package com.oculus.auth.credentials;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class Credentials {
    private final String mAccessToken;
    @Nullable
    private final ExpirableToken mDeviceScopedAccessToken;
    private final String mUserId;

    public Credentials(String userId, String accessToken) {
        this.mUserId = userId;
        this.mAccessToken = accessToken;
        this.mDeviceScopedAccessToken = null;
    }

    public Credentials(String userId, String accessToken, @Nullable ExpirableToken deviceScopedAccessToken) {
        this.mUserId = userId;
        this.mAccessToken = accessToken;
        this.mDeviceScopedAccessToken = deviceScopedAccessToken;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    public ExpirableToken getDeviceScopedAccessToken() {
        return this.mDeviceScopedAccessToken;
    }
}
