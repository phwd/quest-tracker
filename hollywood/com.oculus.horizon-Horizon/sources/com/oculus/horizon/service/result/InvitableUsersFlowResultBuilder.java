package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import android.annotation.SuppressLint;
import android.content.Intent;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID"})
@SuppressLint({"DeprecatedSuperclass"})
public class InvitableUsersFlowResultBuilder extends ResultBuilderNoBundle<Intent> {
    public static final Class<?> TAG = InvitableUsersFlowResultBuilder.class;
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public InvitableUsersFlowResultBuilder(AbstractC06640p5 r3, @Assisted OVRService oVRService) {
        super(oVRService);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
