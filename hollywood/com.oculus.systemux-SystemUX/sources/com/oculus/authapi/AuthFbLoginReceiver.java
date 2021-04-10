package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class AuthFbLoginReceiver extends AuthTaskReceiver<AuthFbLoginResult, AuthError> {
    AuthFbLoginReceiver(AuthResultCallback<AuthFbLoginResult, AuthError> authResultCallback) {
        super(authResultCallback);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public AuthFbLoginResult createResultFromBundle(Bundle bundle) throws AuthError {
        return new AuthFbLoginResult(bundle);
    }
}
