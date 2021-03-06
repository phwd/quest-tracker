package com.oculus.authapi.inject;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0RE;
import X.AnonymousClass0VC;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import android.content.Context;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.binder.BindingStrategy;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class AuthServiceClientAuxiliaryProvider {
    public AnonymousClass0RE _UL_mInjectionContext;

    public AuthServiceClient get(BindingStrategy bindingStrategy) {
        return new AuthServiceClient((Context) AnonymousClass0VF.A03(0, 3, this._UL_mInjectionContext), bindingStrategy);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2098, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AuthServiceClientAuxiliaryProvider _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (AuthServiceClientAuxiliaryProvider) AnonymousClass1TK.A00(2098, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final AuthServiceClientAuxiliaryProvider _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        return new AuthServiceClientAuxiliaryProvider(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2098, r1);
    }

    @Inject
    public AuthServiceClientAuxiliaryProvider(AnonymousClass0lg r3) {
        this._UL_mInjectionContext = new AnonymousClass0RE(1, r3);
    }
}
