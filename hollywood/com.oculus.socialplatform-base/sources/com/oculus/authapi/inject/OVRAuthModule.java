package com.oculus.authapi.inject;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qj;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VB;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.authapi.OVRAuth;
import javax.inject.Provider;

@InjectorModule
public class OVRAuthModule extends AnonymousClass0VI {
    public static volatile OVRAuth _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_BINDING_ID = 2055;
        public static final int _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID = 63;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID = 2098;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID = 84;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_authapi_AuthServiceClient_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2055, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_authapi_OVRAuth_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(63, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AuthServiceClient _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (AuthServiceClient) AnonymousClass1TK.A00(2055, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final OVRAuth _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OVRAuth) AnonymousClass1TK.A00(63, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final OVRAuth _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_FACTORY_METHOD(AnonymousClass0lg r6, Object obj) {
        if (_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE == null) {
            synchronized (OVRAuth.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE, r6);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r6.getApplicationInjector();
                        OVRAuth oVRAuth = new OVRAuth(C00610Hs.A00(applicationInjector), CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(oVRAuth, applicationInjector);
                        _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE = oVRAuth;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_authapi_AuthServiceClient_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2055, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_authapi_OVRAuth_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(63, r2);
    }

    @ProviderMethod
    public static AuthServiceClient provideAuthServiceClient(@ForAppContext Context context) {
        return new AuthServiceClient(context);
    }

    @ApplicationScoped
    @ProviderMethod
    public static OVRAuth provideOVRAuth(@ForAppContext Context context, CallerInfoProviderImpl callerInfoProviderImpl) {
        return new OVRAuth(context, callerInfoProviderImpl);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForOVRAuthModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }

    @AutoGeneratedFactoryMethod
    public static final AuthServiceClient _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_FACTORY_METHOD(AnonymousClass0lg r2, Object obj) {
        AuthServiceClient authServiceClient = new AuthServiceClient(C00610Hs.A00(r2));
        C01150Rm.A00(authServiceClient, r2);
        return authServiceClient;
    }
}