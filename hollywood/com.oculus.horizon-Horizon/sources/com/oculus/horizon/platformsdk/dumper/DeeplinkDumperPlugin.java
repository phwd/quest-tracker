package com.oculus.horizon.platformsdk.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass0dM;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_BINDING_ID"})
public class DeeplinkDumperPlugin implements AnonymousClass0dM {
    public static final String NAME = "deeplink";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public DeeplinkDumperPlugin(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
