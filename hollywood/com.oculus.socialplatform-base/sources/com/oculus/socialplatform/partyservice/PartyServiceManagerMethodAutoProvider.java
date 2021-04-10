package com.oculus.socialplatform.partyservice;

import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.vr_lifecycle.VRLifecycleManager;
import com.oculus.socialplatform.auth.SocialPlatformCredentialsManager;
import com.oculus.socialplatform.sharedstate.PartySharedState;

@Generated({"By: InjectorProcessor"})
public class PartyServiceManagerMethodAutoProvider extends AnonymousClass0VG<PartyServiceManager> {
    public PartyServiceManager get() {
        return PartyServiceModule.providePartyServiceManager(C00610Hs.A00(this), PartyNativeModuleManager._UL__ULSEP_com_oculus_socialplatform_partyservice_PartyNativeModuleManager_ULSEP_ACCESS_METHOD(this), SocialPlatformCredentialsManager._UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_ACCESS_METHOD(this), VRLifecycleManager._UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULSEP_ACCESS_METHOD(this), SocialMethods._UL__ULSEP_com_oculus_horizon_social_SocialMethods_ULSEP_ACCESS_METHOD(this), PartySharedState._UL__ULSEP_com_oculus_socialplatform_sharedstate_PartySharedState_ULSEP_ACCESS_METHOD(this));
    }
}
