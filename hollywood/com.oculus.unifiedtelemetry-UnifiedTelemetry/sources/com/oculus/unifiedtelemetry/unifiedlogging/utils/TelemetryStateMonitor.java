package com.oculus.unifiedtelemetry.unifiedlogging.utils;

import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.updater.MobileConfigUpdaterInit;
import com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService;
import com.oculus.unifiedtelemetry.unifiedlogging.PresenceReporter;
import com.oculus.unifiedtelemetry.unifiedlogging.SendDeviceInfoJobService;
import com.oculus.unifiedtelemetry.unifiedlogging.utils.SettingsManager;
import java.util.Map;
import javax.annotation.Nullable;

@Dependencies({})
public class TelemetryStateMonitor {
    public static final String ANY_PACKAGE_NAME = "ANY";
    public static final String ENTERPRISE_PACKAGE_NAME = "com.oculus.alpenglow";
    public static final String UPDATER_PACKAGE_NAME = "com.oculus.updater";
    public static final String UT_PACKAGE_NAME = "com.oculus.unifiedtelemetry";
    public static final Map<String, ImmutableSet<String>> sManagedEventNameAndPackageAllowList;
    @Nullable
    public SettingsManager mSettingsManager = new SettingsManager();

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("arvr_enterprise_server_yadi", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_config_fetch_started", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_config_fetch_retry", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_config_fetch_completed", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_os_update_configured", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_device_state_upload_started", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_device_state_upload_succeeded", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("arvr_enterprise_server_device_state_upload_failed", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("os_update_process_started", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_check_started", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_check_completed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_check_failed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_apply_started", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_apply_resumed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_apply_suspended", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_apply_completed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_apply_failed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_reboot_started", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_reboot_completed", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_shutdown_triggered", new SingletonImmutableSet("com.oculus.updater"));
        builder.put("os_update_conditions_not_met", new SingletonImmutableSet("com.oculus.updater"));
        builder.put(MobileConfigUpdaterJobService.EVENT_MOBILE_CONFIG_REFRESH, new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put(MobileConfigConfiguration.EVENT_MOBILE_CONFIG_FETCH_RESULT, new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put(MobileConfigUpdaterInit.EVENT_SCHEDULE_INIT, new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put(MobileConfigUpdaterInit.EVENT_SCHEDULE_SUCCESSFUL, new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put(MobileConfigUpdaterInit.EVENT_SCHEDULE_FAILED, new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("oculus_mobile_remote_wipe_requested", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("oculus_mobile_remote_wipe_success", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("oculus_mobile_remote_wipe_fail", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put("oculus_mobile_remote_wipe_retry", new SingletonImmutableSet("com.oculus.alpenglow"));
        builder.put(SendDeviceInfoJobService.DEVICE_INFO_EVENT_NAME, new SingletonImmutableSet("com.oculus.unifiedtelemetry"));
        builder.put(PresenceReporter.EVENT_NAME, new SingletonImmutableSet(ANY_PACKAGE_NAME));
        sManagedEventNameAndPackageAllowList = builder.build();
    }

    public final boolean A00() {
        SettingsManager settingsManager = this.mSettingsManager;
        if (settingsManager == null || settingsManager.mTelemetryState != SettingsManager.TelemetryState.OFF) {
            return false;
        }
        return true;
    }

    public final boolean A01(String str, @Nullable String str2) {
        boolean z;
        if (str2 == null) {
            SettingsManager settingsManager = this.mSettingsManager;
            if (settingsManager == null || settingsManager.mTelemetryState != SettingsManager.TelemetryState.OFF) {
                return false;
            }
            return true;
        }
        ImmutableSet<String> immutableSet = sManagedEventNameAndPackageAllowList.get(str);
        if (immutableSet == null) {
            z = false;
        } else if (immutableSet.contains(ANY_PACKAGE_NAME)) {
            z = true;
        } else {
            z = immutableSet.contains(str2);
        }
        SettingsManager settingsManager2 = this.mSettingsManager;
        if (settingsManager2 == null || settingsManager2.mTelemetryState != SettingsManager.TelemetryState.OFF || z) {
            return false;
        }
        return true;
    }
}
