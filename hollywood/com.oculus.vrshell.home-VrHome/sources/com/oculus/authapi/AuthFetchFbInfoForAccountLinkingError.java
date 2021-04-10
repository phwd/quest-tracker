package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFetchFbInfoForAccountLinkingError extends AuthError {
    @Nullable
    private final String mAccessToken;
    @Nullable
    private final String mUserId;

    AuthFetchFbInfoForAccountLinkingError(Bundle resultData) {
        super(resultData);
        this.mAccessToken = resultData.getString("access_token");
        this.mUserId = resultData.getString("user_id");
    }

    @Nullable
    public String getAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    public String getUserId() {
        return this.mUserId;
    }
}
