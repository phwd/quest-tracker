package com.oculus.config;

import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.UL;
import com.google.common.base.Preconditions;
import com.oculus.config.ConfigModule;
import com.oculus.config.annotations.GatekeeperSharedPrefs;
import com.oculus.config.gatekeeper.DynamicGatekeeperRegistry;
import com.oculus.config.gatekeeper.GatekeeperRegistry;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ConfigController {
    private static volatile ConfigController _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_INSTANCE;
    @Inject
    @Eager
    private final DynamicGatekeeperRegistry mDynamicGatekeeperRegistry;
    @Inject
    @Eager
    private final GatekeeperRegistry mGatekeeperRegistry;
    @Inject
    @Eager
    @GatekeeperSharedPrefs
    private final SharedPreferences mSharedPreferences;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_ConfigController_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ConfigModule.UL_id._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ConfigController _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ConfigController) UL.factorymap.get(ConfigModule.UL_id._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ConfigController _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_config_ConfigController_ULSEP_INSTANCE == null) {
            synchronized (ConfigController.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_config_ConfigController_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_INSTANCE = new ConfigController(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_ConfigController_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ConfigModule.UL_id._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public ConfigController(InjectorLike injectorLike) {
        this.mGatekeeperRegistry = ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_ACCESS_METHOD(injectorLike);
        this.mSharedPreferences = ConfigModule._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_ACCESS_METHOD(injectorLike);
        this.mDynamicGatekeeperRegistry = ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public Boolean getGatekeeper(String str) {
        return getGatekeeper(str, false);
    }

    public Boolean getGatekeeper(String str, boolean z) {
        Preconditions.checkArgument(isGatekeeperRegistered(str));
        synchronized (this.mSharedPreferences) {
            String overrideSharedPreferenceKey = getOverrideSharedPreferenceKey(str);
            if (this.mSharedPreferences.contains(overrideSharedPreferenceKey)) {
                return Boolean.valueOf(this.mSharedPreferences.getBoolean(overrideSharedPreferenceKey, z));
            }
            return Boolean.valueOf(this.mSharedPreferences.getBoolean(getGatekeeperSharedPreferenceKey(str), z));
        }
    }

    public boolean isGatekeeperFetched(String str) {
        Preconditions.checkArgument(isGatekeeperRegistered(str));
        return this.mSharedPreferences.contains(getGatekeeperSharedPreferenceKey(str));
    }

    public boolean isGatekeeperOverrideEnabled(String str) {
        boolean contains;
        Preconditions.checkArgument(isGatekeeperRegistered(str));
        String overrideSharedPreferenceKey = getOverrideSharedPreferenceKey(str);
        synchronized (this.mSharedPreferences) {
            contains = this.mSharedPreferences.contains(overrideSharedPreferenceKey);
        }
        return contains;
    }

    public boolean isGatekeeperRegistered(String str) {
        return this.mGatekeeperRegistry.isGatekeeperRegistered(str) || this.mDynamicGatekeeperRegistry.isGatekeeperRegistered(str);
    }

    @VisibleForTesting
    public static String getGatekeeperSharedPreferenceKey(String str) {
        return ConfigModule.SHARED_PREF_KEY_ROOT_GATEKEEPERS + str;
    }

    public static String getOverrideSharedPreferenceKey(String str) {
        return ConfigModule.SHARED_PREF_KEY_OVERRIDE_ROOT + str;
    }
}