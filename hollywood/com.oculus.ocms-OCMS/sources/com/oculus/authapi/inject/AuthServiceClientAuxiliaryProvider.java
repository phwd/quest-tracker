package com.oculus.authapi.inject;

import android.content.Context;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.authapi.inject.OVRAuthModule;
import com.oculus.binder.BindingStrategy;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class AuthServiceClientAuxiliaryProvider {
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(OVRAuthModule.UL_id._UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final AuthServiceClientAuxiliaryProvider _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (AuthServiceClientAuxiliaryProvider) UL.factorymap.get(OVRAuthModule.UL_id._UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final AuthServiceClientAuxiliaryProvider _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new AuthServiceClientAuxiliaryProvider(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(OVRAuthModule.UL_id._UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public AuthServiceClientAuxiliaryProvider(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    public AuthServiceClient get(BindingStrategy bindingStrategy) {
        return new AuthServiceClient((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext), bindingStrategy);
    }
}