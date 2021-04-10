package com.oculus.horizon.api.login;

import com.oculus.http.core.base.ApiRequest;
import javax.annotation.Nullable;

public class LoginRequest extends ApiRequest<LoginResponse> {
    public final String email;
    @Nullable
    public final String fb_access_token;
    public final String password;

    public LoginRequest(String str, String str2, @Nullable String str3) {
        this.email = str;
        this.password = str2;
        this.fb_access_token = str3;
    }
}
