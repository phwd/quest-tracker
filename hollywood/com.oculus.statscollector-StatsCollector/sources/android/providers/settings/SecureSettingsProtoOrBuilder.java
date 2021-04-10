package android.providers.settings;

import android.providers.settings.SecureSettingsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SecureSettingsProtoOrBuilder extends MessageLiteOrBuilder {
    SecureSettingsProto.Accessibility getAccessibility();

    SettingProto getAllowedGeolocationOrigins();

    SecureSettingsProto.AlwaysOnVpn getAlwaysOnVpn();

    SettingProto getAndroidId();

    SettingProto getAnrShowBackground();

    SecureSettingsProto.Assist getAssist();

    SecureSettingsProto.Autofill getAutofill();

    SecureSettingsProto.AutomaticStorageManager getAutomaticStorageManager();

    SecureSettingsProto.Backup getBackup();

    SettingProto getBluetoothOnWhileDriving();

    SecureSettingsProto.Camera getCamera();

    SettingProto getCarrierAppsHandled();

    SettingProto getCmasAdditionalBroadcastPkg();

    SettingProto getCompletedCategories(int i);

    int getCompletedCategoriesCount();

    List<SettingProto> getCompletedCategoriesList();

    SettingProto getConnectivityReleasePendingIntentDelayMs();

    SettingProto getDevicePaired();

    SettingProto getDialerDefaultApplication();

    SettingProto getDisplayDensityForced();

    SettingProto getDoubleTapToWake();

    SecureSettingsProto.Doze getDoze();

    SettingProto getEmergencyAssistanceApplication();

    SettingProto getEnhancedVoicePrivacyEnabled();

    SecureSettingsProto.Gesture getGesture();

    SettingsOperationProto getHistoricalOperations(int i);

    int getHistoricalOperationsCount();

    List<SettingsOperationProto> getHistoricalOperationsList();

    SettingProto getImmersiveModeConfirmations();

    SecureSettingsProto.Incall getIncall();

    SecureSettingsProto.InputMethods getInputMethods();

    SettingProto getInstallNonMarketApps();

    SettingProto getInstantAppsEnabled();

    SettingProto getKeyguardSliceUri();

    SettingProto getLastSetupShown();

    SecureSettingsProto.Launcher getLauncher();

    SecureSettingsProto.Location getLocation();

    SecureSettingsProto.LocationAccessCheck getLocationAccessCheck();

    SecureSettingsProto.LockScreen getLockScreen();

    SettingProto getLockToAppExitLocked();

    SettingProto getLockdownInPowerMenu();

    SettingProto getLongPressTimeout();

    SecureSettingsProto.ManagedProfile getManagedProfile();

    SecureSettingsProto.Mount getMount();

    SettingProto getMultiPressTimeout();

    SettingProto getNavigationMode();

    SecureSettingsProto.NfcPayment getNfcPayment();

    SecureSettingsProto.NightDisplay getNightDisplay();

    SecureSettingsProto.Notification getNotification();

    SecureSettingsProto.PackageVerifier getPackageVerifier();

    SecureSettingsProto.ParentalControl getParentalControl();

    SecureSettingsProto.PrintService getPrintService();

    SecureSettingsProto.QuickSettings getQs();

    SecureSettingsProto.Rotation getRotation();

    SettingProto getRttCallingMode();

    SecureSettingsProto.Screensaver getScreensaver();

    SecureSettingsProto.Search getSearch();

    SettingProto getSettingsClassname();

    SettingProto getShowFirstCrashDialogDevOption();

    SettingProto getSkipFirstUseHints();

    SettingProto getSleepTimeout();

    SettingProto getSmsDefaultApplication();

    SecureSettingsProto.Sounds getSounds();

    SecureSettingsProto.SpellChecker getSpellChecker();

    SettingProto getSyncParentSounds();

    SettingProto getSystemNavigationKeysEnabled();

    SettingProto getThemeCustomizationOverlayPackages();

    SettingProto getTrustAgentsInitialized();

    SecureSettingsProto.Tts getTts();

    SecureSettingsProto.Tty getTty();

    SecureSettingsProto.Tv getTv();

    SettingProto getUiNightMode();

    SettingProto getUnknownSourcesDefaultReversed();

    SettingProto getUsbAudioAutomaticRoutingDisabled();

    SettingProto getUserSetupComplete();

    SecureSettingsProto.Voice getVoice();

    SecureSettingsProto.Volume getVolume();

    SecureSettingsProto.Vr getVr();

    SettingProto getWakeGestureEnabled();

    SecureSettingsProto.Zen getZen();

    boolean hasAccessibility();

    boolean hasAllowedGeolocationOrigins();

    boolean hasAlwaysOnVpn();

    boolean hasAndroidId();

    boolean hasAnrShowBackground();

    boolean hasAssist();

    boolean hasAutofill();

    boolean hasAutomaticStorageManager();

    boolean hasBackup();

    boolean hasBluetoothOnWhileDriving();

    boolean hasCamera();

    boolean hasCarrierAppsHandled();

    boolean hasCmasAdditionalBroadcastPkg();

    boolean hasConnectivityReleasePendingIntentDelayMs();

    boolean hasDevicePaired();

    boolean hasDialerDefaultApplication();

    boolean hasDisplayDensityForced();

    boolean hasDoubleTapToWake();

    boolean hasDoze();

    boolean hasEmergencyAssistanceApplication();

    boolean hasEnhancedVoicePrivacyEnabled();

    boolean hasGesture();

    boolean hasImmersiveModeConfirmations();

    boolean hasIncall();

    boolean hasInputMethods();

    boolean hasInstallNonMarketApps();

    boolean hasInstantAppsEnabled();

    boolean hasKeyguardSliceUri();

    boolean hasLastSetupShown();

    boolean hasLauncher();

    boolean hasLocation();

    boolean hasLocationAccessCheck();

    boolean hasLockScreen();

    boolean hasLockToAppExitLocked();

    boolean hasLockdownInPowerMenu();

    boolean hasLongPressTimeout();

    boolean hasManagedProfile();

    boolean hasMount();

    boolean hasMultiPressTimeout();

    boolean hasNavigationMode();

    boolean hasNfcPayment();

    boolean hasNightDisplay();

    boolean hasNotification();

    boolean hasPackageVerifier();

    boolean hasParentalControl();

    boolean hasPrintService();

    boolean hasQs();

    boolean hasRotation();

    boolean hasRttCallingMode();

    boolean hasScreensaver();

    boolean hasSearch();

    boolean hasSettingsClassname();

    boolean hasShowFirstCrashDialogDevOption();

    boolean hasSkipFirstUseHints();

    boolean hasSleepTimeout();

    boolean hasSmsDefaultApplication();

    boolean hasSounds();

    boolean hasSpellChecker();

    boolean hasSyncParentSounds();

    boolean hasSystemNavigationKeysEnabled();

    boolean hasThemeCustomizationOverlayPackages();

    boolean hasTrustAgentsInitialized();

    boolean hasTts();

    boolean hasTty();

    boolean hasTv();

    boolean hasUiNightMode();

    boolean hasUnknownSourcesDefaultReversed();

    boolean hasUsbAudioAutomaticRoutingDisabled();

    boolean hasUserSetupComplete();

    boolean hasVoice();

    boolean hasVolume();

    boolean hasVr();

    boolean hasWakeGestureEnabled();

    boolean hasZen();
}
