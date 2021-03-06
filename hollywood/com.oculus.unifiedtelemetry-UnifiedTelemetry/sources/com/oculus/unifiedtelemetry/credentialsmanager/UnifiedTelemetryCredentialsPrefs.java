package com.oculus.unifiedtelemetry.credentialsmanager;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.C0515sp;
import X.Pj;
import X.QC;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.directboot.DirectBootUtils;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_directboot_DirectBootUtils_ULSEP_BINDING_ID"})
@ApplicationScoped
@SuppressLint({"BadMethodUse-android.content.Context.getSharedPreferences", "SharedPreferencesUse"})
public class UnifiedTelemetryCredentialsPrefs {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_USER_ID = "oculus.auth.user_id";
    public static final String SHARED_PREFS_AUTH = "oculus.auth.prefs";
    public static final String SHARED_PREFS_AUTH_DEVICE_PROTECTED = "oculus.auth.prefs.deviceprotected";
    public static volatile UnifiedTelemetryCredentialsPrefs _UL__ULSEP_com_oculus_unifiedtelemetry_credentialsmanager_UnifiedTelemetryCredentialsPrefs_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public final SharedPreferences mDeviceProtectedSharedPreferences;
    @Nullable
    public SharedPreferences mSharedPreferences;

    public static synchronized SharedPreferences A00(UnifiedTelemetryCredentialsPrefs unifiedTelemetryCredentialsPrefs) {
        SharedPreferences sharedPreferences;
        synchronized (unifiedTelemetryCredentialsPrefs) {
            sharedPreferences = unifiedTelemetryCredentialsPrefs.mSharedPreferences;
            if (sharedPreferences == null) {
                sharedPreferences = ((Context) AbstractC0096Hu.A03(0, 3, unifiedTelemetryCredentialsPrefs._UL_mInjectionContext)).getSharedPreferences(SHARED_PREFS_AUTH, 0);
                unifiedTelemetryCredentialsPrefs.mSharedPreferences = sharedPreferences;
            }
        }
        return sharedPreferences;
    }

    @AutoGeneratedAccessMethod
    public static final UnifiedTelemetryCredentialsPrefs A01(AbstractC0247Xu xu) {
        return (UnifiedTelemetryCredentialsPrefs) C0515sp.A00(59, xu);
    }

    @AutoGeneratedFactoryMethod
    public static final UnifiedTelemetryCredentialsPrefs A02(AbstractC0247Xu xu) {
        if (_UL__ULSEP_com_oculus_unifiedtelemetry_credentialsmanager_UnifiedTelemetryCredentialsPrefs_ULSEP_INSTANCE == null) {
            synchronized (UnifiedTelemetryCredentialsPrefs.class) {
                Pj A00 = Pj.A00(_UL__ULSEP_com_oculus_unifiedtelemetry_credentialsmanager_UnifiedTelemetryCredentialsPrefs_ULSEP_INSTANCE, xu);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_unifiedtelemetry_credentialsmanager_UnifiedTelemetryCredentialsPrefs_ULSEP_INSTANCE = new UnifiedTelemetryCredentialsPrefs(xu.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_unifiedtelemetry_credentialsmanager_UnifiedTelemetryCredentialsPrefs_ULSEP_INSTANCE;
    }

    public final void A03(@Nullable String str, @Nullable String str2) {
        if (((DirectBootUtils) AbstractC0096Hu.A03(1, 121, this._UL_mInjectionContext)).A01()) {
            A00(this).edit().putString("access_token", str2).apply();
        }
        this.mDeviceProtectedSharedPreferences.edit().putString(KEY_USER_ID, str).apply();
    }

    @Inject
    public UnifiedTelemetryCredentialsPrefs(AbstractC0247Xu xu) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mDeviceProtectedSharedPreferences = ((Context) AbstractC0096Hu.A03(0, 3, qc)).createDeviceProtectedStorageContext().getSharedPreferences(SHARED_PREFS_AUTH_DEVICE_PROTECTED, 0);
    }
}
