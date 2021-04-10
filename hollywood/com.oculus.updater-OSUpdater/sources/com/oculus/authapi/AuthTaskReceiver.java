package com.oculus.authapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public abstract class AuthTaskReceiver<T, E extends AuthError> extends ResultReceiver {
    private static final String TAG = "AuthTaskReceiver";
    protected final AuthResultCallback<T, E> mAuthResultCallback;

    /* access modifiers changed from: protected */
    public E createErrorFromBundle(Bundle bundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T createResultFromBundle(Bundle bundle) throws AuthError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthTaskReceiver(AuthResultCallback<T, E> authResultCallback) {
        super(Looper.myLooper() == null ? null : new Handler());
        this.mAuthResultCallback = authResultCallback;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.oculus.authapi.AuthTaskReceiver<T, E extends com.oculus.authapi.AuthError> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        if (i == -1) {
            try {
                this.mAuthResultCallback.onResult(createResultFromBundle(bundle));
            } catch (AuthError e) {
                onReceiveError(e);
            }
        } else {
            AuthError createErrorFromBundle = createErrorFromBundle(bundle);
            if (createErrorFromBundle == null) {
                createErrorFromBundle = new AuthError(bundle);
            }
            onReceiveError(createErrorFromBundle);
        }
    }

    private void onReceiveError(E e) {
        String str = TAG;
        Log.e(str, "Received error, code: " + e.getErrorCode() + ", title: [" + e.getErrorTitle() + "], message: [" + e.getMessage() + "]", e);
        this.mAuthResultCallback.onError(e);
    }
}
