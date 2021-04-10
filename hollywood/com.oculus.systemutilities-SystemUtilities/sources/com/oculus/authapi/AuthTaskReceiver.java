package com.oculus.authapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.authapi.AuthError;

abstract class AuthTaskReceiver<T, E extends AuthError> extends ResultReceiver {
    private static final String TAG = AuthTaskReceiver.class.getSimpleName();
    protected final AuthResultCallback<T, E> mAuthResultCallback;

    /* access modifiers changed from: protected */
    public abstract T createResultFromBundle(Bundle bundle) throws AuthError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthTaskReceiver(AuthResultCallback<T, E> authResultCallback) {
        super(Looper.myLooper() == null ? null : new Handler());
        this.mAuthResultCallback = authResultCallback;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.oculus.authapi.AuthTaskReceiver<T, E extends com.oculus.authapi.AuthError> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == -1) {
            try {
                this.mAuthResultCallback.onResult(createResultFromBundle(resultData));
            } catch (AuthError error) {
                onReceiveError(error);
            }
        } else {
            AuthError error2 = createErrorFromBundle(resultData);
            if (error2 == null) {
                error2 = new AuthError(resultData);
            }
            onReceiveError(error2);
        }
    }

    private void onReceiveError(E error) {
        Log.e(TAG, "Received error, code: " + error.getErrorCode() + ", title: [" + error.getErrorTitle() + "], message: [" + error.getMessage() + "]", error);
        this.mAuthResultCallback.onError(error);
    }

    /* access modifiers changed from: protected */
    public E createErrorFromBundle(Bundle resultData) {
        return null;
    }
}
