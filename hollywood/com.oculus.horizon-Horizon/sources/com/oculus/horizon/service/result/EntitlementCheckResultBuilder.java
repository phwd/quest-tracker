package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID"})
public class EntitlementCheckResultBuilder extends ResultBuilder {
    @Inject
    @Eager
    public final ExternalPlatformLocal mExternalPlatformLocal;

    @Inject
    public EntitlementCheckResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService) {
        super(oVRService);
        this.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r2);
    }
}
