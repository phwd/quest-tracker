package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class AuthCredentials {
    @Nullable
    public final String mAccessToken;
    @Nullable
    public final String mUserId;

    @Nullable
    public String getAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    public String getUserId() {
        return this.mUserId;
    }

    public AuthCredentials(Bundle bundle) {
        this.mUserId = bundle.getString("user_id");
        this.mAccessToken = bundle.getString("access_token");
    }

    public AuthCredentials(String str, String str2) {
        this.mUserId = str;
        this.mAccessToken = str2;
    }
}
