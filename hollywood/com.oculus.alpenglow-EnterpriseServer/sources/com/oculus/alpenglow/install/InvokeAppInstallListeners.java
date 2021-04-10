package com.oculus.alpenglow.install;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.managed.ManagedMode;
import java.util.Set;

@Dependencies({"_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Set_ULLT_com_oculus_alpenglow_install_AppInstallListener_ULGT__ULSEP_BINDING_ID"})
public class InvokeAppInstallListeners {
    public static final String TAG = "EnterpriseServer.InvokeAppInstallListeners";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public final void A00() {
        Object A03;
        AnonymousClass0R7 r2 = this._UL_mInjectionContext;
        if (((ManagedMode) AnonymousClass0Lh.A03(0, 86, r2)).isEnterpriseModeEnabled && (A03 = AnonymousClass0Lh.A03(1, 43, r2)) != null) {
            for (AppInstallListener appInstallListener : (Set) A03) {
                try {
                    appInstallListener.A68();
                } catch (Exception e) {
                    AnonymousClass0NK.A04(TAG, "exception in app install listener", e);
                }
            }
        }
    }

    @Inject
    public InvokeAppInstallListeners(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}
