package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.auth.service.contract.ServiceContract;
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
        this.mUserId = bundle.getString(ServiceContract.EXTRA_USER_ID);
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
