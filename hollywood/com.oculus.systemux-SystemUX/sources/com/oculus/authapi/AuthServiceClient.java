package com.oculus.authapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.oculus.aidl.IAuthService2;
import com.oculus.auth.service.contract.ServiceContract;
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

    public AuthCredentials getCredentials() throws RemoteException, InterruptedException {
        InternalClient internalClient = new InternalClient(this.mContext, this.mBindingStrategy);
        try {
            Bundle credentials = ((IAuthService2) internalClient.awaitService()).getCredentials();
            if (credentials == null) {
                credentials = new Bundle();
            }
            AuthCredentials authCredentials = new AuthCredentials(credentials);
            internalClient.close();
            return authCredentials;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static final class InternalClient extends BinderServiceClient<IAuthService2> {
        InternalClient(Context context, BindingStrategy bindingStrategy) throws RemoteException {
            super(context, newIntent(), bindingStrategy);
        }

        private static Intent newIntent() {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BuildConstants.PACKAGE_NAME_HORIZON, ServiceContract.BOUND_SERVICE));
            return intent;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.binder.BinderServiceClient
        public IAuthService2 asInterface(IBinder iBinder) {
            return IAuthService2.Stub.asInterface(iBinder);
        }
    }
}
