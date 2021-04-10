package android.providers.settings;

import android.providers.settings.SystemSettingsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface SystemSettingsProtoOrBuilder extends MessageLiteOrBuilder {
    SettingProto getAdvancedSettings();

    SystemSettingsProto.Alarm getAlarm();

    SystemSettingsProto.Bluetooth getBluetooth();

    SettingProto getDateFormat();

    SystemSettingsProto.DevOptions getDeveloperOptions();

    SettingProto getDisplayColorMode();

    SystemSettingsProto.DtmfTone getDtmfTone();

    SettingProto getEggMode();

    SettingProto getEndButtonBehavior();

    SettingProto getFontScale();

    SystemSettingsProto.HapticFeedback getHapticFeedback();

    SettingProto getHearingAid();

    SettingsOperationProto getHistoricalOperations(int i);

    int getHistoricalOperationsCount();

    List<SettingsOperationProto> getHistoricalOperationsList();

    SettingProto getLockToAppEnabled();

    SystemSettingsProto.Lockscreen getLockscreen();

    SettingProto getMediaButtonReceiver();

    SystemSettingsProto.Notification getNotification();

    SettingProto getPointerSpeed();

    SystemSettingsProto.Ringtone getRingtone();

    SystemSettingsProto.Rotation getRotation();

    SystemSettingsProto.Screen getScreen();

    SettingProto getSetupWizardHasRun();

    SettingProto getShowBatteryPercent();

    SettingProto getShowGtalkServiceStatus();

    SystemSettingsProto.Sip getSip();

    SettingProto getSoundEffectsEnabled();

    SettingProto getSystemLocales();

    SystemSettingsProto.Text getText();

    SettingProto getTime1224();

    SettingProto getTtyMode();

    SystemSettingsProto.Vibrate getVibrate();

    SystemSettingsProto.Volume getVolume();

    SettingProto getWhenToMakeWifiCalls();

    boolean hasAdvancedSettings();

    boolean hasAlarm();

    boolean hasBluetooth();

    boolean hasDateFormat();

    boolean hasDeveloperOptions();

    boolean hasDisplayColorMode();

    boolean hasDtmfTone();

    boolean hasEggMode();

    boolean hasEndButtonBehavior();

    boolean hasFontScale();

    boolean hasHapticFeedback();

    boolean hasHearingAid();

    boolean hasLockToAppEnabled();

    boolean hasLockscreen();

    boolean hasMediaButtonReceiver();

    boolean hasNotification();

    boolean hasPointerSpeed();

    boolean hasRingtone();

    boolean hasRotation();

    boolean hasScreen();

    boolean hasSetupWizardHasRun();

    boolean hasShowBatteryPercent();

    boolean hasShowGtalkServiceStatus();

    boolean hasSip();

    boolean hasSoundEffectsEnabled();

    boolean hasSystemLocales();

    boolean hasText();

    boolean hasTime1224();

    boolean hasTtyMode();

    boolean hasVibrate();

    boolean hasVolume();

    boolean hasWhenToMakeWifiCalls();
}
