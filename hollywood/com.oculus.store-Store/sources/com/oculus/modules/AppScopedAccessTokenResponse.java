package com.oculus.modules;

import androidx.annotation.Nullable;

/* compiled from: SocialModule */
class AppScopedAccessTokenResponse {
    @Nullable
    private String accessToken;
    @Nullable
    private String error;

    public AppScopedAccessTokenResponse(@Nullable String accessToken2, @Nullable String error2) {
        this.accessToken = accessToken2;
        this.error = error2;
    }
}
