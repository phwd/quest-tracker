package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginError extends AuthError {
    @Nullable
    private final String mAuthToken;
    @Nullable
    private final String mLoginFirstFactor;
    @Nullable
    private final String mMachineId;
    @Nullable
    private final String mUid;

    AuthFbLoginError(Bundle bundle) {
        super(bundle);
        this.mUid = bundle.getString("uid");
        this.mMachineId = bundle.getString(ServiceContract.EXTRA_MACHINE_ID);
        this.mAuthToken = bundle.getString(ServiceContract.EXTRA_AUTH_TOKEN);
        this.mLoginFirstFactor = bundle.getString(ServiceContract.EXTRA_LOGIN_FIRST_FACTOR);
    }

    AuthFbLoginError(AuthError authError) {
        super(authError.getErrorCode(), authError.getErrorTitle(), authError.getMessage());
        this.mUid = null;
        this.mMachineId = null;
        this.mAuthToken = null;
        this.mLoginFirstFactor = null;
    }

    @Nullable
    public String getUid() {
        return this.mUid;
    }

    @Nullable
    public String getMachineId() {
        return this.mMachineId;
    }

    @Nullable
    public String getAuthToken() {
        return this.mAuthToken;
    }

    @Nullable
    public String getLoginFirstFactor() {
        return this.mLoginFirstFactor;
    }
}
