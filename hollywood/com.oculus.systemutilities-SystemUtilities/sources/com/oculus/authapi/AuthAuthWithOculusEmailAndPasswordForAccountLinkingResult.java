package com.oculus.authapi;

import android.os.Bundle;

public final class AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult {
    private final String mAccessToken;
    private final String mUserId;

    AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult(Bundle resultData) {
        this.mUserId = resultData.getString("user_id");
        this.mAccessToken = resultData.getString("access_token");
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }
}
