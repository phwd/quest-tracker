package com.oculus.authapi;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.aidl.IAuthService2;
import com.oculus.binder.BinderServiceClient;
import com.oculus.binder.BindingStrategy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AuthServiceClient {
    public final BindingStrategy mBindingStrategy;
    public final Context mContext;

    public static final class InternalClient extends BinderServiceClient<IAuthService2> {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InternalClient(android.content.Context r5, com.oculus.binder.BindingStrategy r6) throws android.os.RemoteException {
            /*
                r4 = this;
                android.content.Intent r3 = new android.content.Intent
                r3.<init>()
                java.lang.String r2 = "com.oculus.horizon"
                java.lang.String r1 = "com.oculus.auth.service.AuthService2"
                android.content.ComponentName r0 = new android.content.ComponentName
                r0.<init>(r2, r1)
                r3.setComponent(r0)
                r4.<init>(r5, r3, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.authapi.AuthServiceClient.InternalClient.<init>(android.content.Context, com.oculus.binder.BindingStrategy):void");
        }
    }

    public final AuthCredentials A00() throws RemoteException, InterruptedException {
        InternalClient internalClient = new InternalClient(this.mContext, this.mBindingStrategy);
        try {
            internalClient.mNotMainThreadVerifier.run();
            try {
                Bundle credentials = internalClient.mFuture.get(BinderServiceClient.DEFAULT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS).getCredentials();
                if (credentials == null) {
                    credentials = new Bundle();
                }
                AuthCredentials authCredentials = new AuthCredentials(credentials);
                internalClient.close();
                return authCredentials;
            } catch (ExecutionException | TimeoutException e) {
                RemoteException remoteException = new RemoteException("Error waiting for service");
                remoteException.initCause(e);
                Log.e(BinderServiceClient.TAG, "Error awaiting service", e);
                throw remoteException;
            }
        } catch (Throwable unused) {
        }
        throw th;
    }

    public AuthServiceClient(Context context, BindingStrategy bindingStrategy) {
        String str;
        if (context == null) {
            str = "context is null";
        } else if (bindingStrategy != null) {
            this.mContext = context;
            this.mBindingStrategy = bindingStrategy;
            return;
        } else {
            str = "bindingStrategy is null";
        }
        throw new NullPointerException(str);
    }
}
