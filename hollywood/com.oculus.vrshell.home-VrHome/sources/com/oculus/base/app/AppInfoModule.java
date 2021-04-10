package com.oculus.base.app;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import javax.inject.Provider;

public abstract class AppInfoModule extends AbstractLibraryModule {

    public static final class UL_id {
        public static final int $ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID : UL.id.dynamicId(Key.get(AppInfo.class)));
    }

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_base_app_AppInfo$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UL_id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID, $ul_injector);
    }

    public static final AppInfo $ul_$xXXcom_oculus_base_app_AppInfo$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (AppInfo) UL.factorymap.get(UL_id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID, $ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_base_app_AppInfo$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UL_id.$ul_$xXXcom_oculus_base_app_AppInfo$xXXBINDING_ID, $ul_injector);
    }

    static class AutoGeneratedBindingsForAppInfoModule {
        AutoGeneratedBindingsForAppInfoModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(AppInfo.class);
                binder.require(BundledAndroidModule.class);
            }
        }
    }
}