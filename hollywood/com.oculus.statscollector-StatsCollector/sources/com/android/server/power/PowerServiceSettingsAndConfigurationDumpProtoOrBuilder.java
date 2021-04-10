package com.android.server.power;

import android.providers.settings.SettingsProto;
import android.view.DisplayStateEnum;
import com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PowerServiceSettingsAndConfigurationDumpProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getAreDreamsActivateOnDockSetting();

    boolean getAreDreamsActivateOnSleepSetting();

    boolean getAreDreamsActivatedOnDockByDefaultConfig();

    boolean getAreDreamsActivatedOnSleepByDefaultConfig();

    boolean getAreDreamsEnabledByDefaultConfig();

    boolean getAreDreamsEnabledOnBatteryConfig();

    boolean getAreDreamsEnabledSetting();

    boolean getAreDreamsSupportedConfig();

    DisplayStateEnum getDozeScreenStateOverrideFromDreamManager();

    float getDozedScreenBrightnessOverrideFromDreamManager();

    boolean getDrawWakeLockOverrideFromSidekick();

    int getDreamsBatteryLevelDrainCutoffConfig();

    int getDreamsBatteryLevelMinimumWhenNotPoweredConfig();

    int getDreamsBatteryLevelMinimumWhenPoweredConfig();

    boolean getIsDecoupleHalAutoSuspendModeFromDisplayConfig();

    boolean getIsDecoupleHalInteractiveModeFromDisplayConfig();

    boolean getIsDoubleTapWakeEnabled();

    boolean getIsDozeAfterScreenOffConfig();

    boolean getIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked();

    boolean getIsSuspendWhenScreenOffDueToProximityConfig();

    boolean getIsTheaterModeEnabled();

    boolean getIsUserInactiveOverrideFromWindowManager();

    boolean getIsVrModeEnabled();

    boolean getIsWakeUpWhenPluggedOrUnpluggedConfig();

    boolean getIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig();

    int getMaximumScreenDimDurationConfigMs();

    float getMaximumScreenDimRatioConfig();

    int getMaximumScreenOffTimeoutFromDeviceAdminMs();

    int getMinimumScreenOffTimeoutConfigMs();

    SettingsProto.ScreenBrightnessMode getScreenBrightnessModeSetting();

    int getScreenBrightnessOverrideFromWindowManager();

    PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProto getScreenBrightnessSettingLimits();

    int getScreenOffTimeoutSettingMs();

    int getSleepTimeoutSettingMs();

    PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProto getStayOnWhilePluggedIn();

    long getUserActivityTimeoutOverrideFromWindowManagerMs();

    boolean hasAreDreamsActivateOnDockSetting();

    boolean hasAreDreamsActivateOnSleepSetting();

    boolean hasAreDreamsActivatedOnDockByDefaultConfig();

    boolean hasAreDreamsActivatedOnSleepByDefaultConfig();

    boolean hasAreDreamsEnabledByDefaultConfig();

    boolean hasAreDreamsEnabledOnBatteryConfig();

    boolean hasAreDreamsEnabledSetting();

    boolean hasAreDreamsSupportedConfig();

    boolean hasDozeScreenStateOverrideFromDreamManager();

    boolean hasDozedScreenBrightnessOverrideFromDreamManager();

    boolean hasDrawWakeLockOverrideFromSidekick();

    boolean hasDreamsBatteryLevelDrainCutoffConfig();

    boolean hasDreamsBatteryLevelMinimumWhenNotPoweredConfig();

    boolean hasDreamsBatteryLevelMinimumWhenPoweredConfig();

    boolean hasIsDecoupleHalAutoSuspendModeFromDisplayConfig();

    boolean hasIsDecoupleHalInteractiveModeFromDisplayConfig();

    boolean hasIsDoubleTapWakeEnabled();

    boolean hasIsDozeAfterScreenOffConfig();

    boolean hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked();

    boolean hasIsSuspendWhenScreenOffDueToProximityConfig();

    boolean hasIsTheaterModeEnabled();

    boolean hasIsUserInactiveOverrideFromWindowManager();

    boolean hasIsVrModeEnabled();

    boolean hasIsWakeUpWhenPluggedOrUnpluggedConfig();

    boolean hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig();

    boolean hasMaximumScreenDimDurationConfigMs();

    boolean hasMaximumScreenDimRatioConfig();

    boolean hasMaximumScreenOffTimeoutFromDeviceAdminMs();

    boolean hasMinimumScreenOffTimeoutConfigMs();

    boolean hasScreenBrightnessModeSetting();

    boolean hasScreenBrightnessOverrideFromWindowManager();

    boolean hasScreenBrightnessSettingLimits();

    boolean hasScreenOffTimeoutSettingMs();

    boolean hasSleepTimeoutSettingMs();

    boolean hasStayOnWhilePluggedIn();

    boolean hasUserActivityTimeoutOverrideFromWindowManagerMs();
}
