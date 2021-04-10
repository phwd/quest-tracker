package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class AuthFetchTwoFactorMethodsResult {
    public final String mNonce;
    @Nullable
    public final ArrayList<AuthTwoFactorMethod> mTwoFactorMethods;

    public AuthFetchTwoFactorMethodsResult(Bundle bundle) {
        this.mTwoFactorMethods = AuthTwoFactorMethod.unmarshallParcelableList(bundle.getByteArray(ServiceContract.EXTRA_TWO_FACTOR_METHODS), AuthTwoFactorMethod.CREATOR);
        this.mNonce = bundle.getString("nonce");
    }

    public String getNonce() {
        return this.mNonce;
    }

    public ArrayList<AuthTwoFactorMethod> getTwoFactorMethods() {
        return this.mTwoFactorMethods;
    }
}
