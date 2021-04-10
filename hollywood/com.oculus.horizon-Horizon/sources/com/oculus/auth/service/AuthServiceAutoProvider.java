package com.oculus.auth.service;

import X.AnonymousClass0J9;

public class AuthServiceAutoProvider extends AnonymousClass0J9<AuthService> {
    public boolean equals(Object obj) {
        return obj instanceof AuthServiceAutoProvider;
    }

    public void inject(AuthService authService) {
        AuthService._UL_staticInjectMe(this, authService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        AuthService._UL_staticInjectMe(this, (AuthService) obj);
    }
}
