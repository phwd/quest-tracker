package com.android.server.power;

import android.app.ProcessStateEnum;
import android.content.IntentProto;
import android.os.BatteryPluggedStateEnum;
import android.os.LooperProto;
import android.os.PowerManagerInternalProto;
import android.telephony.DataConnectionPowerStateEnum;
import com.android.os.AtomsProto;
import com.android.server.power.BatterySaverStateMachineProto;
import com.android.server.power.PowerServiceSettingsAndConfigurationDumpProto;
import com.android.server.power.SuspendBlockerProto;
import com.android.server.power.WakeLockProto;
import com.android.server.power.WirelessChargerDetectorProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class PowerManagerServiceDumpProto extends GeneratedMessageLite<PowerManagerServiceDumpProto, Builder> implements PowerManagerServiceDumpProtoOrBuilder {
    public static final int ACTIVE_WAKE_LOCKS_FIELD_NUMBER = 16;
    public static final int ARE_UIDS_CHANGED_FIELD_NUMBER = 44;
    public static final int ARE_UIDS_CHANGING_FIELD_NUMBER = 43;
    public static final int BATTERY_LEVEL_FIELD_NUMBER = 7;
    public static final int BATTERY_LEVEL_WHEN_DREAM_STARTED_FIELD_NUMBER = 8;
    public static final int BATTERY_SAVER_STATE_MACHINE_FIELD_NUMBER = 50;
    public static final int CONSTANTS_FIELD_NUMBER = 1;
    private static final PowerManagerServiceDumpProto DEFAULT_INSTANCE = new PowerManagerServiceDumpProto();
    public static final int DEVICE_IDLE_TEMP_WHITELIST_FIELD_NUMBER = 28;
    public static final int DEVICE_IDLE_WHITELIST_FIELD_NUMBER = 27;
    public static final int DIRTY_FIELD_NUMBER = 2;
    public static final int DOCK_STATE_FIELD_NUMBER = 9;
    public static final int IS_BATTERY_LEVEL_LOW_FIELD_NUMBER = 24;
    public static final int IS_BOOT_COMPLETED_FIELD_NUMBER = 12;
    public static final int IS_DEVICE_IDLE_MODE_FIELD_NUMBER = 26;
    public static final int IS_DISPLAY_READY_FIELD_NUMBER = 36;
    public static final int IS_HAL_AUTO_INTERACTIVE_MODE_ENABLED_FIELD_NUMBER = 15;
    public static final int IS_HAL_AUTO_SUSPEND_MODE_ENABLED_FIELD_NUMBER = 14;
    public static final int IS_HOLDING_DISPLAY_SUSPEND_BLOCKER_FIELD_NUMBER = 38;
    public static final int IS_HOLDING_WAKE_LOCK_SUSPEND_BLOCKER_FIELD_NUMBER = 37;
    public static final int IS_LIGHT_DEVICE_IDLE_MODE_FIELD_NUMBER = 25;
    public static final int IS_POWERED_FIELD_NUMBER = 5;
    public static final int IS_PROXIMITY_POSITIVE_FIELD_NUMBER = 11;
    public static final int IS_REQUEST_WAIT_FOR_NEGATIVE_PROXIMITY_FIELD_NUMBER = 21;
    public static final int IS_SANDMAN_SCHEDULED_FIELD_NUMBER = 22;
    public static final int IS_SANDMAN_SUMMONED_FIELD_NUMBER = 23;
    public static final int IS_SCREEN_BRIGHTNESS_BOOST_IN_PROGRESS_FIELD_NUMBER = 35;
    public static final int IS_STAY_ON_FIELD_NUMBER = 10;
    public static final int IS_SYSTEM_READY_FIELD_NUMBER = 13;
    public static final int IS_WAKEFULNESS_CHANGING_FIELD_NUMBER = 4;
    public static final int LAST_INTERACTIVE_POWER_HINT_TIME_MS_FIELD_NUMBER = 33;
    public static final int LAST_SCREEN_BRIGHTNESS_BOOST_TIME_MS_FIELD_NUMBER = 34;
    public static final int LAST_SLEEP_TIME_MS_FIELD_NUMBER = 30;
    public static final int LAST_USER_ACTIVITY_TIME_MS_FIELD_NUMBER = 31;
    public static final int LAST_USER_ACTIVITY_TIME_NO_CHANGE_LIGHTS_MS_FIELD_NUMBER = 32;
    public static final int LAST_WAKE_TIME_MS_FIELD_NUMBER = 29;
    public static final int LOOPER_FIELD_NUMBER = 46;
    public static final int NOTIFY_LONG_DISPATCHED_MS_FIELD_NUMBER = 18;
    public static final int NOTIFY_LONG_NEXT_CHECK_MS_FIELD_NUMBER = 19;
    public static final int NOTIFY_LONG_SCHEDULED_MS_FIELD_NUMBER = 17;
    private static volatile Parser<PowerManagerServiceDumpProto> PARSER = null;
    public static final int PLUG_TYPE_FIELD_NUMBER = 6;
    public static final int SCREEN_DIM_DURATION_MS_FIELD_NUMBER = 42;
    public static final int SCREEN_OFF_TIMEOUT_MS_FIELD_NUMBER = 41;
    public static final int SETTINGS_AND_CONFIGURATION_FIELD_NUMBER = 39;
    public static final int SLEEP_TIMEOUT_MS_FIELD_NUMBER = 40;
    public static final int SUSPEND_BLOCKERS_FIELD_NUMBER = 48;
    public static final int UID_STATES_FIELD_NUMBER = 45;
    public static final int USER_ACTIVITY_FIELD_NUMBER = 20;
    public static final int WAKEFULNESS_FIELD_NUMBER = 3;
    public static final int WAKE_LOCKS_FIELD_NUMBER = 47;
    public static final int WIRELESS_CHARGER_DETECTOR_FIELD_NUMBER = 49;
    private ActiveWakeLocksProto activeWakeLocks_;
    private boolean areUidsChanged_ = false;
    private boolean areUidsChanging_ = false;
    private int batteryLevelWhenDreamStarted_ = 0;
    private int batteryLevel_ = 0;
    private BatterySaverStateMachineProto batterySaverStateMachine_;
    private int bitField0_;
    private int bitField1_;
    private ConstantsProto constants_;
    private Internal.IntList deviceIdleTempWhitelist_ = emptyIntList();
    private Internal.IntList deviceIdleWhitelist_ = emptyIntList();
    private int dirty_ = 0;
    private int dockState_ = 0;
    private boolean isBatteryLevelLow_ = false;
    private boolean isBootCompleted_ = false;
    private boolean isDeviceIdleMode_ = false;
    private boolean isDisplayReady_ = false;
    private boolean isHalAutoInteractiveModeEnabled_ = false;
    private boolean isHalAutoSuspendModeEnabled_ = false;
    private boolean isHoldingDisplaySuspendBlocker_ = false;
    private boolean isHoldingWakeLockSuspendBlocker_ = false;
    private boolean isLightDeviceIdleMode_ = false;
    private boolean isPowered_ = false;
    private boolean isProximityPositive_ = false;
    private boolean isRequestWaitForNegativeProximity_ = false;
    private boolean isSandmanScheduled_ = false;
    private boolean isSandmanSummoned_ = false;
    private boolean isScreenBrightnessBoostInProgress_ = false;
    private boolean isStayOn_ = false;
    private boolean isSystemReady_ = false;
    private boolean isWakefulnessChanging_ = false;
    private long lastInteractivePowerHintTimeMs_ = 0;
    private long lastScreenBrightnessBoostTimeMs_ = 0;
    private long lastSleepTimeMs_ = 0;
    private long lastUserActivityTimeMs_ = 0;
    private long lastUserActivityTimeNoChangeLightsMs_ = 0;
    private long lastWakeTimeMs_ = 0;
    private LooperProto looper_;
    private long notifyLongDispatchedMs_ = 0;
    private long notifyLongNextCheckMs_ = 0;
    private long notifyLongScheduledMs_ = 0;
    private int plugType_ = 0;
    private int screenDimDurationMs_ = 0;
    private int screenOffTimeoutMs_ = 0;
    private PowerServiceSettingsAndConfigurationDumpProto settingsAndConfiguration_;
    private int sleepTimeoutMs_ = 0;
    private Internal.ProtobufList<SuspendBlockerProto> suspendBlockers_ = emptyProtobufList();
    private Internal.ProtobufList<UidStateProto> uidStates_ = emptyProtobufList();
    private UserActivityProto userActivity_;
    private Internal.ProtobufList<WakeLockProto> wakeLocks_ = emptyProtobufList();
    private int wakefulness_ = 0;
    private WirelessChargerDetectorProto wirelessChargerDetector_;

    public interface ActiveWakeLocksProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsButtonBright();

        boolean getIsCpu();

        boolean getIsDoze();

        boolean getIsDraw();

        boolean getIsProximityScreenOff();

        boolean getIsScreenBright();

        boolean getIsScreenDim();

        boolean getIsStayAwake();

        boolean hasIsButtonBright();

        boolean hasIsCpu();

        boolean hasIsDoze();

        boolean hasIsDraw();

        boolean hasIsProximityScreenOff();

        boolean hasIsScreenBright();

        boolean hasIsScreenDim();

        boolean hasIsStayAwake();
    }

    public interface ConstantsProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsNoCachedWakeLocks();

        boolean hasIsNoCachedWakeLocks();
    }

    public interface UidStateProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsActive();

        int getNumWakeLocks();

        ProcessStateEnum getProcessState();

        int getUid();

        String getUidString();

        ByteString getUidStringBytes();

        boolean hasIsActive();

        boolean hasNumWakeLocks();

        boolean hasProcessState();

        boolean hasUid();

        boolean hasUidString();
    }

    public interface UserActivityProtoOrBuilder extends MessageLiteOrBuilder {
        boolean getIsScreenBright();

        boolean getIsScreenDim();

        boolean getIsScreenDream();

        boolean hasIsScreenBright();

        boolean hasIsScreenDim();

        boolean hasIsScreenDream();
    }

    private PowerManagerServiceDumpProto() {
    }

    public static final class ConstantsProto extends GeneratedMessageLite<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
        private static final ConstantsProto DEFAULT_INSTANCE = new ConstantsProto();
        public static final int IS_NO_CACHED_WAKE_LOCKS_FIELD_NUMBER = 1;
        private static volatile Parser<ConstantsProto> PARSER;
        private int bitField0_;
        private boolean isNoCachedWakeLocks_ = false;

        private ConstantsProto() {
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ConstantsProtoOrBuilder
        public boolean hasIsNoCachedWakeLocks() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ConstantsProtoOrBuilder
        public boolean getIsNoCachedWakeLocks() {
            return this.isNoCachedWakeLocks_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsNoCachedWakeLocks(boolean value) {
            this.bitField0_ |= 1;
            this.isNoCachedWakeLocks_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsNoCachedWakeLocks() {
            this.bitField0_ &= -2;
            this.isNoCachedWakeLocks_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isNoCachedWakeLocks_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isNoCachedWakeLocks_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ConstantsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ConstantsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ConstantsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ConstantsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ConstantsProto parseFrom(InputStream input) throws IOException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ConstantsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ConstantsProto parseDelimitedFrom(InputStream input) throws IOException {
            return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ConstantsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConstantsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ConstantsProto parseFrom(CodedInputStream input) throws IOException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ConstantsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ConstantsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConstantsProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ConstantsProto, Builder> implements ConstantsProtoOrBuilder {
            private Builder() {
                super(ConstantsProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ConstantsProtoOrBuilder
            public boolean hasIsNoCachedWakeLocks() {
                return ((ConstantsProto) this.instance).hasIsNoCachedWakeLocks();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ConstantsProtoOrBuilder
            public boolean getIsNoCachedWakeLocks() {
                return ((ConstantsProto) this.instance).getIsNoCachedWakeLocks();
            }

            public Builder setIsNoCachedWakeLocks(boolean value) {
                copyOnWrite();
                ((ConstantsProto) this.instance).setIsNoCachedWakeLocks(value);
                return this;
            }

            public Builder clearIsNoCachedWakeLocks() {
                copyOnWrite();
                ((ConstantsProto) this.instance).clearIsNoCachedWakeLocks();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ConstantsProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ConstantsProto other = (ConstantsProto) arg1;
                    this.isNoCachedWakeLocks_ = visitor.visitBoolean(hasIsNoCachedWakeLocks(), this.isNoCachedWakeLocks_, other.hasIsNoCachedWakeLocks(), other.isNoCachedWakeLocks_);
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
                                this.isNoCachedWakeLocks_ = input.readBool();
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
                        synchronized (ConstantsProto.class) {
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

        public static ConstantsProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConstantsProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class ActiveWakeLocksProto extends GeneratedMessageLite<ActiveWakeLocksProto, Builder> implements ActiveWakeLocksProtoOrBuilder {
        private static final ActiveWakeLocksProto DEFAULT_INSTANCE = new ActiveWakeLocksProto();
        public static final int IS_BUTTON_BRIGHT_FIELD_NUMBER = 4;
        public static final int IS_CPU_FIELD_NUMBER = 1;
        public static final int IS_DOZE_FIELD_NUMBER = 7;
        public static final int IS_DRAW_FIELD_NUMBER = 8;
        public static final int IS_PROXIMITY_SCREEN_OFF_FIELD_NUMBER = 5;
        public static final int IS_SCREEN_BRIGHT_FIELD_NUMBER = 2;
        public static final int IS_SCREEN_DIM_FIELD_NUMBER = 3;
        public static final int IS_STAY_AWAKE_FIELD_NUMBER = 6;
        private static volatile Parser<ActiveWakeLocksProto> PARSER;
        private int bitField0_;
        private boolean isButtonBright_ = false;
        private boolean isCpu_ = false;
        private boolean isDoze_ = false;
        private boolean isDraw_ = false;
        private boolean isProximityScreenOff_ = false;
        private boolean isScreenBright_ = false;
        private boolean isScreenDim_ = false;
        private boolean isStayAwake_ = false;

        private ActiveWakeLocksProto() {
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsCpu() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsCpu() {
            return this.isCpu_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsCpu(boolean value) {
            this.bitField0_ |= 1;
            this.isCpu_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsCpu() {
            this.bitField0_ &= -2;
            this.isCpu_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsScreenBright() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsScreenBright() {
            return this.isScreenBright_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsScreenBright(boolean value) {
            this.bitField0_ |= 2;
            this.isScreenBright_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsScreenBright() {
            this.bitField0_ &= -3;
            this.isScreenBright_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsScreenDim() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsScreenDim() {
            return this.isScreenDim_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsScreenDim(boolean value) {
            this.bitField0_ |= 4;
            this.isScreenDim_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsScreenDim() {
            this.bitField0_ &= -5;
            this.isScreenDim_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsButtonBright() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsButtonBright() {
            return this.isButtonBright_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsButtonBright(boolean value) {
            this.bitField0_ |= 8;
            this.isButtonBright_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsButtonBright() {
            this.bitField0_ &= -9;
            this.isButtonBright_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsProximityScreenOff() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsProximityScreenOff() {
            return this.isProximityScreenOff_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsProximityScreenOff(boolean value) {
            this.bitField0_ |= 16;
            this.isProximityScreenOff_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsProximityScreenOff() {
            this.bitField0_ &= -17;
            this.isProximityScreenOff_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsStayAwake() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsStayAwake() {
            return this.isStayAwake_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsStayAwake(boolean value) {
            this.bitField0_ |= 32;
            this.isStayAwake_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsStayAwake() {
            this.bitField0_ &= -33;
            this.isStayAwake_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsDoze() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsDoze() {
            return this.isDoze_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsDoze(boolean value) {
            this.bitField0_ |= 64;
            this.isDoze_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsDoze() {
            this.bitField0_ &= -65;
            this.isDoze_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean hasIsDraw() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
        public boolean getIsDraw() {
            return this.isDraw_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsDraw(boolean value) {
            this.bitField0_ |= 128;
            this.isDraw_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsDraw() {
            this.bitField0_ &= -129;
            this.isDraw_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isCpu_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isScreenBright_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isScreenDim_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBool(4, this.isButtonBright_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBool(5, this.isProximityScreenOff_);
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeBool(6, this.isStayAwake_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBool(7, this.isDoze_);
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeBool(8, this.isDraw_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isCpu_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isScreenBright_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isScreenDim_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBoolSize(4, this.isButtonBright_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBoolSize(5, this.isProximityScreenOff_);
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeBoolSize(6, this.isStayAwake_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBoolSize(7, this.isDoze_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeBoolSize(8, this.isDraw_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static ActiveWakeLocksProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ActiveWakeLocksProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ActiveWakeLocksProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static ActiveWakeLocksProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static ActiveWakeLocksProto parseFrom(InputStream input) throws IOException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveWakeLocksProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ActiveWakeLocksProto parseDelimitedFrom(InputStream input) throws IOException {
            return (ActiveWakeLocksProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveWakeLocksProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveWakeLocksProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static ActiveWakeLocksProto parseFrom(CodedInputStream input) throws IOException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static ActiveWakeLocksProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (ActiveWakeLocksProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ActiveWakeLocksProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ActiveWakeLocksProto, Builder> implements ActiveWakeLocksProtoOrBuilder {
            private Builder() {
                super(ActiveWakeLocksProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsCpu() {
                return ((ActiveWakeLocksProto) this.instance).hasIsCpu();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsCpu() {
                return ((ActiveWakeLocksProto) this.instance).getIsCpu();
            }

            public Builder setIsCpu(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsCpu(value);
                return this;
            }

            public Builder clearIsCpu() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsCpu();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsScreenBright() {
                return ((ActiveWakeLocksProto) this.instance).hasIsScreenBright();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsScreenBright() {
                return ((ActiveWakeLocksProto) this.instance).getIsScreenBright();
            }

            public Builder setIsScreenBright(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsScreenBright(value);
                return this;
            }

            public Builder clearIsScreenBright() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsScreenBright();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsScreenDim() {
                return ((ActiveWakeLocksProto) this.instance).hasIsScreenDim();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsScreenDim() {
                return ((ActiveWakeLocksProto) this.instance).getIsScreenDim();
            }

            public Builder setIsScreenDim(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsScreenDim(value);
                return this;
            }

            public Builder clearIsScreenDim() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsScreenDim();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsButtonBright() {
                return ((ActiveWakeLocksProto) this.instance).hasIsButtonBright();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsButtonBright() {
                return ((ActiveWakeLocksProto) this.instance).getIsButtonBright();
            }

            public Builder setIsButtonBright(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsButtonBright(value);
                return this;
            }

            public Builder clearIsButtonBright() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsButtonBright();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsProximityScreenOff() {
                return ((ActiveWakeLocksProto) this.instance).hasIsProximityScreenOff();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsProximityScreenOff() {
                return ((ActiveWakeLocksProto) this.instance).getIsProximityScreenOff();
            }

            public Builder setIsProximityScreenOff(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsProximityScreenOff(value);
                return this;
            }

            public Builder clearIsProximityScreenOff() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsProximityScreenOff();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsStayAwake() {
                return ((ActiveWakeLocksProto) this.instance).hasIsStayAwake();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsStayAwake() {
                return ((ActiveWakeLocksProto) this.instance).getIsStayAwake();
            }

            public Builder setIsStayAwake(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsStayAwake(value);
                return this;
            }

            public Builder clearIsStayAwake() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsStayAwake();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsDoze() {
                return ((ActiveWakeLocksProto) this.instance).hasIsDoze();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsDoze() {
                return ((ActiveWakeLocksProto) this.instance).getIsDoze();
            }

            public Builder setIsDoze(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsDoze(value);
                return this;
            }

            public Builder clearIsDoze() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsDoze();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean hasIsDraw() {
                return ((ActiveWakeLocksProto) this.instance).hasIsDraw();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.ActiveWakeLocksProtoOrBuilder
            public boolean getIsDraw() {
                return ((ActiveWakeLocksProto) this.instance).getIsDraw();
            }

            public Builder setIsDraw(boolean value) {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).setIsDraw(value);
                return this;
            }

            public Builder clearIsDraw() {
                copyOnWrite();
                ((ActiveWakeLocksProto) this.instance).clearIsDraw();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new ActiveWakeLocksProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    ActiveWakeLocksProto other = (ActiveWakeLocksProto) arg1;
                    this.isCpu_ = visitor.visitBoolean(hasIsCpu(), this.isCpu_, other.hasIsCpu(), other.isCpu_);
                    this.isScreenBright_ = visitor.visitBoolean(hasIsScreenBright(), this.isScreenBright_, other.hasIsScreenBright(), other.isScreenBright_);
                    this.isScreenDim_ = visitor.visitBoolean(hasIsScreenDim(), this.isScreenDim_, other.hasIsScreenDim(), other.isScreenDim_);
                    this.isButtonBright_ = visitor.visitBoolean(hasIsButtonBright(), this.isButtonBright_, other.hasIsButtonBright(), other.isButtonBright_);
                    this.isProximityScreenOff_ = visitor.visitBoolean(hasIsProximityScreenOff(), this.isProximityScreenOff_, other.hasIsProximityScreenOff(), other.isProximityScreenOff_);
                    this.isStayAwake_ = visitor.visitBoolean(hasIsStayAwake(), this.isStayAwake_, other.hasIsStayAwake(), other.isStayAwake_);
                    this.isDoze_ = visitor.visitBoolean(hasIsDoze(), this.isDoze_, other.hasIsDoze(), other.isDoze_);
                    this.isDraw_ = visitor.visitBoolean(hasIsDraw(), this.isDraw_, other.hasIsDraw(), other.isDraw_);
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
                                this.isCpu_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isScreenBright_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isScreenDim_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.isButtonBright_ = input.readBool();
                            } else if (tag == 40) {
                                this.bitField0_ |= 16;
                                this.isProximityScreenOff_ = input.readBool();
                            } else if (tag == 48) {
                                this.bitField0_ |= 32;
                                this.isStayAwake_ = input.readBool();
                            } else if (tag == 56) {
                                this.bitField0_ |= 64;
                                this.isDoze_ = input.readBool();
                            } else if (tag == 64) {
                                this.bitField0_ |= 128;
                                this.isDraw_ = input.readBool();
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
                        synchronized (ActiveWakeLocksProto.class) {
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

        public static ActiveWakeLocksProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ActiveWakeLocksProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UserActivityProto extends GeneratedMessageLite<UserActivityProto, Builder> implements UserActivityProtoOrBuilder {
        private static final UserActivityProto DEFAULT_INSTANCE = new UserActivityProto();
        public static final int IS_SCREEN_BRIGHT_FIELD_NUMBER = 1;
        public static final int IS_SCREEN_DIM_FIELD_NUMBER = 2;
        public static final int IS_SCREEN_DREAM_FIELD_NUMBER = 3;
        private static volatile Parser<UserActivityProto> PARSER;
        private int bitField0_;
        private boolean isScreenBright_ = false;
        private boolean isScreenDim_ = false;
        private boolean isScreenDream_ = false;

        private UserActivityProto() {
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean hasIsScreenBright() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean getIsScreenBright() {
            return this.isScreenBright_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsScreenBright(boolean value) {
            this.bitField0_ |= 1;
            this.isScreenBright_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsScreenBright() {
            this.bitField0_ &= -2;
            this.isScreenBright_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean hasIsScreenDim() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean getIsScreenDim() {
            return this.isScreenDim_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsScreenDim(boolean value) {
            this.bitField0_ |= 2;
            this.isScreenDim_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsScreenDim() {
            this.bitField0_ &= -3;
            this.isScreenDim_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean hasIsScreenDream() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
        public boolean getIsScreenDream() {
            return this.isScreenDream_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsScreenDream(boolean value) {
            this.bitField0_ |= 4;
            this.isScreenDream_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsScreenDream() {
            this.bitField0_ &= -5;
            this.isScreenDream_ = false;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeBool(1, this.isScreenBright_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBool(2, this.isScreenDim_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isScreenDream_);
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
                size2 = 0 + CodedOutputStream.computeBoolSize(1, this.isScreenBright_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBoolSize(2, this.isScreenDim_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isScreenDream_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UserActivityProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserActivityProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserActivityProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UserActivityProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UserActivityProto parseFrom(InputStream input) throws IOException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivityProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserActivityProto parseDelimitedFrom(InputStream input) throws IOException {
            return (UserActivityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivityProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivityProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UserActivityProto parseFrom(CodedInputStream input) throws IOException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UserActivityProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UserActivityProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UserActivityProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserActivityProto, Builder> implements UserActivityProtoOrBuilder {
            private Builder() {
                super(UserActivityProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean hasIsScreenBright() {
                return ((UserActivityProto) this.instance).hasIsScreenBright();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean getIsScreenBright() {
                return ((UserActivityProto) this.instance).getIsScreenBright();
            }

            public Builder setIsScreenBright(boolean value) {
                copyOnWrite();
                ((UserActivityProto) this.instance).setIsScreenBright(value);
                return this;
            }

            public Builder clearIsScreenBright() {
                copyOnWrite();
                ((UserActivityProto) this.instance).clearIsScreenBright();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean hasIsScreenDim() {
                return ((UserActivityProto) this.instance).hasIsScreenDim();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean getIsScreenDim() {
                return ((UserActivityProto) this.instance).getIsScreenDim();
            }

            public Builder setIsScreenDim(boolean value) {
                copyOnWrite();
                ((UserActivityProto) this.instance).setIsScreenDim(value);
                return this;
            }

            public Builder clearIsScreenDim() {
                copyOnWrite();
                ((UserActivityProto) this.instance).clearIsScreenDim();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean hasIsScreenDream() {
                return ((UserActivityProto) this.instance).hasIsScreenDream();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UserActivityProtoOrBuilder
            public boolean getIsScreenDream() {
                return ((UserActivityProto) this.instance).getIsScreenDream();
            }

            public Builder setIsScreenDream(boolean value) {
                copyOnWrite();
                ((UserActivityProto) this.instance).setIsScreenDream(value);
                return this;
            }

            public Builder clearIsScreenDream() {
                copyOnWrite();
                ((UserActivityProto) this.instance).clearIsScreenDream();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserActivityProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UserActivityProto other = (UserActivityProto) arg1;
                    this.isScreenBright_ = visitor.visitBoolean(hasIsScreenBright(), this.isScreenBright_, other.hasIsScreenBright(), other.isScreenBright_);
                    this.isScreenDim_ = visitor.visitBoolean(hasIsScreenDim(), this.isScreenDim_, other.hasIsScreenDim(), other.isScreenDim_);
                    this.isScreenDream_ = visitor.visitBoolean(hasIsScreenDream(), this.isScreenDream_, other.hasIsScreenDream(), other.isScreenDream_);
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
                                this.isScreenBright_ = input.readBool();
                            } else if (tag == 16) {
                                this.bitField0_ |= 2;
                                this.isScreenDim_ = input.readBool();
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isScreenDream_ = input.readBool();
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
                        synchronized (UserActivityProto.class) {
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

        public static UserActivityProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UserActivityProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class UidStateProto extends GeneratedMessageLite<UidStateProto, Builder> implements UidStateProtoOrBuilder {
        private static final UidStateProto DEFAULT_INSTANCE = new UidStateProto();
        public static final int IS_ACTIVE_FIELD_NUMBER = 3;
        public static final int NUM_WAKE_LOCKS_FIELD_NUMBER = 4;
        private static volatile Parser<UidStateProto> PARSER = null;
        public static final int PROCESS_STATE_FIELD_NUMBER = 5;
        public static final int UID_FIELD_NUMBER = 1;
        public static final int UID_STRING_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean isActive_ = false;
        private int numWakeLocks_ = 0;
        private int processState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
        private String uidString_ = "";
        private int uid_ = 0;

        private UidStateProto() {
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean hasUid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public int getUid() {
            return this.uid_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUid(int value) {
            this.bitField0_ |= 1;
            this.uid_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUid() {
            this.bitField0_ &= -2;
            this.uid_ = 0;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean hasUidString() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public String getUidString() {
            return this.uidString_;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public ByteString getUidStringBytes() {
            return ByteString.copyFromUtf8(this.uidString_);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUidString(String value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.uidString_ = value;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUidString() {
            this.bitField0_ &= -3;
            this.uidString_ = getDefaultInstance().getUidString();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUidStringBytes(ByteString value) {
            if (value != null) {
                this.bitField0_ |= 2;
                this.uidString_ = value.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean hasIsActive() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean getIsActive() {
            return this.isActive_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIsActive(boolean value) {
            this.bitField0_ |= 4;
            this.isActive_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIsActive() {
            this.bitField0_ &= -5;
            this.isActive_ = false;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean hasNumWakeLocks() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public int getNumWakeLocks() {
            return this.numWakeLocks_;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNumWakeLocks(int value) {
            this.bitField0_ |= 8;
            this.numWakeLocks_ = value;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNumWakeLocks() {
            this.bitField0_ &= -9;
            this.numWakeLocks_ = 0;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public boolean hasProcessState() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
        public ProcessStateEnum getProcessState() {
            ProcessStateEnum result = ProcessStateEnum.forNumber(this.processState_);
            return result == null ? ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO : result;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setProcessState(ProcessStateEnum value) {
            if (value != null) {
                this.bitField0_ |= 16;
                this.processState_ = value.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearProcessState() {
            this.bitField0_ &= -17;
            this.processState_ = ProcessStateEnum.PROCESS_STATE_UNKNOWN_TO_PROTO_VALUE;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeString(2, getUidString());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBool(3, this.isActive_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.numWakeLocks_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.processState_);
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
                size2 = 0 + CodedOutputStream.computeInt32Size(1, this.uid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeStringSize(2, getUidString());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBoolSize(3, this.isActive_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.numWakeLocks_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.processState_);
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static UidStateProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UidStateProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UidStateProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UidStateProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UidStateProto parseFrom(InputStream input) throws IOException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UidStateProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UidStateProto parseDelimitedFrom(InputStream input) throws IOException {
            return (UidStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UidStateProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidStateProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UidStateProto parseFrom(CodedInputStream input) throws IOException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UidStateProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UidStateProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UidStateProto prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UidStateProto, Builder> implements UidStateProtoOrBuilder {
            private Builder() {
                super(UidStateProto.DEFAULT_INSTANCE);
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean hasUid() {
                return ((UidStateProto) this.instance).hasUid();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public int getUid() {
                return ((UidStateProto) this.instance).getUid();
            }

            public Builder setUid(int value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setUid(value);
                return this;
            }

            public Builder clearUid() {
                copyOnWrite();
                ((UidStateProto) this.instance).clearUid();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean hasUidString() {
                return ((UidStateProto) this.instance).hasUidString();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public String getUidString() {
                return ((UidStateProto) this.instance).getUidString();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public ByteString getUidStringBytes() {
                return ((UidStateProto) this.instance).getUidStringBytes();
            }

            public Builder setUidString(String value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setUidString(value);
                return this;
            }

            public Builder clearUidString() {
                copyOnWrite();
                ((UidStateProto) this.instance).clearUidString();
                return this;
            }

            public Builder setUidStringBytes(ByteString value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setUidStringBytes(value);
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean hasIsActive() {
                return ((UidStateProto) this.instance).hasIsActive();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean getIsActive() {
                return ((UidStateProto) this.instance).getIsActive();
            }

            public Builder setIsActive(boolean value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setIsActive(value);
                return this;
            }

            public Builder clearIsActive() {
                copyOnWrite();
                ((UidStateProto) this.instance).clearIsActive();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean hasNumWakeLocks() {
                return ((UidStateProto) this.instance).hasNumWakeLocks();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public int getNumWakeLocks() {
                return ((UidStateProto) this.instance).getNumWakeLocks();
            }

            public Builder setNumWakeLocks(int value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setNumWakeLocks(value);
                return this;
            }

            public Builder clearNumWakeLocks() {
                copyOnWrite();
                ((UidStateProto) this.instance).clearNumWakeLocks();
                return this;
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public boolean hasProcessState() {
                return ((UidStateProto) this.instance).hasProcessState();
            }

            @Override // com.android.server.power.PowerManagerServiceDumpProto.UidStateProtoOrBuilder
            public ProcessStateEnum getProcessState() {
                return ((UidStateProto) this.instance).getProcessState();
            }

            public Builder setProcessState(ProcessStateEnum value) {
                copyOnWrite();
                ((UidStateProto) this.instance).setProcessState(value);
                return this;
            }

            public Builder clearProcessState() {
                copyOnWrite();
                ((UidStateProto) this.instance).clearProcessState();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new UidStateProto();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    UidStateProto other = (UidStateProto) arg1;
                    this.uid_ = visitor.visitInt(hasUid(), this.uid_, other.hasUid(), other.uid_);
                    this.uidString_ = visitor.visitString(hasUidString(), this.uidString_, other.hasUidString(), other.uidString_);
                    this.isActive_ = visitor.visitBoolean(hasIsActive(), this.isActive_, other.hasIsActive(), other.isActive_);
                    this.numWakeLocks_ = visitor.visitInt(hasNumWakeLocks(), this.numWakeLocks_, other.hasNumWakeLocks(), other.numWakeLocks_);
                    this.processState_ = visitor.visitInt(hasProcessState(), this.processState_, other.hasProcessState(), other.processState_);
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
                                this.uid_ = input.readInt32();
                            } else if (tag == 18) {
                                String s = input.readString();
                                this.bitField0_ |= 2;
                                this.uidString_ = s;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.isActive_ = input.readBool();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.numWakeLocks_ = input.readInt32();
                            } else if (tag == 40) {
                                int rawValue = input.readEnum();
                                if (ProcessStateEnum.forNumber(rawValue) == null) {
                                    super.mergeVarintField(5, rawValue);
                                } else {
                                    this.bitField0_ |= 16;
                                    this.processState_ = rawValue;
                                }
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
                        synchronized (UidStateProto.class) {
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

        public static UidStateProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UidStateProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasConstants() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public ConstantsProto getConstants() {
        ConstantsProto constantsProto = this.constants_;
        return constantsProto == null ? ConstantsProto.getDefaultInstance() : constantsProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConstants(ConstantsProto value) {
        if (value != null) {
            this.constants_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setConstants(ConstantsProto.Builder builderForValue) {
        this.constants_ = (ConstantsProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeConstants(ConstantsProto value) {
        ConstantsProto constantsProto = this.constants_;
        if (constantsProto == null || constantsProto == ConstantsProto.getDefaultInstance()) {
            this.constants_ = value;
        } else {
            this.constants_ = (ConstantsProto) ((ConstantsProto.Builder) ConstantsProto.newBuilder(this.constants_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearConstants() {
        this.constants_ = null;
        this.bitField0_ &= -2;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasDirty() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getDirty() {
        return this.dirty_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDirty(int value) {
        this.bitField0_ |= 2;
        this.dirty_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDirty() {
        this.bitField0_ &= -3;
        this.dirty_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasWakefulness() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public PowerManagerInternalProto.Wakefulness getWakefulness() {
        PowerManagerInternalProto.Wakefulness result = PowerManagerInternalProto.Wakefulness.forNumber(this.wakefulness_);
        return result == null ? PowerManagerInternalProto.Wakefulness.WAKEFULNESS_ASLEEP : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakefulness(PowerManagerInternalProto.Wakefulness value) {
        if (value != null) {
            this.bitField0_ |= 4;
            this.wakefulness_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakefulness() {
        this.bitField0_ &= -5;
        this.wakefulness_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsWakefulnessChanging() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsWakefulnessChanging() {
        return this.isWakefulnessChanging_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsWakefulnessChanging(boolean value) {
        this.bitField0_ |= 8;
        this.isWakefulnessChanging_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsWakefulnessChanging() {
        this.bitField0_ &= -9;
        this.isWakefulnessChanging_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsPowered() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsPowered() {
        return this.isPowered_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsPowered(boolean value) {
        this.bitField0_ |= 16;
        this.isPowered_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsPowered() {
        this.bitField0_ &= -17;
        this.isPowered_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasPlugType() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public BatteryPluggedStateEnum getPlugType() {
        BatteryPluggedStateEnum result = BatteryPluggedStateEnum.forNumber(this.plugType_);
        return result == null ? BatteryPluggedStateEnum.BATTERY_PLUGGED_NONE : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPlugType(BatteryPluggedStateEnum value) {
        if (value != null) {
            this.bitField0_ |= 32;
            this.plugType_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPlugType() {
        this.bitField0_ &= -33;
        this.plugType_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasBatteryLevel() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getBatteryLevel() {
        return this.batteryLevel_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevel(int value) {
        this.bitField0_ |= 64;
        this.batteryLevel_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryLevel() {
        this.bitField0_ &= -65;
        this.batteryLevel_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasBatteryLevelWhenDreamStarted() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getBatteryLevelWhenDreamStarted() {
        return this.batteryLevelWhenDreamStarted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatteryLevelWhenDreamStarted(int value) {
        this.bitField0_ |= 128;
        this.batteryLevelWhenDreamStarted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatteryLevelWhenDreamStarted() {
        this.bitField0_ &= -129;
        this.batteryLevelWhenDreamStarted_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasDockState() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public IntentProto.DockState getDockState() {
        IntentProto.DockState result = IntentProto.DockState.forNumber(this.dockState_);
        return result == null ? IntentProto.DockState.DOCK_STATE_UNDOCKED : result;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDockState(IntentProto.DockState value) {
        if (value != null) {
            this.bitField0_ |= 256;
            this.dockState_ = value.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDockState() {
        this.bitField0_ &= -257;
        this.dockState_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsStayOn() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsStayOn() {
        return this.isStayOn_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsStayOn(boolean value) {
        this.bitField0_ |= 512;
        this.isStayOn_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsStayOn() {
        this.bitField0_ &= -513;
        this.isStayOn_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsProximityPositive() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsProximityPositive() {
        return this.isProximityPositive_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsProximityPositive(boolean value) {
        this.bitField0_ |= 1024;
        this.isProximityPositive_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsProximityPositive() {
        this.bitField0_ &= -1025;
        this.isProximityPositive_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsBootCompleted() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsBootCompleted() {
        return this.isBootCompleted_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBootCompleted(boolean value) {
        this.bitField0_ |= 2048;
        this.isBootCompleted_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBootCompleted() {
        this.bitField0_ &= -2049;
        this.isBootCompleted_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsSystemReady() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsSystemReady() {
        return this.isSystemReady_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSystemReady(boolean value) {
        this.bitField0_ |= 4096;
        this.isSystemReady_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSystemReady() {
        this.bitField0_ &= -4097;
        this.isSystemReady_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsHalAutoSuspendModeEnabled() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsHalAutoSuspendModeEnabled() {
        return this.isHalAutoSuspendModeEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHalAutoSuspendModeEnabled(boolean value) {
        this.bitField0_ |= 8192;
        this.isHalAutoSuspendModeEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHalAutoSuspendModeEnabled() {
        this.bitField0_ &= -8193;
        this.isHalAutoSuspendModeEnabled_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsHalAutoInteractiveModeEnabled() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsHalAutoInteractiveModeEnabled() {
        return this.isHalAutoInteractiveModeEnabled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHalAutoInteractiveModeEnabled(boolean value) {
        this.bitField0_ |= 16384;
        this.isHalAutoInteractiveModeEnabled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHalAutoInteractiveModeEnabled() {
        this.bitField0_ &= -16385;
        this.isHalAutoInteractiveModeEnabled_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasActiveWakeLocks() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public ActiveWakeLocksProto getActiveWakeLocks() {
        ActiveWakeLocksProto activeWakeLocksProto = this.activeWakeLocks_;
        return activeWakeLocksProto == null ? ActiveWakeLocksProto.getDefaultInstance() : activeWakeLocksProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveWakeLocks(ActiveWakeLocksProto value) {
        if (value != null) {
            this.activeWakeLocks_ = value;
            this.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setActiveWakeLocks(ActiveWakeLocksProto.Builder builderForValue) {
        this.activeWakeLocks_ = (ActiveWakeLocksProto) builderForValue.build();
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeActiveWakeLocks(ActiveWakeLocksProto value) {
        ActiveWakeLocksProto activeWakeLocksProto = this.activeWakeLocks_;
        if (activeWakeLocksProto == null || activeWakeLocksProto == ActiveWakeLocksProto.getDefaultInstance()) {
            this.activeWakeLocks_ = value;
        } else {
            this.activeWakeLocks_ = (ActiveWakeLocksProto) ((ActiveWakeLocksProto.Builder) ActiveWakeLocksProto.newBuilder(this.activeWakeLocks_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActiveWakeLocks() {
        this.activeWakeLocks_ = null;
        this.bitField0_ &= -32769;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasNotifyLongScheduledMs() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getNotifyLongScheduledMs() {
        return this.notifyLongScheduledMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotifyLongScheduledMs(long value) {
        this.bitField0_ |= 65536;
        this.notifyLongScheduledMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotifyLongScheduledMs() {
        this.bitField0_ &= -65537;
        this.notifyLongScheduledMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasNotifyLongDispatchedMs() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getNotifyLongDispatchedMs() {
        return this.notifyLongDispatchedMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotifyLongDispatchedMs(long value) {
        this.bitField0_ |= 131072;
        this.notifyLongDispatchedMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotifyLongDispatchedMs() {
        this.bitField0_ &= -131073;
        this.notifyLongDispatchedMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasNotifyLongNextCheckMs() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getNotifyLongNextCheckMs() {
        return this.notifyLongNextCheckMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotifyLongNextCheckMs(long value) {
        this.bitField0_ |= 262144;
        this.notifyLongNextCheckMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotifyLongNextCheckMs() {
        this.bitField0_ &= -262145;
        this.notifyLongNextCheckMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasUserActivity() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public UserActivityProto getUserActivity() {
        UserActivityProto userActivityProto = this.userActivity_;
        return userActivityProto == null ? UserActivityProto.getDefaultInstance() : userActivityProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivity(UserActivityProto value) {
        if (value != null) {
            this.userActivity_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserActivity(UserActivityProto.Builder builderForValue) {
        this.userActivity_ = (UserActivityProto) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeUserActivity(UserActivityProto value) {
        UserActivityProto userActivityProto = this.userActivity_;
        if (userActivityProto == null || userActivityProto == UserActivityProto.getDefaultInstance()) {
            this.userActivity_ = value;
        } else {
            this.userActivity_ = (UserActivityProto) ((UserActivityProto.Builder) UserActivityProto.newBuilder(this.userActivity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUserActivity() {
        this.userActivity_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsRequestWaitForNegativeProximity() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsRequestWaitForNegativeProximity() {
        return this.isRequestWaitForNegativeProximity_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsRequestWaitForNegativeProximity(boolean value) {
        this.bitField0_ |= 1048576;
        this.isRequestWaitForNegativeProximity_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsRequestWaitForNegativeProximity() {
        this.bitField0_ &= -1048577;
        this.isRequestWaitForNegativeProximity_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsSandmanScheduled() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsSandmanScheduled() {
        return this.isSandmanScheduled_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSandmanScheduled(boolean value) {
        this.bitField0_ |= 2097152;
        this.isSandmanScheduled_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSandmanScheduled() {
        this.bitField0_ &= -2097153;
        this.isSandmanScheduled_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsSandmanSummoned() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsSandmanSummoned() {
        return this.isSandmanSummoned_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsSandmanSummoned(boolean value) {
        this.bitField0_ |= 4194304;
        this.isSandmanSummoned_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsSandmanSummoned() {
        this.bitField0_ &= -4194305;
        this.isSandmanSummoned_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsBatteryLevelLow() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsBatteryLevelLow() {
        return this.isBatteryLevelLow_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsBatteryLevelLow(boolean value) {
        this.bitField0_ |= 8388608;
        this.isBatteryLevelLow_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsBatteryLevelLow() {
        this.bitField0_ &= -8388609;
        this.isBatteryLevelLow_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsLightDeviceIdleMode() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsLightDeviceIdleMode() {
        return this.isLightDeviceIdleMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsLightDeviceIdleMode(boolean value) {
        this.bitField0_ |= 16777216;
        this.isLightDeviceIdleMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsLightDeviceIdleMode() {
        this.bitField0_ &= -16777217;
        this.isLightDeviceIdleMode_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsDeviceIdleMode() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsDeviceIdleMode() {
        return this.isDeviceIdleMode_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDeviceIdleMode(boolean value) {
        this.bitField0_ |= 33554432;
        this.isDeviceIdleMode_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDeviceIdleMode() {
        this.bitField0_ &= -33554433;
        this.isDeviceIdleMode_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public List<Integer> getDeviceIdleWhitelistList() {
        return this.deviceIdleWhitelist_;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleWhitelistCount() {
        return this.deviceIdleWhitelist_.size();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleWhitelist(int index) {
        return this.deviceIdleWhitelist_.getInt(index);
    }

    private void ensureDeviceIdleWhitelistIsMutable() {
        if (!this.deviceIdleWhitelist_.isModifiable()) {
            this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdleWhitelist(int index, int value) {
        ensureDeviceIdleWhitelistIsMutable();
        this.deviceIdleWhitelist_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceIdleWhitelist(int value) {
        ensureDeviceIdleWhitelistIsMutable();
        this.deviceIdleWhitelist_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceIdleWhitelist(Iterable<? extends Integer> values) {
        ensureDeviceIdleWhitelistIsMutable();
        AbstractMessageLite.addAll(values, this.deviceIdleWhitelist_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdleWhitelist() {
        this.deviceIdleWhitelist_ = emptyIntList();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public List<Integer> getDeviceIdleTempWhitelistList() {
        return this.deviceIdleTempWhitelist_;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleTempWhitelistCount() {
        return this.deviceIdleTempWhitelist_.size();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getDeviceIdleTempWhitelist(int index) {
        return this.deviceIdleTempWhitelist_.getInt(index);
    }

    private void ensureDeviceIdleTempWhitelistIsMutable() {
        if (!this.deviceIdleTempWhitelist_.isModifiable()) {
            this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeviceIdleTempWhitelist(int index, int value) {
        ensureDeviceIdleTempWhitelistIsMutable();
        this.deviceIdleTempWhitelist_.setInt(index, value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDeviceIdleTempWhitelist(int value) {
        ensureDeviceIdleTempWhitelistIsMutable();
        this.deviceIdleTempWhitelist_.addInt(value);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllDeviceIdleTempWhitelist(Iterable<? extends Integer> values) {
        ensureDeviceIdleTempWhitelistIsMutable();
        AbstractMessageLite.addAll(values, this.deviceIdleTempWhitelist_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeviceIdleTempWhitelist() {
        this.deviceIdleTempWhitelist_ = emptyIntList();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastWakeTimeMs() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastWakeTimeMs() {
        return this.lastWakeTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastWakeTimeMs(long value) {
        this.bitField0_ |= 67108864;
        this.lastWakeTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastWakeTimeMs() {
        this.bitField0_ &= -67108865;
        this.lastWakeTimeMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastSleepTimeMs() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastSleepTimeMs() {
        return this.lastSleepTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastSleepTimeMs(long value) {
        this.bitField0_ |= 134217728;
        this.lastSleepTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastSleepTimeMs() {
        this.bitField0_ &= -134217729;
        this.lastSleepTimeMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastUserActivityTimeMs() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastUserActivityTimeMs() {
        return this.lastUserActivityTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastUserActivityTimeMs(long value) {
        this.bitField0_ |= 268435456;
        this.lastUserActivityTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastUserActivityTimeMs() {
        this.bitField0_ &= -268435457;
        this.lastUserActivityTimeMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastUserActivityTimeNoChangeLightsMs() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastUserActivityTimeNoChangeLightsMs() {
        return this.lastUserActivityTimeNoChangeLightsMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastUserActivityTimeNoChangeLightsMs(long value) {
        this.bitField0_ |= 536870912;
        this.lastUserActivityTimeNoChangeLightsMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastUserActivityTimeNoChangeLightsMs() {
        this.bitField0_ &= -536870913;
        this.lastUserActivityTimeNoChangeLightsMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastInteractivePowerHintTimeMs() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastInteractivePowerHintTimeMs() {
        return this.lastInteractivePowerHintTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastInteractivePowerHintTimeMs(long value) {
        this.bitField0_ |= 1073741824;
        this.lastInteractivePowerHintTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastInteractivePowerHintTimeMs() {
        this.bitField0_ &= -1073741825;
        this.lastInteractivePowerHintTimeMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLastScreenBrightnessBoostTimeMs() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public long getLastScreenBrightnessBoostTimeMs() {
        return this.lastScreenBrightnessBoostTimeMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLastScreenBrightnessBoostTimeMs(long value) {
        this.bitField0_ |= Integer.MIN_VALUE;
        this.lastScreenBrightnessBoostTimeMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLastScreenBrightnessBoostTimeMs() {
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
        this.lastScreenBrightnessBoostTimeMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsScreenBrightnessBoostInProgress() {
        return (this.bitField1_ & 1) == 1;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsScreenBrightnessBoostInProgress() {
        return this.isScreenBrightnessBoostInProgress_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsScreenBrightnessBoostInProgress(boolean value) {
        this.bitField1_ |= 1;
        this.isScreenBrightnessBoostInProgress_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsScreenBrightnessBoostInProgress() {
        this.bitField1_ &= -2;
        this.isScreenBrightnessBoostInProgress_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsDisplayReady() {
        return (this.bitField1_ & 2) == 2;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsDisplayReady() {
        return this.isDisplayReady_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsDisplayReady(boolean value) {
        this.bitField1_ |= 2;
        this.isDisplayReady_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsDisplayReady() {
        this.bitField1_ &= -3;
        this.isDisplayReady_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsHoldingWakeLockSuspendBlocker() {
        return (this.bitField1_ & 4) == 4;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsHoldingWakeLockSuspendBlocker() {
        return this.isHoldingWakeLockSuspendBlocker_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHoldingWakeLockSuspendBlocker(boolean value) {
        this.bitField1_ |= 4;
        this.isHoldingWakeLockSuspendBlocker_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHoldingWakeLockSuspendBlocker() {
        this.bitField1_ &= -5;
        this.isHoldingWakeLockSuspendBlocker_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasIsHoldingDisplaySuspendBlocker() {
        return (this.bitField1_ & 8) == 8;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getIsHoldingDisplaySuspendBlocker() {
        return this.isHoldingDisplaySuspendBlocker_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsHoldingDisplaySuspendBlocker(boolean value) {
        this.bitField1_ |= 8;
        this.isHoldingDisplaySuspendBlocker_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearIsHoldingDisplaySuspendBlocker() {
        this.bitField1_ &= -9;
        this.isHoldingDisplaySuspendBlocker_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasSettingsAndConfiguration() {
        return (this.bitField1_ & 16) == 16;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public PowerServiceSettingsAndConfigurationDumpProto getSettingsAndConfiguration() {
        PowerServiceSettingsAndConfigurationDumpProto powerServiceSettingsAndConfigurationDumpProto = this.settingsAndConfiguration_;
        return powerServiceSettingsAndConfigurationDumpProto == null ? PowerServiceSettingsAndConfigurationDumpProto.getDefaultInstance() : powerServiceSettingsAndConfigurationDumpProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto value) {
        if (value != null) {
            this.settingsAndConfiguration_ = value;
            this.bitField1_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto.Builder builderForValue) {
        this.settingsAndConfiguration_ = (PowerServiceSettingsAndConfigurationDumpProto) builderForValue.build();
        this.bitField1_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto value) {
        PowerServiceSettingsAndConfigurationDumpProto powerServiceSettingsAndConfigurationDumpProto = this.settingsAndConfiguration_;
        if (powerServiceSettingsAndConfigurationDumpProto == null || powerServiceSettingsAndConfigurationDumpProto == PowerServiceSettingsAndConfigurationDumpProto.getDefaultInstance()) {
            this.settingsAndConfiguration_ = value;
        } else {
            this.settingsAndConfiguration_ = (PowerServiceSettingsAndConfigurationDumpProto) ((PowerServiceSettingsAndConfigurationDumpProto.Builder) PowerServiceSettingsAndConfigurationDumpProto.newBuilder(this.settingsAndConfiguration_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSettingsAndConfiguration() {
        this.settingsAndConfiguration_ = null;
        this.bitField1_ &= -17;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasSleepTimeoutMs() {
        return (this.bitField1_ & 32) == 32;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getSleepTimeoutMs() {
        return this.sleepTimeoutMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSleepTimeoutMs(int value) {
        this.bitField1_ |= 32;
        this.sleepTimeoutMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSleepTimeoutMs() {
        this.bitField1_ &= -33;
        this.sleepTimeoutMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasScreenOffTimeoutMs() {
        return (this.bitField1_ & 64) == 64;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getScreenOffTimeoutMs() {
        return this.screenOffTimeoutMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenOffTimeoutMs(int value) {
        this.bitField1_ |= 64;
        this.screenOffTimeoutMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenOffTimeoutMs() {
        this.bitField1_ &= -65;
        this.screenOffTimeoutMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasScreenDimDurationMs() {
        return (this.bitField1_ & 128) == 128;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getScreenDimDurationMs() {
        return this.screenDimDurationMs_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreenDimDurationMs(int value) {
        this.bitField1_ |= 128;
        this.screenDimDurationMs_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreenDimDurationMs() {
        this.bitField1_ &= -129;
        this.screenDimDurationMs_ = 0;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasAreUidsChanging() {
        return (this.bitField1_ & 256) == 256;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getAreUidsChanging() {
        return this.areUidsChanging_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreUidsChanging(boolean value) {
        this.bitField1_ |= 256;
        this.areUidsChanging_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreUidsChanging() {
        this.bitField1_ &= -257;
        this.areUidsChanging_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasAreUidsChanged() {
        return (this.bitField1_ & 512) == 512;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean getAreUidsChanged() {
        return this.areUidsChanged_;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAreUidsChanged(boolean value) {
        this.bitField1_ |= 512;
        this.areUidsChanged_ = value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAreUidsChanged() {
        this.bitField1_ &= -513;
        this.areUidsChanged_ = false;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public List<UidStateProto> getUidStatesList() {
        return this.uidStates_;
    }

    public List<? extends UidStateProtoOrBuilder> getUidStatesOrBuilderList() {
        return this.uidStates_;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getUidStatesCount() {
        return this.uidStates_.size();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public UidStateProto getUidStates(int index) {
        return this.uidStates_.get(index);
    }

    public UidStateProtoOrBuilder getUidStatesOrBuilder(int index) {
        return this.uidStates_.get(index);
    }

    private void ensureUidStatesIsMutable() {
        if (!this.uidStates_.isModifiable()) {
            this.uidStates_ = GeneratedMessageLite.mutableCopy(this.uidStates_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidStates(int index, UidStateProto value) {
        if (value != null) {
            ensureUidStatesIsMutable();
            this.uidStates_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUidStates(int index, UidStateProto.Builder builderForValue) {
        ensureUidStatesIsMutable();
        this.uidStates_.set(index, (UidStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidStates(UidStateProto value) {
        if (value != null) {
            ensureUidStatesIsMutable();
            this.uidStates_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidStates(int index, UidStateProto value) {
        if (value != null) {
            ensureUidStatesIsMutable();
            this.uidStates_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidStates(UidStateProto.Builder builderForValue) {
        ensureUidStatesIsMutable();
        this.uidStates_.add((UidStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addUidStates(int index, UidStateProto.Builder builderForValue) {
        ensureUidStatesIsMutable();
        this.uidStates_.add(index, (UidStateProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllUidStates(Iterable<? extends UidStateProto> values) {
        ensureUidStatesIsMutable();
        AbstractMessageLite.addAll(values, this.uidStates_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearUidStates() {
        this.uidStates_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeUidStates(int index) {
        ensureUidStatesIsMutable();
        this.uidStates_.remove(index);
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasLooper() {
        return (this.bitField1_ & 1024) == 1024;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public LooperProto getLooper() {
        LooperProto looperProto = this.looper_;
        return looperProto == null ? LooperProto.getDefaultInstance() : looperProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLooper(LooperProto value) {
        if (value != null) {
            this.looper_ = value;
            this.bitField1_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLooper(LooperProto.Builder builderForValue) {
        this.looper_ = (LooperProto) builderForValue.build();
        this.bitField1_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLooper(LooperProto value) {
        LooperProto looperProto = this.looper_;
        if (looperProto == null || looperProto == LooperProto.getDefaultInstance()) {
            this.looper_ = value;
        } else {
            this.looper_ = (LooperProto) ((LooperProto.Builder) LooperProto.newBuilder(this.looper_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLooper() {
        this.looper_ = null;
        this.bitField1_ &= -1025;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public List<WakeLockProto> getWakeLocksList() {
        return this.wakeLocks_;
    }

    public List<? extends WakeLockProtoOrBuilder> getWakeLocksOrBuilderList() {
        return this.wakeLocks_;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getWakeLocksCount() {
        return this.wakeLocks_.size();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public WakeLockProto getWakeLocks(int index) {
        return this.wakeLocks_.get(index);
    }

    public WakeLockProtoOrBuilder getWakeLocksOrBuilder(int index) {
        return this.wakeLocks_.get(index);
    }

    private void ensureWakeLocksIsMutable() {
        if (!this.wakeLocks_.isModifiable()) {
            this.wakeLocks_ = GeneratedMessageLite.mutableCopy(this.wakeLocks_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeLocks(int index, WakeLockProto value) {
        if (value != null) {
            ensureWakeLocksIsMutable();
            this.wakeLocks_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWakeLocks(int index, WakeLockProto.Builder builderForValue) {
        ensureWakeLocksIsMutable();
        this.wakeLocks_.set(index, (WakeLockProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeLocks(WakeLockProto value) {
        if (value != null) {
            ensureWakeLocksIsMutable();
            this.wakeLocks_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeLocks(int index, WakeLockProto value) {
        if (value != null) {
            ensureWakeLocksIsMutable();
            this.wakeLocks_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeLocks(WakeLockProto.Builder builderForValue) {
        ensureWakeLocksIsMutable();
        this.wakeLocks_.add((WakeLockProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addWakeLocks(int index, WakeLockProto.Builder builderForValue) {
        ensureWakeLocksIsMutable();
        this.wakeLocks_.add(index, (WakeLockProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllWakeLocks(Iterable<? extends WakeLockProto> values) {
        ensureWakeLocksIsMutable();
        AbstractMessageLite.addAll(values, this.wakeLocks_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWakeLocks() {
        this.wakeLocks_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeWakeLocks(int index) {
        ensureWakeLocksIsMutable();
        this.wakeLocks_.remove(index);
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public List<SuspendBlockerProto> getSuspendBlockersList() {
        return this.suspendBlockers_;
    }

    public List<? extends SuspendBlockerProtoOrBuilder> getSuspendBlockersOrBuilderList() {
        return this.suspendBlockers_;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public int getSuspendBlockersCount() {
        return this.suspendBlockers_.size();
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public SuspendBlockerProto getSuspendBlockers(int index) {
        return this.suspendBlockers_.get(index);
    }

    public SuspendBlockerProtoOrBuilder getSuspendBlockersOrBuilder(int index) {
        return this.suspendBlockers_.get(index);
    }

    private void ensureSuspendBlockersIsMutable() {
        if (!this.suspendBlockers_.isModifiable()) {
            this.suspendBlockers_ = GeneratedMessageLite.mutableCopy(this.suspendBlockers_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuspendBlockers(int index, SuspendBlockerProto value) {
        if (value != null) {
            ensureSuspendBlockersIsMutable();
            this.suspendBlockers_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSuspendBlockers(int index, SuspendBlockerProto.Builder builderForValue) {
        ensureSuspendBlockersIsMutable();
        this.suspendBlockers_.set(index, (SuspendBlockerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuspendBlockers(SuspendBlockerProto value) {
        if (value != null) {
            ensureSuspendBlockersIsMutable();
            this.suspendBlockers_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuspendBlockers(int index, SuspendBlockerProto value) {
        if (value != null) {
            ensureSuspendBlockersIsMutable();
            this.suspendBlockers_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuspendBlockers(SuspendBlockerProto.Builder builderForValue) {
        ensureSuspendBlockersIsMutable();
        this.suspendBlockers_.add((SuspendBlockerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addSuspendBlockers(int index, SuspendBlockerProto.Builder builderForValue) {
        ensureSuspendBlockersIsMutable();
        this.suspendBlockers_.add(index, (SuspendBlockerProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllSuspendBlockers(Iterable<? extends SuspendBlockerProto> values) {
        ensureSuspendBlockersIsMutable();
        AbstractMessageLite.addAll(values, this.suspendBlockers_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSuspendBlockers() {
        this.suspendBlockers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeSuspendBlockers(int index) {
        ensureSuspendBlockersIsMutable();
        this.suspendBlockers_.remove(index);
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasWirelessChargerDetector() {
        return (this.bitField1_ & 2048) == 2048;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public WirelessChargerDetectorProto getWirelessChargerDetector() {
        WirelessChargerDetectorProto wirelessChargerDetectorProto = this.wirelessChargerDetector_;
        return wirelessChargerDetectorProto == null ? WirelessChargerDetectorProto.getDefaultInstance() : wirelessChargerDetectorProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWirelessChargerDetector(WirelessChargerDetectorProto value) {
        if (value != null) {
            this.wirelessChargerDetector_ = value;
            this.bitField1_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWirelessChargerDetector(WirelessChargerDetectorProto.Builder builderForValue) {
        this.wirelessChargerDetector_ = (WirelessChargerDetectorProto) builderForValue.build();
        this.bitField1_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWirelessChargerDetector(WirelessChargerDetectorProto value) {
        WirelessChargerDetectorProto wirelessChargerDetectorProto = this.wirelessChargerDetector_;
        if (wirelessChargerDetectorProto == null || wirelessChargerDetectorProto == WirelessChargerDetectorProto.getDefaultInstance()) {
            this.wirelessChargerDetector_ = value;
        } else {
            this.wirelessChargerDetector_ = (WirelessChargerDetectorProto) ((WirelessChargerDetectorProto.Builder) WirelessChargerDetectorProto.newBuilder(this.wirelessChargerDetector_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWirelessChargerDetector() {
        this.wirelessChargerDetector_ = null;
        this.bitField1_ &= -2049;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public boolean hasBatterySaverStateMachine() {
        return (this.bitField1_ & 4096) == 4096;
    }

    @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
    public BatterySaverStateMachineProto getBatterySaverStateMachine() {
        BatterySaverStateMachineProto batterySaverStateMachineProto = this.batterySaverStateMachine_;
        return batterySaverStateMachineProto == null ? BatterySaverStateMachineProto.getDefaultInstance() : batterySaverStateMachineProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterySaverStateMachine(BatterySaverStateMachineProto value) {
        if (value != null) {
            this.batterySaverStateMachine_ = value;
            this.bitField1_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBatterySaverStateMachine(BatterySaverStateMachineProto.Builder builderForValue) {
        this.batterySaverStateMachine_ = (BatterySaverStateMachineProto) builderForValue.build();
        this.bitField1_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBatterySaverStateMachine(BatterySaverStateMachineProto value) {
        BatterySaverStateMachineProto batterySaverStateMachineProto = this.batterySaverStateMachine_;
        if (batterySaverStateMachineProto == null || batterySaverStateMachineProto == BatterySaverStateMachineProto.getDefaultInstance()) {
            this.batterySaverStateMachine_ = value;
        } else {
            this.batterySaverStateMachine_ = (BatterySaverStateMachineProto) ((BatterySaverStateMachineProto.Builder) BatterySaverStateMachineProto.newBuilder(this.batterySaverStateMachine_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField1_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBatterySaverStateMachine() {
        this.batterySaverStateMachine_ = null;
        this.bitField1_ &= -4097;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(1, getConstants());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeInt32(2, this.dirty_);
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeEnum(3, this.wakefulness_);
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeBool(4, this.isWakefulnessChanging_);
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeBool(5, this.isPowered_);
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeEnum(6, this.plugType_);
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeInt32(7, this.batteryLevel_);
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeInt32(8, this.batteryLevelWhenDreamStarted_);
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeEnum(9, this.dockState_);
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeBool(10, this.isStayOn_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeBool(11, this.isProximityPositive_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeBool(12, this.isBootCompleted_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeBool(13, this.isSystemReady_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeBool(14, this.isHalAutoSuspendModeEnabled_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeBool(15, this.isHalAutoInteractiveModeEnabled_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeMessage(16, getActiveWakeLocks());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeInt64(17, this.notifyLongScheduledMs_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeInt64(18, this.notifyLongDispatchedMs_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeInt64(19, this.notifyLongNextCheckMs_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(20, getUserActivity());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeBool(21, this.isRequestWaitForNegativeProximity_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeBool(22, this.isSandmanScheduled_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeBool(23, this.isSandmanSummoned_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeBool(24, this.isBatteryLevelLow_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeBool(25, this.isLightDeviceIdleMode_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeBool(26, this.isDeviceIdleMode_);
        }
        for (int i = 0; i < this.deviceIdleWhitelist_.size(); i++) {
            output.writeInt32(27, this.deviceIdleWhitelist_.getInt(i));
        }
        for (int i2 = 0; i2 < this.deviceIdleTempWhitelist_.size(); i2++) {
            output.writeInt32(28, this.deviceIdleTempWhitelist_.getInt(i2));
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeInt64(29, this.lastWakeTimeMs_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeInt64(30, this.lastSleepTimeMs_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeInt64(31, this.lastUserActivityTimeMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeInt64(32, this.lastUserActivityTimeNoChangeLightsMs_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeInt64(33, this.lastInteractivePowerHintTimeMs_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeInt64(34, this.lastScreenBrightnessBoostTimeMs_);
        }
        if ((this.bitField1_ & 1) == 1) {
            output.writeBool(35, this.isScreenBrightnessBoostInProgress_);
        }
        if ((this.bitField1_ & 2) == 2) {
            output.writeBool(36, this.isDisplayReady_);
        }
        if ((this.bitField1_ & 4) == 4) {
            output.writeBool(37, this.isHoldingWakeLockSuspendBlocker_);
        }
        if ((this.bitField1_ & 8) == 8) {
            output.writeBool(38, this.isHoldingDisplaySuspendBlocker_);
        }
        if ((this.bitField1_ & 16) == 16) {
            output.writeMessage(39, getSettingsAndConfiguration());
        }
        if ((this.bitField1_ & 32) == 32) {
            output.writeSInt32(40, this.sleepTimeoutMs_);
        }
        if ((this.bitField1_ & 64) == 64) {
            output.writeInt32(41, this.screenOffTimeoutMs_);
        }
        if ((this.bitField1_ & 128) == 128) {
            output.writeInt32(42, this.screenDimDurationMs_);
        }
        if ((this.bitField1_ & 256) == 256) {
            output.writeBool(43, this.areUidsChanging_);
        }
        if ((this.bitField1_ & 512) == 512) {
            output.writeBool(44, this.areUidsChanged_);
        }
        for (int i3 = 0; i3 < this.uidStates_.size(); i3++) {
            output.writeMessage(45, this.uidStates_.get(i3));
        }
        if ((this.bitField1_ & 1024) == 1024) {
            output.writeMessage(46, getLooper());
        }
        for (int i4 = 0; i4 < this.wakeLocks_.size(); i4++) {
            output.writeMessage(47, this.wakeLocks_.get(i4));
        }
        for (int i5 = 0; i5 < this.suspendBlockers_.size(); i5++) {
            output.writeMessage(48, this.suspendBlockers_.get(i5));
        }
        if ((this.bitField1_ & 2048) == 2048) {
            output.writeMessage(49, getWirelessChargerDetector());
        }
        if ((this.bitField1_ & 4096) == 4096) {
            output.writeMessage(50, getBatterySaverStateMachine());
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
            size2 = 0 + CodedOutputStream.computeMessageSize(1, getConstants());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeInt32Size(2, this.dirty_);
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeEnumSize(3, this.wakefulness_);
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeBoolSize(4, this.isWakefulnessChanging_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeBoolSize(5, this.isPowered_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeEnumSize(6, this.plugType_);
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeInt32Size(7, this.batteryLevel_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeInt32Size(8, this.batteryLevelWhenDreamStarted_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeEnumSize(9, this.dockState_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeBoolSize(10, this.isStayOn_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeBoolSize(11, this.isProximityPositive_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeBoolSize(12, this.isBootCompleted_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeBoolSize(13, this.isSystemReady_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeBoolSize(14, this.isHalAutoSuspendModeEnabled_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeBoolSize(15, this.isHalAutoInteractiveModeEnabled_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(16, getActiveWakeLocks());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeInt64Size(17, this.notifyLongScheduledMs_);
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeInt64Size(18, this.notifyLongDispatchedMs_);
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeInt64Size(19, this.notifyLongNextCheckMs_);
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeMessageSize(20, getUserActivity());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeBoolSize(21, this.isRequestWaitForNegativeProximity_);
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeBoolSize(22, this.isSandmanScheduled_);
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeBoolSize(23, this.isSandmanSummoned_);
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeBoolSize(24, this.isBatteryLevelLow_);
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeBoolSize(25, this.isLightDeviceIdleMode_);
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeBoolSize(26, this.isDeviceIdleMode_);
        }
        int dataSize = 0;
        for (int i = 0; i < this.deviceIdleWhitelist_.size(); i++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.deviceIdleWhitelist_.getInt(i));
        }
        int size3 = size2 + dataSize + (getDeviceIdleWhitelistList().size() * 2);
        int dataSize2 = 0;
        for (int i2 = 0; i2 < this.deviceIdleTempWhitelist_.size(); i2++) {
            dataSize2 += CodedOutputStream.computeInt32SizeNoTag(this.deviceIdleTempWhitelist_.getInt(i2));
        }
        int size4 = size3 + dataSize2 + (getDeviceIdleTempWhitelistList().size() * 2);
        if ((this.bitField0_ & 67108864) == 67108864) {
            size4 += CodedOutputStream.computeInt64Size(29, this.lastWakeTimeMs_);
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size4 += CodedOutputStream.computeInt64Size(30, this.lastSleepTimeMs_);
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size4 += CodedOutputStream.computeInt64Size(31, this.lastUserActivityTimeMs_);
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size4 += CodedOutputStream.computeInt64Size(32, this.lastUserActivityTimeNoChangeLightsMs_);
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size4 += CodedOutputStream.computeInt64Size(33, this.lastInteractivePowerHintTimeMs_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size4 += CodedOutputStream.computeInt64Size(34, this.lastScreenBrightnessBoostTimeMs_);
        }
        if ((this.bitField1_ & 1) == 1) {
            size4 += CodedOutputStream.computeBoolSize(35, this.isScreenBrightnessBoostInProgress_);
        }
        if ((this.bitField1_ & 2) == 2) {
            size4 += CodedOutputStream.computeBoolSize(36, this.isDisplayReady_);
        }
        if ((this.bitField1_ & 4) == 4) {
            size4 += CodedOutputStream.computeBoolSize(37, this.isHoldingWakeLockSuspendBlocker_);
        }
        if ((this.bitField1_ & 8) == 8) {
            size4 += CodedOutputStream.computeBoolSize(38, this.isHoldingDisplaySuspendBlocker_);
        }
        if ((this.bitField1_ & 16) == 16) {
            size4 += CodedOutputStream.computeMessageSize(39, getSettingsAndConfiguration());
        }
        if ((this.bitField1_ & 32) == 32) {
            size4 += CodedOutputStream.computeSInt32Size(40, this.sleepTimeoutMs_);
        }
        if ((this.bitField1_ & 64) == 64) {
            size4 += CodedOutputStream.computeInt32Size(41, this.screenOffTimeoutMs_);
        }
        if ((this.bitField1_ & 128) == 128) {
            size4 += CodedOutputStream.computeInt32Size(42, this.screenDimDurationMs_);
        }
        if ((this.bitField1_ & 256) == 256) {
            size4 += CodedOutputStream.computeBoolSize(43, this.areUidsChanging_);
        }
        if ((this.bitField1_ & 512) == 512) {
            size4 += CodedOutputStream.computeBoolSize(44, this.areUidsChanged_);
        }
        for (int i3 = 0; i3 < this.uidStates_.size(); i3++) {
            size4 += CodedOutputStream.computeMessageSize(45, this.uidStates_.get(i3));
        }
        if ((this.bitField1_ & 1024) == 1024) {
            size4 += CodedOutputStream.computeMessageSize(46, getLooper());
        }
        for (int i4 = 0; i4 < this.wakeLocks_.size(); i4++) {
            size4 += CodedOutputStream.computeMessageSize(47, this.wakeLocks_.get(i4));
        }
        for (int i5 = 0; i5 < this.suspendBlockers_.size(); i5++) {
            size4 += CodedOutputStream.computeMessageSize(48, this.suspendBlockers_.get(i5));
        }
        if ((this.bitField1_ & 2048) == 2048) {
            size4 += CodedOutputStream.computeMessageSize(49, getWirelessChargerDetector());
        }
        if ((this.bitField1_ & 4096) == 4096) {
            size4 += CodedOutputStream.computeMessageSize(50, getBatterySaverStateMachine());
        }
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size5;
        return size5;
    }

    public static PowerManagerServiceDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerServiceDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerServiceDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static PowerManagerServiceDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static PowerManagerServiceDumpProto parseFrom(InputStream input) throws IOException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerServiceDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerServiceDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (PowerManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerServiceDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerServiceDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static PowerManagerServiceDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static PowerManagerServiceDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (PowerManagerServiceDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PowerManagerServiceDumpProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PowerManagerServiceDumpProto, Builder> implements PowerManagerServiceDumpProtoOrBuilder {
        private Builder() {
            super(PowerManagerServiceDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasConstants() {
            return ((PowerManagerServiceDumpProto) this.instance).hasConstants();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public ConstantsProto getConstants() {
            return ((PowerManagerServiceDumpProto) this.instance).getConstants();
        }

        public Builder setConstants(ConstantsProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setConstants((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setConstants(ConstantsProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setConstants((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeConstants(ConstantsProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeConstants(value);
            return this;
        }

        public Builder clearConstants() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearConstants();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasDirty() {
            return ((PowerManagerServiceDumpProto) this.instance).hasDirty();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getDirty() {
            return ((PowerManagerServiceDumpProto) this.instance).getDirty();
        }

        public Builder setDirty(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setDirty(value);
            return this;
        }

        public Builder clearDirty() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearDirty();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasWakefulness() {
            return ((PowerManagerServiceDumpProto) this.instance).hasWakefulness();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public PowerManagerInternalProto.Wakefulness getWakefulness() {
            return ((PowerManagerServiceDumpProto) this.instance).getWakefulness();
        }

        public Builder setWakefulness(PowerManagerInternalProto.Wakefulness value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setWakefulness(value);
            return this;
        }

        public Builder clearWakefulness() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearWakefulness();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsWakefulnessChanging() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsWakefulnessChanging();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsWakefulnessChanging() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsWakefulnessChanging();
        }

        public Builder setIsWakefulnessChanging(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsWakefulnessChanging(value);
            return this;
        }

        public Builder clearIsWakefulnessChanging() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsWakefulnessChanging();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsPowered() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsPowered();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsPowered() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsPowered();
        }

        public Builder setIsPowered(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsPowered(value);
            return this;
        }

        public Builder clearIsPowered() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsPowered();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasPlugType() {
            return ((PowerManagerServiceDumpProto) this.instance).hasPlugType();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public BatteryPluggedStateEnum getPlugType() {
            return ((PowerManagerServiceDumpProto) this.instance).getPlugType();
        }

        public Builder setPlugType(BatteryPluggedStateEnum value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setPlugType(value);
            return this;
        }

        public Builder clearPlugType() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearPlugType();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasBatteryLevel() {
            return ((PowerManagerServiceDumpProto) this.instance).hasBatteryLevel();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getBatteryLevel() {
            return ((PowerManagerServiceDumpProto) this.instance).getBatteryLevel();
        }

        public Builder setBatteryLevel(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setBatteryLevel(value);
            return this;
        }

        public Builder clearBatteryLevel() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearBatteryLevel();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasBatteryLevelWhenDreamStarted() {
            return ((PowerManagerServiceDumpProto) this.instance).hasBatteryLevelWhenDreamStarted();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getBatteryLevelWhenDreamStarted() {
            return ((PowerManagerServiceDumpProto) this.instance).getBatteryLevelWhenDreamStarted();
        }

        public Builder setBatteryLevelWhenDreamStarted(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setBatteryLevelWhenDreamStarted(value);
            return this;
        }

        public Builder clearBatteryLevelWhenDreamStarted() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearBatteryLevelWhenDreamStarted();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasDockState() {
            return ((PowerManagerServiceDumpProto) this.instance).hasDockState();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public IntentProto.DockState getDockState() {
            return ((PowerManagerServiceDumpProto) this.instance).getDockState();
        }

        public Builder setDockState(IntentProto.DockState value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setDockState(value);
            return this;
        }

        public Builder clearDockState() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearDockState();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsStayOn() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsStayOn();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsStayOn() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsStayOn();
        }

        public Builder setIsStayOn(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsStayOn(value);
            return this;
        }

        public Builder clearIsStayOn() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsStayOn();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsProximityPositive() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsProximityPositive();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsProximityPositive() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsProximityPositive();
        }

        public Builder setIsProximityPositive(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsProximityPositive(value);
            return this;
        }

        public Builder clearIsProximityPositive() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsProximityPositive();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsBootCompleted() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsBootCompleted();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsBootCompleted() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsBootCompleted();
        }

        public Builder setIsBootCompleted(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsBootCompleted(value);
            return this;
        }

        public Builder clearIsBootCompleted() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsBootCompleted();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsSystemReady() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsSystemReady();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsSystemReady() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsSystemReady();
        }

        public Builder setIsSystemReady(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsSystemReady(value);
            return this;
        }

        public Builder clearIsSystemReady() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsSystemReady();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsHalAutoSuspendModeEnabled() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsHalAutoSuspendModeEnabled();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsHalAutoSuspendModeEnabled() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsHalAutoSuspendModeEnabled();
        }

        public Builder setIsHalAutoSuspendModeEnabled(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsHalAutoSuspendModeEnabled(value);
            return this;
        }

        public Builder clearIsHalAutoSuspendModeEnabled() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsHalAutoSuspendModeEnabled();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsHalAutoInteractiveModeEnabled() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsHalAutoInteractiveModeEnabled();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsHalAutoInteractiveModeEnabled() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsHalAutoInteractiveModeEnabled();
        }

        public Builder setIsHalAutoInteractiveModeEnabled(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsHalAutoInteractiveModeEnabled(value);
            return this;
        }

        public Builder clearIsHalAutoInteractiveModeEnabled() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsHalAutoInteractiveModeEnabled();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasActiveWakeLocks() {
            return ((PowerManagerServiceDumpProto) this.instance).hasActiveWakeLocks();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public ActiveWakeLocksProto getActiveWakeLocks() {
            return ((PowerManagerServiceDumpProto) this.instance).getActiveWakeLocks();
        }

        public Builder setActiveWakeLocks(ActiveWakeLocksProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setActiveWakeLocks((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setActiveWakeLocks(ActiveWakeLocksProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setActiveWakeLocks((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeActiveWakeLocks(ActiveWakeLocksProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeActiveWakeLocks(value);
            return this;
        }

        public Builder clearActiveWakeLocks() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearActiveWakeLocks();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasNotifyLongScheduledMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasNotifyLongScheduledMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getNotifyLongScheduledMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getNotifyLongScheduledMs();
        }

        public Builder setNotifyLongScheduledMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setNotifyLongScheduledMs(value);
            return this;
        }

        public Builder clearNotifyLongScheduledMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearNotifyLongScheduledMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasNotifyLongDispatchedMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasNotifyLongDispatchedMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getNotifyLongDispatchedMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getNotifyLongDispatchedMs();
        }

        public Builder setNotifyLongDispatchedMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setNotifyLongDispatchedMs(value);
            return this;
        }

        public Builder clearNotifyLongDispatchedMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearNotifyLongDispatchedMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasNotifyLongNextCheckMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasNotifyLongNextCheckMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getNotifyLongNextCheckMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getNotifyLongNextCheckMs();
        }

        public Builder setNotifyLongNextCheckMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setNotifyLongNextCheckMs(value);
            return this;
        }

        public Builder clearNotifyLongNextCheckMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearNotifyLongNextCheckMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasUserActivity() {
            return ((PowerManagerServiceDumpProto) this.instance).hasUserActivity();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public UserActivityProto getUserActivity() {
            return ((PowerManagerServiceDumpProto) this.instance).getUserActivity();
        }

        public Builder setUserActivity(UserActivityProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setUserActivity((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setUserActivity(UserActivityProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setUserActivity((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeUserActivity(UserActivityProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeUserActivity(value);
            return this;
        }

        public Builder clearUserActivity() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearUserActivity();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsRequestWaitForNegativeProximity() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsRequestWaitForNegativeProximity();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsRequestWaitForNegativeProximity() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsRequestWaitForNegativeProximity();
        }

        public Builder setIsRequestWaitForNegativeProximity(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsRequestWaitForNegativeProximity(value);
            return this;
        }

        public Builder clearIsRequestWaitForNegativeProximity() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsRequestWaitForNegativeProximity();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsSandmanScheduled() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsSandmanScheduled();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsSandmanScheduled() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsSandmanScheduled();
        }

        public Builder setIsSandmanScheduled(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsSandmanScheduled(value);
            return this;
        }

        public Builder clearIsSandmanScheduled() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsSandmanScheduled();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsSandmanSummoned() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsSandmanSummoned();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsSandmanSummoned() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsSandmanSummoned();
        }

        public Builder setIsSandmanSummoned(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsSandmanSummoned(value);
            return this;
        }

        public Builder clearIsSandmanSummoned() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsSandmanSummoned();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsBatteryLevelLow() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsBatteryLevelLow();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsBatteryLevelLow() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsBatteryLevelLow();
        }

        public Builder setIsBatteryLevelLow(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsBatteryLevelLow(value);
            return this;
        }

        public Builder clearIsBatteryLevelLow() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsBatteryLevelLow();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsLightDeviceIdleMode() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsLightDeviceIdleMode();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsLightDeviceIdleMode() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsLightDeviceIdleMode();
        }

        public Builder setIsLightDeviceIdleMode(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsLightDeviceIdleMode(value);
            return this;
        }

        public Builder clearIsLightDeviceIdleMode() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsLightDeviceIdleMode();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsDeviceIdleMode() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsDeviceIdleMode();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsDeviceIdleMode() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsDeviceIdleMode();
        }

        public Builder setIsDeviceIdleMode(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsDeviceIdleMode(value);
            return this;
        }

        public Builder clearIsDeviceIdleMode() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsDeviceIdleMode();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public List<Integer> getDeviceIdleWhitelistList() {
            return Collections.unmodifiableList(((PowerManagerServiceDumpProto) this.instance).getDeviceIdleWhitelistList());
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleWhitelistCount() {
            return ((PowerManagerServiceDumpProto) this.instance).getDeviceIdleWhitelistCount();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleWhitelist(int index) {
            return ((PowerManagerServiceDumpProto) this.instance).getDeviceIdleWhitelist(index);
        }

        public Builder setDeviceIdleWhitelist(int index, int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setDeviceIdleWhitelist(index, value);
            return this;
        }

        public Builder addDeviceIdleWhitelist(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addDeviceIdleWhitelist(value);
            return this;
        }

        public Builder addAllDeviceIdleWhitelist(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addAllDeviceIdleWhitelist(values);
            return this;
        }

        public Builder clearDeviceIdleWhitelist() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearDeviceIdleWhitelist();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public List<Integer> getDeviceIdleTempWhitelistList() {
            return Collections.unmodifiableList(((PowerManagerServiceDumpProto) this.instance).getDeviceIdleTempWhitelistList());
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleTempWhitelistCount() {
            return ((PowerManagerServiceDumpProto) this.instance).getDeviceIdleTempWhitelistCount();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getDeviceIdleTempWhitelist(int index) {
            return ((PowerManagerServiceDumpProto) this.instance).getDeviceIdleTempWhitelist(index);
        }

        public Builder setDeviceIdleTempWhitelist(int index, int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setDeviceIdleTempWhitelist(index, value);
            return this;
        }

        public Builder addDeviceIdleTempWhitelist(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addDeviceIdleTempWhitelist(value);
            return this;
        }

        public Builder addAllDeviceIdleTempWhitelist(Iterable<? extends Integer> values) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addAllDeviceIdleTempWhitelist(values);
            return this;
        }

        public Builder clearDeviceIdleTempWhitelist() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearDeviceIdleTempWhitelist();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastWakeTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastWakeTimeMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastWakeTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastWakeTimeMs();
        }

        public Builder setLastWakeTimeMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastWakeTimeMs(value);
            return this;
        }

        public Builder clearLastWakeTimeMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastWakeTimeMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastSleepTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastSleepTimeMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastSleepTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastSleepTimeMs();
        }

        public Builder setLastSleepTimeMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastSleepTimeMs(value);
            return this;
        }

        public Builder clearLastSleepTimeMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastSleepTimeMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastUserActivityTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastUserActivityTimeMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastUserActivityTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastUserActivityTimeMs();
        }

        public Builder setLastUserActivityTimeMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastUserActivityTimeMs(value);
            return this;
        }

        public Builder clearLastUserActivityTimeMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastUserActivityTimeMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastUserActivityTimeNoChangeLightsMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastUserActivityTimeNoChangeLightsMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastUserActivityTimeNoChangeLightsMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastUserActivityTimeNoChangeLightsMs();
        }

        public Builder setLastUserActivityTimeNoChangeLightsMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastUserActivityTimeNoChangeLightsMs(value);
            return this;
        }

        public Builder clearLastUserActivityTimeNoChangeLightsMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastUserActivityTimeNoChangeLightsMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastInteractivePowerHintTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastInteractivePowerHintTimeMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastInteractivePowerHintTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastInteractivePowerHintTimeMs();
        }

        public Builder setLastInteractivePowerHintTimeMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastInteractivePowerHintTimeMs(value);
            return this;
        }

        public Builder clearLastInteractivePowerHintTimeMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastInteractivePowerHintTimeMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLastScreenBrightnessBoostTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLastScreenBrightnessBoostTimeMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public long getLastScreenBrightnessBoostTimeMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getLastScreenBrightnessBoostTimeMs();
        }

        public Builder setLastScreenBrightnessBoostTimeMs(long value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLastScreenBrightnessBoostTimeMs(value);
            return this;
        }

        public Builder clearLastScreenBrightnessBoostTimeMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLastScreenBrightnessBoostTimeMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsScreenBrightnessBoostInProgress() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsScreenBrightnessBoostInProgress();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsScreenBrightnessBoostInProgress() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsScreenBrightnessBoostInProgress();
        }

        public Builder setIsScreenBrightnessBoostInProgress(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsScreenBrightnessBoostInProgress(value);
            return this;
        }

        public Builder clearIsScreenBrightnessBoostInProgress() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsScreenBrightnessBoostInProgress();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsDisplayReady() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsDisplayReady();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsDisplayReady() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsDisplayReady();
        }

        public Builder setIsDisplayReady(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsDisplayReady(value);
            return this;
        }

        public Builder clearIsDisplayReady() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsDisplayReady();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsHoldingWakeLockSuspendBlocker() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsHoldingWakeLockSuspendBlocker();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsHoldingWakeLockSuspendBlocker() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsHoldingWakeLockSuspendBlocker();
        }

        public Builder setIsHoldingWakeLockSuspendBlocker(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsHoldingWakeLockSuspendBlocker(value);
            return this;
        }

        public Builder clearIsHoldingWakeLockSuspendBlocker() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsHoldingWakeLockSuspendBlocker();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasIsHoldingDisplaySuspendBlocker() {
            return ((PowerManagerServiceDumpProto) this.instance).hasIsHoldingDisplaySuspendBlocker();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getIsHoldingDisplaySuspendBlocker() {
            return ((PowerManagerServiceDumpProto) this.instance).getIsHoldingDisplaySuspendBlocker();
        }

        public Builder setIsHoldingDisplaySuspendBlocker(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setIsHoldingDisplaySuspendBlocker(value);
            return this;
        }

        public Builder clearIsHoldingDisplaySuspendBlocker() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearIsHoldingDisplaySuspendBlocker();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasSettingsAndConfiguration() {
            return ((PowerManagerServiceDumpProto) this.instance).hasSettingsAndConfiguration();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public PowerServiceSettingsAndConfigurationDumpProto getSettingsAndConfiguration() {
            return ((PowerManagerServiceDumpProto) this.instance).getSettingsAndConfiguration();
        }

        public Builder setSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setSettingsAndConfiguration((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setSettingsAndConfiguration((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeSettingsAndConfiguration(PowerServiceSettingsAndConfigurationDumpProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeSettingsAndConfiguration(value);
            return this;
        }

        public Builder clearSettingsAndConfiguration() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearSettingsAndConfiguration();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasSleepTimeoutMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasSleepTimeoutMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getSleepTimeoutMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getSleepTimeoutMs();
        }

        public Builder setSleepTimeoutMs(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setSleepTimeoutMs(value);
            return this;
        }

        public Builder clearSleepTimeoutMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearSleepTimeoutMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasScreenOffTimeoutMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasScreenOffTimeoutMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getScreenOffTimeoutMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getScreenOffTimeoutMs();
        }

        public Builder setScreenOffTimeoutMs(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setScreenOffTimeoutMs(value);
            return this;
        }

        public Builder clearScreenOffTimeoutMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearScreenOffTimeoutMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasScreenDimDurationMs() {
            return ((PowerManagerServiceDumpProto) this.instance).hasScreenDimDurationMs();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getScreenDimDurationMs() {
            return ((PowerManagerServiceDumpProto) this.instance).getScreenDimDurationMs();
        }

        public Builder setScreenDimDurationMs(int value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setScreenDimDurationMs(value);
            return this;
        }

        public Builder clearScreenDimDurationMs() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearScreenDimDurationMs();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasAreUidsChanging() {
            return ((PowerManagerServiceDumpProto) this.instance).hasAreUidsChanging();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getAreUidsChanging() {
            return ((PowerManagerServiceDumpProto) this.instance).getAreUidsChanging();
        }

        public Builder setAreUidsChanging(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setAreUidsChanging(value);
            return this;
        }

        public Builder clearAreUidsChanging() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearAreUidsChanging();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasAreUidsChanged() {
            return ((PowerManagerServiceDumpProto) this.instance).hasAreUidsChanged();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean getAreUidsChanged() {
            return ((PowerManagerServiceDumpProto) this.instance).getAreUidsChanged();
        }

        public Builder setAreUidsChanged(boolean value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setAreUidsChanged(value);
            return this;
        }

        public Builder clearAreUidsChanged() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearAreUidsChanged();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public List<UidStateProto> getUidStatesList() {
            return Collections.unmodifiableList(((PowerManagerServiceDumpProto) this.instance).getUidStatesList());
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getUidStatesCount() {
            return ((PowerManagerServiceDumpProto) this.instance).getUidStatesCount();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public UidStateProto getUidStates(int index) {
            return ((PowerManagerServiceDumpProto) this.instance).getUidStates(index);
        }

        public Builder setUidStates(int index, UidStateProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setUidStates((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setUidStates(int index, UidStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setUidStates((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addUidStates(UidStateProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addUidStates((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder addUidStates(int index, UidStateProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addUidStates((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addUidStates(UidStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addUidStates((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addUidStates(int index, UidStateProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addUidStates((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllUidStates(Iterable<? extends UidStateProto> values) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addAllUidStates(values);
            return this;
        }

        public Builder clearUidStates() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearUidStates();
            return this;
        }

        public Builder removeUidStates(int index) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).removeUidStates(index);
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasLooper() {
            return ((PowerManagerServiceDumpProto) this.instance).hasLooper();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public LooperProto getLooper() {
            return ((PowerManagerServiceDumpProto) this.instance).getLooper();
        }

        public Builder setLooper(LooperProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLooper((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setLooper(LooperProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setLooper((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeLooper(LooperProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeLooper(value);
            return this;
        }

        public Builder clearLooper() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearLooper();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public List<WakeLockProto> getWakeLocksList() {
            return Collections.unmodifiableList(((PowerManagerServiceDumpProto) this.instance).getWakeLocksList());
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getWakeLocksCount() {
            return ((PowerManagerServiceDumpProto) this.instance).getWakeLocksCount();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public WakeLockProto getWakeLocks(int index) {
            return ((PowerManagerServiceDumpProto) this.instance).getWakeLocks(index);
        }

        public Builder setWakeLocks(int index, WakeLockProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setWakeLocks((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setWakeLocks(int index, WakeLockProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setWakeLocks((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addWakeLocks(WakeLockProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addWakeLocks((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder addWakeLocks(int index, WakeLockProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addWakeLocks((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addWakeLocks(WakeLockProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addWakeLocks((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addWakeLocks(int index, WakeLockProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addWakeLocks((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllWakeLocks(Iterable<? extends WakeLockProto> values) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addAllWakeLocks(values);
            return this;
        }

        public Builder clearWakeLocks() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearWakeLocks();
            return this;
        }

        public Builder removeWakeLocks(int index) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).removeWakeLocks(index);
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public List<SuspendBlockerProto> getSuspendBlockersList() {
            return Collections.unmodifiableList(((PowerManagerServiceDumpProto) this.instance).getSuspendBlockersList());
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public int getSuspendBlockersCount() {
            return ((PowerManagerServiceDumpProto) this.instance).getSuspendBlockersCount();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public SuspendBlockerProto getSuspendBlockers(int index) {
            return ((PowerManagerServiceDumpProto) this.instance).getSuspendBlockers(index);
        }

        public Builder setSuspendBlockers(int index, SuspendBlockerProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setSuspendBlockers((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder setSuspendBlockers(int index, SuspendBlockerProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setSuspendBlockers((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addSuspendBlockers(SuspendBlockerProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addSuspendBlockers((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder addSuspendBlockers(int index, SuspendBlockerProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addSuspendBlockers((PowerManagerServiceDumpProto) index, (int) value);
            return this;
        }

        public Builder addSuspendBlockers(SuspendBlockerProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addSuspendBlockers((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder addSuspendBlockers(int index, SuspendBlockerProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addSuspendBlockers((PowerManagerServiceDumpProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllSuspendBlockers(Iterable<? extends SuspendBlockerProto> values) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).addAllSuspendBlockers(values);
            return this;
        }

        public Builder clearSuspendBlockers() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearSuspendBlockers();
            return this;
        }

        public Builder removeSuspendBlockers(int index) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).removeSuspendBlockers(index);
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasWirelessChargerDetector() {
            return ((PowerManagerServiceDumpProto) this.instance).hasWirelessChargerDetector();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public WirelessChargerDetectorProto getWirelessChargerDetector() {
            return ((PowerManagerServiceDumpProto) this.instance).getWirelessChargerDetector();
        }

        public Builder setWirelessChargerDetector(WirelessChargerDetectorProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setWirelessChargerDetector((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setWirelessChargerDetector(WirelessChargerDetectorProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setWirelessChargerDetector((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeWirelessChargerDetector(WirelessChargerDetectorProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeWirelessChargerDetector(value);
            return this;
        }

        public Builder clearWirelessChargerDetector() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearWirelessChargerDetector();
            return this;
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public boolean hasBatterySaverStateMachine() {
            return ((PowerManagerServiceDumpProto) this.instance).hasBatterySaverStateMachine();
        }

        @Override // com.android.server.power.PowerManagerServiceDumpProtoOrBuilder
        public BatterySaverStateMachineProto getBatterySaverStateMachine() {
            return ((PowerManagerServiceDumpProto) this.instance).getBatterySaverStateMachine();
        }

        public Builder setBatterySaverStateMachine(BatterySaverStateMachineProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setBatterySaverStateMachine((PowerManagerServiceDumpProto) value);
            return this;
        }

        public Builder setBatterySaverStateMachine(BatterySaverStateMachineProto.Builder builderForValue) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).setBatterySaverStateMachine((PowerManagerServiceDumpProto) builderForValue);
            return this;
        }

        public Builder mergeBatterySaverStateMachine(BatterySaverStateMachineProto value) {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).mergeBatterySaverStateMachine(value);
            return this;
        }

        public Builder clearBatterySaverStateMachine() {
            copyOnWrite();
            ((PowerManagerServiceDumpProto) this.instance).clearBatterySaverStateMachine();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new PowerManagerServiceDumpProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.deviceIdleWhitelist_.makeImmutable();
                this.deviceIdleTempWhitelist_.makeImmutable();
                this.uidStates_.makeImmutable();
                this.wakeLocks_.makeImmutable();
                this.suspendBlockers_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                PowerManagerServiceDumpProto other = (PowerManagerServiceDumpProto) arg1;
                this.constants_ = (ConstantsProto) visitor.visitMessage(this.constants_, other.constants_);
                this.dirty_ = visitor.visitInt(hasDirty(), this.dirty_, other.hasDirty(), other.dirty_);
                this.wakefulness_ = visitor.visitInt(hasWakefulness(), this.wakefulness_, other.hasWakefulness(), other.wakefulness_);
                this.isWakefulnessChanging_ = visitor.visitBoolean(hasIsWakefulnessChanging(), this.isWakefulnessChanging_, other.hasIsWakefulnessChanging(), other.isWakefulnessChanging_);
                this.isPowered_ = visitor.visitBoolean(hasIsPowered(), this.isPowered_, other.hasIsPowered(), other.isPowered_);
                this.plugType_ = visitor.visitInt(hasPlugType(), this.plugType_, other.hasPlugType(), other.plugType_);
                this.batteryLevel_ = visitor.visitInt(hasBatteryLevel(), this.batteryLevel_, other.hasBatteryLevel(), other.batteryLevel_);
                this.batteryLevelWhenDreamStarted_ = visitor.visitInt(hasBatteryLevelWhenDreamStarted(), this.batteryLevelWhenDreamStarted_, other.hasBatteryLevelWhenDreamStarted(), other.batteryLevelWhenDreamStarted_);
                this.dockState_ = visitor.visitInt(hasDockState(), this.dockState_, other.hasDockState(), other.dockState_);
                this.isStayOn_ = visitor.visitBoolean(hasIsStayOn(), this.isStayOn_, other.hasIsStayOn(), other.isStayOn_);
                this.isProximityPositive_ = visitor.visitBoolean(hasIsProximityPositive(), this.isProximityPositive_, other.hasIsProximityPositive(), other.isProximityPositive_);
                this.isBootCompleted_ = visitor.visitBoolean(hasIsBootCompleted(), this.isBootCompleted_, other.hasIsBootCompleted(), other.isBootCompleted_);
                this.isSystemReady_ = visitor.visitBoolean(hasIsSystemReady(), this.isSystemReady_, other.hasIsSystemReady(), other.isSystemReady_);
                this.isHalAutoSuspendModeEnabled_ = visitor.visitBoolean(hasIsHalAutoSuspendModeEnabled(), this.isHalAutoSuspendModeEnabled_, other.hasIsHalAutoSuspendModeEnabled(), other.isHalAutoSuspendModeEnabled_);
                this.isHalAutoInteractiveModeEnabled_ = visitor.visitBoolean(hasIsHalAutoInteractiveModeEnabled(), this.isHalAutoInteractiveModeEnabled_, other.hasIsHalAutoInteractiveModeEnabled(), other.isHalAutoInteractiveModeEnabled_);
                this.activeWakeLocks_ = (ActiveWakeLocksProto) visitor.visitMessage(this.activeWakeLocks_, other.activeWakeLocks_);
                this.notifyLongScheduledMs_ = visitor.visitLong(hasNotifyLongScheduledMs(), this.notifyLongScheduledMs_, other.hasNotifyLongScheduledMs(), other.notifyLongScheduledMs_);
                this.notifyLongDispatchedMs_ = visitor.visitLong(hasNotifyLongDispatchedMs(), this.notifyLongDispatchedMs_, other.hasNotifyLongDispatchedMs(), other.notifyLongDispatchedMs_);
                this.notifyLongNextCheckMs_ = visitor.visitLong(hasNotifyLongNextCheckMs(), this.notifyLongNextCheckMs_, other.hasNotifyLongNextCheckMs(), other.notifyLongNextCheckMs_);
                this.userActivity_ = (UserActivityProto) visitor.visitMessage(this.userActivity_, other.userActivity_);
                this.isRequestWaitForNegativeProximity_ = visitor.visitBoolean(hasIsRequestWaitForNegativeProximity(), this.isRequestWaitForNegativeProximity_, other.hasIsRequestWaitForNegativeProximity(), other.isRequestWaitForNegativeProximity_);
                this.isSandmanScheduled_ = visitor.visitBoolean(hasIsSandmanScheduled(), this.isSandmanScheduled_, other.hasIsSandmanScheduled(), other.isSandmanScheduled_);
                this.isSandmanSummoned_ = visitor.visitBoolean(hasIsSandmanSummoned(), this.isSandmanSummoned_, other.hasIsSandmanSummoned(), other.isSandmanSummoned_);
                this.isBatteryLevelLow_ = visitor.visitBoolean(hasIsBatteryLevelLow(), this.isBatteryLevelLow_, other.hasIsBatteryLevelLow(), other.isBatteryLevelLow_);
                this.isLightDeviceIdleMode_ = visitor.visitBoolean(hasIsLightDeviceIdleMode(), this.isLightDeviceIdleMode_, other.hasIsLightDeviceIdleMode(), other.isLightDeviceIdleMode_);
                this.isDeviceIdleMode_ = visitor.visitBoolean(hasIsDeviceIdleMode(), this.isDeviceIdleMode_, other.hasIsDeviceIdleMode(), other.isDeviceIdleMode_);
                this.deviceIdleWhitelist_ = visitor.visitIntList(this.deviceIdleWhitelist_, other.deviceIdleWhitelist_);
                this.deviceIdleTempWhitelist_ = visitor.visitIntList(this.deviceIdleTempWhitelist_, other.deviceIdleTempWhitelist_);
                this.lastWakeTimeMs_ = visitor.visitLong(hasLastWakeTimeMs(), this.lastWakeTimeMs_, other.hasLastWakeTimeMs(), other.lastWakeTimeMs_);
                this.lastSleepTimeMs_ = visitor.visitLong(hasLastSleepTimeMs(), this.lastSleepTimeMs_, other.hasLastSleepTimeMs(), other.lastSleepTimeMs_);
                this.lastUserActivityTimeMs_ = visitor.visitLong(hasLastUserActivityTimeMs(), this.lastUserActivityTimeMs_, other.hasLastUserActivityTimeMs(), other.lastUserActivityTimeMs_);
                this.lastUserActivityTimeNoChangeLightsMs_ = visitor.visitLong(hasLastUserActivityTimeNoChangeLightsMs(), this.lastUserActivityTimeNoChangeLightsMs_, other.hasLastUserActivityTimeNoChangeLightsMs(), other.lastUserActivityTimeNoChangeLightsMs_);
                this.lastInteractivePowerHintTimeMs_ = visitor.visitLong(hasLastInteractivePowerHintTimeMs(), this.lastInteractivePowerHintTimeMs_, other.hasLastInteractivePowerHintTimeMs(), other.lastInteractivePowerHintTimeMs_);
                this.lastScreenBrightnessBoostTimeMs_ = visitor.visitLong(hasLastScreenBrightnessBoostTimeMs(), this.lastScreenBrightnessBoostTimeMs_, other.hasLastScreenBrightnessBoostTimeMs(), other.lastScreenBrightnessBoostTimeMs_);
                this.isScreenBrightnessBoostInProgress_ = visitor.visitBoolean(hasIsScreenBrightnessBoostInProgress(), this.isScreenBrightnessBoostInProgress_, other.hasIsScreenBrightnessBoostInProgress(), other.isScreenBrightnessBoostInProgress_);
                this.isDisplayReady_ = visitor.visitBoolean(hasIsDisplayReady(), this.isDisplayReady_, other.hasIsDisplayReady(), other.isDisplayReady_);
                this.isHoldingWakeLockSuspendBlocker_ = visitor.visitBoolean(hasIsHoldingWakeLockSuspendBlocker(), this.isHoldingWakeLockSuspendBlocker_, other.hasIsHoldingWakeLockSuspendBlocker(), other.isHoldingWakeLockSuspendBlocker_);
                this.isHoldingDisplaySuspendBlocker_ = visitor.visitBoolean(hasIsHoldingDisplaySuspendBlocker(), this.isHoldingDisplaySuspendBlocker_, other.hasIsHoldingDisplaySuspendBlocker(), other.isHoldingDisplaySuspendBlocker_);
                this.settingsAndConfiguration_ = (PowerServiceSettingsAndConfigurationDumpProto) visitor.visitMessage(this.settingsAndConfiguration_, other.settingsAndConfiguration_);
                this.sleepTimeoutMs_ = visitor.visitInt(hasSleepTimeoutMs(), this.sleepTimeoutMs_, other.hasSleepTimeoutMs(), other.sleepTimeoutMs_);
                this.screenOffTimeoutMs_ = visitor.visitInt(hasScreenOffTimeoutMs(), this.screenOffTimeoutMs_, other.hasScreenOffTimeoutMs(), other.screenOffTimeoutMs_);
                this.screenDimDurationMs_ = visitor.visitInt(hasScreenDimDurationMs(), this.screenDimDurationMs_, other.hasScreenDimDurationMs(), other.screenDimDurationMs_);
                this.areUidsChanging_ = visitor.visitBoolean(hasAreUidsChanging(), this.areUidsChanging_, other.hasAreUidsChanging(), other.areUidsChanging_);
                this.areUidsChanged_ = visitor.visitBoolean(hasAreUidsChanged(), this.areUidsChanged_, other.hasAreUidsChanged(), other.areUidsChanged_);
                this.uidStates_ = visitor.visitList(this.uidStates_, other.uidStates_);
                this.looper_ = (LooperProto) visitor.visitMessage(this.looper_, other.looper_);
                this.wakeLocks_ = visitor.visitList(this.wakeLocks_, other.wakeLocks_);
                this.suspendBlockers_ = visitor.visitList(this.suspendBlockers_, other.suspendBlockers_);
                this.wirelessChargerDetector_ = (WirelessChargerDetectorProto) visitor.visitMessage(this.wirelessChargerDetector_, other.wirelessChargerDetector_);
                this.batterySaverStateMachine_ = (BatterySaverStateMachineProto) visitor.visitMessage(this.batterySaverStateMachine_, other.batterySaverStateMachine_);
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
                            case 10:
                                ConstantsProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (ConstantsProto.Builder) this.constants_.toBuilder();
                                }
                                this.constants_ = (ConstantsProto) input.readMessage(ConstantsProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.constants_);
                                    this.constants_ = (ConstantsProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.dirty_ = input.readInt32();
                                break;
                            case 24:
                                int rawValue = input.readEnum();
                                if (PowerManagerInternalProto.Wakefulness.forNumber(rawValue) != null) {
                                    this.bitField0_ |= 4;
                                    this.wakefulness_ = rawValue;
                                    break;
                                } else {
                                    super.mergeVarintField(3, rawValue);
                                    break;
                                }
                            case 32:
                                this.bitField0_ |= 8;
                                this.isWakefulnessChanging_ = input.readBool();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.isPowered_ = input.readBool();
                                break;
                            case 48:
                                int rawValue2 = input.readEnum();
                                if (BatteryPluggedStateEnum.forNumber(rawValue2) != null) {
                                    this.bitField0_ |= 32;
                                    this.plugType_ = rawValue2;
                                    break;
                                } else {
                                    super.mergeVarintField(6, rawValue2);
                                    break;
                                }
                            case 56:
                                this.bitField0_ |= 64;
                                this.batteryLevel_ = input.readInt32();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.batteryLevelWhenDreamStarted_ = input.readInt32();
                                break;
                            case 72:
                                int rawValue3 = input.readEnum();
                                if (IntentProto.DockState.forNumber(rawValue3) != null) {
                                    this.bitField0_ |= 256;
                                    this.dockState_ = rawValue3;
                                    break;
                                } else {
                                    super.mergeVarintField(9, rawValue3);
                                    break;
                                }
                            case 80:
                                this.bitField0_ |= 512;
                                this.isStayOn_ = input.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.isProximityPositive_ = input.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.isBootCompleted_ = input.readBool();
                                break;
                            case 104:
                                this.bitField0_ = 4096 | this.bitField0_;
                                this.isSystemReady_ = input.readBool();
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.isHalAutoSuspendModeEnabled_ = input.readBool();
                                break;
                            case 120:
                                this.bitField0_ |= 16384;
                                this.isHalAutoInteractiveModeEnabled_ = input.readBool();
                                break;
                            case 130:
                                ActiveWakeLocksProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 32768) == 32768) {
                                    subBuilder2 = (ActiveWakeLocksProto.Builder) this.activeWakeLocks_.toBuilder();
                                }
                                this.activeWakeLocks_ = (ActiveWakeLocksProto) input.readMessage(ActiveWakeLocksProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.activeWakeLocks_);
                                    this.activeWakeLocks_ = (ActiveWakeLocksProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 32768;
                                break;
                            case 136:
                                this.bitField0_ |= 65536;
                                this.notifyLongScheduledMs_ = input.readInt64();
                                break;
                            case 144:
                                this.bitField0_ |= 131072;
                                this.notifyLongDispatchedMs_ = input.readInt64();
                                break;
                            case 152:
                                this.bitField0_ |= 262144;
                                this.notifyLongNextCheckMs_ = input.readInt64();
                                break;
                            case 162:
                                UserActivityProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder3 = (UserActivityProto.Builder) this.userActivity_.toBuilder();
                                }
                                this.userActivity_ = (UserActivityProto) input.readMessage(UserActivityProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.userActivity_);
                                    this.userActivity_ = (UserActivityProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            case 168:
                                this.bitField0_ |= 1048576;
                                this.isRequestWaitForNegativeProximity_ = input.readBool();
                                break;
                            case AtomsProto.Atom.ASSIST_GESTURE_PROGRESS_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 2097152;
                                this.isSandmanScheduled_ = input.readBool();
                                break;
                            case 184:
                                this.bitField0_ |= 4194304;
                                this.isSandmanSummoned_ = input.readBool();
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIORECORD_REPORTED_FIELD_NUMBER:
                                this.bitField0_ |= 8388608;
                                this.isBatteryLevelLow_ = input.readBool();
                                break;
                            case 200:
                                this.bitField0_ |= 16777216;
                                this.isLightDeviceIdleMode_ = input.readBool();
                                break;
                            case AtomsProto.Atom.CONTENT_CAPTURE_SESSION_EVENTS_FIELD_NUMBER:
                                this.bitField0_ |= 33554432;
                                this.isDeviceIdleMode_ = input.readBool();
                                break;
                            case AtomsProto.Atom.APP_PERMISSION_FRAGMENT_VIEWED_FIELD_NUMBER:
                                if (!this.deviceIdleWhitelist_.isModifiable()) {
                                    this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
                                }
                                this.deviceIdleWhitelist_.addInt(input.readInt32());
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER:
                                int limit = input.pushLimit(input.readRawVarint32());
                                if (!this.deviceIdleWhitelist_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleWhitelist_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleWhitelist_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case AtomsProto.Atom.BACK_GESTURE_REPORTED_REPORTED_FIELD_NUMBER:
                                if (!this.deviceIdleTempWhitelist_.isModifiable()) {
                                    this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
                                }
                                this.deviceIdleTempWhitelist_.addInt(input.readInt32());
                                break;
                            case ACTION_SEARCH_RESULTS_VALUE:
                                int limit2 = input.pushLimit(input.readRawVarint32());
                                if (!this.deviceIdleTempWhitelist_.isModifiable() && input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleTempWhitelist_ = GeneratedMessageLite.mutableCopy(this.deviceIdleTempWhitelist_);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.deviceIdleTempWhitelist_.addInt(input.readInt32());
                                }
                                input.popLimit(limit2);
                                break;
                            case 232:
                                this.bitField0_ |= 67108864;
                                this.lastWakeTimeMs_ = input.readInt64();
                                break;
                            case FINGERPRINT_ENROLLING_VALUE:
                                this.bitField0_ |= 134217728;
                                this.lastSleepTimeMs_ = input.readInt64();
                                break;
                            case FINGERPRINT_ENROLL_FINISH_SETUP_VALUE:
                                this.bitField0_ |= 268435456;
                                this.lastUserActivityTimeMs_ = input.readInt64();
                                break;
                            case 256:
                                this.bitField0_ |= 536870912;
                                this.lastUserActivityTimeNoChangeLightsMs_ = input.readInt64();
                                break;
                            case 264:
                                this.bitField0_ |= 1073741824;
                                this.lastInteractivePowerHintTimeMs_ = input.readInt64();
                                break;
                            case 272:
                                this.bitField0_ |= Integer.MIN_VALUE;
                                this.lastScreenBrightnessBoostTimeMs_ = input.readInt64();
                                break;
                            case 280:
                                this.bitField1_ |= 1;
                                this.isScreenBrightnessBoostInProgress_ = input.readBool();
                                break;
                            case 288:
                                this.bitField1_ |= 2;
                                this.isDisplayReady_ = input.readBool();
                                break;
                            case 296:
                                this.bitField1_ |= 4;
                                this.isHoldingWakeLockSuspendBlocker_ = input.readBool();
                                break;
                            case 304:
                                this.bitField1_ |= 8;
                                this.isHoldingDisplaySuspendBlocker_ = input.readBool();
                                break;
                            case 314:
                                PowerServiceSettingsAndConfigurationDumpProto.Builder subBuilder4 = null;
                                if ((this.bitField1_ & 16) == 16) {
                                    subBuilder4 = (PowerServiceSettingsAndConfigurationDumpProto.Builder) this.settingsAndConfiguration_.toBuilder();
                                }
                                this.settingsAndConfiguration_ = (PowerServiceSettingsAndConfigurationDumpProto) input.readMessage(PowerServiceSettingsAndConfigurationDumpProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.settingsAndConfiguration_);
                                    this.settingsAndConfiguration_ = (PowerServiceSettingsAndConfigurationDumpProto) subBuilder4.buildPartial();
                                }
                                this.bitField1_ |= 16;
                                break;
                            case 320:
                                this.bitField1_ |= 32;
                                this.sleepTimeoutMs_ = input.readSInt32();
                                break;
                            case 328:
                                this.bitField1_ |= 64;
                                this.screenOffTimeoutMs_ = input.readInt32();
                                break;
                            case SOUND_VALUE:
                                this.bitField1_ |= 128;
                                this.screenDimDurationMs_ = input.readInt32();
                                break;
                            case USER_LOCALE_LIST_VALUE:
                                this.bitField1_ |= 256;
                                this.areUidsChanging_ = input.readBool();
                                break;
                            case 352:
                                this.bitField1_ |= 512;
                                this.areUidsChanged_ = input.readBool();
                                break;
                            case 362:
                                if (!this.uidStates_.isModifiable()) {
                                    this.uidStates_ = GeneratedMessageLite.mutableCopy(this.uidStates_);
                                }
                                this.uidStates_.add((UidStateProto) input.readMessage(UidStateProto.parser(), extensionRegistry));
                                break;
                            case SUW_ACCESSIBILITY_DISPLAY_SIZE_VALUE:
                                LooperProto.Builder subBuilder5 = null;
                                if ((this.bitField1_ & 1024) == 1024) {
                                    subBuilder5 = (LooperProto.Builder) this.looper_.toBuilder();
                                }
                                this.looper_ = (LooperProto) input.readMessage(LooperProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.looper_);
                                    this.looper_ = (LooperProto) subBuilder5.buildPartial();
                                }
                                this.bitField1_ |= 1024;
                                break;
                            case SETTINGS_CONDITION_BACKGROUND_DATA_VALUE:
                                if (!this.wakeLocks_.isModifiable()) {
                                    this.wakeLocks_ = GeneratedMessageLite.mutableCopy(this.wakeLocks_);
                                }
                                this.wakeLocks_.add((WakeLockProto) input.readMessage(WakeLockProto.parser(), extensionRegistry));
                                break;
                            case 386:
                                if (!this.suspendBlockers_.isModifiable()) {
                                    this.suspendBlockers_ = GeneratedMessageLite.mutableCopy(this.suspendBlockers_);
                                }
                                this.suspendBlockers_.add((SuspendBlockerProto) input.readMessage(SuspendBlockerProto.parser(), extensionRegistry));
                                break;
                            case ACTION_DATA_SAVER_MODE_VALUE:
                                WirelessChargerDetectorProto.Builder subBuilder6 = null;
                                if ((this.bitField1_ & 2048) == 2048) {
                                    subBuilder6 = (WirelessChargerDetectorProto.Builder) this.wirelessChargerDetector_.toBuilder();
                                }
                                this.wirelessChargerDetector_ = (WirelessChargerDetectorProto) input.readMessage(WirelessChargerDetectorProto.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.wirelessChargerDetector_);
                                    this.wirelessChargerDetector_ = (WirelessChargerDetectorProto) subBuilder6.buildPartial();
                                }
                                this.bitField1_ = 2048 | this.bitField1_;
                                break;
                            case CONVERT_FBE_VALUE:
                                BatterySaverStateMachineProto.Builder subBuilder7 = null;
                                if ((this.bitField1_ & 4096) == 4096) {
                                    subBuilder7 = (BatterySaverStateMachineProto.Builder) this.batterySaverStateMachine_.toBuilder();
                                }
                                this.batterySaverStateMachine_ = (BatterySaverStateMachineProto) input.readMessage(BatterySaverStateMachineProto.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.batterySaverStateMachine_);
                                    this.batterySaverStateMachine_ = (BatterySaverStateMachineProto) subBuilder7.buildPartial();
                                }
                                this.bitField1_ = 4096 | this.bitField1_;
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
                    synchronized (PowerManagerServiceDumpProto.class) {
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

    public static PowerManagerServiceDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PowerManagerServiceDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
