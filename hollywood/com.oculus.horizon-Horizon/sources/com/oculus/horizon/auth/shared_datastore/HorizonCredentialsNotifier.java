package com.oculus.horizon.auth.shared_datastore;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.CredentialsChangedHandler;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class HorizonCredentialsNotifier implements CredentialsChangedHandler {
    public static final String ACTION_ACCESS_TOKEN_CHANGED = "com.oculus.horizon.ACCESS_TOKEN_CHANGED";
    public static final String ACTION_DEVICE_SCOPED_ACCESS_TOKEN_CHANGED = "com.oculus.horizon.DEVICE_SCOPED_ACCESS_TOKEN_CHANGED";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // com.oculus.auth.credentials.CredentialsChangedHandler
    public final void onCredentialsChanged() {
        ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).sendBroadcast(new Intent(ACTION_ACCESS_TOKEN_CHANGED));
    }

    @Override // com.oculus.auth.credentials.CredentialsChangedHandler
    public final void onDeviceScopedCredentialsChanged() {
        ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).sendBroadcast(new Intent(ACTION_DEVICE_SCOPED_ACCESS_TOKEN_CHANGED));
    }

    @Inject
    public HorizonCredentialsNotifier(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
