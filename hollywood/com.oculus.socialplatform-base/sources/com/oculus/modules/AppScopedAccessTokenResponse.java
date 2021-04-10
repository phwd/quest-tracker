package com.oculus.modules;

import androidx.annotation.Nullable;

public class AppScopedAccessTokenResponse {
    @Nullable
    public String accessToken;
    @Nullable
    public String error;

    public AppScopedAccessTokenResponse(@Nullable String str, @Nullable String str2) {
        this.accessToken = str;
        this.error = str2;
    }
}
