package com.oculus.updater.core.os;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.annotation.Nullable;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.time.Clock;
import com.oculus.time.TimeModule;
import com.oculus.updater.core.broadcast.BroadcastState;
import com.oculus.updater.core.logging.OSUpdateEventLogger;
import com.oculus.updater.core.monitors.BatteryMonitor;
import com.oculus.updater.core.monitors.CheckPeriodMonitor;
import com.oculus.updater.core.monitors.ConditionManager;
import com.oculus.updater.core.monitors.IdleMonitor;
import com.oculus.updater.core.monitors.SystemUpdatePolicyMonitor;
import com.oculus.updater.core.monitors.WifiMonitor;
import com.oculus.updater.core.os.OSUpdateManager;
import com.oculus.updater.core.os.OSUpdateModule;
import com.oculus.updater.core.os.contract.OSContractModule;
import com.oculus.updater.core.os.contract.OSUpdateServiceContract;
import com.oculus.updater.device.DeviceInfo;
import com.oculus.updater.device.DeviceModule;
import com.oculus.updater.gk.GatekeeperHelper;
import com.oculus.updater.gk.GkModule;
import com.oculus.updater.prefs.OSUpdaterSharedPreferences;
import java.util.Locale;

public class OSUpdateService extends IntentService {
    private static final String TAG = "OSUpdateService";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private BatteryMonitor mBatteryMonitor;
    @Inject
    @Eager
    private BroadcastState mBroadcastState;
    @Inject
    @Eager
    private CheckPeriodMonitor mCheckPeriodMonitor;
    @Inject
    @Eager
    private ConditionManager mConditionManager;
    @Inject
    @Eager
    private OSUpdateEventLogger mEventLogger;
    @Inject
    @Eager
    private IdleMonitor mIdleMonitor;
    @Inject
    @Eager
    private OSUpdateManager mOSUpdateManager;
    @Inject
    @Eager
    private OSUpdaterSharedPreferences mPrefs;
    @Inject
    @Eager
    private SystemUpdatePolicyMonitor mSystemUpdatePolicyMonitor;
    @Inject
    @Eager
    private WifiMonitor mWifiMonitor;

    private void setOTADisabledState(Intent intent) {
    }

    private static final void _UL_injectMe(Context context, OSUpdateService oSUpdateService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oSUpdateService);
        } else {
            FbInjector.injectMe(OSUpdateService.class, oSUpdateService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OSUpdateService oSUpdateService) {
        oSUpdateService._UL_mInjectionContext = new InjectionContext(5, injectorLike);
        oSUpdateService.mOSUpdateManager = OSUpdateManager._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mConditionManager = ConditionManager._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mBatteryMonitor = BatteryMonitor._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mWifiMonitor = WifiMonitor._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mIdleMonitor = IdleMonitor._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mCheckPeriodMonitor = CheckPeriodMonitor._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mSystemUpdatePolicyMonitor = SystemUpdatePolicyMonitor._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mEventLogger = OSUpdateEventLogger._UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mBroadcastState = BroadcastState._UL__ULSEP_com_oculus_updater_core_broadcast_BroadcastState_ULSEP_ACCESS_METHOD(injectorLike);
        oSUpdateService.mPrefs = OSUpdaterSharedPreferences._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public OSUpdateService() {
        super(TAG);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            BLog.i(TAG, "Intent with action %s", action);
            char c = 65535;
            switch (action.hashCode()) {
                case -1970424036:
                    if (action.equals("apply_update_succeeded")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1887167283:
                    if (action.equals("system_update_policy_changed")) {
                        c = 20;
                        break;
                    }
                    break;
                case -1809525874:
                    if (action.equals("set_ota_disabled_state")) {
                        c = 26;
                        break;
                    }
                    break;
                case -1554255772:
                    if (action.equals("BatteryAlarm")) {
                        c = 18;
                        break;
                    }
                    break;
                case -1525162093:
                    if (action.equals("system_network_changed")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1413750759:
                    if (action.equals("ext_boot_into_update")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1316095591:
                    if (action.equals("ext_check_updates_full")) {
                        c = 14;
                        break;
                    }
                    break;
                case -725974228:
                    if (action.equals("updater_get_current_state")) {
                        c = 24;
                        break;
                    }
                    break;
                case -687683152:
                    if (action.equals("apply_verifying_update")) {
                        c = 11;
                        break;
                    }
                    break;
                case -591778115:
                    if (action.equals("IdleAlarm")) {
                        c = 17;
                        break;
                    }
                    break;
                case -538926661:
                    if (action.equals("boot_into_update")) {
                        c = 6;
                        break;
                    }
                    break;
                case -144874969:
                    if (action.equals("WifiWaitAlarm")) {
                        c = 16;
                        break;
                    }
                    break;
                case 178840723:
                    if (action.equals("check_updates")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 266376955:
                    if (action.equals("get_update_info")) {
                        c = 22;
                        break;
                    }
                    break;
                case 390867864:
                    if (action.equals("UpdateWindowAlarm")) {
                        c = 19;
                        break;
                    }
                    break;
                case 433383495:
                    if (action.equals("system_interactive_changed")) {
                        c = 5;
                        break;
                    }
                    break;
                case 454157426:
                    if (action.equals("system_battery_changed")) {
                        c = 2;
                        break;
                    }
                    break;
                case 583496437:
                    if (action.equals("ext_check_updates")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 1006595061:
                    if (action.equals("check_ota_availability")) {
                        c = 23;
                        break;
                    }
                    break;
                case 1128371976:
                    if (action.equals("CheckPeriodAlarm")) {
                        c = 15;
                        break;
                    }
                    break;
                case 1138970978:
                    if (action.equals("apply_update_failed")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1367341116:
                    if (action.equals("system_connectivity_changed")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1447785236:
                    if (action.equals("system_rebooted")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1528466711:
                    if (action.equals("apply_update_status")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1888960471:
                    if (action.equals("cancel_ota")) {
                        c = 25;
                        break;
                    }
                    break;
                case 2058757809:
                    if (action.equals("process_started")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2106378854:
                    if (action.equals("system_shutdown")) {
                        c = 21;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    onProcessStarted();
                    return;
                case 1:
                    onSystemRebooted();
                    return;
                case 2:
                    onSystemBatteryChanged();
                    return;
                case 3:
                    onSystemNetworkChanged(intent);
                    return;
                case 4:
                    onSystemConnectivityChange(intent);
                    return;
                case 5:
                    onSystemInteractiveChanged();
                    return;
                case 6:
                case 7:
                    onBootIntoUpdate();
                    return;
                case '\b':
                    onApplyUpdateSucceeded();
                    return;
                case '\t':
                    onApplyUpdateFailed(intent);
                    return;
                case '\n':
                    onApplyUpdateStatus(intent);
                    return;
                case 11:
                    onVerifyingUpdate();
                    return;
                case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID:
                case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID:
                case UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID:
                    onCheckUpdates(intent);
                    return;
                case UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID:
                    onCheckPeriodAlarm();
                    return;
                case UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID:
                    onWifiWaitAlarm();
                    return;
                case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID:
                    onIdleAlarm();
                    return;
                case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID:
                    onBatteryAlarm();
                    return;
                case UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID:
                case UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID:
                    onUpdateStatusPolicyNeedsUpdate(intent);
                    return;
                case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID:
                    onSystemShutdown();
                    return;
                case UL.id._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID:
                    onGetUpdateInfo(intent);
                    return;
                case UL.id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID:
                    checkForOTAvailability(intent);
                    return;
                case UL.id._UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_BINDING_ID:
                    getCurrentUpdaterStatus(intent);
                    return;
                case 25:
                    cancelOTA();
                    return;
                case UL.id._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID:
                    setOTADisabledState(intent);
                    return;
                default:
                    return;
            }
        }
    }

    private void onProcessStarted() {
        if (((GatekeeperHelper) FbInjector.lazyInstance(1, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
            try {
                int updateEngineState = ((UpdateMonitor) FbInjector.lazyInstance(2, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdateEngineState();
                this.mEventLogger.logOSUpdateProcessStarted(Integer.valueOf(updateEngineState));
                switch (updateEngineState) {
                    case 0:
                        try {
                            if (((UpdateMonitor) FbInjector.lazyInstance(2, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdateEngineErrorCode() != -1) {
                                reportUpdateFailed();
                                break;
                            } else {
                                setCheckPeriodAlarmIfNotSet();
                                break;
                            }
                        } catch (RuntimeException e) {
                            BLog.e(TAG, "Couldn't connect to OsUpdateMonitor", e);
                            break;
                        }
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        this.mConditionManager.setUpdatePhase(ConditionManager.UpdatePhase.WaitingToApplyUpdate);
                        this.mOSUpdateManager.setUpdateInProgress(true);
                        this.mIdleMonitor.cancelRequest();
                        this.mOSUpdateManager.registerStatusReceiver();
                        break;
                    case 6:
                        this.mConditionManager.setUpdatePhase(ConditionManager.UpdatePhase.WaitingToRebootIntoUpdate);
                        this.mOSUpdateManager.setUpdateInProgress(true);
                        this.mOSUpdateManager.setWaitForReboot(true);
                        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifyApplyUpdateSucceeded();
                        break;
                    case 7:
                        reportUpdateFailed();
                        break;
                    default:
                        String str = TAG;
                        BLog.w(str, "Entered unknown/idle state Update Engine state: %d", Integer.valueOf(updateEngineState));
                        setCheckPeriodAlarmIfNotSet();
                        break;
                }
            } catch (RuntimeException e2) {
                BLog.e(TAG, "Couldn't connect to OsUpdateMonitor", e2);
                setCheckPeriodAlarmIfNotSet();
            } catch (Throwable th) {
                this.mConditionManager.setListener(this.mOSUpdateManager);
                throw th;
            }
            this.mConditionManager.setListener(this.mOSUpdateManager);
            return;
        }
        this.mEventLogger.logOSUpdateProcessStarted(null);
        this.mOSUpdateManager.cancelOTA();
    }

    private void setCheckPeriodAlarmIfNotSet() {
        if (!this.mPrefs.getBoolean("prefs_condition_check_period_elasped", false) && !this.mPrefs.getBoolean("prefs_condition_check_period_alarm_pending", false)) {
            this.mConditionManager.requestConditionsForWaitingToCheck(this.mOSUpdateManager, ConditionManager.CheckForUpdateConditions.Normal);
        }
    }

    private void reportUpdateFailed() {
        int i;
        try {
            i = ((UpdateMonitor) FbInjector.lazyInstance(2, OSUpdateModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getUpdateEngineErrorCode();
        } catch (RuntimeException e) {
            BLog.e(TAG, "Couldn't connect to OsUpdateMonitor", e);
            i = 1;
        }
        ((OSUpdateServiceContract) FbInjector.lazyInstance(0, OSContractModule.UL_id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID, this._UL_mInjectionContext)).notifyApplyUpdateFailed(String.format(null, "Installation failed. Error code: %d", Integer.valueOf(i)), i);
    }

    private void onSystemRebooted() {
        if (((GatekeeperHelper) FbInjector.lazyInstance(1, GkModule.UL_id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isNonpersistentEnabled()) {
            this.mEventLogger.logDeviceBooted();
            this.mOSUpdateManager.cancelOTA();
        }
        this.mOSUpdateManager.onReboot();
    }

    private void onSystemBatteryChanged() {
        this.mBatteryMonitor.onBatteryChanged();
    }

    private void onSystemNetworkChanged(Intent intent) {
        this.mWifiMonitor.onNetworkChanged(intent);
    }

    private void onSystemConnectivityChange(Intent intent) {
        this.mWifiMonitor.onConnectivityChange(intent);
    }

    private void onSystemInteractiveChanged() {
        this.mIdleMonitor.onInteractiveChanged();
    }

    private void onCheckUpdates(Intent intent) {
        String action = intent.getAction();
        boolean z = !"check_updates".equals(action);
        boolean equals = "ext_check_updates_full".equals(action);
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        String otaState = getOtaState();
        char c = 65535;
        switch (otaState.hashCode()) {
            case -1977205868:
                if (otaState.equals("ota_update_in_progress")) {
                    c = 1;
                    break;
                }
                break;
            case -1917034805:
                if (otaState.equals("Check for updates stopped due to no wifi connection")) {
                    c = 6;
                    break;
                }
                break;
            case -1665620570:
                if (otaState.equals("state_not_allowed_by_system")) {
                    c = 4;
                    break;
                }
                break;
            case -252387059:
                if (otaState.equals("waiting_for_reboot")) {
                    c = 0;
                    break;
                }
                break;
            case 475616557:
                if (otaState.equals("state_ota_disabled")) {
                    c = 3;
                    break;
                }
                break;
            case 1772672651:
                if (otaState.equals("Device is not configured for AB updates")) {
                    c = 5;
                    break;
                }
                break;
            case 1969912373:
                if (otaState.equals("state_ready_to_check_for_ota")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mBroadcastState.waitingForReboot();
                if (resultReceiver != null) {
                    resultReceiver.send(1, createOtaUpdateResponseBundle(null));
                    break;
                }
                break;
            case 1:
                if (resultReceiver != null) {
                    resultReceiver.send(1, createOtaUpdateResponseBundle(null));
                    break;
                }
                break;
            case 2:
                this.mOSUpdateManager.setUpdateInProgress(true);
                this.mBroadcastState.checkingForUpdates();
                this.mOSUpdateManager.setExternalRequest(z);
                OSUpdateManager.UpdateCheckResponse performUpdateCheck = this.mOSUpdateManager.performUpdateCheck(false, equals);
                if (!performUpdateCheck.isSuccess()) {
                    if ("OTA update check failed because no device auth token was found".equals(performUpdateCheck.getErrorMessage()) && action != null && !"ext_check_updates".equals(this.mPrefs.getString("prefs_device_auth_trigger_check_for_updates", null))) {
                        this.mPrefs.putString("prefs_device_auth_trigger_check_for_updates", action).commit();
                    }
                    this.mBroadcastState.errorWhileCheckingForUpdates(performUpdateCheck.getErrorMessage());
                    this.mOSUpdateManager.setUpdateInProgress(false);
                    if (resultReceiver != null) {
                        resultReceiver.send(1, createOtaUpdateResponseBundle(performUpdateCheck));
                        break;
                    }
                } else {
                    if (performUpdateCheck.getOSReleaseInfo() != null) {
                        this.mOSUpdateManager.performApplyUpdate(performUpdateCheck.getOSReleaseInfo());
                    } else {
                        this.mBroadcastState.noUpdatesAvailable();
                        this.mPrefs.putBoolean("ota_no_updates_available", true).commit();
                        this.mOSUpdateManager.setUpdateInProgress(false);
                    }
                    if (resultReceiver != null) {
                        resultReceiver.send(0, createOtaUpdateResponseBundle(performUpdateCheck));
                        break;
                    }
                }
                break;
            case 3:
            case 4:
                this.mBroadcastState.noUpdatesAvailable();
                this.mPrefs.putBoolean("ota_no_updates_available", true).commit();
                if (resultReceiver != null) {
                    resultReceiver.send(1, createOtaUpdateResponseBundle(null));
                    break;
                }
                break;
            case 5:
            case 6:
                this.mBroadcastState.errorWhileCheckingForUpdates(otaState);
                if (resultReceiver != null) {
                    resultReceiver.send(1, createOtaUpdateResponseBundle(null));
                    break;
                }
                break;
        }
        requestNewCheck(z);
    }

    private void requestNewCheck(boolean z) {
        if (!z && !this.mOSUpdateManager.isUpdateInProgress()) {
            BLog.i(TAG, "Check for updates completed, no update being applied. Schedule next check.");
            this.mConditionManager.requestConditionsForWaitingToCheck(this.mOSUpdateManager, ConditionManager.CheckForUpdateConditions.Normal);
        }
    }

    private void onBootIntoUpdate() {
        this.mOSUpdateManager.performReboot();
    }

    private void onApplyUpdateSucceeded() {
        this.mOSUpdateManager.applyUpdateSucceeded();
    }

    private void onApplyUpdateFailed(Intent intent) {
        String stringExtra = intent.getStringExtra("failure_message");
        if (stringExtra == null) {
            BLog.e(TAG, "Extra 'EXTRA_FAILURE_MESSAGE' not found for intent action 'ACTION_APPLY_UPDATE_FAILED'");
            this.mEventLogger.reportSoftError("extra_not_found", "Extra 'EXTRA_FAILURE_MESSAGE' not found for intent action 'ACTION_APPLY_UPDATE_FAILED'", null);
            stringExtra = "<unknown>";
        }
        this.mOSUpdateManager.applyUpdateFailed(stringExtra, intent.getIntExtra("failure_code", -1));
    }

    private void onApplyUpdateStatus(Intent intent) {
        float floatExtra = intent.getFloatExtra("percent_complete", -1.0f);
        if (floatExtra == -1.0f) {
            BLog.e(TAG, "Extra 'EXTRA_PERCENT_COMPLETE' not found for intent action 'ACTION_APPLY_UPDATE_STATUS'");
            this.mEventLogger.reportSoftError("extra_not_found", "Extra 'EXTRA_PERCENT_COMPLETE' not found for intent action 'ACTION_APPLY_UPDATE_STATUS'", null);
            floatExtra = 50.0f;
        }
        this.mOSUpdateManager.applyUpdateStatus(floatExtra);
    }

    private void onVerifyingUpdate() {
        this.mOSUpdateManager.verifyingUpdate();
    }

    private void onCheckPeriodAlarm() {
        this.mCheckPeriodMonitor.onAlarm();
    }

    private void onWifiWaitAlarm() {
        this.mWifiMonitor.onAlarm();
    }

    private void onIdleAlarm() {
        this.mIdleMonitor.onAlarm();
    }

    private void onBatteryAlarm() {
        this.mBatteryMonitor.onAlarm();
    }

    private void onUpdateStatusPolicyNeedsUpdate(Intent intent) {
        BLog.v(TAG, "onUpdateStatusPolicyNeedsUpdate for action=%s", intent.getAction());
        this.mSystemUpdatePolicyMonitor.onPolicyStatusChange();
    }

    private void onSystemShutdown() {
        if (this.mOSUpdateManager.isWaitForReboot()) {
            this.mEventLogger.logOSUpdateShutdown();
        } else if (this.mOSUpdateManager.isUpdateInProgress()) {
            this.mConditionManager.setShuttingDown();
        }
    }

    @SuppressLint({"NewApi"})
    private void onGetUpdateInfo(Intent intent) {
        try {
            ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
            if (resultReceiver != null) {
                resultReceiver.send(-1, buildUpdateInfoBundle());
            }
        } catch (Throwable th) {
            BLog.e(TAG, "Error while getting receiver ", th);
        }
    }

    private Bundle buildUpdateInfoBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("state", this.mOSUpdateManager.areNoOTAUpdatesAvailable() ? "no_updates_available" : "checking_for_updates");
        return bundle;
    }

    private String getOtaState() {
        String str;
        if (this.mOSUpdateManager.isWaitForReboot()) {
            str = "waiting_for_reboot";
        } else if (this.mOSUpdateManager.isUpdateInProgress()) {
            str = "ota_update_in_progress";
        } else {
            str = this.mOSUpdateManager.canPerformUpdateCheck();
        }
        BLog.d(TAG, "getOtaState: %s", str);
        return str;
    }

    private Bundle createOtaStateBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("ota_updater_state", getOtaState());
        bundle.putLong("current_os_version", ((DeviceInfo) FbInjector.lazyInstance(3, DeviceModule.UL_id._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getDeviceVersion());
        long now = ((Clock) FbInjector.lazyInstance(4, TimeModule.UL_id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID, this._UL_mInjectionContext)).now();
        long j = Long.MIN_VALUE;
        long j2 = this.mPrefs.getLong("prefs_boot_into_update_finish_time", Long.MIN_VALUE);
        bundle.putLong("time_since_last_update_msec", j2 != Long.MIN_VALUE ? now - j2 : Long.MIN_VALUE);
        long j3 = this.mPrefs.getLong("prefs_check_for_updates_finish_time", Long.MIN_VALUE);
        if (j3 != Long.MIN_VALUE) {
            j = now - j3;
        }
        bundle.putLong("time_since_last_update_check_msec", j);
        return bundle;
    }

    private Bundle createOtaUpdateResponseBundle(@Nullable OSUpdateManager.UpdateCheckResponse updateCheckResponse) {
        String str;
        Bundle bundle = new Bundle();
        String otaState = getOtaState();
        bundle.putString("ota_updater_state", otaState);
        bundle.putLong("current_os_version", ((DeviceInfo) FbInjector.lazyInstance(3, DeviceModule.UL_id._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getDeviceVersion());
        if (updateCheckResponse != null) {
            if (updateCheckResponse.isSuccess()) {
                bundle.putBoolean("ota_are_updates_available", updateCheckResponse.areOTAUpdatesAvailable());
                if (updateCheckResponse.getOSReleaseInfo() != null) {
                    bundle.putLong("ota_update_base_version", updateCheckResponse.getOSReleaseInfo().base.longValue());
                    bundle.putLong("ota_update_target_version", updateCheckResponse.getOSReleaseInfo().target.longValue());
                    bundle.putString("release_channel_id", updateCheckResponse.getOSReleaseInfo().releaseChannelId);
                    bundle.putString("release_channel_name", updateCheckResponse.getOSReleaseInfo().releaseChannelName);
                    if (updateCheckResponse.getOSReleaseInfo().getReleaseInstallOptions() != null) {
                        bundle.putLong("ota_update_size", updateCheckResponse.getOSReleaseInfo().getReleaseInstallOptions().size.longValue());
                    }
                    otaState = String.format(Locale.US, "OTA available, base version = %d, target version = %d", updateCheckResponse.getOSReleaseInfo().base, updateCheckResponse.getOSReleaseInfo().target);
                } else {
                    otaState = "OTA update check no update available";
                }
            } else {
                bundle.putString("error_message", updateCheckResponse.getErrorMessage());
                otaState = updateCheckResponse.getErrorMessage();
            }
        } else if ("waiting_for_reboot".equals(otaState) || "ota_update_in_progress".equals(otaState)) {
            long buildVersion = this.mPrefs.getBuildVersion("prefs_apply_update_target_version");
            bundle.putLong("ota_update_target_version", buildVersion);
            if ("waiting_for_reboot".equals(otaState)) {
                str = String.format(Locale.US, "Waiting to reboot into OTA, target version = %d", Long.valueOf(buildVersion));
            } else {
                str = String.format(Locale.US, "OTA update in progress, target version = %d", Long.valueOf(buildVersion));
            }
            otaState = str;
        }
        if (otaState != null) {
            bundle.putString("msg", otaState);
        }
        return bundle;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private void checkForOTAvailability(Intent intent) {
        char c;
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        boolean booleanExtra = intent.getBooleanExtra("full_update", false);
        if (resultReceiver == null) {
            BLog.w(TAG, "checkForOTAvailability: result receiver not found. checking anyways");
        }
        String otaState = getOtaState();
        switch (otaState.hashCode()) {
            case -1977205868:
                if (otaState.equals("ota_update_in_progress")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1917034805:
                if (otaState.equals("Check for updates stopped due to no wifi connection")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1665620570:
                if (otaState.equals("state_not_allowed_by_system")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -252387059:
                if (otaState.equals("waiting_for_reboot")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 475616557:
                if (otaState.equals("state_ota_disabled")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1772672651:
                if (otaState.equals("Device is not configured for AB updates")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1969912373:
                if (otaState.equals("state_ready_to_check_for_ota")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            OSUpdateManager.UpdateCheckResponse performUpdateCheck = this.mOSUpdateManager.performUpdateCheck(true, booleanExtra);
            if (resultReceiver == null) {
                return;
            }
            if (performUpdateCheck.isSuccess()) {
                resultReceiver.send(0, createOtaUpdateResponseBundle(performUpdateCheck));
            } else {
                resultReceiver.send(1, createOtaUpdateResponseBundle(performUpdateCheck));
            }
        } else if (resultReceiver != null) {
            resultReceiver.send(1, createOtaUpdateResponseBundle(null));
        }
    }

    private void getCurrentUpdaterStatus(Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("result_receiver");
        if (resultReceiver == null) {
            BLog.e(TAG, "getCurrentUpdaterStatus: result receiver not found. abort");
        } else {
            resultReceiver.send(0, createOtaStateBundle());
        }
    }

    private void cancelOTA() {
        BLog.d(TAG, "cancelOTA: current phase condition is %s", this.mConditionManager.toString());
        this.mOSUpdateManager.cancelOTA();
    }
}
