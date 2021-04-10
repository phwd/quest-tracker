package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class AuthCredentials {
    @Nullable
    public final String mAccessToken;
    @Nullable
    public final String mUserId;

    public AuthCredentials(Bundle bundle) {
        this.mUserId = bundle.getString(ServiceContract.EXTRA_USER_ID);
        this.mAccessToken = bundle.getString("access_token");
    }
}
