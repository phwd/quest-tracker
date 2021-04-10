package com.oculus.vrcast.googlecast.net.auth;

import com.oculus.vrcast.googlecast.net.auth.AuthContext;

public class AuthException extends Exception {
    private final AuthContext.AuthResult mError;

    public AuthException(AuthContext.AuthResult authResult) {
        super("Could not authenticate cast device, error = " + authResult);
        this.mError = authResult;
    }

    public AuthContext.AuthResult getError() {
        return this.mError;
    }
}
