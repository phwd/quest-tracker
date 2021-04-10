package com.oculus.auth.e2etestharness;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TestOculusAuthCallback extends TestAuthCallback<AuthCredentials, AuthError> {
    public TestOculusAuthCallback(Bundle bundle, SettableFuture<Bundle> settableFuture) {
        super(bundle, settableFuture);
    }

    public void onResult(@Nullable AuthCredentials authCredentials) {
        this.mResult.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, true);
        if (authCredentials != null) {
            this.mResult.putString("userId", authCredentials.mUserId);
            this.mResult.putString(Constants.EXTRA_KEY_AUTH_TOKEN, authCredentials.mAccessToken);
        }
        this.mFuture.set(this.mResult);
    }
}
