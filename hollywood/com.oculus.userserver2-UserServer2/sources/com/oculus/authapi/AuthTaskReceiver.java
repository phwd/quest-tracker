package com.oculus.authapi;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.authapi.AuthError;

public abstract class AuthTaskReceiver<T, E extends AuthError> extends ResultReceiver {
    public static final String TAG = "AuthTaskReceiver";
    public final AuthResultCallback<T, E> mAuthResultCallback;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.oculus.authapi.AuthTaskReceiver<T, E extends com.oculus.authapi.AuthError> */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.oculus.authapi.AuthResultCallback<T, E extends com.oculus.authapi.AuthError> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == -1) {
            try {
                this.mAuthResultCallback.A2b(new AuthCredentials(bundle));
            } catch (AuthError e) {
                A00(e);
            }
        } else {
            A00(new AuthError(bundle));
        }
    }

    private void A00(E e) {
        StringBuilder sb = new StringBuilder("Received error, code: ");
        sb.append(e.mErrorCode);
        sb.append(", title: [");
        sb.append(e.mErrorTitle);
        sb.append("], message: [");
        sb.append(e.getMessage());
        sb.append("]");
        Log.e(TAG, sb.toString(), e);
        this.mAuthResultCallback.A2R(e);
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
