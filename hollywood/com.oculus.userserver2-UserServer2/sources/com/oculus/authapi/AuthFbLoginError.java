package com.oculus.authapi;

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
}
