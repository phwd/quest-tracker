package com.android.server.power;

import android.providers.settings.SettingsProto;
import android.telephony.DataConnectionPowerStateEnum;
import android.view.DisplayStateEnum;
import com.android.os.AtomsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class PowerServiceSettingsAndConfigurationDumpProto extends GeneratedMessageLite<PowerServiceSettingsAndConfigurationDumpProto, Builder> implements PowerServiceSettingsAndConfigurationDumpProtoOrBuilder {
    public static final int ARE_DREAMS_ACTIVATED_ON_DOCK_BY_DEFAULT_CONFIG_FIELD_NUMBER = 10;
    public static final int ARE_DREAMS_ACTIVATED_ON_SLEEP_BY_DEFAULT_CONFIG_FIELD_NUMBER = 9;
    public static final int ARE_DREAMS_ACTIVATE_ON_DOCK_SETTING_FIELD_NUMBER = 17;
    public static final int ARE_DREAMS_ACTIVATE_ON_SLEEP_SETTING_FIELD_NUMBER = 16;
    public static final int ARE_DREAMS_ENABLED_BY_DEFAULT_CONFIG_FIELD_NUMBER = 8;
    public static final int ARE_DREAMS_ENABLED_ON_BATTERY_CONFIG_FIELD_NUMBER = 11;
    public static final int ARE_DREAMS_ENABLED_SETTING_FIELD_NUMBER = 15;
    public static final int ARE_DREAMS_SUPPORTED_CONFIG_FIELD_NUMBER = 7;
    private static final PowerServiceSettingsAndConfigurationDumpProto DEFAULT_INSTANCE = new PowerServiceSettingsAndConfigurationDumpProto();
    public static final int DOZED_SCREEN_BRIGHTNESS_OVERRIDE_FROM_DREAM_MANAGER_FIELD_NUMBER = 32;
    public static final int DOZE_SCREEN_STATE_OVERRIDE_FROM_DREAM_MANAGER_FIELD_NUMBER = 31;
    public static final int DRAW_WAKE_LOCK_OVERRIDE_FROM_SIDEKICK_FIELD_NUMBER = 36;
    public static final int DREAMS_BATTERY_LEVEL_DRAIN_CUTOFF_CONFIG_FIELD_NUMBER = 14;
    public static final int DREAMS_BATTERY_LEVEL_MINIMUM_WHEN_NOT_POWERED_CONFIG_FIELD_NUMBER = 13;
    public static final int DREAMS_BATTERY_LEVEL_MINIMUM_WHEN_POWERED_CONFIG_FIELD_NUMBER = 12;
    public static final int IS_DECOUPLE_HAL_AUTO_SUSPEND_MODE_FROM_DISPLAY_CONFIG_FIELD_NUMBER = 1;
    public static final int IS_DECOUPLE_HAL_INTERACTIVE_MODE_FROM_DISPLAY_CONFIG_FIELD_NUMBER = 2;
    public static final int IS_DOUBLE_TAP_WAKE_ENABLED_FIELD_NUMBER = 34;
    public static final int IS_DOZE_AFTER_SCREEN_OFF_CONFIG_FIELD_NUMBER = 18;
    public static final int IS_MAXIMUM_SCREEN_OFF_TIMEOUT_FROM_DEVICE_ADMIN_ENFORCED_LOCKED_FIELD_NUMBER = 25;
    public static final int IS_SUSPEND_WHEN_SCREEN_OFF_DUE_TO_PROXIMITY_CONFIG_FIELD_NUMBER = 6;
    public static final int IS_THEATER_MODE_ENABLED_FIELD_NUMBER = 5;
    public static final int IS_USER_INACTIVE_OVERRIDE_FROM_WINDOW_MANAGER_FIELD_NUMBER = 30;
    public static final int IS_VR_MODE_ENABLED_FIELD_NUMBER = 35;
    public static final int IS_WAKE_UP_WHEN_PLUGGED_OR_UNPLUGGED_CONFIG_FIELD_NUMBER = 3;
    public static final int IS_WAKE_UP_WHEN_PLUGGED_OR_UNPLUGGED_IN_THEATER_MODE_CONFIG_FIELD_NUMBER = 4;
    public static final int MAXIMUM_SCREEN_DIM_DURATION_CONFIG_MS_FIELD_NUMBER = 20;
    public static final int MAXIMUM_SCREEN_DIM_RATIO_CONFIG_FIELD_NUMBER = 21;
    public static final int MAXIMUM_SCREEN_OFF_TIMEOUT_FROM_DEVICE_ADMIN_MS_FIELD_NUMBER = 24;
    public static final int MINIMUM_SCREEN_OFF_TIMEOUT_CONFIG_MS_FIELD_NUMBER = 19;
    private static volatile Parser<PowerServiceSettingsAndConfigurationDumpProto> PARSER = null;
    public static final int SCREEN_BRIGHTNESS_MODE_SETTING_FIELD_NUMBER = 27;
    public static final int SCREEN_BRIGHTNESS_OVERRIDE_FROM_WINDOW_MANAGER_FIELD_NUMBER = 28;
    public static final int SCREEN_BRIGHTNESS_SETTING_LIMITS_FIELD_NUMBER = 33;
    public static final int SCREEN_OFF_TIMEOUT_SETTING_MS_FIELD_NUMBER = 22;
    public static final int SLEEP_TIMEOUT_SETTING_MS_FIELD_NUMBER = 23;
    public static final int STAY_ON_WHILE_PLUGGED_IN_FIELD_NUMBER = 26;
    public static final int USER_ACTIVITY_TIMEOUT_OVERRIDE_FROM_WINDOW_MANAGER_MS_FIELD_NUMBER = 29;
    private boolean areDreamsActivateOnDockSetting_ = false;
    private boolean areDreamsActivateOnSleepSetting_ = false;
    private boolean areDreamsActivatedOnDockByDefaultConfig_ = false;
    private boolean areDreamsActivatedOnSleepByDefaultConfig_ = false;
    private boolean areDreamsEnabledByDefaultConfig_ = false;
    private boolean areDreamsEnabledOnBatteryConfig_ = false;
    private boolean areDreamsEnabledSetting_ = false;
    private boolean areDreamsSupportedConfig_ = false;
    private int bitField0_;
    private int bitField1_;
    private int dozeScreenStateOverrideFromDreamManager_ = 0;
    private float dozedScreenBrightnessOverrideFromDreamManager_ = 0.0f;
    private boolean drawWakeLockOverrideFromSidekick_ = false;
    private int dreamsBatteryLevelDrainCutoffConfig_ = 0;
    private int dreamsBatteryLevelMinimumWhenNotPoweredConfig_ = 0;
    private int dreamsBatteryLevelMinimumWhenPoweredConfig_ = 0;
    private boolean isDecoupleHalAutoSuspendModeFromDisplayConfig_ = false;
    private boolean isDecoupleHalInteractiveModeFromDisplayConfig_ = false;
    private boolean isDoubleTapWakeEnabled_ = false;
    private boolean isDozeAfterScreenOffConfig_ = false;
    private boolean isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_ = false;
    private boolean isSuspendWhenScreenOffDueToProximityConfig_ = false;
    private boolean isTheaterModeEnabled_ = false;
    private boolean isUserInactiveOverrideFromWindowManager_ = false;
    private boolean isVrModeEnabled_ = false;
    private boolean isWakeUpWhenPluggedOrUnpluggedConfig_ = false;
    private boolean isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_ = false;
    private int maximumScreenDimDurationConfigMs_ = 0;
    private float maximumScreenDimRatioConfig_ = 0.0f;
    private int maximumScreenOffTimeoutFromDeviceAdminMs_ = 0;
    private int minimumScreenOffTimeoutConfigMs_ = 0;
    private int screenBrightnessModeSetting_ = 0;
    private int screenBrightnessOverrideFromWindowManager_ = 0;
    private ScreenBrightnessSettingLimitsProto screenBrightnessSettingLimits_;
    private int screenOffTimeoutSettingMs_ = 0;
    private int sleepTimeoutSettingMs_ = 0;
    private StayOnWhilePluggedInProto stayOnWhilePluggedIn_;
    private long userActivityTimeoutOverrideFromWindowManagerMs_ = 0;

    public interface ScreenBrightnessSettingLimitsProtoOrBuilder extends MessageLiteOrBuilder {
        int getSettingDefault();

        int getSettingMaximum();

        int getSettingMinimum();

        boolean hasSettingDefault();

        boolean hasSettingMaximum();

        boolean hasSettingMinimum();
    }

    public interface StayOnWhilePluggedInProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsStayOnWhilePluggedInAc();

        boolean getIsStayOnWhilePluggedInUsb();

        boolean getIsStayOnWhilePluggedInWireless();

        boolean hasIsStayOnWhilePluggedInAc();

        boolean hasIsStayOnWhilePluggedInUsb();

        boolean hasIsStayOnWhilePluggedInWireless();
    }

    private PowerServiceSettingsAndConfigurationDumpProto() {
    }

    public static final class StayOnWhilePluggedInProto extends GeneratedMessageLite<StayOnWhilePluggedInProto, Builder> implements StayOnWhilePluggedInProtoOrBuilder {
        private static final StayOnWhilePluggedInProto DEFAULT_INSTANCE = new StayOnWhilePluggedInProto();
        public static final int IS_STAY_ON_WHILE_PLUGGED_IN_AC_FIELD_NUMBER = 1;
        public static final int IS_STAY_ON_WHILE_PLUGGED_IN_USB_FIELD_NUMBER = 2;
        public static final int IS_STAY_ON_WHILE_PLUGGED_IN_WIRELESS_FIELD_NUMBER = 3;
        private static volatile Parser<StayOnWhilePluggedInProto> PARSER;
        private int bitField0_;
        private boolean isStayOnWhilePluggedInAc_ = false;
        private boolean isStayOnWhilePluggedInUsb_ = false;
        private boolean isStayOnWhilePluggedInWireless_ = false;

        private StayOnWhilePluggedInProto() {
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean hasIsStayOnWhilePluggedInAc() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean getIsStayOnWhilePluggedInAc() {
            return this.isStayOnWhilePluggedInAc_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStayOnWhilePluggedInAc(boolean value) {
            this.bitField0_ |= 1;
            this.isStayOnWhilePluggedInAc_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStayOnWhilePluggedInAc() {
            this.bitField0_ &= -2;
            this.isStayOnWhilePluggedInAc_ = false;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean hasIsStayOnWhilePluggedInUsb() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean getIsStayOnWhilePluggedInUsb() {
            return this.isStayOnWhilePluggedInUsb_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStayOnWhilePluggedInUsb(boolean value) {
            this.bitField0_ |= 2;
            this.isStayOnWhilePluggedInUsb_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStayOnWhilePluggedInUsb() {
            this.bitField0_ &= -3;
            this.isStayOnWhilePluggedInUsb_ = false;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean hasIsStayOnWhilePluggedInWireless() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
        public boolean getIsStayOnWhilePluggedInWireless() {
            return this.isStayOnWhilePluggedInWireless_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStayOnWhilePluggedInWireless(boolean value) {
            this.bitField0_ |= 4;
            this.isStayOnWhilePluggedInWireless_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStayOnWhilePluggedInWireless() {
            this.bitField0_ &= -5;
            this.isStayOnWhilePluggedInWireless_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isStayOnWhilePluggedInAc_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isStayOnWhilePluggedInUsb_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isStayOnWhilePluggedInWireless_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isStayOnWhilePluggedInAc_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isStayOnWhilePluggedInUsb_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isStayOnWhilePluggedInWireless_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static StayOnWhilePluggedInProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StayOnWhilePluggedInProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StayOnWhilePluggedInProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static StayOnWhilePluggedInProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static StayOnWhilePluggedInProto parseFrom(InputStream input) throws IOException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StayOnWhilePluggedInProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StayOnWhilePluggedInProto parseDelimitedFrom(InputStream input) throws IOException {
            return (StayOnWhilePluggedInProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static StayOnWhilePluggedInProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StayOnWhilePluggedInProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static StayOnWhilePluggedInProto parseFrom(CodedInputStream input) throws IOException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static StayOnWhilePluggedInProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (StayOnWhilePluggedInProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(StayOnWhilePluggedInProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StayOnWhilePluggedInProto, Builder> implements StayOnWhilePluggedInProtoOrBuilder {
            private Builder() {
                super(StayOnWhilePluggedInProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean hasIsStayOnWhilePluggedInAc() {
                return ((StayOnWhilePluggedInProto) this.instance).hasIsStayOnWhilePluggedInAc();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean getIsStayOnWhilePluggedInAc() {
                return ((StayOnWhilePluggedInProto) this.instance).getIsStayOnWhilePluggedInAc();
            }

            public Builder setIsStayOnWhilePluggedInAc(boolean value) {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).setIsStayOnWhilePluggedInAc(value);
                return this;
            }

            public Builder clearIsStayOnWhilePluggedInAc() {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).clearIsStayOnWhilePluggedInAc();
                return this;
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean hasIsStayOnWhilePluggedInUsb() {
                return ((StayOnWhilePluggedInProto) this.instance).hasIsStayOnWhilePluggedInUsb();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean getIsStayOnWhilePluggedInUsb() {
                return ((StayOnWhilePluggedInProto) this.instance).getIsStayOnWhilePluggedInUsb();
            }

            public Builder setIsStayOnWhilePluggedInUsb(boolean value) {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).setIsStayOnWhilePluggedInUsb(value);
                return this;
            }

            public Builder clearIsStayOnWhilePluggedInUsb() {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).clearIsStayOnWhilePluggedInUsb();
                return this;
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean hasIsStayOnWhilePluggedInWireless() {
                return ((StayOnWhilePluggedInProto) this.instance).hasIsStayOnWhilePluggedInWireless();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.StayOnWhilePluggedInProtoOrBuilder
            public boolean getIsStayOnWhilePluggedInWireless() {
                return ((StayOnWhilePluggedInProto) this.instance).getIsStayOnWhilePluggedInWireless();
            }

            public Builder setIsStayOnWhilePluggedInWireless(boolean value) {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).setIsStayOnWhilePluggedInWireless(value);
                return this;
            }

            public Builder clearIsStayOnWhilePluggedInWireless() {
                copyOnWrite();
                ((StayOnWhilePluggedInProto) this.instance).clearIsStayOnWhilePluggedInWireless();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new StayOnWhilePluggedInProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    StayOnWhilePluggedInProto other = (StayOnWhilePluggedInProto) arg1;
                    this.isStayOnWhilePluggedInAc_ = visitor.visitBoolean(hasIsStayOnWhilePluggedInAc(), this.isStayOnWhilePluggedInAc_, other.hasIsStayOnWhilePluggedInAc(), other.isStayOnWhilePluggedInAc_);
                    this.isStayOnWhilePluggedInUsb_ = visitor.visitBoolean(hasIsStayOnWhilePluggedInUsb(), this.isStayOnWhilePluggedInUsb_, other.hasIsStayOnWhilePluggedInUsb(), other.isStayOnWhilePluggedInUsb_);
                    this.isStayOnWhilePluggedInWireless_ = visitor.visitBoolean(hasIsStayOnWhilePluggedInWireless(), this.isStayOnWhilePluggedInWireless_, other.hasIsStayOnWhilePluggedInWireless(), other.isStayOnWhilePluggedInWireless_);
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
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.isStayOnWhilePluggedInAc_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isStayOnWhilePluggedInUsb_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isStayOnWhilePluggedInWireless_ = input.readBool();
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
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
                        synchronized (StayOnWhilePluggedInProto.class) {
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

        public static StayOnWhilePluggedInProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<StayOnWhilePluggedInProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ScreenBrightnessSettingLimitsProto extends GeneratedMessageLite<ScreenBrightnessSettingLimitsProto, Builder> implements ScreenBrightnessSettingLimitsProtoOrBuilder {
        private static final ScreenBrightnessSettingLimitsProto DEFAULT_INSTANCE = new ScreenBrightnessSettingLimitsProto();
        private static volatile Parser<ScreenBrightnessSettingLimitsProto> PARSER = null;
        public static final int SETTING_DEFAULT_FIELD_NUMBER = 3;
        public static final int SETTING_MAXIMUM_FIELD_NUMBER = 2;
        public static final int SETTING_MINIMUM_FIELD_NUMBER = 1;
        private int bitField0_;
        private int settingDefault_ = 0;
        private int settingMaximum_ = 0;
        private int settingMinimum_ = 0;

        private ScreenBrightnessSettingLimitsProto() {
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public boolean hasSettingMinimum() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public int getSettingMinimum() {
            return this.settingMinimum_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSettingMinimum(int value) {
            this.bitField0_ |= 1;
            this.settingMinimum_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSettingMinimum() {
            this.bitField0_ &= -2;
            this.settingMinimum_ = 0;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public boolean hasSettingMaximum() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public int getSettingMaximum() {
            return this.settingMaximum_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSettingMaximum(int value) {
            this.bitField0_ |= 2;
            this.settingMaximum_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSettingMaximum() {
            this.bitField0_ &= -3;
            this.settingMaximum_ = 0;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public boolean hasSettingDefault() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
        public int getSettingDefault() {
            return this.settingDefault_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSettingDefault(int value) {
            this.bitField0_ |= 4;
            this.settingDefault_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSettingDefault() {
            this.bitField0_ &= -5;
            this.settingDefault_ = 0;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.settingMinimum_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.settingMaximum_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.settingDefault_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.settingMinimum_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.settingMaximum_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.settingDefault_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(InputStream input) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenBrightnessSettingLimitsProto parseDelimitedFrom(InputStream input) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightnessSettingLimitsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(CodedInputStream input) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ScreenBrightnessSettingLimitsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ScreenBrightnessSettingLimitsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ScreenBrightnessSettingLimitsProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ScreenBrightnessSettingLimitsProto, Builder> implements ScreenBrightnessSettingLimitsProtoOrBuilder {
            private Builder() {
                super(ScreenBrightnessSettingLimitsProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public boolean hasSettingMinimum() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).hasSettingMinimum();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public int getSettingMinimum() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).getSettingMinimum();
            }

            public Builder setSettingMinimum(int value) {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).setSettingMinimum(value);
                return this;
            }

            public Builder clearSettingMinimum() {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).clearSettingMinimum();
                return this;
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public boolean hasSettingMaximum() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).hasSettingMaximum();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public int getSettingMaximum() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).getSettingMaximum();
            }

            public Builder setSettingMaximum(int value) {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).setSettingMaximum(value);
                return this;
            }

            public Builder clearSettingMaximum() {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).clearSettingMaximum();
                return this;
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public boolean hasSettingDefault() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).hasSettingDefault();
            }

            @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto.ScreenBrightnessSettingLimitsProtoOrBuilder
            public int getSettingDefault() {
                return ((ScreenBrightnessSettingLimitsProto) this.instance).getSettingDefault();
            }

            public Builder setSettingDefault(int value) {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).setSettingDefault(value);
                return this;
            }

            public Builder clearSettingDefault() {
                copyOnWrite();
                ((ScreenBrightnessSettingLimitsProto) this.instance).clearSettingDefault();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ScreenBrightnessSettingLimitsProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ScreenBrightnessSettingLimitsProto other = (ScreenBrightnessSettingLimitsProto) arg1;
                    this.settingMinimum_ = visitor.visitInt(hasSettingMinimum(), this.settingMinimum_, other.hasSettingMinimum(), other.settingMinimum_);
                    this.settingMaximum_ = visitor.visitInt(hasSettingMaximum(), this.settingMaximum_, other.hasSettingMaximum(), other.settingMaximum_);
                    this.settingDefault_ = visitor.visitInt(hasSettingDefault(), this.settingDefault_, other.hasSettingDefault(), other.settingDefault_);
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
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.settingMinimum_ = input.readInt32();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.settingMaximum_ = input.readInt32();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.settingDefault_ = input.readInt32();
                            } else if (!parseUnknownField(tag, input)) {
                                done = true;
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
                        synchronized (ScreenBrightnessSettingLimitsProto.class) {
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

        public static ScreenBrightnessSettingLimitsProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScreenBrightnessSettingLimitsProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
        return this.isDecoupleHalAutoSuspendModeFromDisplayConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDecoupleHalAutoSuspendModeFromDisplayConfig(boolean value) {
        this.bitField0_ |= 1;
        this.isDecoupleHalAutoSuspendModeFromDisplayConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
        this.bitField0_ &= -2;
        this.isDecoupleHalAutoSuspendModeFromDisplayConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsDecoupleHalInteractiveModeFromDisplayConfig() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsDecoupleHalInteractiveModeFromDisplayConfig() {
        return this.isDecoupleHalInteractiveModeFromDisplayConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDecoupleHalInteractiveModeFromDisplayConfig(boolean value) {
        this.bitField0_ |= 2;
        this.isDecoupleHalInteractiveModeFromDisplayConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDecoupleHalInteractiveModeFromDisplayConfig() {
        this.bitField0_ &= -3;
        this.isDecoupleHalInteractiveModeFromDisplayConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsWakeUpWhenPluggedOrUnpluggedConfig() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsWakeUpWhenPluggedOrUnpluggedConfig() {
        return this.isWakeUpWhenPluggedOrUnpluggedConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsWakeUpWhenPluggedOrUnpluggedConfig(boolean value) {
        this.bitField0_ |= 4;
        this.isWakeUpWhenPluggedOrUnpluggedConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsWakeUpWhenPluggedOrUnpluggedConfig() {
        this.bitField0_ &= -5;
        this.isWakeUpWhenPluggedOrUnpluggedConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
        return this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig(boolean value) {
        this.bitField0_ |= 8;
        this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
        this.bitField0_ &= -9;
        this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsTheaterModeEnabled() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsTheaterModeEnabled() {
        return this.isTheaterModeEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsTheaterModeEnabled(boolean value) {
        this.bitField0_ |= 16;
        this.isTheaterModeEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsTheaterModeEnabled() {
        this.bitField0_ &= -17;
        this.isTheaterModeEnabled_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsSuspendWhenScreenOffDueToProximityConfig() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsSuspendWhenScreenOffDueToProximityConfig() {
        return this.isSuspendWhenScreenOffDueToProximityConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSuspendWhenScreenOffDueToProximityConfig(boolean value) {
        this.bitField0_ |= 32;
        this.isSuspendWhenScreenOffDueToProximityConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSuspendWhenScreenOffDueToProximityConfig() {
        this.bitField0_ &= -33;
        this.isSuspendWhenScreenOffDueToProximityConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsSupportedConfig() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsSupportedConfig() {
        return this.areDreamsSupportedConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsSupportedConfig(boolean value) {
        this.bitField0_ |= 64;
        this.areDreamsSupportedConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsSupportedConfig() {
        this.bitField0_ &= -65;
        this.areDreamsSupportedConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsEnabledByDefaultConfig() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsEnabledByDefaultConfig() {
        return this.areDreamsEnabledByDefaultConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsEnabledByDefaultConfig(boolean value) {
        this.bitField0_ |= 128;
        this.areDreamsEnabledByDefaultConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsEnabledByDefaultConfig() {
        this.bitField0_ &= -129;
        this.areDreamsEnabledByDefaultConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsActivatedOnSleepByDefaultConfig() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsActivatedOnSleepByDefaultConfig() {
        return this.areDreamsActivatedOnSleepByDefaultConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsActivatedOnSleepByDefaultConfig(boolean value) {
        this.bitField0_ |= 256;
        this.areDreamsActivatedOnSleepByDefaultConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsActivatedOnSleepByDefaultConfig() {
        this.bitField0_ &= -257;
        this.areDreamsActivatedOnSleepByDefaultConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsActivatedOnDockByDefaultConfig() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsActivatedOnDockByDefaultConfig() {
        return this.areDreamsActivatedOnDockByDefaultConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsActivatedOnDockByDefaultConfig(boolean value) {
        this.bitField0_ |= 512;
        this.areDreamsActivatedOnDockByDefaultConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsActivatedOnDockByDefaultConfig() {
        this.bitField0_ &= -513;
        this.areDreamsActivatedOnDockByDefaultConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsEnabledOnBatteryConfig() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsEnabledOnBatteryConfig() {
        return this.areDreamsEnabledOnBatteryConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsEnabledOnBatteryConfig(boolean value) {
        this.bitField0_ |= 1024;
        this.areDreamsEnabledOnBatteryConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsEnabledOnBatteryConfig() {
        this.bitField0_ &= -1025;
        this.areDreamsEnabledOnBatteryConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDreamsBatteryLevelMinimumWhenPoweredConfig() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getDreamsBatteryLevelMinimumWhenPoweredConfig() {
        return this.dreamsBatteryLevelMinimumWhenPoweredConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDreamsBatteryLevelMinimumWhenPoweredConfig(int value) {
        this.bitField0_ |= 2048;
        this.dreamsBatteryLevelMinimumWhenPoweredConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDreamsBatteryLevelMinimumWhenPoweredConfig() {
        this.bitField0_ &= -2049;
        this.dreamsBatteryLevelMinimumWhenPoweredConfig_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
        return this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDreamsBatteryLevelMinimumWhenNotPoweredConfig(int value) {
        this.bitField0_ |= 4096;
        this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
        this.bitField0_ &= -4097;
        this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDreamsBatteryLevelDrainCutoffConfig() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getDreamsBatteryLevelDrainCutoffConfig() {
        return this.dreamsBatteryLevelDrainCutoffConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDreamsBatteryLevelDrainCutoffConfig(int value) {
        this.bitField0_ |= 8192;
        this.dreamsBatteryLevelDrainCutoffConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDreamsBatteryLevelDrainCutoffConfig() {
        this.bitField0_ &= -8193;
        this.dreamsBatteryLevelDrainCutoffConfig_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsEnabledSetting() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsEnabledSetting() {
        return this.areDreamsEnabledSetting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsEnabledSetting(boolean value) {
        this.bitField0_ |= 16384;
        this.areDreamsEnabledSetting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsEnabledSetting() {
        this.bitField0_ &= -16385;
        this.areDreamsEnabledSetting_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsActivateOnSleepSetting() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsActivateOnSleepSetting() {
        return this.areDreamsActivateOnSleepSetting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsActivateOnSleepSetting(boolean value) {
        this.bitField0_ |= 32768;
        this.areDreamsActivateOnSleepSetting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsActivateOnSleepSetting() {
        this.bitField0_ &= -32769;
        this.areDreamsActivateOnSleepSetting_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasAreDreamsActivateOnDockSetting() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getAreDreamsActivateOnDockSetting() {
        return this.areDreamsActivateOnDockSetting_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreDreamsActivateOnDockSetting(boolean value) {
        this.bitField0_ |= 65536;
        this.areDreamsActivateOnDockSetting_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreDreamsActivateOnDockSetting() {
        this.bitField0_ &= -65537;
        this.areDreamsActivateOnDockSetting_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsDozeAfterScreenOffConfig() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsDozeAfterScreenOffConfig() {
        return this.isDozeAfterScreenOffConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDozeAfterScreenOffConfig(boolean value) {
        this.bitField0_ |= 131072;
        this.isDozeAfterScreenOffConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDozeAfterScreenOffConfig() {
        this.bitField0_ &= -131073;
        this.isDozeAfterScreenOffConfig_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasMinimumScreenOffTimeoutConfigMs() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getMinimumScreenOffTimeoutConfigMs() {
        return this.minimumScreenOffTimeoutConfigMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMinimumScreenOffTimeoutConfigMs(int value) {
        this.bitField0_ |= 262144;
        this.minimumScreenOffTimeoutConfigMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMinimumScreenOffTimeoutConfigMs() {
        this.bitField0_ &= -262145;
        this.minimumScreenOffTimeoutConfigMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasMaximumScreenDimDurationConfigMs() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getMaximumScreenDimDurationConfigMs() {
        return this.maximumScreenDimDurationConfigMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaximumScreenDimDurationConfigMs(int value) {
        this.bitField0_ |= 524288;
        this.maximumScreenDimDurationConfigMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaximumScreenDimDurationConfigMs() {
        this.bitField0_ &= -524289;
        this.maximumScreenDimDurationConfigMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasMaximumScreenDimRatioConfig() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public float getMaximumScreenDimRatioConfig() {
        return this.maximumScreenDimRatioConfig_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaximumScreenDimRatioConfig(float value) {
        this.bitField0_ |= 1048576;
        this.maximumScreenDimRatioConfig_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaximumScreenDimRatioConfig() {
        this.bitField0_ &= -1048577;
        this.maximumScreenDimRatioConfig_ = 0.0f;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasScreenOffTimeoutSettingMs() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getScreenOffTimeoutSettingMs() {
        return this.screenOffTimeoutSettingMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenOffTimeoutSettingMs(int value) {
        this.bitField0_ |= 2097152;
        this.screenOffTimeoutSettingMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenOffTimeoutSettingMs() {
        this.bitField0_ &= -2097153;
        this.screenOffTimeoutSettingMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasSleepTimeoutSettingMs() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getSleepTimeoutSettingMs() {
        return this.sleepTimeoutSettingMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSleepTimeoutSettingMs(int value) {
        this.bitField0_ |= 4194304;
        this.sleepTimeoutSettingMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSleepTimeoutSettingMs() {
        this.bitField0_ &= -4194305;
        this.sleepTimeoutSettingMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasMaximumScreenOffTimeoutFromDeviceAdminMs() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getMaximumScreenOffTimeoutFromDeviceAdminMs() {
        return this.maximumScreenOffTimeoutFromDeviceAdminMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMaximumScreenOffTimeoutFromDeviceAdminMs(int value) {
        this.bitField0_ |= 8388608;
        this.maximumScreenOffTimeoutFromDeviceAdminMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMaximumScreenOffTimeoutFromDeviceAdminMs() {
        this.bitField0_ &= -8388609;
        this.maximumScreenOffTimeoutFromDeviceAdminMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
        return this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked(boolean value) {
        this.bitField0_ |= 16777216;
        this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
        this.bitField0_ &= -16777217;
        this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasStayOnWhilePluggedIn() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public StayOnWhilePluggedInProto getStayOnWhilePluggedIn() {
        StayOnWhilePluggedInProto stayOnWhilePluggedInProto = this.stayOnWhilePluggedIn_;
        return stayOnWhilePluggedInProto == null ? StayOnWhilePluggedInProto.getDefaultInstance() : stayOnWhilePluggedInProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStayOnWhilePluggedIn(StayOnWhilePluggedInProto value) {
        if (value != null) {
            this.stayOnWhilePluggedIn_ = value;
            this.bitField0_ |= 33554432;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setStayOnWhilePluggedIn(StayOnWhilePluggedInProto.Builder builderForValue) {
        this.stayOnWhilePluggedIn_ = (StayOnWhilePluggedInProto) builderForValue.build();
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeStayOnWhilePluggedIn(StayOnWhilePluggedInProto value) {
        StayOnWhilePluggedInProto stayOnWhilePluggedInProto = this.stayOnWhilePluggedIn_;
        if (stayOnWhilePluggedInProto == null || stayOnWhilePluggedInProto == StayOnWhilePluggedInProto.getDefaultInstance()) {
            this.stayOnWhilePluggedIn_ = value;
        } else {
            this.stayOnWhilePluggedIn_ = (StayOnWhilePluggedInProto) ((StayOnWhilePluggedInProto.Builder) StayOnWhilePluggedInProto.newBuilder(this.stayOnWhilePluggedIn_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearStayOnWhilePluggedIn() {
        this.stayOnWhilePluggedIn_ = null;
        this.bitField0_ &= -33554433;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasScreenBrightnessModeSetting() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public SettingsProto.ScreenBrightnessMode getScreenBrightnessModeSetting() {
        SettingsProto.ScreenBrightnessMode result = SettingsProto.ScreenBrightnessMode.forNumber(this.screenBrightnessModeSetting_);
        return result == null ? SettingsProto.ScreenBrightnessMode.SCREEN_BRIGHTNESS_MODE_MANUAL : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightnessModeSetting(SettingsProto.ScreenBrightnessMode value) {
        if (value != null) {
            this.bitField0_ |= 67108864;
            this.screenBrightnessModeSetting_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenBrightnessModeSetting() {
        this.bitField0_ &= -67108865;
        this.screenBrightnessModeSetting_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasScreenBrightnessOverrideFromWindowManager() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public int getScreenBrightnessOverrideFromWindowManager() {
        return this.screenBrightnessOverrideFromWindowManager_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightnessOverrideFromWindowManager(int value) {
        this.bitField0_ |= 134217728;
        this.screenBrightnessOverrideFromWindowManager_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenBrightnessOverrideFromWindowManager() {
        this.bitField0_ &= -134217729;
        this.screenBrightnessOverrideFromWindowManager_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasUserActivityTimeoutOverrideFromWindowManagerMs() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public long getUserActivityTimeoutOverrideFromWindowManagerMs() {
        return this.userActivityTimeoutOverrideFromWindowManagerMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivityTimeoutOverrideFromWindowManagerMs(long value) {
        this.bitField0_ |= 268435456;
        this.userActivityTimeoutOverrideFromWindowManagerMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserActivityTimeoutOverrideFromWindowManagerMs() {
        this.bitField0_ &= -268435457;
        this.userActivityTimeoutOverrideFromWindowManagerMs_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsUserInactiveOverrideFromWindowManager() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsUserInactiveOverrideFromWindowManager() {
        return this.isUserInactiveOverrideFromWindowManager_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsUserInactiveOverrideFromWindowManager(boolean value) {
        this.bitField0_ |= 536870912;
        this.isUserInactiveOverrideFromWindowManager_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsUserInactiveOverrideFromWindowManager() {
        this.bitField0_ &= -536870913;
        this.isUserInactiveOverrideFromWindowManager_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDozeScreenStateOverrideFromDreamManager() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public DisplayStateEnum getDozeScreenStateOverrideFromDreamManager() {
        DisplayStateEnum result = DisplayStateEnum.forNumber(this.dozeScreenStateOverrideFromDreamManager_);
        return result == null ? DisplayStateEnum.DISPLAY_STATE_UNKNOWN : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDozeScreenStateOverrideFromDreamManager(DisplayStateEnum value) {
        if (value != null) {
            this.bitField0_ |= 1073741824;
            this.dozeScreenStateOverrideFromDreamManager_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDozeScreenStateOverrideFromDreamManager() {
        this.bitField0_ &= -1073741825;
        this.dozeScreenStateOverrideFromDreamManager_ = 0;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDozedScreenBrightnessOverrideFromDreamManager() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public float getDozedScreenBrightnessOverrideFromDreamManager() {
        return this.dozedScreenBrightnessOverrideFromDreamManager_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDozedScreenBrightnessOverrideFromDreamManager(float value) {
        this.bitField0_ |= Integer.MIN_VALUE;
        this.dozedScreenBrightnessOverrideFromDreamManager_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDozedScreenBrightnessOverrideFromDreamManager() {
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
        this.dozedScreenBrightnessOverrideFromDreamManager_ = 0.0f;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasScreenBrightnessSettingLimits() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public ScreenBrightnessSettingLimitsProto getScreenBrightnessSettingLimits() {
        ScreenBrightnessSettingLimitsProto screenBrightnessSettingLimitsProto = this.screenBrightnessSettingLimits_;
        return screenBrightnessSettingLimitsProto == null ? ScreenBrightnessSettingLimitsProto.getDefaultInstance() : screenBrightnessSettingLimitsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto value) {
        if (value != null) {
            this.screenBrightnessSettingLimits_ = value;
            this.bitField1_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto.Builder builderForValue) {
        this.screenBrightnessSettingLimits_ = (ScreenBrightnessSettingLimitsProto) builderForValue.build();
        this.bitField1_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto value) {
        ScreenBrightnessSettingLimitsProto screenBrightnessSettingLimitsProto = this.screenBrightnessSettingLimits_;
        if (screenBrightnessSettingLimitsProto == null || screenBrightnessSettingLimitsProto == ScreenBrightnessSettingLimitsProto.getDefaultInstance()) {
            this.screenBrightnessSettingLimits_ = value;
        } else {
            this.screenBrightnessSettingLimits_ = (ScreenBrightnessSettingLimitsProto) ((ScreenBrightnessSettingLimitsProto.Builder) ScreenBrightnessSettingLimitsProto.newBuilder(this.screenBrightnessSettingLimits_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenBrightnessSettingLimits() {
        this.screenBrightnessSettingLimits_ = null;
        this.bitField1_ &= -2;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsDoubleTapWakeEnabled() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsDoubleTapWakeEnabled() {
        return this.isDoubleTapWakeEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDoubleTapWakeEnabled(boolean value) {
        this.bitField1_ |= 2;
        this.isDoubleTapWakeEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDoubleTapWakeEnabled() {
        this.bitField1_ &= -3;
        this.isDoubleTapWakeEnabled_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasIsVrModeEnabled() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getIsVrModeEnabled() {
        return this.isVrModeEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsVrModeEnabled(boolean value) {
        this.bitField1_ |= 4;
        this.isVrModeEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsVrModeEnabled() {
        this.bitField1_ &= -5;
        this.isVrModeEnabled_ = false;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean hasDrawWakeLockOverrideFromSidekick() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
    public boolean getDrawWakeLockOverrideFromSidekick() {
        return this.drawWakeLockOverrideFromSidekick_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDrawWakeLockOverrideFromSidekick(boolean value) {
        this.bitField1_ |= 8;
        this.drawWakeLockOverrideFromSidekick_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDrawWakeLockOverrideFromSidekick() {
        this.bitField1_ &= -9;
        this.drawWakeLockOverrideFromSidekick_ = false;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeBool(1, this.isDecoupleHalAutoSuspendModeFromDisplayConfig_);
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeBool(2, this.isDecoupleHalInteractiveModeFromDisplayConfig_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeBool(3, this.isWakeUpWhenPluggedOrUnpluggedConfig_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.isTheaterModeEnabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeBool(6, this.isSuspendWhenScreenOffDueToProximityConfig_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeBool(7, this.areDreamsSupportedConfig_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeBool(8, this.areDreamsEnabledByDefaultConfig_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeBool(9, this.areDreamsActivatedOnSleepByDefaultConfig_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(10, this.areDreamsActivatedOnDockByDefaultConfig_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(11, this.areDreamsEnabledOnBatteryConfig_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeSInt32(12, this.dreamsBatteryLevelMinimumWhenPoweredConfig_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeSInt32(13, this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeSInt32(14, this.dreamsBatteryLevelDrainCutoffConfig_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeBool(15, this.areDreamsEnabledSetting_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeBool(16, this.areDreamsActivateOnSleepSetting_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeBool(17, this.areDreamsActivateOnDockSetting_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeBool(18, this.isDozeAfterScreenOffConfig_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt32(19, this.minimumScreenOffTimeoutConfigMs_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeInt32(20, this.maximumScreenDimDurationConfigMs_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeFloat(21, this.maximumScreenDimRatioConfig_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeInt32(22, this.screenOffTimeoutSettingMs_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeSInt32(23, this.sleepTimeoutSettingMs_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeInt32(24, this.maximumScreenOffTimeoutFromDeviceAdminMs_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeBool(25, this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeMessage(26, getStayOnWhilePluggedIn());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeEnum(27, this.screenBrightnessModeSetting_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeSInt32(28, this.screenBrightnessOverrideFromWindowManager_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeSInt64(29, this.userActivityTimeoutOverrideFromWindowManagerMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeBool(30, this.isUserInactiveOverrideFromWindowManager_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeEnum(31, this.dozeScreenStateOverrideFromDreamManager_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeFloat(32, this.dozedScreenBrightnessOverrideFromDreamManager_);
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeMessage(33, getScreenBrightnessSettingLimits());
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeBool(34, this.isDoubleTapWakeEnabled_);
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeBool(35, this.isVrModeEnabled_);
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeBool(36, this.drawWakeLockOverrideFromSidekick_);
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
            size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isDecoupleHalAutoSuspendModeFromDisplayConfig_);
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(2, this.isDecoupleHalInteractiveModeFromDisplayConfig_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(3, this.isWakeUpWhenPluggedOrUnpluggedConfig_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.isTheaterModeEnabled_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeBoolSize(6, this.isSuspendWhenScreenOffDueToProximityConfig_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeBoolSize(7, this.areDreamsSupportedConfig_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeBoolSize(8, this.areDreamsEnabledByDefaultConfig_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeBoolSize(9, this.areDreamsActivatedOnSleepByDefaultConfig_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeBoolSize(10, this.areDreamsActivatedOnDockByDefaultConfig_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeBoolSize(11, this.areDreamsEnabledOnBatteryConfig_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeSInt32Size(12, this.dreamsBatteryLevelMinimumWhenPoweredConfig_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeSInt32Size(13, this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeSInt32Size(14, this.dreamsBatteryLevelDrainCutoffConfig_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeBoolSize(15, this.areDreamsEnabledSetting_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeBoolSize(16, this.areDreamsActivateOnSleepSetting_);
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeBoolSize(17, this.areDreamsActivateOnDockSetting_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeBoolSize(18, this.isDozeAfterScreenOffConfig_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeInt32Size(19, this.minimumScreenOffTimeoutConfigMs_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeInt32Size(20, this.maximumScreenDimDurationConfigMs_);
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeFloatSize(21, this.maximumScreenDimRatioConfig_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeInt32Size(22, this.screenOffTimeoutSettingMs_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeSInt32Size(23, this.sleepTimeoutSettingMs_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeInt32Size(24, this.maximumScreenOffTimeoutFromDeviceAdminMs_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeBoolSize(25, this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeMessageSize(26, getStayOnWhilePluggedIn());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size2 += CodedOutputStream.computeEnumSize(27, this.screenBrightnessModeSetting_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size2 += CodedOutputStream.computeSInt32Size(28, this.screenBrightnessOverrideFromWindowManager_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size2 += CodedOutputStream.computeSInt64Size(29, this.userActivityTimeoutOverrideFromWindowManagerMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size2 += CodedOutputStream.computeBoolSize(30, this.isUserInactiveOverrideFromWindowManager_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size2 += CodedOutputStream.computeEnumSize(31, this.dozeScreenStateOverrideFromDreamManager_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size2 += CodedOutputStream.computeFloatSize(32, this.dozedScreenBrightnessOverrideFromDreamManager_);
        }
        if ((this.bitField1_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(33, getScreenBrightnessSettingLimits());
        }
        if ((this.bitField1_ & 2) == 2) {
            size2 += CodedOutputStream.computeBoolSize(34, this.isDoubleTapWakeEnabled_);
        }
        if ((this.bitField1_ & 4) == 4) {
            size2 += CodedOutputStream.computeBoolSize(35, this.isVrModeEnabled_);
        }
        if ((this.bitField1_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(36, this.drawWakeLockOverrideFromSidekick_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(InputStream input) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerServiceSettingsAndConfigurationDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerServiceSettingsAndConfigurationDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PowerServiceSettingsAndConfigurationDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PowerServiceSettingsAndConfigurationDumpProto, Builder> implements PowerServiceSettingsAndConfigurationDumpProtoOrBuilder {
        private Builder() {
            super(PowerServiceSettingsAndConfigurationDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsDecoupleHalAutoSuspendModeFromDisplayConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsDecoupleHalAutoSuspendModeFromDisplayConfig();
        }

        public Builder setIsDecoupleHalAutoSuspendModeFromDisplayConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsDecoupleHalAutoSuspendModeFromDisplayConfig(value);
            return this;
        }

        public Builder clearIsDecoupleHalAutoSuspendModeFromDisplayConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsDecoupleHalAutoSuspendModeFromDisplayConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsDecoupleHalInteractiveModeFromDisplayConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsDecoupleHalInteractiveModeFromDisplayConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsDecoupleHalInteractiveModeFromDisplayConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsDecoupleHalInteractiveModeFromDisplayConfig();
        }

        public Builder setIsDecoupleHalInteractiveModeFromDisplayConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsDecoupleHalInteractiveModeFromDisplayConfig(value);
            return this;
        }

        public Builder clearIsDecoupleHalInteractiveModeFromDisplayConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsDecoupleHalInteractiveModeFromDisplayConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsWakeUpWhenPluggedOrUnpluggedConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsWakeUpWhenPluggedOrUnpluggedConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsWakeUpWhenPluggedOrUnpluggedConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsWakeUpWhenPluggedOrUnpluggedConfig();
        }

        public Builder setIsWakeUpWhenPluggedOrUnpluggedConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsWakeUpWhenPluggedOrUnpluggedConfig(value);
            return this;
        }

        public Builder clearIsWakeUpWhenPluggedOrUnpluggedConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsWakeUpWhenPluggedOrUnpluggedConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig();
        }

        public Builder setIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig(value);
            return this;
        }

        public Builder clearIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsTheaterModeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsTheaterModeEnabled();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsTheaterModeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsTheaterModeEnabled();
        }

        public Builder setIsTheaterModeEnabled(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsTheaterModeEnabled(value);
            return this;
        }

        public Builder clearIsTheaterModeEnabled() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsTheaterModeEnabled();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsSuspendWhenScreenOffDueToProximityConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsSuspendWhenScreenOffDueToProximityConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsSuspendWhenScreenOffDueToProximityConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsSuspendWhenScreenOffDueToProximityConfig();
        }

        public Builder setIsSuspendWhenScreenOffDueToProximityConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsSuspendWhenScreenOffDueToProximityConfig(value);
            return this;
        }

        public Builder clearIsSuspendWhenScreenOffDueToProximityConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsSuspendWhenScreenOffDueToProximityConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsSupportedConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsSupportedConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsSupportedConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsSupportedConfig();
        }

        public Builder setAreDreamsSupportedConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsSupportedConfig(value);
            return this;
        }

        public Builder clearAreDreamsSupportedConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsSupportedConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsEnabledByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsEnabledByDefaultConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsEnabledByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsEnabledByDefaultConfig();
        }

        public Builder setAreDreamsEnabledByDefaultConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsEnabledByDefaultConfig(value);
            return this;
        }

        public Builder clearAreDreamsEnabledByDefaultConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsEnabledByDefaultConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsActivatedOnSleepByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsActivatedOnSleepByDefaultConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsActivatedOnSleepByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsActivatedOnSleepByDefaultConfig();
        }

        public Builder setAreDreamsActivatedOnSleepByDefaultConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsActivatedOnSleepByDefaultConfig(value);
            return this;
        }

        public Builder clearAreDreamsActivatedOnSleepByDefaultConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsActivatedOnSleepByDefaultConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsActivatedOnDockByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsActivatedOnDockByDefaultConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsActivatedOnDockByDefaultConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsActivatedOnDockByDefaultConfig();
        }

        public Builder setAreDreamsActivatedOnDockByDefaultConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsActivatedOnDockByDefaultConfig(value);
            return this;
        }

        public Builder clearAreDreamsActivatedOnDockByDefaultConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsActivatedOnDockByDefaultConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsEnabledOnBatteryConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsEnabledOnBatteryConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsEnabledOnBatteryConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsEnabledOnBatteryConfig();
        }

        public Builder setAreDreamsEnabledOnBatteryConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsEnabledOnBatteryConfig(value);
            return this;
        }

        public Builder clearAreDreamsEnabledOnBatteryConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsEnabledOnBatteryConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDreamsBatteryLevelMinimumWhenPoweredConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDreamsBatteryLevelMinimumWhenPoweredConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getDreamsBatteryLevelMinimumWhenPoweredConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDreamsBatteryLevelMinimumWhenPoweredConfig();
        }

        public Builder setDreamsBatteryLevelMinimumWhenPoweredConfig(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDreamsBatteryLevelMinimumWhenPoweredConfig(value);
            return this;
        }

        public Builder clearDreamsBatteryLevelMinimumWhenPoweredConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDreamsBatteryLevelMinimumWhenPoweredConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDreamsBatteryLevelMinimumWhenNotPoweredConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDreamsBatteryLevelMinimumWhenNotPoweredConfig();
        }

        public Builder setDreamsBatteryLevelMinimumWhenNotPoweredConfig(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDreamsBatteryLevelMinimumWhenNotPoweredConfig(value);
            return this;
        }

        public Builder clearDreamsBatteryLevelMinimumWhenNotPoweredConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDreamsBatteryLevelMinimumWhenNotPoweredConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDreamsBatteryLevelDrainCutoffConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDreamsBatteryLevelDrainCutoffConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getDreamsBatteryLevelDrainCutoffConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDreamsBatteryLevelDrainCutoffConfig();
        }

        public Builder setDreamsBatteryLevelDrainCutoffConfig(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDreamsBatteryLevelDrainCutoffConfig(value);
            return this;
        }

        public Builder clearDreamsBatteryLevelDrainCutoffConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDreamsBatteryLevelDrainCutoffConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsEnabledSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsEnabledSetting();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsEnabledSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsEnabledSetting();
        }

        public Builder setAreDreamsEnabledSetting(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsEnabledSetting(value);
            return this;
        }

        public Builder clearAreDreamsEnabledSetting() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsEnabledSetting();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsActivateOnSleepSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsActivateOnSleepSetting();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsActivateOnSleepSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsActivateOnSleepSetting();
        }

        public Builder setAreDreamsActivateOnSleepSetting(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsActivateOnSleepSetting(value);
            return this;
        }

        public Builder clearAreDreamsActivateOnSleepSetting() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsActivateOnSleepSetting();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasAreDreamsActivateOnDockSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasAreDreamsActivateOnDockSetting();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getAreDreamsActivateOnDockSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getAreDreamsActivateOnDockSetting();
        }

        public Builder setAreDreamsActivateOnDockSetting(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setAreDreamsActivateOnDockSetting(value);
            return this;
        }

        public Builder clearAreDreamsActivateOnDockSetting() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearAreDreamsActivateOnDockSetting();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsDozeAfterScreenOffConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsDozeAfterScreenOffConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsDozeAfterScreenOffConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsDozeAfterScreenOffConfig();
        }

        public Builder setIsDozeAfterScreenOffConfig(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsDozeAfterScreenOffConfig(value);
            return this;
        }

        public Builder clearIsDozeAfterScreenOffConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsDozeAfterScreenOffConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasMinimumScreenOffTimeoutConfigMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasMinimumScreenOffTimeoutConfigMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getMinimumScreenOffTimeoutConfigMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getMinimumScreenOffTimeoutConfigMs();
        }

        public Builder setMinimumScreenOffTimeoutConfigMs(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setMinimumScreenOffTimeoutConfigMs(value);
            return this;
        }

        public Builder clearMinimumScreenOffTimeoutConfigMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearMinimumScreenOffTimeoutConfigMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasMaximumScreenDimDurationConfigMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasMaximumScreenDimDurationConfigMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getMaximumScreenDimDurationConfigMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getMaximumScreenDimDurationConfigMs();
        }

        public Builder setMaximumScreenDimDurationConfigMs(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setMaximumScreenDimDurationConfigMs(value);
            return this;
        }

        public Builder clearMaximumScreenDimDurationConfigMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearMaximumScreenDimDurationConfigMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasMaximumScreenDimRatioConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasMaximumScreenDimRatioConfig();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public float getMaximumScreenDimRatioConfig() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getMaximumScreenDimRatioConfig();
        }

        public Builder setMaximumScreenDimRatioConfig(float value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setMaximumScreenDimRatioConfig(value);
            return this;
        }

        public Builder clearMaximumScreenDimRatioConfig() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearMaximumScreenDimRatioConfig();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasScreenOffTimeoutSettingMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasScreenOffTimeoutSettingMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getScreenOffTimeoutSettingMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getScreenOffTimeoutSettingMs();
        }

        public Builder setScreenOffTimeoutSettingMs(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setScreenOffTimeoutSettingMs(value);
            return this;
        }

        public Builder clearScreenOffTimeoutSettingMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearScreenOffTimeoutSettingMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasSleepTimeoutSettingMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasSleepTimeoutSettingMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getSleepTimeoutSettingMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getSleepTimeoutSettingMs();
        }

        public Builder setSleepTimeoutSettingMs(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setSleepTimeoutSettingMs(value);
            return this;
        }

        public Builder clearSleepTimeoutSettingMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearSleepTimeoutSettingMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasMaximumScreenOffTimeoutFromDeviceAdminMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasMaximumScreenOffTimeoutFromDeviceAdminMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getMaximumScreenOffTimeoutFromDeviceAdminMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getMaximumScreenOffTimeoutFromDeviceAdminMs();
        }

        public Builder setMaximumScreenOffTimeoutFromDeviceAdminMs(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setMaximumScreenOffTimeoutFromDeviceAdminMs(value);
            return this;
        }

        public Builder clearMaximumScreenOffTimeoutFromDeviceAdminMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearMaximumScreenOffTimeoutFromDeviceAdminMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked();
        }

        public Builder setIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked(value);
            return this;
        }

        public Builder clearIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasStayOnWhilePluggedIn() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasStayOnWhilePluggedIn();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public StayOnWhilePluggedInProto getStayOnWhilePluggedIn() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getStayOnWhilePluggedIn();
        }

        public Builder setStayOnWhilePluggedIn(StayOnWhilePluggedInProto value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setStayOnWhilePluggedIn((PowerServiceSettingsAndConfigurationDumpProto) value);
            return this;
        }

        public Builder setStayOnWhilePluggedIn(StayOnWhilePluggedInProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setStayOnWhilePluggedIn((PowerServiceSettingsAndConfigurationDumpProto) builderForValue);
            return this;
        }

        public Builder mergeStayOnWhilePluggedIn(StayOnWhilePluggedInProto value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).mergeStayOnWhilePluggedIn(value);
            return this;
        }

        public Builder clearStayOnWhilePluggedIn() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearStayOnWhilePluggedIn();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasScreenBrightnessModeSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasScreenBrightnessModeSetting();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public SettingsProto.ScreenBrightnessMode getScreenBrightnessModeSetting() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getScreenBrightnessModeSetting();
        }

        public Builder setScreenBrightnessModeSetting(SettingsProto.ScreenBrightnessMode value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setScreenBrightnessModeSetting(value);
            return this;
        }

        public Builder clearScreenBrightnessModeSetting() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearScreenBrightnessModeSetting();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasScreenBrightnessOverrideFromWindowManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasScreenBrightnessOverrideFromWindowManager();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public int getScreenBrightnessOverrideFromWindowManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getScreenBrightnessOverrideFromWindowManager();
        }

        public Builder setScreenBrightnessOverrideFromWindowManager(int value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setScreenBrightnessOverrideFromWindowManager(value);
            return this;
        }

        public Builder clearScreenBrightnessOverrideFromWindowManager() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearScreenBrightnessOverrideFromWindowManager();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasUserActivityTimeoutOverrideFromWindowManagerMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasUserActivityTimeoutOverrideFromWindowManagerMs();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public long getUserActivityTimeoutOverrideFromWindowManagerMs() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getUserActivityTimeoutOverrideFromWindowManagerMs();
        }

        public Builder setUserActivityTimeoutOverrideFromWindowManagerMs(long value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setUserActivityTimeoutOverrideFromWindowManagerMs(value);
            return this;
        }

        public Builder clearUserActivityTimeoutOverrideFromWindowManagerMs() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearUserActivityTimeoutOverrideFromWindowManagerMs();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsUserInactiveOverrideFromWindowManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsUserInactiveOverrideFromWindowManager();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsUserInactiveOverrideFromWindowManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsUserInactiveOverrideFromWindowManager();
        }

        public Builder setIsUserInactiveOverrideFromWindowManager(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsUserInactiveOverrideFromWindowManager(value);
            return this;
        }

        public Builder clearIsUserInactiveOverrideFromWindowManager() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsUserInactiveOverrideFromWindowManager();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDozeScreenStateOverrideFromDreamManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDozeScreenStateOverrideFromDreamManager();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public DisplayStateEnum getDozeScreenStateOverrideFromDreamManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDozeScreenStateOverrideFromDreamManager();
        }

        public Builder setDozeScreenStateOverrideFromDreamManager(DisplayStateEnum value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDozeScreenStateOverrideFromDreamManager(value);
            return this;
        }

        public Builder clearDozeScreenStateOverrideFromDreamManager() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDozeScreenStateOverrideFromDreamManager();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDozedScreenBrightnessOverrideFromDreamManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDozedScreenBrightnessOverrideFromDreamManager();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public float getDozedScreenBrightnessOverrideFromDreamManager() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDozedScreenBrightnessOverrideFromDreamManager();
        }

        public Builder setDozedScreenBrightnessOverrideFromDreamManager(float value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDozedScreenBrightnessOverrideFromDreamManager(value);
            return this;
        }

        public Builder clearDozedScreenBrightnessOverrideFromDreamManager() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDozedScreenBrightnessOverrideFromDreamManager();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasScreenBrightnessSettingLimits() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasScreenBrightnessSettingLimits();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public ScreenBrightnessSettingLimitsProto getScreenBrightnessSettingLimits() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getScreenBrightnessSettingLimits();
        }

        public Builder setScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setScreenBrightnessSettingLimits((PowerServiceSettingsAndConfigurationDumpProto) value);
            return this;
        }

        public Builder setScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setScreenBrightnessSettingLimits((PowerServiceSettingsAndConfigurationDumpProto) builderForValue);
            return this;
        }

        public Builder mergeScreenBrightnessSettingLimits(ScreenBrightnessSettingLimitsProto value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).mergeScreenBrightnessSettingLimits(value);
            return this;
        }

        public Builder clearScreenBrightnessSettingLimits() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearScreenBrightnessSettingLimits();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsDoubleTapWakeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsDoubleTapWakeEnabled();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsDoubleTapWakeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsDoubleTapWakeEnabled();
        }

        public Builder setIsDoubleTapWakeEnabled(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsDoubleTapWakeEnabled(value);
            return this;
        }

        public Builder clearIsDoubleTapWakeEnabled() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsDoubleTapWakeEnabled();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasIsVrModeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasIsVrModeEnabled();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getIsVrModeEnabled() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getIsVrModeEnabled();
        }

        public Builder setIsVrModeEnabled(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setIsVrModeEnabled(value);
            return this;
        }

        public Builder clearIsVrModeEnabled() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearIsVrModeEnabled();
            return this;
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean hasDrawWakeLockOverrideFromSidekick() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).hasDrawWakeLockOverrideFromSidekick();
        }

        @Override // com.android.server.power.PowerServiceSettingsAndConfigurationDumpProtoOrBuilder
        public boolean getDrawWakeLockOverrideFromSidekick() {
            return ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).getDrawWakeLockOverrideFromSidekick();
        }

        public Builder setDrawWakeLockOverrideFromSidekick(boolean value) {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).setDrawWakeLockOverrideFromSidekick(value);
            return this;
        }

        public Builder clearDrawWakeLockOverrideFromSidekick() {
            copyOnWrite();
            ((PowerServiceSettingsAndConfigurationDumpProto) this.instance).clearDrawWakeLockOverrideFromSidekick();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PowerServiceSettingsAndConfigurationDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PowerServiceSettingsAndConfigurationDumpProto other = (PowerServiceSettingsAndConfigurationDumpProto) arg1;
                this.isDecoupleHalAutoSuspendModeFromDisplayConfig_ = visitor.visitBoolean(hasIsDecoupleHalAutoSuspendModeFromDisplayConfig(), this.isDecoupleHalAutoSuspendModeFromDisplayConfig_, other.hasIsDecoupleHalAutoSuspendModeFromDisplayConfig(), other.isDecoupleHalAutoSuspendModeFromDisplayConfig_);
                this.isDecoupleHalInteractiveModeFromDisplayConfig_ = visitor.visitBoolean(hasIsDecoupleHalInteractiveModeFromDisplayConfig(), this.isDecoupleHalInteractiveModeFromDisplayConfig_, other.hasIsDecoupleHalInteractiveModeFromDisplayConfig(), other.isDecoupleHalInteractiveModeFromDisplayConfig_);
                this.isWakeUpWhenPluggedOrUnpluggedConfig_ = visitor.visitBoolean(hasIsWakeUpWhenPluggedOrUnpluggedConfig(), this.isWakeUpWhenPluggedOrUnpluggedConfig_, other.hasIsWakeUpWhenPluggedOrUnpluggedConfig(), other.isWakeUpWhenPluggedOrUnpluggedConfig_);
                this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_ = visitor.visitBoolean(hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig(), this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_, other.hasIsWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig(), other.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_);
                this.isTheaterModeEnabled_ = visitor.visitBoolean(hasIsTheaterModeEnabled(), this.isTheaterModeEnabled_, other.hasIsTheaterModeEnabled(), other.isTheaterModeEnabled_);
                this.isSuspendWhenScreenOffDueToProximityConfig_ = visitor.visitBoolean(hasIsSuspendWhenScreenOffDueToProximityConfig(), this.isSuspendWhenScreenOffDueToProximityConfig_, other.hasIsSuspendWhenScreenOffDueToProximityConfig(), other.isSuspendWhenScreenOffDueToProximityConfig_);
                this.areDreamsSupportedConfig_ = visitor.visitBoolean(hasAreDreamsSupportedConfig(), this.areDreamsSupportedConfig_, other.hasAreDreamsSupportedConfig(), other.areDreamsSupportedConfig_);
                this.areDreamsEnabledByDefaultConfig_ = visitor.visitBoolean(hasAreDreamsEnabledByDefaultConfig(), this.areDreamsEnabledByDefaultConfig_, other.hasAreDreamsEnabledByDefaultConfig(), other.areDreamsEnabledByDefaultConfig_);
                this.areDreamsActivatedOnSleepByDefaultConfig_ = visitor.visitBoolean(hasAreDreamsActivatedOnSleepByDefaultConfig(), this.areDreamsActivatedOnSleepByDefaultConfig_, other.hasAreDreamsActivatedOnSleepByDefaultConfig(), other.areDreamsActivatedOnSleepByDefaultConfig_);
                this.areDreamsActivatedOnDockByDefaultConfig_ = visitor.visitBoolean(hasAreDreamsActivatedOnDockByDefaultConfig(), this.areDreamsActivatedOnDockByDefaultConfig_, other.hasAreDreamsActivatedOnDockByDefaultConfig(), other.areDreamsActivatedOnDockByDefaultConfig_);
                this.areDreamsEnabledOnBatteryConfig_ = visitor.visitBoolean(hasAreDreamsEnabledOnBatteryConfig(), this.areDreamsEnabledOnBatteryConfig_, other.hasAreDreamsEnabledOnBatteryConfig(), other.areDreamsEnabledOnBatteryConfig_);
                this.dreamsBatteryLevelMinimumWhenPoweredConfig_ = visitor.visitInt(hasDreamsBatteryLevelMinimumWhenPoweredConfig(), this.dreamsBatteryLevelMinimumWhenPoweredConfig_, other.hasDreamsBatteryLevelMinimumWhenPoweredConfig(), other.dreamsBatteryLevelMinimumWhenPoweredConfig_);
                this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_ = visitor.visitInt(hasDreamsBatteryLevelMinimumWhenNotPoweredConfig(), this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_, other.hasDreamsBatteryLevelMinimumWhenNotPoweredConfig(), other.dreamsBatteryLevelMinimumWhenNotPoweredConfig_);
                this.dreamsBatteryLevelDrainCutoffConfig_ = visitor.visitInt(hasDreamsBatteryLevelDrainCutoffConfig(), this.dreamsBatteryLevelDrainCutoffConfig_, other.hasDreamsBatteryLevelDrainCutoffConfig(), other.dreamsBatteryLevelDrainCutoffConfig_);
                this.areDreamsEnabledSetting_ = visitor.visitBoolean(hasAreDreamsEnabledSetting(), this.areDreamsEnabledSetting_, other.hasAreDreamsEnabledSetting(), other.areDreamsEnabledSetting_);
                this.areDreamsActivateOnSleepSetting_ = visitor.visitBoolean(hasAreDreamsActivateOnSleepSetting(), this.areDreamsActivateOnSleepSetting_, other.hasAreDreamsActivateOnSleepSetting(), other.areDreamsActivateOnSleepSetting_);
                this.areDreamsActivateOnDockSetting_ = visitor.visitBoolean(hasAreDreamsActivateOnDockSetting(), this.areDreamsActivateOnDockSetting_, other.hasAreDreamsActivateOnDockSetting(), other.areDreamsActivateOnDockSetting_);
                this.isDozeAfterScreenOffConfig_ = visitor.visitBoolean(hasIsDozeAfterScreenOffConfig(), this.isDozeAfterScreenOffConfig_, other.hasIsDozeAfterScreenOffConfig(), other.isDozeAfterScreenOffConfig_);
                this.minimumScreenOffTimeoutConfigMs_ = visitor.visitInt(hasMinimumScreenOffTimeoutConfigMs(), this.minimumScreenOffTimeoutConfigMs_, other.hasMinimumScreenOffTimeoutConfigMs(), other.minimumScreenOffTimeoutConfigMs_);
                this.maximumScreenDimDurationConfigMs_ = visitor.visitInt(hasMaximumScreenDimDurationConfigMs(), this.maximumScreenDimDurationConfigMs_, other.hasMaximumScreenDimDurationConfigMs(), other.maximumScreenDimDurationConfigMs_);
                this.maximumScreenDimRatioConfig_ = visitor.visitFloat(hasMaximumScreenDimRatioConfig(), this.maximumScreenDimRatioConfig_, other.hasMaximumScreenDimRatioConfig(), other.maximumScreenDimRatioConfig_);
                this.screenOffTimeoutSettingMs_ = visitor.visitInt(hasScreenOffTimeoutSettingMs(), this.screenOffTimeoutSettingMs_, other.hasScreenOffTimeoutSettingMs(), other.screenOffTimeoutSettingMs_);
                this.sleepTimeoutSettingMs_ = visitor.visitInt(hasSleepTimeoutSettingMs(), this.sleepTimeoutSettingMs_, other.hasSleepTimeoutSettingMs(), other.sleepTimeoutSettingMs_);
                this.maximumScreenOffTimeoutFromDeviceAdminMs_ = visitor.visitInt(hasMaximumScreenOffTimeoutFromDeviceAdminMs(), this.maximumScreenOffTimeoutFromDeviceAdminMs_, other.hasMaximumScreenOffTimeoutFromDeviceAdminMs(), other.maximumScreenOffTimeoutFromDeviceAdminMs_);
                this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_ = visitor.visitBoolean(hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked(), this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_, other.hasIsMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked(), other.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_);
                this.stayOnWhilePluggedIn_ = (StayOnWhilePluggedInProto) visitor.visitMessage(this.stayOnWhilePluggedIn_, other.stayOnWhilePluggedIn_);
                this.screenBrightnessModeSetting_ = visitor.visitInt(hasScreenBrightnessModeSetting(), this.screenBrightnessModeSetting_, other.hasScreenBrightnessModeSetting(), other.screenBrightnessModeSetting_);
                this.screenBrightnessOverrideFromWindowManager_ = visitor.visitInt(hasScreenBrightnessOverrideFromWindowManager(), this.screenBrightnessOverrideFromWindowManager_, other.hasScreenBrightnessOverrideFromWindowManager(), other.screenBrightnessOverrideFromWindowManager_);
                this.userActivityTimeoutOverrideFromWindowManagerMs_ = visitor.visitLong(hasUserActivityTimeoutOverrideFromWindowManagerMs(), this.userActivityTimeoutOverrideFromWindowManagerMs_, other.hasUserActivityTimeoutOverrideFromWindowManagerMs(), other.userActivityTimeoutOverrideFromWindowManagerMs_);
                this.isUserInactiveOverrideFromWindowManager_ = visitor.visitBoolean(hasIsUserInactiveOverrideFromWindowManager(), this.isUserInactiveOverrideFromWindowManager_, other.hasIsUserInactiveOverrideFromWindowManager(), other.isUserInactiveOverrideFromWindowManager_);
                this.dozeScreenStateOverrideFromDreamManager_ = visitor.visitInt(hasDozeScreenStateOverrideFromDreamManager(), this.dozeScreenStateOverrideFromDreamManager_, other.hasDozeScreenStateOverrideFromDreamManager(), other.dozeScreenStateOverrideFromDreamManager_);
                this.dozedScreenBrightnessOverrideFromDreamManager_ = visitor.visitFloat(hasDozedScreenBrightnessOverrideFromDreamManager(), this.dozedScreenBrightnessOverrideFromDreamManager_, other.hasDozedScreenBrightnessOverrideFromDreamManager(), other.dozedScreenBrightnessOverrideFromDreamManager_);
                this.screenBrightnessSettingLimits_ = (ScreenBrightnessSettingLimitsProto) visitor.visitMessage(this.screenBrightnessSettingLimits_, other.screenBrightnessSettingLimits_);
                this.isDoubleTapWakeEnabled_ = visitor.visitBoolean(hasIsDoubleTapWakeEnabled(), this.isDoubleTapWakeEnabled_, other.hasIsDoubleTapWakeEnabled(), other.isDoubleTapWakeEnabled_);
                this.isVrModeEnabled_ = visitor.visitBoolean(hasIsVrModeEnabled(), this.isVrModeEnabled_, other.hasIsVrModeEnabled(), other.isVrModeEnabled_);
                this.drawWakeLockOverrideFromSidekick_ = visitor.visitBoolean(hasDrawWakeLockOverrideFromSidekick(), this.drawWakeLockOverrideFromSidekick_, other.hasDrawWakeLockOverrideFromSidekick(), other.drawWakeLockOverrideFromSidekick_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
                    this.bitField1_ |= other.bitField1_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream input = (CodedInputStream) arg0;
                ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                boolean done = false;
                while (!done) {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                this.bitField0_ = 1 | this.bitField0_;
                                this.isDecoupleHalAutoSuspendModeFromDisplayConfig_ = input.readBool();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.isDecoupleHalInteractiveModeFromDisplayConfig_ = input.readBool();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.isWakeUpWhenPluggedOrUnpluggedConfig_ = input.readBool();
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.isWakeUpWhenPluggedOrUnpluggedInTheaterModeConfig_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.isTheaterModeEnabled_ = input.readBool();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.isSuspendWhenScreenOffDueToProximityConfig_ = input.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.areDreamsSupportedConfig_ = input.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.areDreamsEnabledByDefaultConfig_ = input.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.areDreamsActivatedOnSleepByDefaultConfig_ = input.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.areDreamsActivatedOnDockByDefaultConfig_ = input.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.areDreamsEnabledOnBatteryConfig_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.dreamsBatteryLevelMinimumWhenPoweredConfig_ = input.readSInt32();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.dreamsBatteryLevelMinimumWhenNotPoweredConfig_ = input.readSInt32();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.dreamsBatteryLevelDrainCutoffConfig_ = input.readSInt32();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.areDreamsEnabledSetting_ = input.readBool();
                                break;
                            case 128:
                                this.bitField0_ |= 32768;
                                this.areDreamsActivateOnSleepSetting_ = input.readBool();
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.areDreamsActivateOnDockSetting_ = input.readBool();
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.isDozeAfterScreenOffConfig_ = input.readBool();
                                break;
                            case 152:
                                this.bitField0_ |= 262144;
                                this.minimumScreenOffTimeoutConfigMs_ = input.readInt32();
                                break;
                            case 160:
                                this.bitField0_ |= 524288;
                                this.maximumScreenDimDurationConfigMs_ = input.readInt32();
                                break;
                            case AtomsProto.Atom.BUBBLE_DEVELOPER_ERROR_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 1048576;
                                this.maximumScreenDimRatioConfig_ = input.readFloat();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 2097152;
                                this.screenOffTimeoutSettingMs_ = input.readInt32();
                                break;
                            case 184:
                                this.bitField0_ |= 4194304;
                                this.sleepTimeoutSettingMs_ = input.readSInt32();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 8388608;
                                this.maximumScreenOffTimeoutFromDeviceAdminMs_ = input.readInt32();
                                break;
                            case 200:
                                this.bitField0_ |= 16777216;
                                this.isMaximumScreenOffTimeoutFromDeviceAdminEnforcedLocked_ = input.readBool();
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER:
                                StayOnWhilePluggedInProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 33554432) == 33554432) {
                                    subBuilder = (StayOnWhilePluggedInProto.Builder) this.stayOnWhilePluggedIn_.toBuilder();
                                }
                                this.stayOnWhilePluggedIn_ = (StayOnWhilePluggedInProto) input.readMessage(StayOnWhilePluggedInProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.stayOnWhilePluggedIn_);
                                    this.stayOnWhilePluggedIn_ = (StayOnWhilePluggedInProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 33554432;
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER:
                                int rawValue = input.readEnum();
                                if (SettingsProto.ScreenBrightnessMode.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 67108864;
                                    this.screenBrightnessModeSetting_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(27, rawValue);
                                    break;
                                }
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 134217728;
                                this.screenBrightnessOverrideFromWindowManager_ = input.readSInt32();
                                break;
                            case 232:
                                this.bitField0_ |= 268435456;
                                this.userActivityTimeoutOverrideFromWindowManagerMs_ = input.readSInt64();
                                break;
                            case FINGERPRINT_ENROLLING_VALUE:
                                this.bitField0_ |= 536870912;
                                this.isUserInactiveOverrideFromWindowManager_ = input.readBool();
                                break;
                            case FINGERPRINT_ENROLL_FINISH_SETUP_VALUE:
                                int rawValue2 = input.readEnum();
                                if (DisplayStateEnum.forNumber(rawValue2) != null) {
                                    this.bitField0_ |= 1073741824;
                                    this.dozeScreenStateOverrideFromDreamManager_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(31, rawValue2);
                                    break;
                                }
                            case 261:
                                this.bitField0_ |= Integer.MIN_VALUE;
                                this.dozedScreenBrightnessOverrideFromDreamManager_ = input.readFloat();
                                break;
                            case 266:
                                ScreenBrightnessSettingLimitsProto.Builder subBuilder2 = null;
                                if ((this.bitField1_ & 1) == 1) {
                                    subBuilder2 = (ScreenBrightnessSettingLimitsProto.Builder) this.screenBrightnessSettingLimits_.toBuilder();
                                }
                                this.screenBrightnessSettingLimits_ = (ScreenBrightnessSettingLimitsProto) input.readMessage(ScreenBrightnessSettingLimitsProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.screenBrightnessSettingLimits_);
                                    this.screenBrightnessSettingLimits_ = (ScreenBrightnessSettingLimitsProto) subBuilder2.buildPartial();
                                }
                                this.bitField1_ = 1 | this.bitField1_;
                                break;
                            case 272:
                                this.bitField1_ |= 2;
                                this.isDoubleTapWakeEnabled_ = input.readBool();
                                break;
                            case 280:
                                this.bitField1_ |= 4;
                                this.isVrModeEnabled_ = input.readBool();
                                break;
                            case 288:
                                this.bitField1_ |= 8;
                                this.drawWakeLockOverrideFromSidekick_ = input.readBool();
                                break;
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
                    synchronized (PowerServiceSettingsAndConfigurationDumpProto.class) {
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

    public static PowerServiceSettingsAndConfigurationDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PowerServiceSettingsAndConfigurationDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
