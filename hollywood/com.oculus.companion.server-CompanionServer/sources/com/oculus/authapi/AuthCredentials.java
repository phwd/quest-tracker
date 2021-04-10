package com.oculus.authapi;

import android.os.Bundle;

public final class AuthCredentials {
    private final String mAccessToken;
    private final String mUserId;

    AuthCredentials(Bundle bundle) {
        this.mUserId = bundle.getString("user_id");
        this.mAccessToken = bundle.getString("access_token");
    }
}
