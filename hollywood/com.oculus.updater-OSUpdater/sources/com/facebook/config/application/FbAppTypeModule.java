package com.facebook.config.application;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class FbAppTypeModule extends AbstractLibraryModule {
    private static volatile FbAppType _UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_java_lang_Boolean_ULSEP_com_facebook_common_build_IsInternalBuild_ULSEP_LOCK = new Object();
    private static final Object _UL__ULSEP_java_lang_Boolean_ULSEP_com_facebook_common_build_IsWorkBuild_ULSEP_LOCK = new Object();

    @AutoGeneratedFactoryMethod
    public static final FbAppType _UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_INSTANCE == null) {
            synchronized (FbAppType.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_INSTANCE = provideFbAppType();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_INSTANCE;
    }

    @ApplicationScoped
    @ProviderMethod
    static FbAppType provideFbAppType() {
        FbAppType appType = FbAppType.getAppType();
        if (appType != null) {
            return appType;
        }
        throw new IllegalStateException("Application did not provide its own FbAppType");
    }
}
