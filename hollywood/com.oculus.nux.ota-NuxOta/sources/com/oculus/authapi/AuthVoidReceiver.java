package com.oculus.authapi;

import android.os.Bundle;

final class AuthVoidReceiver extends AuthTaskReceiver<Void, AuthError> {
    /* access modifiers changed from: protected */
    @Override // com.oculus.authapi.AuthTaskReceiver
    public Void createResultFromBundle(Bundle bundle) {
        return null;
    }
}
