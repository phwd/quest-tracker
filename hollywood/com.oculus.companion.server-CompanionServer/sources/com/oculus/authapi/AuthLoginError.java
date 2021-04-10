package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import java.util.ArrayList;

public class AuthLoginError extends AuthError {
    private final String mNonce;
    private final ArrayList<AuthTwoFactorMethod> mTwoFactorMethods;

    public AuthLoginError(Bundle bundle) {
        super(bundle);
        this.mTwoFactorMethods = AuthTwoFactorMethod.unmarshallParcelableList(bundle.getByteArray("two_factor_methods"), AuthTwoFactorMethod.CREATOR);
        this.mNonce = bundle.getString("nonce");
    }
}
