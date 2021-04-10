package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_components_DsatHelper_ULSEP_BINDING_ID"})
public class DeviceScopedAccessTokenResultBuilder extends ResultBuilder {
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public DeviceScopedAccessTokenResultBuilder(AbstractC06640p5 r3, @Assisted OVRService oVRService) {
        super(oVRService);
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
