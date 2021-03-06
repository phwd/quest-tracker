package com.oculus.ocms.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.ocms.app.OCMSAppModule;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OCMSConfigurationPrefs {
    private static final String KEY_ACCESS_TOKEN = "ocms.auth.access_token";
    private static final String KEY_USER_ID = "ocms.auth.user_id";
    private static final String SHARED_PREFS_AUTH = "ocms.auth.prefs";
    private static volatile OCMSConfigurationPrefs _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_INSTANCE;
    private final SharedPreferences mSharedPreferences;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_ocms_app_OCMSConfigurationPrefs_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OCMSAppModule.UL_id._UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OCMSConfigurationPrefs _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OCMSConfigurationPrefs) UL.factorymap.get(OCMSAppModule.UL_id._UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OCMSConfigurationPrefs _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_INSTANCE == null) {
            synchronized (OCMSConfigurationPrefs.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_INSTANCE = new OCMSConfigurationPrefs(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_ocms_app_OCMSConfigurationPrefs_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OCMSAppModule.UL_id._UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public OCMSConfigurationPrefs(@UnsafeContextInjection Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFS_AUTH, 0);
    }

    public synchronized String getAccessToken() {
        return this.mSharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    public synchronized String getUserId() {
        return this.mSharedPreferences.getString(KEY_USER_ID, null);
    }

    public synchronized void setCredentials(String str, String str2) {
        this.mSharedPreferences.edit().putString(KEY_USER_ID, str).putString(KEY_ACCESS_TOKEN, str2).apply();
    }
}
