package com.oculus.authapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.oculus.aidl.IAuthService2;
import com.oculus.binder.BinderServiceClient;
import com.oculus.binder.BindingStrategy;
import com.oculus.common.build.BuildConstants;

public class AuthServiceClient {
    private final BindingStrategy mBindingStrategy;
    private final Context mContext;

    public AuthServiceClient(Context context) {
        this(context, BindingStrategy.DEFAULT);
    }

    public AuthServiceClient(Context context, BindingStrategy bindingStrategy) {
        if (context == null) {
            throw new NullPointerException("context is null");
        } else if (bindingStrategy != null) {
            this.mContext = context;
            this.mBindingStrategy = bindingStrategy;
        } else {
            throw new NullPointerException("bindingStrategy is null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.authapi.AuthCredentials getCredentials() throws android.os.RemoteException, java.lang.InterruptedException {
        /*
            r2 = this;
            com.oculus.authapi.AuthServiceClient$InternalClient r0 = new com.oculus.authapi.AuthServiceClient$InternalClient
            android.content.Context r1 = r2.mContext
            com.oculus.binder.BindingStrategy r2 = r2.mBindingStrategy
            r0.<init>(r1, r2)
            java.lang.Object r2 = r0.awaitService()     // Catch:{ all -> 0x0024 }
            com.oculus.aidl.IAuthService2 r2 = (com.oculus.aidl.IAuthService2) r2     // Catch:{ all -> 0x0024 }
            android.os.Bundle r2 = r2.getCredentials()     // Catch:{ all -> 0x0024 }
            com.oculus.authapi.AuthCredentials r1 = new com.oculus.authapi.AuthCredentials     // Catch:{ all -> 0x0024 }
            if (r2 == 0) goto L_0x0018
            goto L_0x001d
        L_0x0018:
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ all -> 0x0024 }
            r2.<init>()     // Catch:{ all -> 0x0024 }
        L_0x001d:
            r1.<init>(r2)     // Catch:{ all -> 0x0024 }
            r0.close()
            return r1
        L_0x0024:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x002f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.authapi.AuthServiceClient.getCredentials():com.oculus.authapi.AuthCredentials");
    }

    private static final class InternalClient extends BinderServiceClient<IAuthService2> {
        InternalClient(Context context, BindingStrategy bindingStrategy) throws RemoteException {
            super(context, newIntent(), bindingStrategy);
        }

        private static Intent newIntent() {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BuildConstants.PACKAGE_NAME_HORIZON, "com.oculus.auth.service.AuthService2"));
            return intent;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.binder.BinderServiceClient
        public IAuthService2 asInterface(IBinder iBinder) {
            return IAuthService2.Stub.asInterface(iBinder);
        }
    }
}
