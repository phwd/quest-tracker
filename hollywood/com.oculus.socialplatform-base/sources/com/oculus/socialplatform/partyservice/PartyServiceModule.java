package com.oculus.socialplatform.partyservice;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.vr_lifecycle.VRLifecycleManager;
import com.oculus.partystatemanager.PartyStateManager;
import com.oculus.socialplatform.auth.SocialPlatformCredentialsManager;
import com.oculus.socialplatform.sharedstate.PartySharedState;
import javax.inject.Provider;

@InjectorModule
public class PartyServiceModule extends AnonymousClass0VI {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_socialplatform_partyservice_PartyNativeModuleManager_ULSEP_BINDING_ID = 71;
        public static final int _UL__ULSEP_com_oculus_socialplatform_partyservice_PartyServiceManager_ULSEP_BINDING_ID = 17;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_socialplatform_partyservice_PartyServiceManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(17, r1);
    }

    @AutoGeneratedAccessMethod
    public static final PartyServiceManager _UL__ULSEP_com_oculus_socialplatform_partyservice_PartyServiceManager_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (PartyServiceManager) AnonymousClass1TK.A00(17, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_socialplatform_partyservice_PartyServiceManager_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(17, r1);
    }

    @ProviderMethod
    public static PartyServiceManager providePartyServiceManager(@ForAppContext Context context, PartyNativeModuleManager partyNativeModuleManager, SocialPlatformCredentialsManager socialPlatformCredentialsManager, VRLifecycleManager vRLifecycleManager, SocialMethods socialMethods, PartySharedState partySharedState) {
        return new PartyServiceManager(context, new PartyStateManager(partyNativeModuleManager, socialMethods), socialPlatformCredentialsManager, vRLifecycleManager, partySharedState, partyNativeModuleManager);
    }

    @AutoGeneratedFactoryMethod
    public static final PartyServiceManager _UL__ULSEP_com_oculus_socialplatform_partyservice_PartyServiceManager_ULSEP_FACTORY_METHOD(AnonymousClass0lg r6, Object obj) {
        PartyServiceManager providePartyServiceManager = providePartyServiceManager(C00610Hs.A00(r6), PartyNativeModuleManager._UL__ULSEP_com_oculus_socialplatform_partyservice_PartyNativeModuleManager_ULSEP_ACCESS_METHOD(r6), SocialPlatformCredentialsManager._UL__ULSEP_com_oculus_socialplatform_auth_SocialPlatformCredentialsManager_ULSEP_ACCESS_METHOD(r6), VRLifecycleManager._UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleManager_ULSEP_ACCESS_METHOD(r6), SocialMethods._UL__ULSEP_com_oculus_horizon_social_SocialMethods_ULSEP_ACCESS_METHOD(r6), PartySharedState._UL__ULSEP_com_oculus_socialplatform_sharedstate_PartySharedState_ULSEP_ACCESS_METHOD(r6));
        C01150Rm.A00(providePartyServiceManager, r6);
        return providePartyServiceManager;
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForPartyServiceModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
