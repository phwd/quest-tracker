package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class AuthCredentials {
    @Nullable
    private final String mAccessToken;
    @Nullable
    private final String mUserId;

    public AuthCredentials(String str, String str2) {
        this.mUserId = str;
        this.mAccessToken = str2;
    }

    AuthCredentials(Bundle bundle) {
        this.mUserId = bundle.getString("user_id");
        this.mAccessToken = bundle.getString("access_token");
    }

    @Nullable
    public String getUserId() {
        return this.mUserId;
    }

    @Nullable
    public String getAccessToken() {
        return this.mAccessToken;
    }
}
