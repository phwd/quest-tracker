package com.oculus.defaultapps;

import X.C07190ra;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.Collection;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class DefaultAppsPrefs {
    public static final String COMPLETED_PRELOAD_APP_LIST = "completed_preload_app_list";
    public static final String DEFAULT_ENVIRONMENT_PACKAGE_NAME = "default_environment_package_name";
    public static final String FAILED_PRELOAD_APP_LIST = "failed_preload_app_list";
    public static final String HIGH_PRIORITY_APPS_DOWNLOAD_STATUS = "high_pri_apps_download_status";
    public static final String HIGH_PRIORITY_APPS_FETCH = "high_pri_apps_fetch";
    public static final String HIGH_PRIORITY_APPS_FETCH_FAILURE = "high_pri_apps_fetch_failure";
    public static final String HIGH_PRIORITY_APPS_PACKAGES = "high_pri_apps_packages";
    public static final String HIGH_PRIORITY_APPS_SETUP_COMPLETE = "high_pri_apps_setup_complete";
    public static final String MODIFIABLE_HIGH_PRI_APP_LIST = "modifiable_high_pri_app_list";
    public static final String NUX_OTA_DOWNLOAD_HIGH_PRI_APPS_BROADCAST = "nux_ota_download_high_pri_apps_broadcast";
    public static final String PRELOAD_INSTALLER_COMPLETE = "preload_installer_complete";
    public static final String RESCHEDULE_ATTEMPT = "reschedule_attempt";
    public static final String SETUP_ATTEMPT = "setup_attempt";
    public static final String SETUP_COMPLETE = "setup_complete";
    public static final String SETUP_COMPLETED_PACKAGES = "completed_packages";
    public static final String SHARED_PREFS_PACIFIC = "prefs_horizon_pacific";
    public final SharedPreferences mPrefs;

    public final void A00(int i) {
        this.mPrefs.edit().putInt(RESCHEDULE_ATTEMPT, i).apply();
    }

    public final void A01(Collection<String> collection) {
        this.mPrefs.edit().putStringSet(MODIFIABLE_HIGH_PRI_APP_LIST, C07190ra.A02(collection)).apply();
    }

    public final void A02(boolean z) {
        this.mPrefs.edit().putBoolean(HIGH_PRIORITY_APPS_SETUP_COMPLETE, z).apply();
    }

    @Inject
    public DefaultAppsPrefs(@ForAppContext Context context) {
        this.mPrefs = context.getSharedPreferences(SHARED_PREFS_PACIFIC, 0);
    }
}
