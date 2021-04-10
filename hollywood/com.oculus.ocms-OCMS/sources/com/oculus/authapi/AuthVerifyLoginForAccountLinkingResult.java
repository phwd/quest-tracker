package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AuthVerifyLoginForAccountLinkingResult {
    @Nullable
    private final String mAccessToken;
    @Nullable
    private final String mUserId;

    AuthVerifyLoginForAccountLinkingResult(Bundle bundle) {
        this.mUserId = bundle.getString("user_id");
        this.mAccessToken = bundle.getString("access_token");
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
