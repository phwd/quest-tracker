package com.oculus.updater.core.monitors;

import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.updater.core.logging.LoggingModule;
import com.oculus.updater.core.logging.OSUpdateEventLogger;
import com.oculus.updater.core.monitors.BatteryMonitor;
import com.oculus.updater.core.monitors.CheckPeriodMonitor;
import com.oculus.updater.core.monitors.IdleMonitor;
import com.oculus.updater.core.monitors.MonitorsModule;
import com.oculus.updater.core.monitors.SystemUpdatePolicyMonitor;
import com.oculus.updater.core.monitors.WifiMonitor;
import com.oculus.updater.gk.GatekeeperHelper;
import com.oculus.updater.gk.GkModule;
import com.oculus.updater.prefs.OSUpdaterSharedPreferences;
import com.oculus.updater.prefs.PrefsModule;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies
@ApplicationScoped
public class ConditionManager implements BatteryMonitor.BatteryListener, CheckPeriodMonitor.CheckPeriodListener, IdleMonitor.OnIdleListener, SystemUpdatePolicyMonitor.SystemUpdatePolicyListener, WifiMonitor.WifiListener {
    private static final long DEFAULT_CHECK_DURATION_MINS = (ONE_HOUR_MINS * 4);
    private static final long IDLE_DURATION_FOR_CHECK_SEC;
    private static final long IDLE_DURATION_FOR_REBOOT_SEC = ONE_HOUR_SEC;
    private static final long IDLE_DURATION_FOR_UPDATE_SEC;
    private static final long MAX_CHECK_INTERVAL_MINS = (ONE_DAY_MINS * 3);
    private static final long ONE_DAY_MINS = TimeUnit.DAYS.toMinutes(1);
    private static final long ONE_HOUR_MINS = TimeUnit.HOURS.toMinutes(1);
    private static final long ONE_HOUR_SEC = TimeUnit.HOURS.toSeconds(1);
    private static final long ONE_MINUTE_SEC = TimeUnit.MINUTES.toSeconds(1);
    private static final String TAG = "ConditionManager";
    private static volatile ConditionManager _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;
    private boolean mAreConditionsMet = areConditionsMet();
    private boolean mIsAllowedBySystemUpdatePolicy = ((SystemUpdatePolicyMonitor) FbInjector.lazyInstance(6, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestAllowedByPolicy(this);
    private boolean mIsBatteryNormal = ((BatteryMonitor) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isBatteryNormal();
    private boolean mIsCheckPeriodElapsed = false;
    private boolean mIsDeviceOn = true;
    private boolean mIsIdle = ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestIdle(getIdleDurationForCheck(), 10, this);
    private boolean mIsWifiConnected = ((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isWifiConnected();
    @Nullable
    private ConditionListener mListener;
    private UpdatePhase mUpdatePhase = UpdatePhase.WaitingToCheckForUpdates;

    public enum CheckForUpdateConditions {
        Normal,
        NoCheckPeriod,
        NoCheckOrIdlePeriod
    }

    public interface ConditionListener {
        void onConditionsMet(UpdatePhase updatePhase, boolean z);

        void onConditionsNotMet(UpdatePhase updatePhase, boolean z, ConditionsChange conditionsChange);
    }

    public enum ConditionsChange {
        NONE,
        IDLE,
        NOT_IDLE,
        ABOVE_BATTERY_THRESHOLD,
        BELOW_BATTERY_THRESHOLD,
        WIFI_CONNECTED,
        WIFI_DISCONNECTED,
        CHECK_PERIOD_ELAPSED,
        SYSTEM_UPDATE_WINDOW_START,
        SYSTEM_UPDATE_WINDOW_END,
        DEVICE_NOT_ON
    }

    public enum UpdatePhase {
        WaitingToCheckForUpdates,
        WaitingToApplyUpdate,
        WaitingToRebootIntoUpdate
    }

    @AutoGeneratedAccessMethod
    public static final ConditionManager _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ConditionManager) UL.factorymap.get(MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ConditionManager _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_INSTANCE == null) {
            synchronized (ConditionManager.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_INSTANCE = new ConditionManager(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_INSTANCE;
    }

    static {
        long j = ONE_MINUTE_SEC;
        IDLE_DURATION_FOR_CHECK_SEC = j * 2;
        IDLE_DURATION_FOR_UPDATE_SEC = j * 2;
    }

    @Inject
    public ConditionManager(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(9, injectorLike);
    }

    public void setCheckIntervalMinutes(long j) {
        if (j <= 0 || j > MAX_CHECK_INTERVAL_MINS) {
            BLog.e(TAG, "Check for updates interval from server is outside normal range");
            ((IErrorReporter) FbInjector.lazyInstance(1, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError("server_check_interval_outside_range", "Check for updates interval from server is outside normal range");
            return;
        }
        BLog.i(TAG, "Check interval being set to %d minutes", Long.valueOf(j));
        if (((SharedPreferences) FbInjector.lazyInstance(0, PrefsModule.UL_id._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("scheduler_check_interval", -1) != j) {
            ((SharedPreferences) FbInjector.lazyInstance(0, PrefsModule.UL_id._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).edit().putLong("scheduler_check_interval", j).apply();
        }
    }

    public long getCheckDuration() {
        long j = ((SharedPreferences) FbInjector.lazyInstance(0, PrefsModule.UL_id._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getLong("scheduler_check_interval", DEFAULT_CHECK_DURATION_MINS) * ONE_MINUTE_SEC;
        if (!((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkTestModeGatekeeper()) {
            return j;
        }
        BLog.i(TAG, "Test mode active. Check duration is %d seconds", (Object) 70L);
        return 70;
    }

    public long getIdleDurationForCheck() {
        long j = IDLE_DURATION_FOR_CHECK_SEC;
        if (!((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkTestModeGatekeeper()) {
            return j;
        }
        BLog.i(TAG, "Test mode active. Idle duration for check is %d seconds", (Object) 30L);
        return 30;
    }

    public long getIdleDurationForUpdate() {
        long j = IDLE_DURATION_FOR_UPDATE_SEC;
        if (!((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkTestModeGatekeeper()) {
            return j;
        }
        BLog.i(TAG, "Test mode active. Idle duration for update is %d seconds", (Object) 40L);
        return 40;
    }

    public long getIdleDurationForReboot() {
        long j = IDLE_DURATION_FOR_REBOOT_SEC;
        if (!((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).checkTestModeGatekeeper()) {
            return j;
        }
        BLog.i(TAG, "Test mode active. Idle duration for reboot is %d seconds", (Object) 50L);
        return 50;
    }

    public void requestConditionsForWaitingToCheck(ConditionListener conditionListener, CheckForUpdateConditions checkForUpdateConditions) {
        BLog.e(TAG, "Requesting WaitingToCheckForUpdates conditions=%s", checkForUpdateConditions.toString());
        this.mUpdatePhase = UpdatePhase.WaitingToCheckForUpdates;
        this.mListener = conditionListener;
        ((BatteryMonitor) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
        if (checkForUpdateConditions == CheckForUpdateConditions.Normal) {
            ((CheckPeriodMonitor) FbInjector.lazyInstance(3, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setCheckPeriodAlarm(getCheckDuration(), 10, this);
            this.mIsCheckPeriodElapsed = false;
            if (((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
                ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_condition_check_period_alarm_pending", true).commit();
                ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_condition_check_period_elasped", false).commit();
            }
        } else {
            ((CheckPeriodMonitor) FbInjector.lazyInstance(3, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
            this.mIsCheckPeriodElapsed = true;
            if (((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
                ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_condition_check_period_elasped", true).commit();
            }
        }
        if (checkForUpdateConditions == CheckForUpdateConditions.NoCheckOrIdlePeriod) {
            ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).forceLongScreenOffTime();
        }
        this.mIsIdle = ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestIdle(getIdleDurationForCheck(), 10, this);
        this.mIsWifiConnected = ((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestConnected(this);
        this.mIsAllowedBySystemUpdatePolicy = ((SystemUpdatePolicyMonitor) FbInjector.lazyInstance(6, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestAllowedByPolicy(this);
        testInitialConditions();
    }

    public void requestConditionsForWaitingToApplyUpdate(ConditionListener conditionListener) {
        BLog.d(TAG, "Requesting WaitingToApplyUpdate");
        this.mUpdatePhase = UpdatePhase.WaitingToApplyUpdate;
        this.mListener = conditionListener;
        this.mIsBatteryNormal = ((BatteryMonitor) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestNormal(this);
        ((CheckPeriodMonitor) FbInjector.lazyInstance(3, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
        this.mIsIdle = ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestIdle(getIdleDurationForUpdate(), 10, this);
        ((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
        testInitialConditions();
    }

    public void requestConditionsForWaitingToReboot(ConditionListener conditionListener) {
        BLog.d(TAG, "Requesting WaitingToRebootIntoUpdate");
        this.mUpdatePhase = UpdatePhase.WaitingToRebootIntoUpdate;
        this.mListener = conditionListener;
        this.mIsBatteryNormal = ((BatteryMonitor) FbInjector.lazyInstance(2, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestNormal(this);
        ((CheckPeriodMonitor) FbInjector.lazyInstance(3, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
        this.mIsIdle = ((IdleMonitor) FbInjector.lazyInstance(4, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).requestIdle(getIdleDurationForReboot(), 10, this);
        ((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).cancelRequest();
        testInitialConditions();
    }

    public void setListener(ConditionListener conditionListener) {
        this.mListener = conditionListener;
    }

    private void testInitialConditions() {
        BLog.i(TAG, toString());
        this.mAreConditionsMet = areConditionsMet();
        if (this.mAreConditionsMet) {
            ConditionListener conditionListener = this.mListener;
            if (conditionListener != null) {
                conditionListener.onConditionsMet(this.mUpdatePhase, true);
                return;
            }
            return;
        }
        ConditionListener conditionListener2 = this.mListener;
        if (conditionListener2 != null) {
            conditionListener2.onConditionsNotMet(this.mUpdatePhase, true, ConditionsChange.NONE);
        }
    }

    private boolean areConditionsMet() {
        OSUpdateEventLogger oSUpdateEventLogger = (OSUpdateEventLogger) FbInjector.localInstance(LoggingModule.UL_id._UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_BINDING_ID, this._UL_mInjectionContext);
        boolean z = true;
        if (this.mUpdatePhase != UpdatePhase.WaitingToCheckForUpdates ? !this.mIsBatteryNormal || !this.mIsIdle : !isCheckPeriodElapsed() || !this.mIsIdle || !this.mIsWifiConnected || !this.mIsAllowedBySystemUpdatePolicy) {
            z = false;
        }
        if (!z) {
            oSUpdateEventLogger.logOSUpdateConditionsNotMet(toString());
        }
        return z;
    }

    private void testConditions(ConditionsChange conditionsChange) {
        BLog.i(TAG, toString());
        boolean areConditionsMet = areConditionsMet();
        boolean z = this.mAreConditionsMet;
        if (z == areConditionsMet) {
            BLog.d(TAG, "testConditions, return because old status (%b) = new status (%b)", Boolean.valueOf(z), Boolean.valueOf(areConditionsMet));
            return;
        }
        this.mAreConditionsMet = areConditionsMet;
        if (this.mAreConditionsMet) {
            BLog.d(TAG, "Conditions now met");
            ConditionListener conditionListener = this.mListener;
            if (conditionListener != null) {
                conditionListener.onConditionsMet(this.mUpdatePhase, false);
                return;
            }
            return;
        }
        BLog.d(TAG, "Conditions now not met");
        ConditionListener conditionListener2 = this.mListener;
        if (conditionListener2 != null) {
            conditionListener2.onConditionsNotMet(this.mUpdatePhase, false, conditionsChange);
        }
    }

    @Override // com.oculus.updater.core.monitors.IdleMonitor.OnIdleListener
    public void onIdle() {
        this.mIsIdle = true;
        testConditions(ConditionsChange.IDLE);
    }

    @Override // com.oculus.updater.core.monitors.IdleMonitor.OnIdleListener
    public void onNotIdle() {
        this.mIsIdle = false;
        testConditions(ConditionsChange.NOT_IDLE);
    }

    @Override // com.oculus.updater.core.monitors.BatteryMonitor.BatteryListener
    public void onNormal() {
        this.mIsBatteryNormal = true;
        testConditions(ConditionsChange.ABOVE_BATTERY_THRESHOLD);
    }

    @Override // com.oculus.updater.core.monitors.BatteryMonitor.BatteryListener
    public void onLow() {
        this.mIsBatteryNormal = false;
        testConditions(ConditionsChange.BELOW_BATTERY_THRESHOLD);
    }

    @Override // com.oculus.updater.core.monitors.WifiMonitor.WifiListener
    public void onConnected() {
        this.mIsWifiConnected = true;
        testConditions(ConditionsChange.WIFI_CONNECTED);
    }

    @Override // com.oculus.updater.core.monitors.WifiMonitor.WifiListener
    public void onDisconnected() {
        this.mIsWifiConnected = false;
        testConditions(ConditionsChange.WIFI_DISCONNECTED);
    }

    @Override // com.oculus.updater.core.monitors.CheckPeriodMonitor.CheckPeriodListener
    public void onCheckPeriodElapsed() {
        this.mIsCheckPeriodElapsed = true;
        if (((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_condition_check_period_elasped", true).commit();
            ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).putBoolean("prefs_condition_check_period_alarm_pending", false).commit();
        }
        testConditions(ConditionsChange.CHECK_PERIOD_ELAPSED);
    }

    @Override // com.oculus.updater.core.monitors.SystemUpdatePolicyMonitor.SystemUpdatePolicyListener
    public void onSystemPolicyUpdateWindowStart() {
        BLog.d(TAG, "onSystemPolicyUpdateWindowStart called");
        this.mIsAllowedBySystemUpdatePolicy = true;
        testConditions(ConditionsChange.SYSTEM_UPDATE_WINDOW_START);
    }

    @Override // com.oculus.updater.core.monitors.SystemUpdatePolicyMonitor.SystemUpdatePolicyListener
    public void onSystemPolicyUpdateWindowEnd() {
        BLog.d(TAG, "onSystemPolicyUpdateWindowEnd called");
        this.mIsAllowedBySystemUpdatePolicy = false;
        testConditions(ConditionsChange.SYSTEM_UPDATE_WINDOW_END);
    }

    public void setShuttingDown() {
        this.mIsDeviceOn = false;
        testConditions(ConditionsChange.DEVICE_NOT_ON);
    }

    public UpdatePhase getUpdatePhase() {
        return this.mUpdatePhase;
    }

    public void setUpdatePhase(UpdatePhase updatePhase) {
        this.mUpdatePhase = updatePhase;
    }

    public boolean isCheckPeriodElapsed() {
        if (((GatekeeperHelper) FbInjector.lazyInstance(8, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
            return ((OSUpdaterSharedPreferences) FbInjector.lazyInstance(7, PrefsModule.UL_id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getBoolean("prefs_condition_check_period_elasped", false);
        }
        return this.mIsCheckPeriodElapsed;
    }

    public boolean isWifiConnected() {
        this.mIsWifiConnected = ((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNetworkAvailable(((WifiMonitor) FbInjector.lazyInstance(5, MonitorsModule.UL_id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isWifiConnected());
        return this.mIsWifiConnected;
    }

    public boolean isIdle() {
        return this.mIsIdle;
    }

    public boolean isBatteryLevelAboveThreshold() {
        return this.mIsBatteryNormal;
    }

    public boolean isAllowedBySystemUpdatePolicy() {
        return this.mIsAllowedBySystemUpdatePolicy;
    }

    public String toString() {
        return String.format(null, "UpdatePhase=%s CheckPeriodElapsed=%b Wifi=%b Idle=%b Battery=%b DeviceOn=%b", this.mUpdatePhase.toString(), Boolean.valueOf(isCheckPeriodElapsed()), Boolean.valueOf(this.mIsWifiConnected), Boolean.valueOf(this.mIsIdle), Boolean.valueOf(this.mIsBatteryNormal), Boolean.valueOf(this.mIsDeviceOn));
    }
}