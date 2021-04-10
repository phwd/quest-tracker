package com.android.server.power;

import com.android.server.power.BatterySaverStateMachineProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BatterySaverStateMachineProtoOrBuilder extends MessageLiteOrBuilder {
    int getBatteryLevel();

    boolean getBatteryStatusSet();

    boolean getBootCompleted();

    boolean getEnabled();

    boolean getIsAdaptiveEnabled();

    boolean getIsBatteryLevelLow();

    boolean getIsFullEnabled();

    boolean getIsPowered();

    long getLastAdaptiveBatterySaverChangedExternallyElapsed();

    boolean getSettingBatterySaverEnabled();

    boolean getSettingBatterySaverEnabledSticky();

    boolean getSettingBatterySaverStickyAutoDisableEnabled();

    int getSettingBatterySaverStickyAutoDisableThreshold();

    int getSettingBatterySaverTriggerThreshold();

    boolean getSettingsLoaded();

    boolean getShouldAdvertiseIsEnabled();

    BatterySaverStateMachineProto.StateEnum getState();

    boolean hasBatteryLevel();

    boolean hasBatteryStatusSet();

    boolean hasBootCompleted();

    boolean hasEnabled();

    boolean hasIsAdaptiveEnabled();

    boolean hasIsBatteryLevelLow();

    boolean hasIsFullEnabled();

    boolean hasIsPowered();

    boolean hasLastAdaptiveBatterySaverChangedExternallyElapsed();

    boolean hasSettingBatterySaverEnabled();

    boolean hasSettingBatterySaverEnabledSticky();

    boolean hasSettingBatterySaverStickyAutoDisableEnabled();

    boolean hasSettingBatterySaverStickyAutoDisableThreshold();

    boolean hasSettingBatterySaverTriggerThreshold();

    boolean hasSettingsLoaded();

    boolean hasShouldAdvertiseIsEnabled();

    boolean hasState();
}
