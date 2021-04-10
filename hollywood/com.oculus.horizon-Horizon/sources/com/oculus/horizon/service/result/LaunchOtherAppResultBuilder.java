package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_BINDING_ID"})
public class LaunchOtherAppResultBuilder extends ResultBuilder {
    public static final Class<?> TAG = LaunchOtherAppResultBuilder.class;
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public LaunchOtherAppResultBuilder(AbstractC06640p5 r3, @Assisted OVRService oVRService) {
        super(oVRService);
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
