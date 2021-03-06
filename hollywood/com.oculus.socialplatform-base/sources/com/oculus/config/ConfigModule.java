package com.oculus.config;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qj;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VB;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.DeclareMultiBinding;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.config.annotations.GatekeeperSet;
import com.oculus.config.annotations.GatekeeperSharedPrefs;
import com.oculus.config.annotations.MobileConfigSharedPrefs;
import com.oculus.config.gatekeeper.DynamicGatekeeperRegistry;
import com.oculus.config.gatekeeper.GatekeeperRegistry;
import com.oculus.config.gatekeeper.SetBindingGatekeeperRegistry;
import java.util.Set;
import javax.inject.Provider;

@InjectorModule
public abstract class ConfigModule extends AnonymousClass0VI {
    public static final String GATEKEEPER_PREFERENCES = "gatekeeper_preferences";
    public static final String LAST_CONFIG_UPDATE_MS = "/config/last_config_update_ms";
    public static final String LAST_MOBILECONFIG_UPDATE_MS = "/mobileconfigs/last_mobileconfig_update_ms";
    public static final String MOBILECONFIG_PREFERENCES = "mobileconfig_preferences";
    public static final String SHARED_PREF_KEY_OVERRIDE_ROOT = "/gatekeeper_overrides/";
    public static final String SHARED_PREF_KEY_ROOT_GATEKEEPERS = "/gatekeepers/";
    public static final String SHARED_PREF_KEY_ROOT_QUICK_EXPERIMENTS = "/quick_experiments/";
    public static volatile DynamicGatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_INSTANCE;
    public static volatile GatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_BINDING_ID = 74;
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_BINDING_ID = 2111;
        public static final int _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_BINDING_ID = 1;
        public static final int _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_BINDING_ID = 70;
        public static final int _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_BINDING_ID = 34;
        public static final int _UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_BINDING_ID = 107;
    }

    @GatekeeperSet
    @DeclareMultiBinding
    public abstract Set<String> provideGatekeeperSet();

    @AutoGeneratedAccessMethod
    public static final SharedPreferences _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (SharedPreferences) AnonymousClass1TK.A00(74, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final SharedPreferences _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (SharedPreferences) AnonymousClass1TK.A00(2111, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_android_content_SharedPreferences_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(74, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_android_content_SharedPreferences_ULGT__ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2111, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(70, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_gatekeeper_GatekeeperRegistry_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(34, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_java_lang_String_ULGT__ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(107, r1);
    }

    @AutoGeneratedAccessMethod
    public static final DynamicGatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (DynamicGatekeeperRegistry) AnonymousClass1TK.A00(70, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final DynamicGatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_FACTORY_METHOD(AnonymousClass0lg r6, Object obj) {
        if (_UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_INSTANCE == null) {
            synchronized (DynamicGatekeeperRegistry.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_INSTANCE, r6);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r6.getApplicationInjector();
                        DynamicGatekeeperRegistry dynamicGatekeeperRegistry = new DynamicGatekeeperRegistry(_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(dynamicGatekeeperRegistry, applicationInjector);
                        _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_INSTANCE = dynamicGatekeeperRegistry;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (GatekeeperRegistry) AnonymousClass1TK.A00(34, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperRegistry _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_FACTORY_METHOD(AnonymousClass0lg r5, Object obj) {
        if (_UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_INSTANCE == null) {
            synchronized (GatekeeperRegistry.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_INSTANCE, r5);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r5.getApplicationInjector();
                        SetBindingGatekeeperRegistry setBindingGatekeeperRegistry = new SetBindingGatekeeperRegistry(_UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(applicationInjector));
                        C01150Rm.A00(setBindingGatekeeperRegistry, applicationInjector);
                        _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_INSTANCE = setBindingGatekeeperRegistry;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Set _UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (Set) AnonymousClass1TK.A00(107, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_android_content_SharedPreferences_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(74, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_android_content_SharedPreferences_ULGT__ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2111, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(70, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_gatekeeper_GatekeeperRegistry_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(34, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_util_Set_ULLT_java_lang_String_ULGT__ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(107, r1);
    }

    @ApplicationScoped
    @ProviderMethod
    public static DynamicGatekeeperRegistry provideDynamicGatekeeperStore(@GatekeeperSharedPrefs SharedPreferences sharedPreferences, @GatekeeperSet Set<String> set) {
        return new DynamicGatekeeperRegistry(sharedPreferences, set);
    }

    @GatekeeperSharedPrefs
    @ProviderMethod
    public static SharedPreferences provideGatekeeperSharedPreferences(@ForAppContext Context context) {
        return context.getSharedPreferences(GATEKEEPER_PREFERENCES, 0);
    }

    @ApplicationScoped
    @ProviderMethod
    public static GatekeeperRegistry provideGatekeeperStore(@GatekeeperSet Set<String> set) {
        return new SetBindingGatekeeperRegistry(set);
    }

    @MobileConfigSharedPrefs
    @ProviderMethod
    public static SharedPreferences provideMobileConfigSharedPrefserences(@ForAppContext Context context) {
        return context.getSharedPreferences(MOBILECONFIG_PREFERENCES, 0);
    }

    @AutoGeneratedFactoryMethod
    public static final SharedPreferences _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        SharedPreferences provideGatekeeperSharedPreferences = provideGatekeeperSharedPreferences(C00610Hs.A00(r1));
        C01150Rm.A00(provideGatekeeperSharedPreferences, r1);
        return provideGatekeeperSharedPreferences;
    }

    @AutoGeneratedFactoryMethod
    public static final SharedPreferences _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        SharedPreferences provideMobileConfigSharedPrefserences = provideMobileConfigSharedPrefserences(C00610Hs.A00(r1));
        C01150Rm.A00(provideMobileConfigSharedPrefserences, r1);
        return provideMobileConfigSharedPrefserences;
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForConfigModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
