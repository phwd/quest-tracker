package com.oculus.updater;

import android.content.Context;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.os.DumpsysProxyClientService;
import com.oculus.updater.core.monitors.BatteryMonitor;
import com.oculus.updater.core.monitors.ConditionManager;
import com.oculus.updater.core.monitors.IdleMonitor;
import com.oculus.updater.core.monitors.MonitorsModule;
import com.oculus.updater.core.monitors.WifiMonitor;
import com.oculus.updater.core.os.OSUpdateManager;
import com.oculus.updater.core.os.OSUpdateModule;
import com.oculus.updater.core.os.SystemProperties;
import com.oculus.updater.gk.GatekeeperHelper;
import com.oculus.updater.gk.GkModule;
import com.oculus.updater.prefs.OSUpdaterSharedPreferences;
import com.oculus.updater.prefs.PrefsModule;
import java.util.Locale;

public class OculusUpdaterDumpService extends DumpsysProxyClientService {
    private InjectionContext _UL_mInjectionContext;

    public String getAppName() {
        return "OculusUpdater";
    }

    private static final void _UL_injectMe(Context context, OculusUpdaterDumpService oculusUpdaterDumpService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusUpdaterDumpService);
        } else {
            FbInjector.injectMe(OculusUpdaterDumpService.class, oculusUpdaterDumpService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusUpdaterDumpService oculusUpdaterDumpService) {
        oculusUpdaterDumpService._UL_mInjectionContext = new InjectionContext(7, injectorLike);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.oculus.updater.OculusUpdaterDumpService */
    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        OculusUpdaterDumpService.super.onCreate();
        _UL_injectMe(this, this);
    }

    public String dumpState() {
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[23];
        objArr[0] = Boolean.valueOf(((OSUpdateManager) FbInjector.lazyInstance(0, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isDeviceUpdateable());
        objArr[1] = Boolean.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isAllowedBySystemUpdatePolicy());
        objArr[2] = Boolean.valueOf(((SystemProperties) FbInjector.lazyInstance(1, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isOtaDisabledByUser());
        objArr[3] = ((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdatePhase().name();
        objArr[4] = Boolean.valueOf(((OSUpdateManager) FbInjector.lazyInstance(0, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isUpdateInProgress());
        objArr[5] = Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(5, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_base_version"));
        objArr[6] = Long.valueOf(((OSUpdaterSharedPreferences) FbInjector.lazyInstance(5, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBuildVersion("prefs_apply_update_target_version"));
        objArr[7] = Boolean.valueOf(((OSUpdateManager) FbInjector.lazyInstance(0, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isFullUpdateFallbackMode());
        objArr[8] = Long.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCheckDuration() / 60);
        objArr[9] = Boolean.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isCheckPeriodElapsed());
        objArr[10] = Boolean.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isWifiConnected());
        objArr[11] = Boolean.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isIdle());
        objArr[12] = Boolean.valueOf(!((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isBatteryLevelAboveThreshold());
        objArr[13] = Boolean.valueOf(WifiMonitor.isOculusAddressReachable());
        objArr[14] = Boolean.valueOf(((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isInteractive());
        objArr[15] = Long.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getIdleDurationForCheck());
        objArr[16] = Long.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getIdleDurationForUpdate());
        objArr[17] = Long.valueOf(((ConditionManager) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getIdleDurationForReboot());
        long j = 0;
        if (((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getTimeSpentIdling() != 0) {
            j = ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getTimeSpentIdling() / 60000;
        }
        objArr[18] = Long.valueOf(j);
        objArr[19] = Integer.valueOf(((BatteryMonitor) FbInjector.lazyInstance(3, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBatteryLevel());
        objArr[20] = 30;
        objArr[21] = Boolean.valueOf(((GatekeeperHelper) FbInjector.lazyInstance(6, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkTestModeGatekeeper());
        objArr[22] = Boolean.valueOf(((GatekeeperHelper) FbInjector.lazyInstance(6, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled());
        return String.format(locale, "Supports A/B updates: %b\nAllowed by system update policy: %b\nDisabled: %b\nUpdate phase (this is misleading): %s\nIs update in progress (includes checking + applying + rebooting): %b\nOTA package base build: %d\nOTA package target build: %d\nIs full update: %b\nCheck period: %d mins\nCheck period elapsed: %b\nWifi connected: %b\nIdle: %b\nLow battery: %b\nWifi can ping oculus.com: %b\nIdle check, is screen on: %b\nIdle timeout requirement for update check: %d sec\nIdle timeout requirement for update apply: %d sec\nIdle timeout requirement for update reboot: %d sec\nIdle check, time spent in idle: %d mins\nBattery level: %d\nLow battery threshold: %d\nIs test mode enabled = %b\nIs nonpersistent enabled = %b\n", objArr);
    }
}
