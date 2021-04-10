package com.oculus.authapi;

import android.content.Context;
import android.os.IBinder;
import com.oculus.aidl.IAuthService2;
import com.oculus.binder.BinderServiceClient;
import com.oculus.binder.BindingStrategy;

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

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // com.oculus.binder.BinderServiceClient
        public final IAuthService2 A00(IBinder iBinder) {
            return IAuthService2.Stub.asInterface(iBinder);
        }
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
