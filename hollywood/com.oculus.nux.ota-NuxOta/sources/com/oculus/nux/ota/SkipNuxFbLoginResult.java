package com.oculus.nux.ota;

import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthFbLoginResult;

public class SkipNuxFbLoginResult {
    private AuthFbLoginError error;
    private AuthFbLoginResult result;

    public SkipNuxFbLoginResult(AuthFbLoginError authFbLoginError) {
        this.error = authFbLoginError;
    }

    public SkipNuxFbLoginResult(AuthFbLoginResult authFbLoginResult) {
        this.result = authFbLoginResult;
    }

    public AuthFbLoginError getError() {
        return this.error;
    }

    public AuthFbLoginResult getResult() {
        return this.result;
    }
}
