package com.oculus.authapi;

import android.os.Bundle;

final class AuthFbLoginReceiver extends AuthTaskReceiver<AuthFbLoginResult, AuthError> {
    AuthFbLoginReceiver(AuthResultCallback<AuthFbLoginResult, AuthError> resultCallback) {
        super(resultCallback);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public AuthFbLoginResult createResultFromBundle(Bundle resultData) throws AuthError {
        return new AuthFbLoginResult(resultData);
    }
}
