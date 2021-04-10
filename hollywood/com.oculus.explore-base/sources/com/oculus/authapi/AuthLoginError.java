package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import java.util.ArrayList;

public class AuthLoginError extends AuthError {
    private final String mNonce;
    private final ArrayList<AuthTwoFactorMethod> mTwoFactorMethods;

    public AuthLoginError(Bundle resultData) {
        super(resultData);
        this.mTwoFactorMethods = AuthTwoFactorMethod.unmarshallParcelableList(resultData.getByteArray("two_factor_methods"), AuthTwoFactorMethod.CREATOR);
        this.mNonce = resultData.getString("nonce");
    }

    public ArrayList<AuthTwoFactorMethod> getTwoFactorMethods() {
        return this.mTwoFactorMethods;
    }

    public String getNonce() {
        return this.mNonce;
    }
}
