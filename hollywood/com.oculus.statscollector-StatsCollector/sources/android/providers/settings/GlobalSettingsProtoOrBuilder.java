package android.providers.settings;

import android.providers.settings.GlobalSettingsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface GlobalSettingsProtoOrBuilder extends MessageLiteOrBuilder {
    SettingProto getActivityManagerConstants();

    SettingProto getAdbEnabled();

    SettingProto getAddUsersWhenLocked();

    GlobalSettingsProto.AirplaneMode getAirplaneMode();

    SettingProto getAlarmManagerConstants();

    SettingProto getAllowUserSwitchingWhenSystemUserLocked();

    SettingProto getAlwaysFinishActivities();

    SettingProto getAlwaysOnDisplayConstants();

    SettingProto getAnimatorDurationScale();

    GlobalSettingsProto.Anomaly getAnomaly();

    GlobalSettingsProto.ApnDb getApnDb();

    GlobalSettingsProto.App getApp();

    SettingProto getAppOpsConstants();

    SettingProto getApplyRampingRinger();

    SettingProto getAssistedGpsEnabled();

    SettingProto getAudioSafeVolumeState();

    GlobalSettingsProto.Auto getAuto();

    GlobalSettingsProto.Autofill getAutofill();

    GlobalSettingsProto.Backup getBackup();

    GlobalSettingsProto.Battery getBattery();

    GlobalSettingsProto.BleScan getBleScan();

    GlobalSettingsProto.Bluetooth getBluetooth();

    SettingProto getBootCount();

    SettingProto getBugreportInPowerMenu();

    SettingProto getCallAutoRetry();

    GlobalSettingsProto.CaptivePortal getCaptivePortal();

    GlobalSettingsProto.Carrier getCarrier();

    GlobalSettingsProto.Cdma getCdma();

    SettingProto getCellOn();

    GlobalSettingsProto.CertPin getCertPin();

    SettingProto getChainedBatteryAttributionEnabled();

    SettingProto getCompatibilityMode();

    GlobalSettingsProto.Connectivity getConnectivity();

    SettingProto getContactMetadataSyncEnabled();

    SettingProto getContactsDatabaseWalEnabled();

    GlobalSettingsProto.Data getData();

    GlobalSettingsProto.Database getDatabase();

    GlobalSettingsProto.Debug getDebug();

    GlobalSettingsProto.Default getDefault();

    GlobalSettingsProto.Development getDevelopment();

    GlobalSettingsProto.Device getDevice();

    SettingProto getDiskFreeChangeReportingThreshold();

    GlobalSettingsProto.Display getDisplay();

    GlobalSettingsProto.DnsResolver getDnsResolver();

    SettingProto getDockAudioMediaEnabled();

    GlobalSettingsProto.Download getDownload();

    GlobalSettingsProto.Dropbox getDropbox();

    GlobalSettingsProto.DynamicPowerSavings getDynamicPowerSavings();

    GlobalSettingsProto.Emergency getEmergency();

    GlobalSettingsProto.Enable getEnable();

    SettingProto getEncodedSurroundOutput();

    SettingProto getEnhanced4GModeEnabled();

    SettingProto getErrorLogcatLines(int i);

    int getErrorLogcatLinesCount();

    List<SettingProto> getErrorLogcatLinesList();

    GlobalSettingsProto.Euicc getEuicc();

    SettingProto getFancyImeAnimations();

    SettingProto getForceAllowOnExternal();

    SettingProto getFpsDivisor();

    SettingProto getFstrimMandatoryInterval();

    GlobalSettingsProto.GlobalHttpProxy getGlobalHttpProxy();

    SettingProto getGprsRegisterCheckPeriodMs();

    GlobalSettingsProto.Gpu getGpu();

    GlobalSettingsProto.Hdmi getHdmi();

    SettingProto getHeadsUpNotificationsEnabled();

    SettingProto getHiddenApiBlacklistExemptions();

    SettingsOperationProto getHistoricalOperations(int i);

    int getHistoricalOperationsCount();

    List<SettingsOperationProto> getHistoricalOperationsList();

    GlobalSettingsProto.InetCondition getInetCondition();

    GlobalSettingsProto.InstantApp getInstantApp();

    GlobalSettingsProto.IntentFirewall getIntentFirewall();

    SettingProto getJobSchedulerConstants();

    SettingProto getJobSchedulerQuotaControllerConstants();

    SettingProto getJobSchedulerTimeControllerConstants();

    SettingProto getKeepProfileInBackground();

    GlobalSettingsProto.LangId getLangId();

    GlobalSettingsProto.Location getLocation();

    GlobalSettingsProto.LowPowerMode getLowPowerMode();

    SettingProto getLteServiceForced();

    SettingProto getMdcInitialMaxRetry();

    GlobalSettingsProto.Mhl getMhl();

    GlobalSettingsProto.MobileData getMobileData();

    SettingProto getModeRinger();

    GlobalSettingsProto.MultiSim getMultiSim();

    SettingProto getNativeFlagsHealthCheckEnabled();

    GlobalSettingsProto.Netstats getNetstats();

    GlobalSettingsProto.Network getNetwork();

    SettingProto getNewContactAggregator();

    SettingProto getNightDisplayForcedAutoModeAvailable();

    GlobalSettingsProto.NitzUpdate getNitzUpdate();

    GlobalSettingsProto.Notification getNotification();

    SettingProto getNsdOn();

    GlobalSettingsProto.Ntp getNtp();

    SettingProto getOtaDisableAutomaticUpdate();

    SettingProto getOverlayDisplayDevices();

    SettingProto getOverrideSettingsProviderRestoreAnyVersion();

    SettingProto getPacChangeDelay();

    GlobalSettingsProto.PackageVerifier getPackageVerifier();

    GlobalSettingsProto.PdpWatchdog getPdpWatchdog();

    SettingProto getPolicyControl();

    SettingProto getPowerManagerConstants();

    GlobalSettingsProto.PrepaidSetup getPrepaidSetup();

    GlobalSettingsProto.Private getPrivate();

    SettingProto getProvisioningApnAlarmDelayInMs();

    SettingProto getReadExternalStorageEnforcedDefault();

    SettingProto getRequirePasswordToDecrypt();

    SettingProto getSafeBootDisallowed();

    GlobalSettingsProto.Selinux getSelinux();

    SettingProto getSendActionAppError();

    SettingProto getSetInstallLocation();

    SettingProto getShortcutManagerConstants();

    SettingProto getShowFirstCrashDialog();

    SettingProto getShowHiddenLauncherIconAppsEnabled();

    SettingProto getShowMuteInCrashDialog();

    SettingProto getShowNewAppInstalledNotificationEnabled();

    SettingProto getShowRestartInCrashDialog();

    GlobalSettingsProto.SmartSelection getSmartSelection();

    GlobalSettingsProto.Sms getSms();

    GlobalSettingsProto.SoundTrigger getSoundTrigger();

    GlobalSettingsProto.Sounds getSounds();

    SettingProto getSpeedLabelCacheEvictionAgeMs();

    SettingProto getSqliteCompatibilityWalFlags();

    SettingProto getStayOnWhilePluggedIn();

    GlobalSettingsProto.Storage getStorage();

    GlobalSettingsProto.Sync getSync();

    GlobalSettingsProto.Sys getSys();

    SettingProto getTcpDefaultInitRwnd();

    GlobalSettingsProto.TemperatureWarning getTemperatureWarning();

    GlobalSettingsProto.Tether getTether();

    SettingProto getTextClassifierActionModelParams();

    SettingProto getTextClassifierConstants();

    SettingProto getTheaterModeOn();

    SettingProto getTimeOnlyModeConstants();

    SettingProto getTransitionAnimationScale();

    GlobalSettingsProto.Tzinfo getTzinfo();

    SettingProto getUnusedStaticSharedLibMinCachePeriodMs();

    SettingProto getUsbMassStorageEnabled();

    SettingProto getUseGoogleMail();

    SettingProto getUseOpenWifiPackage();

    GlobalSettingsProto.UserAbsentSmallBattery getUserAbsentSmallBattery();

    SettingProto getVtImsEnabled();

    SettingProto getWaitForDebugger();

    GlobalSettingsProto.Webview getWebview();

    GlobalSettingsProto.Wfc getWfc();

    GlobalSettingsProto.Wifi getWifi();

    SettingProto getWimaxNetworksAvailableNotificationOn();

    SettingProto getWindowAnimationScale();

    SettingProto getWtfIsFatal();

    GlobalSettingsProto.Zen getZen();

    SettingProto getZramEnabled();

    boolean hasActivityManagerConstants();

    boolean hasAdbEnabled();

    boolean hasAddUsersWhenLocked();

    boolean hasAirplaneMode();

    boolean hasAlarmManagerConstants();

    boolean hasAllowUserSwitchingWhenSystemUserLocked();

    boolean hasAlwaysFinishActivities();

    boolean hasAlwaysOnDisplayConstants();

    boolean hasAnimatorDurationScale();

    boolean hasAnomaly();

    boolean hasApnDb();

    boolean hasApp();

    boolean hasAppOpsConstants();

    boolean hasApplyRampingRinger();

    boolean hasAssistedGpsEnabled();

    boolean hasAudioSafeVolumeState();

    boolean hasAuto();

    boolean hasAutofill();

    boolean hasBackup();

    boolean hasBattery();

    boolean hasBleScan();

    boolean hasBluetooth();

    boolean hasBootCount();

    boolean hasBugreportInPowerMenu();

    boolean hasCallAutoRetry();

    boolean hasCaptivePortal();

    boolean hasCarrier();

    boolean hasCdma();

    boolean hasCellOn();

    boolean hasCertPin();

    boolean hasChainedBatteryAttributionEnabled();

    boolean hasCompatibilityMode();

    boolean hasConnectivity();

    boolean hasContactMetadataSyncEnabled();

    boolean hasContactsDatabaseWalEnabled();

    boolean hasData();

    boolean hasDatabase();

    boolean hasDebug();

    boolean hasDefault();

    boolean hasDevelopment();

    boolean hasDevice();

    boolean hasDiskFreeChangeReportingThreshold();

    boolean hasDisplay();

    boolean hasDnsResolver();

    boolean hasDockAudioMediaEnabled();

    boolean hasDownload();

    boolean hasDropbox();

    boolean hasDynamicPowerSavings();

    boolean hasEmergency();

    boolean hasEnable();

    boolean hasEncodedSurroundOutput();

    boolean hasEnhanced4GModeEnabled();

    boolean hasEuicc();

    boolean hasFancyImeAnimations();

    boolean hasForceAllowOnExternal();

    boolean hasFpsDivisor();

    boolean hasFstrimMandatoryInterval();

    boolean hasGlobalHttpProxy();

    boolean hasGprsRegisterCheckPeriodMs();

    boolean hasGpu();

    boolean hasHdmi();

    boolean hasHeadsUpNotificationsEnabled();

    boolean hasHiddenApiBlacklistExemptions();

    boolean hasInetCondition();

    boolean hasInstantApp();

    boolean hasIntentFirewall();

    boolean hasJobSchedulerConstants();

    boolean hasJobSchedulerQuotaControllerConstants();

    boolean hasJobSchedulerTimeControllerConstants();

    boolean hasKeepProfileInBackground();

    boolean hasLangId();

    boolean hasLocation();

    boolean hasLowPowerMode();

    boolean hasLteServiceForced();

    boolean hasMdcInitialMaxRetry();

    boolean hasMhl();

    boolean hasMobileData();

    boolean hasModeRinger();

    boolean hasMultiSim();

    boolean hasNativeFlagsHealthCheckEnabled();

    boolean hasNetstats();

    boolean hasNetwork();

    boolean hasNewContactAggregator();

    boolean hasNightDisplayForcedAutoModeAvailable();

    boolean hasNitzUpdate();

    boolean hasNotification();

    boolean hasNsdOn();

    boolean hasNtp();

    boolean hasOtaDisableAutomaticUpdate();

    boolean hasOverlayDisplayDevices();

    boolean hasOverrideSettingsProviderRestoreAnyVersion();

    boolean hasPacChangeDelay();

    boolean hasPackageVerifier();

    boolean hasPdpWatchdog();

    boolean hasPolicyControl();

    boolean hasPowerManagerConstants();

    boolean hasPrepaidSetup();

    boolean hasPrivate();

    boolean hasProvisioningApnAlarmDelayInMs();

    boolean hasReadExternalStorageEnforcedDefault();

    boolean hasRequirePasswordToDecrypt();

    boolean hasSafeBootDisallowed();

    boolean hasSelinux();

    boolean hasSendActionAppError();

    boolean hasSetInstallLocation();

    boolean hasShortcutManagerConstants();

    boolean hasShowFirstCrashDialog();

    boolean hasShowHiddenLauncherIconAppsEnabled();

    boolean hasShowMuteInCrashDialog();

    boolean hasShowNewAppInstalledNotificationEnabled();

    boolean hasShowRestartInCrashDialog();

    boolean hasSmartSelection();

    boolean hasSms();

    boolean hasSoundTrigger();

    boolean hasSounds();

    boolean hasSpeedLabelCacheEvictionAgeMs();

    boolean hasSqliteCompatibilityWalFlags();

    boolean hasStayOnWhilePluggedIn();

    boolean hasStorage();

    boolean hasSync();

    boolean hasSys();

    boolean hasTcpDefaultInitRwnd();

    boolean hasTemperatureWarning();

    boolean hasTether();

    boolean hasTextClassifierActionModelParams();

    boolean hasTextClassifierConstants();

    boolean hasTheaterModeOn();

    boolean hasTimeOnlyModeConstants();

    boolean hasTransitionAnimationScale();

    boolean hasTzinfo();

    boolean hasUnusedStaticSharedLibMinCachePeriodMs();

    boolean hasUsbMassStorageEnabled();

    boolean hasUseGoogleMail();

    boolean hasUseOpenWifiPackage();

    boolean hasUserAbsentSmallBattery();

    boolean hasVtImsEnabled();

    boolean hasWaitForDebugger();

    boolean hasWebview();

    boolean hasWfc();

    boolean hasWifi();

    boolean hasWimaxNetworksAvailableNotificationOn();

    boolean hasWindowAnimationScale();

    boolean hasWtfIsFatal();

    boolean hasZen();

    boolean hasZramEnabled();
}
