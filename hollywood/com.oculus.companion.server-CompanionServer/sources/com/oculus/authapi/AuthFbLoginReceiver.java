package com.oculus.authapi;

import android.os.Bundle;

final class AuthFbLoginReceiver extends AuthTaskReceiver<AuthFbLoginResult, AuthError> {
    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public AuthFbLoginResult createResultFromBundle(Bundle bundle) throws AuthError {
        return new AuthFbLoginResult(bundle);
    }
}
