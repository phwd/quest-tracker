package com.oculus.authapi;

import android.os.Bundle;

public final class AuthFbLoginResult {
    private final String mAccessToken;
    private final String mUid;

    AuthFbLoginResult(Bundle resultData) throws AuthError {
        String uid = resultData.getString("uid");
        if (uid == null) {
            throw new AuthError(-7, "UID missing from FB login result", "UID missing from FB login result");
        }
        String accessToken = resultData.getString("access_token");
        if (accessToken == null) {
            throw new AuthError(-7, "Access token missing from FB login result", "Access token missing from FB login result");
        }
        this.mUid = uid;
        this.mAccessToken = accessToken;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }
}
