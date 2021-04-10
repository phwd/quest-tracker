package com.oculus.authapi;

import android.os.Bundle;

public final class AuthFetchFbInfoForAccountLinkingResult {
    private final String mEmail;
    private final String mName;
    private final String mProfilePicUri;

    AuthFetchFbInfoForAccountLinkingResult(Bundle resultData) {
        this.mName = resultData.getString("name");
        this.mEmail = resultData.getString("email");
        this.mProfilePicUri = resultData.getString("profile_pic_uri");
    }

    public String getName() {
        return this.mName;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }
}
