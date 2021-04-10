package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.ExternalPlatformNetwork;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformNetwork_ULSEP_BINDING_ID"})
public class AppScopedAccessTokenResultBuilder extends ResultBuilder {
    @Inject
    @Eager
    public final ExternalPlatformNetwork mExternalPlatformNetwork;

    @Inject
    public AppScopedAccessTokenResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService) {
        super(oVRService);
        this.mExternalPlatformNetwork = ExternalPlatformNetwork._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformNetwork_ULSEP_ACCESS_METHOD(r2);
    }
}
