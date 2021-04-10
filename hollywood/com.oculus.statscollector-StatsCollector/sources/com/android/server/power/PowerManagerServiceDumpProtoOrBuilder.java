package com.android.server.power;

import android.content.IntentProto;
import android.os.BatteryPluggedStateEnum;
import android.os.LooperProto;
import android.os.PowerManagerInternalProto;
import com.android.server.power.PowerManagerServiceDumpProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PowerManagerServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    PowerManagerServiceDumpProto.ActiveWakeLocksProto getActiveWakeLocks();

    boolean getAreUidsChanged();

    boolean getAreUidsChanging();

    int getBatteryLevel();

    int getBatteryLevelWhenDreamStarted();

    BatterySaverStateMachineProto getBatterySaverStateMachine();

    PowerManagerServiceDumpProto.ConstantsProto getConstants();

    int getDeviceIdleTempWhitelist(int i);

    int getDeviceIdleTempWhitelistCount();

    List<Integer> getDeviceIdleTempWhitelistList();

    int getDeviceIdleWhitelist(int i);

    int getDeviceIdleWhitelistCount();

    List<Integer> getDeviceIdleWhitelistList();

    int getDirty();

    IntentProto.DockState getDockState();

    boolean getIsBatteryLevelLow();

    boolean getIsBootCompleted();

    boolean getIsDeviceIdleMode();

    boolean getIsDisplayReady();

    boolean getIsHalAutoInteractiveModeEnabled();

    boolean getIsHalAutoSuspendModeEnabled();

    boolean getIsHoldingDisplaySuspendBlocker();

    boolean getIsHoldingWakeLockSuspendBlocker();

    boolean getIsLightDeviceIdleMode();

    boolean getIsPowered();

    boolean getIsProximityPositive();

    boolean getIsRequestWaitForNegativeProximity();

    boolean getIsSandmanScheduled();

    boolean getIsSandmanSummoned();

    boolean getIsScreenBrightnessBoostInProgress();

    boolean getIsStayOn();

    boolean getIsSystemReady();

    boolean getIsWakefulnessChanging();

    long getLastInteractivePowerHintTimeMs();

    long getLastScreenBrightnessBoostTimeMs();

    long getLastSleepTimeMs();

    long getLastUserActivityTimeMs();

    long getLastUserActivityTimeNoChangeLightsMs();

    long getLastWakeTimeMs();

    LooperProto getLooper();

    long getNotifyLongDispatchedMs();

    long getNotifyLongNextCheckMs();

    long getNotifyLongScheduledMs();

    BatteryPluggedStateEnum getPlugType();

    int getScreenDimDurationMs();

    int getScreenOffTimeoutMs();

    PowerServiceSettingsAndConfigurationDumpProto getSettingsAndConfiguration();

    int getSleepTimeoutMs();

    SuspendBlockerProto getSuspendBlockers(int i);

    int getSuspendBlockersCount();

    List<SuspendBlockerProto> getSuspendBlockersList();

    PowerManagerServiceDumpProto.UidStateProto getUidStates(int i);

    int getUidStatesCount();

    List<PowerManagerServiceDumpProto.UidStateProto> getUidStatesList();

    PowerManagerServiceDumpProto.UserActivityProto getUserActivity();

    WakeLockProto getWakeLocks(int i);

    int getWakeLocksCount();

    List<WakeLockProto> getWakeLocksList();

    PowerManagerInternalProto.Wakefulness getWakefulness();

    WirelessChargerDetectorProto getWirelessChargerDetector();

    boolean hasActiveWakeLocks();

    boolean hasAreUidsChanged();

    boolean hasAreUidsChanging();

    boolean hasBatteryLevel();

    boolean hasBatteryLevelWhenDreamStarted();

    boolean hasBatterySaverStateMachine();

    boolean hasConstants();

    boolean hasDirty();

    boolean hasDockState();

    boolean hasIsBatteryLevelLow();

    boolean hasIsBootCompleted();

    boolean hasIsDeviceIdleMode();

    boolean hasIsDisplayReady();

    boolean hasIsHalAutoInteractiveModeEnabled();

    boolean hasIsHalAutoSuspendModeEnabled();

    boolean hasIsHoldingDisplaySuspendBlocker();

    boolean hasIsHoldingWakeLockSuspendBlocker();

    boolean hasIsLightDeviceIdleMode();

    boolean hasIsPowered();

    boolean hasIsProximityPositive();

    boolean hasIsRequestWaitForNegativeProximity();

    boolean hasIsSandmanScheduled();

    boolean hasIsSandmanSummoned();

    boolean hasIsScreenBrightnessBoostInProgress();

    boolean hasIsStayOn();

    boolean hasIsSystemReady();

    boolean hasIsWakefulnessChanging();

    boolean hasLastInteractivePowerHintTimeMs();

    boolean hasLastScreenBrightnessBoostTimeMs();

    boolean hasLastSleepTimeMs();

    boolean hasLastUserActivityTimeMs();

    boolean hasLastUserActivityTimeNoChangeLightsMs();

    boolean hasLastWakeTimeMs();

    boolean hasLooper();

    boolean hasNotifyLongDispatchedMs();

    boolean hasNotifyLongNextCheckMs();

    boolean hasNotifyLongScheduledMs();

    boolean hasPlugType();

    boolean hasScreenDimDurationMs();

    boolean hasScreenOffTimeoutMs();

    boolean hasSettingsAndConfiguration();

    boolean hasSleepTimeoutMs();

    boolean hasUserActivity();

    boolean hasWakefulness();

    boolean hasWirelessChargerDetector();
}
