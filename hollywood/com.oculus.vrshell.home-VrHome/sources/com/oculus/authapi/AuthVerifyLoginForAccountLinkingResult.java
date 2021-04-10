package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthVerifyLoginForAccountLinkingResult {
    @Nullable
    private final String mAccessToken;
    @Nullable
    private final String mUserId;

    AuthVerifyLoginForAccountLinkingResult(Bundle resultData) {
        this.mUserId = resultData.getString("user_id");
        this.mAccessToken = resultData.getString("access_token");
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
