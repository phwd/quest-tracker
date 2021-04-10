package com.oculus.authapi;

import android.os.Bundle;

public final class AuthFbLoginError extends AuthError {
    private final String mAuthToken;
    private final String mLoginFirstFactor;
    private final String mMachineId;
    private final String mUid;

    AuthFbLoginError(Bundle bundle) {
        super(bundle);
        this.mUid = bundle.getString("uid");
        this.mMachineId = bundle.getString("machine_id");
        this.mAuthToken = bundle.getString("auth_token");
        this.mLoginFirstFactor = bundle.getString("login_first_factor");
    }

    AuthFbLoginError(AuthError authError) {
        super(authError.getErrorCode(), authError.getErrorTitle(), authError.getMessage());
        this.mUid = null;
        this.mMachineId = null;
        this.mAuthToken = null;
        this.mLoginFirstFactor = null;
    }
}
