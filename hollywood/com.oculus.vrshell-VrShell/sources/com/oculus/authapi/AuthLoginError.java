package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class AuthLoginError extends AuthError {
    private final String mNonce;
    @Nullable
    private final ArrayList<AuthTwoFactorMethod> mTwoFactorMethods;

    public AuthLoginError(Bundle bundle) {
        super(bundle);
        this.mTwoFactorMethods = AuthTwoFactorMethod.unmarshallParcelableList(bundle.getByteArray(ServiceContract.EXTRA_TWO_FACTOR_METHODS), AuthTwoFactorMethod.CREATOR);
        this.mNonce = bundle.getString(ServiceContract.EXTRA_NONCE);
    }

    public ArrayList<AuthTwoFactorMethod> getTwoFactorMethods() {
        return this.mTwoFactorMethods;
    }

    public String getNonce() {
        return this.mNonce;
    }
}
