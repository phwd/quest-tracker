package com.oculus.horizon.usermanagerupdater;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_userserver_api_inject_OculusUserManagerProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class UserManagerUpdater {
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public UserManagerUpdater(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
