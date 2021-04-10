package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult {
    @Nullable
    public final String mAccessToken;
    @Nullable
    public final String mUserId;

    public AuthAuthWithOculusEmailAndPasswordForAccountLinkingResult(Bundle bundle) {
        this.mUserId = bundle.getString("user_id");
        this.mAccessToken = bundle.getString("access_token");
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
