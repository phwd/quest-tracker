package com.oculus.authapi;

import android.net.Uri;
import com.oculus.horizon.logging.LoggingKeys;

public final class AuthPrivateContract {
    public static final String AUTHORITY_AUTHENTICATOR_CONTROL = "com.oculus.auth.authenticator.control";
    public static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_TYPE = "type";
    public static final String METHOD_ADD_ACCOUNT = "add_account";
    public static final Uri URI_AUTHENTICATOR_CONTROL = new Uri.Builder().scheme(LoggingKeys.REFERRER_CONTENT).authority(AUTHORITY_AUTHENTICATOR_CONTROL).build();

    public AuthPrivateContract() {
        throw new AssertionError();
    }
}
