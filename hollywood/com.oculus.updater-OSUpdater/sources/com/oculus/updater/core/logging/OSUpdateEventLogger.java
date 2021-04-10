package com.oculus.updater.core.logging;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.common.identifiers.SafeUUIDGenerator;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.common.build.BuildConfig;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.LogCollectorClient;
import com.oculus.os.SettingsManager;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.os.Version;
import com.oculus.updater.core.logging.LoggingModule;
import com.oculus.updater.net.methods.OSReleasesResponse;
import com.oculus.updater.net.methods.ReleaseInstallOptions;
import com.oculus.updater.prefs.OSUpdaterSharedPreferences;
import com.oculus.updater.prefs.PrefsModule;
import com.oculus.util.device.DeviceModule;
import com.oculus.util.device.DeviceUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies
public class OSUpdateEventLogger {
    private static final String TAG = "OSUpdateEventLogger";
    private InjectionContext _UL_mInjectionContext;
    private final Context mContext;
    private final Queue<OSUpdateEvent> mEventQueue = new ArrayDeque();
    @Nullable
    private String mSessionId;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(this.mContext);

    @AutoGeneratedAccessMethod
    public static final OSUpdateEventLogger _UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OSUpdateEventLogger) UL.factorymap.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OSUpdateEventLogger _UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OSUpdateEventLogger(injectorLike, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @Inject
    public OSUpdateEventLogger(InjectorLike injectorLike, @ForAppContext Context context) {
        this._UL_mInjectionContext = new InjectionContext(4, injectorLike);
        this.mContext = context;
        restoreOtaSession();
    }

    private void addAdditionalKeys(OSUpdateEvent oSUpdateEvent) {
        KeyguardManager keyguardManager = (KeyguardManager) this.mContext.getSystemService("keyguard");
        if (keyguardManager != null && Build.VERSION.SDK_INT >= 23) {
            oSUpdateEvent.setKey("lockscreen_pin_set", Boolean.valueOf(keyguardManager.isDeviceSecure()));
        }
        oSUpdateEvent.setKey("slot_suffix", SystemPropertiesInternal.get("ro.boot.slot_suffix", "unknown"));
        if (!TextUtils.isEmpty(this.mSessionId)) {
            oSUpdateEvent.setKey("update_session_id", this.mSessionId);
        }
    }

    private synchronized void log(OSUpdateEvent oSUpdateEvent) {
        addAdditionalKeys(oSUpdateEvent);
        BLog.d(TAG, "adding event '%s' to the event queue", oSUpdateEvent.getName());
        this.mEventQueue.add(oSUpdateEvent);
        flushEventQueue();
    }

    private String generateOtaSession() {
        String uuid = SafeUUIDGenerator.randomUUID().toString();
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putString("ota_session_id", uuid).apply();
        return uuid;
    }

    private void restoreOtaSession() {
        this.mSessionId = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getString("ota_session_id", BuildConfig.PROVIDER_SUFFIX);
    }

    private void resetOtaSession() {
        this.mSessionId = null;
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putString("ota_session_id", BuildConfig.PROVIDER_SUFFIX).apply();
    }

    public void logOSUpdateProcessStarted(@Nullable Integer num) {
        BLog.i(TAG, "logOSUpdateProcessStarted");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_process_started", true);
        if (num != null) {
            oSUpdateEvent.setKey("update_engine_state", num);
        }
        log(oSUpdateEvent);
    }

    public void logOSUpdateCheckStarted(@Nullable String str, boolean z) {
        BLog.i(TAG, "logOSUpdateCheckStarted");
        this.mSessionId = generateOtaSession();
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_check_started", true);
        oSUpdateEvent.setKey("received_access_token", Boolean.valueOf(!TextUtils.isEmpty(str)));
        oSUpdateEvent.setKey("user_triggered", Boolean.valueOf(z));
        log(oSUpdateEvent);
    }

    public void logOSUpdateCheckCompletedWithNoUpdates(OSReleasesResponse oSReleasesResponse, boolean z) {
        BLog.i(TAG, "logOSUpdateCheckCompletedWithNoUpdates");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_check_completed", true);
        oSUpdateEvent.setKey("updates_available", false);
        oSUpdateEvent.setKey("user_triggered", Boolean.valueOf(z));
        oSUpdateEvent.setKey("release_response", oSReleasesResponse.updates != null ? oSReleasesResponse.updates.toString() : BuildConfig.PROVIDER_SUFFIX);
        oSUpdateEvent.setKey("update_check_failure_count", Integer.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_check_for_update_failed")));
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setCurrentTime("prefs_check_for_updates_finish_time");
        boolean z2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean("prefs_check_for_update_found", false);
        BLog.d(TAG, "logOSUpdateCheckCompletedWithNoUpdates: update found = %b", Boolean.valueOf(z2));
        if (!z2) {
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).incrementCount("prefs_check_for_update_count");
        }
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_check_for_update_failed");
    }

    public void logOSUpdateCheckCompletedWithUpdates(OSReleasesResponse oSReleasesResponse, OSReleasesResponse.OSReleaseInfo oSReleaseInfo, boolean z) {
        BLog.i(TAG, "logOSUpdateCheckCompletedWithUpdates");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_check_completed", true);
        oSUpdateEvent.setKey("updates_available", true);
        oSUpdateEvent.setKey("user_triggered", Boolean.valueOf(z));
        oSUpdateEvent.setKey("release_response", oSReleasesResponse.updates != null ? oSReleasesResponse.updates.toString() : BuildConfig.PROVIDER_SUFFIX);
        oSUpdateEvent.setKey("update_base_version", oSReleaseInfo.base);
        oSUpdateEvent.setKey("update_version", oSReleaseInfo.target);
        oSUpdateEvent.setKey("release_channel_id", oSReleaseInfo.releaseChannelId);
        oSUpdateEvent.setKey("release_channel_name", oSReleaseInfo.releaseChannelName);
        oSUpdateEvent.setKey("update_check_failure_count", Integer.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_check_for_update_failed")));
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setCurrentTime("prefs_check_for_updates_finish_time");
        boolean z2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean("prefs_check_for_update_found", false);
        BLog.d(TAG, "logOSUpdateCheckCompletedWithUpdates: update found = %b", Boolean.valueOf(z2));
        OSUpdaterSharedPreferences.TimeUpdate updateTime = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_check_for_update_start_time");
        if (!z2 && updateTime.diff != 0) {
            int incrementCount = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).incrementCount("prefs_check_for_update_count");
            BLog.d(TAG, "logOSUpdateCheckCompletedWithUpdates: updateCheckCount = %d", Integer.valueOf(incrementCount));
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_check_for_update_found", true).commit();
            OSUpdateEvent oSUpdateEvent2 = new OSUpdateEvent("os_update_found", true);
            oSUpdateEvent2.setKey("update_check_count", Integer.valueOf(incrementCount));
            long j = updateTime.diff;
            BLog.d(TAG, "logOSUpdateCheckCompletedWithUpdates: timeTakenToFindUpdate = %d", Long.valueOf(j));
            oSUpdateEvent2.setKey("time_spent_checking_for_update", Long.valueOf(j));
            log(oSUpdateEvent2);
        }
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_check_for_update_failed");
    }

    public void logOSUpdateCheckFailed(Exception exc, boolean z) {
        BLog.i(TAG, "logOSUpdateCheckFailed");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_check_failed", true);
        oSUpdateEvent.setKey("user_triggered", Boolean.valueOf(z));
        String message = exc.getMessage();
        if (message == null) {
            message = "unknown";
        }
        oSUpdateEvent.setKey("message", message);
        String message2 = exc.getCause() != null ? exc.getCause().getMessage() : null;
        if (message2 == null) {
            message2 = "unknown";
        }
        oSUpdateEvent.setKey("cause_message", message2);
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).incrementCount("prefs_check_for_update_failed");
        resetOtaSession();
    }

    public void logOSApplyUpdateStarted(OSReleasesResponse.OSReleaseInfo oSReleaseInfo, boolean z) {
        Long l;
        BLog.i(TAG, "logOSApplyUpdateStarted");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_apply_started", true);
        oSUpdateEvent.setKey("base_version", oSReleaseInfo.base);
        ReleaseInstallOptions releaseInstallOptions = oSReleaseInfo.getReleaseInstallOptions();
        long j = 0L;
        String str = "[]";
        if (releaseInstallOptions != null) {
            if (releaseInstallOptions.headers != null) {
                str = Arrays.toString(releaseInstallOptions.headers);
            }
            j = releaseInstallOptions.offset;
            l = releaseInstallOptions.size;
        } else {
            l = 0L;
        }
        oSUpdateEvent.setKey("headers", str);
        oSUpdateEvent.setKey("offset", j);
        oSUpdateEvent.setKey("file_size", l);
        oSUpdateEvent.setKey("update_version", oSReleaseInfo.target);
        oSUpdateEvent.setKey("device_locked", Boolean.valueOf(z));
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_apply_update_start_time");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_apply_update_downloading_start_time");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putLong("prefs_apply_update_size", l.longValue()).putLong("prefs_apply_update_base_version", oSReleaseInfo.base.longValue()).putLong("prefs_apply_update_target_version", oSReleaseInfo.target.longValue()).commit();
    }

    public void logOSApplyUpdateProgress(float f) {
        BLog.i(TAG, "logOSApplyUpdateProgress");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putFloat("prefs_apply_update_percent_complete", f).commit();
    }

    public void logOSApplyUpdateSuspended(String str, @Nullable String str2) {
        BLog.i(TAG, "logOSApplyUpdateSuspended");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_apply_suspended", true);
        oSUpdateEvent.setKey("condition_status", str);
        log(oSUpdateEvent);
        if (str2 == null) {
            BLog.i(TAG, "invalid prefsKey. Not saving count");
        } else if (str2.equals("prefs_apply_suspend_due_to_battery") || str2.equals("prefs_apply_suspend_due_to_not_idle") || str2.equals("prefs_apply_update_shutdown_count")) {
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).incrementCount(str2);
        }
    }

    public void logOSApplyUpdateResume() {
        BLog.i(TAG, "logOSApplyUpdateResume");
        log(new OSUpdateEvent("os_update_apply_resumed", true));
    }

    public void logOSApplyUpdateSucceeded(boolean z) {
        BLog.i(TAG, "logOSApplyUpdateSucceeded");
        OSUpdaterSharedPreferences.TimeUpdate updateTime = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_apply_update_start_time");
        OSUpdaterSharedPreferences.TimeUpdate updateTime2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_apply_update_finalizing_start_time");
        long count = (long) ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_suspend_due_to_battery");
        long count2 = (long) ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_suspend_due_to_not_idle");
        long count3 = (long) ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_update_shutdown_count");
        long buildVersion = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_base_version");
        long buildVersion2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_target_version");
        Long valueOf = Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("prefs_apply_update_size", -1));
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_apply_completed", true);
        oSUpdateEvent.setKey("base_version", Long.valueOf(buildVersion));
        oSUpdateEvent.setKey("update_version", Long.valueOf(buildVersion2));
        oSUpdateEvent.setKey("reboot_file_created", Boolean.valueOf(z));
        oSUpdateEvent.setKey("time_spent_applying_update", Long.valueOf(updateTime.diff));
        oSUpdateEvent.setKey("time_spent_downloading_update_secs", Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("prefs_total_downloading_time", 0)));
        oSUpdateEvent.setKey("time_spent_verifying_update_secs", Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("prefs_total_verifying_time", 0)));
        oSUpdateEvent.setKey("time_spent_finalizing_update_secs", Long.valueOf(updateTime2.diff));
        oSUpdateEvent.setKey("apply_update_suspend_count", Long.valueOf(count + count2 + count3));
        oSUpdateEvent.setKey("apply_update_suspend_count_battery", Long.valueOf(count));
        oSUpdateEvent.setKey("apply_update_suspend_count_not_idle", Long.valueOf(count2));
        oSUpdateEvent.setKey("apply_update_shutdown_count", Long.valueOf(count3));
        oSUpdateEvent.setKey("file_size", valueOf);
        int count4 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_update_failure_count");
        BLog.d(TAG, "logOSApplyUpdateSucceeded: apply update failure count = %d", Integer.valueOf(count4));
        oSUpdateEvent.setKey("apply_update_total_failure_count", Integer.valueOf(count4));
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_suspend_due_to_battery");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_suspend_due_to_not_idle");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_update_shutdown_count");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_wait_for_reboot_start_time");
    }

    public void logOSUpdateConditionsNotMet(String str) {
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_conditions_not_met", true);
        oSUpdateEvent.setKey("update_conditions", str);
        log(oSUpdateEvent);
    }

    @Nullable
    private OSUpdaterSharedPreferences.TimeUpdate updateTimeForApplyUpdateFailed(String str) {
        if (((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong(str, 0) != 0) {
            return ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime(str);
        }
        return null;
    }

    public void logOSApplyUpdateFailed(String str, int i) {
        BLog.i(TAG, "logOSApplyUpdateFailed");
        OSUpdaterSharedPreferences.TimeUpdate updateTimeForApplyUpdateFailed = updateTimeForApplyUpdateFailed("prefs_apply_update_downloading_start_time");
        OSUpdaterSharedPreferences.TimeUpdate updateTimeForApplyUpdateFailed2 = updateTimeForApplyUpdateFailed("prefs_apply_update_verifying_start_time");
        OSUpdaterSharedPreferences.TimeUpdate updateTimeForApplyUpdateFailed3 = updateTimeForApplyUpdateFailed("prefs_apply_update_finalizing_start_time");
        OSUpdaterSharedPreferences.TimeUpdate updateTime = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_apply_update_start_time");
        int count = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_suspend_due_to_battery");
        int count2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_suspend_due_to_not_idle");
        int count3 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCount("prefs_apply_update_shutdown_count");
        int i2 = count + count2 + count3;
        float f = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getFloat("prefs_apply_update_percent_complete", 0.0f);
        int incrementCount = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).incrementCount("prefs_apply_update_failure_count");
        long buildVersion = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_base_version");
        long buildVersion2 = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_target_version");
        Long valueOf = Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("prefs_apply_update_size", -1));
        reportSoftErrorWithSystemLogs("update_engine_error_" + i, str, null);
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_apply_failed", true);
        oSUpdateEvent.setKey("base_version", Long.valueOf(buildVersion));
        oSUpdateEvent.setKey("update_version", Long.valueOf(buildVersion2));
        oSUpdateEvent.setKey("message", str);
        oSUpdateEvent.setKey("time_spent_applying_update", Long.valueOf(updateTime.diff));
        if (updateTimeForApplyUpdateFailed != null) {
            oSUpdateEvent.setKey("time_spent_downloading_update_secs", Long.valueOf(updateTimeForApplyUpdateFailed.diff));
        }
        if (updateTimeForApplyUpdateFailed2 != null) {
            oSUpdateEvent.setKey("time_spent_verifying_update_secs", Long.valueOf(updateTimeForApplyUpdateFailed2.diff));
        }
        if (updateTimeForApplyUpdateFailed3 != null) {
            oSUpdateEvent.setKey("time_spent_finalizing_update_secs", Long.valueOf(updateTimeForApplyUpdateFailed3.diff));
        }
        BLog.d(TAG, "logOSApplyUpdateFailed: total suspend count = %d", Integer.valueOf(i2));
        oSUpdateEvent.setKey("apply_update_suspend_count", Integer.valueOf(i2));
        oSUpdateEvent.setKey("apply_update_suspend_count_battery", Integer.valueOf(count));
        oSUpdateEvent.setKey("apply_update_suspend_count_not_idle", Integer.valueOf(count2));
        oSUpdateEvent.setKey("apply_update_shutdown_count", Integer.valueOf(count3));
        oSUpdateEvent.setKey("applying_update_percent_complete", Float.valueOf(f));
        BLog.d(TAG, "logOSApplyUpdateFailed: failure count = %d", Integer.valueOf(incrementCount));
        oSUpdateEvent.setKey("apply_update_current_failure_count", Integer.valueOf(incrementCount));
        oSUpdateEvent.setKey("file_size", valueOf);
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_suspend_due_to_battery");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_suspend_due_to_not_idle");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_update_shutdown_count");
        resetOtaSession();
    }

    public void logOSRebootStarted() {
        BLog.i(TAG, "logOSRebootStarted");
        OSUpdaterSharedPreferences.TimeUpdate updateTime = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_wait_for_reboot_start_time");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_reboot_started", true);
        oSUpdateEvent.setKey("time_spent_waiting_for_reboot", Long.valueOf(updateTime.diff));
        log(oSUpdateEvent);
    }

    public void logDeviceBooted() {
        BLog.i(TAG, "logDeviceBooted");
        log(new OSUpdateEvent("os_update_device_booted", true));
    }

    public void logOSRebootCompleted(String str, boolean z) {
        BLog.i(TAG, "logOSRebootCompleted");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_reboot_completed", true);
        oSUpdateEvent.setKey("build_version_pre_reboot", str);
        oSUpdateEvent.setKey("update_result_on_reboot", Boolean.valueOf(z));
        oSUpdateEvent.setKey("target_version", Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_target_version")));
        if (z) {
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).resetCount("prefs_apply_update_failure_count");
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setCurrentTime("prefs_boot_into_update_finish_time");
        }
        log(oSUpdateEvent);
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_check_for_update_start_time");
        ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).remove("prefs_apply_update_size").remove("prefs_apply_update_base_version").remove("prefs_apply_update_target_version").commit();
        resetOtaSession();
    }

    public void logOSUpdateShutdown() {
        BLog.i(TAG, "logOSUpdateShutdown");
        OSUpdaterSharedPreferences.TimeUpdate updateTime = ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(1, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).updateTime("prefs_wait_for_reboot_start_time");
        OSUpdateEvent oSUpdateEvent = new OSUpdateEvent("os_update_shutdown_triggered", true);
        oSUpdateEvent.setKey("time_spent_waiting_for_reboot", Long.valueOf(updateTime.diff));
        log(oSUpdateEvent);
    }

    public String reportFailFromUpdateEngine(Throwable th, String str) {
        String str2;
        BLog.i(TAG, "reportFailFromUpdateEngine");
        Integer serviceSpecificErrorCode = getServiceSpecificErrorCode(th);
        if (serviceSpecificErrorCode != null) {
            str2 = "Exception during " + str + " operation. Code " + serviceSpecificErrorCode;
            reportSoftErrorWithSystemLogs("update_engine_with_code", str2, th);
        } else {
            str2 = "Unknown exception during " + str + " operation.";
            reportSoftErrorWithSystemLogs("update_engine_generic", str2, th);
        }
        BLog.e(TAG, str2, th);
        return str2;
    }

    @Nullable
    public Integer getServiceSpecificErrorCode(Throwable th) {
        if ("android.os.ServiceSpecificException".equals(th.getClass().getName())) {
            try {
                return Integer.valueOf(th.getClass().getField("errorCode").getInt(th));
            } catch (Throwable th2) {
                String str = "Unable to get error code from " + th.toString();
                BLog.e(TAG, str, th2);
                reportSoftErrorWithSystemLogs("service_specific_code_error", str, null);
            }
        }
        return null;
    }

    private static AnalyticsEvent createAnalyticsEvent(OSUpdateEvent oSUpdateEvent) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(oSUpdateEvent.getName());
        JSONObject data = oSUpdateEvent.getData();
        Iterator<String> keys = data.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                analyticsEvent.setExtra(next, data.get(next));
            } catch (JSONException e) {
                BLog.d(TAG, "Failed to get data from JSON", (Throwable) e);
            }
        }
        return analyticsEvent;
    }

    private synchronized void flushEventQueue() {
        while (true) {
            OSUpdateEvent poll = this.mEventQueue.poll();
            if (poll != null) {
                this.mUnifiedTelemetryLogger.reportEvent(createAnalyticsEvent(poll), poll.getIsLowLatency());
            }
        }
    }

    public void reportSoftError(String str, String str2, @Nullable Throwable th) {
        BLog.i(TAG, "reportSoftError, message = %s", str2);
        if (th == null) {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(str, str2);
        } else {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(str, str2, th);
        }
    }

    public void reportSoftErrorWithSystemLogs(final String str, final String str2, @Nullable final Throwable th) {
        BLog.i(TAG, "reportSoftError, message = %s", str2);
        ((OculusThreadExecutor) FbInjector.lazyInstance(3, ExecutorsModule.UL_id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).execute(new Runnable() {
            /* class com.oculus.updater.core.logging.OSUpdateEventLogger.AnonymousClass1 */

            public void run() {
                Throwable addSystemLogs = OSUpdateEventLogger.this.addSystemLogs(th);
                if (addSystemLogs == null) {
                    ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, OSUpdateEventLogger.this._UL_mInjectionContext)).softError(str, str2);
                } else {
                    ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, OSUpdateEventLogger.this._UL_mInjectionContext)).softError(str, str2, addSystemLogs);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private synchronized Throwable addSystemLogs(@Nullable Throwable th) {
        if (Version.CURRENT_SDK_VERSION <= 10) {
            return th;
        }
        disableVisionLogCollection();
        File dir = this.mContext.getDir("errorlogs", 0);
        if (!dir.exists()) {
            BLog.d(TAG, "mkdir success = %b", Boolean.valueOf(dir.mkdir()));
        }
        try {
            File file = new File(dir.getCanonicalPath(), "logFile.zip");
            if (new LogCollectorClient().collectLogArchive(this.mContext, file).isError()) {
                BLog.e(TAG, "could not collect data from log collector");
                return th;
            }
            try {
                File unzipAndGetBugReportFile = unzipAndGetBugReportFile(file, dir);
                if (unzipAndGetBugReportFile != null) {
                    StringBuilder readBugReportFile = readBugReportFile(unzipAndGetBugReportFile);
                    if (th != null) {
                        new Throwable(readBugReportFile.toString());
                    } else {
                        th = new Throwable(readBugReportFile.toString());
                    }
                }
                if (dir.exists()) {
                    BLog.d(TAG, "errorLogDir delete = %b", Boolean.valueOf(deleteDirectory(dir)));
                }
                return th;
            } catch (IOException e) {
                BLog.e(TAG, "unzip failed", e);
                return th;
            }
        } catch (IOException e2) {
            BLog.e(TAG, "Failed to create zip file for log collector", e2);
            return th;
        }
    }

    private void disableVisionLogCollection() {
        if (((DeviceUtils) FbInjector.lazyInstance(2, DeviceModule.UL_id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isStandAloneDevice() && Version.CURRENT_SDK_VERSION >= 3) {
            new SettingsManager(this.mContext).setBoolean("bug_report_add_vision_logs", false);
        }
    }

    @Nullable
    private static File unzipAndGetBugReportFile(File file, File file2) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (true) {
                if (nextEntry != null) {
                    if (!nextEntry.isDirectory() && nextEntry.getName().contains("bugreport")) {
                        break;
                    }
                    nextEntry = zipInputStream.getNextEntry();
                } else {
                    break;
                }
            }
            if (nextEntry == null) {
                BLog.e(TAG, "Could not find bugreport*.txt file");
                zipInputStream.close();
                return null;
            }
            File file3 = new File(file2, nextEntry.getName());
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        zipInputStream.close();
                        return file3;
                    }
                }
            } catch (Throwable unused) {
            }
            throw th;
            throw th;
        } catch (Throwable unused2) {
        }
    }

    private static StringBuilder readBugReportFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = bufferedReader.readLine();
            boolean z = false;
            while (true) {
                if (readLine == null) {
                    break;
                }
                if (readLine.contains("------ SYSTEM LOG")) {
                    z = true;
                }
                if (z) {
                    if (readLine.contains("------ LOG STATISTICS")) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                }
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            BLog.e(TAG, "Could not read log", e);
        }
        return sb;
    }

    private static boolean deleteDirectory(File file) {
        boolean z;
        File[] listFiles;
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            z = true;
        } else {
            z = true;
            for (File file2 : listFiles) {
                z = z && deleteDirectory(file2);
            }
        }
        if (!z || !file.delete()) {
            return false;
        }
        return true;
    }
}
