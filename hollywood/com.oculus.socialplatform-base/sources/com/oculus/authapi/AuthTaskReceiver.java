package com.oculus.authapi;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

public abstract class AuthTaskReceiver<T, E extends AuthError> extends ResultReceiver {
    public static final String TAG = "AuthTaskReceiver";
    public final AuthResultCallback<T, E> mAuthResultCallback;

    public E createErrorFromBundle(Bundle bundle) {
        return null;
    }

    @Nullable
    public abstract T createResultFromBundle(Bundle bundle) throws AuthError;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.oculus.authapi.AuthTaskReceiver<T, E extends com.oculus.authapi.AuthError> */
    /* JADX WARN: Multi-variable type inference failed */
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
        StringBuilder sb = new StringBuilder("Received error, code: ");
        sb.append(e.mErrorCode);
        sb.append(", title: [");
        sb.append(e.mErrorTitle);
        sb.append("], message: [");
        sb.append(e.getMessage());
        sb.append("]");
        Log.e(TAG, sb.toString(), e);
        this.mAuthResultCallback.onError(e);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AuthTaskReceiver(com.oculus.authapi.AuthResultCallback<T, E> r2) {
        /*
            r1 = this;
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto L_0x000d
            r0 = 0
        L_0x0007:
            r1.<init>(r0)
            r1.mAuthResultCallback = r2
            return
        L_0x000d:
            android.os.Handler r0 = new android.os.Handler
            r0.<init>()
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.authapi.AuthTaskReceiver.<init>(com.oculus.authapi.AuthResultCallback):void");
    }
}
