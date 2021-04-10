package com.oculus.auth.e2etestharness;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthFbLoginResult;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TestFbAuthCallback extends TestAuthCallback<AuthFbLoginResult, AuthFbLoginError> {
    public TestFbAuthCallback(Bundle bundle, SettableFuture<Bundle> settableFuture) {
        super(bundle, settableFuture);
    }

    public void onResult(@Nullable AuthFbLoginResult authFbLoginResult) {
        this.mResult.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, true);
        if (authFbLoginResult != null) {
            this.mResult.putString("userId", authFbLoginResult.mUid);
            this.mResult.putString(Constants.EXTRA_KEY_AUTH_TOKEN, authFbLoginResult.mAccessToken);
        }
        this.mFuture.set(this.mResult);
    }
}
