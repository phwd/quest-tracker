package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.QC;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.directboot.DirectBootUtils;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_directboot_DirectBootUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
@SuppressLint({"SharedPreferencesUse", "BadMethodUse-android.content.Context.getSharedPreferences"})
public class EventBuilderConfig {
    public static final String KEY_A2_ENABLED = "a2_enabled";
    public static final String KEY_LOGCAT_ENABLED = "logcat_enabled";
    public static final String PREFS_NAME = "event_builder_config";
    public static volatile EventBuilderConfig _UL__ULSEP_com_oculus_logging_analytics2_EventBuilderConfig_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    @Nullable
    public volatile SharedPreferences mPrefs;

    @Nullable
    public static SharedPreferences A00(EventBuilderConfig eventBuilderConfig) {
        if (eventBuilderConfig.mPrefs == null && ((DirectBootUtils) AbstractC0096Hu.A03(0, 121, eventBuilderConfig._UL_mInjectionContext)).A01()) {
            eventBuilderConfig.mPrefs = ((Context) AbstractC0096Hu.A03(1, 3, eventBuilderConfig._UL_mInjectionContext)).getSharedPreferences(PREFS_NAME, 0);
        }
        return eventBuilderConfig.mPrefs;
    }

    @Inject
    public EventBuilderConfig(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(2, xu);
    }
}
