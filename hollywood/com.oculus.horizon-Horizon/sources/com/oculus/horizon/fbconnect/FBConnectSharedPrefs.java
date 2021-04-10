package com.oculus.horizon.fbconnect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FBConnectSharedPrefs {
    public static final String KEY_MAYBE_ACCESS_TOKEN_VALID = "maybe_access_token_valid";
}
