package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class AuthVoidReceiver extends AuthTaskReceiver<Void, AuthError> {
    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public Void createResultFromBundle(Bundle bundle) {
        return null;
    }

    AuthVoidReceiver(AuthResultCallback<Void, AuthError> authResultCallback) {
        super(authResultCallback);
    }
}
