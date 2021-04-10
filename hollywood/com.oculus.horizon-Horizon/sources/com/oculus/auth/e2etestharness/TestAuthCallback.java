package com.oculus.auth.e2etestharness;

import X.AnonymousClass0NO;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthResultCallback;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TestAuthCallback<T, E extends AuthError> implements AuthResultCallback<T, E> {
    public static final String TAG = "TestAuthCallback";
    public final SettableFuture<Bundle> mFuture;
    public final Bundle mResult;

    @Override // com.oculus.authapi.AuthResultCallback
    public void onError(E e) {
        StringBuilder sb = new StringBuilder("Received error, code: ");
        sb.append(e.mErrorCode);
        sb.append(", title: [");
        sb.append(e.mErrorTitle);
        sb.append("], message: [");
        sb.append(e.getMessage());
        sb.append("]");
        String obj = sb.toString();
        AnonymousClass0NO.A0B(TAG, obj, e);
        this.mResult.putString(Constants.EXTRA_KEY_ERROR, obj);
        this.mResult.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
        this.mFuture.set(this.mResult);
    }

    @Override // com.oculus.authapi.AuthResultCallback
    public void onResult(@Nullable T t) {
        this.mResult.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, true);
        this.mFuture.set(this.mResult);
    }

    public TestAuthCallback(Bundle bundle, SettableFuture<Bundle> settableFuture) {
        this.mResult = bundle;
        this.mFuture = settableFuture;
    }
}
