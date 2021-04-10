package com.oculus.authapi;

import android.os.Bundle;

public final class AuthFbLoginError extends AuthError {
    private final String mAuthToken;
    private final String mLoginFirstFactor;
    private final String mMachineId;
    private final String mUid;

    AuthFbLoginError(Bundle resultData) {
        super(resultData);
        this.mUid = resultData.getString("uid");
        this.mMachineId = resultData.getString("machine_id");
        this.mAuthToken = resultData.getString("auth_token");
        this.mLoginFirstFactor = resultData.getString("login_first_factor");
    }

    AuthFbLoginError(AuthError basicError) {
        super(basicError.getErrorCode(), basicError.getErrorTitle(), basicError.getMessage());
        this.mUid = null;
        this.mMachineId = null;
        this.mAuthToken = null;
        this.mLoginFirstFactor = null;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getMachineId() {
        return this.mMachineId;
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }

    public String getLoginFirstFactor() {
        return this.mLoginFirstFactor;
    }
}
