package com.oculus.unifiedtelemetry.unifiedlogging.utils;

import com.facebook.ultralight.Dependencies;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.oculus.horizon.service.ServiceLogger;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.remotewipe.WipeTelemetry;
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
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("arvr_enterprise_server_yadi", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_config_fetch_started", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_config_fetch_retry", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_config_fetch_completed", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_os_update_configured", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_device_state_upload_started", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_device_state_upload_succeeded", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("arvr_enterprise_server_device_state_upload_failed", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("os_update_process_started", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_check_started", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_check_completed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_check_failed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_apply_started", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_apply_resumed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_apply_suspended", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_apply_completed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_apply_failed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_reboot_started", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_reboot_completed", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_shutdown_triggered", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("os_update_conditions_not_met", new SingletonImmutableSet("com.oculus.updater"));
        A01.put("oc_mobileconfig_refresh_started", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put(MobileConfigConfiguration.EVENT_MOBILE_CONFIG_FETCH_RESULT, new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("oc_mobileconfig_schedule_init", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("oc_mobileconfig_schedule_successful", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("oc_mobileconfig_schedule_failed", new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put(WipeTelemetry.EVENT_WIPE_REQUESTED, new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put(WipeTelemetry.EVENT_WIPE_SUCCESS, new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put(WipeTelemetry.EVENT_WIPE_FAIL, new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put(WipeTelemetry.EVENT_WIPE_RETRY, new SingletonImmutableSet("com.oculus.alpenglow"));
        A01.put("device_info", new SingletonImmutableSet("com.oculus.unifiedtelemetry"));
        A01.put(ServiceLogger.EVENT_SDK_LOGGING, new SingletonImmutableSet(ANY_PACKAGE_NAME));
        sManagedEventNameAndPackageAllowList = A01.build();
    }
}
