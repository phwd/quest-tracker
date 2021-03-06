package com.oculus.mobileconfig.init;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.time.TimeModule;
import com.facebook.debug.log.BLog;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.mobileconfig.MobileConfigUserIdProvider;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.factory.module.SessionlessMC;
import com.facebook.mobileconfig.impl.MobileConfigFactoryImpl;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.interfaces.MobileConfigInterfacesModule;
import com.facebook.mobileconfig.metadata.MobileConfigParamsMapModule;
import com.facebook.tigon.oktigon.OkTigonModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.AuthHandlerModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.INeedInit;
import com.oculus.common.init.NeedsHighPriorityInit;
import com.oculus.config.interfaces.ConfigInterfacesModule;
import com.oculus.config.interfaces.Configuration;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.locale.LocaleModule;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.oktigon.OculusOkTigonModule;
import com.oculus.util.device.DeviceModule;
import com.oculus.xanalytics.OculusXAnalyticsModule;
import java.lang.annotation.Annotation;
import java.util.Locale;
import javax.inject.Provider;

@InjectorModule
public abstract class MobileConfigInitModule extends AbstractLibraryModule {
    private static volatile PackageInfo _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_LOCK = new Object();
    private static volatile MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_LOCK = new Object();
    private static volatile MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(PackageInfo.class)));
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfig.class)));
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfig.class, (Class<? extends Annotation>) SessionlessMC.class)));
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigConfiguration.class)));
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigInitOptions.class)));
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigInit.class)));
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigLoginHandler.class)));
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MobileConfigLogoutHandler.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, injectorLike);
    }

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract Configuration addMobileConfigConfiguration(MobileConfigConfiguration mobileConfigConfiguration);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    @NeedsHighPriorityInit
    public abstract INeedInit addMobileConfigInit(MobileConfigInit mobileConfigInit);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract LoginHandler addMobileConfigLoginHandler(MobileConfigLoginHandler mobileConfigLoginHandler);

    /* access modifiers changed from: package-private */
    @AddToMultiBind
    public abstract LogoutHandler addMobileConfigLogoutHandler(MobileConfigLogoutHandler mobileConfigLogoutHandler);

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForMobileConfigInitModule {
        AutoGeneratedBindingsForMobileConfigInitModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(MobileConfigInitOptions.class);
                binder.require(TimeModule.class);
                binder.require(BundledAndroidModule.class);
                binder.require(MobileConfigFactoryModule.class);
                binder.require(MobileConfigFactoryImplModule.class);
                binder.require(MobileConfigInterfacesModule.class);
                binder.require(MobileConfigParamsMapModule.class);
                binder.require(OkTigonModule.class);
                binder.require(CredentialsModule.class);
                binder.require(AuthHandlerModule.class);
                binder.require(AppInitModule.class);
                binder.require(ConfigInterfacesModule.class);
                binder.require(InterfacesModule.class);
                binder.require(LocaleModule.class);
                binder.require(UtilsModule.class);
                binder.require(OculusOkTigonModule.class);
                binder.require(DeviceModule.class);
                binder.require(OculusXAnalyticsModule.class);
                binder.bindMulti(LogoutHandler.class).add(MobileConfigLogoutHandler.class);
                binder.bindMulti(LoginHandler.class).add(MobileConfigLoginHandler.class);
                binder.bindMulti(INeedInit.class, NeedsHighPriorityInit.class).add(MobileConfigInit.class);
                binder.bindMulti(Configuration.class).add(MobileConfigConfiguration.class);
                binder.bind(PackageInfo.class).toProvider(new PackageInfoMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(MobileConfig.class).annotatedWith(SessionlessMC.class).toProvider(new MobileConfig_com_facebook_mobileconfig_factory_module_SessionlessMCMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(MobileConfig.class).toProvider(new MobileConfigMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(MobileConfigConfiguration.class).toProvider(new MobileConfigConfigurationAutoProvider());
                binder.bind(MobileConfigInit.class).toProvider(new MobileConfigInitAutoProvider()).in(ApplicationScoped.class);
                binder.bind(MobileConfigLoginHandler.class).toProvider(new MobileConfigLoginHandlerAutoProvider());
                binder.bind(MobileConfigLogoutHandler.class).toProvider(new MobileConfigLogoutHandlerAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final PackageInfo _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (PackageInfo) UL.factorymap.get(UL_id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final PackageInfo _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_INSTANCE = providePackageInfo(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_android_content_pm_PackageInfo_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfig) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_INSTANCE = provideMobileConfig(MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_ACCESS_METHOD(applicationInjector), BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(applicationInjector), CredentialsModule._UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfig) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfig _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE = provideSessionlessMobileConfig(MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(applicationInjector), BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_android_content_pm_PackageInfo_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfigInitOptions _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfigInitOptions) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, injectorLike);
    }

    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static MobileConfig provideMobileConfig(MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder, @ForAppContext Context context, final Lazy<Credentials> lazy) {
        return MobileConfigFactoryImpl.builder(mobileConfigManagerSingletonHolder).dataDir(context.getFilesDir()).userIdProvider(new MobileConfigUserIdProvider() {
            /* class com.oculus.mobileconfig.init.MobileConfigInitModule.AnonymousClass1 */

            @Override // com.facebook.mobileconfig.MobileConfigUserIdProvider
            public String getUserId() {
                return Lazy.this.get() != null ? ((Credentials) Lazy.this.get()).getUserId() : "";
            }
        }).build();
    }

    @SessionlessMC
    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static MobileConfig provideSessionlessMobileConfig(@SessionlessMC MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder, @ForAppContext Context context) {
        return MobileConfigFactoryImpl.builder(mobileConfigManagerSingletonHolder).dataDir(context.getFilesDir()).assetManager(context.getAssets()).build();
    }

    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static PackageInfo providePackageInfo(@UnsafeContextInjection Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            String packageName = context.getPackageName();
            try {
                int aPKVersionCode = BuildConstants.getAPKVersionCode();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                if (packageInfo.versionCode != aPKVersionCode) {
                    BLog.e("AndroidModule", String.format(Locale.US, "Android PackageManager returned version code: %d, apk version code is: %d", Integer.valueOf(packageInfo.versionCode), Integer.valueOf(aPKVersionCode)));
                }
                return packageInfo;
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Package Manager not found");
        }
    }
}
