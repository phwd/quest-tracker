package com.oculus.auth.credentials;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class Credentials {
    private final String mAccessToken;
    @Nullable
    private final ExpirableToken mDeviceScopedAccessToken = null;
    private final String mUserId;

    public Credentials(String str, String str2) {
        this.mUserId = str;
        this.mAccessToken = str2;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }
}
