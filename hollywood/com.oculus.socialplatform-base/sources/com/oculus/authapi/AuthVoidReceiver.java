package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthVoidReceiver extends AuthTaskReceiver<Void, AuthError> {
    @Override // com.oculus.authapi.AuthTaskReceiver
    public Void createResultFromBundle(Bundle bundle) {
        return null;
    }

    public AuthVoidReceiver(AuthResultCallback<Void, AuthError> authResultCallback) {
        super(authResultCallback);
    }
}
