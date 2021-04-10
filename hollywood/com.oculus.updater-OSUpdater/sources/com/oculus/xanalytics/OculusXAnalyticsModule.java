package com.oculus.xanalytics;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.facebook.xanalytics.XAnalyticsProvider;
import com.google.inject.Key;

@InjectorModule
public class OculusXAnalyticsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(XAnalyticsProvider.class)));
        public static final int _UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OculusXAnalyticsProvider.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_xanalytics_XAnalyticsProvider_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID, injectorLike);
    }

    @ProviderMethod
    static XAnalyticsProvider provideXAnalyticsProvider(OculusXAnalyticsProvider oculusXAnalyticsProvider) {
        return oculusXAnalyticsProvider;
    }

    @AutoGeneratedFactoryMethod
    public static final XAnalyticsProvider _UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideXAnalyticsProvider(OculusXAnalyticsProvider._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_ACCESS_METHOD(injectorLike));
    }
}
