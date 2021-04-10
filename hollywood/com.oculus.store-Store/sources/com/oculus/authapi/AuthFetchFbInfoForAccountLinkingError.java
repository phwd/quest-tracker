package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AuthFetchFbInfoForAccountLinkingError extends AuthError {
    @Nullable
    private final String mAccessToken;
    @Nullable
    private final String mUserId;

    AuthFetchFbInfoForAccountLinkingError(Bundle resultData) {
        super(resultData);
        this.mAccessToken = resultData.getString("access_token");
        this.mUserId = resultData.getString(ServiceContract.EXTRA_USER_ID);
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
