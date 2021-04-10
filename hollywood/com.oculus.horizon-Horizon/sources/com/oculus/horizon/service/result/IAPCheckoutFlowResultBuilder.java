package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.C003809k;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID"})
public class IAPCheckoutFlowResultBuilder extends ResultBuilderNoBundle<Intent> {
    public static final Uri INTENT_DATA = Uri.parse("apk://com.oculus.vrshell.home");
    public static final Class<?> TAG = IAPCheckoutFlowResultBuilder.class;
    @Inject
    @Eager
    public final ExternalPlatformLocal mExternalPlatformLocal;
    public final String mOfferID;
    @Inject
    @Eager
    public final PackageManager mPackageManager;

    @Inject
    public IAPCheckoutFlowResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService, @Assisted String str) {
        super(oVRService);
        this.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r2);
        this.mPackageManager = C003809k.A03(r2);
        this.mOfferID = str;
    }
}
