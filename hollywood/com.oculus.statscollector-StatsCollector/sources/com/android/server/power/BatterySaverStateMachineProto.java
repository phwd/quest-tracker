package com.android.server.power;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class BatterySaverStateMachineProto extends GeneratedMessageLite<BatterySaverStateMachineProto, Builder> implements BatterySaverStateMachineProtoOrBuilder {
    public static final int BATTERY_LEVEL_FIELD_NUMBER = 7;
    public static final int BATTERY_STATUS_SET_FIELD_NUMBER = 4;
    public static final int BOOT_COMPLETED_FIELD_NUMBER = 2;
    private static final BatterySaverStateMachineProto DEFAULT_INSTANCE = new BatterySaverStateMachineProto();
    public static final int ENABLED_FIELD_NUMBER = 1;
    public static final int IS_ADAPTIVE_ENABLED_FIELD_NUMBER = 15;
    public static final int IS_BATTERY_LEVEL_LOW_FIELD_NUMBER = 8;
    public static final int IS_FULL_ENABLED_FIELD_NUMBER = 14;
    public static final int IS_POWERED_FIELD_NUMBER = 6;
    public static final int LAST_ADAPTIVE_BATTERY_SAVER_CHANGED_EXTERNALLY_ELAPSED_FIELD_NUMBER = 17;
    private static volatile Parser<BatterySaverStateMachineProto> PARSER = null;
    public static final int SETTINGS_LOADED_FIELD_NUMBER = 3;
    public static final int SETTING_BATTERY_SAVER_ENABLED_FIELD_NUMBER = 9;
    public static final int SETTING_BATTERY_SAVER_ENABLED_STICKY_FIELD_NUMBER = 10;
    public static final int SETTING_BATTERY_SAVER_STICKY_AUTO_DISABLE_ENABLED_FIELD_NUMBER = 12;
    public static final int SETTING_BATTERY_SAVER_STICKY_AUTO_DISABLE_THRESHOLD_FIELD_NUMBER = 13;
    public static final int SETTING_BATTERY_SAVER_TRIGGER_THRESHOLD_FIELD_NUMBER = 11;
    public static final int SHOULD_ADVERTISE_IS_ENABLED_FIELD_NUMBER = 16;
    public static final int STATE_FIELD_NUMBER = 18;
    private int batteryLevel_ = 0;
    private boolean batteryStatusSet_ = false;
    private int bitField0_;
    private boolean bootCompleted_ = false;
    private boolean enabled_ = false;
    private boolean isAdaptiveEnabled_ = false;
    private boolean isBatteryLevelLow_ = false;
    private boolean isFullEnabled_ = false;
    private boolean isPowered_ = false;
    private long lastAdaptiveBatterySaverChangedExternallyElapsed_ = 0;
    private boolean settingBatterySaverEnabledSticky_ = false;
    private boolean settingBatterySaverEnabled_ = false;
    private boolean settingBatterySaverStickyAutoDisableEnabled_ = false;
    private int settingBatterySaverStickyAutoDisableThreshold_ = 0;
    private int settingBatterySaverTriggerThreshold_ = 0;
    private boolean settingsLoaded_ = false;
    private boolean shouldAdvertiseIsEnabled_ = false;
    private int state_ = 0;

    private BatterySaverStateMachineProto() {
    }

    public enum StateEnum implements Internal.EnumLite {
        STATE_UNKNOWN(0),
        STATE_OFF(1),
        STATE_MANUAL_ON(2),
        STATE_AUTOMATIC_ON(3),
        STATE_OFF_AUTOMATIC_SNOOZED(4),
        STATE_PENDING_STICKY_ON(5);
        
        public static final int STATE_AUTOMATIC_ON_VALUE = 3;
        public static final int STATE_MANUAL_ON_VALUE = 2;
        public static final int STATE_OFF_AUTOMATIC_SNOOZED_VALUE = 4;
        public static final int STATE_OFF_VALUE = 1;
        public static final int STATE_PENDING_STICKY_ON_VALUE = 5;
        public static final int STATE_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap<StateEnum> internalValueMap = new Internal.EnumLiteMap<StateEnum>() {
            /* class com.android.server.power.BatterySaverStateMachineProto.StateEnum.AnonymousClass1 */

            @Override // com.google.protobuf.Internal.EnumLiteMap
            public StateEnum findValueByNumber(int number) {
                return StateEnum.forNumber(number);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static StateEnum valueOf(int value2) {
            return forNumber(value2);
        }

        public static StateEnum forNumber(int value2) {
            if (value2 == 0) {
                return STATE_UNKNOWN;
            }
            if (value2 == 1) {
                return STATE_OFF;
            }
            if (value2 == 2) {
                return STATE_MANUAL_ON;
            }
            if (value2 == 3) {
                return STATE_AUTOMATIC_ON;
            }
            if (value2 == 4) {
                return STATE_OFF_AUTOMATIC_SNOOZED;
            }
            if (value2 != 5) {
                return null;
            }
            return STATE_PENDING_STICKY_ON;
        }

        public static Internal.EnumLiteMap<StateEnum> internalGetValueMap() {
            return internalValueMap;
        }

        private StateEnum(int value2) {
            this.value = value2;
        }
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasEnabled() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getEnabled() {
        return this.enabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEnabled(boolean value) {
        this.bitField0_ |= 1;
        this.enabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEnabled() {
        this.bitField0_ &= -2;
        this.enabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasState() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public StateEnum getState() {
        StateEnum result = StateEnum.forNumber(this.state_);
        return result == null ? StateEnum.STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setState(StateEnum value) {
        if (value != null) {
            this.bitField0_ |= 2;
            this.state_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearState() {
        this.bitField0_ &= -3;
        this.state_ = 0;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasIsFullEnabled() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getIsFullEnabled() {
        return this.isFullEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsFullEnabled(boolean value) {
        this.bitField0_ |= 4;
        this.isFullEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsFullEnabled() {
        this.bitField0_ &= -5;
        this.isFullEnabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasIsAdaptiveEnabled() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getIsAdaptiveEnabled() {
        return this.isAdaptiveEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsAdaptiveEnabled(boolean value) {
        this.bitField0_ |= 8;
        this.isAdaptiveEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsAdaptiveEnabled() {
        this.bitField0_ &= -9;
        this.isAdaptiveEnabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasShouldAdvertiseIsEnabled() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getShouldAdvertiseIsEnabled() {
        return this.shouldAdvertiseIsEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShouldAdvertiseIsEnabled(boolean value) {
        this.bitField0_ |= 16;
        this.shouldAdvertiseIsEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShouldAdvertiseIsEnabled() {
        this.bitField0_ &= -17;
        this.shouldAdvertiseIsEnabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasBootCompleted() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getBootCompleted() {
        return this.bootCompleted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBootCompleted(boolean value) {
        this.bitField0_ |= 32;
        this.bootCompleted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBootCompleted() {
        this.bitField0_ &= -33;
        this.bootCompleted_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingsLoaded() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getSettingsLoaded() {
        return this.settingsLoaded_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsLoaded(boolean value) {
        this.bitField0_ |= 64;
        this.settingsLoaded_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingsLoaded() {
        this.bitField0_ &= -65;
        this.settingsLoaded_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasBatteryStatusSet() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getBatteryStatusSet() {
        return this.batteryStatusSet_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryStatusSet(boolean value) {
        this.bitField0_ |= 128;
        this.batteryStatusSet_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryStatusSet() {
        this.bitField0_ &= -129;
        this.batteryStatusSet_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasIsPowered() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getIsPowered() {
        return this.isPowered_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPowered(boolean value) {
        this.bitField0_ |= 256;
        this.isPowered_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPowered() {
        this.bitField0_ &= -257;
        this.isPowered_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasBatteryLevel() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public int getBatteryLevel() {
        return this.batteryLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevel(int value) {
        this.bitField0_ |= 512;
        this.batteryLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryLevel() {
        this.bitField0_ &= -513;
        this.batteryLevel_ = 0;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasIsBatteryLevelLow() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getIsBatteryLevelLow() {
        return this.isBatteryLevelLow_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBatteryLevelLow(boolean value) {
        this.bitField0_ |= 1024;
        this.isBatteryLevelLow_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBatteryLevelLow() {
        this.bitField0_ &= -1025;
        this.isBatteryLevelLow_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingBatterySaverEnabled() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getSettingBatterySaverEnabled() {
        return this.settingBatterySaverEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBatterySaverEnabled(boolean value) {
        this.bitField0_ |= 2048;
        this.settingBatterySaverEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingBatterySaverEnabled() {
        this.bitField0_ &= -2049;
        this.settingBatterySaverEnabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingBatterySaverEnabledSticky() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getSettingBatterySaverEnabledSticky() {
        return this.settingBatterySaverEnabledSticky_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBatterySaverEnabledSticky(boolean value) {
        this.bitField0_ |= 4096;
        this.settingBatterySaverEnabledSticky_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingBatterySaverEnabledSticky() {
        this.bitField0_ &= -4097;
        this.settingBatterySaverEnabledSticky_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingBatterySaverTriggerThreshold() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public int getSettingBatterySaverTriggerThreshold() {
        return this.settingBatterySaverTriggerThreshold_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBatterySaverTriggerThreshold(int value) {
        this.bitField0_ |= 8192;
        this.settingBatterySaverTriggerThreshold_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingBatterySaverTriggerThreshold() {
        this.bitField0_ &= -8193;
        this.settingBatterySaverTriggerThreshold_ = 0;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingBatterySaverStickyAutoDisableEnabled() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean getSettingBatterySaverStickyAutoDisableEnabled() {
        return this.settingBatterySaverStickyAutoDisableEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBatterySaverStickyAutoDisableEnabled(boolean value) {
        this.bitField0_ |= 16384;
        this.settingBatterySaverStickyAutoDisableEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingBatterySaverStickyAutoDisableEnabled() {
        this.bitField0_ &= -16385;
        this.settingBatterySaverStickyAutoDisableEnabled_ = false;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasSettingBatterySaverStickyAutoDisableThreshold() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public int getSettingBatterySaverStickyAutoDisableThreshold() {
        return this.settingBatterySaverStickyAutoDisableThreshold_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingBatterySaverStickyAutoDisableThreshold(int value) {
        this.bitField0_ |= 32768;
        this.settingBatterySaverStickyAutoDisableThreshold_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingBatterySaverStickyAutoDisableThreshold() {
        this.bitField0_ &= -32769;
        this.settingBatterySaverStickyAutoDisableThreshold_ = 0;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public boolean hasLastAdaptiveBatterySaverChangedExternallyElapsed() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
    public long getLastAdaptiveBatterySaverChangedExternallyElapsed() {
        return this.lastAdaptiveBatterySaverChangedExternallyElapsed_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastAdaptiveBatterySaverChangedExternallyElapsed(long value) {
        this.bitField0_ |= 65536;
        this.lastAdaptiveBatterySaverChangedExternallyElapsed_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastAdaptiveBatterySaverChangedExternallyElapsed() {
        this.bitField0_ &= -65537;
        this.lastAdaptiveBatterySaverChangedExternallyElapsed_ = 0;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.enabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(2, this.bootCompleted_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(3, this.settingsLoaded_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(4, this.batteryStatusSet_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(6, this.isPowered_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeInt32(7, this.batteryLevel_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(8, this.isBatteryLevelLow_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(9, this.settingBatterySaverEnabled_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeBool(10, this.settingBatterySaverEnabledSticky_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeInt32(11, this.settingBatterySaverTriggerThreshold_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeBool(12, this.settingBatterySaverStickyAutoDisableEnabled_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeInt32(13, this.settingBatterySaverStickyAutoDisableThreshold_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(14, this.isFullEnabled_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(15, this.isAdaptiveEnabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(16, this.shouldAdvertiseIsEnabled_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt64(17, this.lastAdaptiveBatterySaverChangedExternallyElapsed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeEnum(18, this.state_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int size = this.memoizedSerializedSize;
        if (size != -1) {
            return size;
        }
        int size2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.enabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(2, this.bootCompleted_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(3, this.settingsLoaded_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(4, this.batteryStatusSet_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isPowered_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeInt32Size(7, this.batteryLevel_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeBoolSize(8, this.isBatteryLevelLow_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(9, this.settingBatterySaverEnabled_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeBoolSize(10, this.settingBatterySaverEnabledSticky_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeInt32Size(11, this.settingBatterySaverTriggerThreshold_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeBoolSize(12, this.settingBatterySaverStickyAutoDisableEnabled_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeInt32Size(13, this.settingBatterySaverStickyAutoDisableThreshold_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(14, this.isFullEnabled_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(15, this.isAdaptiveEnabled_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(16, this.shouldAdvertiseIsEnabled_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt64Size(17, this.lastAdaptiveBatterySaverChangedExternallyElapsed_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeEnumSize(18, this.state_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static BatterySaverStateMachineProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatterySaverStateMachineProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatterySaverStateMachineProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static BatterySaverStateMachineProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static BatterySaverStateMachineProto parseFrom(InputStream input) throws IOException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatterySaverStateMachineProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatterySaverStateMachineProto parseDelimitedFrom(InputStream input) throws IOException {
        return (BatterySaverStateMachineProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static BatterySaverStateMachineProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatterySaverStateMachineProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static BatterySaverStateMachineProto parseFrom(CodedInputStream input) throws IOException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static BatterySaverStateMachineProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (BatterySaverStateMachineProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BatterySaverStateMachineProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatterySaverStateMachineProto, Builder> implements BatterySaverStateMachineProtoOrBuilder {
        private Builder() {
            super(BatterySaverStateMachineProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getEnabled();
        }

        public Builder setEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setEnabled(value);
            return this;
        }

        public Builder clearEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasState() {
            return ((BatterySaverStateMachineProto) this.instance).hasState();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public StateEnum getState() {
            return ((BatterySaverStateMachineProto) this.instance).getState();
        }

        public Builder setState(StateEnum value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setState(value);
            return this;
        }

        public Builder clearState() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearState();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasIsFullEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasIsFullEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getIsFullEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getIsFullEnabled();
        }

        public Builder setIsFullEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setIsFullEnabled(value);
            return this;
        }

        public Builder clearIsFullEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearIsFullEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasIsAdaptiveEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasIsAdaptiveEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getIsAdaptiveEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getIsAdaptiveEnabled();
        }

        public Builder setIsAdaptiveEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setIsAdaptiveEnabled(value);
            return this;
        }

        public Builder clearIsAdaptiveEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearIsAdaptiveEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasShouldAdvertiseIsEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasShouldAdvertiseIsEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getShouldAdvertiseIsEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getShouldAdvertiseIsEnabled();
        }

        public Builder setShouldAdvertiseIsEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setShouldAdvertiseIsEnabled(value);
            return this;
        }

        public Builder clearShouldAdvertiseIsEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearShouldAdvertiseIsEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasBootCompleted() {
            return ((BatterySaverStateMachineProto) this.instance).hasBootCompleted();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getBootCompleted() {
            return ((BatterySaverStateMachineProto) this.instance).getBootCompleted();
        }

        public Builder setBootCompleted(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setBootCompleted(value);
            return this;
        }

        public Builder clearBootCompleted() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearBootCompleted();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingsLoaded() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingsLoaded();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getSettingsLoaded() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingsLoaded();
        }

        public Builder setSettingsLoaded(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingsLoaded(value);
            return this;
        }

        public Builder clearSettingsLoaded() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingsLoaded();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasBatteryStatusSet() {
            return ((BatterySaverStateMachineProto) this.instance).hasBatteryStatusSet();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getBatteryStatusSet() {
            return ((BatterySaverStateMachineProto) this.instance).getBatteryStatusSet();
        }

        public Builder setBatteryStatusSet(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setBatteryStatusSet(value);
            return this;
        }

        public Builder clearBatteryStatusSet() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearBatteryStatusSet();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasIsPowered() {
            return ((BatterySaverStateMachineProto) this.instance).hasIsPowered();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getIsPowered() {
            return ((BatterySaverStateMachineProto) this.instance).getIsPowered();
        }

        public Builder setIsPowered(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setIsPowered(value);
            return this;
        }

        public Builder clearIsPowered() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearIsPowered();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasBatteryLevel() {
            return ((BatterySaverStateMachineProto) this.instance).hasBatteryLevel();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public int getBatteryLevel() {
            return ((BatterySaverStateMachineProto) this.instance).getBatteryLevel();
        }

        public Builder setBatteryLevel(int value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setBatteryLevel(value);
            return this;
        }

        public Builder clearBatteryLevel() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearBatteryLevel();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasIsBatteryLevelLow() {
            return ((BatterySaverStateMachineProto) this.instance).hasIsBatteryLevelLow();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getIsBatteryLevelLow() {
            return ((BatterySaverStateMachineProto) this.instance).getIsBatteryLevelLow();
        }

        public Builder setIsBatteryLevelLow(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setIsBatteryLevelLow(value);
            return this;
        }

        public Builder clearIsBatteryLevelLow() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearIsBatteryLevelLow();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingBatterySaverEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingBatterySaverEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getSettingBatterySaverEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingBatterySaverEnabled();
        }

        public Builder setSettingBatterySaverEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingBatterySaverEnabled(value);
            return this;
        }

        public Builder clearSettingBatterySaverEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingBatterySaverEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingBatterySaverEnabledSticky() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingBatterySaverEnabledSticky();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getSettingBatterySaverEnabledSticky() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingBatterySaverEnabledSticky();
        }

        public Builder setSettingBatterySaverEnabledSticky(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingBatterySaverEnabledSticky(value);
            return this;
        }

        public Builder clearSettingBatterySaverEnabledSticky() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingBatterySaverEnabledSticky();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingBatterySaverTriggerThreshold() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingBatterySaverTriggerThreshold();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public int getSettingBatterySaverTriggerThreshold() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingBatterySaverTriggerThreshold();
        }

        public Builder setSettingBatterySaverTriggerThreshold(int value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingBatterySaverTriggerThreshold(value);
            return this;
        }

        public Builder clearSettingBatterySaverTriggerThreshold() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingBatterySaverTriggerThreshold();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingBatterySaverStickyAutoDisableEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingBatterySaverStickyAutoDisableEnabled();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean getSettingBatterySaverStickyAutoDisableEnabled() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingBatterySaverStickyAutoDisableEnabled();
        }

        public Builder setSettingBatterySaverStickyAutoDisableEnabled(boolean value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingBatterySaverStickyAutoDisableEnabled(value);
            return this;
        }

        public Builder clearSettingBatterySaverStickyAutoDisableEnabled() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingBatterySaverStickyAutoDisableEnabled();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasSettingBatterySaverStickyAutoDisableThreshold() {
            return ((BatterySaverStateMachineProto) this.instance).hasSettingBatterySaverStickyAutoDisableThreshold();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public int getSettingBatterySaverStickyAutoDisableThreshold() {
            return ((BatterySaverStateMachineProto) this.instance).getSettingBatterySaverStickyAutoDisableThreshold();
        }

        public Builder setSettingBatterySaverStickyAutoDisableThreshold(int value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setSettingBatterySaverStickyAutoDisableThreshold(value);
            return this;
        }

        public Builder clearSettingBatterySaverStickyAutoDisableThreshold() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearSettingBatterySaverStickyAutoDisableThreshold();
            return this;
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public boolean hasLastAdaptiveBatterySaverChangedExternallyElapsed() {
            return ((BatterySaverStateMachineProto) this.instance).hasLastAdaptiveBatterySaverChangedExternallyElapsed();
        }

        @Override // com.android.server.power.BatterySaverStateMachineProtoOrBuilder
        public long getLastAdaptiveBatterySaverChangedExternallyElapsed() {
            return ((BatterySaverStateMachineProto) this.instance).getLastAdaptiveBatterySaverChangedExternallyElapsed();
        }

        public Builder setLastAdaptiveBatterySaverChangedExternallyElapsed(long value) {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).setLastAdaptiveBatterySaverChangedExternallyElapsed(value);
            return this;
        }

        public Builder clearLastAdaptiveBatterySaverChangedExternallyElapsed() {
            copyOnWrite();
            ((BatterySaverStateMachineProto) this.instance).clearLastAdaptiveBatterySaverChangedExternallyElapsed();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new BatterySaverStateMachineProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                BatterySaverStateMachineProto other = (BatterySaverStateMachineProto) arg1;
                this.enabled_ = visitor.visitBoolean(hasEnabled(), this.enabled_, other.hasEnabled(), other.enabled_);
                this.state_ = visitor.visitInt(hasState(), this.state_, other.hasState(), other.state_);
                this.isFullEnabled_ = visitor.visitBoolean(hasIsFullEnabled(), this.isFullEnabled_, other.hasIsFullEnabled(), other.isFullEnabled_);
                this.isAdaptiveEnabled_ = visitor.visitBoolean(hasIsAdaptiveEnabled(), this.isAdaptiveEnabled_, other.hasIsAdaptiveEnabled(), other.isAdaptiveEnabled_);
                this.shouldAdvertiseIsEnabled_ = visitor.visitBoolean(hasShouldAdvertiseIsEnabled(), this.shouldAdvertiseIsEnabled_, other.hasShouldAdvertiseIsEnabled(), other.shouldAdvertiseIsEnabled_);
                this.bootCompleted_ = visitor.visitBoolean(hasBootCompleted(), this.bootCompleted_, other.hasBootCompleted(), other.bootCompleted_);
                this.settingsLoaded_ = visitor.visitBoolean(hasSettingsLoaded(), this.settingsLoaded_, other.hasSettingsLoaded(), other.settingsLoaded_);
                this.batteryStatusSet_ = visitor.visitBoolean(hasBatteryStatusSet(), this.batteryStatusSet_, other.hasBatteryStatusSet(), other.batteryStatusSet_);
                this.isPowered_ = visitor.visitBoolean(hasIsPowered(), this.isPowered_, other.hasIsPowered(), other.isPowered_);
                this.batteryLevel_ = visitor.visitInt(hasBatteryLevel(), this.batteryLevel_, other.hasBatteryLevel(), other.batteryLevel_);
                this.isBatteryLevelLow_ = visitor.visitBoolean(hasIsBatteryLevelLow(), this.isBatteryLevelLow_, other.hasIsBatteryLevelLow(), other.isBatteryLevelLow_);
                this.settingBatterySaverEnabled_ = visitor.visitBoolean(hasSettingBatterySaverEnabled(), this.settingBatterySaverEnabled_, other.hasSettingBatterySaverEnabled(), other.settingBatterySaverEnabled_);
                this.settingBatterySaverEnabledSticky_ = visitor.visitBoolean(hasSettingBatterySaverEnabledSticky(), this.settingBatterySaverEnabledSticky_, other.hasSettingBatterySaverEnabledSticky(), other.settingBatterySaverEnabledSticky_);
                this.settingBatterySaverTriggerThreshold_ = visitor.visitInt(hasSettingBatterySaverTriggerThreshold(), this.settingBatterySaverTriggerThreshold_, other.hasSettingBatterySaverTriggerThreshold(), other.settingBatterySaverTriggerThreshold_);
                this.settingBatterySaverStickyAutoDisableEnabled_ = visitor.visitBoolean(hasSettingBatterySaverStickyAutoDisableEnabled(), this.settingBatterySaverStickyAutoDisableEnabled_, other.hasSettingBatterySaverStickyAutoDisableEnabled(), other.settingBatterySaverStickyAutoDisableEnabled_);
                this.settingBatterySaverStickyAutoDisableThreshold_ = visitor.visitInt(hasSettingBatterySaverStickyAutoDisableThreshold(), this.settingBatterySaverStickyAutoDisableThreshold_, other.hasSettingBatterySaverStickyAutoDisableThreshold(), other.settingBatterySaverStickyAutoDisableThreshold_);
                this.lastAdaptiveBatterySaverChangedExternallyElapsed_ = visitor.visitLong(hasLastAdaptiveBatterySaverChangedExternallyElapsed(), this.lastAdaptiveBatterySaverChangedExternallyElapsed_, other.hasLastAdaptiveBatterySaverChangedExternallyElapsed(), other.lastAdaptiveBatterySaverChangedExternallyElapsed_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.enabled_ = input.readBool();
                                break;
                            case 16:
                                this.bitField0_ |= 32;
                                this.bootCompleted_ = input.readBool();
                                break;
                            case 24:
                                this.bitField0_ |= 64;
                                this.settingsLoaded_ = input.readBool();
                                break;
                            case 32:
                                this.bitField0_ |= 128;
                                this.batteryStatusSet_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 256;
                                this.isPowered_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 512;
                                this.batteryLevel_ = input.readInt32();
                                break;
                            case 64:
                                this.bitField0_ |= 1024;
                                this.isBatteryLevelLow_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 2048;
                                this.settingBatterySaverEnabled_ = input.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 4096;
                                this.settingBatterySaverEnabledSticky_ = input.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 8192;
                                this.settingBatterySaverTriggerThreshold_ = input.readInt32();
                                break;
                            case 96:
                                this.bitField0_ |= 16384;
                                this.settingBatterySaverStickyAutoDisableEnabled_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 32768;
                                this.settingBatterySaverStickyAutoDisableThreshold_ = input.readInt32();
                                break;
                            case 112:
                                this.bitField0_ |= 4;
                                this.isFullEnabled_ = input.readBool();
                                break;
                            case 120:
                                this.bitField0_ |= 8;
                                this.isAdaptiveEnabled_ = input.readBool();
                                break;
                            case 128:
                                this.bitField0_ |= 16;
                                this.shouldAdvertiseIsEnabled_ = input.readBool();
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.lastAdaptiveBatterySaverChangedExternallyElapsed_ = input.readInt64();
                                break;
                            case 144:
                                int rawValue = input.readEnum();
                                if (StateEnum.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 2;
                                    this.state_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(18, rawValue);
                                    break;
                                }
                            default:
                                if (parseUnknownField(tag, input)) {
                                    break;
                                } else {
                                    done = true;
                                    break;
                                }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (BatterySaverStateMachineProto.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static BatterySaverStateMachineProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatterySaverStateMachineProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
