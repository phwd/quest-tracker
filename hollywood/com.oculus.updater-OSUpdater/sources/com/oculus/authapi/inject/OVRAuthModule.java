package com.oculus.authapi.inject;

import android.content.Context;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.authapi.OVRAuth;

@InjectorModule
public class OVRAuthModule extends AbstractLibraryModule {
    private static volatile OVRAuth _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AuthServiceClient.class)));
        public static final int _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OVRAuth.class)));
        public static final int _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AuthServiceClientAuxiliaryProvider.class)));
        public static final int _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(CallerInfoProviderImpl.class)));
    }

    @AutoGeneratedFactoryMethod
    public static final OVRAuth _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE == null) {
            synchronized (OVRAuth.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE = provideOVRAuth(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(applicationInjector), CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_INSTANCE;
    }

    @ApplicationScoped
    @ProviderMethod
    static OVRAuth provideOVRAuth(@ForAppContext Context context, CallerInfoProviderImpl callerInfoProviderImpl) {
        return new OVRAuth(context, callerInfoProviderImpl);
    }
}