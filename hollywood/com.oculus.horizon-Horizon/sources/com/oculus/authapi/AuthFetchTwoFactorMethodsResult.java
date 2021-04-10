package com.oculus.authapi;

import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class AuthFetchTwoFactorMethodsResult {
    public final String mNonce;
    @Nullable
    public final ArrayList<AuthTwoFactorMethod> mTwoFactorMethods;
}
