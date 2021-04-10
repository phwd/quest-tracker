package com.oculus.authapi;

import android.os.Bundle;

public final class AuthFetchFbInfoForAccountLinkingError extends AuthError {
    private final String mAccessToken;
    private final String mUserId;

    AuthFetchFbInfoForAccountLinkingError(Bundle resultData) {
        super(resultData);
        this.mAccessToken = resultData.getString("access_token");
        this.mUserId = resultData.getString("user_id");
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getUserId() {
        return this.mUserId;
    }
}
