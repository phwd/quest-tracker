package com.oculus.remotewipe;

import X.AbstractC02990bJ;
import X.AnonymousClass0R7;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_remotewipe_WipeTelemetry_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_remotewipe_IRemoteWipeManager_ULSEP_BINDING_ID"})
public class CompanionBinder {
    public static final String COMPANION_SERVER = "com.oculus.companion.server.CompanionServer";
    public static final String FAIL_BIND_FALSE = "bindService to CompanionServer false.";
    public static final String FAIL_REMOTE_EXCEPTION = "RemoteException from CompanionServer.";
    public static final String TAG = "CompanionBinder";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Inject
    public CompanionBinder(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(3, r3);
    }
}
