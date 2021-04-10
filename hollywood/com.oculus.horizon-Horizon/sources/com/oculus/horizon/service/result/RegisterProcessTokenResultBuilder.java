package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service.OVRService;
import com.oculus.processtokentracker.ProcessTokenTracker;

@Dependencies({"_UL__ULSEP_com_oculus_processtokentracker_ProcessTokenTracker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID"})
public class RegisterProcessTokenResultBuilder extends ResultBuilder {
    public static final String BINDER_TOKEN_KEY = "token";
    public static final Class<?> TAG = RegisterProcessTokenResultBuilder.class;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final ProcessTokenTracker mProcessTokenTracker;

    @Inject
    public RegisterProcessTokenResultBuilder(AbstractC06640p5 r3, @Assisted OVRService oVRService) {
        super(oVRService);
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mProcessTokenTracker = (ProcessTokenTracker) AnonymousClass117.A00(389, r3);
    }
}
