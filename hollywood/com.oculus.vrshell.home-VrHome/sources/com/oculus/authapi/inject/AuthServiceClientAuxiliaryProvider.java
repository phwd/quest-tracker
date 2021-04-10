package com.oculus.authapi.inject;

import android.content.Context;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.authapi.inject.OVRAuthModule;
import com.oculus.binder.BindingStrategy;
import javax.inject.Provider;

public class AuthServiceClientAuxiliaryProvider {
    private InjectionContext $ul_mInjectionContext;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$xXXBINDING_ID, $ul_injector);
    }

    public static final AuthServiceClientAuxiliaryProvider $ul_$xXXcom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (AuthServiceClientAuxiliaryProvider) UL.factorymap.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$xXXBINDING_ID, $ul_injector);
    }

    public static final AuthServiceClientAuxiliaryProvider $ul_$xXXcom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new AuthServiceClientAuxiliaryProvider($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider$xXXBINDING_ID, $ul_injector);
    }

    public AuthServiceClientAuxiliaryProvider(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(1, $ul_injector);
    }

    public AuthServiceClient get(BindingStrategy bindingStrategy) {
        return new AuthServiceClient((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, this.$ul_mInjectionContext), bindingStrategy);
    }
}
