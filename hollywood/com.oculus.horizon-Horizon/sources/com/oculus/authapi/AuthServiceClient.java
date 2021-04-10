package com.oculus.authapi;

import android.content.Context;
import com.oculus.aidl.IAuthService2;
import com.oculus.binder.BinderServiceClient;
import com.oculus.binder.BindingStrategy;

public class AuthServiceClient {
    public final BindingStrategy mBindingStrategy;
    public final Context mContext;

    public static final class InternalClient extends BinderServiceClient<IAuthService2> {
    }

    public AuthServiceClient(Context context) {
        String str;
        BindingStrategy bindingStrategy = BindingStrategy.DEFAULT;
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
