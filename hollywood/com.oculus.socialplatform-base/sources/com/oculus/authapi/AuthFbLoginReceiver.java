package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginReceiver extends AuthTaskReceiver<AuthFbLoginResult, AuthError> {
    public AuthFbLoginReceiver(AuthResultCallback<AuthFbLoginResult, AuthError> authResultCallback) {
        super(authResultCallback);
    }

    @Override // com.oculus.authapi.AuthTaskReceiver
    public AuthFbLoginResult createResultFromBundle(Bundle bundle) throws AuthError {
        return new AuthFbLoginResult(bundle);
    }
}
