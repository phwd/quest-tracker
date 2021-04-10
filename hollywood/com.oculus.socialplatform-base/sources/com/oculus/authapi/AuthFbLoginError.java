package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginError extends AuthError {
    @Nullable
    public final String mAuthToken;
    @Nullable
    public final String mLoginFirstFactor;
    @Nullable
    public final String mMachineId;
    @Nullable
    public final String mUid;

    @Nullable
    public String getAuthToken() {
        return this.mAuthToken;
    }

    @Nullable
    public String getLoginFirstFactor() {
        return this.mLoginFirstFactor;
    }

    @Nullable
    public String getMachineId() {
        return this.mMachineId;
    }

    @Nullable
    public String getUid() {
        return this.mUid;
    }

    public AuthFbLoginError(Bundle bundle) {
        super(bundle);
        this.mUid = bundle.getString("uid");
        this.mMachineId = bundle.getString("machine_id");
        this.mAuthToken = bundle.getString(ServiceContract.EXTRA_AUTH_TOKEN);
        this.mLoginFirstFactor = bundle.getString(ServiceContract.EXTRA_LOGIN_FIRST_FACTOR);
    }

    public AuthFbLoginError(AuthError authError) {
        super(authError.mErrorCode, authError.mErrorTitle, authError.getMessage());
        this.mUid = null;
        this.mMachineId = null;
        this.mAuthToken = null;
        this.mLoginFirstFactor = null;
    }
}
