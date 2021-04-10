package com.oculus.authapi.inject;

import android.content.Context;
import android.content.Intent;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import com.facebook.secure.trustedapp.exception.CannotAttachCallerInfoException;
import com.facebook.ultralight.UL;
import com.oculus.authapi.OVRAuth;
import com.oculus.authapi.inject.OVRAuthModule;
import javax.inject.Provider;

public class CallerInfoProviderImpl implements OVRAuth.CallerInfoProvider {
    private InjectionContext $ul_mInjectionContext;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_authapi_inject_CallerInfoProviderImpl$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXBINDING_ID, $ul_injector);
    }

    public static final CallerInfoProviderImpl $ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (CallerInfoProviderImpl) UL.factorymap.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXBINDING_ID, $ul_injector);
    }

    public static final CallerInfoProviderImpl $ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new CallerInfoProviderImpl($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_authapi_inject_CallerInfoProviderImpl$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(OVRAuthModule.UL_id.$ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXBINDING_ID, $ul_injector);
    }

    public CallerInfoProviderImpl(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(1, $ul_injector);
    }

    @Override // com.oculus.authapi.OVRAuth.CallerInfoProvider
    public Intent attachCallerInfo(Intent intent) {
        try {
            return CallerInfoHelper.attachCallerInfo(intent, (Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXBINDING_ID, this.$ul_mInjectionContext), null);
        } catch (CannotAttachCallerInfoException e) {
            throw new RuntimeException(e);
        }
    }
}
