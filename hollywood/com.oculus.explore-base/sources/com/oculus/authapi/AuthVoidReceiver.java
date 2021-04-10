package com.oculus.authapi;

import android.os.Bundle;

final class AuthVoidReceiver extends AuthTaskReceiver<Void, AuthError> {
    AuthVoidReceiver(AuthResultCallback<Void, AuthError> resultCallback) {
        super(resultCallback);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public Void createResultFromBundle(Bundle resultData) {
        return null;
    }
}
