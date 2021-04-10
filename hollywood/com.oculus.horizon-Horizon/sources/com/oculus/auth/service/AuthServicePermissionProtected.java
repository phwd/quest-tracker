package com.oculus.auth.service;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.service.contract.ServiceContract;

public class AuthServicePermissionProtected extends IntentService {
    public static final String TAG = "AuthServicePermissionProtected";
    @Inject
    @Eager
    public AuthManager mAuthManager;

    public AuthServicePermissionProtected() {
        super(TAG);
    }

    public static final void _UL_injectMe(Context context, AuthServicePermissionProtected authServicePermissionProtected) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), authServicePermissionProtected);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, AuthServicePermissionProtected authServicePermissionProtected) {
        authServicePermissionProtected.mAuthManager = AuthManager._UL__ULSEP_com_oculus_auth_service_AuthManager_ULSEP_ACCESS_METHOD(r0);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            AnonymousClass0NO.A08(TAG, "Intent action is null");
        } else if (action.hashCode() != 86604526 || !action.equals(ServiceContract.ACTION_LOGIN)) {
            AnonymousClass0NO.A0E(TAG, "not supported action: %s", action);
        } else {
            this.mAuthManager.performBackdoorLogin(intent);
        }
    }
}
