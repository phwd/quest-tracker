package android.providers.settings;

import android.providers.settings.SettingProto;
import android.providers.settings.SettingsOperationProto;
import android.telephony.DataConnectionPowerStateEnum;
import com.android.os.AtomsProto;
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

public final class SystemSettingsProto extends GeneratedMessageLite<SystemSettingsProto, Builder> implements SystemSettingsProtoOrBuilder {
    public static final int ADVANCED_SETTINGS_FIELD_NUMBER = 2;
    public static final int ALARM_FIELD_NUMBER = 3;
    public static final int BLUETOOTH_FIELD_NUMBER = 4;
    public static final int DATE_FORMAT_FIELD_NUMBER = 5;
    private static final SystemSettingsProto DEFAULT_INSTANCE = new SystemSettingsProto();
    public static final int DEVELOPER_OPTIONS_FIELD_NUMBER = 7;
    public static final int DISPLAY_COLOR_MODE_FIELD_NUMBER = 6;
    public static final int DTMF_TONE_FIELD_NUMBER = 8;
    public static final int EGG_MODE_FIELD_NUMBER = 9;
    public static final int END_BUTTON_BEHAVIOR_FIELD_NUMBER = 10;
    public static final int FONT_SCALE_FIELD_NUMBER = 11;
    public static final int HAPTIC_FEEDBACK_FIELD_NUMBER = 12;
    public static final int HEARING_AID_FIELD_NUMBER = 13;
    public static final int HISTORICAL_OPERATIONS_FIELD_NUMBER = 1;
    public static final int LOCKSCREEN_FIELD_NUMBER = 15;
    public static final int LOCK_TO_APP_ENABLED_FIELD_NUMBER = 14;
    public static final int MEDIA_BUTTON_RECEIVER_FIELD_NUMBER = 16;
    public static final int NOTIFICATION_FIELD_NUMBER = 17;
    private static volatile Parser<SystemSettingsProto> PARSER = null;
    public static final int POINTER_SPEED_FIELD_NUMBER = 18;
    public static final int RINGTONE_FIELD_NUMBER = 19;
    public static final int ROTATION_FIELD_NUMBER = 20;
    public static final int SCREEN_FIELD_NUMBER = 22;
    public static final int SETUP_WIZARD_HAS_RUN_FIELD_NUMBER = 23;
    public static final int SHOW_BATTERY_PERCENT_FIELD_NUMBER = 24;
    public static final int SHOW_GTALK_SERVICE_STATUS_FIELD_NUMBER = 25;
    public static final int SIP_FIELD_NUMBER = 26;
    public static final int SOUND_EFFECTS_ENABLED_FIELD_NUMBER = 27;
    public static final int SYSTEM_LOCALES_FIELD_NUMBER = 28;
    public static final int TEXT_FIELD_NUMBER = 29;
    public static final int TIME_12_24_FIELD_NUMBER = 30;
    public static final int TTY_MODE_FIELD_NUMBER = 31;
    public static final int VIBRATE_FIELD_NUMBER = 32;
    public static final int VOLUME_FIELD_NUMBER = 33;
    public static final int WHEN_TO_MAKE_WIFI_CALLS_FIELD_NUMBER = 34;
    private SettingProto advancedSettings_;
    private Alarm alarm_;
    private int bitField0_;
    private Bluetooth bluetooth_;
    private SettingProto dateFormat_;
    private DevOptions developerOptions_;
    private SettingProto displayColorMode_;
    private DtmfTone dtmfTone_;
    private SettingProto eggMode_;
    private SettingProto endButtonBehavior_;
    private SettingProto fontScale_;
    private HapticFeedback hapticFeedback_;
    private SettingProto hearingAid_;
    private Internal.ProtobufList<SettingsOperationProto> historicalOperations_ = emptyProtobufList();
    private SettingProto lockToAppEnabled_;
    private Lockscreen lockscreen_;
    private SettingProto mediaButtonReceiver_;
    private Notification notification_;
    private SettingProto pointerSpeed_;
    private Ringtone ringtone_;
    private Rotation rotation_;
    private Screen screen_;
    private SettingProto setupWizardHasRun_;
    private SettingProto showBatteryPercent_;
    private SettingProto showGtalkServiceStatus_;
    private Sip sip_;
    private SettingProto soundEffectsEnabled_;
    private SettingProto systemLocales_;
    private Text text_;
    private SettingProto time1224_;
    private SettingProto ttyMode_;
    private Vibrate vibrate_;
    private Volume volume_;
    private SettingProto whenToMakeWifiCalls_;

    public interface AlarmOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAlertCache();

        SettingProto getDefaultUri();

        boolean hasAlertCache();

        boolean hasDefaultUri();
    }

    public interface BluetoothOrBuilder extends MessageLiteOrBuilder {
        SettingProto getDiscoverability();

        SettingProto getDiscoverabilityTimeoutSecs();

        boolean hasDiscoverability();

        boolean hasDiscoverabilityTimeoutSecs();
    }

    public interface DevOptionsOrBuilder extends MessageLiteOrBuilder {
        SettingProto getPointerLocation();

        SettingProto getShowTouches();

        SettingProto getWindowOrientationListenerLog();

        boolean hasPointerLocation();

        boolean hasShowTouches();

        boolean hasWindowOrientationListenerLog();
    }

    public interface DtmfToneOrBuilder extends MessageLiteOrBuilder {
        SettingProto getPlayWhenDialing();

        SettingProto getTypePlayedWhenDialing();

        boolean hasPlayWhenDialing();

        boolean hasTypePlayedWhenDialing();
    }

    public interface HapticFeedbackOrBuilder extends MessageLiteOrBuilder {
        SettingProto getEnabled();

        SettingProto getIntensity();

        boolean hasEnabled();

        boolean hasIntensity();
    }

    public interface LockscreenOrBuilder extends MessageLiteOrBuilder {
        SettingProto getDisabled();

        SettingProto getSoundsEnabled();

        boolean hasDisabled();

        boolean hasSoundsEnabled();
    }

    public interface NotificationOrBuilder extends MessageLiteOrBuilder {
        SettingProto getLightPulse();

        SettingProto getSound();

        SettingProto getSoundCache();

        SettingProto getVibrationIntensity();

        boolean hasLightPulse();

        boolean hasSound();

        boolean hasSoundCache();

        boolean hasVibrationIntensity();
    }

    public interface RingtoneOrBuilder extends MessageLiteOrBuilder {
        SettingProto getCache();

        SettingProto getDefaultUri();

        boolean hasCache();

        boolean hasDefaultUri();
    }

    public interface RotationOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAccelerometerRotation();

        SettingProto getHideRotationLockToggleForAccessibility();

        SettingProto getUserRotation();

        boolean hasAccelerometerRotation();

        boolean hasHideRotationLockToggleForAccessibility();

        boolean hasUserRotation();
    }

    public interface ScreenOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAutoBrightnessAdj();

        SettingProto getBrightness();

        SettingProto getBrightnessForVr();

        SettingProto getBrightnessMode();

        SettingProto getOffTimeout();

        boolean hasAutoBrightnessAdj();

        boolean hasBrightness();

        boolean hasBrightnessForVr();

        boolean hasBrightnessMode();

        boolean hasOffTimeout();
    }

    public interface SipOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAddressOnly();

        SettingProto getAlways();

        SettingProto getCallOptions();

        SettingProto getReceiveCalls();

        boolean hasAddressOnly();

        boolean hasAlways();

        boolean hasCallOptions();

        boolean hasReceiveCalls();
    }

    public interface TextOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAutoCaps();

        SettingProto getAutoPunctuate();

        SettingProto getAutoReplace();

        SettingProto getShowPassword();

        boolean hasAutoCaps();

        boolean hasAutoPunctuate();

        boolean hasAutoReplace();

        boolean hasShowPassword();
    }

    public interface VibrateOrBuilder extends MessageLiteOrBuilder {
        SettingProto getInSilent();

        SettingProto getInputDevices();

        SettingProto getOn();

        SettingProto getWhenRinging();

        boolean hasInSilent();

        boolean hasInputDevices();

        boolean hasOn();

        boolean hasWhenRinging();
    }

    public interface VolumeOrBuilder extends MessageLiteOrBuilder {
        SettingProto getAccessibility();

        SettingProto getAlarm();

        SettingProto getBluetoothSco();

        SettingProto getMaster();

        SettingProto getMasterBalance();

        SettingProto getMasterMono();

        SettingProto getModeRingerStreamsAffected();

        SettingProto getMusic();

        SettingProto getMuteStreamsAffected();

        SettingProto getNotification();

        SettingProto getRing();

        SettingProto getSystem();

        SettingProto getVoice();

        boolean hasAccessibility();

        boolean hasAlarm();

        boolean hasBluetoothSco();

        boolean hasMaster();

        boolean hasMasterBalance();

        boolean hasMasterMono();

        boolean hasModeRingerStreamsAffected();

        boolean hasMusic();

        boolean hasMuteStreamsAffected();

        boolean hasNotification();

        boolean hasRing();

        boolean hasSystem();

        boolean hasVoice();
    }

    private SystemSettingsProto() {
    }

    public static final class Alarm extends GeneratedMessageLite<Alarm, Builder> implements AlarmOrBuilder {
        public static final int ALERT_CACHE_FIELD_NUMBER = 2;
        private static final Alarm DEFAULT_INSTANCE = new Alarm();
        public static final int DEFAULT_URI_FIELD_NUMBER = 1;
        private static volatile Parser<Alarm> PARSER;
        private SettingProto alertCache_;
        private int bitField0_;
        private SettingProto defaultUri_;

        private Alarm() {
        }

        @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
        public boolean hasDefaultUri() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
        public SettingProto getDefaultUri() {
            SettingProto settingProto = this.defaultUri_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDefaultUri(SettingProto value) {
            if (value != null) {
                this.defaultUri_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDefaultUri(SettingProto.Builder builderForValue) {
            this.defaultUri_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDefaultUri(SettingProto value) {
            SettingProto settingProto = this.defaultUri_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.defaultUri_ = value;
            } else {
                this.defaultUri_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.defaultUri_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDefaultUri() {
            this.defaultUri_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
        public boolean hasAlertCache() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
        public SettingProto getAlertCache() {
            SettingProto settingProto = this.alertCache_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlertCache(SettingProto value) {
            if (value != null) {
                this.alertCache_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlertCache(SettingProto.Builder builderForValue) {
            this.alertCache_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAlertCache(SettingProto value) {
            SettingProto settingProto = this.alertCache_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.alertCache_ = value;
            } else {
                this.alertCache_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.alertCache_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlertCache() {
            this.alertCache_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getDefaultUri());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getAlertCache());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getDefaultUri());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getAlertCache());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Alarm parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alarm parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alarm parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Alarm parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Alarm parseFrom(InputStream input) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alarm parseDelimitedFrom(InputStream input) throws IOException {
            return (Alarm) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Alarm parseFrom(CodedInputStream input) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Alarm parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Alarm) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Alarm prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Alarm, Builder> implements AlarmOrBuilder {
            private Builder() {
                super(Alarm.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
            public boolean hasDefaultUri() {
                return ((Alarm) this.instance).hasDefaultUri();
            }

            @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
            public SettingProto getDefaultUri() {
                return ((Alarm) this.instance).getDefaultUri();
            }

            public Builder setDefaultUri(SettingProto value) {
                copyOnWrite();
                ((Alarm) this.instance).setDefaultUri((Alarm) value);
                return this;
            }

            public Builder setDefaultUri(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Alarm) this.instance).setDefaultUri((Alarm) builderForValue);
                return this;
            }

            public Builder mergeDefaultUri(SettingProto value) {
                copyOnWrite();
                ((Alarm) this.instance).mergeDefaultUri(value);
                return this;
            }

            public Builder clearDefaultUri() {
                copyOnWrite();
                ((Alarm) this.instance).clearDefaultUri();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
            public boolean hasAlertCache() {
                return ((Alarm) this.instance).hasAlertCache();
            }

            @Override // android.providers.settings.SystemSettingsProto.AlarmOrBuilder
            public SettingProto getAlertCache() {
                return ((Alarm) this.instance).getAlertCache();
            }

            public Builder setAlertCache(SettingProto value) {
                copyOnWrite();
                ((Alarm) this.instance).setAlertCache((Alarm) value);
                return this;
            }

            public Builder setAlertCache(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Alarm) this.instance).setAlertCache((Alarm) builderForValue);
                return this;
            }

            public Builder mergeAlertCache(SettingProto value) {
                copyOnWrite();
                ((Alarm) this.instance).mergeAlertCache(value);
                return this;
            }

            public Builder clearAlertCache() {
                copyOnWrite();
                ((Alarm) this.instance).clearAlertCache();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Alarm();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Alarm other = (Alarm) arg1;
                    this.defaultUri_ = (SettingProto) visitor.visitMessage(this.defaultUri_, other.defaultUri_);
                    this.alertCache_ = (SettingProto) visitor.visitMessage(this.alertCache_, other.alertCache_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.defaultUri_.toBuilder();
                                }
                                this.defaultUri_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.defaultUri_);
                                    this.defaultUri_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.alertCache_.toBuilder();
                                }
                                this.alertCache_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.alertCache_);
                                    this.alertCache_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (Alarm.class) {
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

        public static Alarm getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Alarm> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Bluetooth extends GeneratedMessageLite<Bluetooth, Builder> implements BluetoothOrBuilder {
        private static final Bluetooth DEFAULT_INSTANCE = new Bluetooth();
        public static final int DISCOVERABILITY_FIELD_NUMBER = 1;
        public static final int DISCOVERABILITY_TIMEOUT_SECS_FIELD_NUMBER = 2;
        private static volatile Parser<Bluetooth> PARSER;
        private int bitField0_;
        private SettingProto discoverabilityTimeoutSecs_;
        private SettingProto discoverability_;

        private Bluetooth() {
        }

        @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
        public boolean hasDiscoverability() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
        public SettingProto getDiscoverability() {
            SettingProto settingProto = this.discoverability_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDiscoverability(SettingProto value) {
            if (value != null) {
                this.discoverability_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDiscoverability(SettingProto.Builder builderForValue) {
            this.discoverability_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDiscoverability(SettingProto value) {
            SettingProto settingProto = this.discoverability_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.discoverability_ = value;
            } else {
                this.discoverability_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.discoverability_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDiscoverability() {
            this.discoverability_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
        public boolean hasDiscoverabilityTimeoutSecs() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
        public SettingProto getDiscoverabilityTimeoutSecs() {
            SettingProto settingProto = this.discoverabilityTimeoutSecs_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDiscoverabilityTimeoutSecs(SettingProto value) {
            if (value != null) {
                this.discoverabilityTimeoutSecs_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDiscoverabilityTimeoutSecs(SettingProto.Builder builderForValue) {
            this.discoverabilityTimeoutSecs_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDiscoverabilityTimeoutSecs(SettingProto value) {
            SettingProto settingProto = this.discoverabilityTimeoutSecs_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.discoverabilityTimeoutSecs_ = value;
            } else {
                this.discoverabilityTimeoutSecs_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.discoverabilityTimeoutSecs_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDiscoverabilityTimeoutSecs() {
            this.discoverabilityTimeoutSecs_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getDiscoverability());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getDiscoverabilityTimeoutSecs());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getDiscoverability());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getDiscoverabilityTimeoutSecs());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Bluetooth parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Bluetooth parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Bluetooth parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Bluetooth parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Bluetooth parseFrom(InputStream input) throws IOException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Bluetooth parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Bluetooth parseDelimitedFrom(InputStream input) throws IOException {
            return (Bluetooth) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Bluetooth parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Bluetooth) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Bluetooth parseFrom(CodedInputStream input) throws IOException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Bluetooth parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Bluetooth) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Bluetooth prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Bluetooth, Builder> implements BluetoothOrBuilder {
            private Builder() {
                super(Bluetooth.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
            public boolean hasDiscoverability() {
                return ((Bluetooth) this.instance).hasDiscoverability();
            }

            @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
            public SettingProto getDiscoverability() {
                return ((Bluetooth) this.instance).getDiscoverability();
            }

            public Builder setDiscoverability(SettingProto value) {
                copyOnWrite();
                ((Bluetooth) this.instance).setDiscoverability((Bluetooth) value);
                return this;
            }

            public Builder setDiscoverability(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Bluetooth) this.instance).setDiscoverability((Bluetooth) builderForValue);
                return this;
            }

            public Builder mergeDiscoverability(SettingProto value) {
                copyOnWrite();
                ((Bluetooth) this.instance).mergeDiscoverability(value);
                return this;
            }

            public Builder clearDiscoverability() {
                copyOnWrite();
                ((Bluetooth) this.instance).clearDiscoverability();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
            public boolean hasDiscoverabilityTimeoutSecs() {
                return ((Bluetooth) this.instance).hasDiscoverabilityTimeoutSecs();
            }

            @Override // android.providers.settings.SystemSettingsProto.BluetoothOrBuilder
            public SettingProto getDiscoverabilityTimeoutSecs() {
                return ((Bluetooth) this.instance).getDiscoverabilityTimeoutSecs();
            }

            public Builder setDiscoverabilityTimeoutSecs(SettingProto value) {
                copyOnWrite();
                ((Bluetooth) this.instance).setDiscoverabilityTimeoutSecs((Bluetooth) value);
                return this;
            }

            public Builder setDiscoverabilityTimeoutSecs(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Bluetooth) this.instance).setDiscoverabilityTimeoutSecs((Bluetooth) builderForValue);
                return this;
            }

            public Builder mergeDiscoverabilityTimeoutSecs(SettingProto value) {
                copyOnWrite();
                ((Bluetooth) this.instance).mergeDiscoverabilityTimeoutSecs(value);
                return this;
            }

            public Builder clearDiscoverabilityTimeoutSecs() {
                copyOnWrite();
                ((Bluetooth) this.instance).clearDiscoverabilityTimeoutSecs();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Bluetooth();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Bluetooth other = (Bluetooth) arg1;
                    this.discoverability_ = (SettingProto) visitor.visitMessage(this.discoverability_, other.discoverability_);
                    this.discoverabilityTimeoutSecs_ = (SettingProto) visitor.visitMessage(this.discoverabilityTimeoutSecs_, other.discoverabilityTimeoutSecs_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.discoverability_.toBuilder();
                                }
                                this.discoverability_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.discoverability_);
                                    this.discoverability_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.discoverabilityTimeoutSecs_.toBuilder();
                                }
                                this.discoverabilityTimeoutSecs_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.discoverabilityTimeoutSecs_);
                                    this.discoverabilityTimeoutSecs_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (Bluetooth.class) {
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

        public static Bluetooth getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Bluetooth> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DevOptions extends GeneratedMessageLite<DevOptions, Builder> implements DevOptionsOrBuilder {
        private static final DevOptions DEFAULT_INSTANCE = new DevOptions();
        private static volatile Parser<DevOptions> PARSER = null;
        public static final int POINTER_LOCATION_FIELD_NUMBER = 1;
        public static final int SHOW_TOUCHES_FIELD_NUMBER = 2;
        public static final int WINDOW_ORIENTATION_LISTENER_LOG_FIELD_NUMBER = 3;
        private int bitField0_;
        private SettingProto pointerLocation_;
        private SettingProto showTouches_;
        private SettingProto windowOrientationListenerLog_;

        private DevOptions() {
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public boolean hasPointerLocation() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public SettingProto getPointerLocation() {
            SettingProto settingProto = this.pointerLocation_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPointerLocation(SettingProto value) {
            if (value != null) {
                this.pointerLocation_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPointerLocation(SettingProto.Builder builderForValue) {
            this.pointerLocation_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePointerLocation(SettingProto value) {
            SettingProto settingProto = this.pointerLocation_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.pointerLocation_ = value;
            } else {
                this.pointerLocation_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.pointerLocation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPointerLocation() {
            this.pointerLocation_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public boolean hasShowTouches() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public SettingProto getShowTouches() {
            SettingProto settingProto = this.showTouches_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShowTouches(SettingProto value) {
            if (value != null) {
                this.showTouches_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShowTouches(SettingProto.Builder builderForValue) {
            this.showTouches_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeShowTouches(SettingProto value) {
            SettingProto settingProto = this.showTouches_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.showTouches_ = value;
            } else {
                this.showTouches_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.showTouches_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShowTouches() {
            this.showTouches_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public boolean hasWindowOrientationListenerLog() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
        public SettingProto getWindowOrientationListenerLog() {
            SettingProto settingProto = this.windowOrientationListenerLog_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWindowOrientationListenerLog(SettingProto value) {
            if (value != null) {
                this.windowOrientationListenerLog_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWindowOrientationListenerLog(SettingProto.Builder builderForValue) {
            this.windowOrientationListenerLog_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeWindowOrientationListenerLog(SettingProto value) {
            SettingProto settingProto = this.windowOrientationListenerLog_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.windowOrientationListenerLog_ = value;
            } else {
                this.windowOrientationListenerLog_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.windowOrientationListenerLog_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWindowOrientationListenerLog() {
            this.windowOrientationListenerLog_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getPointerLocation());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getShowTouches());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getWindowOrientationListenerLog());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getPointerLocation());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getShowTouches());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getWindowOrientationListenerLog());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DevOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DevOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DevOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DevOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DevOptions parseFrom(InputStream input) throws IOException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DevOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DevOptions parseDelimitedFrom(InputStream input) throws IOException {
            return (DevOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DevOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DevOptions) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DevOptions parseFrom(CodedInputStream input) throws IOException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DevOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DevOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DevOptions prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DevOptions, Builder> implements DevOptionsOrBuilder {
            private Builder() {
                super(DevOptions.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public boolean hasPointerLocation() {
                return ((DevOptions) this.instance).hasPointerLocation();
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public SettingProto getPointerLocation() {
                return ((DevOptions) this.instance).getPointerLocation();
            }

            public Builder setPointerLocation(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).setPointerLocation((DevOptions) value);
                return this;
            }

            public Builder setPointerLocation(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((DevOptions) this.instance).setPointerLocation((DevOptions) builderForValue);
                return this;
            }

            public Builder mergePointerLocation(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).mergePointerLocation(value);
                return this;
            }

            public Builder clearPointerLocation() {
                copyOnWrite();
                ((DevOptions) this.instance).clearPointerLocation();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public boolean hasShowTouches() {
                return ((DevOptions) this.instance).hasShowTouches();
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public SettingProto getShowTouches() {
                return ((DevOptions) this.instance).getShowTouches();
            }

            public Builder setShowTouches(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).setShowTouches((DevOptions) value);
                return this;
            }

            public Builder setShowTouches(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((DevOptions) this.instance).setShowTouches((DevOptions) builderForValue);
                return this;
            }

            public Builder mergeShowTouches(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).mergeShowTouches(value);
                return this;
            }

            public Builder clearShowTouches() {
                copyOnWrite();
                ((DevOptions) this.instance).clearShowTouches();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public boolean hasWindowOrientationListenerLog() {
                return ((DevOptions) this.instance).hasWindowOrientationListenerLog();
            }

            @Override // android.providers.settings.SystemSettingsProto.DevOptionsOrBuilder
            public SettingProto getWindowOrientationListenerLog() {
                return ((DevOptions) this.instance).getWindowOrientationListenerLog();
            }

            public Builder setWindowOrientationListenerLog(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).setWindowOrientationListenerLog((DevOptions) value);
                return this;
            }

            public Builder setWindowOrientationListenerLog(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((DevOptions) this.instance).setWindowOrientationListenerLog((DevOptions) builderForValue);
                return this;
            }

            public Builder mergeWindowOrientationListenerLog(SettingProto value) {
                copyOnWrite();
                ((DevOptions) this.instance).mergeWindowOrientationListenerLog(value);
                return this;
            }

            public Builder clearWindowOrientationListenerLog() {
                copyOnWrite();
                ((DevOptions) this.instance).clearWindowOrientationListenerLog();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DevOptions();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DevOptions other = (DevOptions) arg1;
                    this.pointerLocation_ = (SettingProto) visitor.visitMessage(this.pointerLocation_, other.pointerLocation_);
                    this.showTouches_ = (SettingProto) visitor.visitMessage(this.showTouches_, other.showTouches_);
                    this.windowOrientationListenerLog_ = (SettingProto) visitor.visitMessage(this.windowOrientationListenerLog_, other.windowOrientationListenerLog_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.pointerLocation_.toBuilder();
                                }
                                this.pointerLocation_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.pointerLocation_);
                                    this.pointerLocation_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.showTouches_.toBuilder();
                                }
                                this.showTouches_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.showTouches_);
                                    this.showTouches_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.windowOrientationListenerLog_.toBuilder();
                                }
                                this.windowOrientationListenerLog_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.windowOrientationListenerLog_);
                                    this.windowOrientationListenerLog_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
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
                        synchronized (DevOptions.class) {
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

        public static DevOptions getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DevOptions> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class DtmfTone extends GeneratedMessageLite<DtmfTone, Builder> implements DtmfToneOrBuilder {
        private static final DtmfTone DEFAULT_INSTANCE = new DtmfTone();
        private static volatile Parser<DtmfTone> PARSER = null;
        public static final int PLAY_WHEN_DIALING_FIELD_NUMBER = 1;
        public static final int TYPE_PLAYED_WHEN_DIALING_FIELD_NUMBER = 2;
        private int bitField0_;
        private SettingProto playWhenDialing_;
        private SettingProto typePlayedWhenDialing_;

        private DtmfTone() {
        }

        @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
        public boolean hasPlayWhenDialing() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
        public SettingProto getPlayWhenDialing() {
            SettingProto settingProto = this.playWhenDialing_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPlayWhenDialing(SettingProto value) {
            if (value != null) {
                this.playWhenDialing_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setPlayWhenDialing(SettingProto.Builder builderForValue) {
            this.playWhenDialing_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergePlayWhenDialing(SettingProto value) {
            SettingProto settingProto = this.playWhenDialing_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.playWhenDialing_ = value;
            } else {
                this.playWhenDialing_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.playWhenDialing_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearPlayWhenDialing() {
            this.playWhenDialing_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
        public boolean hasTypePlayedWhenDialing() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
        public SettingProto getTypePlayedWhenDialing() {
            SettingProto settingProto = this.typePlayedWhenDialing_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTypePlayedWhenDialing(SettingProto value) {
            if (value != null) {
                this.typePlayedWhenDialing_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setTypePlayedWhenDialing(SettingProto.Builder builderForValue) {
            this.typePlayedWhenDialing_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeTypePlayedWhenDialing(SettingProto value) {
            SettingProto settingProto = this.typePlayedWhenDialing_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.typePlayedWhenDialing_ = value;
            } else {
                this.typePlayedWhenDialing_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.typePlayedWhenDialing_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearTypePlayedWhenDialing() {
            this.typePlayedWhenDialing_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getPlayWhenDialing());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getTypePlayedWhenDialing());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getPlayWhenDialing());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getTypePlayedWhenDialing());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static DtmfTone parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DtmfTone parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DtmfTone parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static DtmfTone parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static DtmfTone parseFrom(InputStream input) throws IOException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DtmfTone parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DtmfTone parseDelimitedFrom(InputStream input) throws IOException {
            return (DtmfTone) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static DtmfTone parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DtmfTone) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static DtmfTone parseFrom(CodedInputStream input) throws IOException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static DtmfTone parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (DtmfTone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DtmfTone prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DtmfTone, Builder> implements DtmfToneOrBuilder {
            private Builder() {
                super(DtmfTone.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
            public boolean hasPlayWhenDialing() {
                return ((DtmfTone) this.instance).hasPlayWhenDialing();
            }

            @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
            public SettingProto getPlayWhenDialing() {
                return ((DtmfTone) this.instance).getPlayWhenDialing();
            }

            public Builder setPlayWhenDialing(SettingProto value) {
                copyOnWrite();
                ((DtmfTone) this.instance).setPlayWhenDialing((DtmfTone) value);
                return this;
            }

            public Builder setPlayWhenDialing(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((DtmfTone) this.instance).setPlayWhenDialing((DtmfTone) builderForValue);
                return this;
            }

            public Builder mergePlayWhenDialing(SettingProto value) {
                copyOnWrite();
                ((DtmfTone) this.instance).mergePlayWhenDialing(value);
                return this;
            }

            public Builder clearPlayWhenDialing() {
                copyOnWrite();
                ((DtmfTone) this.instance).clearPlayWhenDialing();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
            public boolean hasTypePlayedWhenDialing() {
                return ((DtmfTone) this.instance).hasTypePlayedWhenDialing();
            }

            @Override // android.providers.settings.SystemSettingsProto.DtmfToneOrBuilder
            public SettingProto getTypePlayedWhenDialing() {
                return ((DtmfTone) this.instance).getTypePlayedWhenDialing();
            }

            public Builder setTypePlayedWhenDialing(SettingProto value) {
                copyOnWrite();
                ((DtmfTone) this.instance).setTypePlayedWhenDialing((DtmfTone) value);
                return this;
            }

            public Builder setTypePlayedWhenDialing(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((DtmfTone) this.instance).setTypePlayedWhenDialing((DtmfTone) builderForValue);
                return this;
            }

            public Builder mergeTypePlayedWhenDialing(SettingProto value) {
                copyOnWrite();
                ((DtmfTone) this.instance).mergeTypePlayedWhenDialing(value);
                return this;
            }

            public Builder clearTypePlayedWhenDialing() {
                copyOnWrite();
                ((DtmfTone) this.instance).clearTypePlayedWhenDialing();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new DtmfTone();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    DtmfTone other = (DtmfTone) arg1;
                    this.playWhenDialing_ = (SettingProto) visitor.visitMessage(this.playWhenDialing_, other.playWhenDialing_);
                    this.typePlayedWhenDialing_ = (SettingProto) visitor.visitMessage(this.typePlayedWhenDialing_, other.typePlayedWhenDialing_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.playWhenDialing_.toBuilder();
                                }
                                this.playWhenDialing_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.playWhenDialing_);
                                    this.playWhenDialing_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.typePlayedWhenDialing_.toBuilder();
                                }
                                this.typePlayedWhenDialing_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.typePlayedWhenDialing_);
                                    this.typePlayedWhenDialing_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (DtmfTone.class) {
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

        public static DtmfTone getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DtmfTone> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class HapticFeedback extends GeneratedMessageLite<HapticFeedback, Builder> implements HapticFeedbackOrBuilder {
        private static final HapticFeedback DEFAULT_INSTANCE = new HapticFeedback();
        public static final int ENABLED_FIELD_NUMBER = 1;
        public static final int INTENSITY_FIELD_NUMBER = 2;
        private static volatile Parser<HapticFeedback> PARSER;
        private int bitField0_;
        private SettingProto enabled_;
        private SettingProto intensity_;

        private HapticFeedback() {
        }

        @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
        public boolean hasEnabled() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
        public SettingProto getEnabled() {
            SettingProto settingProto = this.enabled_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnabled(SettingProto value) {
            if (value != null) {
                this.enabled_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setEnabled(SettingProto.Builder builderForValue) {
            this.enabled_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeEnabled(SettingProto value) {
            SettingProto settingProto = this.enabled_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.enabled_ = value;
            } else {
                this.enabled_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.enabled_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearEnabled() {
            this.enabled_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
        public boolean hasIntensity() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
        public SettingProto getIntensity() {
            SettingProto settingProto = this.intensity_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntensity(SettingProto value) {
            if (value != null) {
                this.intensity_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setIntensity(SettingProto.Builder builderForValue) {
            this.intensity_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeIntensity(SettingProto value) {
            SettingProto settingProto = this.intensity_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.intensity_ = value;
            } else {
                this.intensity_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.intensity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearIntensity() {
            this.intensity_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getEnabled());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getIntensity());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getEnabled());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getIntensity());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static HapticFeedback parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HapticFeedback parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HapticFeedback parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static HapticFeedback parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static HapticFeedback parseFrom(InputStream input) throws IOException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HapticFeedback parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HapticFeedback parseDelimitedFrom(InputStream input) throws IOException {
            return (HapticFeedback) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static HapticFeedback parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HapticFeedback) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static HapticFeedback parseFrom(CodedInputStream input) throws IOException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static HapticFeedback parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (HapticFeedback) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(HapticFeedback prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<HapticFeedback, Builder> implements HapticFeedbackOrBuilder {
            private Builder() {
                super(HapticFeedback.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
            public boolean hasEnabled() {
                return ((HapticFeedback) this.instance).hasEnabled();
            }

            @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
            public SettingProto getEnabled() {
                return ((HapticFeedback) this.instance).getEnabled();
            }

            public Builder setEnabled(SettingProto value) {
                copyOnWrite();
                ((HapticFeedback) this.instance).setEnabled((HapticFeedback) value);
                return this;
            }

            public Builder setEnabled(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((HapticFeedback) this.instance).setEnabled((HapticFeedback) builderForValue);
                return this;
            }

            public Builder mergeEnabled(SettingProto value) {
                copyOnWrite();
                ((HapticFeedback) this.instance).mergeEnabled(value);
                return this;
            }

            public Builder clearEnabled() {
                copyOnWrite();
                ((HapticFeedback) this.instance).clearEnabled();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
            public boolean hasIntensity() {
                return ((HapticFeedback) this.instance).hasIntensity();
            }

            @Override // android.providers.settings.SystemSettingsProto.HapticFeedbackOrBuilder
            public SettingProto getIntensity() {
                return ((HapticFeedback) this.instance).getIntensity();
            }

            public Builder setIntensity(SettingProto value) {
                copyOnWrite();
                ((HapticFeedback) this.instance).setIntensity((HapticFeedback) value);
                return this;
            }

            public Builder setIntensity(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((HapticFeedback) this.instance).setIntensity((HapticFeedback) builderForValue);
                return this;
            }

            public Builder mergeIntensity(SettingProto value) {
                copyOnWrite();
                ((HapticFeedback) this.instance).mergeIntensity(value);
                return this;
            }

            public Builder clearIntensity() {
                copyOnWrite();
                ((HapticFeedback) this.instance).clearIntensity();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new HapticFeedback();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    HapticFeedback other = (HapticFeedback) arg1;
                    this.enabled_ = (SettingProto) visitor.visitMessage(this.enabled_, other.enabled_);
                    this.intensity_ = (SettingProto) visitor.visitMessage(this.intensity_, other.intensity_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.enabled_.toBuilder();
                                }
                                this.enabled_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.enabled_);
                                    this.enabled_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.intensity_.toBuilder();
                                }
                                this.intensity_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.intensity_);
                                    this.intensity_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (HapticFeedback.class) {
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

        public static HapticFeedback getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<HapticFeedback> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Lockscreen extends GeneratedMessageLite<Lockscreen, Builder> implements LockscreenOrBuilder {
        private static final Lockscreen DEFAULT_INSTANCE = new Lockscreen();
        public static final int DISABLED_FIELD_NUMBER = 2;
        private static volatile Parser<Lockscreen> PARSER = null;
        public static final int SOUNDS_ENABLED_FIELD_NUMBER = 1;
        private int bitField0_;
        private SettingProto disabled_;
        private SettingProto soundsEnabled_;

        private Lockscreen() {
        }

        @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
        public boolean hasSoundsEnabled() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
        public SettingProto getSoundsEnabled() {
            SettingProto settingProto = this.soundsEnabled_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSoundsEnabled(SettingProto value) {
            if (value != null) {
                this.soundsEnabled_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSoundsEnabled(SettingProto.Builder builderForValue) {
            this.soundsEnabled_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSoundsEnabled(SettingProto value) {
            SettingProto settingProto = this.soundsEnabled_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.soundsEnabled_ = value;
            } else {
                this.soundsEnabled_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.soundsEnabled_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSoundsEnabled() {
            this.soundsEnabled_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
        public boolean hasDisabled() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
        public SettingProto getDisabled() {
            SettingProto settingProto = this.disabled_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDisabled(SettingProto value) {
            if (value != null) {
                this.disabled_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDisabled(SettingProto.Builder builderForValue) {
            this.disabled_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDisabled(SettingProto value) {
            SettingProto settingProto = this.disabled_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.disabled_ = value;
            } else {
                this.disabled_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.disabled_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDisabled() {
            this.disabled_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getSoundsEnabled());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getDisabled());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getSoundsEnabled());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getDisabled());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Lockscreen parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Lockscreen parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Lockscreen parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Lockscreen parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Lockscreen parseFrom(InputStream input) throws IOException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Lockscreen parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Lockscreen parseDelimitedFrom(InputStream input) throws IOException {
            return (Lockscreen) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Lockscreen parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Lockscreen) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Lockscreen parseFrom(CodedInputStream input) throws IOException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Lockscreen parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Lockscreen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Lockscreen prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Lockscreen, Builder> implements LockscreenOrBuilder {
            private Builder() {
                super(Lockscreen.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
            public boolean hasSoundsEnabled() {
                return ((Lockscreen) this.instance).hasSoundsEnabled();
            }

            @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
            public SettingProto getSoundsEnabled() {
                return ((Lockscreen) this.instance).getSoundsEnabled();
            }

            public Builder setSoundsEnabled(SettingProto value) {
                copyOnWrite();
                ((Lockscreen) this.instance).setSoundsEnabled((Lockscreen) value);
                return this;
            }

            public Builder setSoundsEnabled(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Lockscreen) this.instance).setSoundsEnabled((Lockscreen) builderForValue);
                return this;
            }

            public Builder mergeSoundsEnabled(SettingProto value) {
                copyOnWrite();
                ((Lockscreen) this.instance).mergeSoundsEnabled(value);
                return this;
            }

            public Builder clearSoundsEnabled() {
                copyOnWrite();
                ((Lockscreen) this.instance).clearSoundsEnabled();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
            public boolean hasDisabled() {
                return ((Lockscreen) this.instance).hasDisabled();
            }

            @Override // android.providers.settings.SystemSettingsProto.LockscreenOrBuilder
            public SettingProto getDisabled() {
                return ((Lockscreen) this.instance).getDisabled();
            }

            public Builder setDisabled(SettingProto value) {
                copyOnWrite();
                ((Lockscreen) this.instance).setDisabled((Lockscreen) value);
                return this;
            }

            public Builder setDisabled(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Lockscreen) this.instance).setDisabled((Lockscreen) builderForValue);
                return this;
            }

            public Builder mergeDisabled(SettingProto value) {
                copyOnWrite();
                ((Lockscreen) this.instance).mergeDisabled(value);
                return this;
            }

            public Builder clearDisabled() {
                copyOnWrite();
                ((Lockscreen) this.instance).clearDisabled();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Lockscreen();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Lockscreen other = (Lockscreen) arg1;
                    this.soundsEnabled_ = (SettingProto) visitor.visitMessage(this.soundsEnabled_, other.soundsEnabled_);
                    this.disabled_ = (SettingProto) visitor.visitMessage(this.disabled_, other.disabled_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.soundsEnabled_.toBuilder();
                                }
                                this.soundsEnabled_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.soundsEnabled_);
                                    this.soundsEnabled_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.disabled_.toBuilder();
                                }
                                this.disabled_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.disabled_);
                                    this.disabled_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (Lockscreen.class) {
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

        public static Lockscreen getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Lockscreen> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Notification extends GeneratedMessageLite<Notification, Builder> implements NotificationOrBuilder {
        private static final Notification DEFAULT_INSTANCE = new Notification();
        public static final int LIGHT_PULSE_FIELD_NUMBER = 3;
        private static volatile Parser<Notification> PARSER = null;
        public static final int SOUND_CACHE_FIELD_NUMBER = 2;
        public static final int SOUND_FIELD_NUMBER = 1;
        public static final int VIBRATION_INTENSITY_FIELD_NUMBER = 4;
        private int bitField0_;
        private SettingProto lightPulse_;
        private SettingProto soundCache_;
        private SettingProto sound_;
        private SettingProto vibrationIntensity_;

        private Notification() {
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public boolean hasSound() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public SettingProto getSound() {
            SettingProto settingProto = this.sound_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSound(SettingProto value) {
            if (value != null) {
                this.sound_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSound(SettingProto.Builder builderForValue) {
            this.sound_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSound(SettingProto value) {
            SettingProto settingProto = this.sound_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.sound_ = value;
            } else {
                this.sound_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.sound_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSound() {
            this.sound_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public boolean hasSoundCache() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public SettingProto getSoundCache() {
            SettingProto settingProto = this.soundCache_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSoundCache(SettingProto value) {
            if (value != null) {
                this.soundCache_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSoundCache(SettingProto.Builder builderForValue) {
            this.soundCache_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSoundCache(SettingProto value) {
            SettingProto settingProto = this.soundCache_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.soundCache_ = value;
            } else {
                this.soundCache_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.soundCache_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSoundCache() {
            this.soundCache_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public boolean hasLightPulse() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public SettingProto getLightPulse() {
            SettingProto settingProto = this.lightPulse_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightPulse(SettingProto value) {
            if (value != null) {
                this.lightPulse_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setLightPulse(SettingProto.Builder builderForValue) {
            this.lightPulse_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeLightPulse(SettingProto value) {
            SettingProto settingProto = this.lightPulse_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.lightPulse_ = value;
            } else {
                this.lightPulse_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.lightPulse_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLightPulse() {
            this.lightPulse_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public boolean hasVibrationIntensity() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
        public SettingProto getVibrationIntensity() {
            SettingProto settingProto = this.vibrationIntensity_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVibrationIntensity(SettingProto value) {
            if (value != null) {
                this.vibrationIntensity_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVibrationIntensity(SettingProto.Builder builderForValue) {
            this.vibrationIntensity_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeVibrationIntensity(SettingProto value) {
            SettingProto settingProto = this.vibrationIntensity_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.vibrationIntensity_ = value;
            } else {
                this.vibrationIntensity_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.vibrationIntensity_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVibrationIntensity() {
            this.vibrationIntensity_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getSound());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getSoundCache());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getLightPulse());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getVibrationIntensity());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getSound());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getSoundCache());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getLightPulse());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getVibrationIntensity());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Notification parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Notification parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Notification parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Notification parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Notification parseFrom(InputStream input) throws IOException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Notification parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Notification parseDelimitedFrom(InputStream input) throws IOException {
            return (Notification) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Notification parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Notification) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Notification parseFrom(CodedInputStream input) throws IOException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Notification parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Notification) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Notification prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Notification, Builder> implements NotificationOrBuilder {
            private Builder() {
                super(Notification.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public boolean hasSound() {
                return ((Notification) this.instance).hasSound();
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public SettingProto getSound() {
                return ((Notification) this.instance).getSound();
            }

            public Builder setSound(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).setSound((Notification) value);
                return this;
            }

            public Builder setSound(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Notification) this.instance).setSound((Notification) builderForValue);
                return this;
            }

            public Builder mergeSound(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).mergeSound(value);
                return this;
            }

            public Builder clearSound() {
                copyOnWrite();
                ((Notification) this.instance).clearSound();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public boolean hasSoundCache() {
                return ((Notification) this.instance).hasSoundCache();
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public SettingProto getSoundCache() {
                return ((Notification) this.instance).getSoundCache();
            }

            public Builder setSoundCache(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).setSoundCache((Notification) value);
                return this;
            }

            public Builder setSoundCache(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Notification) this.instance).setSoundCache((Notification) builderForValue);
                return this;
            }

            public Builder mergeSoundCache(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).mergeSoundCache(value);
                return this;
            }

            public Builder clearSoundCache() {
                copyOnWrite();
                ((Notification) this.instance).clearSoundCache();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public boolean hasLightPulse() {
                return ((Notification) this.instance).hasLightPulse();
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public SettingProto getLightPulse() {
                return ((Notification) this.instance).getLightPulse();
            }

            public Builder setLightPulse(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).setLightPulse((Notification) value);
                return this;
            }

            public Builder setLightPulse(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Notification) this.instance).setLightPulse((Notification) builderForValue);
                return this;
            }

            public Builder mergeLightPulse(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).mergeLightPulse(value);
                return this;
            }

            public Builder clearLightPulse() {
                copyOnWrite();
                ((Notification) this.instance).clearLightPulse();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public boolean hasVibrationIntensity() {
                return ((Notification) this.instance).hasVibrationIntensity();
            }

            @Override // android.providers.settings.SystemSettingsProto.NotificationOrBuilder
            public SettingProto getVibrationIntensity() {
                return ((Notification) this.instance).getVibrationIntensity();
            }

            public Builder setVibrationIntensity(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).setVibrationIntensity((Notification) value);
                return this;
            }

            public Builder setVibrationIntensity(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Notification) this.instance).setVibrationIntensity((Notification) builderForValue);
                return this;
            }

            public Builder mergeVibrationIntensity(SettingProto value) {
                copyOnWrite();
                ((Notification) this.instance).mergeVibrationIntensity(value);
                return this;
            }

            public Builder clearVibrationIntensity() {
                copyOnWrite();
                ((Notification) this.instance).clearVibrationIntensity();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Notification();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Notification other = (Notification) arg1;
                    this.sound_ = (SettingProto) visitor.visitMessage(this.sound_, other.sound_);
                    this.soundCache_ = (SettingProto) visitor.visitMessage(this.soundCache_, other.soundCache_);
                    this.lightPulse_ = (SettingProto) visitor.visitMessage(this.lightPulse_, other.lightPulse_);
                    this.vibrationIntensity_ = (SettingProto) visitor.visitMessage(this.vibrationIntensity_, other.vibrationIntensity_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.sound_.toBuilder();
                                }
                                this.sound_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.sound_);
                                    this.sound_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.soundCache_.toBuilder();
                                }
                                this.soundCache_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.soundCache_);
                                    this.soundCache_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.lightPulse_.toBuilder();
                                }
                                this.lightPulse_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.lightPulse_);
                                    this.lightPulse_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.vibrationIntensity_.toBuilder();
                                }
                                this.vibrationIntensity_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.vibrationIntensity_);
                                    this.vibrationIntensity_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
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
                        synchronized (Notification.class) {
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

        public static Notification getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Notification> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Ringtone extends GeneratedMessageLite<Ringtone, Builder> implements RingtoneOrBuilder {
        public static final int CACHE_FIELD_NUMBER = 2;
        private static final Ringtone DEFAULT_INSTANCE = new Ringtone();
        public static final int DEFAULT_URI_FIELD_NUMBER = 1;
        private static volatile Parser<Ringtone> PARSER;
        private int bitField0_;
        private SettingProto cache_;
        private SettingProto defaultUri_;

        private Ringtone() {
        }

        @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
        public boolean hasDefaultUri() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
        public SettingProto getDefaultUri() {
            SettingProto settingProto = this.defaultUri_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDefaultUri(SettingProto value) {
            if (value != null) {
                this.defaultUri_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setDefaultUri(SettingProto.Builder builderForValue) {
            this.defaultUri_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeDefaultUri(SettingProto value) {
            SettingProto settingProto = this.defaultUri_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.defaultUri_ = value;
            } else {
                this.defaultUri_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.defaultUri_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearDefaultUri() {
            this.defaultUri_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
        public boolean hasCache() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
        public SettingProto getCache() {
            SettingProto settingProto = this.cache_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCache(SettingProto value) {
            if (value != null) {
                this.cache_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCache(SettingProto.Builder builderForValue) {
            this.cache_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeCache(SettingProto value) {
            SettingProto settingProto = this.cache_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.cache_ = value;
            } else {
                this.cache_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.cache_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCache() {
            this.cache_ = null;
            this.bitField0_ &= -3;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getDefaultUri());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getCache());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getDefaultUri());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getCache());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Ringtone parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ringtone parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ringtone parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Ringtone parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Ringtone parseFrom(InputStream input) throws IOException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ringtone parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ringtone parseDelimitedFrom(InputStream input) throws IOException {
            return (Ringtone) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Ringtone parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ringtone) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Ringtone parseFrom(CodedInputStream input) throws IOException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Ringtone parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Ringtone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Ringtone prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Ringtone, Builder> implements RingtoneOrBuilder {
            private Builder() {
                super(Ringtone.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
            public boolean hasDefaultUri() {
                return ((Ringtone) this.instance).hasDefaultUri();
            }

            @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
            public SettingProto getDefaultUri() {
                return ((Ringtone) this.instance).getDefaultUri();
            }

            public Builder setDefaultUri(SettingProto value) {
                copyOnWrite();
                ((Ringtone) this.instance).setDefaultUri((Ringtone) value);
                return this;
            }

            public Builder setDefaultUri(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Ringtone) this.instance).setDefaultUri((Ringtone) builderForValue);
                return this;
            }

            public Builder mergeDefaultUri(SettingProto value) {
                copyOnWrite();
                ((Ringtone) this.instance).mergeDefaultUri(value);
                return this;
            }

            public Builder clearDefaultUri() {
                copyOnWrite();
                ((Ringtone) this.instance).clearDefaultUri();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
            public boolean hasCache() {
                return ((Ringtone) this.instance).hasCache();
            }

            @Override // android.providers.settings.SystemSettingsProto.RingtoneOrBuilder
            public SettingProto getCache() {
                return ((Ringtone) this.instance).getCache();
            }

            public Builder setCache(SettingProto value) {
                copyOnWrite();
                ((Ringtone) this.instance).setCache((Ringtone) value);
                return this;
            }

            public Builder setCache(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Ringtone) this.instance).setCache((Ringtone) builderForValue);
                return this;
            }

            public Builder mergeCache(SettingProto value) {
                copyOnWrite();
                ((Ringtone) this.instance).mergeCache(value);
                return this;
            }

            public Builder clearCache() {
                copyOnWrite();
                ((Ringtone) this.instance).clearCache();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Ringtone();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Ringtone other = (Ringtone) arg1;
                    this.defaultUri_ = (SettingProto) visitor.visitMessage(this.defaultUri_, other.defaultUri_);
                    this.cache_ = (SettingProto) visitor.visitMessage(this.cache_, other.cache_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.defaultUri_.toBuilder();
                                }
                                this.defaultUri_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.defaultUri_);
                                    this.defaultUri_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.cache_.toBuilder();
                                }
                                this.cache_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.cache_);
                                    this.cache_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
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
                        synchronized (Ringtone.class) {
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

        public static Ringtone getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Ringtone> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Rotation extends GeneratedMessageLite<Rotation, Builder> implements RotationOrBuilder {
        public static final int ACCELEROMETER_ROTATION_FIELD_NUMBER = 1;
        private static final Rotation DEFAULT_INSTANCE = new Rotation();
        public static final int HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY_FIELD_NUMBER = 3;
        private static volatile Parser<Rotation> PARSER = null;
        public static final int USER_ROTATION_FIELD_NUMBER = 2;
        private SettingProto accelerometerRotation_;
        private int bitField0_;
        private SettingProto hideRotationLockToggleForAccessibility_;
        private SettingProto userRotation_;

        private Rotation() {
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public boolean hasAccelerometerRotation() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public SettingProto getAccelerometerRotation() {
            SettingProto settingProto = this.accelerometerRotation_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAccelerometerRotation(SettingProto value) {
            if (value != null) {
                this.accelerometerRotation_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAccelerometerRotation(SettingProto.Builder builderForValue) {
            this.accelerometerRotation_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAccelerometerRotation(SettingProto value) {
            SettingProto settingProto = this.accelerometerRotation_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.accelerometerRotation_ = value;
            } else {
                this.accelerometerRotation_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.accelerometerRotation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAccelerometerRotation() {
            this.accelerometerRotation_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public boolean hasUserRotation() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public SettingProto getUserRotation() {
            SettingProto settingProto = this.userRotation_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserRotation(SettingProto value) {
            if (value != null) {
                this.userRotation_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setUserRotation(SettingProto.Builder builderForValue) {
            this.userRotation_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeUserRotation(SettingProto value) {
            SettingProto settingProto = this.userRotation_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.userRotation_ = value;
            } else {
                this.userRotation_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.userRotation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearUserRotation() {
            this.userRotation_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public boolean hasHideRotationLockToggleForAccessibility() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
        public SettingProto getHideRotationLockToggleForAccessibility() {
            SettingProto settingProto = this.hideRotationLockToggleForAccessibility_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHideRotationLockToggleForAccessibility(SettingProto value) {
            if (value != null) {
                this.hideRotationLockToggleForAccessibility_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setHideRotationLockToggleForAccessibility(SettingProto.Builder builderForValue) {
            this.hideRotationLockToggleForAccessibility_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeHideRotationLockToggleForAccessibility(SettingProto value) {
            SettingProto settingProto = this.hideRotationLockToggleForAccessibility_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.hideRotationLockToggleForAccessibility_ = value;
            } else {
                this.hideRotationLockToggleForAccessibility_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.hideRotationLockToggleForAccessibility_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearHideRotationLockToggleForAccessibility() {
            this.hideRotationLockToggleForAccessibility_ = null;
            this.bitField0_ &= -5;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getAccelerometerRotation());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getUserRotation());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getHideRotationLockToggleForAccessibility());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getAccelerometerRotation());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getUserRotation());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getHideRotationLockToggleForAccessibility());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Rotation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Rotation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Rotation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Rotation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Rotation parseFrom(InputStream input) throws IOException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Rotation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Rotation parseDelimitedFrom(InputStream input) throws IOException {
            return (Rotation) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Rotation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Rotation) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Rotation parseFrom(CodedInputStream input) throws IOException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Rotation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Rotation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Rotation prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Rotation, Builder> implements RotationOrBuilder {
            private Builder() {
                super(Rotation.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public boolean hasAccelerometerRotation() {
                return ((Rotation) this.instance).hasAccelerometerRotation();
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public SettingProto getAccelerometerRotation() {
                return ((Rotation) this.instance).getAccelerometerRotation();
            }

            public Builder setAccelerometerRotation(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).setAccelerometerRotation((Rotation) value);
                return this;
            }

            public Builder setAccelerometerRotation(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Rotation) this.instance).setAccelerometerRotation((Rotation) builderForValue);
                return this;
            }

            public Builder mergeAccelerometerRotation(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).mergeAccelerometerRotation(value);
                return this;
            }

            public Builder clearAccelerometerRotation() {
                copyOnWrite();
                ((Rotation) this.instance).clearAccelerometerRotation();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public boolean hasUserRotation() {
                return ((Rotation) this.instance).hasUserRotation();
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public SettingProto getUserRotation() {
                return ((Rotation) this.instance).getUserRotation();
            }

            public Builder setUserRotation(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).setUserRotation((Rotation) value);
                return this;
            }

            public Builder setUserRotation(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Rotation) this.instance).setUserRotation((Rotation) builderForValue);
                return this;
            }

            public Builder mergeUserRotation(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).mergeUserRotation(value);
                return this;
            }

            public Builder clearUserRotation() {
                copyOnWrite();
                ((Rotation) this.instance).clearUserRotation();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public boolean hasHideRotationLockToggleForAccessibility() {
                return ((Rotation) this.instance).hasHideRotationLockToggleForAccessibility();
            }

            @Override // android.providers.settings.SystemSettingsProto.RotationOrBuilder
            public SettingProto getHideRotationLockToggleForAccessibility() {
                return ((Rotation) this.instance).getHideRotationLockToggleForAccessibility();
            }

            public Builder setHideRotationLockToggleForAccessibility(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).setHideRotationLockToggleForAccessibility((Rotation) value);
                return this;
            }

            public Builder setHideRotationLockToggleForAccessibility(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Rotation) this.instance).setHideRotationLockToggleForAccessibility((Rotation) builderForValue);
                return this;
            }

            public Builder mergeHideRotationLockToggleForAccessibility(SettingProto value) {
                copyOnWrite();
                ((Rotation) this.instance).mergeHideRotationLockToggleForAccessibility(value);
                return this;
            }

            public Builder clearHideRotationLockToggleForAccessibility() {
                copyOnWrite();
                ((Rotation) this.instance).clearHideRotationLockToggleForAccessibility();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Rotation();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Rotation other = (Rotation) arg1;
                    this.accelerometerRotation_ = (SettingProto) visitor.visitMessage(this.accelerometerRotation_, other.accelerometerRotation_);
                    this.userRotation_ = (SettingProto) visitor.visitMessage(this.userRotation_, other.userRotation_);
                    this.hideRotationLockToggleForAccessibility_ = (SettingProto) visitor.visitMessage(this.hideRotationLockToggleForAccessibility_, other.hideRotationLockToggleForAccessibility_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.accelerometerRotation_.toBuilder();
                                }
                                this.accelerometerRotation_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.accelerometerRotation_);
                                    this.accelerometerRotation_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.userRotation_.toBuilder();
                                }
                                this.userRotation_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.userRotation_);
                                    this.userRotation_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.hideRotationLockToggleForAccessibility_.toBuilder();
                                }
                                this.hideRotationLockToggleForAccessibility_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.hideRotationLockToggleForAccessibility_);
                                    this.hideRotationLockToggleForAccessibility_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
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
                        synchronized (Rotation.class) {
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

        public static Rotation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Rotation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Screen extends GeneratedMessageLite<Screen, Builder> implements ScreenOrBuilder {
        public static final int AUTO_BRIGHTNESS_ADJ_FIELD_NUMBER = 5;
        public static final int BRIGHTNESS_FIELD_NUMBER = 2;
        public static final int BRIGHTNESS_FOR_VR_FIELD_NUMBER = 3;
        public static final int BRIGHTNESS_MODE_FIELD_NUMBER = 4;
        private static final Screen DEFAULT_INSTANCE = new Screen();
        public static final int OFF_TIMEOUT_FIELD_NUMBER = 1;
        private static volatile Parser<Screen> PARSER;
        private SettingProto autoBrightnessAdj_;
        private int bitField0_;
        private SettingProto brightnessForVr_;
        private SettingProto brightnessMode_;
        private SettingProto brightness_;
        private SettingProto offTimeout_;

        private Screen() {
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public boolean hasOffTimeout() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public SettingProto getOffTimeout() {
            SettingProto settingProto = this.offTimeout_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOffTimeout(SettingProto value) {
            if (value != null) {
                this.offTimeout_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOffTimeout(SettingProto.Builder builderForValue) {
            this.offTimeout_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeOffTimeout(SettingProto value) {
            SettingProto settingProto = this.offTimeout_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.offTimeout_ = value;
            } else {
                this.offTimeout_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.offTimeout_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOffTimeout() {
            this.offTimeout_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public boolean hasBrightness() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public SettingProto getBrightness() {
            SettingProto settingProto = this.brightness_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightness(SettingProto value) {
            if (value != null) {
                this.brightness_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightness(SettingProto.Builder builderForValue) {
            this.brightness_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBrightness(SettingProto value) {
            SettingProto settingProto = this.brightness_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.brightness_ = value;
            } else {
                this.brightness_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.brightness_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBrightness() {
            this.brightness_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public boolean hasBrightnessForVr() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public SettingProto getBrightnessForVr() {
            SettingProto settingProto = this.brightnessForVr_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightnessForVr(SettingProto value) {
            if (value != null) {
                this.brightnessForVr_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightnessForVr(SettingProto.Builder builderForValue) {
            this.brightnessForVr_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBrightnessForVr(SettingProto value) {
            SettingProto settingProto = this.brightnessForVr_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.brightnessForVr_ = value;
            } else {
                this.brightnessForVr_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.brightnessForVr_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBrightnessForVr() {
            this.brightnessForVr_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public boolean hasBrightnessMode() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public SettingProto getBrightnessMode() {
            SettingProto settingProto = this.brightnessMode_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightnessMode(SettingProto value) {
            if (value != null) {
                this.brightnessMode_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBrightnessMode(SettingProto.Builder builderForValue) {
            this.brightnessMode_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBrightnessMode(SettingProto value) {
            SettingProto settingProto = this.brightnessMode_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.brightnessMode_ = value;
            } else {
                this.brightnessMode_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.brightnessMode_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBrightnessMode() {
            this.brightnessMode_ = null;
            this.bitField0_ &= -9;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public boolean hasAutoBrightnessAdj() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
        public SettingProto getAutoBrightnessAdj() {
            SettingProto settingProto = this.autoBrightnessAdj_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoBrightnessAdj(SettingProto value) {
            if (value != null) {
                this.autoBrightnessAdj_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoBrightnessAdj(SettingProto.Builder builderForValue) {
            this.autoBrightnessAdj_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAutoBrightnessAdj(SettingProto value) {
            SettingProto settingProto = this.autoBrightnessAdj_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.autoBrightnessAdj_ = value;
            } else {
                this.autoBrightnessAdj_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.autoBrightnessAdj_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAutoBrightnessAdj() {
            this.autoBrightnessAdj_ = null;
            this.bitField0_ &= -17;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getOffTimeout());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getBrightness());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getBrightnessForVr());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getBrightnessMode());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getAutoBrightnessAdj());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getOffTimeout());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getBrightness());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getBrightnessForVr());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getBrightnessMode());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getAutoBrightnessAdj());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Screen parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Screen parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Screen parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Screen parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Screen parseFrom(InputStream input) throws IOException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Screen parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Screen parseDelimitedFrom(InputStream input) throws IOException {
            return (Screen) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Screen parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Screen) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Screen parseFrom(CodedInputStream input) throws IOException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Screen parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Screen) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Screen prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Screen, Builder> implements ScreenOrBuilder {
            private Builder() {
                super(Screen.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public boolean hasOffTimeout() {
                return ((Screen) this.instance).hasOffTimeout();
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public SettingProto getOffTimeout() {
                return ((Screen) this.instance).getOffTimeout();
            }

            public Builder setOffTimeout(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).setOffTimeout((Screen) value);
                return this;
            }

            public Builder setOffTimeout(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Screen) this.instance).setOffTimeout((Screen) builderForValue);
                return this;
            }

            public Builder mergeOffTimeout(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).mergeOffTimeout(value);
                return this;
            }

            public Builder clearOffTimeout() {
                copyOnWrite();
                ((Screen) this.instance).clearOffTimeout();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public boolean hasBrightness() {
                return ((Screen) this.instance).hasBrightness();
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public SettingProto getBrightness() {
                return ((Screen) this.instance).getBrightness();
            }

            public Builder setBrightness(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).setBrightness((Screen) value);
                return this;
            }

            public Builder setBrightness(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Screen) this.instance).setBrightness((Screen) builderForValue);
                return this;
            }

            public Builder mergeBrightness(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).mergeBrightness(value);
                return this;
            }

            public Builder clearBrightness() {
                copyOnWrite();
                ((Screen) this.instance).clearBrightness();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public boolean hasBrightnessForVr() {
                return ((Screen) this.instance).hasBrightnessForVr();
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public SettingProto getBrightnessForVr() {
                return ((Screen) this.instance).getBrightnessForVr();
            }

            public Builder setBrightnessForVr(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).setBrightnessForVr((Screen) value);
                return this;
            }

            public Builder setBrightnessForVr(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Screen) this.instance).setBrightnessForVr((Screen) builderForValue);
                return this;
            }

            public Builder mergeBrightnessForVr(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).mergeBrightnessForVr(value);
                return this;
            }

            public Builder clearBrightnessForVr() {
                copyOnWrite();
                ((Screen) this.instance).clearBrightnessForVr();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public boolean hasBrightnessMode() {
                return ((Screen) this.instance).hasBrightnessMode();
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public SettingProto getBrightnessMode() {
                return ((Screen) this.instance).getBrightnessMode();
            }

            public Builder setBrightnessMode(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).setBrightnessMode((Screen) value);
                return this;
            }

            public Builder setBrightnessMode(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Screen) this.instance).setBrightnessMode((Screen) builderForValue);
                return this;
            }

            public Builder mergeBrightnessMode(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).mergeBrightnessMode(value);
                return this;
            }

            public Builder clearBrightnessMode() {
                copyOnWrite();
                ((Screen) this.instance).clearBrightnessMode();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public boolean hasAutoBrightnessAdj() {
                return ((Screen) this.instance).hasAutoBrightnessAdj();
            }

            @Override // android.providers.settings.SystemSettingsProto.ScreenOrBuilder
            public SettingProto getAutoBrightnessAdj() {
                return ((Screen) this.instance).getAutoBrightnessAdj();
            }

            public Builder setAutoBrightnessAdj(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).setAutoBrightnessAdj((Screen) value);
                return this;
            }

            public Builder setAutoBrightnessAdj(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Screen) this.instance).setAutoBrightnessAdj((Screen) builderForValue);
                return this;
            }

            public Builder mergeAutoBrightnessAdj(SettingProto value) {
                copyOnWrite();
                ((Screen) this.instance).mergeAutoBrightnessAdj(value);
                return this;
            }

            public Builder clearAutoBrightnessAdj() {
                copyOnWrite();
                ((Screen) this.instance).clearAutoBrightnessAdj();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Screen();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Screen other = (Screen) arg1;
                    this.offTimeout_ = (SettingProto) visitor.visitMessage(this.offTimeout_, other.offTimeout_);
                    this.brightness_ = (SettingProto) visitor.visitMessage(this.brightness_, other.brightness_);
                    this.brightnessForVr_ = (SettingProto) visitor.visitMessage(this.brightnessForVr_, other.brightnessForVr_);
                    this.brightnessMode_ = (SettingProto) visitor.visitMessage(this.brightnessMode_, other.brightnessMode_);
                    this.autoBrightnessAdj_ = (SettingProto) visitor.visitMessage(this.autoBrightnessAdj_, other.autoBrightnessAdj_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.offTimeout_.toBuilder();
                                }
                                this.offTimeout_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.offTimeout_);
                                    this.offTimeout_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.brightness_.toBuilder();
                                }
                                this.brightness_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.brightness_);
                                    this.brightness_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.brightnessForVr_.toBuilder();
                                }
                                this.brightnessForVr_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.brightnessForVr_);
                                    this.brightnessForVr_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.brightnessMode_.toBuilder();
                                }
                                this.brightnessMode_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.brightnessMode_);
                                    this.brightnessMode_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            } else if (tag == 42) {
                                SettingProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder5 = (SettingProto.Builder) this.autoBrightnessAdj_.toBuilder();
                                }
                                this.autoBrightnessAdj_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.autoBrightnessAdj_);
                                    this.autoBrightnessAdj_ = (SettingProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 16;
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
                        synchronized (Screen.class) {
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

        public static Screen getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Screen> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Sip extends GeneratedMessageLite<Sip, Builder> implements SipOrBuilder {
        public static final int ADDRESS_ONLY_FIELD_NUMBER = 4;
        public static final int ALWAYS_FIELD_NUMBER = 3;
        public static final int CALL_OPTIONS_FIELD_NUMBER = 2;
        private static final Sip DEFAULT_INSTANCE = new Sip();
        private static volatile Parser<Sip> PARSER = null;
        public static final int RECEIVE_CALLS_FIELD_NUMBER = 1;
        private SettingProto addressOnly_;
        private SettingProto always_;
        private int bitField0_;
        private SettingProto callOptions_;
        private SettingProto receiveCalls_;

        private Sip() {
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public boolean hasReceiveCalls() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public SettingProto getReceiveCalls() {
            SettingProto settingProto = this.receiveCalls_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiveCalls(SettingProto value) {
            if (value != null) {
                this.receiveCalls_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setReceiveCalls(SettingProto.Builder builderForValue) {
            this.receiveCalls_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeReceiveCalls(SettingProto value) {
            SettingProto settingProto = this.receiveCalls_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.receiveCalls_ = value;
            } else {
                this.receiveCalls_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.receiveCalls_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearReceiveCalls() {
            this.receiveCalls_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public boolean hasCallOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public SettingProto getCallOptions() {
            SettingProto settingProto = this.callOptions_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCallOptions(SettingProto value) {
            if (value != null) {
                this.callOptions_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCallOptions(SettingProto.Builder builderForValue) {
            this.callOptions_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeCallOptions(SettingProto value) {
            SettingProto settingProto = this.callOptions_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.callOptions_ = value;
            } else {
                this.callOptions_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.callOptions_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCallOptions() {
            this.callOptions_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public boolean hasAlways() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public SettingProto getAlways() {
            SettingProto settingProto = this.always_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlways(SettingProto value) {
            if (value != null) {
                this.always_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlways(SettingProto.Builder builderForValue) {
            this.always_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAlways(SettingProto value) {
            SettingProto settingProto = this.always_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.always_ = value;
            } else {
                this.always_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.always_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlways() {
            this.always_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public boolean hasAddressOnly() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
        public SettingProto getAddressOnly() {
            SettingProto settingProto = this.addressOnly_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAddressOnly(SettingProto value) {
            if (value != null) {
                this.addressOnly_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAddressOnly(SettingProto.Builder builderForValue) {
            this.addressOnly_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAddressOnly(SettingProto value) {
            SettingProto settingProto = this.addressOnly_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.addressOnly_ = value;
            } else {
                this.addressOnly_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.addressOnly_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAddressOnly() {
            this.addressOnly_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getReceiveCalls());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getCallOptions());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getAlways());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getAddressOnly());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getReceiveCalls());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getCallOptions());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getAlways());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getAddressOnly());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Sip parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sip parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sip parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Sip parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Sip parseFrom(InputStream input) throws IOException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sip parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sip parseDelimitedFrom(InputStream input) throws IOException {
            return (Sip) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Sip parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sip) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Sip parseFrom(CodedInputStream input) throws IOException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Sip parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Sip) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Sip prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Sip, Builder> implements SipOrBuilder {
            private Builder() {
                super(Sip.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public boolean hasReceiveCalls() {
                return ((Sip) this.instance).hasReceiveCalls();
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public SettingProto getReceiveCalls() {
                return ((Sip) this.instance).getReceiveCalls();
            }

            public Builder setReceiveCalls(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).setReceiveCalls((Sip) value);
                return this;
            }

            public Builder setReceiveCalls(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Sip) this.instance).setReceiveCalls((Sip) builderForValue);
                return this;
            }

            public Builder mergeReceiveCalls(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).mergeReceiveCalls(value);
                return this;
            }

            public Builder clearReceiveCalls() {
                copyOnWrite();
                ((Sip) this.instance).clearReceiveCalls();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public boolean hasCallOptions() {
                return ((Sip) this.instance).hasCallOptions();
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public SettingProto getCallOptions() {
                return ((Sip) this.instance).getCallOptions();
            }

            public Builder setCallOptions(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).setCallOptions((Sip) value);
                return this;
            }

            public Builder setCallOptions(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Sip) this.instance).setCallOptions((Sip) builderForValue);
                return this;
            }

            public Builder mergeCallOptions(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).mergeCallOptions(value);
                return this;
            }

            public Builder clearCallOptions() {
                copyOnWrite();
                ((Sip) this.instance).clearCallOptions();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public boolean hasAlways() {
                return ((Sip) this.instance).hasAlways();
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public SettingProto getAlways() {
                return ((Sip) this.instance).getAlways();
            }

            public Builder setAlways(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).setAlways((Sip) value);
                return this;
            }

            public Builder setAlways(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Sip) this.instance).setAlways((Sip) builderForValue);
                return this;
            }

            public Builder mergeAlways(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).mergeAlways(value);
                return this;
            }

            public Builder clearAlways() {
                copyOnWrite();
                ((Sip) this.instance).clearAlways();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public boolean hasAddressOnly() {
                return ((Sip) this.instance).hasAddressOnly();
            }

            @Override // android.providers.settings.SystemSettingsProto.SipOrBuilder
            public SettingProto getAddressOnly() {
                return ((Sip) this.instance).getAddressOnly();
            }

            public Builder setAddressOnly(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).setAddressOnly((Sip) value);
                return this;
            }

            public Builder setAddressOnly(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Sip) this.instance).setAddressOnly((Sip) builderForValue);
                return this;
            }

            public Builder mergeAddressOnly(SettingProto value) {
                copyOnWrite();
                ((Sip) this.instance).mergeAddressOnly(value);
                return this;
            }

            public Builder clearAddressOnly() {
                copyOnWrite();
                ((Sip) this.instance).clearAddressOnly();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Sip();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Sip other = (Sip) arg1;
                    this.receiveCalls_ = (SettingProto) visitor.visitMessage(this.receiveCalls_, other.receiveCalls_);
                    this.callOptions_ = (SettingProto) visitor.visitMessage(this.callOptions_, other.callOptions_);
                    this.always_ = (SettingProto) visitor.visitMessage(this.always_, other.always_);
                    this.addressOnly_ = (SettingProto) visitor.visitMessage(this.addressOnly_, other.addressOnly_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.receiveCalls_.toBuilder();
                                }
                                this.receiveCalls_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.receiveCalls_);
                                    this.receiveCalls_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.callOptions_.toBuilder();
                                }
                                this.callOptions_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.callOptions_);
                                    this.callOptions_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.always_.toBuilder();
                                }
                                this.always_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.always_);
                                    this.always_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.addressOnly_.toBuilder();
                                }
                                this.addressOnly_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.addressOnly_);
                                    this.addressOnly_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
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
                        synchronized (Sip.class) {
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

        public static Sip getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Sip> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Text extends GeneratedMessageLite<Text, Builder> implements TextOrBuilder {
        public static final int AUTO_CAPS_FIELD_NUMBER = 2;
        public static final int AUTO_PUNCTUATE_FIELD_NUMBER = 3;
        public static final int AUTO_REPLACE_FIELD_NUMBER = 1;
        private static final Text DEFAULT_INSTANCE = new Text();
        private static volatile Parser<Text> PARSER = null;
        public static final int SHOW_PASSWORD_FIELD_NUMBER = 4;
        private SettingProto autoCaps_;
        private SettingProto autoPunctuate_;
        private SettingProto autoReplace_;
        private int bitField0_;
        private SettingProto showPassword_;

        private Text() {
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public boolean hasAutoReplace() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public SettingProto getAutoReplace() {
            SettingProto settingProto = this.autoReplace_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoReplace(SettingProto value) {
            if (value != null) {
                this.autoReplace_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoReplace(SettingProto.Builder builderForValue) {
            this.autoReplace_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAutoReplace(SettingProto value) {
            SettingProto settingProto = this.autoReplace_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.autoReplace_ = value;
            } else {
                this.autoReplace_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.autoReplace_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAutoReplace() {
            this.autoReplace_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public boolean hasAutoCaps() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public SettingProto getAutoCaps() {
            SettingProto settingProto = this.autoCaps_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoCaps(SettingProto value) {
            if (value != null) {
                this.autoCaps_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoCaps(SettingProto.Builder builderForValue) {
            this.autoCaps_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAutoCaps(SettingProto value) {
            SettingProto settingProto = this.autoCaps_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.autoCaps_ = value;
            } else {
                this.autoCaps_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.autoCaps_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAutoCaps() {
            this.autoCaps_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public boolean hasAutoPunctuate() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public SettingProto getAutoPunctuate() {
            SettingProto settingProto = this.autoPunctuate_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoPunctuate(SettingProto value) {
            if (value != null) {
                this.autoPunctuate_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAutoPunctuate(SettingProto.Builder builderForValue) {
            this.autoPunctuate_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAutoPunctuate(SettingProto value) {
            SettingProto settingProto = this.autoPunctuate_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.autoPunctuate_ = value;
            } else {
                this.autoPunctuate_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.autoPunctuate_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAutoPunctuate() {
            this.autoPunctuate_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public boolean hasShowPassword() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
        public SettingProto getShowPassword() {
            SettingProto settingProto = this.showPassword_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShowPassword(SettingProto value) {
            if (value != null) {
                this.showPassword_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setShowPassword(SettingProto.Builder builderForValue) {
            this.showPassword_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeShowPassword(SettingProto value) {
            SettingProto settingProto = this.showPassword_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.showPassword_ = value;
            } else {
                this.showPassword_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.showPassword_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearShowPassword() {
            this.showPassword_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getAutoReplace());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getAutoCaps());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getAutoPunctuate());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getShowPassword());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getAutoReplace());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getAutoCaps());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getAutoPunctuate());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getShowPassword());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Text parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Text parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Text parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Text parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Text parseFrom(InputStream input) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Text parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Text parseDelimitedFrom(InputStream input) throws IOException {
            return (Text) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Text parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Text) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Text parseFrom(CodedInputStream input) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Text parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Text) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Text prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Text, Builder> implements TextOrBuilder {
            private Builder() {
                super(Text.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public boolean hasAutoReplace() {
                return ((Text) this.instance).hasAutoReplace();
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public SettingProto getAutoReplace() {
                return ((Text) this.instance).getAutoReplace();
            }

            public Builder setAutoReplace(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).setAutoReplace((Text) value);
                return this;
            }

            public Builder setAutoReplace(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Text) this.instance).setAutoReplace((Text) builderForValue);
                return this;
            }

            public Builder mergeAutoReplace(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).mergeAutoReplace(value);
                return this;
            }

            public Builder clearAutoReplace() {
                copyOnWrite();
                ((Text) this.instance).clearAutoReplace();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public boolean hasAutoCaps() {
                return ((Text) this.instance).hasAutoCaps();
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public SettingProto getAutoCaps() {
                return ((Text) this.instance).getAutoCaps();
            }

            public Builder setAutoCaps(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).setAutoCaps((Text) value);
                return this;
            }

            public Builder setAutoCaps(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Text) this.instance).setAutoCaps((Text) builderForValue);
                return this;
            }

            public Builder mergeAutoCaps(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).mergeAutoCaps(value);
                return this;
            }

            public Builder clearAutoCaps() {
                copyOnWrite();
                ((Text) this.instance).clearAutoCaps();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public boolean hasAutoPunctuate() {
                return ((Text) this.instance).hasAutoPunctuate();
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public SettingProto getAutoPunctuate() {
                return ((Text) this.instance).getAutoPunctuate();
            }

            public Builder setAutoPunctuate(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).setAutoPunctuate((Text) value);
                return this;
            }

            public Builder setAutoPunctuate(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Text) this.instance).setAutoPunctuate((Text) builderForValue);
                return this;
            }

            public Builder mergeAutoPunctuate(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).mergeAutoPunctuate(value);
                return this;
            }

            public Builder clearAutoPunctuate() {
                copyOnWrite();
                ((Text) this.instance).clearAutoPunctuate();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public boolean hasShowPassword() {
                return ((Text) this.instance).hasShowPassword();
            }

            @Override // android.providers.settings.SystemSettingsProto.TextOrBuilder
            public SettingProto getShowPassword() {
                return ((Text) this.instance).getShowPassword();
            }

            public Builder setShowPassword(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).setShowPassword((Text) value);
                return this;
            }

            public Builder setShowPassword(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Text) this.instance).setShowPassword((Text) builderForValue);
                return this;
            }

            public Builder mergeShowPassword(SettingProto value) {
                copyOnWrite();
                ((Text) this.instance).mergeShowPassword(value);
                return this;
            }

            public Builder clearShowPassword() {
                copyOnWrite();
                ((Text) this.instance).clearShowPassword();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Text();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Text other = (Text) arg1;
                    this.autoReplace_ = (SettingProto) visitor.visitMessage(this.autoReplace_, other.autoReplace_);
                    this.autoCaps_ = (SettingProto) visitor.visitMessage(this.autoCaps_, other.autoCaps_);
                    this.autoPunctuate_ = (SettingProto) visitor.visitMessage(this.autoPunctuate_, other.autoPunctuate_);
                    this.showPassword_ = (SettingProto) visitor.visitMessage(this.showPassword_, other.showPassword_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.autoReplace_.toBuilder();
                                }
                                this.autoReplace_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.autoReplace_);
                                    this.autoReplace_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.autoCaps_.toBuilder();
                                }
                                this.autoCaps_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.autoCaps_);
                                    this.autoCaps_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.autoPunctuate_.toBuilder();
                                }
                                this.autoPunctuate_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.autoPunctuate_);
                                    this.autoPunctuate_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.showPassword_.toBuilder();
                                }
                                this.showPassword_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.showPassword_);
                                    this.showPassword_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
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
                        synchronized (Text.class) {
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

        public static Text getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Text> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Vibrate extends GeneratedMessageLite<Vibrate, Builder> implements VibrateOrBuilder {
        private static final Vibrate DEFAULT_INSTANCE = new Vibrate();
        public static final int INPUT_DEVICES_FIELD_NUMBER = 2;
        public static final int IN_SILENT_FIELD_NUMBER = 3;
        public static final int ON_FIELD_NUMBER = 1;
        private static volatile Parser<Vibrate> PARSER = null;
        public static final int WHEN_RINGING_FIELD_NUMBER = 4;
        private int bitField0_;
        private SettingProto inSilent_;
        private SettingProto inputDevices_;
        private SettingProto on_;
        private SettingProto whenRinging_;

        private Vibrate() {
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public boolean hasOn() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public SettingProto getOn() {
            SettingProto settingProto = this.on_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOn(SettingProto value) {
            if (value != null) {
                this.on_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setOn(SettingProto.Builder builderForValue) {
            this.on_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeOn(SettingProto value) {
            SettingProto settingProto = this.on_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.on_ = value;
            } else {
                this.on_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.on_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearOn() {
            this.on_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public boolean hasInputDevices() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public SettingProto getInputDevices() {
            SettingProto settingProto = this.inputDevices_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInputDevices(SettingProto value) {
            if (value != null) {
                this.inputDevices_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInputDevices(SettingProto.Builder builderForValue) {
            this.inputDevices_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInputDevices(SettingProto value) {
            SettingProto settingProto = this.inputDevices_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.inputDevices_ = value;
            } else {
                this.inputDevices_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.inputDevices_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInputDevices() {
            this.inputDevices_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public boolean hasInSilent() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public SettingProto getInSilent() {
            SettingProto settingProto = this.inSilent_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInSilent(SettingProto value) {
            if (value != null) {
                this.inSilent_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setInSilent(SettingProto.Builder builderForValue) {
            this.inSilent_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeInSilent(SettingProto value) {
            SettingProto settingProto = this.inSilent_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.inSilent_ = value;
            } else {
                this.inSilent_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.inSilent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearInSilent() {
            this.inSilent_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public boolean hasWhenRinging() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
        public SettingProto getWhenRinging() {
            SettingProto settingProto = this.whenRinging_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhenRinging(SettingProto value) {
            if (value != null) {
                this.whenRinging_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setWhenRinging(SettingProto.Builder builderForValue) {
            this.whenRinging_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeWhenRinging(SettingProto value) {
            SettingProto settingProto = this.whenRinging_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.whenRinging_ = value;
            } else {
                this.whenRinging_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.whenRinging_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearWhenRinging() {
            this.whenRinging_ = null;
            this.bitField0_ &= -9;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getOn());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getInputDevices());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getInSilent());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getWhenRinging());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getOn());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getInputDevices());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getInSilent());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getWhenRinging());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Vibrate parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Vibrate parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Vibrate parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Vibrate parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Vibrate parseFrom(InputStream input) throws IOException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Vibrate parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Vibrate parseDelimitedFrom(InputStream input) throws IOException {
            return (Vibrate) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Vibrate parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Vibrate) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Vibrate parseFrom(CodedInputStream input) throws IOException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Vibrate parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Vibrate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Vibrate prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Vibrate, Builder> implements VibrateOrBuilder {
            private Builder() {
                super(Vibrate.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public boolean hasOn() {
                return ((Vibrate) this.instance).hasOn();
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public SettingProto getOn() {
                return ((Vibrate) this.instance).getOn();
            }

            public Builder setOn(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).setOn((Vibrate) value);
                return this;
            }

            public Builder setOn(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Vibrate) this.instance).setOn((Vibrate) builderForValue);
                return this;
            }

            public Builder mergeOn(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).mergeOn(value);
                return this;
            }

            public Builder clearOn() {
                copyOnWrite();
                ((Vibrate) this.instance).clearOn();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public boolean hasInputDevices() {
                return ((Vibrate) this.instance).hasInputDevices();
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public SettingProto getInputDevices() {
                return ((Vibrate) this.instance).getInputDevices();
            }

            public Builder setInputDevices(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).setInputDevices((Vibrate) value);
                return this;
            }

            public Builder setInputDevices(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Vibrate) this.instance).setInputDevices((Vibrate) builderForValue);
                return this;
            }

            public Builder mergeInputDevices(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).mergeInputDevices(value);
                return this;
            }

            public Builder clearInputDevices() {
                copyOnWrite();
                ((Vibrate) this.instance).clearInputDevices();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public boolean hasInSilent() {
                return ((Vibrate) this.instance).hasInSilent();
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public SettingProto getInSilent() {
                return ((Vibrate) this.instance).getInSilent();
            }

            public Builder setInSilent(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).setInSilent((Vibrate) value);
                return this;
            }

            public Builder setInSilent(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Vibrate) this.instance).setInSilent((Vibrate) builderForValue);
                return this;
            }

            public Builder mergeInSilent(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).mergeInSilent(value);
                return this;
            }

            public Builder clearInSilent() {
                copyOnWrite();
                ((Vibrate) this.instance).clearInSilent();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public boolean hasWhenRinging() {
                return ((Vibrate) this.instance).hasWhenRinging();
            }

            @Override // android.providers.settings.SystemSettingsProto.VibrateOrBuilder
            public SettingProto getWhenRinging() {
                return ((Vibrate) this.instance).getWhenRinging();
            }

            public Builder setWhenRinging(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).setWhenRinging((Vibrate) value);
                return this;
            }

            public Builder setWhenRinging(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Vibrate) this.instance).setWhenRinging((Vibrate) builderForValue);
                return this;
            }

            public Builder mergeWhenRinging(SettingProto value) {
                copyOnWrite();
                ((Vibrate) this.instance).mergeWhenRinging(value);
                return this;
            }

            public Builder clearWhenRinging() {
                copyOnWrite();
                ((Vibrate) this.instance).clearWhenRinging();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Vibrate();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Vibrate other = (Vibrate) arg1;
                    this.on_ = (SettingProto) visitor.visitMessage(this.on_, other.on_);
                    this.inputDevices_ = (SettingProto) visitor.visitMessage(this.inputDevices_, other.inputDevices_);
                    this.inSilent_ = (SettingProto) visitor.visitMessage(this.inSilent_, other.inSilent_);
                    this.whenRinging_ = (SettingProto) visitor.visitMessage(this.whenRinging_, other.whenRinging_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream input = (CodedInputStream) arg0;
                    ExtensionRegistryLite extensionRegistry = (ExtensionRegistryLite) arg1;
                    boolean done = false;
                    while (!done) {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 10) {
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.on_.toBuilder();
                                }
                                this.on_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.on_);
                                    this.on_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (tag == 18) {
                                SettingProto.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (SettingProto.Builder) this.inputDevices_.toBuilder();
                                }
                                this.inputDevices_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.inputDevices_);
                                    this.inputDevices_ = (SettingProto) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (tag == 26) {
                                SettingProto.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (SettingProto.Builder) this.inSilent_.toBuilder();
                                }
                                this.inSilent_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.inSilent_);
                                    this.inSilent_ = (SettingProto) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (tag == 34) {
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.whenRinging_.toBuilder();
                                }
                                this.whenRinging_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.whenRinging_);
                                    this.whenRinging_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
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
                        synchronized (Vibrate.class) {
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

        public static Vibrate getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Vibrate> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public static final class Volume extends GeneratedMessageLite<Volume, Builder> implements VolumeOrBuilder {
        public static final int ACCESSIBILITY_FIELD_NUMBER = 8;
        public static final int ALARM_FIELD_NUMBER = 5;
        public static final int BLUETOOTH_SCO_FIELD_NUMBER = 7;
        private static final Volume DEFAULT_INSTANCE = new Volume();
        public static final int MASTER_BALANCE_FIELD_NUMBER = 13;
        public static final int MASTER_FIELD_NUMBER = 9;
        public static final int MASTER_MONO_FIELD_NUMBER = 10;
        public static final int MODE_RINGER_STREAMS_AFFECTED_FIELD_NUMBER = 11;
        public static final int MUSIC_FIELD_NUMBER = 4;
        public static final int MUTE_STREAMS_AFFECTED_FIELD_NUMBER = 12;
        public static final int NOTIFICATION_FIELD_NUMBER = 6;
        private static volatile Parser<Volume> PARSER = null;
        public static final int RING_FIELD_NUMBER = 1;
        public static final int SYSTEM_FIELD_NUMBER = 2;
        public static final int VOICE_FIELD_NUMBER = 3;
        private SettingProto accessibility_;
        private SettingProto alarm_;
        private int bitField0_;
        private SettingProto bluetoothSco_;
        private SettingProto masterBalance_;
        private SettingProto masterMono_;
        private SettingProto master_;
        private SettingProto modeRingerStreamsAffected_;
        private SettingProto music_;
        private SettingProto muteStreamsAffected_;
        private SettingProto notification_;
        private SettingProto ring_;
        private SettingProto system_;
        private SettingProto voice_;

        private Volume() {
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasRing() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getRing() {
            SettingProto settingProto = this.ring_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRing(SettingProto value) {
            if (value != null) {
                this.ring_ = value;
                this.bitField0_ |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setRing(SettingProto.Builder builderForValue) {
            this.ring_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeRing(SettingProto value) {
            SettingProto settingProto = this.ring_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.ring_ = value;
            } else {
                this.ring_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.ring_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearRing() {
            this.ring_ = null;
            this.bitField0_ &= -2;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasSystem() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getSystem() {
            SettingProto settingProto = this.system_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSystem(SettingProto value) {
            if (value != null) {
                this.system_ = value;
                this.bitField0_ |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSystem(SettingProto.Builder builderForValue) {
            this.system_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeSystem(SettingProto value) {
            SettingProto settingProto = this.system_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.system_ = value;
            } else {
                this.system_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.system_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearSystem() {
            this.system_ = null;
            this.bitField0_ &= -3;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasVoice() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getVoice() {
            SettingProto settingProto = this.voice_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVoice(SettingProto value) {
            if (value != null) {
                this.voice_ = value;
                this.bitField0_ |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setVoice(SettingProto.Builder builderForValue) {
            this.voice_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeVoice(SettingProto value) {
            SettingProto settingProto = this.voice_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.voice_ = value;
            } else {
                this.voice_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.voice_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearVoice() {
            this.voice_ = null;
            this.bitField0_ &= -5;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasMusic() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getMusic() {
            SettingProto settingProto = this.music_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMusic(SettingProto value) {
            if (value != null) {
                this.music_ = value;
                this.bitField0_ |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMusic(SettingProto.Builder builderForValue) {
            this.music_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMusic(SettingProto value) {
            SettingProto settingProto = this.music_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.music_ = value;
            } else {
                this.music_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.music_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMusic() {
            this.music_ = null;
            this.bitField0_ &= -9;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasAlarm() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getAlarm() {
            SettingProto settingProto = this.alarm_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlarm(SettingProto value) {
            if (value != null) {
                this.alarm_ = value;
                this.bitField0_ |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAlarm(SettingProto.Builder builderForValue) {
            this.alarm_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAlarm(SettingProto value) {
            SettingProto settingProto = this.alarm_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.alarm_ = value;
            } else {
                this.alarm_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.alarm_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 16;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAlarm() {
            this.alarm_ = null;
            this.bitField0_ &= -17;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasNotification() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getNotification() {
            SettingProto settingProto = this.notification_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotification(SettingProto value) {
            if (value != null) {
                this.notification_ = value;
                this.bitField0_ |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setNotification(SettingProto.Builder builderForValue) {
            this.notification_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeNotification(SettingProto value) {
            SettingProto settingProto = this.notification_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.notification_ = value;
            } else {
                this.notification_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.notification_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 32;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearNotification() {
            this.notification_ = null;
            this.bitField0_ &= -33;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasBluetoothSco() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getBluetoothSco() {
            SettingProto settingProto = this.bluetoothSco_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBluetoothSco(SettingProto value) {
            if (value != null) {
                this.bluetoothSco_ = value;
                this.bitField0_ |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setBluetoothSco(SettingProto.Builder builderForValue) {
            this.bluetoothSco_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeBluetoothSco(SettingProto value) {
            SettingProto settingProto = this.bluetoothSco_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.bluetoothSco_ = value;
            } else {
                this.bluetoothSco_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.bluetoothSco_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 64;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearBluetoothSco() {
            this.bluetoothSco_ = null;
            this.bitField0_ &= -65;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasAccessibility() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getAccessibility() {
            SettingProto settingProto = this.accessibility_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAccessibility(SettingProto value) {
            if (value != null) {
                this.accessibility_ = value;
                this.bitField0_ |= 128;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setAccessibility(SettingProto.Builder builderForValue) {
            this.accessibility_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 128;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeAccessibility(SettingProto value) {
            SettingProto settingProto = this.accessibility_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.accessibility_ = value;
            } else {
                this.accessibility_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.accessibility_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 128;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearAccessibility() {
            this.accessibility_ = null;
            this.bitField0_ &= -129;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasMaster() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getMaster() {
            SettingProto settingProto = this.master_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaster(SettingProto value) {
            if (value != null) {
                this.master_ = value;
                this.bitField0_ |= 256;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMaster(SettingProto.Builder builderForValue) {
            this.master_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 256;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMaster(SettingProto value) {
            SettingProto settingProto = this.master_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.master_ = value;
            } else {
                this.master_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.master_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 256;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMaster() {
            this.master_ = null;
            this.bitField0_ &= -257;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasMasterMono() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getMasterMono() {
            SettingProto settingProto = this.masterMono_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMasterMono(SettingProto value) {
            if (value != null) {
                this.masterMono_ = value;
                this.bitField0_ |= 512;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMasterMono(SettingProto.Builder builderForValue) {
            this.masterMono_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 512;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMasterMono(SettingProto value) {
            SettingProto settingProto = this.masterMono_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.masterMono_ = value;
            } else {
                this.masterMono_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.masterMono_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 512;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMasterMono() {
            this.masterMono_ = null;
            this.bitField0_ &= -513;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasModeRingerStreamsAffected() {
            return (this.bitField0_ & 1024) == 1024;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getModeRingerStreamsAffected() {
            SettingProto settingProto = this.modeRingerStreamsAffected_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setModeRingerStreamsAffected(SettingProto value) {
            if (value != null) {
                this.modeRingerStreamsAffected_ = value;
                this.bitField0_ |= 1024;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setModeRingerStreamsAffected(SettingProto.Builder builderForValue) {
            this.modeRingerStreamsAffected_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 1024;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeModeRingerStreamsAffected(SettingProto value) {
            SettingProto settingProto = this.modeRingerStreamsAffected_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.modeRingerStreamsAffected_ = value;
            } else {
                this.modeRingerStreamsAffected_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.modeRingerStreamsAffected_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 1024;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearModeRingerStreamsAffected() {
            this.modeRingerStreamsAffected_ = null;
            this.bitField0_ &= -1025;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasMuteStreamsAffected() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getMuteStreamsAffected() {
            SettingProto settingProto = this.muteStreamsAffected_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMuteStreamsAffected(SettingProto value) {
            if (value != null) {
                this.muteStreamsAffected_ = value;
                this.bitField0_ |= 2048;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMuteStreamsAffected(SettingProto.Builder builderForValue) {
            this.muteStreamsAffected_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 2048;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMuteStreamsAffected(SettingProto value) {
            SettingProto settingProto = this.muteStreamsAffected_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.muteStreamsAffected_ = value;
            } else {
                this.muteStreamsAffected_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.muteStreamsAffected_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 2048;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMuteStreamsAffected() {
            this.muteStreamsAffected_ = null;
            this.bitField0_ &= -2049;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public boolean hasMasterBalance() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
        public SettingProto getMasterBalance() {
            SettingProto settingProto = this.masterBalance_;
            return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMasterBalance(SettingProto value) {
            if (value != null) {
                this.masterBalance_ = value;
                this.bitField0_ |= 4096;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setMasterBalance(SettingProto.Builder builderForValue) {
            this.masterBalance_ = (SettingProto) builderForValue.build();
            this.bitField0_ |= 4096;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void mergeMasterBalance(SettingProto value) {
            SettingProto settingProto = this.masterBalance_;
            if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
                this.masterBalance_ = value;
            } else {
                this.masterBalance_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.masterBalance_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
            }
            this.bitField0_ |= 4096;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearMasterBalance() {
            this.masterBalance_ = null;
            this.bitField0_ &= -4097;
        }

        @Override // com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, getRing());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, getSystem());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, getVoice());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, getMusic());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeMessage(5, getAlarm());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeMessage(6, getNotification());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeMessage(7, getBluetoothSco());
            }
            if ((this.bitField0_ & 128) == 128) {
                output.writeMessage(8, getAccessibility());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeMessage(9, getMaster());
            }
            if ((this.bitField0_ & 512) == 512) {
                output.writeMessage(10, getMasterMono());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                output.writeMessage(11, getModeRingerStreamsAffected());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeMessage(12, getMuteStreamsAffected());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeMessage(13, getMasterBalance());
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
                size2 = 0 + CodedOutputStream.computeMessageSize(1, getRing());
            }
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, getSystem());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, getVoice());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, getMusic());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeMessageSize(5, getAlarm());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeMessageSize(6, getNotification());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeMessageSize(7, getBluetoothSco());
            }
            if ((this.bitField0_ & 128) == 128) {
                size2 += CodedOutputStream.computeMessageSize(8, getAccessibility());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeMessageSize(9, getMaster());
            }
            if ((this.bitField0_ & 512) == 512) {
                size2 += CodedOutputStream.computeMessageSize(10, getMasterMono());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                size2 += CodedOutputStream.computeMessageSize(11, getModeRingerStreamsAffected());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeMessageSize(12, getMuteStreamsAffected());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeMessageSize(13, getMasterBalance());
            }
            int size3 = size2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        public static Volume parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Volume parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Volume parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Volume parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Volume parseFrom(InputStream input) throws IOException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Volume parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Volume parseDelimitedFrom(InputStream input) throws IOException {
            return (Volume) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Volume parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Volume) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Volume parseFrom(CodedInputStream input) throws IOException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Volume parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Volume) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Volume prototype) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Volume, Builder> implements VolumeOrBuilder {
            private Builder() {
                super(Volume.DEFAULT_INSTANCE);
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasRing() {
                return ((Volume) this.instance).hasRing();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getRing() {
                return ((Volume) this.instance).getRing();
            }

            public Builder setRing(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setRing((Volume) value);
                return this;
            }

            public Builder setRing(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setRing((Volume) builderForValue);
                return this;
            }

            public Builder mergeRing(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeRing(value);
                return this;
            }

            public Builder clearRing() {
                copyOnWrite();
                ((Volume) this.instance).clearRing();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasSystem() {
                return ((Volume) this.instance).hasSystem();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getSystem() {
                return ((Volume) this.instance).getSystem();
            }

            public Builder setSystem(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setSystem((Volume) value);
                return this;
            }

            public Builder setSystem(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setSystem((Volume) builderForValue);
                return this;
            }

            public Builder mergeSystem(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeSystem(value);
                return this;
            }

            public Builder clearSystem() {
                copyOnWrite();
                ((Volume) this.instance).clearSystem();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasVoice() {
                return ((Volume) this.instance).hasVoice();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getVoice() {
                return ((Volume) this.instance).getVoice();
            }

            public Builder setVoice(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setVoice((Volume) value);
                return this;
            }

            public Builder setVoice(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setVoice((Volume) builderForValue);
                return this;
            }

            public Builder mergeVoice(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeVoice(value);
                return this;
            }

            public Builder clearVoice() {
                copyOnWrite();
                ((Volume) this.instance).clearVoice();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasMusic() {
                return ((Volume) this.instance).hasMusic();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getMusic() {
                return ((Volume) this.instance).getMusic();
            }

            public Builder setMusic(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setMusic((Volume) value);
                return this;
            }

            public Builder setMusic(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setMusic((Volume) builderForValue);
                return this;
            }

            public Builder mergeMusic(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeMusic(value);
                return this;
            }

            public Builder clearMusic() {
                copyOnWrite();
                ((Volume) this.instance).clearMusic();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasAlarm() {
                return ((Volume) this.instance).hasAlarm();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getAlarm() {
                return ((Volume) this.instance).getAlarm();
            }

            public Builder setAlarm(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setAlarm((Volume) value);
                return this;
            }

            public Builder setAlarm(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setAlarm((Volume) builderForValue);
                return this;
            }

            public Builder mergeAlarm(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeAlarm(value);
                return this;
            }

            public Builder clearAlarm() {
                copyOnWrite();
                ((Volume) this.instance).clearAlarm();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasNotification() {
                return ((Volume) this.instance).hasNotification();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getNotification() {
                return ((Volume) this.instance).getNotification();
            }

            public Builder setNotification(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setNotification((Volume) value);
                return this;
            }

            public Builder setNotification(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setNotification((Volume) builderForValue);
                return this;
            }

            public Builder mergeNotification(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeNotification(value);
                return this;
            }

            public Builder clearNotification() {
                copyOnWrite();
                ((Volume) this.instance).clearNotification();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasBluetoothSco() {
                return ((Volume) this.instance).hasBluetoothSco();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getBluetoothSco() {
                return ((Volume) this.instance).getBluetoothSco();
            }

            public Builder setBluetoothSco(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setBluetoothSco((Volume) value);
                return this;
            }

            public Builder setBluetoothSco(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setBluetoothSco((Volume) builderForValue);
                return this;
            }

            public Builder mergeBluetoothSco(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeBluetoothSco(value);
                return this;
            }

            public Builder clearBluetoothSco() {
                copyOnWrite();
                ((Volume) this.instance).clearBluetoothSco();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasAccessibility() {
                return ((Volume) this.instance).hasAccessibility();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getAccessibility() {
                return ((Volume) this.instance).getAccessibility();
            }

            public Builder setAccessibility(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setAccessibility((Volume) value);
                return this;
            }

            public Builder setAccessibility(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setAccessibility((Volume) builderForValue);
                return this;
            }

            public Builder mergeAccessibility(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeAccessibility(value);
                return this;
            }

            public Builder clearAccessibility() {
                copyOnWrite();
                ((Volume) this.instance).clearAccessibility();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasMaster() {
                return ((Volume) this.instance).hasMaster();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getMaster() {
                return ((Volume) this.instance).getMaster();
            }

            public Builder setMaster(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setMaster((Volume) value);
                return this;
            }

            public Builder setMaster(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setMaster((Volume) builderForValue);
                return this;
            }

            public Builder mergeMaster(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeMaster(value);
                return this;
            }

            public Builder clearMaster() {
                copyOnWrite();
                ((Volume) this.instance).clearMaster();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasMasterMono() {
                return ((Volume) this.instance).hasMasterMono();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getMasterMono() {
                return ((Volume) this.instance).getMasterMono();
            }

            public Builder setMasterMono(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setMasterMono((Volume) value);
                return this;
            }

            public Builder setMasterMono(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setMasterMono((Volume) builderForValue);
                return this;
            }

            public Builder mergeMasterMono(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeMasterMono(value);
                return this;
            }

            public Builder clearMasterMono() {
                copyOnWrite();
                ((Volume) this.instance).clearMasterMono();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasModeRingerStreamsAffected() {
                return ((Volume) this.instance).hasModeRingerStreamsAffected();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getModeRingerStreamsAffected() {
                return ((Volume) this.instance).getModeRingerStreamsAffected();
            }

            public Builder setModeRingerStreamsAffected(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setModeRingerStreamsAffected((Volume) value);
                return this;
            }

            public Builder setModeRingerStreamsAffected(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setModeRingerStreamsAffected((Volume) builderForValue);
                return this;
            }

            public Builder mergeModeRingerStreamsAffected(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeModeRingerStreamsAffected(value);
                return this;
            }

            public Builder clearModeRingerStreamsAffected() {
                copyOnWrite();
                ((Volume) this.instance).clearModeRingerStreamsAffected();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasMuteStreamsAffected() {
                return ((Volume) this.instance).hasMuteStreamsAffected();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getMuteStreamsAffected() {
                return ((Volume) this.instance).getMuteStreamsAffected();
            }

            public Builder setMuteStreamsAffected(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setMuteStreamsAffected((Volume) value);
                return this;
            }

            public Builder setMuteStreamsAffected(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setMuteStreamsAffected((Volume) builderForValue);
                return this;
            }

            public Builder mergeMuteStreamsAffected(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeMuteStreamsAffected(value);
                return this;
            }

            public Builder clearMuteStreamsAffected() {
                copyOnWrite();
                ((Volume) this.instance).clearMuteStreamsAffected();
                return this;
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public boolean hasMasterBalance() {
                return ((Volume) this.instance).hasMasterBalance();
            }

            @Override // android.providers.settings.SystemSettingsProto.VolumeOrBuilder
            public SettingProto getMasterBalance() {
                return ((Volume) this.instance).getMasterBalance();
            }

            public Builder setMasterBalance(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).setMasterBalance((Volume) value);
                return this;
            }

            public Builder setMasterBalance(SettingProto.Builder builderForValue) {
                copyOnWrite();
                ((Volume) this.instance).setMasterBalance((Volume) builderForValue);
                return this;
            }

            public Builder mergeMasterBalance(SettingProto value) {
                copyOnWrite();
                ((Volume) this.instance).mergeMasterBalance(value);
                return this;
            }

            public Builder clearMasterBalance() {
                copyOnWrite();
                ((Volume) this.instance).clearMasterBalance();
                return this;
            }
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        /* access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE:
                    return new Volume();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                    Volume other = (Volume) arg1;
                    this.ring_ = (SettingProto) visitor.visitMessage(this.ring_, other.ring_);
                    this.system_ = (SettingProto) visitor.visitMessage(this.system_, other.system_);
                    this.voice_ = (SettingProto) visitor.visitMessage(this.voice_, other.voice_);
                    this.music_ = (SettingProto) visitor.visitMessage(this.music_, other.music_);
                    this.alarm_ = (SettingProto) visitor.visitMessage(this.alarm_, other.alarm_);
                    this.notification_ = (SettingProto) visitor.visitMessage(this.notification_, other.notification_);
                    this.bluetoothSco_ = (SettingProto) visitor.visitMessage(this.bluetoothSco_, other.bluetoothSco_);
                    this.accessibility_ = (SettingProto) visitor.visitMessage(this.accessibility_, other.accessibility_);
                    this.master_ = (SettingProto) visitor.visitMessage(this.master_, other.master_);
                    this.masterMono_ = (SettingProto) visitor.visitMessage(this.masterMono_, other.masterMono_);
                    this.modeRingerStreamsAffected_ = (SettingProto) visitor.visitMessage(this.modeRingerStreamsAffected_, other.modeRingerStreamsAffected_);
                    this.muteStreamsAffected_ = (SettingProto) visitor.visitMessage(this.muteStreamsAffected_, other.muteStreamsAffected_);
                    this.masterBalance_ = (SettingProto) visitor.visitMessage(this.masterBalance_, other.masterBalance_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= other.bitField0_;
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
                                    SettingProto.Builder subBuilder = null;
                                    if ((this.bitField0_ & 1) == 1) {
                                        subBuilder = (SettingProto.Builder) this.ring_.toBuilder();
                                    }
                                    this.ring_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder != null) {
                                        subBuilder.mergeFrom((GeneratedMessageLite) this.ring_);
                                        this.ring_ = (SettingProto) subBuilder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                    break;
                                case 18:
                                    SettingProto.Builder subBuilder2 = null;
                                    if ((this.bitField0_ & 2) == 2) {
                                        subBuilder2 = (SettingProto.Builder) this.system_.toBuilder();
                                    }
                                    this.system_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder2 != null) {
                                        subBuilder2.mergeFrom((GeneratedMessageLite) this.system_);
                                        this.system_ = (SettingProto) subBuilder2.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                    break;
                                case 26:
                                    SettingProto.Builder subBuilder3 = null;
                                    if ((this.bitField0_ & 4) == 4) {
                                        subBuilder3 = (SettingProto.Builder) this.voice_.toBuilder();
                                    }
                                    this.voice_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder3 != null) {
                                        subBuilder3.mergeFrom((GeneratedMessageLite) this.voice_);
                                        this.voice_ = (SettingProto) subBuilder3.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                    break;
                                case 34:
                                    SettingProto.Builder subBuilder4 = null;
                                    if ((this.bitField0_ & 8) == 8) {
                                        subBuilder4 = (SettingProto.Builder) this.music_.toBuilder();
                                    }
                                    this.music_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder4 != null) {
                                        subBuilder4.mergeFrom((GeneratedMessageLite) this.music_);
                                        this.music_ = (SettingProto) subBuilder4.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                    break;
                                case 42:
                                    SettingProto.Builder subBuilder5 = null;
                                    if ((this.bitField0_ & 16) == 16) {
                                        subBuilder5 = (SettingProto.Builder) this.alarm_.toBuilder();
                                    }
                                    this.alarm_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder5 != null) {
                                        subBuilder5.mergeFrom((GeneratedMessageLite) this.alarm_);
                                        this.alarm_ = (SettingProto) subBuilder5.buildPartial();
                                    }
                                    this.bitField0_ |= 16;
                                    break;
                                case 50:
                                    SettingProto.Builder subBuilder6 = null;
                                    if ((this.bitField0_ & 32) == 32) {
                                        subBuilder6 = (SettingProto.Builder) this.notification_.toBuilder();
                                    }
                                    this.notification_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder6 != null) {
                                        subBuilder6.mergeFrom((GeneratedMessageLite) this.notification_);
                                        this.notification_ = (SettingProto) subBuilder6.buildPartial();
                                    }
                                    this.bitField0_ |= 32;
                                    break;
                                case 58:
                                    SettingProto.Builder subBuilder7 = null;
                                    if ((this.bitField0_ & 64) == 64) {
                                        subBuilder7 = (SettingProto.Builder) this.bluetoothSco_.toBuilder();
                                    }
                                    this.bluetoothSco_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder7 != null) {
                                        subBuilder7.mergeFrom((GeneratedMessageLite) this.bluetoothSco_);
                                        this.bluetoothSco_ = (SettingProto) subBuilder7.buildPartial();
                                    }
                                    this.bitField0_ |= 64;
                                    break;
                                case 66:
                                    SettingProto.Builder subBuilder8 = null;
                                    if ((this.bitField0_ & 128) == 128) {
                                        subBuilder8 = (SettingProto.Builder) this.accessibility_.toBuilder();
                                    }
                                    this.accessibility_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder8 != null) {
                                        subBuilder8.mergeFrom((GeneratedMessageLite) this.accessibility_);
                                        this.accessibility_ = (SettingProto) subBuilder8.buildPartial();
                                    }
                                    this.bitField0_ |= 128;
                                    break;
                                case 74:
                                    SettingProto.Builder subBuilder9 = null;
                                    if ((this.bitField0_ & 256) == 256) {
                                        subBuilder9 = (SettingProto.Builder) this.master_.toBuilder();
                                    }
                                    this.master_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder9 != null) {
                                        subBuilder9.mergeFrom((GeneratedMessageLite) this.master_);
                                        this.master_ = (SettingProto) subBuilder9.buildPartial();
                                    }
                                    this.bitField0_ |= 256;
                                    break;
                                case 82:
                                    SettingProto.Builder subBuilder10 = null;
                                    if ((this.bitField0_ & 512) == 512) {
                                        subBuilder10 = (SettingProto.Builder) this.masterMono_.toBuilder();
                                    }
                                    this.masterMono_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder10 != null) {
                                        subBuilder10.mergeFrom((GeneratedMessageLite) this.masterMono_);
                                        this.masterMono_ = (SettingProto) subBuilder10.buildPartial();
                                    }
                                    this.bitField0_ |= 512;
                                    break;
                                case 90:
                                    SettingProto.Builder subBuilder11 = null;
                                    if ((this.bitField0_ & 1024) == 1024) {
                                        subBuilder11 = (SettingProto.Builder) this.modeRingerStreamsAffected_.toBuilder();
                                    }
                                    this.modeRingerStreamsAffected_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder11 != null) {
                                        subBuilder11.mergeFrom((GeneratedMessageLite) this.modeRingerStreamsAffected_);
                                        this.modeRingerStreamsAffected_ = (SettingProto) subBuilder11.buildPartial();
                                    }
                                    this.bitField0_ |= 1024;
                                    break;
                                case 98:
                                    SettingProto.Builder subBuilder12 = null;
                                    if ((this.bitField0_ & 2048) == 2048) {
                                        subBuilder12 = (SettingProto.Builder) this.muteStreamsAffected_.toBuilder();
                                    }
                                    this.muteStreamsAffected_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder12 != null) {
                                        subBuilder12.mergeFrom((GeneratedMessageLite) this.muteStreamsAffected_);
                                        this.muteStreamsAffected_ = (SettingProto) subBuilder12.buildPartial();
                                    }
                                    this.bitField0_ |= 2048;
                                    break;
                                case 106:
                                    SettingProto.Builder subBuilder13 = null;
                                    if ((this.bitField0_ & 4096) == 4096) {
                                        subBuilder13 = (SettingProto.Builder) this.masterBalance_.toBuilder();
                                    }
                                    this.masterBalance_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                    if (subBuilder13 != null) {
                                        subBuilder13.mergeFrom((GeneratedMessageLite) this.masterBalance_);
                                        this.masterBalance_ = (SettingProto) subBuilder13.buildPartial();
                                    }
                                    this.bitField0_ |= 4096;
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
                        synchronized (Volume.class) {
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

        public static Volume getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Volume> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public List<SettingsOperationProto> getHistoricalOperationsList() {
        return this.historicalOperations_;
    }

    public List<? extends SettingsOperationProtoOrBuilder> getHistoricalOperationsOrBuilderList() {
        return this.historicalOperations_;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public int getHistoricalOperationsCount() {
        return this.historicalOperations_.size();
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingsOperationProto getHistoricalOperations(int index) {
        return this.historicalOperations_.get(index);
    }

    public SettingsOperationProtoOrBuilder getHistoricalOperationsOrBuilder(int index) {
        return this.historicalOperations_.get(index);
    }

    private void ensureHistoricalOperationsIsMutable() {
        if (!this.historicalOperations_.isModifiable()) {
            this.historicalOperations_ = GeneratedMessageLite.mutableCopy(this.historicalOperations_);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalOperations(int index, SettingsOperationProto value) {
        if (value != null) {
            ensureHistoricalOperationsIsMutable();
            this.historicalOperations_.set(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHistoricalOperations(int index, SettingsOperationProto.Builder builderForValue) {
        ensureHistoricalOperationsIsMutable();
        this.historicalOperations_.set(index, (SettingsOperationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalOperations(SettingsOperationProto value) {
        if (value != null) {
            ensureHistoricalOperationsIsMutable();
            this.historicalOperations_.add(value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalOperations(int index, SettingsOperationProto value) {
        if (value != null) {
            ensureHistoricalOperationsIsMutable();
            this.historicalOperations_.add(index, value);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalOperations(SettingsOperationProto.Builder builderForValue) {
        ensureHistoricalOperationsIsMutable();
        this.historicalOperations_.add((SettingsOperationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addHistoricalOperations(int index, SettingsOperationProto.Builder builderForValue) {
        ensureHistoricalOperationsIsMutable();
        this.historicalOperations_.add(index, (SettingsOperationProto) builderForValue.build());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAllHistoricalOperations(Iterable<? extends SettingsOperationProto> values) {
        ensureHistoricalOperationsIsMutable();
        AbstractMessageLite.addAll(values, this.historicalOperations_);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHistoricalOperations() {
        this.historicalOperations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeHistoricalOperations(int index) {
        ensureHistoricalOperationsIsMutable();
        this.historicalOperations_.remove(index);
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasAdvancedSettings() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getAdvancedSettings() {
        SettingProto settingProto = this.advancedSettings_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdvancedSettings(SettingProto value) {
        if (value != null) {
            this.advancedSettings_ = value;
            this.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAdvancedSettings(SettingProto.Builder builderForValue) {
        this.advancedSettings_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAdvancedSettings(SettingProto value) {
        SettingProto settingProto = this.advancedSettings_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.advancedSettings_ = value;
        } else {
            this.advancedSettings_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.advancedSettings_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAdvancedSettings() {
        this.advancedSettings_ = null;
        this.bitField0_ &= -2;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasAlarm() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Alarm getAlarm() {
        Alarm alarm = this.alarm_;
        return alarm == null ? Alarm.getDefaultInstance() : alarm;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarm(Alarm value) {
        if (value != null) {
            this.alarm_ = value;
            this.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setAlarm(Alarm.Builder builderForValue) {
        this.alarm_ = (Alarm) builderForValue.build();
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeAlarm(Alarm value) {
        Alarm alarm = this.alarm_;
        if (alarm == null || alarm == Alarm.getDefaultInstance()) {
            this.alarm_ = value;
        } else {
            this.alarm_ = (Alarm) ((Alarm.Builder) Alarm.newBuilder(this.alarm_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearAlarm() {
        this.alarm_ = null;
        this.bitField0_ &= -3;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasBluetooth() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Bluetooth getBluetooth() {
        Bluetooth bluetooth = this.bluetooth_;
        return bluetooth == null ? Bluetooth.getDefaultInstance() : bluetooth;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetooth(Bluetooth value) {
        if (value != null) {
            this.bluetooth_ = value;
            this.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBluetooth(Bluetooth.Builder builderForValue) {
        this.bluetooth_ = (Bluetooth) builderForValue.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeBluetooth(Bluetooth value) {
        Bluetooth bluetooth = this.bluetooth_;
        if (bluetooth == null || bluetooth == Bluetooth.getDefaultInstance()) {
            this.bluetooth_ = value;
        } else {
            this.bluetooth_ = (Bluetooth) ((Bluetooth.Builder) Bluetooth.newBuilder(this.bluetooth_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearBluetooth() {
        this.bluetooth_ = null;
        this.bitField0_ &= -5;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasDateFormat() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getDateFormat() {
        SettingProto settingProto = this.dateFormat_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDateFormat(SettingProto value) {
        if (value != null) {
            this.dateFormat_ = value;
            this.bitField0_ |= 8;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDateFormat(SettingProto.Builder builderForValue) {
        this.dateFormat_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDateFormat(SettingProto value) {
        SettingProto settingProto = this.dateFormat_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.dateFormat_ = value;
        } else {
            this.dateFormat_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.dateFormat_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDateFormat() {
        this.dateFormat_ = null;
        this.bitField0_ &= -9;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasDisplayColorMode() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getDisplayColorMode() {
        SettingProto settingProto = this.displayColorMode_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayColorMode(SettingProto value) {
        if (value != null) {
            this.displayColorMode_ = value;
            this.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDisplayColorMode(SettingProto.Builder builderForValue) {
        this.displayColorMode_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDisplayColorMode(SettingProto value) {
        SettingProto settingProto = this.displayColorMode_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.displayColorMode_ = value;
        } else {
            this.displayColorMode_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.displayColorMode_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDisplayColorMode() {
        this.displayColorMode_ = null;
        this.bitField0_ &= -17;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasDeveloperOptions() {
        return (this.bitField0_ & 32) == 32;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public DevOptions getDeveloperOptions() {
        DevOptions devOptions = this.developerOptions_;
        return devOptions == null ? DevOptions.getDefaultInstance() : devOptions;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeveloperOptions(DevOptions value) {
        if (value != null) {
            this.developerOptions_ = value;
            this.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDeveloperOptions(DevOptions.Builder builderForValue) {
        this.developerOptions_ = (DevOptions) builderForValue.build();
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDeveloperOptions(DevOptions value) {
        DevOptions devOptions = this.developerOptions_;
        if (devOptions == null || devOptions == DevOptions.getDefaultInstance()) {
            this.developerOptions_ = value;
        } else {
            this.developerOptions_ = (DevOptions) ((DevOptions.Builder) DevOptions.newBuilder(this.developerOptions_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDeveloperOptions() {
        this.developerOptions_ = null;
        this.bitField0_ &= -33;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasDtmfTone() {
        return (this.bitField0_ & 64) == 64;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public DtmfTone getDtmfTone() {
        DtmfTone dtmfTone = this.dtmfTone_;
        return dtmfTone == null ? DtmfTone.getDefaultInstance() : dtmfTone;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDtmfTone(DtmfTone value) {
        if (value != null) {
            this.dtmfTone_ = value;
            this.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDtmfTone(DtmfTone.Builder builderForValue) {
        this.dtmfTone_ = (DtmfTone) builderForValue.build();
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeDtmfTone(DtmfTone value) {
        DtmfTone dtmfTone = this.dtmfTone_;
        if (dtmfTone == null || dtmfTone == DtmfTone.getDefaultInstance()) {
            this.dtmfTone_ = value;
        } else {
            this.dtmfTone_ = (DtmfTone) ((DtmfTone.Builder) DtmfTone.newBuilder(this.dtmfTone_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 64;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearDtmfTone() {
        this.dtmfTone_ = null;
        this.bitField0_ &= -65;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasEggMode() {
        return (this.bitField0_ & 128) == 128;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getEggMode() {
        SettingProto settingProto = this.eggMode_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEggMode(SettingProto value) {
        if (value != null) {
            this.eggMode_ = value;
            this.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEggMode(SettingProto.Builder builderForValue) {
        this.eggMode_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeEggMode(SettingProto value) {
        SettingProto settingProto = this.eggMode_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.eggMode_ = value;
        } else {
            this.eggMode_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.eggMode_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 128;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEggMode() {
        this.eggMode_ = null;
        this.bitField0_ &= -129;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasEndButtonBehavior() {
        return (this.bitField0_ & 256) == 256;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getEndButtonBehavior() {
        SettingProto settingProto = this.endButtonBehavior_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndButtonBehavior(SettingProto value) {
        if (value != null) {
            this.endButtonBehavior_ = value;
            this.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setEndButtonBehavior(SettingProto.Builder builderForValue) {
        this.endButtonBehavior_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeEndButtonBehavior(SettingProto value) {
        SettingProto settingProto = this.endButtonBehavior_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.endButtonBehavior_ = value;
        } else {
            this.endButtonBehavior_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.endButtonBehavior_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 256;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearEndButtonBehavior() {
        this.endButtonBehavior_ = null;
        this.bitField0_ &= -257;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasFontScale() {
        return (this.bitField0_ & 512) == 512;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getFontScale() {
        SettingProto settingProto = this.fontScale_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFontScale(SettingProto value) {
        if (value != null) {
            this.fontScale_ = value;
            this.bitField0_ |= 512;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFontScale(SettingProto.Builder builderForValue) {
        this.fontScale_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeFontScale(SettingProto value) {
        SettingProto settingProto = this.fontScale_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.fontScale_ = value;
        } else {
            this.fontScale_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.fontScale_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 512;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFontScale() {
        this.fontScale_ = null;
        this.bitField0_ &= -513;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasHapticFeedback() {
        return (this.bitField0_ & 1024) == 1024;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public HapticFeedback getHapticFeedback() {
        HapticFeedback hapticFeedback = this.hapticFeedback_;
        return hapticFeedback == null ? HapticFeedback.getDefaultInstance() : hapticFeedback;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHapticFeedback(HapticFeedback value) {
        if (value != null) {
            this.hapticFeedback_ = value;
            this.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHapticFeedback(HapticFeedback.Builder builderForValue) {
        this.hapticFeedback_ = (HapticFeedback) builderForValue.build();
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHapticFeedback(HapticFeedback value) {
        HapticFeedback hapticFeedback = this.hapticFeedback_;
        if (hapticFeedback == null || hapticFeedback == HapticFeedback.getDefaultInstance()) {
            this.hapticFeedback_ = value;
        } else {
            this.hapticFeedback_ = (HapticFeedback) ((HapticFeedback.Builder) HapticFeedback.newBuilder(this.hapticFeedback_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1024;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHapticFeedback() {
        this.hapticFeedback_ = null;
        this.bitField0_ &= -1025;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasHearingAid() {
        return (this.bitField0_ & 2048) == 2048;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getHearingAid() {
        SettingProto settingProto = this.hearingAid_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHearingAid(SettingProto value) {
        if (value != null) {
            this.hearingAid_ = value;
            this.bitField0_ |= 2048;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setHearingAid(SettingProto.Builder builderForValue) {
        this.hearingAid_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeHearingAid(SettingProto value) {
        SettingProto settingProto = this.hearingAid_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.hearingAid_ = value;
        } else {
            this.hearingAid_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.hearingAid_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2048;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearHearingAid() {
        this.hearingAid_ = null;
        this.bitField0_ &= -2049;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasLockToAppEnabled() {
        return (this.bitField0_ & 4096) == 4096;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getLockToAppEnabled() {
        SettingProto settingProto = this.lockToAppEnabled_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockToAppEnabled(SettingProto value) {
        if (value != null) {
            this.lockToAppEnabled_ = value;
            this.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockToAppEnabled(SettingProto.Builder builderForValue) {
        this.lockToAppEnabled_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLockToAppEnabled(SettingProto value) {
        SettingProto settingProto = this.lockToAppEnabled_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.lockToAppEnabled_ = value;
        } else {
            this.lockToAppEnabled_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.lockToAppEnabled_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4096;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockToAppEnabled() {
        this.lockToAppEnabled_ = null;
        this.bitField0_ &= -4097;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasLockscreen() {
        return (this.bitField0_ & 8192) == 8192;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Lockscreen getLockscreen() {
        Lockscreen lockscreen = this.lockscreen_;
        return lockscreen == null ? Lockscreen.getDefaultInstance() : lockscreen;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockscreen(Lockscreen value) {
        if (value != null) {
            this.lockscreen_ = value;
            this.bitField0_ |= 8192;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLockscreen(Lockscreen.Builder builderForValue) {
        this.lockscreen_ = (Lockscreen) builderForValue.build();
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeLockscreen(Lockscreen value) {
        Lockscreen lockscreen = this.lockscreen_;
        if (lockscreen == null || lockscreen == Lockscreen.getDefaultInstance()) {
            this.lockscreen_ = value;
        } else {
            this.lockscreen_ = (Lockscreen) ((Lockscreen.Builder) Lockscreen.newBuilder(this.lockscreen_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8192;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLockscreen() {
        this.lockscreen_ = null;
        this.bitField0_ &= -8193;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasMediaButtonReceiver() {
        return (this.bitField0_ & 16384) == 16384;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getMediaButtonReceiver() {
        SettingProto settingProto = this.mediaButtonReceiver_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaButtonReceiver(SettingProto value) {
        if (value != null) {
            this.mediaButtonReceiver_ = value;
            this.bitField0_ |= 16384;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMediaButtonReceiver(SettingProto.Builder builderForValue) {
        this.mediaButtonReceiver_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeMediaButtonReceiver(SettingProto value) {
        SettingProto settingProto = this.mediaButtonReceiver_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.mediaButtonReceiver_ = value;
        } else {
            this.mediaButtonReceiver_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.mediaButtonReceiver_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16384;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearMediaButtonReceiver() {
        this.mediaButtonReceiver_ = null;
        this.bitField0_ &= -16385;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasNotification() {
        return (this.bitField0_ & 32768) == 32768;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Notification getNotification() {
        Notification notification = this.notification_;
        return notification == null ? Notification.getDefaultInstance() : notification;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotification(Notification value) {
        if (value != null) {
            this.notification_ = value;
            this.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNotification(Notification.Builder builderForValue) {
        this.notification_ = (Notification) builderForValue.build();
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeNotification(Notification value) {
        Notification notification = this.notification_;
        if (notification == null || notification == Notification.getDefaultInstance()) {
            this.notification_ = value;
        } else {
            this.notification_ = (Notification) ((Notification.Builder) Notification.newBuilder(this.notification_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 32768;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearNotification() {
        this.notification_ = null;
        this.bitField0_ &= -32769;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasPointerSpeed() {
        return (this.bitField0_ & 65536) == 65536;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getPointerSpeed() {
        SettingProto settingProto = this.pointerSpeed_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPointerSpeed(SettingProto value) {
        if (value != null) {
            this.pointerSpeed_ = value;
            this.bitField0_ |= 65536;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setPointerSpeed(SettingProto.Builder builderForValue) {
        this.pointerSpeed_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergePointerSpeed(SettingProto value) {
        SettingProto settingProto = this.pointerSpeed_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.pointerSpeed_ = value;
        } else {
            this.pointerSpeed_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.pointerSpeed_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 65536;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPointerSpeed() {
        this.pointerSpeed_ = null;
        this.bitField0_ &= -65537;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasRingtone() {
        return (this.bitField0_ & 131072) == 131072;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Ringtone getRingtone() {
        Ringtone ringtone = this.ringtone_;
        return ringtone == null ? Ringtone.getDefaultInstance() : ringtone;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRingtone(Ringtone value) {
        if (value != null) {
            this.ringtone_ = value;
            this.bitField0_ |= 131072;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRingtone(Ringtone.Builder builderForValue) {
        this.ringtone_ = (Ringtone) builderForValue.build();
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRingtone(Ringtone value) {
        Ringtone ringtone = this.ringtone_;
        if (ringtone == null || ringtone == Ringtone.getDefaultInstance()) {
            this.ringtone_ = value;
        } else {
            this.ringtone_ = (Ringtone) ((Ringtone.Builder) Ringtone.newBuilder(this.ringtone_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 131072;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRingtone() {
        this.ringtone_ = null;
        this.bitField0_ &= -131073;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasRotation() {
        return (this.bitField0_ & 262144) == 262144;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Rotation getRotation() {
        Rotation rotation = this.rotation_;
        return rotation == null ? Rotation.getDefaultInstance() : rotation;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(Rotation value) {
        if (value != null) {
            this.rotation_ = value;
            this.bitField0_ |= 262144;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setRotation(Rotation.Builder builderForValue) {
        this.rotation_ = (Rotation) builderForValue.build();
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeRotation(Rotation value) {
        Rotation rotation = this.rotation_;
        if (rotation == null || rotation == Rotation.getDefaultInstance()) {
            this.rotation_ = value;
        } else {
            this.rotation_ = (Rotation) ((Rotation.Builder) Rotation.newBuilder(this.rotation_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 262144;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRotation() {
        this.rotation_ = null;
        this.bitField0_ &= -262145;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasScreen() {
        return (this.bitField0_ & 524288) == 524288;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Screen getScreen() {
        Screen screen = this.screen_;
        return screen == null ? Screen.getDefaultInstance() : screen;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreen(Screen value) {
        if (value != null) {
            this.screen_ = value;
            this.bitField0_ |= 524288;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setScreen(Screen.Builder builderForValue) {
        this.screen_ = (Screen) builderForValue.build();
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeScreen(Screen value) {
        Screen screen = this.screen_;
        if (screen == null || screen == Screen.getDefaultInstance()) {
            this.screen_ = value;
        } else {
            this.screen_ = (Screen) ((Screen.Builder) Screen.newBuilder(this.screen_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 524288;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearScreen() {
        this.screen_ = null;
        this.bitField0_ &= -524289;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasSetupWizardHasRun() {
        return (this.bitField0_ & 1048576) == 1048576;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getSetupWizardHasRun() {
        SettingProto settingProto = this.setupWizardHasRun_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSetupWizardHasRun(SettingProto value) {
        if (value != null) {
            this.setupWizardHasRun_ = value;
            this.bitField0_ |= 1048576;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSetupWizardHasRun(SettingProto.Builder builderForValue) {
        this.setupWizardHasRun_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSetupWizardHasRun(SettingProto value) {
        SettingProto settingProto = this.setupWizardHasRun_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.setupWizardHasRun_ = value;
        } else {
            this.setupWizardHasRun_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.setupWizardHasRun_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1048576;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetupWizardHasRun() {
        this.setupWizardHasRun_ = null;
        this.bitField0_ &= -1048577;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasShowBatteryPercent() {
        return (this.bitField0_ & 2097152) == 2097152;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getShowBatteryPercent() {
        SettingProto settingProto = this.showBatteryPercent_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowBatteryPercent(SettingProto value) {
        if (value != null) {
            this.showBatteryPercent_ = value;
            this.bitField0_ |= 2097152;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowBatteryPercent(SettingProto.Builder builderForValue) {
        this.showBatteryPercent_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeShowBatteryPercent(SettingProto value) {
        SettingProto settingProto = this.showBatteryPercent_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.showBatteryPercent_ = value;
        } else {
            this.showBatteryPercent_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.showBatteryPercent_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 2097152;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShowBatteryPercent() {
        this.showBatteryPercent_ = null;
        this.bitField0_ &= -2097153;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasShowGtalkServiceStatus() {
        return (this.bitField0_ & 4194304) == 4194304;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getShowGtalkServiceStatus() {
        SettingProto settingProto = this.showGtalkServiceStatus_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowGtalkServiceStatus(SettingProto value) {
        if (value != null) {
            this.showGtalkServiceStatus_ = value;
            this.bitField0_ |= 4194304;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setShowGtalkServiceStatus(SettingProto.Builder builderForValue) {
        this.showGtalkServiceStatus_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeShowGtalkServiceStatus(SettingProto value) {
        SettingProto settingProto = this.showGtalkServiceStatus_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.showGtalkServiceStatus_ = value;
        } else {
            this.showGtalkServiceStatus_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.showGtalkServiceStatus_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 4194304;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearShowGtalkServiceStatus() {
        this.showGtalkServiceStatus_ = null;
        this.bitField0_ &= -4194305;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasSip() {
        return (this.bitField0_ & 8388608) == 8388608;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Sip getSip() {
        Sip sip = this.sip_;
        return sip == null ? Sip.getDefaultInstance() : sip;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSip(Sip value) {
        if (value != null) {
            this.sip_ = value;
            this.bitField0_ |= 8388608;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSip(Sip.Builder builderForValue) {
        this.sip_ = (Sip) builderForValue.build();
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSip(Sip value) {
        Sip sip = this.sip_;
        if (sip == null || sip == Sip.getDefaultInstance()) {
            this.sip_ = value;
        } else {
            this.sip_ = (Sip) ((Sip.Builder) Sip.newBuilder(this.sip_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 8388608;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSip() {
        this.sip_ = null;
        this.bitField0_ &= -8388609;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasSoundEffectsEnabled() {
        return (this.bitField0_ & 16777216) == 16777216;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getSoundEffectsEnabled() {
        SettingProto settingProto = this.soundEffectsEnabled_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSoundEffectsEnabled(SettingProto value) {
        if (value != null) {
            this.soundEffectsEnabled_ = value;
            this.bitField0_ |= 16777216;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSoundEffectsEnabled(SettingProto.Builder builderForValue) {
        this.soundEffectsEnabled_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSoundEffectsEnabled(SettingProto value) {
        SettingProto settingProto = this.soundEffectsEnabled_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.soundEffectsEnabled_ = value;
        } else {
            this.soundEffectsEnabled_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.soundEffectsEnabled_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 16777216;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSoundEffectsEnabled() {
        this.soundEffectsEnabled_ = null;
        this.bitField0_ &= -16777217;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasSystemLocales() {
        return (this.bitField0_ & 33554432) == 33554432;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getSystemLocales() {
        SettingProto settingProto = this.systemLocales_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemLocales(SettingProto value) {
        if (value != null) {
            this.systemLocales_ = value;
            this.bitField0_ |= 33554432;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSystemLocales(SettingProto.Builder builderForValue) {
        this.systemLocales_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeSystemLocales(SettingProto value) {
        SettingProto settingProto = this.systemLocales_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.systemLocales_ = value;
        } else {
            this.systemLocales_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.systemLocales_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 33554432;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSystemLocales() {
        this.systemLocales_ = null;
        this.bitField0_ &= -33554433;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasText() {
        return (this.bitField0_ & 67108864) == 67108864;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Text getText() {
        Text text = this.text_;
        return text == null ? Text.getDefaultInstance() : text;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setText(Text value) {
        if (value != null) {
            this.text_ = value;
            this.bitField0_ |= 67108864;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setText(Text.Builder builderForValue) {
        this.text_ = (Text) builderForValue.build();
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeText(Text value) {
        Text text = this.text_;
        if (text == null || text == Text.getDefaultInstance()) {
            this.text_ = value;
        } else {
            this.text_ = (Text) ((Text.Builder) Text.newBuilder(this.text_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 67108864;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearText() {
        this.text_ = null;
        this.bitField0_ &= -67108865;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasTime1224() {
        return (this.bitField0_ & 134217728) == 134217728;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getTime1224() {
        SettingProto settingProto = this.time1224_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTime1224(SettingProto value) {
        if (value != null) {
            this.time1224_ = value;
            this.bitField0_ |= 134217728;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTime1224(SettingProto.Builder builderForValue) {
        this.time1224_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTime1224(SettingProto value) {
        SettingProto settingProto = this.time1224_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.time1224_ = value;
        } else {
            this.time1224_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.time1224_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 134217728;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTime1224() {
        this.time1224_ = null;
        this.bitField0_ &= -134217729;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasTtyMode() {
        return (this.bitField0_ & 268435456) == 268435456;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getTtyMode() {
        SettingProto settingProto = this.ttyMode_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTtyMode(SettingProto value) {
        if (value != null) {
            this.ttyMode_ = value;
            this.bitField0_ |= 268435456;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setTtyMode(SettingProto.Builder builderForValue) {
        this.ttyMode_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeTtyMode(SettingProto value) {
        SettingProto settingProto = this.ttyMode_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.ttyMode_ = value;
        } else {
            this.ttyMode_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.ttyMode_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 268435456;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearTtyMode() {
        this.ttyMode_ = null;
        this.bitField0_ &= -268435457;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasVibrate() {
        return (this.bitField0_ & 536870912) == 536870912;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Vibrate getVibrate() {
        Vibrate vibrate = this.vibrate_;
        return vibrate == null ? Vibrate.getDefaultInstance() : vibrate;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVibrate(Vibrate value) {
        if (value != null) {
            this.vibrate_ = value;
            this.bitField0_ |= 536870912;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVibrate(Vibrate.Builder builderForValue) {
        this.vibrate_ = (Vibrate) builderForValue.build();
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVibrate(Vibrate value) {
        Vibrate vibrate = this.vibrate_;
        if (vibrate == null || vibrate == Vibrate.getDefaultInstance()) {
            this.vibrate_ = value;
        } else {
            this.vibrate_ = (Vibrate) ((Vibrate.Builder) Vibrate.newBuilder(this.vibrate_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 536870912;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVibrate() {
        this.vibrate_ = null;
        this.bitField0_ &= -536870913;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasVolume() {
        return (this.bitField0_ & 1073741824) == 1073741824;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public Volume getVolume() {
        Volume volume = this.volume_;
        return volume == null ? Volume.getDefaultInstance() : volume;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVolume(Volume value) {
        if (value != null) {
            this.volume_ = value;
            this.bitField0_ |= 1073741824;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setVolume(Volume.Builder builderForValue) {
        this.volume_ = (Volume) builderForValue.build();
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeVolume(Volume value) {
        Volume volume = this.volume_;
        if (volume == null || volume == Volume.getDefaultInstance()) {
            this.volume_ = value;
        } else {
            this.volume_ = (Volume) ((Volume.Builder) Volume.newBuilder(this.volume_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= 1073741824;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearVolume() {
        this.volume_ = null;
        this.bitField0_ &= -1073741825;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public boolean hasWhenToMakeWifiCalls() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }

    @Override // android.providers.settings.SystemSettingsProtoOrBuilder
    public SettingProto getWhenToMakeWifiCalls() {
        SettingProto settingProto = this.whenToMakeWifiCalls_;
        return settingProto == null ? SettingProto.getDefaultInstance() : settingProto;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhenToMakeWifiCalls(SettingProto value) {
        if (value != null) {
            this.whenToMakeWifiCalls_ = value;
            this.bitField0_ |= Integer.MIN_VALUE;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setWhenToMakeWifiCalls(SettingProto.Builder builderForValue) {
        this.whenToMakeWifiCalls_ = (SettingProto) builderForValue.build();
        this.bitField0_ |= Integer.MIN_VALUE;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mergeWhenToMakeWifiCalls(SettingProto value) {
        SettingProto settingProto = this.whenToMakeWifiCalls_;
        if (settingProto == null || settingProto == SettingProto.getDefaultInstance()) {
            this.whenToMakeWifiCalls_ = value;
        } else {
            this.whenToMakeWifiCalls_ = (SettingProto) ((SettingProto.Builder) SettingProto.newBuilder(this.whenToMakeWifiCalls_).mergeFrom((GeneratedMessageLite) value)).buildPartial();
        }
        this.bitField0_ |= Integer.MIN_VALUE;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearWhenToMakeWifiCalls() {
        this.whenToMakeWifiCalls_ = null;
        this.bitField0_ &= DataConnectionPowerStateEnum.DATA_CONNECTION_POWER_STATE_UNKNOWN_VALUE;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream output) throws IOException {
        for (int i = 0; i < this.historicalOperations_.size(); i++) {
            output.writeMessage(1, this.historicalOperations_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            output.writeMessage(2, getAdvancedSettings());
        }
        if ((this.bitField0_ & 2) == 2) {
            output.writeMessage(3, getAlarm());
        }
        if ((this.bitField0_ & 4) == 4) {
            output.writeMessage(4, getBluetooth());
        }
        if ((this.bitField0_ & 8) == 8) {
            output.writeMessage(5, getDateFormat());
        }
        if ((this.bitField0_ & 16) == 16) {
            output.writeMessage(6, getDisplayColorMode());
        }
        if ((this.bitField0_ & 32) == 32) {
            output.writeMessage(7, getDeveloperOptions());
        }
        if ((this.bitField0_ & 64) == 64) {
            output.writeMessage(8, getDtmfTone());
        }
        if ((this.bitField0_ & 128) == 128) {
            output.writeMessage(9, getEggMode());
        }
        if ((this.bitField0_ & 256) == 256) {
            output.writeMessage(10, getEndButtonBehavior());
        }
        if ((this.bitField0_ & 512) == 512) {
            output.writeMessage(11, getFontScale());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            output.writeMessage(12, getHapticFeedback());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            output.writeMessage(13, getHearingAid());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            output.writeMessage(14, getLockToAppEnabled());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            output.writeMessage(15, getLockscreen());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            output.writeMessage(16, getMediaButtonReceiver());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            output.writeMessage(17, getNotification());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            output.writeMessage(18, getPointerSpeed());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            output.writeMessage(19, getRingtone());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            output.writeMessage(20, getRotation());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            output.writeMessage(22, getScreen());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            output.writeMessage(23, getSetupWizardHasRun());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            output.writeMessage(24, getShowBatteryPercent());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            output.writeMessage(25, getShowGtalkServiceStatus());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            output.writeMessage(26, getSip());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            output.writeMessage(27, getSoundEffectsEnabled());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            output.writeMessage(28, getSystemLocales());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            output.writeMessage(29, getText());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            output.writeMessage(30, getTime1224());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            output.writeMessage(31, getTtyMode());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            output.writeMessage(32, getVibrate());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            output.writeMessage(33, getVolume());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            output.writeMessage(34, getWhenToMakeWifiCalls());
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
        for (int i = 0; i < this.historicalOperations_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(1, this.historicalOperations_.get(i));
        }
        if ((this.bitField0_ & 1) == 1) {
            size2 += CodedOutputStream.computeMessageSize(2, getAdvancedSettings());
        }
        if ((this.bitField0_ & 2) == 2) {
            size2 += CodedOutputStream.computeMessageSize(3, getAlarm());
        }
        if ((this.bitField0_ & 4) == 4) {
            size2 += CodedOutputStream.computeMessageSize(4, getBluetooth());
        }
        if ((this.bitField0_ & 8) == 8) {
            size2 += CodedOutputStream.computeMessageSize(5, getDateFormat());
        }
        if ((this.bitField0_ & 16) == 16) {
            size2 += CodedOutputStream.computeMessageSize(6, getDisplayColorMode());
        }
        if ((this.bitField0_ & 32) == 32) {
            size2 += CodedOutputStream.computeMessageSize(7, getDeveloperOptions());
        }
        if ((this.bitField0_ & 64) == 64) {
            size2 += CodedOutputStream.computeMessageSize(8, getDtmfTone());
        }
        if ((this.bitField0_ & 128) == 128) {
            size2 += CodedOutputStream.computeMessageSize(9, getEggMode());
        }
        if ((this.bitField0_ & 256) == 256) {
            size2 += CodedOutputStream.computeMessageSize(10, getEndButtonBehavior());
        }
        if ((this.bitField0_ & 512) == 512) {
            size2 += CodedOutputStream.computeMessageSize(11, getFontScale());
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size2 += CodedOutputStream.computeMessageSize(12, getHapticFeedback());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            size2 += CodedOutputStream.computeMessageSize(13, getHearingAid());
        }
        if ((this.bitField0_ & 4096) == 4096) {
            size2 += CodedOutputStream.computeMessageSize(14, getLockToAppEnabled());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            size2 += CodedOutputStream.computeMessageSize(15, getLockscreen());
        }
        if ((this.bitField0_ & 16384) == 16384) {
            size2 += CodedOutputStream.computeMessageSize(16, getMediaButtonReceiver());
        }
        if ((this.bitField0_ & 32768) == 32768) {
            size2 += CodedOutputStream.computeMessageSize(17, getNotification());
        }
        if ((this.bitField0_ & 65536) == 65536) {
            size2 += CodedOutputStream.computeMessageSize(18, getPointerSpeed());
        }
        if ((this.bitField0_ & 131072) == 131072) {
            size2 += CodedOutputStream.computeMessageSize(19, getRingtone());
        }
        if ((this.bitField0_ & 262144) == 262144) {
            size2 += CodedOutputStream.computeMessageSize(20, getRotation());
        }
        if ((this.bitField0_ & 524288) == 524288) {
            size2 += CodedOutputStream.computeMessageSize(22, getScreen());
        }
        if ((this.bitField0_ & 1048576) == 1048576) {
            size2 += CodedOutputStream.computeMessageSize(23, getSetupWizardHasRun());
        }
        if ((this.bitField0_ & 2097152) == 2097152) {
            size2 += CodedOutputStream.computeMessageSize(24, getShowBatteryPercent());
        }
        if ((this.bitField0_ & 4194304) == 4194304) {
            size2 += CodedOutputStream.computeMessageSize(25, getShowGtalkServiceStatus());
        }
        if ((this.bitField0_ & 8388608) == 8388608) {
            size2 += CodedOutputStream.computeMessageSize(26, getSip());
        }
        if ((this.bitField0_ & 16777216) == 16777216) {
            size2 += CodedOutputStream.computeMessageSize(27, getSoundEffectsEnabled());
        }
        if ((this.bitField0_ & 33554432) == 33554432) {
            size2 += CodedOutputStream.computeMessageSize(28, getSystemLocales());
        }
        if ((this.bitField0_ & 67108864) == 67108864) {
            size2 += CodedOutputStream.computeMessageSize(29, getText());
        }
        if ((this.bitField0_ & 134217728) == 134217728) {
            size2 += CodedOutputStream.computeMessageSize(30, getTime1224());
        }
        if ((this.bitField0_ & 268435456) == 268435456) {
            size2 += CodedOutputStream.computeMessageSize(31, getTtyMode());
        }
        if ((this.bitField0_ & 536870912) == 536870912) {
            size2 += CodedOutputStream.computeMessageSize(32, getVibrate());
        }
        if ((this.bitField0_ & 1073741824) == 1073741824) {
            size2 += CodedOutputStream.computeMessageSize(33, getVolume());
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            size2 += CodedOutputStream.computeMessageSize(34, getWhenToMakeWifiCalls());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = size3;
        return size3;
    }

    public static SystemSettingsProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemSettingsProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemSettingsProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static SystemSettingsProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static SystemSettingsProto parseFrom(InputStream input) throws IOException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemSettingsProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemSettingsProto parseDelimitedFrom(InputStream input) throws IOException {
        return (SystemSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemSettingsProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemSettingsProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static SystemSettingsProto parseFrom(CodedInputStream input) throws IOException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static SystemSettingsProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (SystemSettingsProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemSettingsProto prototype) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom((GeneratedMessageLite) prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemSettingsProto, Builder> implements SystemSettingsProtoOrBuilder {
        private Builder() {
            super(SystemSettingsProto.DEFAULT_INSTANCE);
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public List<SettingsOperationProto> getHistoricalOperationsList() {
            return Collections.unmodifiableList(((SystemSettingsProto) this.instance).getHistoricalOperationsList());
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public int getHistoricalOperationsCount() {
            return ((SystemSettingsProto) this.instance).getHistoricalOperationsCount();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingsOperationProto getHistoricalOperations(int index) {
            return ((SystemSettingsProto) this.instance).getHistoricalOperations(index);
        }

        public Builder setHistoricalOperations(int index, SettingsOperationProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHistoricalOperations((SystemSettingsProto) index, (int) value);
            return this;
        }

        public Builder setHistoricalOperations(int index, SettingsOperationProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHistoricalOperations((SystemSettingsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addHistoricalOperations(SettingsOperationProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).addHistoricalOperations((SystemSettingsProto) value);
            return this;
        }

        public Builder addHistoricalOperations(int index, SettingsOperationProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).addHistoricalOperations((SystemSettingsProto) index, (int) value);
            return this;
        }

        public Builder addHistoricalOperations(SettingsOperationProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).addHistoricalOperations((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder addHistoricalOperations(int index, SettingsOperationProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).addHistoricalOperations((SystemSettingsProto) index, (int) builderForValue);
            return this;
        }

        public Builder addAllHistoricalOperations(Iterable<? extends SettingsOperationProto> values) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).addAllHistoricalOperations(values);
            return this;
        }

        public Builder clearHistoricalOperations() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearHistoricalOperations();
            return this;
        }

        public Builder removeHistoricalOperations(int index) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).removeHistoricalOperations(index);
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasAdvancedSettings() {
            return ((SystemSettingsProto) this.instance).hasAdvancedSettings();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getAdvancedSettings() {
            return ((SystemSettingsProto) this.instance).getAdvancedSettings();
        }

        public Builder setAdvancedSettings(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setAdvancedSettings((SystemSettingsProto) value);
            return this;
        }

        public Builder setAdvancedSettings(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setAdvancedSettings((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeAdvancedSettings(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeAdvancedSettings(value);
            return this;
        }

        public Builder clearAdvancedSettings() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearAdvancedSettings();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasAlarm() {
            return ((SystemSettingsProto) this.instance).hasAlarm();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Alarm getAlarm() {
            return ((SystemSettingsProto) this.instance).getAlarm();
        }

        public Builder setAlarm(Alarm value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setAlarm((SystemSettingsProto) value);
            return this;
        }

        public Builder setAlarm(Alarm.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setAlarm((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeAlarm(Alarm value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeAlarm(value);
            return this;
        }

        public Builder clearAlarm() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearAlarm();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasBluetooth() {
            return ((SystemSettingsProto) this.instance).hasBluetooth();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Bluetooth getBluetooth() {
            return ((SystemSettingsProto) this.instance).getBluetooth();
        }

        public Builder setBluetooth(Bluetooth value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setBluetooth((SystemSettingsProto) value);
            return this;
        }

        public Builder setBluetooth(Bluetooth.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setBluetooth((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeBluetooth(Bluetooth value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeBluetooth(value);
            return this;
        }

        public Builder clearBluetooth() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearBluetooth();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasDateFormat() {
            return ((SystemSettingsProto) this.instance).hasDateFormat();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getDateFormat() {
            return ((SystemSettingsProto) this.instance).getDateFormat();
        }

        public Builder setDateFormat(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDateFormat((SystemSettingsProto) value);
            return this;
        }

        public Builder setDateFormat(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDateFormat((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeDateFormat(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeDateFormat(value);
            return this;
        }

        public Builder clearDateFormat() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearDateFormat();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasDisplayColorMode() {
            return ((SystemSettingsProto) this.instance).hasDisplayColorMode();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getDisplayColorMode() {
            return ((SystemSettingsProto) this.instance).getDisplayColorMode();
        }

        public Builder setDisplayColorMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDisplayColorMode((SystemSettingsProto) value);
            return this;
        }

        public Builder setDisplayColorMode(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDisplayColorMode((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeDisplayColorMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeDisplayColorMode(value);
            return this;
        }

        public Builder clearDisplayColorMode() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearDisplayColorMode();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasDeveloperOptions() {
            return ((SystemSettingsProto) this.instance).hasDeveloperOptions();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public DevOptions getDeveloperOptions() {
            return ((SystemSettingsProto) this.instance).getDeveloperOptions();
        }

        public Builder setDeveloperOptions(DevOptions value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDeveloperOptions((SystemSettingsProto) value);
            return this;
        }

        public Builder setDeveloperOptions(DevOptions.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDeveloperOptions((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeDeveloperOptions(DevOptions value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeDeveloperOptions(value);
            return this;
        }

        public Builder clearDeveloperOptions() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearDeveloperOptions();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasDtmfTone() {
            return ((SystemSettingsProto) this.instance).hasDtmfTone();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public DtmfTone getDtmfTone() {
            return ((SystemSettingsProto) this.instance).getDtmfTone();
        }

        public Builder setDtmfTone(DtmfTone value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDtmfTone((SystemSettingsProto) value);
            return this;
        }

        public Builder setDtmfTone(DtmfTone.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setDtmfTone((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeDtmfTone(DtmfTone value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeDtmfTone(value);
            return this;
        }

        public Builder clearDtmfTone() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearDtmfTone();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasEggMode() {
            return ((SystemSettingsProto) this.instance).hasEggMode();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getEggMode() {
            return ((SystemSettingsProto) this.instance).getEggMode();
        }

        public Builder setEggMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setEggMode((SystemSettingsProto) value);
            return this;
        }

        public Builder setEggMode(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setEggMode((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeEggMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeEggMode(value);
            return this;
        }

        public Builder clearEggMode() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearEggMode();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasEndButtonBehavior() {
            return ((SystemSettingsProto) this.instance).hasEndButtonBehavior();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getEndButtonBehavior() {
            return ((SystemSettingsProto) this.instance).getEndButtonBehavior();
        }

        public Builder setEndButtonBehavior(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setEndButtonBehavior((SystemSettingsProto) value);
            return this;
        }

        public Builder setEndButtonBehavior(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setEndButtonBehavior((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeEndButtonBehavior(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeEndButtonBehavior(value);
            return this;
        }

        public Builder clearEndButtonBehavior() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearEndButtonBehavior();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasFontScale() {
            return ((SystemSettingsProto) this.instance).hasFontScale();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getFontScale() {
            return ((SystemSettingsProto) this.instance).getFontScale();
        }

        public Builder setFontScale(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setFontScale((SystemSettingsProto) value);
            return this;
        }

        public Builder setFontScale(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setFontScale((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeFontScale(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeFontScale(value);
            return this;
        }

        public Builder clearFontScale() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearFontScale();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasHapticFeedback() {
            return ((SystemSettingsProto) this.instance).hasHapticFeedback();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public HapticFeedback getHapticFeedback() {
            return ((SystemSettingsProto) this.instance).getHapticFeedback();
        }

        public Builder setHapticFeedback(HapticFeedback value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHapticFeedback((SystemSettingsProto) value);
            return this;
        }

        public Builder setHapticFeedback(HapticFeedback.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHapticFeedback((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeHapticFeedback(HapticFeedback value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeHapticFeedback(value);
            return this;
        }

        public Builder clearHapticFeedback() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearHapticFeedback();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasHearingAid() {
            return ((SystemSettingsProto) this.instance).hasHearingAid();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getHearingAid() {
            return ((SystemSettingsProto) this.instance).getHearingAid();
        }

        public Builder setHearingAid(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHearingAid((SystemSettingsProto) value);
            return this;
        }

        public Builder setHearingAid(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setHearingAid((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeHearingAid(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeHearingAid(value);
            return this;
        }

        public Builder clearHearingAid() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearHearingAid();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasLockToAppEnabled() {
            return ((SystemSettingsProto) this.instance).hasLockToAppEnabled();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getLockToAppEnabled() {
            return ((SystemSettingsProto) this.instance).getLockToAppEnabled();
        }

        public Builder setLockToAppEnabled(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setLockToAppEnabled((SystemSettingsProto) value);
            return this;
        }

        public Builder setLockToAppEnabled(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setLockToAppEnabled((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeLockToAppEnabled(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeLockToAppEnabled(value);
            return this;
        }

        public Builder clearLockToAppEnabled() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearLockToAppEnabled();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasLockscreen() {
            return ((SystemSettingsProto) this.instance).hasLockscreen();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Lockscreen getLockscreen() {
            return ((SystemSettingsProto) this.instance).getLockscreen();
        }

        public Builder setLockscreen(Lockscreen value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setLockscreen((SystemSettingsProto) value);
            return this;
        }

        public Builder setLockscreen(Lockscreen.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setLockscreen((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeLockscreen(Lockscreen value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeLockscreen(value);
            return this;
        }

        public Builder clearLockscreen() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearLockscreen();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasMediaButtonReceiver() {
            return ((SystemSettingsProto) this.instance).hasMediaButtonReceiver();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getMediaButtonReceiver() {
            return ((SystemSettingsProto) this.instance).getMediaButtonReceiver();
        }

        public Builder setMediaButtonReceiver(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setMediaButtonReceiver((SystemSettingsProto) value);
            return this;
        }

        public Builder setMediaButtonReceiver(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setMediaButtonReceiver((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeMediaButtonReceiver(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeMediaButtonReceiver(value);
            return this;
        }

        public Builder clearMediaButtonReceiver() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearMediaButtonReceiver();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasNotification() {
            return ((SystemSettingsProto) this.instance).hasNotification();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Notification getNotification() {
            return ((SystemSettingsProto) this.instance).getNotification();
        }

        public Builder setNotification(Notification value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setNotification((SystemSettingsProto) value);
            return this;
        }

        public Builder setNotification(Notification.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setNotification((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeNotification(Notification value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeNotification(value);
            return this;
        }

        public Builder clearNotification() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearNotification();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasPointerSpeed() {
            return ((SystemSettingsProto) this.instance).hasPointerSpeed();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getPointerSpeed() {
            return ((SystemSettingsProto) this.instance).getPointerSpeed();
        }

        public Builder setPointerSpeed(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setPointerSpeed((SystemSettingsProto) value);
            return this;
        }

        public Builder setPointerSpeed(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setPointerSpeed((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergePointerSpeed(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergePointerSpeed(value);
            return this;
        }

        public Builder clearPointerSpeed() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearPointerSpeed();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasRingtone() {
            return ((SystemSettingsProto) this.instance).hasRingtone();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Ringtone getRingtone() {
            return ((SystemSettingsProto) this.instance).getRingtone();
        }

        public Builder setRingtone(Ringtone value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setRingtone((SystemSettingsProto) value);
            return this;
        }

        public Builder setRingtone(Ringtone.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setRingtone((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeRingtone(Ringtone value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeRingtone(value);
            return this;
        }

        public Builder clearRingtone() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearRingtone();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasRotation() {
            return ((SystemSettingsProto) this.instance).hasRotation();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Rotation getRotation() {
            return ((SystemSettingsProto) this.instance).getRotation();
        }

        public Builder setRotation(Rotation value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setRotation((SystemSettingsProto) value);
            return this;
        }

        public Builder setRotation(Rotation.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setRotation((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeRotation(Rotation value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeRotation(value);
            return this;
        }

        public Builder clearRotation() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearRotation();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasScreen() {
            return ((SystemSettingsProto) this.instance).hasScreen();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Screen getScreen() {
            return ((SystemSettingsProto) this.instance).getScreen();
        }

        public Builder setScreen(Screen value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setScreen((SystemSettingsProto) value);
            return this;
        }

        public Builder setScreen(Screen.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setScreen((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeScreen(Screen value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeScreen(value);
            return this;
        }

        public Builder clearScreen() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearScreen();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasSetupWizardHasRun() {
            return ((SystemSettingsProto) this.instance).hasSetupWizardHasRun();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getSetupWizardHasRun() {
            return ((SystemSettingsProto) this.instance).getSetupWizardHasRun();
        }

        public Builder setSetupWizardHasRun(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSetupWizardHasRun((SystemSettingsProto) value);
            return this;
        }

        public Builder setSetupWizardHasRun(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSetupWizardHasRun((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSetupWizardHasRun(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeSetupWizardHasRun(value);
            return this;
        }

        public Builder clearSetupWizardHasRun() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearSetupWizardHasRun();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasShowBatteryPercent() {
            return ((SystemSettingsProto) this.instance).hasShowBatteryPercent();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getShowBatteryPercent() {
            return ((SystemSettingsProto) this.instance).getShowBatteryPercent();
        }

        public Builder setShowBatteryPercent(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setShowBatteryPercent((SystemSettingsProto) value);
            return this;
        }

        public Builder setShowBatteryPercent(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setShowBatteryPercent((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeShowBatteryPercent(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeShowBatteryPercent(value);
            return this;
        }

        public Builder clearShowBatteryPercent() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearShowBatteryPercent();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasShowGtalkServiceStatus() {
            return ((SystemSettingsProto) this.instance).hasShowGtalkServiceStatus();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getShowGtalkServiceStatus() {
            return ((SystemSettingsProto) this.instance).getShowGtalkServiceStatus();
        }

        public Builder setShowGtalkServiceStatus(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setShowGtalkServiceStatus((SystemSettingsProto) value);
            return this;
        }

        public Builder setShowGtalkServiceStatus(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setShowGtalkServiceStatus((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeShowGtalkServiceStatus(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeShowGtalkServiceStatus(value);
            return this;
        }

        public Builder clearShowGtalkServiceStatus() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearShowGtalkServiceStatus();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasSip() {
            return ((SystemSettingsProto) this.instance).hasSip();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Sip getSip() {
            return ((SystemSettingsProto) this.instance).getSip();
        }

        public Builder setSip(Sip value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSip((SystemSettingsProto) value);
            return this;
        }

        public Builder setSip(Sip.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSip((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSip(Sip value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeSip(value);
            return this;
        }

        public Builder clearSip() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearSip();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasSoundEffectsEnabled() {
            return ((SystemSettingsProto) this.instance).hasSoundEffectsEnabled();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getSoundEffectsEnabled() {
            return ((SystemSettingsProto) this.instance).getSoundEffectsEnabled();
        }

        public Builder setSoundEffectsEnabled(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSoundEffectsEnabled((SystemSettingsProto) value);
            return this;
        }

        public Builder setSoundEffectsEnabled(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSoundEffectsEnabled((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSoundEffectsEnabled(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeSoundEffectsEnabled(value);
            return this;
        }

        public Builder clearSoundEffectsEnabled() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearSoundEffectsEnabled();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasSystemLocales() {
            return ((SystemSettingsProto) this.instance).hasSystemLocales();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getSystemLocales() {
            return ((SystemSettingsProto) this.instance).getSystemLocales();
        }

        public Builder setSystemLocales(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSystemLocales((SystemSettingsProto) value);
            return this;
        }

        public Builder setSystemLocales(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setSystemLocales((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeSystemLocales(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeSystemLocales(value);
            return this;
        }

        public Builder clearSystemLocales() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearSystemLocales();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasText() {
            return ((SystemSettingsProto) this.instance).hasText();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Text getText() {
            return ((SystemSettingsProto) this.instance).getText();
        }

        public Builder setText(Text value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setText((SystemSettingsProto) value);
            return this;
        }

        public Builder setText(Text.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setText((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeText(Text value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeText(value);
            return this;
        }

        public Builder clearText() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearText();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasTime1224() {
            return ((SystemSettingsProto) this.instance).hasTime1224();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getTime1224() {
            return ((SystemSettingsProto) this.instance).getTime1224();
        }

        public Builder setTime1224(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setTime1224((SystemSettingsProto) value);
            return this;
        }

        public Builder setTime1224(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setTime1224((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeTime1224(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeTime1224(value);
            return this;
        }

        public Builder clearTime1224() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearTime1224();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasTtyMode() {
            return ((SystemSettingsProto) this.instance).hasTtyMode();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getTtyMode() {
            return ((SystemSettingsProto) this.instance).getTtyMode();
        }

        public Builder setTtyMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setTtyMode((SystemSettingsProto) value);
            return this;
        }

        public Builder setTtyMode(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setTtyMode((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeTtyMode(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeTtyMode(value);
            return this;
        }

        public Builder clearTtyMode() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearTtyMode();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasVibrate() {
            return ((SystemSettingsProto) this.instance).hasVibrate();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Vibrate getVibrate() {
            return ((SystemSettingsProto) this.instance).getVibrate();
        }

        public Builder setVibrate(Vibrate value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setVibrate((SystemSettingsProto) value);
            return this;
        }

        public Builder setVibrate(Vibrate.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setVibrate((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeVibrate(Vibrate value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeVibrate(value);
            return this;
        }

        public Builder clearVibrate() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearVibrate();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasVolume() {
            return ((SystemSettingsProto) this.instance).hasVolume();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public Volume getVolume() {
            return ((SystemSettingsProto) this.instance).getVolume();
        }

        public Builder setVolume(Volume value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setVolume((SystemSettingsProto) value);
            return this;
        }

        public Builder setVolume(Volume.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setVolume((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeVolume(Volume value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeVolume(value);
            return this;
        }

        public Builder clearVolume() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearVolume();
            return this;
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public boolean hasWhenToMakeWifiCalls() {
            return ((SystemSettingsProto) this.instance).hasWhenToMakeWifiCalls();
        }

        @Override // android.providers.settings.SystemSettingsProtoOrBuilder
        public SettingProto getWhenToMakeWifiCalls() {
            return ((SystemSettingsProto) this.instance).getWhenToMakeWifiCalls();
        }

        public Builder setWhenToMakeWifiCalls(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setWhenToMakeWifiCalls((SystemSettingsProto) value);
            return this;
        }

        public Builder setWhenToMakeWifiCalls(SettingProto.Builder builderForValue) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).setWhenToMakeWifiCalls((SystemSettingsProto) builderForValue);
            return this;
        }

        public Builder mergeWhenToMakeWifiCalls(SettingProto value) {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).mergeWhenToMakeWifiCalls(value);
            return this;
        }

        public Builder clearWhenToMakeWifiCalls() {
            copyOnWrite();
            ((SystemSettingsProto) this.instance).clearWhenToMakeWifiCalls();
            return this;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE:
                return new SystemSettingsProto();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.historicalOperations_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) arg0;
                SystemSettingsProto other = (SystemSettingsProto) arg1;
                this.historicalOperations_ = visitor.visitList(this.historicalOperations_, other.historicalOperations_);
                this.advancedSettings_ = (SettingProto) visitor.visitMessage(this.advancedSettings_, other.advancedSettings_);
                this.alarm_ = (Alarm) visitor.visitMessage(this.alarm_, other.alarm_);
                this.bluetooth_ = (Bluetooth) visitor.visitMessage(this.bluetooth_, other.bluetooth_);
                this.dateFormat_ = (SettingProto) visitor.visitMessage(this.dateFormat_, other.dateFormat_);
                this.displayColorMode_ = (SettingProto) visitor.visitMessage(this.displayColorMode_, other.displayColorMode_);
                this.developerOptions_ = (DevOptions) visitor.visitMessage(this.developerOptions_, other.developerOptions_);
                this.dtmfTone_ = (DtmfTone) visitor.visitMessage(this.dtmfTone_, other.dtmfTone_);
                this.eggMode_ = (SettingProto) visitor.visitMessage(this.eggMode_, other.eggMode_);
                this.endButtonBehavior_ = (SettingProto) visitor.visitMessage(this.endButtonBehavior_, other.endButtonBehavior_);
                this.fontScale_ = (SettingProto) visitor.visitMessage(this.fontScale_, other.fontScale_);
                this.hapticFeedback_ = (HapticFeedback) visitor.visitMessage(this.hapticFeedback_, other.hapticFeedback_);
                this.hearingAid_ = (SettingProto) visitor.visitMessage(this.hearingAid_, other.hearingAid_);
                this.lockToAppEnabled_ = (SettingProto) visitor.visitMessage(this.lockToAppEnabled_, other.lockToAppEnabled_);
                this.lockscreen_ = (Lockscreen) visitor.visitMessage(this.lockscreen_, other.lockscreen_);
                this.mediaButtonReceiver_ = (SettingProto) visitor.visitMessage(this.mediaButtonReceiver_, other.mediaButtonReceiver_);
                this.notification_ = (Notification) visitor.visitMessage(this.notification_, other.notification_);
                this.pointerSpeed_ = (SettingProto) visitor.visitMessage(this.pointerSpeed_, other.pointerSpeed_);
                this.ringtone_ = (Ringtone) visitor.visitMessage(this.ringtone_, other.ringtone_);
                this.rotation_ = (Rotation) visitor.visitMessage(this.rotation_, other.rotation_);
                this.screen_ = (Screen) visitor.visitMessage(this.screen_, other.screen_);
                this.setupWizardHasRun_ = (SettingProto) visitor.visitMessage(this.setupWizardHasRun_, other.setupWizardHasRun_);
                this.showBatteryPercent_ = (SettingProto) visitor.visitMessage(this.showBatteryPercent_, other.showBatteryPercent_);
                this.showGtalkServiceStatus_ = (SettingProto) visitor.visitMessage(this.showGtalkServiceStatus_, other.showGtalkServiceStatus_);
                this.sip_ = (Sip) visitor.visitMessage(this.sip_, other.sip_);
                this.soundEffectsEnabled_ = (SettingProto) visitor.visitMessage(this.soundEffectsEnabled_, other.soundEffectsEnabled_);
                this.systemLocales_ = (SettingProto) visitor.visitMessage(this.systemLocales_, other.systemLocales_);
                this.text_ = (Text) visitor.visitMessage(this.text_, other.text_);
                this.time1224_ = (SettingProto) visitor.visitMessage(this.time1224_, other.time1224_);
                this.ttyMode_ = (SettingProto) visitor.visitMessage(this.ttyMode_, other.ttyMode_);
                this.vibrate_ = (Vibrate) visitor.visitMessage(this.vibrate_, other.vibrate_);
                this.volume_ = (Volume) visitor.visitMessage(this.volume_, other.volume_);
                this.whenToMakeWifiCalls_ = (SettingProto) visitor.visitMessage(this.whenToMakeWifiCalls_, other.whenToMakeWifiCalls_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= other.bitField0_;
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
                                if (!this.historicalOperations_.isModifiable()) {
                                    this.historicalOperations_ = GeneratedMessageLite.mutableCopy(this.historicalOperations_);
                                }
                                this.historicalOperations_.add((SettingsOperationProto) input.readMessage(SettingsOperationProto.parser(), extensionRegistry));
                                break;
                            case 18:
                                SettingProto.Builder subBuilder = null;
                                if ((this.bitField0_ & 1) == 1) {
                                    subBuilder = (SettingProto.Builder) this.advancedSettings_.toBuilder();
                                }
                                this.advancedSettings_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom((GeneratedMessageLite) this.advancedSettings_);
                                    this.advancedSettings_ = (SettingProto) subBuilder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                                break;
                            case 26:
                                Alarm.Builder subBuilder2 = null;
                                if ((this.bitField0_ & 2) == 2) {
                                    subBuilder2 = (Alarm.Builder) this.alarm_.toBuilder();
                                }
                                this.alarm_ = (Alarm) input.readMessage(Alarm.parser(), extensionRegistry);
                                if (subBuilder2 != null) {
                                    subBuilder2.mergeFrom((GeneratedMessageLite) this.alarm_);
                                    this.alarm_ = (Alarm) subBuilder2.buildPartial();
                                }
                                this.bitField0_ |= 2;
                                break;
                            case 34:
                                Bluetooth.Builder subBuilder3 = null;
                                if ((this.bitField0_ & 4) == 4) {
                                    subBuilder3 = (Bluetooth.Builder) this.bluetooth_.toBuilder();
                                }
                                this.bluetooth_ = (Bluetooth) input.readMessage(Bluetooth.parser(), extensionRegistry);
                                if (subBuilder3 != null) {
                                    subBuilder3.mergeFrom((GeneratedMessageLite) this.bluetooth_);
                                    this.bluetooth_ = (Bluetooth) subBuilder3.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 42:
                                SettingProto.Builder subBuilder4 = null;
                                if ((this.bitField0_ & 8) == 8) {
                                    subBuilder4 = (SettingProto.Builder) this.dateFormat_.toBuilder();
                                }
                                this.dateFormat_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder4 != null) {
                                    subBuilder4.mergeFrom((GeneratedMessageLite) this.dateFormat_);
                                    this.dateFormat_ = (SettingProto) subBuilder4.buildPartial();
                                }
                                this.bitField0_ |= 8;
                                break;
                            case 50:
                                SettingProto.Builder subBuilder5 = null;
                                if ((this.bitField0_ & 16) == 16) {
                                    subBuilder5 = (SettingProto.Builder) this.displayColorMode_.toBuilder();
                                }
                                this.displayColorMode_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder5 != null) {
                                    subBuilder5.mergeFrom((GeneratedMessageLite) this.displayColorMode_);
                                    this.displayColorMode_ = (SettingProto) subBuilder5.buildPartial();
                                }
                                this.bitField0_ |= 16;
                                break;
                            case 58:
                                DevOptions.Builder subBuilder6 = null;
                                if ((this.bitField0_ & 32) == 32) {
                                    subBuilder6 = (DevOptions.Builder) this.developerOptions_.toBuilder();
                                }
                                this.developerOptions_ = (DevOptions) input.readMessage(DevOptions.parser(), extensionRegistry);
                                if (subBuilder6 != null) {
                                    subBuilder6.mergeFrom((GeneratedMessageLite) this.developerOptions_);
                                    this.developerOptions_ = (DevOptions) subBuilder6.buildPartial();
                                }
                                this.bitField0_ |= 32;
                                break;
                            case 66:
                                DtmfTone.Builder subBuilder7 = null;
                                if ((this.bitField0_ & 64) == 64) {
                                    subBuilder7 = (DtmfTone.Builder) this.dtmfTone_.toBuilder();
                                }
                                this.dtmfTone_ = (DtmfTone) input.readMessage(DtmfTone.parser(), extensionRegistry);
                                if (subBuilder7 != null) {
                                    subBuilder7.mergeFrom((GeneratedMessageLite) this.dtmfTone_);
                                    this.dtmfTone_ = (DtmfTone) subBuilder7.buildPartial();
                                }
                                this.bitField0_ |= 64;
                                break;
                            case 74:
                                SettingProto.Builder subBuilder8 = null;
                                if ((this.bitField0_ & 128) == 128) {
                                    subBuilder8 = (SettingProto.Builder) this.eggMode_.toBuilder();
                                }
                                this.eggMode_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder8 != null) {
                                    subBuilder8.mergeFrom((GeneratedMessageLite) this.eggMode_);
                                    this.eggMode_ = (SettingProto) subBuilder8.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 82:
                                SettingProto.Builder subBuilder9 = null;
                                if ((this.bitField0_ & 256) == 256) {
                                    subBuilder9 = (SettingProto.Builder) this.endButtonBehavior_.toBuilder();
                                }
                                this.endButtonBehavior_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder9 != null) {
                                    subBuilder9.mergeFrom((GeneratedMessageLite) this.endButtonBehavior_);
                                    this.endButtonBehavior_ = (SettingProto) subBuilder9.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 90:
                                SettingProto.Builder subBuilder10 = null;
                                if ((this.bitField0_ & 512) == 512) {
                                    subBuilder10 = (SettingProto.Builder) this.fontScale_.toBuilder();
                                }
                                this.fontScale_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder10 != null) {
                                    subBuilder10.mergeFrom((GeneratedMessageLite) this.fontScale_);
                                    this.fontScale_ = (SettingProto) subBuilder10.buildPartial();
                                }
                                this.bitField0_ |= 512;
                                break;
                            case 98:
                                HapticFeedback.Builder subBuilder11 = null;
                                if ((this.bitField0_ & 1024) == 1024) {
                                    subBuilder11 = (HapticFeedback.Builder) this.hapticFeedback_.toBuilder();
                                }
                                this.hapticFeedback_ = (HapticFeedback) input.readMessage(HapticFeedback.parser(), extensionRegistry);
                                if (subBuilder11 != null) {
                                    subBuilder11.mergeFrom((GeneratedMessageLite) this.hapticFeedback_);
                                    this.hapticFeedback_ = (HapticFeedback) subBuilder11.buildPartial();
                                }
                                this.bitField0_ |= 1024;
                                break;
                            case 106:
                                SettingProto.Builder subBuilder12 = null;
                                if ((this.bitField0_ & 2048) == 2048) {
                                    subBuilder12 = (SettingProto.Builder) this.hearingAid_.toBuilder();
                                }
                                this.hearingAid_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder12 != null) {
                                    subBuilder12.mergeFrom((GeneratedMessageLite) this.hearingAid_);
                                    this.hearingAid_ = (SettingProto) subBuilder12.buildPartial();
                                }
                                this.bitField0_ |= 2048;
                                break;
                            case 114:
                                SettingProto.Builder subBuilder13 = null;
                                if ((this.bitField0_ & 4096) == 4096) {
                                    subBuilder13 = (SettingProto.Builder) this.lockToAppEnabled_.toBuilder();
                                }
                                this.lockToAppEnabled_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder13 != null) {
                                    subBuilder13.mergeFrom((GeneratedMessageLite) this.lockToAppEnabled_);
                                    this.lockToAppEnabled_ = (SettingProto) subBuilder13.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 122:
                                Lockscreen.Builder subBuilder14 = null;
                                if ((this.bitField0_ & 8192) == 8192) {
                                    subBuilder14 = (Lockscreen.Builder) this.lockscreen_.toBuilder();
                                }
                                this.lockscreen_ = (Lockscreen) input.readMessage(Lockscreen.parser(), extensionRegistry);
                                if (subBuilder14 != null) {
                                    subBuilder14.mergeFrom((GeneratedMessageLite) this.lockscreen_);
                                    this.lockscreen_ = (Lockscreen) subBuilder14.buildPartial();
                                }
                                this.bitField0_ |= 8192;
                                break;
                            case 130:
                                SettingProto.Builder subBuilder15 = null;
                                if ((this.bitField0_ & 16384) == 16384) {
                                    subBuilder15 = (SettingProto.Builder) this.mediaButtonReceiver_.toBuilder();
                                }
                                this.mediaButtonReceiver_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder15 != null) {
                                    subBuilder15.mergeFrom((GeneratedMessageLite) this.mediaButtonReceiver_);
                                    this.mediaButtonReceiver_ = (SettingProto) subBuilder15.buildPartial();
                                }
                                this.bitField0_ |= 16384;
                                break;
                            case 138:
                                Notification.Builder subBuilder16 = null;
                                if ((this.bitField0_ & 32768) == 32768) {
                                    subBuilder16 = (Notification.Builder) this.notification_.toBuilder();
                                }
                                this.notification_ = (Notification) input.readMessage(Notification.parser(), extensionRegistry);
                                if (subBuilder16 != null) {
                                    subBuilder16.mergeFrom((GeneratedMessageLite) this.notification_);
                                    this.notification_ = (Notification) subBuilder16.buildPartial();
                                }
                                this.bitField0_ |= 32768;
                                break;
                            case 146:
                                SettingProto.Builder subBuilder17 = null;
                                if ((this.bitField0_ & 65536) == 65536) {
                                    subBuilder17 = (SettingProto.Builder) this.pointerSpeed_.toBuilder();
                                }
                                this.pointerSpeed_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder17 != null) {
                                    subBuilder17.mergeFrom((GeneratedMessageLite) this.pointerSpeed_);
                                    this.pointerSpeed_ = (SettingProto) subBuilder17.buildPartial();
                                }
                                this.bitField0_ |= 65536;
                                break;
                            case 154:
                                Ringtone.Builder subBuilder18 = null;
                                if ((this.bitField0_ & 131072) == 131072) {
                                    subBuilder18 = (Ringtone.Builder) this.ringtone_.toBuilder();
                                }
                                this.ringtone_ = (Ringtone) input.readMessage(Ringtone.parser(), extensionRegistry);
                                if (subBuilder18 != null) {
                                    subBuilder18.mergeFrom((GeneratedMessageLite) this.ringtone_);
                                    this.ringtone_ = (Ringtone) subBuilder18.buildPartial();
                                }
                                this.bitField0_ |= 131072;
                                break;
                            case 162:
                                Rotation.Builder subBuilder19 = null;
                                if ((this.bitField0_ & 262144) == 262144) {
                                    subBuilder19 = (Rotation.Builder) this.rotation_.toBuilder();
                                }
                                this.rotation_ = (Rotation) input.readMessage(Rotation.parser(), extensionRegistry);
                                if (subBuilder19 != null) {
                                    subBuilder19.mergeFrom((GeneratedMessageLite) this.rotation_);
                                    this.rotation_ = (Rotation) subBuilder19.buildPartial();
                                }
                                this.bitField0_ |= 262144;
                                break;
                            case 178:
                                Screen.Builder subBuilder20 = null;
                                if ((this.bitField0_ & 524288) == 524288) {
                                    subBuilder20 = (Screen.Builder) this.screen_.toBuilder();
                                }
                                this.screen_ = (Screen) input.readMessage(Screen.parser(), extensionRegistry);
                                if (subBuilder20 != null) {
                                    subBuilder20.mergeFrom((GeneratedMessageLite) this.screen_);
                                    this.screen_ = (Screen) subBuilder20.buildPartial();
                                }
                                this.bitField0_ |= 524288;
                                break;
                            case AtomsProto.Atom.TOMB_STONE_OCCURRED_FIELD_NUMBER /*{ENCODED_INT: 186}*/:
                                SettingProto.Builder subBuilder21 = null;
                                if ((this.bitField0_ & 1048576) == 1048576) {
                                    subBuilder21 = (SettingProto.Builder) this.setupWizardHasRun_.toBuilder();
                                }
                                this.setupWizardHasRun_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder21 != null) {
                                    subBuilder21.mergeFrom((GeneratedMessageLite) this.setupWizardHasRun_);
                                    this.setupWizardHasRun_ = (SettingProto) subBuilder21.buildPartial();
                                }
                                this.bitField0_ |= 1048576;
                                break;
                            case AtomsProto.Atom.MEDIAMETRICS_AUDIOTRACK_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 194}*/:
                                SettingProto.Builder subBuilder22 = null;
                                if ((this.bitField0_ & 2097152) == 2097152) {
                                    subBuilder22 = (SettingProto.Builder) this.showBatteryPercent_.toBuilder();
                                }
                                this.showBatteryPercent_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder22 != null) {
                                    subBuilder22.mergeFrom((GeneratedMessageLite) this.showBatteryPercent_);
                                    this.showBatteryPercent_ = (SettingProto) subBuilder22.buildPartial();
                                }
                                this.bitField0_ |= 2097152;
                                break;
                            case PROCESS_STATS_SUMMARY_VALUE:
                                SettingProto.Builder subBuilder23 = null;
                                if ((this.bitField0_ & 4194304) == 4194304) {
                                    subBuilder23 = (SettingProto.Builder) this.showGtalkServiceStatus_.toBuilder();
                                }
                                this.showGtalkServiceStatus_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder23 != null) {
                                    subBuilder23.mergeFrom((GeneratedMessageLite) this.showGtalkServiceStatus_);
                                    this.showGtalkServiceStatus_ = (SettingProto) subBuilder23.buildPartial();
                                }
                                this.bitField0_ |= 4194304;
                                break;
                            case AtomsProto.Atom.LOCATION_MANAGER_API_USAGE_REPORTED_FIELD_NUMBER /*{ENCODED_INT: 210}*/:
                                Sip.Builder subBuilder24 = null;
                                if ((this.bitField0_ & 8388608) == 8388608) {
                                    subBuilder24 = (Sip.Builder) this.sip_.toBuilder();
                                }
                                this.sip_ = (Sip) input.readMessage(Sip.parser(), extensionRegistry);
                                if (subBuilder24 != null) {
                                    subBuilder24.mergeFrom((GeneratedMessageLite) this.sip_);
                                    this.sip_ = (Sip) subBuilder24.buildPartial();
                                }
                                this.bitField0_ |= 8388608;
                                break;
                            case AtomsProto.Atom.PERMISSION_APPS_FRAGMENT_VIEWED_FIELD_NUMBER /*{ENCODED_INT: 218}*/:
                                SettingProto.Builder subBuilder25 = null;
                                if ((this.bitField0_ & 16777216) == 16777216) {
                                    subBuilder25 = (SettingProto.Builder) this.soundEffectsEnabled_.toBuilder();
                                }
                                this.soundEffectsEnabled_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder25 != null) {
                                    subBuilder25.mergeFrom((GeneratedMessageLite) this.soundEffectsEnabled_);
                                    this.soundEffectsEnabled_ = (SettingProto) subBuilder25.buildPartial();
                                }
                                this.bitField0_ |= 16777216;
                                break;
                            case ACTION_SEARCH_RESULTS_VALUE:
                                SettingProto.Builder subBuilder26 = null;
                                if ((this.bitField0_ & 33554432) == 33554432) {
                                    subBuilder26 = (SettingProto.Builder) this.systemLocales_.toBuilder();
                                }
                                this.systemLocales_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder26 != null) {
                                    subBuilder26.mergeFrom((GeneratedMessageLite) this.systemLocales_);
                                    this.systemLocales_ = (SettingProto) subBuilder26.buildPartial();
                                }
                                this.bitField0_ |= 33554432;
                                break;
                            case 234:
                                Text.Builder subBuilder27 = null;
                                if ((this.bitField0_ & 67108864) == 67108864) {
                                    subBuilder27 = (Text.Builder) this.text_.toBuilder();
                                }
                                this.text_ = (Text) input.readMessage(Text.parser(), extensionRegistry);
                                if (subBuilder27 != null) {
                                    subBuilder27.mergeFrom((GeneratedMessageLite) this.text_);
                                    this.text_ = (Text) subBuilder27.buildPartial();
                                }
                                this.bitField0_ |= 67108864;
                                break;
                            case FINGERPRINT_ENROLL_FINISH_VALUE:
                                SettingProto.Builder subBuilder28 = null;
                                if ((this.bitField0_ & 134217728) == 134217728) {
                                    subBuilder28 = (SettingProto.Builder) this.time1224_.toBuilder();
                                }
                                this.time1224_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder28 != null) {
                                    subBuilder28.mergeFrom((GeneratedMessageLite) this.time1224_);
                                    this.time1224_ = (SettingProto) subBuilder28.buildPartial();
                                }
                                this.bitField0_ |= 134217728;
                                break;
                            case NS_T_TSIG_VALUE:
                                SettingProto.Builder subBuilder29 = null;
                                if ((this.bitField0_ & 268435456) == 268435456) {
                                    subBuilder29 = (SettingProto.Builder) this.ttyMode_.toBuilder();
                                }
                                this.ttyMode_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder29 != null) {
                                    subBuilder29.mergeFrom((GeneratedMessageLite) this.ttyMode_);
                                    this.ttyMode_ = (SettingProto) subBuilder29.buildPartial();
                                }
                                this.bitField0_ |= 268435456;
                                break;
                            case BACKGROUND_CHECK_SUMMARY_VALUE:
                                Vibrate.Builder subBuilder30 = null;
                                if ((this.bitField0_ & 536870912) == 536870912) {
                                    subBuilder30 = (Vibrate.Builder) this.vibrate_.toBuilder();
                                }
                                this.vibrate_ = (Vibrate) input.readMessage(Vibrate.parser(), extensionRegistry);
                                if (subBuilder30 != null) {
                                    subBuilder30.mergeFrom((GeneratedMessageLite) this.vibrate_);
                                    this.vibrate_ = (Vibrate) subBuilder30.buildPartial();
                                }
                                this.bitField0_ |= 536870912;
                                break;
                            case 266:
                                Volume.Builder subBuilder31 = null;
                                if ((this.bitField0_ & 1073741824) == 1073741824) {
                                    subBuilder31 = (Volume.Builder) this.volume_.toBuilder();
                                }
                                this.volume_ = (Volume) input.readMessage(Volume.parser(), extensionRegistry);
                                if (subBuilder31 != null) {
                                    subBuilder31.mergeFrom((GeneratedMessageLite) this.volume_);
                                    this.volume_ = (Volume) subBuilder31.buildPartial();
                                }
                                this.bitField0_ |= 1073741824;
                                break;
                            case 274:
                                SettingProto.Builder subBuilder32 = null;
                                if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                                    subBuilder32 = (SettingProto.Builder) this.whenToMakeWifiCalls_.toBuilder();
                                }
                                this.whenToMakeWifiCalls_ = (SettingProto) input.readMessage(SettingProto.parser(), extensionRegistry);
                                if (subBuilder32 != null) {
                                    subBuilder32.mergeFrom((GeneratedMessageLite) this.whenToMakeWifiCalls_);
                                    this.whenToMakeWifiCalls_ = (SettingProto) subBuilder32.buildPartial();
                                }
                                this.bitField0_ |= Integer.MIN_VALUE;
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
                    synchronized (SystemSettingsProto.class) {
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

    public static SystemSettingsProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemSettingsProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
