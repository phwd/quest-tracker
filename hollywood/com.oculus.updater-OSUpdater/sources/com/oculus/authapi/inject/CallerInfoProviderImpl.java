package com.oculus.authapi.inject;

import android.content.Context;
import android.content.Intent;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.exception.CannotAttachCallerInfoException;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.authapi.OVRAuth;
import com.oculus.authapi.inject.OVRAuthModule;

@Dependencies
public class CallerInfoProviderImpl implements OVRAuth.CallerInfoProvider {
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final CallerInfoProviderImpl _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (CallerInfoProviderImpl) UL.factorymap.get(OVRAuthModule.UL_id._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final CallerInfoProviderImpl _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new CallerInfoProviderImpl(injectorLike);
    }

    @Inject
    public CallerInfoProviderImpl(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
    public Intent attachCallerInfo(Intent intent) {
        try {
            return CallerInfoHelper.attachCallerInfo(intent, (Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext), null);
        } catch (CannotAttachCallerInfoException e) {
            throw new RuntimeException(e);
        }
    }
}