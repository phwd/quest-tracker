package com.oculus.auth.e2etestharness;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthLoginError;
import com.oculus.authapi.AuthResultCallback;

public class TestResultReceiver<T, E extends AuthError> extends ResultReceiver {
    public final AuthResultCallback<T, E> mAuthResultCallback;
    public Bundle mResult;

    public TestResultReceiver(AuthResultCallback<T, E> authResultCallback, Bundle bundle) {
        super(null);
        this.mResult = bundle;
        this.mAuthResultCallback = authResultCallback;
    }

    public void onReceiveResult(int i, Bundle bundle) {
        if (i == -1) {
            if (bundle != null) {
                String string = bundle.getString("user_id", "");
                if (!"".equals(string)) {
                    this.mResult.putString("userId", string);
                    bundle.remove("user_id");
                }
                this.mResult.putAll(bundle);
            }
            this.mAuthResultCallback.onResult(null);
            return;
        }
        this.mAuthResultCallback.onError(new AuthLoginError(bundle));
    }
}
